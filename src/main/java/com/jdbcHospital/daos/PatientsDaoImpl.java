package com.jdbcHospital.daos;

import com.jdbcHospital.exceptions.IdNotFoundException;
import com.jdbcHospital.models.Patients;
import com.jdbcHospital.util.MySQLConnector;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//throw exception
public class PatientsDaoImpl implements PatientsDao{
    @Override
    public void insertPatient(Patients p) {
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "insert into patients(first_name, last_name, phone_number, email, gender, address, username, password, doc_id) values(?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, p.getFirst_name());
            ps.setString(2, p.getLast_name());
            ps.setLong(3, p.getPhone_number());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getGender());
            ps.setString(6, p.getAddress());
            ps.setString(7,p.getUsername());
            ps.setString(8,p.getPassword());
            ps.setInt(9,p.getDoc_id());
            ps.executeUpdate();
            System.out.println("Patient is inserted!");
        }catch (SQLIntegrityConstraintViolationException e) {
            // Handle duplicate entry error
            System.err.println("Duplicate entry error for username or password: " + e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updatePatient(Patients p, int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "update patients set first_name =?, last_name=?, phone_number=?, email=?, gender=?, address=?, status=?, doc_id=? where patient_id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, p.getFirst_name());
            ps.setString(2,p.getLast_name());
            ps.setLong(3,p.getPhone_number());
            ps.setString(4, p.getEmail());
            ps.setString(5,p.getGender());
            ps.setString(6,p.getAddress());
            ps.setString(7,p.getStatus());
            ps.setInt(8,p.getDoc_id());
            ps.setInt(9, id);
            ps.executeUpdate();
            System.out.println("Patient is updated! ID = "+id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deletePatient(int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "delete from patients where patient_id = ?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Patient is removed! ID = "+ id);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Patients getPatient(int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from patients where patient_id =?;";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
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
                return p;
            }
            else{
                throw new IdNotFoundException(id);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("The patient does not exist! "+ id); //throw exception
        return null;
    }

    @Override
    public List<Patients> getAllPatients() {
        List<Patients> listP= new ArrayList<>();
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from patients;";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
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
                listP.add(p);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        //throw exception
        return listP;
    }
    public void assignPatientToDepartment(int pId, int dId){
        try(Connection connect = MySQLConnector.getConnection()){
            String query = "insert into patients_departments(p_id, d_id) value(?,?);";
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1,pId);
            ps.setInt(2,dId);
            ps.executeUpdate();
            System.out.println("Patient "+ pId+" is assigned to Department " +dId);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<Patients> getAllAssignedPatientsToDepartment(int dID){
        List<Patients> patientsList = new ArrayList<>();
        try(Connection connect = MySQLConnector.getConnection()){
            String query ="select * from patients_departments where d_id =?;";
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, dID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int pId = rs.getInt("p_id");
                String sql = "select * from patients where patient_id =?;";
                PreparedStatement ps2 = connect.prepareStatement(sql);
                ps2.setInt(1, pId);
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    Patients p = new Patients();
                    p.setPatient_id(rs2.getInt("patient_id"));
                    p.setFirst_name(rs2.getString("first_name"));
                    p.setLast_name(rs2.getString("last_name"));
                    p.setPhone_number(rs2.getLong("phone_number"));
                    p.setEmail(rs2.getString("email"));
                    p.setGender(rs2.getString("gender"));
                    p.setAddress(rs2.getString("address"));
                    p.setStatus(rs.getString("status"));
                    p.setDoc_id(rs.getInt("doc_id"));
                    patientsList.add(p);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return patientsList;
    }
    public void unassignPatientToDepartment(int pId, int dId){
        try(Connection connect = MySQLConnector.getConnection()){
            String query = "delete from patients_departments where p_id =? and d_id= ?";
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1,pId);
            ps.setInt(2,dId);
            ps.executeUpdate();
            System.out.println("Patient "+ pId+" is unassigned to Department " +dId);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void printAllPatientsIds() {
        List<Integer> ids= new ArrayList<>();
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from patients;";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                int id = (rs.getInt("patient_id"));
                ids.add(id);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("All available ids: ");
        for(Integer id: ids){
            System.out.print(" "+ id);
        }
        System.out.println();
    }
    public boolean login(int id, String usr, String pw) {
        boolean login = false;
        String username=" ";
        String password =" ";
        try (Connection con = MySQLConnector.getConnection()) {
            String query = "select username,password from patients where patient_id =?;";
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
}
