package com.example.foodrestaurent;

import android.graphics.Bitmap;

public class food_admin {
    String food_name;
    double food_price;
    Bitmap bm;
    public food_admin(String food_name, double food_price, Bitmap bm){
        this.food_name = food_name;
        this.food_price = food_price;
        this.bm=bm;
    }


    public String getFood_name() {
        return food_name;
    }


    public double getFood_price() {
        return food_price;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public void setFood_price(double food_price) {
        this.food_price = food_price;
    }


    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

}

