package com.example.tourguider;

public class Guide {
    private int id_user;
    private String username;
    private String name;
    private String surname;
    private String email;
    private int phone;

    public Guide(int id_user, String username, String name, String surname, String email, int phone) {
       this.id_user = id_user;
       this.username = username;
       this.name = name;
       this.surname = surname;
       this.email = email;
       this.phone = phone;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
