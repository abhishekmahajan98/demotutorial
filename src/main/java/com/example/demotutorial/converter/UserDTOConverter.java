package com.example.demotutorial.converter;

import com.example.demotutorial.dto.UserDTO;
import com.example.demotutorial.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserDTOConverter extends AbstractDTOConverter<Users, UserDTO> {
    @Override
    public UserDTO convert(Users entity) {
        UserDTO dto = new UserDTO();
        super.convert(entity,dto);
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        return dto;
    }
}
