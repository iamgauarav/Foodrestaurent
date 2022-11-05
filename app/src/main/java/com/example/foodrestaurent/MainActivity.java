package com.example.foodrestaurent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b,login;
    EditText emailtext,passwordtext;
    TextView t3;
    String email;
    String password;
    DataBAseHelper dbh=new DataBAseHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailtext=findViewById(R.id.email);
        passwordtext=findViewById(R.id.pass);
        b=findViewById(R.id.login);
        t3=findViewById(R.id.reg);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                String str;
                i.getIntExtra("s",1);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(MainActivity.this,OrderActivity.class);
                startActivity(i1);
            }
        });
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=emailtext.getText().toString();
                password=passwordtext.getText().toString();
              customer cm=new customer(email,password);
              if(dbh.findcustomer(cm)==0)
                  Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
              else if(dbh.findcustomer(cm)==1)
                  Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
              else
                  Toast.makeText(MainActivity.this, "No account found in the database", Toast.LENGTH_SHORT).show();
            }
                
        });
    }
}