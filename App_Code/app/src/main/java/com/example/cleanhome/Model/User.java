package com.example.cleanhome.Model;

public class User {

    private String P_imageURL;
    private String about;
    private String address;
    private String contactNumber;
    private String email;
    private String id;
    private String name;
    private String status;
    private String type;

    public User() {
    }

    public User(String p_imageURL, String about, String address, String contactNumber, String email, String id, String name, String status, String type) {
        P_imageURL = p_imageURL;
        this.about = about;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.id = id;
        this.name = name;
        this.status = status;
        this.type = type;
    }

    public String getP_imageURL() {
        return P_imageURL;
    }

    public void setP_imageURL(String p_imageURL) {
        P_imageURL = p_imageURL;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
