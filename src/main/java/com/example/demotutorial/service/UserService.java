package com.example.demotutorial.service;

import com.example.demotutorial.api.UserApi;
import com.example.demotutorial.converter.UserDTOConverter;
import com.example.demotutorial.dto.UserDTO;
import com.example.demotutorial.entity.Users;
import com.example.demotutorial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractCRUDLService<Users, UserDTO> implements UserApi {
    private UserRepository userRepository;
    private UserDTOConverter userDTOConverter;
    @Autowired
    public UserService(final UserRepository userRepository,final UserDTOConverter userDTOConverter) {
        super(userRepository, userDTOConverter);
        this.userRepository = userRepository;
        this.userDTOConverter = userDTOConverter;
    }


    @Override
    protected void updateEntity(Users entity, UserDTO dto) {
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
    }


}
