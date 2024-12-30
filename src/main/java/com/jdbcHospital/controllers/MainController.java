package com.jdbcHospital.controllers;

import com.jdbcHospital.services.DepartmentService;
import com.jdbcHospital.services.DoctorService;
import com.jdbcHospital.services.PatientService;

import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        DepartmentService departService = new DepartmentService();
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        boolean stop = false;
        while (!stop) {
            System.out.println();
            System.out.println(" |-------------------------------------------------------------Welcome to Hospital---------------------------------------------------------------------|");
            System.out.println("""
                    |-----------------------------------------------------------------------------------------------------------------------------------------------------|
                    | 1. Add Department 🏬   |  2. View Department 🔍    | 3. Update Department ✍️ | 4. Delete Department ❌ | 5. Get all Departments 🏬 🏬              |
                    | 6. Add Doctor 🥼️  | 7. View Doctor 🔍  8. Update Doctor ✍️   | 9. Delete Doctor ❌ | 10. Get all Doctors 🥼🥼                                      |
                    | 11. Add Patient 😷  | 12. View Patient 🔍  13. Update Patient ✍️   | 14. Delete Patient ❌ | 15. Get all Patients 😷😷                             |
                    | 16. Assign Patient To Department   |  17. Unassign Patient to Department |  18. Show all the assigned Patients to Department 😷🏬                   |
                    |                                                             0. exit ➡️                                                                              |
                    |-----------------------------------------------------------------------------------------------------------------------------------------------------|
                   """);
            System.out.println("Enter your choice: ");
            int option = scan.nextInt();

            switch (option) {
                case 1:
                    departService.addDepartment();
                    break;
                case 2:
                    departService.viewDepartment();
                    break;
                case 3:
                    departService.updateDepartment();
                    break;
                case 4:
                    departService.deleteDepartment();
                    break;
                case 5:
                    departService.getAllDepartments();
                    break;
                case 6:
                    doctorService.addDoctor();
                    break;
                case 7:
                    doctorService.viewDoctor();
                    break;
                case 8:
                    doctorService.updateDoctor();
                    break;
                case 9:
                    doctorService.deleteDoctor();
                    break;
                case 10:
                    doctorService.getAllDoctors();
                    break;
                case 11:
                    patientService.addPatient();
                    break;
                case 12:
                    patientService.viewPatient();
                    break;
                case 13:
                    patientService.updatePatient();
                    break;
                case 14:
                    patientService.deletePatient();
                    break;
                case 15:
                    patientService.getAllPatients();
                    break;
                case 16:
                    patientService.assignPatientToDepartment();
                    break;
                case 17:
                    patientService.unassignPatientToDepartment();
                    break;
                case 18:
                    patientService.showAllAssignedPatientsToDepart();
                    break;
                case 0:
                    stop = true;
                    System.out.println("Thank you for visiting our Hospital! ");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }
}
