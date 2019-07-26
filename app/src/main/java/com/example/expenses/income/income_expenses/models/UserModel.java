package com.example.expenses.income.income_expenses.models;

public class UserModel {

    private Integer idUser;
    private String email;
    private String password;
    private String name;
    private String surname;

    public UserModel(Integer idUser, String email, String password, String name, String surname) {
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public UserModel(String email, String password, String name, String surname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
