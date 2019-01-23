package com.company.benzon.benzon;

public class GlobalData {

    public static Profile user;

    public static String PASSWORD = "123";
    public static String USER_PREFS = "User";
    public static String SURNAME_KEY = "Surname";
    public static String NAME_KEY = "Name";
    public static String NUMBER_KEY = "Number";

    private static boolean logged;

    public static boolean isLogged(){
        if(user == null){
            return false;
        }
        else {
            return true;
        }
    }

}
