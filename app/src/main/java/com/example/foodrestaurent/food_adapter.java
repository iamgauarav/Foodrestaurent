package com.example.foodrestaurent;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class food_adapter extends ArrayAdapter<food> {
    int number;
    //list<List<string>list<string>list<string>..>
    //inner list has two string items items and price
    List<food>al;
    int order_id;
    public food_adapter(@NonNull Context context, int resource, @NonNull List<food> al) {
        super(context, resource, al);
        this.al=al;
    }

    @Nullable
    @Override
    public food getItem(int position) {
        return al.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=LayoutInflater.from(getContext()).inflate(R.layout.layout_order_menu_new,parent,false);
        TextView price=convertView.findViewById(R.id.foodprice);
        TextView name=convertView.findViewById(R.id.foodname);

        TextView counter=convertView.findViewById(R.id.counter);
        ImageView plus=convertView.findViewById(R.id.plus);
        ImageView minus=convertView.findViewById(R.id.minus);
        CircleImageView im=convertView.findViewById(R.id.imageView3);
//        int resourceId = getContext().getResources().
//                getIdentifier( "i"+position, "drawable", getContext().getPackageName());
//        Bitmap bm=BitmapFactory.decodeResource(getContext().getResources(),resourceId,10);
//        String str=helpermethods.bitmaptostring(bm);
//        bm=helpermethods.StringToBitMap(str);
        Bitmap bm=getItem(position).getBm();
        im.setImageBitmap(bm);
        String n=getItem(position).getFood_name();
        name.setText(n);
        String p=getItem(position).getFood_price()+"";
        price.setText("INR "+p);
        counter.setText(al.get(position).getQuantity()+"");
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=Integer.parseInt(counter.getText().toString());
                number++;
                counter.setText(number+"");
                al.get(position).setQuantity(number);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number=Integer.parseInt(counter.getText().toString());
                if(number!=0)
                number--;
                counter.setText(number+"");
                al.get(position).setQuantity(number);
            }
        });
        return convertView;
    }
    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

}
