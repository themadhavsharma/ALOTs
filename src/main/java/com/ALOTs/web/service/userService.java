package com.ALOTs.web.service;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.entity.iAmUser;
import com.ALOTs.web.repository.ALOTsRepository;
import com.ALOTs.web.repository.userRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class userService {
    @Autowired
    private userRepository repo;

    public void saveEntry(iAmUser user){
        repo.save(user);
    }

    public List<iAmUser> getAll(){

        try {
            return repo.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }



    public iAmUser findById(ObjectId id){
        return repo.findById(id).orElse(null);
    }

    public void deleteUser(ObjectId id){
        repo.deleteById(id);
    }

    public iAmUser findByusername(String username){
        return repo.findByusername(username);
    }

}
