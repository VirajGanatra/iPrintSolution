package com.example.iPrintSolution;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Client {
    private @Id @GeneratedValue long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        } else if (!(o instanceof Client)) {
            return false;
        } else {
            Client client = (Client) o;
            return Objects.equals(client.id, this.id) && Objects.equals(client.username, this.username) && Objects.equals(client.password, this.password);
        }
    }
}
