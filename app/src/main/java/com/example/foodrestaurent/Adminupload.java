package com.example.foodrestaurent;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adminupload extends AppCompatActivity {
 private CircleImageView foodimage;
 private EditText food_name,food_price;
 ConstraintLayout cl;
 Uri imageUri;
 Button upload,upload2;
    DataBAseHelper dbh=new DataBAseHelper(Adminupload.this);
    private static final int PICK_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminupload);
        upload2=findViewById(R.id.delete);
        foodimage=findViewById(R.id.profile);
        upload=findViewById(R.id.upload);
        cl=findViewById(R.id.cl);
        food_name=findViewById(R.id.foodnameadmin);
        food_price=findViewById(R.id.foodpriceadmin);
        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Adminupload.this,Adminmenuview.class);
                startActivity(i);
            }
        });
        foodimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"select Picture"),PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imageUri=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                foodimage.setImageBitmap(bitmap);
                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if((food_name.getText().toString()).isEmpty()|| (food_price.getText().toString()).isEmpty())
                            Toast.makeText(Adminupload.this, "Please enter details", Toast.LENGTH_SHORT).show();
                        else
                        {
                            byte[] bit = helpermethods.getBytes(bitmap);
                            long p = dbh.insertfood(food_name.getText().toString(), Double.parseDouble(food_price.getText().toString()), bit);
                            if(p!=-1)
                                Toast.makeText(Adminupload.this, "insert successfully", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(Adminupload.this, "insertion failed", Toast.LENGTH_SHORT).show();
                            food_name.setText("");
                            food_price.setText("");
                            foodimage.setImageResource(R.drawable.egg);
                        }
                    }

                });
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}