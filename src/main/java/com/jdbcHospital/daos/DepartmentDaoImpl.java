package com.jdbcHospital.daos;

import com.jdbcHospital.exceptions.IdNotFoundException;
import com.jdbcHospital.models.Departments;
import com.jdbcHospital.util.MySQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public void insertDepartment(Departments depart) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "insert into Departments(department_id, department_name,floor_number) values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,depart.getDepartment_id());
            ps.setString(2, depart.getDepartment_name());
            ps.setInt(3, depart.getFloor_number());
            ps.executeUpdate();
            System.out.println("Department added successfully! ID = " + depart.getDepartment_id());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateDepartment(Departments depart, int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "update Departments set department_name=?, floor_number=? where department_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, depart.getDepartment_name());
            ps.setInt(2, depart.getFloor_number());
            ps.setInt(3,id);
            ps.executeUpdate();
            System.out.println("Department updated successfully! ID = "+id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteDepartment(int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "delete from Departments where department_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Department is removed successfully! ID = "+id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Departments getDepartmentById(int id) {
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from Departments where department_id =?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Departments depart = new Departments();
                depart.setDepartment_id(rs.getInt("department_id"));
                depart.setDepartment_name(rs.getString("department_name"));
                depart.setFloor_number(rs.getInt("floor_number"));
                return depart;
            }
            else{
                throw new IdNotFoundException(id);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Department table is empty!");
        return null;
    }

    @Override
    public List<Departments> getAllDepartments() {
        List<Departments> departments = new ArrayList<>();
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from Departments;";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Departments depart = new Departments();
                depart.setDepartment_id(rs.getInt("department_id"));
                depart.setDepartment_name(rs.getString("department_name"));
                depart.setFloor_number(rs.getInt("floor_number"));
                departments.add(depart);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return departments;
    }
    public void printAllDepartments() {
        List<Integer> departmentsIds = new ArrayList<>();
        try(Connection con = MySQLConnector.getConnection()){
            String query = "select * from Departments;";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int id = (rs.getInt("department_id"));

                departmentsIds.add(id);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Available Ids: ");
        for(Integer id: departmentsIds){
            System.out.print(" "+id);
        }
        System.out.println();
    }


}
