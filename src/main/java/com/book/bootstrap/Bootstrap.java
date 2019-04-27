package com.book.bootstrap;

import com.book.domain.User;
import com.book.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//CommandLineRunner is Spring Boot specific which allows to run
//code on startup
@Component
public class Bootstrap implements CommandLineRunner {

    private UserRepository userRepository;

    public Bootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {

        User userFirstBracket = new User();
        User userSecondBracket = new User();
        User userThirdBracket = new User();

        userFirstBracket.setName("Adam");
        userFirstBracket.setEmail("adam@gmail.com");
        userFirstBracket.getFeedback().put("Teen & Young Adult", 13);
        userFirstBracket.getFeedback().put("History", -7);
        userFirstBracket.getFeedback().put("Science & Math", 6);

        userSecondBracket.setName("Michael");
        userSecondBracket.setEmail("michael@gmail.com");
        userSecondBracket.getFeedback().put("Romance", 90);
        userSecondBracket.getFeedback().put("Teen & Young Adult", 13);
        userSecondBracket.getFeedback().put("History", -7);

        userThirdBracket.setName("Sean");
        userThirdBracket.setEmail("sean@gmail.com");
        userThirdBracket.getFeedback().put("History", 400);

        userRepository.save(userFirstBracket);
        userRepository.save(userSecondBracket);
        userRepository.save(userThirdBracket);

        System.out.println("Data Loaded for User: " + userRepository.count());
    }
}
