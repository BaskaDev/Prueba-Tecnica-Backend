package com.prueba.backend.repository;

import com.prueba.backend.entity.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryRepository extends JpaRepository<UserStory,Integer> {
}
