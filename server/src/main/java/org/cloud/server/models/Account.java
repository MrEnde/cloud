package org.cloud.server.models;

import jakarta.validation.constraints.Email;
import lombok.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "accounts")
public class Account implements Model {

    public Account() { }

    @Column(name = "first_name", nullable = false)
    @Size(min = 1, max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 1, max = 50)
    private String lastName;

    @Column(name = "nick", nullable = false, unique = true)
    @Size(min = 4, max = 17)
    private String nick;

    @Column(name = "login", nullable = false, unique = true)
    @Size(min = 6, max = 30)
    private String login;

    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 30)
    private String password;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "data_joined", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateJoined;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
}
