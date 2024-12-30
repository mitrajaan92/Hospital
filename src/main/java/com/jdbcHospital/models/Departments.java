package com.jdbcHospital.models;

public class Departments {
    private int department_id;
    private String department_name;
    private int floor_number;

    public Departments(){}
    public Departments(int department_id, String department_name, int floor_number) {
        this.department_id = department_id;
        this.department_name = department_name;
        this.floor_number = floor_number;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    @Override
    public String toString() {
        return "Departments: {department_id= " + department_id +
                ", department_name= " + department_name +
                ", floor_number= " + floor_number+"}";
    }
}
