package com.example.iPrintSolution.objects.client;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.Type;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Schema(
        description = "User schema",
        type = "Client",
        example = " {\"username\" :\"testuser\" ,"
+ "\"password\": \"testpassword\", " + "\"role\": \"ROLE_USER\"}"
)
public class Client {



    @Column(name = "id", nullable = false)
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;



    @Column(name = "roles", nullable = false)
    @Type(type = "com.example.iPrintSolution.objects.client.GenericArrayUserType")
    private String[] roles;

    public Client() {}

    public Client(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role=role;
    }

    public Client(String username, String password, String[] roles) {
        this.username = username;
        this.password = password;
        this.roles=roles;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            System.out.println("this == o");
            return true;
        } else if (!(o instanceof Client)) {
            return false;
        } else {
            Client client = (Client) o;
            return Objects.equals(client.id, this.id) && Objects.equals(client.username, this.username) && Objects.equals(client.password, this.password) && Arrays.equals(client.roles, this.roles);
        }
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", roles='" + Arrays.toString(roles) + '\'' + '}';
    }
}
