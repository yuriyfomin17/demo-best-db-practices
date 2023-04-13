package com.example.demobestdbpractices;

import com.example.demobestdbpractices.model.Profile;
import com.example.demobestdbpractices.model.User;
import com.example.demobestdbpractices.util.PersonGenerator;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbLoader {

    private final EntityManager entityManager;

    //    @EventListener(ContextRefreshedEvent.class)
//    @Transactional
    public void fillDatabase() {
        for (int i = 0; i < 1000; i++) {
            User user = PersonGenerator.generateUser();
            user.setEmail(user.getEmail() + i);
            Profile profile = PersonGenerator.generateProfile();
            entityManager.persist(user);
            user.setProfile(profile);
            profile.setUser(user);
            entityManager.persist(profile);
            log.info("USER_SAVED:" + user);

        }
    }
}
