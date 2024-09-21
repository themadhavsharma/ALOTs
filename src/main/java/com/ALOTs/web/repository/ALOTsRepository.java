package com.ALOTs.web.repository;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.entity.iAmUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


public interface ALOTsRepository extends MongoRepository<ALOTsUser, ObjectId> {
    iAmUser findByUsername(String username);

}

