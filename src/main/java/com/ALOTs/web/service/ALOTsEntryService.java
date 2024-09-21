package com.ALOTs.web.service;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.entity.iAmUser;
import com.ALOTs.web.repository.ALOTsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class ALOTsEntryService {
    @Autowired
    private ALOTsRepository repo;

    @Autowired
    private userService uService;

    @Transactional // treats the whole function as a single operation and if any line produce an error, the entire function revert.
    public void saveEntry(ALOTsUser user, String username){
        try{
            iAmUser iUser = uService.findByusername(username);
            ALOTsUser saved = repo.save(user);
            iUser.getAlotsUser().add(saved);
            uService.saveEntry(iUser);
        }catch(Exception e){
            throw new RuntimeException("An error occured while saving the entry.", e);
        }
    }

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

    public void deleteUser(ObjectId id, String username){
        iAmUser iUser = uService.findByusername(username);
        iUser.getAlotsUser().removeIf(x -> x.getId().equals(id));
        uService.saveEntry(iUser);
        repo.deleteById(id);
    }

}
