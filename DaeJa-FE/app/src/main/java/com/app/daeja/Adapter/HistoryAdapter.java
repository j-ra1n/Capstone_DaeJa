package com.app.daeja.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.History;
import com.app.daeja.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Viewholder> {
    ArrayList<History> histories;
    DecimalFormat formatter;

    public HistoryAdapter(ArrayList<History> historys){
        this.histories = historys;
        formatter= new DecimalFormat("###,###,###,###");
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_history, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.startPointTxt.setText(histories.get(position).getStartPointTxt());
        holder.destinationPointTxt.setText(histories.get(position).getDestinationPointTxt());
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView startPointTxt, destinationPointTxt;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            startPointTxt = itemView.findViewById(R.id.startPointTxt);
            destinationPointTxt = itemView.findViewById(R.id.destinationPointTxt);
        }
    }
}
