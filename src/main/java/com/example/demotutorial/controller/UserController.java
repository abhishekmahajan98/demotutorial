package com.example.demotutorial.controller;

import com.example.demotutorial.api.UserApi;
import com.example.demotutorial.dto.UserDTO;
import com.example.demotutorial.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractCRUCLController<Users, UserDTO> {
    private UserApi api;

    @Autowired
    public UserController(final UserApi api) {
        super(api);
    }
}
