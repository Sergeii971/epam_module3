package com.epam.esm.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * The type User.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Entity
@Table(name = "user")
public class User implements BaseEntity, UserDetails {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    @Column(name = "login")
    @NotNull(message = "javax.validation.constraints.NotNull.message.login")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.Size.message.login")
    private String login;
    @Column(name = "password")
    @NotNull(message = "javax.validation.constraints.NotNull.message.password")
    @Size(min = 5, max = 128, message = "javax.validation.constraints.Size.message.password")
    private String password;
    @Column(name = "name")
    @NotNull(message = "javax.validation.constraints.NotNull.message.name")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.Size.message.name")
    private String name;
    @Column(name = "surname")
    @NotNull(message = "javax.validation.constraints.NotNull.message.surname")
    @Size(min = 1, max = 45, message = "javax.validation.constraints.Size.message.surname")
    private String surname;
    @NotNull(message = "javax.validation.constraints.NotNull.message.balance")
    @DecimalMin(value = "1", message = "javax.validation.constraints.Min.message.balance")
    @DecimalMax(value = "100000000", message = "javax.validation.constraints.Max.message.balance")
    @Digits(integer = 10, fraction = 4)
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "isAdmin")
    private boolean isAdmin;
    @OneToMany(mappedBy = "user")
    private List<UserOrder> orders = new ArrayList<>();
    @Transient
    private Set<Role> roles;

    public User() {
        roles = new HashSet<>();
    }

    public User(long userId, String login, String name, String surname, BigDecimal balance, boolean isAdmin) {
        roles = new HashSet<>();
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public User(long userId, String login, String name, String surname, BigDecimal balance, boolean isAdmin, List<UserOrder> orders) {
        roles = new HashSet<>();
        this.userId = userId;
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
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets isAdmin.
     *
     * @param admin the is Admin
     */
    public void setIsAdmin(boolean admin) {
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

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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
        result += result * 31 + (balance == null ? 0 : balance.hashCode());
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
