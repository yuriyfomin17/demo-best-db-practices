package com.example.demobestdbpractices.repository;

import com.example.demobestdbpractices.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
}
