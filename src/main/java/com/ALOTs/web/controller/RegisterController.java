package com.ALOTs.web.controller;

import com.ALOTs.web.entity.ALOTsUser;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private Map<Long, ALOTsUser> users = new HashMap<>();

    @GetMapping
    public String home(){
        return "Welcome to ALOTs: One stop solution for all your needs. Please register yourself in order to enjoy our service";
    }

    @GetMapping("id/{myId}")
    public ALOTsUser getUser(@PathVariable Long myId){
        return users.get(myId);
    }

    @GetMapping("all")
    public List<ALOTsUser> getAll(){
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public boolean register(@RequestBody ALOTsUser user){
        users.put(user.getId(), user);
        return true;
    }

    @DeleteMapping("id/{myId}")
    public ALOTsUser deleteUser(@PathVariable Long myId){
        return users.remove(myId);
    }

    @PutMapping("id/{myId}")
    public ALOTsUser updateUser(@PathVariable Long myId, @RequestBody ALOTsUser user){
        return users.put(myId, user);
    }






}
