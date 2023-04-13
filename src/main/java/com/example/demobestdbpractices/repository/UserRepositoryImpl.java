package com.example.demobestdbpractices.repository;

import com.example.demobestdbpractices.model.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRepositoryImpl implements CustomUserRepository {
    private final EntityManager entityManager;

    @Override
    public List<User> findAllByIdBetween(long startId, long endId) {
        var users = entityManager.createQuery(
                        "select u from User u  left join fetch u.profile left join fetch u.notes where u.id >= ?1 and u.id < ?2",
                        User.class)
                .setParameter(1, startId)
                .setParameter(2, endId).getResultList();
        return entityManager.createQuery("select u from User u  left join fetch u.profile left join fetch u.reminders where u in ?1",
                        User.class)
                .setParameter(1, users)
                .getResultList();
    }
}