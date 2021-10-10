package com.example.kemen.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Authority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.name();
    }
}



