package com.tts.codelab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.codelab.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
