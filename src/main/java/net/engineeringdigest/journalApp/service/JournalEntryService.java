package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UsersService usersService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try {
            Users user = usersService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            usersService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

   @Transactional
   public void deleteById(ObjectId id, String username){
       // boolean removed=false;
        try {
            Users user = usersService.findByUserName(username);
            user.getJournalEntries().removeIf(x->x.getId().equals(id));
            usersService.saveEntry(user);
            journalEntryRepository.deleteById(id);
        } catch (Exception e) {
            log.error("an error occurred",e);
            throw new RuntimeException("An error occurred while deleting the entry",e);
        }


    }


}




