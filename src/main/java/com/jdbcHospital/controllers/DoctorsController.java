package com.jdbcHospital.controllers;

import com.jdbcHospital.services.DepartmentService;
import com.jdbcHospital.services.DoctorService;

import java.util.Scanner;

import static java.lang.System.exit;

public class DoctorsController {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DepartmentService departService = new DepartmentService();
        DoctorService doctorService = new DoctorService();
        boolean login = false;
        int attempt = 0;
        while (!login && attempt < 3){
            login = doctorService.checkLogin();
            if(!login){
                System.out.println("Your username or password is wrong! Try again!");
            }
            attempt++;
            if(attempt == 3){
                System.out.println("Too many attempts, You are locked out!");
                exit(0);
            }
        }
        while (login) {
            System.out.println();
            System.out.println(" |-------------------------------------------------------------Welcome to Unity Hospital---------------------------------------------------------------------|");
            System.out.println("""
                     |-----------------------------------------------------------------------------------------------------------------------------------------------------|
                     | 1.View Department 🔍           |      2. Get all Departments 🏬 🏬      |       3. View all your Patients 😷😷                                     |
                     | 4. Create a Doctor Account 🥼️  |      5. View Doctor 🔍    |    6. Update Doctor ✍️   |       7. Delete Doctor ❌ |       8. Get all Doctors 🥼🥼  |
                     |                                                             0. Logout ➡️                                                                            |
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
                    doctorService.viewYourPatients();
                    break;
                case 4:
                    doctorService.addDoctor();
                    break;
                case 5:
                    doctorService.viewDoctor();
                    break;
                case 6:
                    doctorService.updateDoctor();
                    break;
                case 7:
                    doctorService.deleteDoctor();
                    break;
                case 8:
                    doctorService.getAllDoctors();
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
