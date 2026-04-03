package com.learninglog.learninglogproject.user.model;

// User model class represents a user entity (table in database)
public class User {

    // Instance variables (fields) for User
    private int id;          // Unique ID of the user
    private String name;     // Name of the user
    private String email;   // Email address of the user
    private String password; // Password of the user

    // Default constructor (no-argument constructor)
    // Used when creating empty object and setting values later
    public User(){}

    // Parameterized constructor
    // Used to create object with all values at once
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter method for id
    public int getId() {
        return id;
    }

    // Setter method for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for email
    public String getEmail() {
        return email;
    }

    // Setter method for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter method for passwordn
    public String getPassword() {
        return password;
    }

    // Setter method for password
    public void setPassword(String password) {
        this.password = password;
    }
}