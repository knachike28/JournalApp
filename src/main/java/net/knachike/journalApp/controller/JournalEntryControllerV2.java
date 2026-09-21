package net.knachike.journalApp.controller;

import net.knachike.journalApp.entity.JournalEntity;
import net.knachike.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntity> getAll(){
        return journalEntryService.getALl();
    }

    @PostMapping
    public JournalEntity CreateEntry(@RequestBody JournalEntity myEntity){
        myEntity.setDate(LocalDateTime.now());
        journalEntryService.saveJournalEntry(myEntity);
    return myEntity;
    }

    @GetMapping("/id/{my_id}")
    public JournalEntity getJournalById(@PathVariable ObjectId my_id){

        return journalEntryService.findById(my_id).orElse(null) ;
    }

    @DeleteMapping("/id/{my_id}")
    public boolean deleteJournalById(@PathVariable ObjectId my_id){
        journalEntryService.deleteById(my_id);
        return true;
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllEntry(){

    }

    @PutMapping ("/id/{myId}")
    public JournalEntity updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntry){
        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if (old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
        }
        journalEntryService.saveJournalEntry(old);
        return old;
    }
}
