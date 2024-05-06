package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "User Login and Registration",description = "This APIs exposes endpoints to manage User Login and Registration functionality ")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    @Operation(
            summary = "Register a User",
            description = "Pass a UserDTO with information to register a User"
    )
    @ApiResponse(description = "Response is a UserDTO ",responseCode = "201")
    public ResponseEntity<UserDTO> register(@Parameter(
            name = "userDTO") @Valid  @RequestBody UserDTO userDTO){
        userDTO = userService.register(userDTO);
        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        return responseEntity;
    }
    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO){
        userDTO = userService.login(userDTO.getOwnerEmail(),userDTO.getPassword());
        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.OK);
        return responseEntity;
    }

}
