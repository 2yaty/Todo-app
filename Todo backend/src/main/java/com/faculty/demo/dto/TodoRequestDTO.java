package com.faculty.demo.dto;

import com.faculty.demo.todo.Status;

public record TodoRequestDTO(
    String title,
    String description,
        Status status
        )
{

}
