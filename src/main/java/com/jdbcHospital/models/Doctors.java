package com.jdbcHospital.models;

public class Doctors {
    private int doctor_id;
    private String first_name;
    private String last_name;
    private long phone_number;
    private String email;
    private String specialization;
    private int depart_id;

    public Doctors(){}
    public Doctors(int doctor_id, String first_name, String last_name, long phone_number, String email, String specialization, int depart_id) {
        this.doctor_id = doctor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.specialization = specialization;
        this.depart_id = depart_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(int depart_id) {
        this.depart_id = depart_id;
    }

    @Override
    public String toString() {
        return "Doctors:{ " +
                "doctor_id = " + this.doctor_id +
                ", first_name = " + this.first_name +
                ", last_name = " + this.last_name +
                ", phone_number = " + phone_number +
                ", email = " + email +
                ", specialization = " + specialization +
                ", depart_id = " + depart_id +
                '}';
    }
}
