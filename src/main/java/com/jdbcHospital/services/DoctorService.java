package com.jdbcHospital.services;

import com.jdbcHospital.daos.DoctorsDaoImpl;
import com.jdbcHospital.exceptions.IdNotFoundException;
import com.jdbcHospital.models.Doctors;

import java.util.List;
import java.util.Scanner;

public class DoctorService {
    private final Scanner scan;
    private final DoctorsDaoImpl doctorDaoImpl;

    public DoctorService(){
        scan = new Scanner(System.in);
        doctorDaoImpl = new DoctorsDaoImpl();
    }
    public void addDoctor(){
        System.out.println("Enter Doctor ID: ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Enter Doctor first name: ");
        String fName= scan.nextLine();
        System.out.println("Enter Doctor last name:");
        String lName = scan.nextLine();
        System.out.println("Enter Doctor phone number: ");
        long phone = scan.nextLong();
        scan.nextLine();
        System.out.println("Enter Doctor email: ");
        String email= scan.nextLine();
        System.out.println("Enter Doctor specialization: ");
        String spec = scan.nextLine();
        System.out.println("Enter Doctor depart_id: ");
        int dId = scan.nextInt();
        Doctors doc = new Doctors(id,fName,lName,phone,email,spec, dId);
        doctorDaoImpl.insertDoctor(doc);
    }
    public void viewDoctor(){
        System.out.println("Enter Doctor ID: ");
        int id = scan.nextInt();
        Doctors doc = doctorDaoImpl.getDoctorById(id);
        if(doc != null) {
            System.out.println(doc.toString());
        } else{
            throw new IdNotFoundException(id);
        }
    }
    public void deleteDoctor(){
        System.out.println("Enter Doctor ID: ");
        int id = scan.nextInt();
        doctorDaoImpl.deleteDoctor(id);
    }
    public void updateDoctor() {
        System.out.println("Enter Doctor id that you want to update: ");
        int id = scan.nextInt();
        scan.nextLine();
        Doctors doc = doctorDaoImpl.getDoctorById(id);
        System.out.println(doc);
        System.out.println("Which information would you like to update?");
        System.out.println("1. First Name, 2. Last Name, 3. Phone number, 4. Email, 5. Specialization, 6. Department id ");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice == 1) {
            System.out.println("Enter updated First name :");
            String updatedName = scan.nextLine();
            doc.setFirst_name(updatedName);
        } else if (choice == 2) {
            System.out.println("Enter updated Last name :");
            String updatedName = scan.nextLine();
            doc.setLast_name(updatedName);
        } else if (choice == 3) {
            System.out.println("Enter updated Phone number :");
            long phone = scan.nextInt();
            scan.nextLine();
            doc.setPhone_number(phone);
        } else if (choice == 4) {
            System.out.println("Enter updated email :");
            String updatedEmail = scan.nextLine();
            doc.setEmail(updatedEmail);
        } else if (choice == 5) {
            System.out.println("Enter updated specialization:");
            String spec = scan.nextLine();
            doc.setSpecialization(spec);

        }else if(choice == 6){
            System.out.println("Enter updated Department id :");
            int deprt = scan.nextInt();
            scan.nextLine();
            doc.setDepart_id(deprt);
        }else{
            System.out.println("Invalid choice");
        }
       doctorDaoImpl.updateDoctor(doc, id);

    }
    public void getAllDoctors(){
        List<Doctors> doctorsList = doctorDaoImpl.getAllDoctors();
        for(Doctors doc: doctorsList){
            System.out.println(doc.toString());
        }
    }

}
