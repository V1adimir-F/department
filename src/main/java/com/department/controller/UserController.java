package com.department.controller;

import com.department.dto.response.JwtResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vladimir F. 07.11.2022 16:22
 */

@RestController
@RequestMapping(value = "/department/user")
@CrossOrigin(origins = "${client.host}")
public class UserController {

    @GetMapping(value = "/auth")
    public ResponseEntity<?> checkAuth() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJlbWFpbCI6InNvbWVtYWlsQG1haWwuY29tIiwiaWF0IjoxNTE2MjM5MDIyfQ.YjPHMU37M0J4KnxTr3NLhOAGnE3rgiAvXAYG4oRWvqg";
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new JwtResponseDto(token));
    }
}
