package com.jdbcHospital.daos;

import com.jdbcHospital.exceptions.IdNotFoundException;
import com.jdbcHospital.models.Doctors;
import com.jdbcHospital.util.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DoctorsDaoImpl implements DoctorsDao{
    @Override
    public void insertDoctor(Doctors doc) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "insert into doctors(doctor_id, first_name, last_name, phone_number, email,specialization, depart_id) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, doc.getDoctor_id());
            ps.setString(2, doc.getFirst_name());
            ps.setString(3, doc.getLast_name());
            ps.setLong(4,doc.getPhone_number());
            ps.setString(5,doc.getEmail());
            ps.setString(6, doc.getSpecialization());
            ps.setInt(7, doc.getDepart_id());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateDoctor(Doctors doc, int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "update doctors set first_name =?, last_name =?, phone_number=?, email =?, specialization=?, depart_id =? where doctor_id =?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, doc.getFirst_name());
            ps.setString(2, doc.getLast_name());
            ps.setLong(3,doc.getPhone_number());
            ps.setString(4,doc.getEmail());
            ps.setString(5, doc.getSpecialization());
            ps.setInt(6, doc.getDepart_id());
            ps.setInt(7, id);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "delete from doctors where id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Doctors getDoctorById(int id) {
        Doctors doc;
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from doctors where doctor_id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                doc = new Doctors();
                doc.setDoctor_id(rs.getInt("doctor_id"));
                doc.setFirst_name(rs.getString("first_name"));
                doc.setLast_name(rs.getString("last_name"));
                doc.setPhone_number(rs.getLong("phone_number"));
                doc.setEmail(rs.getString("email"));
                doc.setSpecialization(rs.getString("specialization"));
                doc.setDepart_id(rs.getInt("depart_id"));
                return doc;
            }
            else{
                throw new IdNotFoundException(id);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctors> getAllDoctors() {
        Doctors doc;
        List<Doctors> docs = new ArrayList<>();
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from doctors;";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return docs;
    }
}
