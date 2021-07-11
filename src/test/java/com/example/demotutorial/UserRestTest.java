package com.example.demotutorial;

import com.example.demotutorial.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

public class UserRestTest {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();
    private static final String URL = "http://localhost:8080/api/users";

    @Test
    public void testUserCRUDL(){
        //Assertions.assertEquals(true,true);
        final UserDTO dto = new UserDTO();
        dto.setUsername("Username: "+ UUID.randomUUID());
        dto.setName("Name: "+ UUID.randomUUID());
        final UserDTO saveDTO= REST_TEMPLATE.postForObject(URL,dto,UserDTO.class);
        Assertions.assertNotNull(dto);
    }
}
