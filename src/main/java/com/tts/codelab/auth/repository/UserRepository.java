package com.tts.codelab.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.codelab.auth.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
