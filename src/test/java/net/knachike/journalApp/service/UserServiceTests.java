package net.knachike.journalApp.service;

import net.knachike.journalApp.entity.User;
import net.knachike.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
//to load the bean and dependencies
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName() {
        //userRepository.findByUserName("ram");
        //assertEquals(4, 2+2);
        assertNotNull(userRepository.findByUserName("ram"));
        User user = userRepository.findByUserName("ram");
        assertFalse(user.getJournalEntities().isEmpty());

    }

    @ParameterizedTest
    @CsvSource({
            "ram",
            "kumar",
            "vipul"
    })
    public void testFindByUserName1(String name) {
        assertNotNull(userRepository.findByUserName(name), "failed for :"+name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 10, 12",
            "3, 3, 9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
