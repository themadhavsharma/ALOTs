package com.ALOTs.web.controller;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.service.ALOTsEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private ALOTsEntryService service;

    @GetMapping("all")
    public ResponseEntity<?> All(){
        List<ALOTsUser> all = service.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getUser(@PathVariable ObjectId myId){
        Optional<ALOTsUser> entry = Optional.ofNullable(service.findById(myId));
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<?> register(@RequestBody ALOTsUser user){
        try{
            user.setDate(LocalDateTime.now());
            service.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId myId){
        try{
            service.deleteUser(myId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateUser(@PathVariable ObjectId myId, @RequestBody ALOTsUser user){
        try{
            ALOTsUser prevEntry = service.findById(myId);
            if(prevEntry != null){
                prevEntry.setEmail(user.getEmail() != null && !user.getEmail().equals("")?user.getEmail() : prevEntry.getEmail());
                prevEntry.setName(user.getName() != null && !user.getName().equals("")?user.getName() : prevEntry.getName());
                prevEntry.setPassword(user.getPassword() != null && !user.getPassword().equals("")?user.getPassword() : prevEntry.getPassword());
                prevEntry.setPhoneNumber(user.getPhoneNumber() != null && !user.getPhoneNumber().equals("")?user.getPhoneNumber(): prevEntry.getPhoneNumber());
            }
            service.saveEntry(prevEntry);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
