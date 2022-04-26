package com.example.userservice.controller;

import com.example.userservice.domain.User;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user") // apigateway 라우팅을 위한
public class UserController {

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    public String healthCheck() {
        return "healthCheck";
    }

    @PostMapping("/insert")
    public ResponseEntity insertTest(@Valid @RequestBody UserDto userDto) {
        User saveUser = userDto.toEntity();
        User userObject = userRepository.save(saveUser);
        System.out.println(userObject);
        //return new ResponseEntity("200 OK", HttpStatus.OK);
        return ResponseEntity.ok().body(userDto.getName());
    }

}
