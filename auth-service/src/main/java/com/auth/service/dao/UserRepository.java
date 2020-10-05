package com.auth.service.dao;

import com.auth.service.dto.UserDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends  JpaRepository<UserDetailsDTO, String>{
	
	@Query(value = "select u from UserDetailsDTO u where u.username = ?1")
	public UserDetailsDTO findByUserName(String userName);
}
