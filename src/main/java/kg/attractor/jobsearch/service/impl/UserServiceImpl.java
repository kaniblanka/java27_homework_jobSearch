package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

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
    public List<UserDto> getAllUsers() {
        return userDao.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserDto findById(Long id) throws UserNotFoundException {
        return mapToDto(
                userDao.findById(id)
                        .orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {
        return mapToDto(
                userDao.findByEmail(email)
                        .orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.findByEmail(email).isPresent();
    }

    @Override
    public List<UserDto> findByName(String name) {
        return userDao.findByName(name)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserDto findByPhone(String phone) throws UserNotFoundException {
        return mapToDto(
                userDao.findByPhone(phone)
                        .orElseThrow(UserNotFoundException::new)
        );
    }

    @Override
    public void updateProfile(Long id, UserEditDto userEditDto) throws UserNotFoundException, UpdateEntryException {
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

    log.info("Getting all users");
log.info("Getting user by id: {}", id);
log.info("Getting user by email: {}", email);
log.info("Updating user profile, id: {}", id);
}