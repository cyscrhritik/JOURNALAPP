package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController {

    private Map<ObjectId, JournalEntry> journalEntries= new HashMap<ObjectId,JournalEntry>();


    @GetMapping()
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(), entry);
        return true;
    }

    @GetMapping("/id/{myid}")
    public JournalEntry findById(@PathVariable ObjectId myid){
        return journalEntries.get(myid);
    }

    @DeleteMapping("/id/{myid}")
    public boolean deleteById(@PathVariable ObjectId myid){
        journalEntries.remove(myid);
        return true;
    }

    @PutMapping()
    public boolean updateJournal(@RequestBody JournalEntry entry){
        journalEntries.put(entry.getId(),entry);
        return true;
    }



}
