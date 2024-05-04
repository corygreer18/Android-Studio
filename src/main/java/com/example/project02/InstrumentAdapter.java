package com.example.project02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class InstrumentAdapter extends RecyclerView.Adapter<InstrumentAdapter.MyViewHolder> {

    private List<Item> list;

    public InstrumentAdapter(List<Item> list) {
        this.list = list;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView price;
        private TextView quantity;


        public MyViewHolder(final View view){
            super(view);
            name = view.findViewById(R.id.instrumentName);
            price = view.findViewById(R.id.instrumentPrice);
            quantity = view.findViewById(R.id.instrumentQuantity);



        }
    }


    @NonNull
    @Override
    public InstrumentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.instrument_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InstrumentAdapter.MyViewHolder viewHolder, final int position) {
        viewHolder.name.setText(list.get(position).getItemName());
        viewHolder.price.setText(list.get(position).getItemPrice());
        viewHolder.quantity.setText(list.get(position).getItemQuantity());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
