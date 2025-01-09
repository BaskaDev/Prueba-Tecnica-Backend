package com.prueba.backend.service;


import com.prueba.backend.entity.UserStory;
import com.prueba.backend.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStoryService {

    @Autowired
    private UserStoryRepository userStoryRepository;

    public UserStory create(UserStory userStory) {
        return userStoryRepository.save(userStory);
    }

    public List<UserStory> readAll() {
        return userStoryRepository.findAll();
    }

    public UserStory findById(Integer id) {
        for (UserStory u : userStoryRepository.findAll()) {
            if (u.getId_users_stories().equals(id)) {
                return u;
            }
        }
        return null;
    }

    public UserStory updateUserStory(Integer id, UserStory userStoryNew) {
        UserStory existingUserStory = this.findById(id);

        if (existingUserStory != null) {
            existingUserStory.setTitle_users_stories(userStoryNew.getTitle_users_stories());
            if (userStoryNew.getProject() != null) {
                existingUserStory .setProject(userStoryNew.getProject());
            }
            return userStoryRepository.save(existingUserStory);
        }

        return null;
    }

    public UserStory deleteUserStory(Integer id) {
       UserStory userStory = findById(id);
        userStoryRepository.delete(userStory);
        return userStory;

    }







}
