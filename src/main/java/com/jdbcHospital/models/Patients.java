package com.jdbcHospital.models;

public class Patients {
    private int patient_id;
    private String first_name;
    private String last_name;
    private long phone_number;
    private String email;
    private String gender;
    private String address;

    public Patients(){}
    public Patients(int patient_id, String first_name, String last_name, long phone_number, String email, String gender, String address) {
        this.patient_id = patient_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.gender = gender;
        this.address = address;
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

    @Override
    public String toString() {
        return "Patients: {" +
                "  patient_id = " + patient_id +
                ", first_name = " + first_name +
                ", last_name = " + last_name +
                ", phone_number = " + phone_number +
                ", email= " + email +
                ", gender = " + gender +
                ", address = " + address +"}";
    }
}
