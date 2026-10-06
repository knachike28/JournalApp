package net.knachike.journalApp.service;

import net.knachike.journalApp.entity.JournalEntity;
import net.knachike.journalApp.entity.User;
import net.knachike.journalApp.repository.JournalEntryRepository;
import net.knachike.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User userEntity) {
        userRepository.save(userEntity);
    }

    public List<User> getALl(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public void deleteAll(User userEntity){
        userRepository.delete(userEntity);
    }

    public User findByUserName (String userName){
        return userRepository.findByUserName(userName);
    }


}