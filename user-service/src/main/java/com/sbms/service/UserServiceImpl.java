package com.sbms.service;

import com.sbms.entity.User;
import com.sbms.repository.UserRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow( ()-> new RuntimeException(" Not Found"));
    }

    @Override
    public User updateUser(Long id, User userDetails) {
        User savedUser = getUserById(id);
        savedUser.setName(userDetails.getName());
        savedUser.setEmail(userDetails.getEmail());

        return userRepository.save(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);

    }
}
