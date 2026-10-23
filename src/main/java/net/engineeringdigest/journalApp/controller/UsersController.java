package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UsersRepository;
import net.engineeringdigest.journalApp.service.UsersService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    /*@GetMapping
      public List<Users> getAllUser(){
        return usersService.getAllEntry();
    }*/



    @PutMapping()
    public ResponseEntity<?> updateByUserName(@RequestBody Users newuser){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        Users olduser = usersService.findByUserName(username);
        olduser.setUsername(newuser.getUsername());
        olduser.setPassword(newuser.getPassword());
        boolean saved = usersService.saveNewUser(olduser);
        if(saved) {
            return new ResponseEntity<>(olduser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


    }

    @GetMapping("/id/{myid}")
    public Users getUserById(@PathVariable ObjectId myid){
        return usersService.findById(myid).orElse(null);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUserByUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();
        usersRepository.deleteByUsername(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{myid}")
    public Users updateUserById(@PathVariable ObjectId myid, @RequestBody Users newuser){
        Users  olduser= usersService.findById(myid).orElse(null);
        if(olduser!=null){
            olduser.setUsername(newuser.getUsername()!=null && !(newuser.getUsername().isEmpty()) ? newuser.getUsername() : olduser.getUsername());
            olduser.setPassword(newuser.getPassword()!=null && !(newuser.getPassword().isEmpty()) ? newuser.getPassword() : olduser.getPassword());
        }
        usersService.saveEntry(olduser);
        return olduser;
    }


}




