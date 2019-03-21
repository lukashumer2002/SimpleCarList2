package com.example.simplecarlist;

public class Logic {

    int id;
    String firstName;
    String lastName;
    String email;
    String gender;
    double lat;
    double lonG;
    String street;
    String street_nb;
    String postalCode;
    String city;
    String make;
    String model;
    String year;
    String car_vin;

    public Logic(String firstName, String lastName, String make, String model) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.make = make;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public double getLat() {
        return lat;
    }

    public double getLonG() {
        return lonG;
    }

    public String getStreet() {
        return street;
    }

    public String getStreet_nb() {
        return street_nb;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getCar_vin() {
        return car_vin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLonG(double lonG) {
        this.lonG = lonG;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreet_nb(String street_nb) {
        this.street_nb = street_nb;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCar_vin(String car_vin) {
        this.car_vin = car_vin;
    }

    public String myString()
    {
        return firstName+" "+lastName+" "+make+" "+ model;
    }

    public String writeString()
    {
        return firstName+","+lastName+","+make+","+ model;
    }
}
