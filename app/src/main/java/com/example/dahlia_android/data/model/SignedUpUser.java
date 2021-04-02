package com.example.dahlia_android.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class SignedUpUser {

    private String userName;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String agency;
    private String newUserToken;

    public SignedUpUser(String userName, String userEmail, String firstName, String lastName, String agency) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.agency = agency;
        this.newUserToken = "Not-Set";
    }

    public SignedUpUser(String userName, String userEmail, String firstName, String lastName, String agency, String newUserToken) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.agency = agency;
        this.newUserToken = newUserToken;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAgency() {
        return agency;
    }

    public String getNewUserToken() { return newUserToken; }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public void setNewUserToken(String newUserToken) {
        this.newUserToken = newUserToken;
    }
}