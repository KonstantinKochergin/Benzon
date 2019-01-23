package com.company.benzon.benzon;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class InteractionWithServer {

    public static String[] getUser(String number) throws Exception {
        String response = hey("whois " + number);
        String[] FIO = new String[2];
        int x = 0;
        for (String retval : response.split(" ", 2)) {
            FIO[x] = retval;
            x++;
        }
        return FIO;
    }

    public static String[] logIn(String number, String pass)throws Exception {
        String response = hey("login " + number + " " + pass);
        String[] FIO = new String[2];
        int x = 0;
        for (String retval : response.split(" ", 2)) {
            FIO[x] = retval;
            x++;
        }
        if (FIO[0].equals("Incorrect") && FIO[1].equals("username/password")) return null;
        else if (FIO[0].equals("Ошибка") && FIO[1].equals("в запросе, для получение справки напишите help")) return null;
        return FIO;
    }

    public static boolean newUser (String number , String pass, String surname, String name) throws Exception
    {
        if(hey("remember " + number + " " + pass + " " + surname + " " + name).equals("User added sucsessfully!")) return true;
        else return false;
    }

    public static String hey (String message)throws Exception
    {
        try ( Socket s = new Socket("shit.ntaddv.space", 30777) ) {
            int c;

            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();

            byte buf[] = message.getBytes("UTF-16LE");

            out.write(buf);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while((c = in.read()) != -1) {
                baos.write( c );
            }
            return (baos.toString("UTF-16LE"));
        }
    }
}
