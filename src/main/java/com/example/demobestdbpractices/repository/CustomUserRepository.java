package com.example.demobestdbpractices.repository;

import com.example.demobestdbpractices.model.User;

import java.util.List;

public interface CustomUserRepository {
    List<User> findAllByIdBetween(long startId , long endId);
}
