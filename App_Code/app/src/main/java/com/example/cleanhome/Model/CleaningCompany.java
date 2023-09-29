package com.example.cleanhome.Model;

public class CleaningCompany {

    String id;
    String name;
    String email;
    String address;
    String contactNumber;
    String district;
    String about;
    String P_imageURL;
    String status;
    String type;
    String count;

    public CleaningCompany() {
    }

    public CleaningCompany(String id, String name, String email, String address, String contactNumber, String district, String about, String p_imageURL, String status, String type, String count) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.district = district;
        this.about = about;
        P_imageURL = p_imageURL;
        this.status = status;
        this.type = type;
        this.count = count;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getP_imageURL() {
        return P_imageURL;
    }

    public void setP_imageURL(String p_imageURL) {
        P_imageURL = p_imageURL;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
