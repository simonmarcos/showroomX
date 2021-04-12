package com.SistemaGestion.ShowroomX.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @Column(name = "nameUser", length = 50, unique = true)
    private String nameUser;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "roles")
    private String roles;

    public User(String nameUser, String password, Boolean status, String roles) {
        this.nameUser = nameUser;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public User() {
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "nameUser='" + nameUser + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return nameUser.equals(user.nameUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameUser);
    }
}
