package com.example.kemen.repos;

import com.example.kemen.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface UserCrud extends JpaRepository<User, Long> {
    User save(User u);

    Page<User> findAll(Pageable pageable);

    User findUserById(long id);

    User findUserByLogin(String l);

    User findFirstByVkUserId(String str);

    void removeUserById(long id);

    User findUserByGoogleId(String str);
}

