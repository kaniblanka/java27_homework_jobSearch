package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto findById(Long id) throws UserNotFoundException;
    UserDto findByEmail(String email) throws UserNotFoundException;
    boolean existsByEmail(String email);
    List<UserDto> findByName(String name);
    UserDto findByPhone(String phone) throws UserNotFoundException;
    void updateProfile(Long id, UserEditDto userEditDto) throws UserNotFoundException, UpdateEntryException;
    UserDto createUser(UserCreateDto userCreateDto) throws CreateEntryException;
}