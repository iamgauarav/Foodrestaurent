package com.example.foodrestaurent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class DataBAseHelper extends SQLiteOpenHelper {


    public static final String CUST_EMAIL = "cust_email";
    public static final String CUST_PASSWORD = "cust_password";
    public static final String CUSTOMER_TABLE = "Customer";

    //-------------------food table constants below
    public static final String FOOD_TABLE = "food_table";
    public static final String FOOD_NAME = "food_name";
    public static final String FOOD_PRICE = "food_price ";

    String createTableStatement= "create table " + CUSTOMER_TABLE + "(" + CUST_EMAIL + " text primary key, " + CUST_PASSWORD + " text)";
    public DataBAseHelper(@Nullable Context context) {
        super(context,"restut.db", null, 1);
    }
    //------------------------------------------------food table creation below;
    String createfoodtable= "create table " + FOOD_TABLE + "(" + FOOD_NAME + " text," + FOOD_PRICE + "float)";
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createTableStatement);
        db.execSQL(createfoodtable);
    }
    public boolean addcustomer(customer cm){
        //System.out.println(createTableStatement);
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(CUST_EMAIL,cm.getEmail());
        cv.put(CUST_PASSWORD,cm.getPassword());
        long insert=db.insert(CUSTOMER_TABLE,null,cv);

        if(insert==-1)
            return false;
        else return true;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public ArrayList<String>getAllcustomersemail(){
        ArrayList<String>ans=new ArrayList<String>();
        String queryString="select * from "+CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
           do{
             String cus_email=cursor.getString(0);
               ans.add(cus_email);
           }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return ans;
    }
    public ArrayList<customer>getAllcustomer(){//returns the arrraylist of customer objects present in the database
        ArrayList<customer>ans=new ArrayList<customer>();
        String queryString="select * from "+CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
              ans.add(new customer(cursor.getString(0),cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return ans;
    }
    public int findcustomer(customer cm){
//        returns 0 if customer is found but password is incorrect
//        returns 1 if customer is found in database with correct password (log the customer in)
//        return 2 if customer is not found in database
        System.out.println("hit");
   ArrayList<customer>allcustomerinfo=new ArrayList<customer>();
   String email=cm.getEmail();
   String password=cm.getPassword();
   allcustomerinfo=getAllcustomer();
   for(int i=0;i<allcustomerinfo.size();i++)
   {
     if(Objects.equals(allcustomerinfo.get(i).getEmail(), email))
     {
         if(Objects.equals(allcustomerinfo.get(i).getPassword(), password))
         {
             //log the customer in
             return 1;
         }
         return 0;
     }
   }
   return 2;
    }
}
