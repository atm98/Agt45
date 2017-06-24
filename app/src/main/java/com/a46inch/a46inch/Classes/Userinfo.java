package com.a46inch.a46inch.Classes;

import java.util.List;

/**
 * Created by S.H.I.E.L.D on 4/29/2017.
 */

public class Userinfo {
    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAdob() {
        return adob;
    }

    public void setAdob(String adob) {
        this.adob = adob;
    }

    public String getAaddress() {
        return aaddress;
    }

    public void setAaddress(String aaddress) {
        this.aaddress = aaddress;
    }

    public String getAphone_num() {
        return aphone_num;
    }

    public void setAphone_num(String aphone_num) {
        this.aphone_num = aphone_num;
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail;
    }


    public String aname; //name of the user
    public String adob; //date of birth of user
    public String aaddress; //phone num of user
    public String aphone_num; //address of user
    public String aemail;
    public List<String> awishlist;
    public Userinfo(){
			//empty constructor
    }

    public Userinfo(String name, String dob, String phone_num, String address,String email,List<String> wishlist) {
        this.aname = name; 
        this.adob = dob; 
        this.aphone_num = phone_num;
        this.aaddress = address;
        this.aemail = email;
        this.awishlist = wishlist;
    } //Constructor for setting the data into the object

}
