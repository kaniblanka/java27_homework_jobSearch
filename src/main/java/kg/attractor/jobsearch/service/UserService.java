package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User findById(Long id);
    User findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByName(String name);
    User findByPhone(String phone);
}