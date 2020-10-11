package com.company.taxfiler.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.company.taxfiler.dao.MessagesEntity;

@Component
@Repository
public interface CustomerMessagesRepo extends CrudRepository<MessagesEntity, Integer> {

	@Query("select u from MessagesEntity u where u.mainStatus = :mainStatus and u.subStatus = :subStatus")
	public Page<MessagesEntity> findByMainStatusAndSubStatusWithPagination(@Param("mainStatus") String mainStatus,
			@Param("subStatus") String subStatus, Pageable pageable);

	@Query("select u from MessagesEntity u where u.mainStatus = :mainStatus")
	public Page<MessagesEntity> findByMainStatus(@Param("mainStatus") String mainStatus, Pageable pageable);

}
