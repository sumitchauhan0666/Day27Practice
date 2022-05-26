package com.bridgelabz.Addressbookio;

public class Contact {
    private String firstname;
    private String lastname;
    private String address;
    private String city;
    private String state;
    private int zip;
    private long phonenumber;
    private String email;


    Contact(String firstname, String lastname, String address, String city, String state, int zip, long phonenumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phonenumber = phonenumber;
        this.email = email;
    }


    public String getFirstname() {
        return firstname;
    }

    public String setFirstname(String firstname) {
        this.firstname = firstname;
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String setLastname(String lastname) {
        this.lastname = lastname;
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String setAddress(String address) {
        this.address = address;
        return address;
    }

    public String getCity() {
        return city;
    }

    public String setCity(String city) {
        this.city = city;
        return city;
    }

    public String getState() {
        return state;
    }

    public String setState(String state) {
        this.state = state;
        return state;
    }

    public int getZip() {
        return zip;
    }

    public int setZip(int zip) {
        this.zip = zip;
        return zip;
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    public long setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", phonenumber=" + phonenumber +
                ", email='" + email + '\'' +
                '}';
    }
}