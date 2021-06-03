package org.cloud.server.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Table(name = "accounts")
public class Account implements Model {

    public Account() { }

    @Setter
    @Getter
    @NotNull
    @Column(name = "first_name", nullable = false)
    @Size(min = 1, max = 50)
    private String firstName;

    @Setter
    @Getter
    @NotNull
    @Column(name = "last_name", nullable = false)
    @Size(min = 1, max = 50)
    private String lastName;

    @Setter
    @Getter
    @NotNull
    @Column(name = "nick", nullable = false, unique = true)
    @Size(min = 4, max = 17)
    private String nick;

    @Setter
    @Getter
    @NotNull
    @Column(name = "login", nullable = false, unique = true)
    @Size(min = 6, max = 30)
    private String login;

    @Setter
    @Getter
    @NotNull
    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 30)
    private String password;

    @Setter
    @Getter
    @NotNull
    @Column(name = "data_joined", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateJoined;

    @Setter
    @Getter
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;
}
