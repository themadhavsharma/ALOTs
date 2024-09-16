package com.ALOTs.web.repository;

import com.ALOTs.web.entity.ALOTsUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ALOTsRepository extends MongoRepository<ALOTsUser, String> {
}
