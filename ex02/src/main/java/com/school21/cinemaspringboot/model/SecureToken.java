package com.school21.cinemaspringboot.model;


import javax.persistence.*;

@Entity
@Table(name = "secure_token")
public class SecureToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
