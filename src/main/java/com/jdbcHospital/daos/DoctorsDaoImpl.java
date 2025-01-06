package com.jdbcHospital.daos;

import com.jdbcHospital.exceptions.IdNotFoundException;
import com.jdbcHospital.models.Doctors;
import com.jdbcHospital.models.Patients;
import com.jdbcHospital.util.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDaoImpl implements DoctorsDao {
    @Override
    public void insertDoctor(Doctors doc) {
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "insert into doctors(doctor_id, first_name, last_name, phone_number, email,specialization, depart_id) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doc.getDoctor_id());
            ps.setString(2, doc.getFirst_name());
            ps.setString(3, doc.getLast_name());
            ps.setLong(4, doc.getPhone_number());
            ps.setString(5, doc.getEmail());
            ps.setString(6, doc.getSpecialization());
            ps.setInt(7, doc.getDepart_id());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle duplicate entry error
            System.err.println("Duplicate entry error for username or password: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateDoctor(Doctors doc, int id) {
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "update doctors set first_name =?, last_name =?, phone_number=?, email =?, specialization=?, depart_id =? where doctor_id =?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, doc.getFirst_name());
            ps.setString(2, doc.getLast_name());
            ps.setLong(3, doc.getPhone_number());
            ps.setString(4, doc.getEmail());
            ps.setString(5, doc.getSpecialization());
            ps.setInt(6, doc.getDepart_id());
            ps.setInt(7, id);
            ps.executeUpdate();
            System.out.println("Doctor is updated! ID: "+doc.getDoctor_id());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(int id) {
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "delete from doctors where id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Doctors getDoctorById(int id) {
        Doctors doc;
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "select * from doctors where doctor_id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                doc = new Doctors();
                doc.setDoctor_id(rs.getInt("doctor_id"));
                doc.setFirst_name(rs.getString("first_name"));
                doc.setLast_name(rs.getString("last_name"));
                doc.setPhone_number(rs.getLong("phone_number"));
                doc.setEmail(rs.getString("email"));
                doc.setSpecialization(rs.getString("specialization"));
                doc.setDepart_id(rs.getInt("depart_id"));
                return doc;
            } else {
                throw new IdNotFoundException(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctors> getAllDoctors() {
        Doctors doc;
        List<Doctors> docs = new ArrayList<>();
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "select * from doctors;";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                doc = new Doctors();
                doc.setDoctor_id(rs.getInt("doctor_id"));
                doc.setFirst_name(rs.getString("first_name"));
                doc.setLast_name(rs.getString("last_name"));
                doc.setPhone_number(rs.getLong("phone_number"));
                doc.setEmail(rs.getString("email"));
                doc.setSpecialization(rs.getString("specialization"));
                doc.setDepart_id(rs.getInt("depart_id"));
                docs.add(doc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return docs;
    }
    @Override
    public void printAllIds() {
        List<Integer> docsIds = new ArrayList<>();
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "select * from doctors;";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = (rs.getInt("doctor_id"));
                docsIds.add(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("All available Ids: ");
        for (Integer id : docsIds) {
            System.out.print(" " + id);
        }
        System.out.println();
    }
    @Override
    public boolean login(int id, String usr, String pw) {
        boolean login = false;
        String username=" ";
        String password =" ";
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "select username,password from doctors where doctor_id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                username = (rs.getString("username"));
                password = (rs.getString("password"));

            } else {
                throw new IdNotFoundException(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(usr.equals(username)  && pw.equals(password)){
            login = true;
        }
        return login;
    }
    @Override
    public List<Patients> viewAllPatients(int docId){
        List<Patients> patients = new ArrayList<>();
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "select * from patients where doc_id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,docId);
            ResultSet rs = ps.executeQuery();
            if(!(rs.next())){
                System.out.println("No patients has been assigned!");
            }
            while (rs.next()) {
                Patients p = new Patients();
                p.setPatient_id(rs.getInt("patient_id"));
                p.setFirst_name(rs.getString("first_name"));
                p.setLast_name(rs.getString("last_name"));
                p.setPhone_number(rs.getLong("phone_number"));
                p.setEmail(rs.getString("email"));
                p.setGender(rs.getString("gender"));
                p.setAddress(rs.getString("address"));
                p.setStatus(rs.getString("status"));
                p.setDoc_id(rs.getInt("doc_id"));
                patients.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return patients;
    }
}
