package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


public class UserDetailsServiceImplTest {

   /* @Autowired
    UserDetailsService userDetailsService;*/

    /*@MockBean
    UsersRepository usersRepository;*/

    @InjectMocks
    private CustomUserDetailsServiceImpl userDetailsService;

    @Mock
    private UsersRepository usersRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(usersRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(Users.builder().username("Hrg").password("radhe").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("Hrg");
        Assertions.assertNotNull(userDetails);
    }

    /*so please remember one thing that we will use @MocBean when we will use @Autowired injection in testing class with it , because we
    need the spring-applicationc-ontext bean so still it will use spring context now to make it free of whole spring context scenario we will use
    @InjectMocks- and then we can use that @Mock in the using repository
    * */
}
