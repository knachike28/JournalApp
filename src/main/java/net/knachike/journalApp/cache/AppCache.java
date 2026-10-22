package net.knachike.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.knachike.journalApp.entity.ConfigJournalAppEntity;
import net.knachike.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    
    
    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        System.out.println("Documents found: " + all.size());
        for( ConfigJournalAppEntity configJournalAppEntity: all)
        {
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }

}
