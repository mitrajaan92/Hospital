package com.jdbcHospital.util;

import java.util.Scanner;

public class HwStringPilandrome {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("enter a string to check if it is palindrome or not: ");
        String word = scan.nextLine();
        char[] reverse= new char[word.length()];
        int j=0;
        boolean equal = true;
        for(int i= word.length()-1; i>=0;i--){
            reverse[j]= word.charAt(i);
            j++;
        }
        for (char c : reverse) {
            System.out.print(c);
        }
        System.out.println();
        for(int x =0; x< word.length(); x++){
            if(word.charAt(x) != reverse[x]){
                equal = false;
                break;
            }
        }
        if(equal){
            System.out.println("This word ("+ word+") is a palindrome!");
        }
        else{
            System.out.println("It is not a palindrome word!");
        }
    }
}
