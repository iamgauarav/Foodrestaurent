package com.example.foodrestaurent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class Myorder extends AppCompatActivity {
    ArrayList<Myordermodel> myordermodels = new ArrayList<>();
    ArrayList<ArrayList<String>> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder);
        Intent i = getIntent();
//        al = i.getExtras("key", 0);
        for (int j = 0; j < al.size(); j++) {
            myordermodels.add(new Myordermodel(al.get(j).get(0), al.get(j).get(1), al.get(j).get(2), al.get(j).get(3)));
            //
        }
        //myordermodel contains the arraylist of all the myordermodels selected by the user

    }
}
