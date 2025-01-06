package com.jdbcHospital.controllers;

import com.jdbcHospital.services.DepartmentService;
import com.jdbcHospital.services.DoctorService;
import com.jdbcHospital.services.PatientService;

import java.util.Scanner;

import static java.lang.System.exit;

public class PatientsController {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DepartmentService departService = new DepartmentService();
        PatientService patientService = new PatientService();
        boolean login = false;
        int attempt = 0;
        try {
            while (!login && attempt < 3) {
                login = patientService.checkLogin();
                if (!login) {
                    System.out.println("Your username or password is wrong! Try again!");
                }
                attempt++;
                if (attempt == 3) {
                    System.out.println("Too many attempts, You are locked out!");
                    exit(0);
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        while (login) {
            System.out.println();
            System.out.println(" |-------------------------------------------------------------Welcome to Unity Hospital---------------------------------------------------------------------|");
            System.out.println("""
                    |-----------------------------------------------------------------------------------------------------------------------------------------------------|
                    | 1. View Department 🔍    |     2. Get all Departments 🏬 🏬                                                                                         |
                    | 3. Create a Patient Account 😷  | 4. View Patient 🔍  5. Update Patient ✍️   | 6. Delete Patient ❌ | 7. Get all Patients 😷😷                     |
                    | 8. Assign Patient To Department   |  9. Unassign Patient to Department |  10. Show all the assigned Patients to Department 😷🏬                   |
                    |                                                             0. Logout ➡️                                                                              |
                    |-----------------------------------------------------------------------------------------------------------------------------------------------------|
                   """);
            System.out.println("Enter your choice: ");
            int option = scan.nextInt();

            switch (option) {
                case 1:
                    departService.viewDepartment();
                    break;
                case 2:
                    departService.getAllDepartments();
                    break;
                case 3:
                    patientService.addPatient();
                    break;
                case 4:
                    patientService.viewPatient();
                    break;
                case 5:
                    patientService.updatePatient();
                    break;
                case 6:
                    patientService.deletePatient();
                    break;
                case 7:
                    patientService.getAllPatients();
                    break;
                case 8:
                    patientService.assignPatientToDepartment();
                    break;
                case 9:
                    patientService.unassignPatientToDepartment();
                    break;
                case 10:
                    patientService.showAllAssignedPatientsToDepart();
                    break;
                case 0:
                    login = false;
                    System.out.println("Thank you for visiting our Hospital! ");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
