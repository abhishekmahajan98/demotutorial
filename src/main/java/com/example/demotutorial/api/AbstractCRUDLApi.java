package com.example.demotutorial.api;

import com.example.demotutorial.dto.BaseDTO;
import com.example.demotutorial.entity.DistributedEntity;

import java.util.List;

/**
 * Abstract CRUDL API containing methods for handling crudl operations
 * @param <ENTITY> Entity class
 * @param <DTO> DTO class
 **/

public interface AbstractCRUDLApi<ENTITY extends DistributedEntity,DTO extends BaseDTO> {

    /**
     * Saves forvarded dto
     * @param dto DTO to save
     * @return returns saved entity as dto
     */
    DTO save(DTO dto);

    /**
     * Finds DTO by Id
     * @param id id used for searching
     * @return returns found DTO otherwise null
     */
    DTO getById(Integer id);

    /**
     * Lists all found DTOs
     * @return returns list of DTOs
     */
     List<DTO> list();

    /**
     * Deletes the user by Id
     * @param Id
     * @return boolean result. true if success
     */
     Boolean delete(Integer id);
}
