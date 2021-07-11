package com.example.demotutorial.converter;

import com.example.demotutorial.dto.BaseDTO;
import com.example.demotutorial.entity.DistributedEntity;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract converter used to convert from ENTITY to DTO
 * @param <ENTITY> Entity to convert
 * @param <DTO> DTO to convert to from ENTITY
 **/

public abstract class AbstractDTOConverter<ENTITY extends DistributedEntity,DTO extends BaseDTO> {

    /**
     * convert forwarded entity into dto
     * @param entity Entity to convert
     * @return converted entity as a DTO
     **/
    public abstract DTO convert(final ENTITY entity);

    /**
     * convert forwarded entity to forwarded dto
     * @param entity Entity to convert
     * @param dto DTO to convert to from ENTITY
     **/
    public void convert(final ENTITY entity, final DTO dto){
        dto.setId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setModified(entity.getModified());
    }
    /**
     * convert forwarded entity LIST to forwarded dto LIST
     * @param entity Entity to convert
     * @return LIST of converted DTOs
     **/
    public List<DTO> convertList(final List<ENTITY> entities){
        if(CollectionUtils.isEmpty(entities)){
            return Collections.emptyList();
        }
        //return entities.stream().sequential().map(this::convert).collect(Collectors.toList()); **SAME OUTPUT**
        return entities.stream().sequential().map(entity -> convert(entity)).collect(Collectors.toList());
    }
}
