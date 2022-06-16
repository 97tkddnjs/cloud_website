package com.marine.website.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class TestUser {

    @Autowired
    public UserService userService;

    @Test
    @Transactional
    void qTestJpa() {
        SiteUser lee = userService.create("kee", "97sw@naver.com", "1234");
        System.out.println("kee = " + lee);
    }

    @Test
    @Transactional
    void Testsignup() {
        SiteUser lee = userService.create("kim", "97sw@naver.com", "1234");

        SiteUser kim = userService.getUser("kim");
        System.out.println("kim = " + kim.getEmail());

        SiteUser email = userService.getEmail("97sw@naver.com");
        System.out.println("email = " + email.getEmail());
    }

}
