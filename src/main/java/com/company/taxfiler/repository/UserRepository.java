package com.company.taxfiler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.company.taxfiler.dao.UserEntity;

@Component
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>{
	
	UserEntity findByEmail(String username);

}
