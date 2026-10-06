package net.knachike.journalApp.service;

import net.knachike.journalApp.entity.JournalEntity;
import net.knachike.journalApp.entity.User;
import net.knachike.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(JournalEntity journalEntity, String userName) {
        try {
            User user= userService.findByUserName(userName);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity savedEntity = journalEntryRepository.save(journalEntity);
            user.getJournalEntities().add(savedEntity);
            userService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry. ", e);
        }
    }

    public void saveJournalEntry(JournalEntity journalEntity) {
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getALl(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user= userService.findByUserName(userName);
        user.getJournalEntities().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

    public void deleteAll(JournalEntity journalEntity){
        journalEntryRepository.delete(journalEntity);
    }


}