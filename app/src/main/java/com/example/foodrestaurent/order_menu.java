package com.example.foodrestaurent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class order_menu extends AppCompatActivity {
    ListView lv;
    food_adapter fd;
    ArrayList<food> foodlist=new ArrayList<food>();
    TextView welcome,name,price,counter,textView;
    LottieAnimationView cartbutton;
    HashMap<Integer,Integer>idandquantity;//first will be the food id and the second will be the food quantity
    DataBAseHelper dbh=new DataBAseHelper(order_menu.this);
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //the below code will make the cursor size big or else the app will crash because of blob
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 10240000); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_menu);
        lv=findViewById(R.id.lv);
        textView=findViewById(R.id.textView);
       cartbutton=findViewById(R.id.gotocart);
        fd=new food_adapter(order_menu.this,R.layout.activity_order_menu,foodlist);//initialising the food adapter
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
               food temp=new food(foodname,foodprice,0,bm);
               foodlist.add(temp);
            } while (c.moveToNext());
            //close both the c and the database
            c.close();
        }


        //-------------------------------------
        welcome=findViewById(R.id.welcometext);
        Intent i=getIntent();
        int custid=i.getIntExtra("sendcustid",-1);//custid corresponds to the id of the customer who ordered the came to the order menu
        String custname=i.getStringExtra("sendcustemail");
        welcome.setText("'Hi, "+custname);
        lv.setAdapter(fd);
        cartbutton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(order_menu.this, "GO TO YOUR CART", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        cartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idandquantity=new HashMap<Integer,Integer>();
                for(int j=0;j<fd.getCount();j++) {
                     name=fd.getViewByPosition(j,lv).findViewById(R.id.foodname);
                    String foodname=name.getText().toString();
                     price=fd.getViewByPosition(j,lv).findViewById(R.id.price);
                     counter=fd.getViewByPosition(j,lv).findViewById(R.id.counter);
                    int quantity=Integer.parseInt(counter.getText().toString());
                    if(quantity!=0) {
                        int id=dbh.returnfoodidbyfoodname(foodname);
//                        Toast.makeText(order_menu.this, id+"", Toast.LENGTH_SHORT).show();
                        idandquantity.put(id,quantity);
                    }
                }
                Intent i=new Intent(order_menu.this,CART.class);
                i.putExtra("cust_name",custname);
                i.putExtra("cust_id",custid);
                i.putExtra("data",idandquantity);
              startActivity(i);
            }
        });

        Shader textShader = new LinearGradient(0, 0, 4, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.REPEAT);

        textView.getPaint().setShader(textShader);
    }
    public class Load extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }
    }
}