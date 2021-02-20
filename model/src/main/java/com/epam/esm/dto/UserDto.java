package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type UserDto.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
public class UserDto implements BaseDto {
    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("balance")
    private BigDecimal balance;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    private Map<String, String> links;

    public UserDto() {
        links = new HashMap<>();
    }

    public UserDto(String login, String name, String surname, BigDecimal balance, boolean isAdmin) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.balance = balance;
        this.isAdmin = isAdmin;
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
     * Gets links.
     *
     * @return the links
     */
    public Map<String, String> getLinks() {
        return links;
    }

    /**
     * Sets links.
     *
     * @param links the links
     */
    public void setLinks(Map<String, String> links) {
        this.links = links;
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
        UserDto user = (UserDto) o;

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
