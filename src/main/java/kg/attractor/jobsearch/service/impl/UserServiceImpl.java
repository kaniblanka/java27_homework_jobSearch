package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserCreateDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    private UserDto mapToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getAge(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAvatar(),
                user.getAccountType()
        );
    }

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) throws CreateEntryException {
        log.info("Creating new user with email: {}", userCreateDto.getEmail());

        if (userDao.findByEmail(userCreateDto.getEmail()).isPresent()) {
            throw new CreateEntryException("User with this email already exists");
        }

        User user = new User();
        user.setName(userCreateDto.getName());
        user.setSurname(userCreateDto.getSurname());
        user.setAge(userCreateDto.getAge());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setAvatar(userCreateDto.getAvatar());
        user.setAccountType(userCreateDto.getAccountType());
        user.setEnabled(true);

        Long userId = userDao.create(user);

        if (userId == null) {
            throw new CreateEntryException("User was not created");
        }

        if ("CANDIDATE".equals(user.getAccountType())) {
            userDao.addAuthorityToUser(userId, "CREATE_RESUME");
        }

        if ("EMPLOYER".equals(user.getAccountType())) {
            userDao.addAuthorityToUser(userId, "CREATE_VACANCY");
        }

        log.info("User created successfully with id: {}", userId);

        user.setId(userId);
        return mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users");
        return userDao.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserDto findById(Long id) throws UserNotFoundException {
        log.info("Getting user by id: {}", id);
        return mapToDto(
                userDao.findById(id)
                        .orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {
        log.info("Getting user by email: {}", email);
        return mapToDto(
                userDao.findByEmail(email)
                        .orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("Checking if user exists by email: {}", email);
        return userDao.findByEmail(email).isPresent();
    }

    @Override
    public List<UserDto> findByName(String name) {
        log.info("Getting users by name: {}", name);
        return userDao.findByName(name)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserDto findByPhone(String phone) throws UserNotFoundException {
        log.info("Getting user by phone: {}", phone);
        return mapToDto(
                userDao.findByPhone(phone)
                        .orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public void updateProfile(Long id, UserEditDto userEditDto) throws UserNotFoundException, UpdateEntryException {
        log.info("Updating user profile with id: {}", id);

        userDao.findById(id).orElseThrow(UserNotFoundException::new);

        boolean updated = userDao.updateProfile(
                id,
                userEditDto.getName(),
                userEditDto.getSurname(),
                userEditDto.getAge(),
                userEditDto.getEmail(),
                userEditDto.getPhoneNumber(),
                userEditDto.getAvatar()
        );

        if (!updated) {
            throw new UpdateEntryException("User profile was not updated");
        }
    }
}