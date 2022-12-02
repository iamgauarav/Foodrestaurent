package com.example.foodrestaurent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Calendar;

public class DataBAseHelper extends SQLiteOpenHelper {


    public static final String CUST_EMAIL = "cust_email";
    public static final String CUST_PASSWORD = "cust_password";
    public static final String CUSTOMER_TABLE = "Customer";

    //-------------------food table constants below
    public static final String FOOD_TABLE = "food_table";
    public static final String FOOD_NAME = "food_name";
    public static final String FOOD_PRICE = "food_price ";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String ORDER_TABLE = "order_table";
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_DATE_TIME = "order_date_time";
    public static final String STATUS = "status";
    String createcustomertable="create table "+CUSTOMER_TABLE+"("+ CUSTOMER_ID +" INTEGER,"+ CUST_EMAIL +" text," + CUST_PASSWORD + " text"+",PRIMARY KEY("+CUSTOMER_ID+" AUTOINCREMENT))";
    String createordertable= "create table " + ORDER_TABLE + "("+CUSTOMER_ID +" INTEGER not null," + ORDER_ID + " integer not null," + ORDER_DATE_TIME + " text not null," + STATUS + " integer not null, PRIMARY KEY(order_id AUTOINCREMENT),FOREIGN KEY(customer_id) REFERENCES food_table(customer_id))";
    String createfoodtable="create table " + FOOD_TABLE + "("+FOOD_NAME +" TEXT not null," + ORDER_ID + " integer not null,"+FOOD_PRICE+" REAL,"+"PRIMARY KEY(order_id,food_name),FOREIGN KEY(order_id) REFERENCES order_table(order_id))";

    public DataBAseHelper(@Nullable Context context) {
        super(context,"restut.db", null, 1);
    }
    //------------------------------------------------food table creation below;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createcustomertable);
        db.execSQL(createordertable);
        db.execSQL(createfoodtable);
    }
    //adding customer in the table
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
    //adding order in the table
    public boolean addorder(customer cm){
        //System.out.println(createTableStatement);
        SQLiteDatabase db=this.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(CUSTOMER_ID,cm.getCustomer_id());
        Date currenttime=Calendar.getInstance().getTime();
        cv.put(ORDER_DATE_TIME,currenttime.toString());
        cv.put(STATUS,0);
        long insert=db.insert(ORDER_TABLE,null,cv);
        if(insert==-1)
            return false;
        else return true;
    }
    public ArrayList<ArrayList<String>>getallorderdetails(){
        ArrayList<ArrayList<String>>ans=new ArrayList<ArrayList<String>>();
        String queryString="select * from "+ORDER_TABLE;
        System.out.println(queryString);
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int cus_id=cursor.getInt(0);
                int order_id=cursor.getInt(1);
                String order_date=cursor.getString(2);
                int status=cursor.getInt(3);
                ArrayList<String>temp=new ArrayList<String>();
                temp.add(cus_id+"");
                temp.add(order_id+"");
                temp.add(order_date+"");
                temp.add(status+"");
                ans.add(temp);
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return ans;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public ArrayList<String>getAllcustomersemail(){
        ArrayList<String>ans=new ArrayList<String>();
        String queryString="select * from "+CUSTOMER_TABLE;
        System.out.println(queryString);
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
           do{
             String cus_email=cursor.getString(1);
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
              ans.add(new customer(cursor.getString(1),cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return ans;
    }
    public int getcustomeridbyemail(customer cm){
        SQLiteDatabase db=getReadableDatabase();
        String querystring="SELECT * from Customer where cust_email='"+cm.getEmail().toString()+"'";
        System.out.println(querystring);
        Cursor cursor=db.rawQuery(querystring,null);
        int id=2234;
        cursor.moveToFirst();
            id=cursor.getInt(0);
        return id;
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

    //add food items mapped to a particular order
    public boolean mapfooditemtoorder(customer cm){
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

}
