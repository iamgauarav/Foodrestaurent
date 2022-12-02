package com.example.foodrestaurent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class food_adapter extends ArrayAdapter<ArrayList<String>> {
    //list<List<string>list<string>list<string>..>
    //inner list has two string items items and price
    List<ArrayList<String>>al;
    int order_id;
    public food_adapter(@NonNull Context context, int resource, @NonNull List<ArrayList<String>> al) {
        super(context, resource, al);
        this.al=al;
    }

    @Nullable
    @Override
    public ArrayList<String> getItem(int position) {
        return al.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(getContext()).inflate(R.layout.foodlayout,parent,false);
        TextView price=convertView.findViewById(R.id.price);
        TextView name=convertView.findViewById(R.id.name);
        String n=getItem(position).get(0);
        name.setText(n);
        String p=getItem(position).get(1);
        int order_id=Integer.parseInt(getItem(position).get(2));
        price.setText(p);
        ImageButton ib=convertView.findViewById(R.id.imageButton5);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), order_id+" was clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
