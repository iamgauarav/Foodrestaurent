package com.example.foodrestaurent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderPlaced extends AppCompatActivity {
    ImageView imageView;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        imageView=findViewById(R.id.imageView2);
        imageView.setImageResource(R.drawable.deliveringparcels);
        tv=findViewById(R.id.myorder);
    }
}