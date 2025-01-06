package com.jdbcHospital.controllers;

import com.jdbcHospital.services.DepartmentService;
import com.jdbcHospital.services.PatientService;

import java.util.Scanner;

public class DepartmentController {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DepartmentService departService = new DepartmentService();
        PatientService patientService = new PatientService();
        boolean stop = false;
        while (!stop) {
            System.out.println();
            System.out.println(" |-------------------------------------------------------------Welcome to Unity Hospital---------------------------------------------------------------------|");
            System.out.println("""
                     |-----------------------------------------------------------------------------------------------------------------------------------------------------|
                     | 1. Add Department 🏬   |  2. View Department 🔍    | 3. Update Department ✍️ | 4. Delete Department ❌ | 5. Get all Departments 🏬 🏬              |
                     | 6. Show all the assigned Patients to Department 😷🏬                   |
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
