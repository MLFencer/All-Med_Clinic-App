package com.bigmacdev.clinicapp;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;
import net.maritimecloud.internal.core.javax.json.JsonObjectBuilder;

import java.io.Serializable;
import java.util.ArrayList;


public class Patient extends Person implements Serializable{
    private static final long serialVersionUID = 2L;


    //---Temporary Variables-------
    private Boolean done = null;
    private String scripts = "";
    private Client client;

    private ArrayList<Perscription> perscriptions = new ArrayList<Perscription>();
    private ArrayList<String> eyeProblems = new ArrayList<String>();
    private ArrayList<String> cancers = new ArrayList<String>();
    private ArrayList<String> stds = new ArrayList<String>();
    private ArrayList<String> others = new ArrayList<String>();
    private ArrayList<Practice> approvedPractices = new ArrayList<Practice>();
    private ArrayList<String>changes=new ArrayList<String>();
    private String path="";
    private String changedBy="";
    private String gender="";
    private String username="";
    private String password="";
    private String homeNumber="";
    private String cellNumber="";
    private String address="";
    private String city="";
    private String state="";
    private String zip="";
    private String ssn="";
    private String email="";
    private String emergencyContact="";
    private String emergencyContactRelationship="";
    private String eCHomeNumber="";
    private String eCCellNumber="";
    private boolean diabetes = false;
    private boolean kidneyDisease = false;
    private boolean stroke = false;
    private boolean tuberculosis = false;
    private boolean arrythmia = false;
    private boolean highBloodPressure = false;
    private boolean hepatitis = false;
    private boolean depression = false;
    private boolean coronaryArteryDisease = false;
    private boolean asthma = false;
    private boolean thyroidDisease = false;
    private boolean emphasyma = false;
    private boolean congestiveHeartFailure = false;
    private boolean heartAttack = false;
    private boolean seizures = false;
    private boolean eyeProblem = false;
    private boolean std = false;
    private boolean cancer = false;
    private boolean other = false;

    public ArrayList<Perscription> getPerscriptions() {return perscriptions;}

    public ArrayList<String> getEyeProblems(){
        return eyeProblems;
    }

    public ArrayList<String> getCancers(){
        return cancers;
    }

    public ArrayList<String> getStds(){
        return stds;
    }

    public ArrayList<String> getOthers(){
        return others;
    }

    public void addEyeProblems(String problem){
        eyeProblems.add(problem);
    }

    public void addCancers(String type){
        cancers.add(type);
    }

    public void addStds(String type){
        stds.add(type);
    }

    public void addOthers(String thing){
        others.add(thing);
    }

    public void setOthers(ArrayList<String> a){
        this.others=a;
    }

    public void setEyeProblems(ArrayList<String> a){
        this.eyeProblems=a;
    }

    public void setCancers(ArrayList<String> a){
        this.cancers=a;
    }

    public void setStds(ArrayList<String> a){
        this.stds=a;
    }

    public void setChanges(ArrayList<String> a){
        this.changes=a;
    }


    public boolean isDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean isKidneyDisease() {
        return kidneyDisease;
    }

    public void setKidneyDisease(boolean kidneyDisease) {
        this.kidneyDisease = kidneyDisease;
    }

    public boolean isStroke() {
        return stroke;
    }

    public void setStroke(boolean stroke) {
        this.stroke = stroke;
    }

    public boolean isTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public boolean isArrythmia() {
        return arrythmia;
    }

    public void setArrythmia(boolean arrythmia) {
        this.arrythmia = arrythmia;
    }

    public boolean isHighBloodPressure() {
        return highBloodPressure;
    }

    public void setHighBloodPressure(boolean highBloodPressure) {
        this.highBloodPressure = highBloodPressure;
    }

    public boolean isHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(boolean hepatitis) {
        this.hepatitis = hepatitis;
    }

    public boolean isDepression() {
        return depression;
    }

    public void setDepression(boolean depression) {
        this.depression = depression;
    }

    public boolean isCoronaryArteryDisease() {
        return coronaryArteryDisease;
    }

    public void setCoronaryArteryDisease(boolean coronaryArteryDisease) {
        this.coronaryArteryDisease = coronaryArteryDisease;
    }

    public boolean isAsthma() {
        return asthma;
    }

    public void setAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public boolean isThyroidDisease() {
        return thyroidDisease;
    }

    public void setThyroidDisease(boolean thyroidDisease) {
        this.thyroidDisease = thyroidDisease;
    }

    public boolean isEmphasyma() {
        return emphasyma;
    }

    public void setEmphasyma(boolean emphasyma) {
        this.emphasyma = emphasyma;
    }

    public boolean isCongestiveHeartFailure() {
        return congestiveHeartFailure;
    }

    public void setCongestiveHeartFailure(boolean congestiveHeartFailure) {
        this.congestiveHeartFailure = congestiveHeartFailure;
    }

    public boolean isHeartAttack() {
        return heartAttack;
    }

    public void setHeartAttack(boolean heartAttack) {
        this.heartAttack = heartAttack;
    }

    public boolean isSeizures() {
        return seizures;
    }

    public void setSeizures(boolean seizures) {
        this.seizures = seizures;
    }

    public boolean isEyeProblem() {
        return eyeProblem;
    }

    public void setEyeProblem(boolean eyeProblem) {
        this.eyeProblem = eyeProblem;
    }

    public boolean isStd() {
        return std;
    }

    public void setStd(boolean std) {
        this.std = std;
    }

    public boolean isCancer() {
        return cancer;
    }

    public void setCancer(boolean cancer) {
        this.cancer = cancer;
    }

    public boolean isOther() {
        return other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }

    //------Getters-----------
    public String getPath(){return path;}
    public String getUsername(){return username;}
    public String getGender(){return gender;}
    public String getPassword(){return password;}
    public String getHomeNumber(){return homeNumber;}
    public String getCellNumber(){return cellNumber;}
    public String getAddress() {return address;}
    public String getCity(){return city;}
    public String getState(){return state;}
    public String getZip() {return zip;}
    public String getShortSsn() {return ssn.substring(ssn.length()-4,ssn.length());}
    public String getEmail(){return email;}
    public String getEmergencyContact(){return emergencyContact;}
    public String getEmergencyContactRelationship(){return emergencyContactRelationship;}
    public String geteCHomeNumber(){return eCHomeNumber;}
    public String geteCCellNumber(){return eCHomeNumber;}
    public String getFullName(){return fName+" "+lName;}
    public String getChangedBy(){return changedBy;}
    //------------------------------

    //------Setters--------------
    public void setPath(String s){path=s;}
    public void setUsername(String s){username=s;}
    public void setGender(String s){gender=s;}
    public void setPassword(String password){this.password=password;}
    public void setHomeNumber(String s){homeNumber=s;}
    public void setCellNumber(String s){cellNumber=s;}
    public void setAddress(String s){address=s;}
    public void setCity(String s){city=s;}
    public void setState(String s){state=s;}
    public void setZip(String s){zip=s;}
    public void setSsn(String s){ssn=s;}
    public void setEmail(String s){email=s;}
    public void setEmergencyContact(String s){emergencyContact=s;}
    public void setEmergencyContactRelationship(String s){emergencyContactRelationship=s;}
    public void seteCHomeNumber(String s){eCHomeNumber=s;}
    public void seteCCellNumber(String s){eCCellNumber=s;}
    public void setChangedBy(String s){this.changedBy=s;}
    //----------------------------------

    //-----------get perscriptions-----------
    public void loadPerscriptions(){
        done = null;
        client = new Client();
        new Thread(){
            @Override
            public void run() {
                scripts = client.runRequest("scripts:"+client.encryptData(path));
                done=true;
            }
        }.start();
        while (done==null){}
        scripts = client.decryptData(scripts);
        //Todo: convert string to json and get data from json

    }


    //---------Remove Changes-------
    public void clearChanges(){
        changedBy="";
        changes.clear();
    }

    //-----Changes-------------
    public void addChange(String change){
        changes.add(change);
    }


    //---Constructors------------------------------------------------------------
    public Patient(){}
    public Patient(Person p){
        super(p.getfName(),p.getlName(),p.getDobY(),p.getDobM(),p.getDobD());
    }
    public Patient(String f, String l, int y, int m, int d){
        super(f,l,y,m,d);
    }

    //-------Create Patient from Json---------
    public void loadData(JsonObject jo){
        this.password=jo.getString("password");
        this.username=jo.getString("username");
        this.path=jo.getString("path");
        JsonObject personalInfo = jo.getJsonObject("personal_info");
        JsonObject name = personalInfo.getJsonObject("name");
        this.fName=name.getString("first");
        if(name.containsKey("middle")){
            this.mName=name.getString("middle");
        }
        this.lName=name.getString("last");
        JsonObject dob = personalInfo.getJsonObject("dob");
        this.dobD=dob.getInt("day");
        this.dobM=dob.getInt("month");
        this.dobY=dob.getInt("year");
        this.ssn=jo.getString("ssn");
        JsonObject con = jo.getJsonObject("contact");
        this.homeNumber=con.getString("home");
        this.cellNumber=con.getString("cell");
        this.email=con.getString("email");
        this.address=con.getString("address");
        this.city=con.getString("city");
        this.state=con.getString("state");
        this.zip=con.getString("zip");
        JsonObject contact = jo.getJsonObject("emergency");
        this.emergencyContact=contact.getString("contact");
        this.emergencyContactRelationship=contact.getString("relationship");
        this.eCCellNumber=contact.getString("cell");
        this.eCHomeNumber=contact.getString("home");
        if(jo.containsKey("conditions")){
            JsonObject condit = jo.getJsonObject("conditions");
            this.diabetes=condit.getBoolean("diabetes");
            this.kidneyDisease=condit.getBoolean("kidneyDisease");
            this.stroke=condit.getBoolean("stroke");
            this.tuberculosis=condit.getBoolean("tuberculosis");
            this.arrythmia=condit.getBoolean("arrythmia");
            this.highBloodPressure=condit.getBoolean("highBloodPressure");
            this.hepatitis=condit.getBoolean("hepatitis");
            this.depression=condit.getBoolean("depression");
            this.coronaryArteryDisease=condit.getBoolean("coronaryArteryDisease");
            this.asthma=condit.getBoolean("asthma");
            this.thyroidDisease=condit.getBoolean("thyroidDisease");
            this.emphasyma=condit.getBoolean("emphasyma");
            this.congestiveHeartFailure=condit.getBoolean("congestiveHeartFailure");
            this.seizures=condit.getBoolean("seizures");
            this.eyeProblem=condit.getBoolean("eyeProblem");
            this.std=condit.getBoolean("std");
            this.cancer=condit.getBoolean("cancer");
            this.other=condit.getBoolean("other");
            if(eyeProblem){
                JsonObject eye = condit.getJsonObject("eyeProblems");
                int i=0;
                while (true){
                    if(eye.containsKey("eyeProblem"+i)){
                        eyeProblems.add(eye.getString("eyeProblem"+i));
                    }else{
                        break;
                    }
                    i++;
                }
            }
            if(std){
                JsonObject sts = condit.getJsonObject("stds");
                int i=0;
                while (true){
                    if(sts.containsKey("std"+i)){
                        stds.add(sts.getString("std"+i));
                    }else{
                        break;
                    }
                    i++;
                }
            }
            if(cancer){
                JsonObject can = condit.getJsonObject("cancers");
                int i=0;
                while (true){
                    if(can.containsKey("cancer"+i)){
                        cancers.add(can.getString("cancer"+i));
                    }else{
                        break;
                    }
                    i++;
                }
            }
            if(other){
                JsonObject oth = condit.getJsonObject("others");
                int i=0;
                while (true){
                    if(oth.containsKey("other"+i)){
                        others.add(oth.getString("other"+i));
                    }else{
                        break;
                    }
                    i++;
                }
            }
        }
    }
    //-----------------------------------------------------------------------------

    //-------Create Json------
    public String toJsonString(){
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonObjectBuilder job2 = Json.createObjectBuilder();
        JsonObjectBuilder job3 = Json.createObjectBuilder();
        JsonObjectBuilder job4 = Json.createObjectBuilder();
        job.add("username",username)
           .add("password",password)
            .add("path", path)
            .add("personal_info", Json.createObjectBuilder()
                .add("name", Json.createObjectBuilder()
                            .add("first", fName)
                            .add("middle", mName)
                            .add("last", lName)
                )
                .add("dob", Json.createObjectBuilder()
                            .add("day",dobD)
                            .add("month",dobM)
                            .add("year", dobY)
                )

            )
            .add("ssn",ssn)
            .add("contact", Json.createObjectBuilder()
                .add("home",homeNumber)
                .add("cell",cellNumber)
                .add("email",email)
                .add("address",address)
                .add("city",city)
                .add("state",state)
                .add("zip",zip)
            )
            .add("emergency", Json.createObjectBuilder()
                .add("contact",emergencyContact)
                .add("relationship",emergencyContactRelationship)
                .add("home",eCHomeNumber)
                .add("cell",eCCellNumber)
            );
        try {
            if (!changedBy.equals("")) {
                job2.add("updatedBy", changedBy);
                for (int i = 0; i < changes.size(); i++) {
                    job3.add("field" + i, changes.get(i));
                }
                job2.add("fieldsChanged", job3);
                job.add("changes", job2);
            }
        }catch(Exception e){}
        job4.add("diabetes", diabetes);
        job4.add("kidneyDisease", kidneyDisease);
        job4.add("stroke", stroke);
        job4.add("tuberculosis", tuberculosis);
        job4.add("arrythmia", arrythmia);
        job4.add("highBloodPressure", highBloodPressure);
        job4.add("hepatitis", hepatitis);
        job4.add("depression", depression);
        job4.add("coronaryArteryDisease", coronaryArteryDisease);
        job4.add("asthma", asthma);
        job4.add("thyroidDisease", thyroidDisease);
        job4.add("emphasyma", emphasyma);
        job4.add("congestiveHeartFailure", congestiveHeartFailure);
        job4.add("seizures", seizures);
        job4.add("eyeProblem", eyeProblem);
        job4.add("std", std);
        job4.add("cancer", cancer);
        job4.add("other", other);

        if(eyeProblem){
            JsonObjectBuilder job5 = Json.createObjectBuilder();
            for(int i=0; i<eyeProblems.size(); i++){
                job5.add("eyeProblem"+i, eyeProblems.get(i));
            }
            job4.add("eyeProblems",job5);
        }
        if(std){
            JsonObjectBuilder job5 = Json.createObjectBuilder();
            for(int i=0; i<stds.size(); i++){
                job5.add("std"+i, stds.get(i));
            }
            job4.add("stds",job5);
        }
        if(cancer){
            JsonObjectBuilder job5 = Json.createObjectBuilder();
            for(int i=0; i<cancers.size(); i++){
                job5.add("cancer"+i, cancers.get(i));
            }
            job4.add("cancers",job5);
        }
        if(other){
            JsonObjectBuilder job5 = Json.createObjectBuilder();
            for(int i=0; i<others.size(); i++){
                job5.add("other"+i, others.get(i));
            }
            job4.add("others",job5);
        }
        job.add("conditions",job4);



        JsonObject jo;
        jo= job.build();
        return jo.toString();
    }
    //------------------------------

}
