package com.example.foodrestaurent;

public class Myordermodel {
    String foodname;
    String quantity;
    String price;
    String image;

    public Myordermodel(String foodname, String price, String quantity, String image) {
        this.foodname = foodname;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
