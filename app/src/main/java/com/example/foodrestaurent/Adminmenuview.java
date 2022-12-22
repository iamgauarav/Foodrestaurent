package com.example.foodrestaurent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Adminmenuview extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<food_admin> foodlist=new ArrayList<food_admin>();
    DataBAseHelper dbh=new DataBAseHelper(Adminmenuview.this);
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmenuview);
        //the below code will make the cursor size big or else the app will crash because of blob
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 10240000); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

        rv=findViewById(R.id.rv);
        //initialising the food adapter
        //initialpopulation of sendorderdetails

        //populating food menulist from food_table
        Cursor c= dbh.returnallfood();
        if(c.moveToFirst()) {//loop through the c
            do {
                String foodname=c.getString(0);
                int foodid=c.getInt(1);
                double foodprice=c.getDouble(2);
                byte[] blob=c.getBlob(3);
                Bitmap bm=helpermethods.getImage(blob);
                food_admin temp=new food_admin(foodname,foodprice,bm);
                foodlist.add(temp);
            } while (c.moveToNext());
            c.close();
        }


        //-------------------------------------
        Intent i=getIntent();
        int custid=i.getIntExtra("sendcustid",-1);//custid corresponds to the id of the customer who ordered the came to the order menu
        String custname=i.getStringExtra("sendcustemail");
        rv.setLayoutManager(new LinearLayoutManager(this));
        food_adapter_admin fd=new food_adapter_admin(foodlist);
        rv.setAdapter(fd);
            Toast.makeText(this, foodlist.size()+"", Toast.LENGTH_SHORT).show();
    }

}