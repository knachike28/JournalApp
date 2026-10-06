package net.knachike.journalApp.controller;

import net.knachike.journalApp.entity.JournalEntity;
import net.knachike.journalApp.entity.User;
import net.knachike.journalApp.service.JournalEntryService;
import net.knachike.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);

        List<JournalEntity> all = user.getJournalEntities();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntity> CreateEntry(@RequestBody JournalEntity myEntity, @PathVariable String userName){
        try {

            journalEntryService.saveJournalEntry(myEntity, userName);
            return new ResponseEntity<>(myEntity, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{my_id}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId my_id){

        Optional<JournalEntity> journalEntity = journalEntryService.findById(my_id);
        if(journalEntity.isPresent()){
            return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{my_id}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId my_id, @PathVariable String userName){
        journalEntryService.deleteById(my_id, userName);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllEntry(){

    }

    @PutMapping ("/id/{userName}/{myId}")
    public ResponseEntity<JournalEntity> updateJournalById(
            @PathVariable ObjectId myId,
            @RequestBody JournalEntity newEntry,
            @PathVariable String userName
    ){
        JournalEntity old = journalEntryService.findById(myId).orElse(null);
        if (old!=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            journalEntryService.saveJournalEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
