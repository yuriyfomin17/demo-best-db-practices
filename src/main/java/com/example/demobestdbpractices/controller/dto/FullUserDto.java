package com.example.demobestdbpractices.controller.dto;

import java.util.List;

public record FullUserDto(
        int id,
        String firstName,
        String lastName,
        List<String> notes,
        List<String> reminders
) {
}
