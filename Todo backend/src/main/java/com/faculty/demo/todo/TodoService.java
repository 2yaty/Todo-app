package com.faculty.demo.todo;

import com.faculty.demo.dto.TodoRequestDTO;
import com.faculty.demo.dto.TodoResponseDTO;
import com.faculty.demo.exception.ApiRequestException;
import com.faculty.demo.user.User;
import com.faculty.demo.user.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserService userService;

    public void addNewTodo(TodoRequestDTO todoRequestDTO, Long userId) {
        User user = userService.getUserById(userId);

        Todo todo = new Todo(
                todoRequestDTO.title(),
                todoRequestDTO.description(),
                todoRequestDTO.status(),
                user
        );
        todoRepository.save(todo);
    }

    public void deleteTodoById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ApiRequestException("Todo with id " + id + " does not exist!");
        }
        todoRepository.deleteById(id);
    }

    public void updateTodoById(Long id, Todo todo) {
        if (!todoRepository.existsById(id)) {
            throw new ApiRequestException("Todo with id " + id + " does not exist!");
        }
        todoRepository.save(todo);
    }

    public List<TodoResponseDTO> getAllTodos(Long userId) {
        if (!userService.checkIfUserExistsById(userId)) {
            throw new ApiRequestException("User with id " + userId + " does not exist!");
        }
        List<TodoResponseDTO> lst = new ArrayList<>();
        todoRepository.findAllByUser(userId).forEach(
                todo -> {
                    lst.add(
                            new TodoResponseDTO(
                                    todo.getId(),
                                    todo.getTitle(),
                                    todo.getDescription(),
                                    todo.getStatus()
                            )
                    );
                }
        );
        return lst;
    }

    public Todo getTodoById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ApiRequestException("Todo with id " + id + " does not exist!");
        }
        return todoRepository.findById(id).get();
    }

    public void updateTodo(TodoRequestDTO todoRequestDTO, Long userId, Long todoId) {
        User user = userService.getUserById(userId);
        Todo todo = new Todo(
                todoRequestDTO.title(),
                todoRequestDTO.description(),
                todoRequestDTO.status(),
                user
        );
        todo.setId(todoId);
        todoRepository.save(todo);
    }
}
