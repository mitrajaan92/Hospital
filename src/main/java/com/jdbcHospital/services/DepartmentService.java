package com.jdbcHospital.services;

import com.jdbcHospital.daos.DepartmentDaoImpl;
import com.jdbcHospital.daos.PatientsDaoImpl;
import com.jdbcHospital.models.Departments;
import com.jdbcHospital.models.Patients;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentService {
    private final Scanner  scan;
    private final DepartmentDaoImpl departmentDao;
    private final PatientsDaoImpl patientsDao;

    public DepartmentService(){
        scan = new Scanner(System.in);
        departmentDao = new DepartmentDaoImpl();
        patientsDao = new PatientsDaoImpl();
    }
    public void addDepartment(){
        System.out.println("Enter the department id: ");
        int dId = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter the department name: ");
        String dName = scan.nextLine();
        System.out.println("Enter the department floor number: ");
        int dFloor = scan.nextInt();
        Departments depart = new Departments(dId,dName,dFloor);
        departmentDao.insertDepartment(depart);
    }
    public void viewDepartment(){
        departmentDao.printAllDepartments();
        System.out.println("Enter the department ID: ");
        int id = scan.nextInt();
        System.out.println("********* Department *********");
        Departments d = departmentDao.getDepartmentById(id);
        System.out.println(d);
        System.out.println("********* Assigned Patients to the Department: "+ id+": *********");
        List<Patients> patients = patientsDao.getAllAssignedPatientsToDepartment(id);
        for(Patients p: patients){
            System.out.println(p);
        }
    }
    public void updateDepartment(){
        departmentDao.printAllDepartments();
        System.out.println("Enter department id that you want to update: ");
        int id = scan.nextInt();
        scan.nextLine();
        Departments depart = departmentDao.getDepartmentById(id);
        System.out.println(depart);
        System.out.println("Which information would you like to update?");
        System.out.println("1. Name, 2. Floor number?");
        int choice = scan.nextInt();
        scan.nextLine();
        if(choice == 1){
            System.out.println("Enter updated department name :");
            String updatedName = scan.nextLine();
            depart.setDepartment_name(updatedName);
        }else if(choice == 2){
            System.out.println("Enter updated floor number :");
            int floor = scan.nextInt();
            scan.nextLine();
            depart.setFloor_number(floor);
        }else{
            System.out.println("Invalid choice");
        }
        departmentDao.updateDepartment(depart, id);
    }
    public void deleteDepartment(){
        departmentDao.printAllDepartments();
        System.out.println("Enter department id that you want to delete: ");
        int dID = scan.nextInt();
        departmentDao.deleteDepartment(dID);
    }
    public void getAllDepartments(){
        List<Departments> departs;
        departs = departmentDao.getAllDepartments();
        for(Departments d: departs){
            System.out.println("********* Department *********");
            System.out.println(d.toString());
            int id = d.getDepartment_id();
            System.out.println("=> Assigned Patients to the Department: "+ id+": ");
            List<Patients> patients = patientsDao.getAllAssignedPatientsToDepartment(id);
            for(Patients p: patients){
                System.out.println(p);
            }
            System.out.println();
        }
    }



}
