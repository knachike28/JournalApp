package net.knachike.journalApp.service;

import net.knachike.journalApp.entity.JournalEntity;
import net.knachike.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveJournalEntry(JournalEntity journalEntity) {

        //System.out.println("MongoTemplate database: "+ mongoTemplate.getDb().getName());

        JournalEntity savedEntity = journalEntryRepository.save(journalEntity);

        //System.out.println("Saved ID: " + savedEntity.getId());
    }

    public List<JournalEntity> getALl(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }

    public void deleteAll(JournalEntity journalEntity){
        journalEntryRepository.delete(journalEntity);
    }


}