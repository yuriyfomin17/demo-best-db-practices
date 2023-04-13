package com.example.demobestdbpractices.controller.dto;

public record UserDto(
        String firstName,
        String lastName,
        long notesCount
) {
}
