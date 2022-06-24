package com.booking;

import com.booking.users.PasswordLogger;
import com.booking.users.PasswordLoggerRepository;
import com.booking.users.User;
import com.booking.users.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Optional;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, PasswordLoggerRepository passwordLoggerRepository) {
        return args -> {
            if (repository.findByUsername("seed-user-1").isEmpty()) {
                repository.save(new User("seed-user-1", "P@rul1xyz"));
            }
            if (repository.findByUsername("seed-user-2").isEmpty()) {
                repository.save(new User("seed-user-2", "P@rul1xyz"));
            }
        };
    }
}
