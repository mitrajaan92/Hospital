package com.jdbcHospital.services;

import com.jdbcHospital.daos.DepartmentDaoImpl;
import com.jdbcHospital.daos.PatientsDaoImpl;
import com.jdbcHospital.exceptions.IdNotFoundException;
import com.jdbcHospital.models.Departments;
import com.jdbcHospital.models.Patients;

import java.util.List;
import java.util.Scanner;

public class PatientService {
    private final Scanner scan;
    private final PatientsDaoImpl patientsDao;
    private final DepartmentDaoImpl departmentDao;

    public PatientService(){
        scan = new Scanner(System.in);
        patientsDao = new PatientsDaoImpl();
        departmentDao = new DepartmentDaoImpl();
    }
    public boolean checkLogin(){
        System.out.println("Enter Your ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter username: ");
        String user = scan.nextLine();
        System.out.println("Enter password: ");
        String pw = scan.nextLine();
        return patientsDao.login(id,user,pw);
    }
    public void addPatient(){
        System.out.println("Enter Patient first name: ");
        String fName= scan.nextLine();
        System.out.println("Enter Patient last name:");
        String lName = scan.nextLine();
        System.out.println("Enter Patient phone number: ");
        long phone = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Patient email: ");
        String email= scan.nextLine();
        System.out.println("Enter Patient gender: ");
        String gender = scan.nextLine();
        System.out.println("Enter Patient address: ");
        String address = scan.nextLine();
        System.out.println("Enter the Doctor id:  ");
        int doc = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter your username: ");
        String user = scan.nextLine();
        System.out.println("Enter a password: ");
        String pw = scan.nextLine();
        Patients patient = new Patients(fName,lName,phone,email,gender, address, user, pw, doc);
        patientsDao.insertPatient(patient);
    }
    public void viewPatient(){
        patientsDao.printAllPatientsIds();
        System.out.println("Enter Patient ID: ");
        int id = scan.nextInt();
        Patients p = patientsDao.getPatient(id);
        if(p != null){
            System.out.println(p.toString());
        }
        else{
            throw new IdNotFoundException(id);
        }
    }
    public void deletePatient(){
        patientsDao.printAllPatientsIds();
        System.out.println("Enter Patient ID: ");
        int id = scan.nextInt();
        patientsDao.deletePatient(id);
    }
    public void updatePatient() {
        patientsDao.printAllPatientsIds();
        System.out.println("Enter Patient id that you want to update: ");
        int id = scan.nextInt();
        scan.nextLine();
        Patients p = patientsDao.getPatient(id);
        System.out.println(p);
        System.out.println("Which information would you like to update?");
        System.out.println("1. First Name, 2. Last Name, 3. Phone number, 4. Email, 5. Gender, 6. Address, 7. Status, 8. Doctor id ");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice == 1) {
            System.out.println("Enter updated First name :");
            String updatedName = scan.nextLine();
            p.setFirst_name(updatedName);
        } else if (choice == 2) {
            System.out.println("Enter updated Last name :");
            String updatedName = scan.nextLine();
            p.setLast_name(updatedName);
        } else if (choice == 3) {
            System.out.println("Enter updated Phone number :");
            int phone = scan.nextInt();
            scan.nextLine();
            p.setPhone_number(phone);
        } else if (choice == 4) {
            System.out.println("Enter updated email :");
            String updatedEmail = scan.nextLine();
            p.setEmail(updatedEmail);
        } else if (choice == 5) {
            System.out.println("Enter updated gender:");
            String gender = scan.nextLine();
            p.setGender(gender);

        }else if(choice == 6) {
            System.out.println("Enter updated address :");
            String address = scan.nextLine();
            scan.nextLine();
            p.setAddress(address);
        }else if(choice == 7){
            System.out.println("Enter updated status :");
            String status = scan.nextLine();
            scan.nextLine();
            p.setStatus(status);
        }else if(choice == 8){
            System.out.println("Enter updated doctor id :");
            int doc= scan.nextInt();
            scan.nextLine();
            p.setDoc_id(doc);
        }else{
            System.out.println("Invalid choice");
        }
        patientsDao.updatePatient(p,id);

    }
    public void getAllPatients(){
        List<Patients> patientsList = patientsDao.getAllPatients();
        for(Patients p: patientsList){
            System.out.println(p.toString());
        }
    }
    public void assignPatientToDepartment(){
        System.out.println("Enter Department id: ");
        departmentDao.printAllDepartments();
        int dId = scan.nextInt();
        Departments depart = departmentDao.getDepartmentById(dId);
        System.out.println(depart);
        List<Patients> alreadyAssignedPatients =patientsDao.getAllAssignedPatientsToDepartment(dId);
        List<Patients> allPatients =patientsDao.getAllPatients();
        System.out.println("Select Patient id that you would like to assign to Department "+dId +" from the list below: ");
        for(Patients pat: allPatients){
            if(!(alreadyAssignedPatients.contains(pat))){
                System.out.println(pat);
            }
        }
        int pId= scan.nextInt();
        patientsDao.assignPatientToDepartment(pId,dId);
    }
    public void showAllAssignedPatientsToDepart(){
        System.out.println("Enter the department id: ");
        departmentDao.printAllDepartments();
        int dID = scan.nextInt();
        List<Patients> patients = patientsDao.getAllAssignedPatientsToDepartment(dID);
        for(Patients p: patients){
            System.out.println(p);
        }
    }
    public void unassignPatientToDepartment(){
        System.out.println("Enter Department id that you would like to unassign to the patient: ");
        departmentDao.printAllDepartments();
        int dId= scan.nextInt();
        List<Patients> assignedPatients = patientsDao.getAllAssignedPatientsToDepartment(dId);
        for(Patients p: assignedPatients){
            System.out.println(p);
        }
        System.out.println("Which Patient id would you like to unassign? ");
        int pId = scan.nextInt();
        patientsDao.unassignPatientToDepartment(pId,dId);
    }


}
