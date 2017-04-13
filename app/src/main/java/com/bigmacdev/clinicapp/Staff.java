package com.bigmacdev.clinicapp;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;
import net.maritimecloud.internal.core.javax.json.JsonObjectBuilder;

import java.io.File;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;

public class Staff implements Serializable{
    private static final long serialVersionUID = 354652L;

    private String last, first, username, password, title, email, path;
    private ArrayList<String> pharmacies = new ArrayList<String>();
    private ArrayList<String> clinics = new ArrayList<String>();

    public Staff(){}

    public Staff(String first, String last, String username, String password, String email){
        this.last=last;
        this.first=first;
        this.username=username;
        this.password=password;
        this.email=email;
        path = "storage/login/staff/"+username;
        new File(path).mkdirs();
    }

    public String getPassword(){
        return password;
    }

    public String getLocation(int type, int index){
        if(type==1){
            return pharmacies.get(index);
        }else if(type==2){
            return clinics.get(index);
        }
        return "";
    }

    public void addLocation(String path, int type){
        if(type==1){
            pharmacies.add(path);
        } else if (type==2){
            clinics.add(path);
        }
    }

    public void loadData(String data){
        JsonObject jo = Json.createReader(new StringReader(data)).readObject();
        this.username=jo.getString("username");
        this.password=jo.getString("password");
        this.email=jo.getString("email");
        this.first=jo.getString("first");
        this.last=jo.getString("last");
        if (jo.containsKey("clinics")){
            JsonObject cl = jo.getJsonObject("clinics");
            int i = 0;
            while(true){
                if(cl.containsKey("location"+i)){
                    clinics.add(cl.getString("location"+i));
                }else{
                    break;
                }
                i++;
            }
        }
        if (jo.containsKey("pharmacies")){
            JsonObject pharm = jo.getJsonObject("pharmacies");
            int i = 0;
            while(true){
                if(pharm.containsKey("location"+i)){
                    pharmacies.add(pharm.getString("location"+i));
                }else{
                    break;
                }
                i++;
            }
        }
    }

    public JsonObject createJson(){
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonObjectBuilder job2 = Json.createObjectBuilder();
        JsonObjectBuilder job3 = Json.createObjectBuilder();
        job.add("username", username);
        job.add("password", password);
        job.add("first", first);
        job.add("last", last);
        job.add("email", email);
        if(pharmacies.size()>0){
            for (int i=0; i<pharmacies.size(); i++){
                job2.add("location"+i, pharmacies.get(i));
            }
            job.add("pharmacies", job2);
        }
        if(clinics.size()>0){
            for (int i=0; i<clinics.size(); i++){
                job3.add("location"+i, clinics.get(i));
            }
            job.add("clinics", job3);
        }
        JsonObject jo = job.build();
        return jo;
    }

    public String jsonToString(JsonObject jo){
        return jo.toString();
    }
}
