package net.knachike.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.knachike.journalApp.entity.User;
import net.knachike.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    private static final PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public boolean saveNewUser(User userEntity) {
        try {
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRoles(Arrays.asList("USER"));
            userRepository.save(userEntity);
            return true;
        } catch (Exception e) {
            log.error("Error occurred for {} :", userEntity.getUserName(), e);
//            logger.error("Error occurred for {} :", userEntity.getUserName(), e);
//            logger.warn("Error occurred", e);
//            logger.info("Error occurred", e);
//            logger.debug("Error occurred", e);
//            logger.trace("Error occurred", e);

            return false;
        }
    }

    public void saveUser(User userEntity) {
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

    public void saveAdmin(User userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(userEntity);
    }
}