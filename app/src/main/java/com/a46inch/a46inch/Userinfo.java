package com.a46inch.a46inch;

/**
 * Created by S.H.I.E.L.D on 4/29/2017.
 */

public class Userinfo {
    public String aname; //name of the user 
    public String adob; //date of birth of user
    public String aaddress; //phone num of user
    public String aphone_num; //address of user


    public Userinfo(){
			//empty constructor
    }

    public Userinfo(String name, String dob, String phone_num, String address) {
        this.aname = name; 
        this.adob = dob; 
        this.aphone_num = phone_num;
        this.aaddress = address;
    } //Constructor for setting the data into the object

}
