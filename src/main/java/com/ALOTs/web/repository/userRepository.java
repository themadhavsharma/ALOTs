package com.ALOTs.web.repository;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.entity.iAmUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface userRepository extends MongoRepository<iAmUser, ObjectId> {
    iAmUser findByusername(String username);
}

