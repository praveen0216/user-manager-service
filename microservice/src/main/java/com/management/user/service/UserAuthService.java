package com.management.user.service;

import com.management.user.output.repository.entity.UserAuthEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {

    private static final String EXiSTING_EMAIL = "test@gmail.com";
    private static final String ANOTHER_EMAIL = "another@gmail.com";
    public Optional<UserAuthEntity> findByEmail(String email) {
        //Move this to database
        if (EXiSTING_EMAIL.equalsIgnoreCase(email)) {
            var user = new UserAuthEntity();
            user.setId(1L);
            user.setEmail(EXiSTING_EMAIL);
            user.setPassword("$2a$12$jvgj3j/vVqtSKTwueTjbS.PWvjVi6aQqJEphJtuggPgGTI2.bi4mG"); //test
            user.setRole("ADMIN");
            return Optional.of(user);
        } else if (ANOTHER_EMAIL.equalsIgnoreCase(email)) {
            var user = new UserAuthEntity();
            user.setId(99L);
            user.setEmail(ANOTHER_EMAIL);
            user.setPassword("$2a$12$jvgj3j/vVqtSKTwueTjbS.PWvjVi6aQqJEphJtuggPgGTI2.bi4mG"); //test
            user.setRole("ROLE_USER");
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
