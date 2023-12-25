package com.faculty.demo.dto;

import com.faculty.demo.todo.Status;
import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@AllArgsConstructor
public class TodoResponseDTO {
    private Long id;
    private String title, description;
    private Status status;
}
