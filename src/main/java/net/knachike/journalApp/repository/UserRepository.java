package net.knachike.journalApp.repository;

import net.knachike.journalApp.entity.JournalEntity;
import net.knachike.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);

}
