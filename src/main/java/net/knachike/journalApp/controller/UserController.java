package net.knachike.journalApp.controller;

import net.knachike.journalApp.entity.JournalEntity;
import net.knachike.journalApp.entity.User;
import net.knachike.journalApp.service.JournalEntryService;
import net.knachike.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping()
//    public List<User> getAllUser(){
//        return userService.getALl();
//    }

//    @PostMapping()
//    public void createUser( @RequestBody User user){
//        userService.saveEntry(user);
//    }

    @PutMapping
    public ResponseEntity<?> updateUser( @RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveEntry(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

//    @DeleteMapping("/user")
//    public deleteUserById(@RequestBody User user){
//
//    }


}
