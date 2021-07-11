package com.example.demotutorial.service;

import com.example.demotutorial.api.AbstractCRUDLApi;
import com.example.demotutorial.converter.AbstractDTOConverter;
import com.example.demotutorial.dto.BaseDTO;
import com.example.demotutorial.entity.DistributedEntity;
import com.example.demotutorial.repository.DistributedRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class AbstractCRUDLService<ENTITY extends DistributedEntity,DTO extends BaseDTO> implements AbstractCRUDLApi<ENTITY,DTO> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractCRUDLService.class);
    private DistributedRepository<ENTITY> repository;
    private Class<ENTITY> entityClass;
    private AbstractDTOConverter<ENTITY,DTO> converter;



    public AbstractCRUDLService(final DistributedRepository<ENTITY> repository, final AbstractDTOConverter<ENTITY,DTO> converter) {
        this.repository = repository;
        this.converter = converter;
        final Class<?>[] params =  GenericTypeResolver.resolveTypeArguments(getClass(),AbstractCRUDLService.class);
        entityClass = (Class<ENTITY>) params[0];
    }

    @Override
    public DTO save(DTO dto) {
        final ENTITY entity ;
        if(dto.isNew()){
            //create new entity
            entity = initEntity();
        }
        else {
            //update existing entity
            entity= getEntityById(dto.getId());
        }
        if(entity==null){
            LOG.error("failed to save the entity of class {}",entityClass.getSimpleName());
            return null;
        }
        //to set modified date at every update/create
        entity.setModified(LocalDateTime.now());

        //map dto to entity
        updateEntity(entity,dto);

        // save entity to DB
        ENTITY savedEntity = repository.save(entity);

        // convert savedEntity to DTO and return the DTO
        return converter.convert(savedEntity);
    }

    @Override
    public DTO getById(Integer id) {
        ENTITY entity= getEntityById(id);
        System.out.println("yes");
        if(entity ==null){
            LOG.error("failed to find entity with id {}", id);
            return null;
        }
        DTO dto= converter.convert(entity);
        return dto;
    }

    private ENTITY getEntityById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Boolean delete(Integer id) {
        ENTITY entity= getEntityById(id);
        if(entity==null){
            LOG.error("No entity found with id {}",id);
            return false;
        }
        try {
            repository.delete(entity);
            return true;
        }catch (final Exception e){
            LOG.error(e.getMessage());
            return false;
        }
    }
    @Override
    public List<DTO> list() {
        List<ENTITY> usersList = new ArrayList<ENTITY>();
        final Iterable<ENTITY> entities = repository.findAll();
        for(ENTITY entity: entities){
            usersList.add(entity);
        }
        if(CollectionUtils.isEmpty(usersList)){
            return Collections.emptyList();
        }
        return converter.convertList(usersList);
    }

    protected abstract void updateEntity(final ENTITY entity, final DTO dto);

    private ENTITY initEntity(){
        try {
            final ENTITY entity = entityClass.newInstance();
            //Add created timestamp
            entity.setCreated(LocalDateTime.now());
            return entity;
        } catch (final InstantiationException | IllegalAccessException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
