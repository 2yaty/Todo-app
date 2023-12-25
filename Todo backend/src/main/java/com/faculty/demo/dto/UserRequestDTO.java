package com.faculty.demo.dto;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
