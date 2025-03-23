package com.sbms.service;

import com.sbms.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    public List<User> getAllUsers();
    public User getUserById(Long id);
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);

}
