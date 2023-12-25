package com.faculty.demo.user;

import com.faculty.demo.dto.ResponsePayload;
import com.faculty.demo.dto.UserLoginDTO;
import com.faculty.demo.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@Controller
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/user/register")
    public void addUser(@RequestBody UserRequestDTO userRequestDTO){
        userService.addNewUser(userRequestDTO);
        log.info("calling add user controller");
    }

    @PostMapping("/user/login")
    public ResponseEntity<ResponsePayload> loginUser(@RequestBody UserLoginDTO userLogin){

        ResponsePayload rp = ResponsePayload.builder()
                .code(HttpStatus.OK)
                .successMessage("User logged in successfully")
                .data(
                        Map.of("data", userService.loginUser(userLogin))
                ).build();
        log.info("calling login user controller");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        rp
                );
    }

    @DeleteMapping("/user/delete")
    public void deleteUser(@RequestParam Long id){
        userService.deleteUserById(id);
        log.info("calling delete user controller");
    }

    @PutMapping("/user/update")
    public void updateUser(@RequestParam Long id, @RequestBody UserRequestDTO userRequestDTO){
        userService.updateUserById(id, userRequestDTO);
        log.info("calling update user controller");
    }
}
