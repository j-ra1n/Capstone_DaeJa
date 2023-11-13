package com.app.daeja.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.R;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ParkingInfoAdapter extends RecyclerView.Adapter<ParkingInfoAdapter.Viewholder> {
    ArrayList<ParkingInfo> parkingInfos;
    DecimalFormat formatter;

    public ParkingInfoAdapter(ArrayList<ParkingInfo> parkingInfos){
        this.parkingInfos = parkingInfos;
        formatter= new DecimalFormat("###,###,###,###");
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_parking_info, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.parkingNameTxt.setText(parkingInfos.get(position).getParkingNameTxt());
        holder.parkingStateTxt.setText(parkingInfos.get(position).getParkingStateTxt());
        //holder.cryptoPriceTxt.setText("$"+formatter.format(parkingInfos.get(position).getCryptoPrice()));
        holder.changePercentTxt.setText(formatter.format(parkingInfos.get(position).getCurrentParking())+ " / " +
                formatter.format(parkingInfos.get(position).getTotalParking()));
        //holder.propertySizeTxt.setText(parkingInfos.get(position).getPropertySize()+parkingInfos.get(position).getCryptoSymbol());
        //holder.propertyAmountTxt.setText("$"+parkingInfos.get(position).getPropertyAmount());
        //holder.lineChart.setData(parkingInfos.get(position).getLineData());
        double cur_par = Double.parseDouble(formatter.format(parkingInfos.get(position).getCurrentParking()));
        double tat_par = Double.parseDouble(formatter.format(parkingInfos.get(position).getTotalParking()));
        double congestion = cur_par/tat_par;
        String str_cng;
        if(congestion<0.3){
            holder.changePercentTxt.setTextColor(Color.parseColor("#97B9AD"));
            holder.parkingStateTxt.setText("주차 잔여공간 많음");
            str_cng = "ic_green";
        }else if (congestion<0.6){
            holder.changePercentTxt.setTextColor(Color.parseColor("#FFF7E682"));
            holder.parkingStateTxt.setText("주차 잔여공간 보통");
            str_cng = "ic_yellow";
        }else{
            holder.changePercentTxt.setTextColor(Color.parseColor("#FFD65745"));
            holder.parkingStateTxt.setText("주차 잔여공간 적음");
            str_cng = "ic_red";
        }

        int drawableResourceID=holder.itemView.getContext().getResources()
                .getIdentifier(str_cng, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceID)
                .into(holder.logoCrypto);
    }

    @Override
    public int getItemCount() {
        return parkingInfos.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView parkingNameTxt, parkingStateTxt, changePercentTxt, parkingPriceTxt;
        ImageView logoCrypto;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            parkingNameTxt = itemView.findViewById(R.id.parkingNameTxt);
            parkingStateTxt = itemView.findViewById(R.id.parkingStateTxt);
            changePercentTxt = itemView.findViewById(R.id.changePercentTxt);
            parkingPriceTxt = itemView.findViewById(R.id.parkingPriceTxt);
            logoCrypto = itemView.findViewById(R.id.logoImg);
        }
    }
}
