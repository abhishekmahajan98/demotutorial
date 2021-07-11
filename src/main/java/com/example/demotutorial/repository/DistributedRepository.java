package com.example.demotutorial.repository;

import com.example.demotutorial.entity.DistributedEntity;
import org.springframework.data.repository.CrudRepository;

public interface DistributedRepository<ENTITY extends DistributedEntity> extends CrudRepository<ENTITY,Integer> {
}
