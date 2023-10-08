package com.example.myapplication;

public class Contact {
    String name;
    String Designation;
    String address;

    public Contact() {

    }

    public Contact(String name, String designation, String address) {
        this.name = name;
        this.Designation = designation;

        this.address = address;}

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





        public String getAddress() {
            return address;
        }

        public void setAddress(String address){
            this.address = address;
        }


    }

