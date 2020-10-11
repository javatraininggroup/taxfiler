package com.company.taxfiler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.company.taxfiler.dao.UserEntity;

@Component
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

	public UserEntity findByEmail(String username);

	@Query("SELECT u from UserEntity u left join MessagesEntity m on m.taxFileYear=u.id LEFT join UploadFilesEntity f on f.taxFileYear=u.id")
	public List<UserEntity> findAll();

}
