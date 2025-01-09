package com.prueba.backend.service;

import com.prueba.backend.entity.User;
import com.prueba.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User createUser (User user){
        return userRepository.save(user);
    }

    public User findById(Integer id){
        for (User u : userRepository.findAll()) {
            if(u.getId_user().equals(id)){
                return u;
            }
        }
        return null;
    }

    public User findByUsername(String username){
        for(User u : userRepository.findAll()){
            if(u.getUsername_user().equals(username)){
                return u;
            }
        }
        return null;
    }

    public User updateUser(Integer id, User userNew) {
        User existingUser = this.findById(id);
        if(existingUser != null){
            existingUser.setUsername_user(userNew.getUsername_user());
            existingUser.setPassword_user(userNew.getPassword_user());
            if(existingUser.getCompany() != null){
                existingUser.setCompany(userNew.getCompany());
            }
            return userRepository.save(existingUser);
        }
        return null;
    }

    public User deleteUser(Integer id){
        User user = findById(id);
        userRepository.delete(user);
        return user;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
