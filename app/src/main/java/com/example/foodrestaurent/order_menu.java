package com.example.foodrestaurent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class order_menu extends AppCompatActivity {
    ListView lv;
    food_adapter fd;
    List<ArrayList<String>> foodlist=new ArrayList<ArrayList<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);
        lv=findViewById(R.id.lv);
        fd=new food_adapter(order_menu.this,R.layout.activity_order_menu,foodlist);//initialising the food adapter
//        //now we will add the food items name and price
        foodlist.add(new ArrayList<String>());//initialising food containers
        foodlist.add(new ArrayList<String>());
        foodlist.add(new ArrayList<String>());
        foodlist.add(new ArrayList<String>());
        foodlist.add(new ArrayList<String>());
        foodlist.add(new ArrayList<String>());
        foodlist.get(0).add("Alu Palak");
        foodlist.get(0).add("50.00");
        foodlist.get(1).add("Paneer Palak");
        foodlist.get(1).add("60.00");
        foodlist.get(2).add("Paneer Butter Masala");
        foodlist.get(2).add("70.00");
        foodlist.get(3).add("Paneer Kurma");
        foodlist.get(3).add("60.00");
        foodlist.get(4).add("Paneer Mughlai");
        foodlist.get(4).add("65.00");
        foodlist.get(5).add("Paneer Channa");
        foodlist.get(5).add("60.00");

        //-------------------------------------
        Intent i=getIntent();
        int custid=i.getIntExtra("sendcustid",-1);//custid corresponds to the id of the customer who ordered the came to the order menu

        lv.setAdapter(fd);
        //here we are giving a third parameter to every arraylist that is the order id
        for(int j=0;j<foodlist.size();j++)
        {
            foodlist.get(j).add(custid+"");
        }

    }
}