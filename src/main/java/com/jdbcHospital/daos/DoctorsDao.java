package com.jdbcHospital.daos;

import com.jdbcHospital.models.Doctors;
import com.jdbcHospital.models.Patients;

import java.util.List;

public interface DoctorsDao {
    void insertDoctor(Doctors doc);
    void updateDoctor(Doctors doc, int id);
    void deleteDoctor(int id);
    Doctors getDoctorById(int id);
    List<Doctors> getAllDoctors();
    boolean login(int id, String usr, String pw);
    void printAllIds();
    List<Patients> viewAllPatients(int docId);
}
