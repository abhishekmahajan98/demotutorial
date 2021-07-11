package com.example.demotutorial.controller;

import com.example.demotutorial.api.AbstractCRUDLApi;
import com.example.demotutorial.dto.BaseDTO;
import com.example.demotutorial.entity.DistributedEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AbstractCRUCLController<ENTITY extends DistributedEntity,DTO extends BaseDTO> {
    private AbstractCRUDLApi<ENTITY,DTO> api;
    public AbstractCRUCLController(AbstractCRUDLApi<ENTITY, DTO> api) {
        this.api = api;
    }



    @PostMapping
    public DTO save(@RequestBody DTO dto){
        return api.save(dto);
    }

    @GetMapping("/{id}")
    public DTO getById(@PathVariable Integer id){
        return api.getById(id);
    }

    @GetMapping("/list")
    public List<DTO> list(){
        return api.list();
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return api.delete(id);
    }
}
