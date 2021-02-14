package com.epam.esm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements BaseEntity {
    @Id
    @Column(name = "login")
    @NotNull(message = "javax.validation.constraints.NotNull.message.login")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.Size.message.login")
    private String login;
    @Column(name = "name")
    @NotNull(message = "javax.validation.constraints.NotNull.message.name")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.Size.message.name")
    private String name;
    @Column(name = "surname")
    @NotNull(message = "javax.validation.constraints.NotNull.message.surname")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.Size.message.surname")
    private String surname;
    @Column(name = "isAdmin")
    private boolean isAdmin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserOrder> orders = new ArrayList<>();

    public User() {
    }

    public User(String login, String name, String surname, boolean isAdmin) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
    }

    public User(String login,String name, String surname, boolean isAdmin, List<UserOrder> orders) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
        this.orders = orders;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o ==null || this.getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;

        return (Objects.equals(name, user.name) && Objects.equals(surname, user.surname)
                && Objects.equals(login, user.login) && isAdmin == user.isAdmin);

    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + (name == null ? 0 : name.hashCode());
        result += result * 31 + (surname == null ? 0 : surname.hashCode());
        result += result * 31 + (login == null ? 0 : login.hashCode());
        result += result * 31 + Boolean.hashCode(isAdmin);

        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(login)
                .append(" ")
                .append(name)
                .append(" ")
                .append(surname)
                .toString();
    }
}
