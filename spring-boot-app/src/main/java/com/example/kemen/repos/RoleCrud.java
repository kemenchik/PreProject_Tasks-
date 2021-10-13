package com.example.kemen.repos;

import com.example.kemen.entities.Authority;
import com.example.kemen.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCrud extends JpaRepository<Authority, Long> {

    Authority getAuthorityByRole(Role role);
}
