package userApp.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
}
