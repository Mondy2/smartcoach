package com.example.smartcoach.models;

public class UserProfile {
    private String profileID;
    private String userID;
    private String firstName;
    private String lastName;
    private int age;
    private int height;
    private float weight;
    private String gender;
    private String fitnessGoal; // Наприклад: "WeightLoss", "MuscleGain", "Maintenance"
    private String trainingMode; // Наприклад: "Beginner", "Intermediate", "Advanced"

    // Конструктор за замовчуванням для Firebase
    public UserProfile() {}

    public UserProfile(String profileID, String userID, String fitnessGoal, String trainingMode) {
        this.profileID = profileID;
        this.userID = userID;
        this.fitnessGoal = fitnessGoal;
        this.trainingMode = trainingMode;
    }

    // Геттери і сеттери
    public String getProfileID() { return profileID; }
    public void setProfileID(String profileID) { this.profileID = profileID; }
    public String getUserID() { return userID; }
    public void setUserID(String userID) { this.userID = userID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }
    public String getTrainingMode() { return trainingMode; }
    public void setTrainingMode(String trainingMode) { this.trainingMode = trainingMode; }
}
