package com.example.foodrestaurent;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
     ImageButton ib;
     EditText email,password,retypepass;
     Button register;
    DataBAseHelper dbh=new DataBAseHelper(SecondActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ib=findViewById(R.id.imageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        retypepass=findViewById(R.id.retypepassword);
        register=findViewById(R.id.registerbutton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getemail,getpass,getrepass;
                getemail=email.getText().toString();
                getpass=password.getText().toString();
                getrepass=retypepass.getText().toString();
                System.out.println(getpass+" "+getrepass);
                if(getemail.isEmpty() || getpass.isEmpty()|| getrepass.isEmpty()){
                    Toast.makeText(SecondActivity.this, "Please give valid credentials", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!getpass.equals(getrepass))
                    {
//                        System.out.println(getpass+" "+getrepass);
                        Toast.makeText(SecondActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        customer cm=new customer(getemail,getpass);
                  boolean success= dbh.addcustomer(cm);
                  if(success)
                      Toast.makeText(SecondActivity.this, "Successfully Created Account", Toast.LENGTH_SHORT).show();
                  else
                      Toast.makeText(SecondActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}