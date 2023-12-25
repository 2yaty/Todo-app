package com.faculty.demo.todo;

import com.faculty.demo.dto.ResponsePayload;
import com.faculty.demo.dto.TodoRequestDTO;
import com.faculty.demo.dto.TodoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class TodoController {
    private final TodoService todoService;


    @PostMapping("/todo/add")
    public ResponseEntity<ResponsePayload> addTodo(@RequestParam Long userId, @RequestBody TodoRequestDTO todoRequestDTO){
        todoService.addNewTodo(
                todoRequestDTO,
                userId
        );
        ResponsePayload rp = ResponsePayload.builder()
                .code(HttpStatus.OK)
                .time(new Date())
                .successMessage("Todo added successfully")
                .data( Map.of()
                ).build();
        log.info("calling add todo controller");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        rp
                );
    }

    @GetMapping("/todo/all")
    public ResponseEntity<ResponsePayload> getAllTodos(@RequestParam Long userId){
        List<TodoResponseDTO> lst = new ArrayList<>();
        ResponsePayload rp = ResponsePayload.builder()
                .code(HttpStatus.OK)
                .time(new Date())
                .successMessage("Data fetched successfully")
                .data(
                        Map.of("data", todoService.getAllTodos(userId))
                ).build();
        log.info("calling get all todos controller");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        rp
                );
    }

    @PutMapping("/todo")
    public ResponseEntity<ResponsePayload> updateTodo(@RequestParam Long userId, @RequestParam Long todoId, @RequestBody TodoRequestDTO todoRequestDTO){

        todoService.updateTodo(
                todoRequestDTO,
                userId,
                todoId
        );
        ResponsePayload rp = ResponsePayload.builder()
                .code(HttpStatus.OK)
                .time(new Date())
                .successMessage("Todo updated successfully")
                .data( Map.of()
                ).build();

        log.info("calling update todo controller");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        rp
                );
    }


    @DeleteMapping("/todo")
    public ResponseEntity<ResponsePayload> deleteTodo( @RequestParam Long todoId){
        todoService.deleteTodoById(todoId);
        ResponsePayload rp = ResponsePayload.builder()
                .code(HttpStatus.OK)
                .time(new Date())
                .successMessage("Todo deleted successfully")
                .data( Map.of()
                ).build();
        log.info("calling delete todo controller");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        rp
                );
    }


}
