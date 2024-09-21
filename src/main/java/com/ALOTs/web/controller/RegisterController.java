package com.ALOTs.web.controller;

import com.ALOTs.web.entity.ALOTsUser;
import com.ALOTs.web.entity.iAmUser;
import com.ALOTs.web.service.ALOTsEntryService;
import com.ALOTs.web.service.userService;
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

    @Autowired
    private userService uService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllEntriesOfUser(@PathVariable String username){
        iAmUser byusername = uService.findByusername(username);
        List<ALOTsUser> all = byusername.getAlotsUser();
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
    @PostMapping("{username}")
    public ResponseEntity<?> register(@RequestBody ALOTsUser user, @PathVariable String username){
        try{
            user.setDate(LocalDateTime.now());
            service.saveEntry(user, username);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId myId, @PathVariable String username){
        try{
            service.deleteUser(myId, username);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updateUser(
            @PathVariable ObjectId myId,
            @PathVariable String username,
            @RequestBody ALOTsUser user){
        try{
            ALOTsUser prevEntry = service.findById(myId);
            if(prevEntry != null){
                prevEntry.setEmail(user.getEmail() != null && !user.getEmail().equals("")?user.getEmail() : prevEntry.getEmail());
                prevEntry.setName(user.getName() != null && !user.getName().equals("")?user.getName() : prevEntry.getName());
                prevEntry.setPassword(user.getPassword() != null && !user.getPassword().equals("")?user.getPassword() : prevEntry.getPassword());
                prevEntry.setPhoneNumber(user.getPhoneNumber() != null && !user.getPhoneNumber().equals("")?user.getPhoneNumber(): prevEntry.getPhoneNumber());
            }
            service.saveEntry(prevEntry);
            return new ResponseEntity<>(prevEntry, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
