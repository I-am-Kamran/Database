package com.example.marvin.database;

/**
 * Created by MARVIN on 20-02-2018.
 */

/* Its is called POJO class it is used for setter and getter*/
public class Product
{
    int id;
    String name,price;

    public  Product()
    {

    }


    public Product(int id, String name, String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
