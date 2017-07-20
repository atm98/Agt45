package com.a46inch.a46inch.Classes;

/**
 * Created by ar265 on 7/20/2017.
 */

public class Products {
    private String Pname;

    private String Pdesc;
    private String Pprice;



    private String Pseller;

    private String Pcatagory;
    private String Pquant;

    public Products() {
        //.....
    }
    public Products(String pname, String pdesc, String pprice, String pseller, String pcatagory, String pquant) {
        this.Pname = pname;
        this.Pdesc = pdesc;
        this.Pprice = pprice;
        this.Pseller = pseller;
        this.Pcatagory = pcatagory;
        this.Pquant = pquant;
    }


    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPdesc() {
        return Pdesc;
    }

    public void setPdesc(String pdesc) {
        Pdesc = pdesc;
    }

    public String getPprice() {
        return Pprice;
    }

    public void setPprice(String pprice) {
        Pprice = pprice;
    }

    public String getPseller() {
        return Pseller;
    }

    public void setPseller(String pseller) {
        Pseller = pseller;
    }



    public String getPcatagory() {
        return Pcatagory;
    }

    public void setPcatagory(String pcatagory) {
        Pcatagory = pcatagory;
    }

    public String getPquant() {
        return Pquant;
    }

    public void setPquant(String pquant) {
        Pquant = pquant;
    }


}
