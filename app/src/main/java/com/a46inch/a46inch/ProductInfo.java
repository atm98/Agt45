package com.a46inch.a46inch;

/**
 * Created by S.H.I.E.L.D on 4/30/2017.
 */

public class ProductInfo {
    private String name;
    private String image;
    private Integer price;
    private String description;
    private String seller;
    private String id;
    private String category;
    private Integer quantity;
    public ProductInfo(){
    }

    public ProductInfo(String name, String image, Integer price){
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public ProductInfo(String name, Integer price, String description, String seller,String id){
        this.name = name;
        this.id = id;
        this.price = price;
        this.description = description;
        this.seller = seller;
    }

    public ProductInfo(String name, String image, Integer price, String description, String seller, String id, String category){
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.seller = seller;
        this.id = id;
        this.category = category;
    }
    public ProductInfo(String name, String image, Integer price, String description, String seller, String id, Integer quantity){
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.seller = seller;
        this.id = id;
        this.quantity = quantity;
    }
    public String getName(){
        return name;
    }
    public String getImage(){
        return image;
    }
    public String getDescription(){
        return description;
    }
    public Integer getprice(){
        return price;
    }
    public Integer getQuantity(){
        return quantity;
    }
    public String getSeller(){
        return seller;
    }
    public String getId(){
        return id;
    }
    public String getCategory(){
        return category;
    }
}
