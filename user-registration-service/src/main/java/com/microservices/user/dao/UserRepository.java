package com.microservices.user.dao;

import org.springframework.data.repository.CrudRepository;

import com.microservices.user.model.UserDetails;

public interface UserRepository extends CrudRepository<UserDetails, Long> {

}
