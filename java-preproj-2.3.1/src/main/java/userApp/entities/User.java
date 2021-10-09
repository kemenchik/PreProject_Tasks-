package userApp.entities;


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
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    public User(String login, String name, String lastName, String password) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.roles.add(new Role("ROLE_USER"));
    }

    public User(String login, String name, String lastName, String password, Role role) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.roles.add(role);
    }

    public User(String login, String name, String lastName, String password, Set<Role> roles) {
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
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
