package com.ALOTs.web.controller;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.service.ALOTsEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private ALOTsEntryService service;

    @GetMapping("all")
    public List<ALOTsUser> All(){
        return service.getAll();
    }

    @GetMapping("id/{myId}")
    public ALOTsUser getUser(@PathVariable ObjectId myId){
        return service.findById(myId);
    }
    @PostMapping
    public ALOTsUser register(@RequestBody ALOTsUser user){
        user.setDate(LocalDateTime.now());
        service.saveEntry(user);
        return user;

    }

    @DeleteMapping("id/{myId}")
    public boolean deleteUser(@PathVariable ObjectId myId){
        service.deleteUser(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public ALOTsUser updateUser(@PathVariable ObjectId myId, @RequestBody ALOTsUser user){
        ALOTsUser prevEntry = service.findById(myId);
        if(prevEntry != null){
            prevEntry.setEmail(user.getEmail() != null && !user.getEmail().equals("")?user.getEmail() : prevEntry.getEmail());
            prevEntry.setName(user.getName() != null && !user.getName().equals("")?user.getName() : prevEntry.getName());
            prevEntry.setPassword(user.getPassword() != null && !user.getPassword().equals("")?user.getPassword() : prevEntry.getPassword());
            prevEntry.setPhoneNumber(user.getPhoneNumber() != null && !user.getPhoneNumber().equals("")?user.getPhoneNumber(): prevEntry.getPhoneNumber());
        }
        service.saveEntry(prevEntry);
        return prevEntry;

    }

}
