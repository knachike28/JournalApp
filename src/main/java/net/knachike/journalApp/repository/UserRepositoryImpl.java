package net.knachike.journalApp.repository;

import net.knachike.journalApp.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query= new Query();
        //query.addCriteria(Criteria.where("userName").is("kumar"));
//        query.addCriteria(Criteria.where("email").exists(true));
//        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        //query.addCriteria(Criteria.where("roles").in("USER", "ADMIN"));
        //query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BsonType.BOOLEAN));
        /*
        best example of ORM;
        here we are providing class not the collection
        bcx in class we have annotated by @Document(collection = "users")
        */
        List<User> users = mongoTemplate.find(query, User.class);
        return users;

    }

}
