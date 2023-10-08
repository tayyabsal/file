package com.example.myapplication;

public class Users {
    String name;
    String Designation;
    String contact;
    String address;

    public Users() {

    }

    public Users(String name, String designation, String contact, String address) {
        this.name = name;
        this.Designation = designation;
        this.contact = contact;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
