package com.faculty.demo.todo;

import com.faculty.demo.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data

public class Todo {
    @Id   //PK
    @GeneratedValue         //Sequence
    private Long id;
    private String title;
    private String description;
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(String title, String description, Status status, User user) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }
}
