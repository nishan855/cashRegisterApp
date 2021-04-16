package com.example.cashregisterapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private ArrayList<OrderItems> lst;

    // RecyclerView recyclerView;
    public ItemAdapter(ArrayList<OrderItems> listdata) {
        this.lst = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup par, int vType) {
        LayoutInflater layoutInflater = LayoutInflater.from(par.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_item, par, false);
        ViewHolder vh = new ViewHolder(listItem);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OrderItems myListData = lst.get(position);
        holder.textViewItem.setText(lst.get(position).getItemName());
        holder.textViewPrice.setText(lst.get(position).getItemprice());
        holder.textViewTotal.setText(String.format("%.2f",lst.get(position).getTotal()));

    }


    @Override
    public int getItemCount() {
        return lst.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private TextView textViewItem;
        private TextView textViewPrice;
        private TextView textViewQnty;
        private TextView textViewTax;
        private TextView textViewTotal;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.textViewItem = (TextView) itemView.findViewById(R.id.textView33);
            this.textViewPrice = (TextView) itemView.findViewById(R.id.textView66);
            this.textViewTotal = (TextView) itemView.findViewById(R.id.textView77);

        }
    }
}