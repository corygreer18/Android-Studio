// ViewHolder code for RecyclerView
package com.example.project02;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class InstrumentViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView price;
    TextView quantity;
    View view;

    InstrumentViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.instrumentName);
        price = (TextView) itemView.findViewById(R.id.instrumentPrice);
        quantity = (TextView) itemView.findViewById(R.id.instrumentQuantity);
        view = itemView;
    }

}

