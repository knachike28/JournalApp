package net.knachike.journalApp.entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entities")
public class JournalEntity {
    @Id
    private ObjectId id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private String title;
    private String content;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private LocalDateTime date;

}
