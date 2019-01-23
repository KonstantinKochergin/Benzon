package com.company.benzon.benzon;

public class Profile {

    private String name;
    private String surname;
    private String phoneNumber;


    public Profile(String name, String surname, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
