package com.example.meesh.labsession7;

/**
 * Created by meesh on 2/22/2018.
 */

public class DataModel {
    public  String name;
    public String email;
    public String phone;

    DataModel(String name, String email,String phone){
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
