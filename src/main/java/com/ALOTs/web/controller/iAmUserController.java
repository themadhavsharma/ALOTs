package com.ALOTs.web.controller;

import com.ALOTs.web.entity.iAmUser;
import com.ALOTs.web.service.userService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class iAmUserController {
    @Autowired
    private userService service;

    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        List<iAmUser> all = service.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getById(@PathVariable ObjectId myId){
        Optional<iAmUser> user = Optional.ofNullable(service.findById(myId));
        if(user.isPresent()){
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody iAmUser user){
        try {
            service.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("name/{username}")// we will update using the @RequestBody because there will be username which will help to find the user coz it is unique itself :)
    public ResponseEntity<?> updateUser(@RequestBody iAmUser theUser, @PathVariable String username){
       try{
           iAmUser user = service.findByusername(username);
           if(user != null){
               user.setUsername(theUser.getUsername() != null && !theUser.getUsername().equals("") ? theUser.getUsername() : user.getUsername());
               user.setPassword(theUser.getPassword() != null && !theUser.getPassword().equals("") ? theUser.getPassword() : user.getPassword());
               service.saveEntry(user);
           }
           return new ResponseEntity<>(user, HttpStatus.OK);
       } catch(Exception e){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }

    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId myId){
        try{
            service.deleteUser(myId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


}
