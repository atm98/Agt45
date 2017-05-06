package com.a46inch.a46inch;

/**
 * Created by S.H.I.E.L.D on 4/29/2017.
 */

public class Userinfo {
    public String aname;
    public String adob;
    public String aaddress;
    public String aphone_num;


    public Userinfo(){

    }

    public Userinfo(String name, String dob, String phone_num, String address) {
        this.aname = name;
        this.adob = dob;
        this.aphone_num = phone_num;
        this.aaddress = address;
    }

}
