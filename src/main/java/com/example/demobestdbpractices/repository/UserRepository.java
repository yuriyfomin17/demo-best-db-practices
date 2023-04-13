package com.example.demobestdbpractices.repository;

import com.example.demobestdbpractices.controller.dto.UserDto;
import com.example.demobestdbpractices.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface UserRepository extends Repository<User, Long>, CustomUserRepository {

    @Query("select new com.example.demobestdbpractices.controller.dto.UserDto(u.firstName, u.lastName, count(n)) " +
            "from User u join u.notes n " +
            "where u.id >=:startId and u.id < :endId group by u.id")
    List<UserDto> findAllByIdWithNotesCountBetween(long startId , long endId);

}
