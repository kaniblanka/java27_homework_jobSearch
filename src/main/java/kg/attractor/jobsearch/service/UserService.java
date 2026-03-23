package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto findById(Long id);

    UserDto findByEmail(String email);

    boolean existsByEmail(String email);

    List<UserDto> findByName(String name);

    UserDto findByPhone(String phone);

    boolean updateProfile(Long id, UserEditDto userEditDto);
}