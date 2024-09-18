package com.ALOTs.web.service;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.repository.ALOTsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class ALOTsEntryService {
    @Autowired
    private ALOTsRepository repo;

    public void saveEntry(ALOTsUser user){
        repo.save(user);
    }

    public List<ALOTsUser> getAll(){

        try {
            return repo.findAll();
        } catch (Exception e) {
            // Add logging here for better error insight
            System.out.println("Error fetching users: " + e.getMessage());
            return new ArrayList<>();
        }
    }



    public ALOTsUser findById(ObjectId id){
        return repo.findById(id).orElse(null);
    }

    public void deleteUser(ObjectId id){
        repo.deleteById(id);
    }

}
