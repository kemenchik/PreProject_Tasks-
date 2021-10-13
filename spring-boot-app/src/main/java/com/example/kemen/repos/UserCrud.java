package com.example.kemen.repos;

import com.example.kemen.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCrud extends JpaRepository<User, Long> {
    User save(User u);

    List<User> findAll();

    User findUserById(long id);

    User findUserByLogin(String l);

    void removeUserById(long id);
}

