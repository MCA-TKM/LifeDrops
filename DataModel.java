package com.example.faris.lifedrops;

/**
 * Created by Salman Faris on 02-12-2018.
 */

public class DataModel {

    String name;
    String mail;
    String age;
    String phone;

    public DataModel(String name, String mail, String age, String phone ) {
        this.name=name;
        this.mail=mail;
        this.age=age;
        this.phone=phone;

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return mail;
    }

    public String getVersion_number() {
        return age;
    }

    public String getFeature() {
        return phone;
    }

}

