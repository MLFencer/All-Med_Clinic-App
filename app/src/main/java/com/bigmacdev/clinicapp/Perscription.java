package com.bigmacdev.clinicapp;

import net.maritimecloud.internal.core.javax.json.JsonObject;

public class Perscription {
    private static final long serialVersionUID = 454690L;
    private String patientLastName, patientFirstName, drugId, perscriberName, scriptWrittenDate, scriptNumber, quantity;
    private int timeDue;

    public Perscription(){}

    public Perscription(String patientLastName, String patientFirstName, String drugId, String perscriberName, String scriptWrittenDate, String scriptNumber, String quantity){
        this.drugId=drugId;
        this.patientLastName=patientLastName;
        this.patientFirstName=patientFirstName;
        this.perscriberName=perscriberName;
        this.quantity=quantity;
        this.scriptWrittenDate=scriptWrittenDate;
        this.scriptNumber=scriptNumber;

    }

    public void loadPerscription(JsonObject jo){
        this.drugId = jo.getString("drugId");
        this.perscriberName = jo.getString("perscriber");
        this.quantity = jo.getString("quantity");
        this.scriptNumber = jo.getString("scriptNumber");
        this.scriptWrittenDate = jo.getString("writtenDate");
        this.patientFirstName = jo.getString("fname");
        this.patientLastName = jo.getString("lname");
    }

    public void setTimeDue(int minutes){
        timeDue = minutes;
    }

    public String getTimeDue(){
        return String.valueOf(timeDue);
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getDrugId() {
        return drugId;
    }

    public String getPerscriberName() {
        return perscriberName;
    }

    public String getScriptWrittenDate() {
        return scriptWrittenDate;
    }


    public String getScriptNumber() {
        return scriptNumber;
    }


    public String getQuantity() {
        return quantity;
    }


}
