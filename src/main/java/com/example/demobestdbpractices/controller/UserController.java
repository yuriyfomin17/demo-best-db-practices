package com.example.demobestdbpractices.controller;

import com.example.demobestdbpractices.controller.dto.UserDto;
import com.example.demobestdbpractices.model.User;
import com.example.demobestdbpractices.repository.CustomUserRepository;
import com.example.demobestdbpractices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/wn/range/{startId}/{endId}")
    public List<UserDto> getUsersInRangeWithNotesCount(@PathVariable long startId, @PathVariable long endId) {
        return userRepository.findAllByIdWithNotesCountBetween(startId, endId);
    }

    @GetMapping("/wnr/range/{startId}/{endId}")
    public List<User> getUsersInRangeWithNotesCountAndReminders(@PathVariable long startId, @PathVariable long endId) {
        return userRepository.findAllByIdBetween(startId, endId);
    }




}
