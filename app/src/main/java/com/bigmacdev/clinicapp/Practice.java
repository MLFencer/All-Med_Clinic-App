package com.bigmacdev.clinicapp;
import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;
import net.maritimecloud.internal.core.javax.json.JsonObjectBuilder;

import java.io.File;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Practice {
	String name, address, city, state, manager, path, zip, phone;
	int rooms;
	ArrayList<String> staff = new ArrayList<String>();

	public Practice(String name, String street, String city, String state, String zip, String phone, String manager, int rooms){
		this.address=street;
		this.city=city;
		this.name=name;
		this.manager=manager;
		this.state=state;
		this.zip=zip;
		this.phone=phone;
		this.rooms=rooms;
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmm");
		Date date = new Date();
		this.path="storage/data/clinic/"+name+"_"+manager+"_"+dateFormat.format(date);
		new File(path+"/records").mkdirs();
	}

	public Practice(){}

	public void addStaff(String username){staff.add(username);}

	public String getPath(){return path;}

	public JsonObject createJson(){
		JsonObjectBuilder job = Json.createObjectBuilder();
		JsonObjectBuilder job2 = Json.createObjectBuilder();
		JsonObjectBuilder job3 = Json.createObjectBuilder();
		job.add("name", name);
		job.add("phone", phone);
		job.add("path", path);
		job.add("manager", manager);
		job.add("rooms", rooms);
		job3.add("street", address);
		job3.add("city", city);
		job3.add("state", state);
		job3.add("zip", zip);
		job.add("location", job3);
		if(staff.size()>0){
			for (int i=0; i<staff.size(); i++){
				job2.add("staff"+i, staff.get(i));
			}
			job.add("staff", job2);
		}
		JsonObject jo = job.build();
		return jo;
	}

	public String jsonToString(JsonObject jo){return jo.toString();}

	public void loadData(String s){
		JsonObject jo = Json.createReader(new StringReader(s)).readObject();
		this.name = jo.getString("name");
		this.phone=jo.getString("phone");
		this.manager=jo.getString("manager");
		this.rooms = jo.getInt("rooms");
		this.path = jo.getString("path");
		JsonObject loc = jo.getJsonObject("location");
		this.address=loc.getString("street");
		this.city=loc.getString("city");
		this.state=loc.getString("state");
		this.zip=loc.getString("zip");
		if(jo.containsKey("staff")){
			int i=0;
			JsonObject sta = jo.getJsonObject("staff");
			while(true){
				if(sta.containsKey("staff"+i)){
					staff.add(sta.getString("staff"+i));
				}else{
					break;
				}
			}
		}
	}
}
