package com.homeworks.homework20.exeptions;

public class UserNotFaundExeption extends Exception {
    public UserNotFaundExeption(){
        super("There is no user");
    }

    public UserNotFaundExeption(Long id){
        super("There is no user with id " + id);
    }
}
