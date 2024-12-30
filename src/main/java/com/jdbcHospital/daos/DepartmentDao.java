package com.jdbcHospital.daos;

import com.jdbcHospital.models.Departments;

import java.util.List;

public interface DepartmentDao {
    void insertDepartment(Departments depart);
    void updateDepartment(Departments depart, int id);
    void deleteDepartment(int id);
    Departments getDepartmentById(int id);
    List<Departments> getAllDepartments();




}
