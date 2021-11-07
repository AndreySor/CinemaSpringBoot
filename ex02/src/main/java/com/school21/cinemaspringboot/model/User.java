package com.school21.cinemaspringboot.model;

import com.school21.cinemaspringboot.validation.ValidPassword;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    @NotEmpty(message = "{validation.login}")
    private String login;

    @Column(name = "first_name")
    @NotEmpty(message = "{validation.firstName}")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "{validation.lastName}")
    private String lastName;

    @Column(name = "password")
    @ValidPassword
    private String password;

    @Column(name = "email")
    @Email(message = "{validation.email}")
    @NotEmpty(message = "{validation.emailEmp}")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "(\\+7)(\\([0-9]{3}\\))[0-9]{7}", message = "{validation.phone}")
    @NotEmpty(message = "{validation.phoneEmp}")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_confirmed")
    private Boolean isConfirmed;

    @Column(name = "verification_code")
    private String verificationCode;

    public User() {
    }

    public User(Long id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Long id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }
}
