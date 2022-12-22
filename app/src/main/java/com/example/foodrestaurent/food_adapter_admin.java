package com.example.foodrestaurent;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class food_adapter_admin  extends RecyclerView.Adapter<food_adapter_admin.ViewHolder> {
    private static ArrayList<food_admin> localdataset;
    DataBAseHelper dbh;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView foodname,foodprice;
        CircleImageView im;
        LottieAnimationView trash;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname= itemView.findViewById(R.id.foodname);
            foodprice= itemView.findViewById(R.id.foodprice);
            im= itemView.findViewById(R.id.imageView3);
            trash=itemView.findViewById(R.id.trash);
            DataBAseHelper dbh=new DataBAseHelper(itemView.getContext());

            trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
               String name =foodname.getText().toString();
               int food_id=dbh.returnfoodidbyfoodname(name);
//                    Toast.makeText(itemView.getContext(), food_id+"", Toast.LENGTH_SHORT).show();
                    dbh.deletebyfoodid(food_id);
                    Cursor c= dbh.returnallfood();
                    localdataset.clear();

                    if(c.moveToFirst()) {//loop through the c
                        do {
                            String foodname=c.getString(0);
                            int foodid=c.getInt(1);
                            double foodprice=c.getDouble(2);
                            byte[] blob=c.getBlob(3);
                            Bitmap bm=helpermethods.getImage(blob);
                            food_admin temp=new food_admin(foodname,foodprice,bm);
                            localdataset.add(temp);
                        } while (c.moveToNext());
                        c.close();
                    }
//                    Toast.makeText(itemView.getContext(), localdataset.size()+"", Toast.LENGTH_SHORT).show();

                    notifyDataSetChanged();

                }
            });

        }
        public TextView getnametextView()
        {
            return foodname;
        }
        public TextView getpricetextView()
        {
            return foodprice;
        }
        public CircleImageView circleImageView()
        {
            return im;
        }
    }
    public food_adapter_admin(ArrayList<food_admin> dataset){
        localdataset=dataset;
    }

    @NonNull
    @Override
    public food_adapter_admin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_food_view,parent,false);
        return new food_adapter_admin.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getnametextView().setText(localdataset.get(position).getFood_name());
        holder.getpricetextView().setText("INR:"+localdataset.get(position).getFood_price()+"");
        holder.circleImageView().setImageBitmap(localdataset.get(position).getBm());
    }

    @Override
    public int getItemCount() {
        return localdataset.size();
    }
}


