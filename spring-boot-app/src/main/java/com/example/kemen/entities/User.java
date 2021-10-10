package com.example.kemen.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private Set<Authority> authorities = new HashSet<>();

    public User(String login, String name, String lastName, String password) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.authorities.add(new Authority(Role.ROLE_USER));
    }

    public User(String login, String name, String lastName, String password, Authority authority) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.authorities.add(authority);
    }

    public User(String login, String name, String lastName, String password, Set<Authority> authorities) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        authorities.remove(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

