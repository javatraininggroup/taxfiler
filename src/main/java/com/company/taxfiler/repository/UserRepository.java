package com.company.taxfiler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.company.taxfiler.dao.UserEntity;

@Component
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>, JpaRepository<UserEntity, Integer> {

	public UserEntity findByEmail(String username);

	@Query("SELECT u from UserEntity u left join MessagesEntity m on m.taxFileYear=u.id LEFT join UploadFilesEntity f on f.taxFileYear=u.id "
			+ "where ((m.mainStatus = :mainStatus and m.subStatus = :subStatus) or (f.subStatus = :subStatus and f.mainStatus = :mainStatus))")
	public List<UserEntity> findAll(@Param("mainStatus") final String mainStatus,
			@Param("subStatus") final String subStatus);

	@Query("SELECT u from UserEntity u left join MessagesEntity m on m.taxFileYear=u.id LEFT join UploadFilesEntity f on f.taxFileYear=u.id where m.mainStatus=:mainStatus and f.mainStatus=:mainStatus")
	public List<UserEntity> findAllWithMainStatus(@Param("mainStatus") final String mainStatus);
	
	@Query("SELECT distinct u from UserEntity u left join MessagesEntity m on m.taxFileYear=u.id LEFT join UploadFilesEntity f on f.taxFileYear=u.id ")
	public List<UserEntity> findAll();

}
