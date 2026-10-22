package net.knachike.journalApp.repository;

import net.knachike.journalApp.entity.ConfigJournalAppEntity;
import net.knachike.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}
