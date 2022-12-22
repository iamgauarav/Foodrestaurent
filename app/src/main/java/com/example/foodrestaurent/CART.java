package com.example.foodrestaurent;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CART extends AppCompatActivity {
   //this is the cart which comes after the order is made
    RecyclerView rv;
    TextView yourorders,totalp;
    ArrayList<food> data;
    LottieAnimationView lt;
    HashMap<Integer,Integer>reciveddata;
    ArrayList<food>orderlist;
    double total=0;
    DataBAseHelper dbh=new DataBAseHelper(CART.this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        yourorders=findViewById(R.id.yourorderstext);
        rv=findViewById(R.id.rv);
        lt=findViewById(R.id.placed);
        totalp=findViewById(R.id.totalp);
        Intent i=getIntent();
        reciveddata=(HashMap<Integer, Integer>) i.getSerializableExtra("data");
        //recieved data has the details in the form of hashmap<food_id,quantity>

        String custname=i.getStringExtra("cust_name");
        int custid=i.getIntExtra("cust_id",0);
        orderlist=new ArrayList<food>();
        Cursor c=dbh.returnallfood();

        if(c.moveToFirst()){
            do{
                if((reciveddata.containsKey(c.getInt(1)))) {
//                    Toast.makeText(this, "hit", Toast.LENGTH_SHORT).show();
                    String food_name = c.getString(0);
                    int food_id = c.getInt(1);
                    double food_price = c.getInt(2);

                    byte[] bitimage = c.getBlob(3);
                    Bitmap bm = helpermethods.getImage(bitimage);
                    food temp = new food(food_name, food_price,reciveddata.get(c.getInt(1)), bm);
                    total += food_price*reciveddata.get(c.getInt(1));
                    orderlist.add(temp);
                }
//                Toast.makeText(this, c.getInt(1)+"", Toast.LENGTH_SHORT).show();
            }while(c.moveToNext());
        }
        totalp.setText("Total:"+total);
        Shader textShader = new LinearGradient(0, 0, 4, yourorders.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);

        yourorders.getPaint().setShader(textShader);
        rv.setLayoutManager(new LinearLayoutManager(this));

//        Toast.makeText(this, orderlist.size()+"", Toast.LENGTH_SHORT).show();
        order_items_in_cart_adapter adp = new order_items_in_cart_adapter(orderlist);
        rv.setAdapter(adp);
        lt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             customer cm=new customer(custname,null);
               boolean t= dbh.addorder(cm);
                Toast.makeText(CART.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                if (reciveddata.size()!=0) {
                    Intent i1 = new Intent(CART.this, OrderPlaced.class);
                    startActivity(i1);
                }
            }
        });
    }
}