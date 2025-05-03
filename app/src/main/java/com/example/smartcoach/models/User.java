package com.example.smartcoach.models;

public class User {
    private String name;
    private String email;
    private String password;
    private int height;
    private float weight;
    private int birthYear;
    private String profileImageUrl;
    public User(){}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getHeight() {return height;}

    public void setHeight(int height) {this.height = height;}

    public float getWeight() {return weight;}

    public void setWeight(float weight) {this.weight = weight;}

    public int getBirthYear() {return birthYear;}

    public void setBirthYear(int birthYear) {this.birthYear = birthYear;}

    public String getProfileImageUrl() {return profileImageUrl;}

    public void setProfileImageUrl(String profileImageUrl) {this.profileImageUrl = profileImageUrl;}
}
