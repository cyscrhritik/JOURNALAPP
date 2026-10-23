package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UsersRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UsersService {

    //private static final Logger logger= LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository usersRepository;

    private static final BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public void saveEntry(Users user){
        usersRepository.save(user);
    }

    public boolean saveNewUser(Users user){
        boolean saved = false;
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            usersRepository.save(user);
            saved=true;
        } catch (Exception e) {
            //log.trace("some trace is saying about username {}",user.getUsername());
            //log.debug("some debug happened log is printing username {}",user.getUsername());
            log.info("some information about username {}",user.getUsername());
            log.warn("some warning about username {}",user.getUsername());
            log.error("An error occured for {}",user.getUsername(),e);
            //throw new RuntimeException(e);
        }
        return saved;
    }

    public void saveNewAdmin(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        usersRepository.save(user);
    }

    public List<Users> getAllEntry(){
        return usersRepository.findAll();
    }

    public Optional<Users> findById(ObjectId id){
        return usersRepository.findById(id);
    }

    public Users findByUserName(String username){
        return usersRepository.findByUsername(username);
    }

    public void deleteById(ObjectId id){
        usersRepository.deleteById(id);
    }


}




