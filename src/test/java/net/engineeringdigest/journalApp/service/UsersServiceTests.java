package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsersServiceTests {

    @Autowired
    UsersRepository usersRepository;

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testFindByUserName(Users user){
        Assertions.assertNotNull(usersRepository.findByUsername(user.getUsername()));
    }


    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "Hrg",
            "Hrx",
            "HRR"
    })
    public void testFindByUserName_(String name){
        Assertions.assertNotNull(usersRepository.findByUsername(name));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,4,6",
            "3,6,10"
    })
    public void testAdd(int a, int b, int expected){
        Assertions.assertEquals(expected,a+b);
    }

//so there are some more annotations like @BeforeEach->what it does is that you are saying to the junit that please run this method
//before running any test method and in that particular method you can implement anything, same there is one more annotation
//@beforeAll -> what it does is that run this method before any test method mentioned in the test file
    //same as there is @AfterAll and @Aftereach


}
