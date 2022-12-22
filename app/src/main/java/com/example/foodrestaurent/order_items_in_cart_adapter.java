package com.example.foodrestaurent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class order_items_in_cart_adapter extends RecyclerView.Adapter<order_items_in_cart_adapter.ViewHolder> {
    private ArrayList<food> localdataset;
    public static class ViewHolder extends RecyclerView.ViewHolder{
       TextView foodname,foodprice,quantity;
       ImageView im;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodname=(TextView) itemView.findViewById(R.id.foodname);
            foodprice=(TextView) itemView.findViewById(R.id.foodprice);
            quantity=(TextView) itemView.findViewById(R.id.quantity);
            im=(ImageView) itemView.findViewById(R.id.imageView3);
        }
        public TextView getnametextView()
        {
          return foodname;
        }
        public TextView getpricetextView()
        {
            return foodprice;
        }
        public TextView getquantitytextView()
        {
            return quantity;
        }
        public ImageView getimageview()
        {
            return im;
        }
    }
    public order_items_in_cart_adapter(ArrayList<food>dataset ){
        localdataset=dataset;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getnametextView().setText(localdataset.get(position).getFood_name());
        holder.getpricetextView().setText("INR:"+(localdataset.get(position).getFood_price()*localdataset.get(position).getQuantity())+"");
        holder.getquantitytextView().setText(localdataset.get(position).getQuantity()+"");
        holder.getimageview().setImageBitmap(localdataset.get(position).getBm());
    }

    @Override
    public int getItemCount() {
       return localdataset.size();
    }
}
