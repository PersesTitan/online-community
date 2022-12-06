package com.spring.onlinecommunity.repository;

import com.spring.onlinecommunity.domain.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public void remove(User user) {
        em.remove(user);
    }
}
