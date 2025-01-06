package com.jdbcHospital.models;

public class Patients {
    private int patient_id;
    private String first_name;
    private String last_name;
    private long phone_number;
    private String email;
    private String gender;
    private String address;
    private int doc_id;
    private String status;
    private String username;
    private String password;

    public Patients(){}
    public Patients(String first_name, String last_name, long phone_number, String email, String gender, String address, String username, String password, int doc_id) {
    //    this.patient_id = patient_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.username = username;
        this.password = password;
        this.doc_id = doc_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(int doc_id) {
        this.doc_id = doc_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patients: {" +
                "  patient_id = " + patient_id +
                ", first_name = " + first_name +
                ", last_name = " + last_name +
                ", phone_number = " + phone_number +
                ", email= " + email +
                ", gender = " + gender +
                ", address = " + address +
                ", status = " + status+
                ", doctor id = "+ doc_id+
                "}";
    }
}
