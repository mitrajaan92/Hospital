package com.jdbcHospital.exceptions;

public class IdNotFoundException extends  RuntimeException{
    public IdNotFoundException(int  id) {
        super("Id " + id + " not found");
    }
}



