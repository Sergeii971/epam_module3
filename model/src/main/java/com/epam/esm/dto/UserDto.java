package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDto implements BaseDto {
    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    private List<String> links;

    public UserDto() {
        links = new ArrayList<>();
    }

    public UserDto(String login, String name, String surname, boolean isAdmin) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.isAdmin = isAdmin;
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
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
