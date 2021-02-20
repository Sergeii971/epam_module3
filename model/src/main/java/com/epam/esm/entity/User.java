package com.epam.esm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The type User.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
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
    @NotNull(message = "javax.validation.constraints.NotNull.message.price")
    @Min(value = 1, message = "javax.validation.constraints.Min.message.price")
    @Max(value = 100000000, message = "javax.validation.constraints.Max.message.price")
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "isAdmin")
    private boolean isAdmin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserOrder> orders = new ArrayList<>();

    public User() {
    }

    public User(String login, String name, String surname, BigDecimal balance, boolean isAdmin) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public User(String login, String name, String surname, BigDecimal balance, boolean isAdmin, List<UserOrder> orders) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.isAdmin = isAdmin;
        this.orders = orders;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets is admin.
     *
     * @return isAdmin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets isAdmin.
     *
     * @param admin the is Admin
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Gets balance.
     *
     * @return the balance
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets balance.
     *
     * @param balance the balance
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
                && Objects.equals(balance, user.balance) && Objects.equals(login, user.login) && isAdmin == user.isAdmin);

    }

    @Override
    public int hashCode() {
        int result = 1;

        result += result * 31 + (name == null ? 0 : name.hashCode());
        result += result * 31 + (surname == null ? 0 : surname.hashCode());
        result += result * 31 + (login == null ? 0 : login.hashCode());
        result += result * 31 + Boolean.hashCode(isAdmin);
        result += result * 31 + balance.hashCode();
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
                .append(" ")
                .append(balance)
                .toString();
    }
}
