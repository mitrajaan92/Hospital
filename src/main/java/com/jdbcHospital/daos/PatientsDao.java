package com.jdbcHospital.daos;

import com.jdbcHospital.models.Patients;

import java.util.List;

public interface PatientsDao {
    void insertPatient(Patients p);
    void updatePatient(Patients p, int id);
    void deletePatient(int id);
    Patients getPatient(int id);
    List<Patients> getAllPatients();
    void assignPatientToDepartment(int pId, int dId);
    List<Patients> getAllAssignedPatientsToDepartment(int dID);
    void unassignPatientToDepartment(int pId, int dId);

    }
