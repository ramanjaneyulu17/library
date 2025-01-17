package com.developer.library.controller;

import com.developer.library.model.Users;
import com.developer.library.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    private UsersService service;

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        Optional<Users> user = service.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Users(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody Users user) {
        Optional<Users> addUser=service.findById(user.getId());
        if (addUser.isEmpty()) {
            service.addUser(user);
            return new ResponseEntity<>("added",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("can not added", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody Users user){
        Optional<Users> findUser=service.findById(id);
        if(findUser.isEmpty()){
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }else {
            service.updateUser(id,user);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        Optional<Users> deleteUser=service.findById(id);
        if(deleteUser.isPresent()) {
            service.deleteUser(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
    }
}
