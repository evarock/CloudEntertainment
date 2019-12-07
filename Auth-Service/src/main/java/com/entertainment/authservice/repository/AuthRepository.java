package com.entertainment.authservice.repository;

import com.entertainment.authservice.model.AuthEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<AuthEntity, String> {

}
