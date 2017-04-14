package com.bigmacdev.clinicapp;

import android.util.Base64;
import android.util.Log;

import org.jasypt.util.text.BasicTextEncryptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;


public class Client {

    String address, response;
    int port;
    PrintWriter out;
    BufferedReader in;


    Client(){
        address="10.0.0.33";
        port=8088;
    }

    public void setPort(int port){
        this.port=port;
    }

    protected String runRequest(String requestString){
        Socket socket=null;
        try {
            socket = new Socket(address, port);
            Log.d("Client", socket.isConnected()+"");
            out = new PrintWriter (new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("clinic:"+requestString);
            Log.d("Client", "Out.Write Executed.");
            response=in.readLine();
            Log.d("Client", response);
            socket.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private static String encryptionKey(){
        Long unixTime = System.currentTimeMillis()/10000000;
        System.out.println("Time: "+unixTime);
        String keyGenSeed = unixTime+"";
        String output="";
        String keyGenSeedStart=keyGenSeed;
        while (keyGenSeed.length()>0) {
            char letter = keyGenSeed.charAt(0);
            keyGenSeed=keyGenSeed.substring(1, keyGenSeed.length());
            switch (letter){
                case '1':
                    output+="a";
                    break;
                case '2':
                    output+="b";
                    break;
                case '3':
                    output+="c";
                    break;
                case '4':
                    output+="d";
                    break;
                case '5':
                    output+="e";
                    break;
                case '6':
                    output+="f";
                    break;
                case '7':
                    output+="g";
                    break;
                case '8':
                    output+="h";
                    break;
                case '9':
                    output+="j";
                    break;
                case '0':
                    output+="k";
                    break;
            }
        }
        return output+keyGenSeedStart+output;
    }

    protected String hashPassword(String password){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(password);
        return textEncryptor.encrypt(password);
    }

    protected String deHashPassword(String hashedPassword, String pword){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(pword);
        return textEncryptor.decrypt(hashedPassword);
    }

    protected String encryptData(String data){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionKey());
        return textEncryptor.encrypt(data);
    }
    protected String decryptData(String data) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionKey());
        return textEncryptor.decrypt(data);
    }
}
