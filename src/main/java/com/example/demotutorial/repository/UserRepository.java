package com.example.demotutorial.repository;

import com.example.demotutorial.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends DistributedRepository<Users>{
}
