package com.app.daeja.Adapter;

import static com.app.daeja.Fragment.HistroyFragment.histories;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.History;
import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.Activity.ParkingDatailActivity;
import com.app.daeja.Activity.RecomendActivity;
import com.app.daeja.R;
import com.bumptech.glide.Glide;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapTapi;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ParkingInfoAdapter extends RecyclerView.Adapter<ParkingInfoAdapter.Viewholder> {
    ArrayList<ParkingInfo> parkingInfos;
    DecimalFormat formatter;
    private RecomendActivity recomendActivity;


    public ParkingInfoAdapter(ArrayList<ParkingInfo> parkingInfos, RecomendActivity recomendActivity){
        this.parkingInfos = parkingInfos;
        this.recomendActivity = recomendActivity;
        formatter= new DecimalFormat("###,###,###,###");
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_parking_info, parent, false);
        return new Viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.parkingNameTxt.setText(parkingInfos.get(position).getPARKING_NAME());
        holder.parkingStateTxt.setText("주차 잔여공간 " + parkingInfos.get(position).getColor());
        holder.changePercentTxt.setText(formatter.format(parkingInfos.get(position).getCur_PARKING())+ " / " +
                formatter.format(parkingInfos.get(position).getCapacity()));
        holder.parkingPriceTxt.setText(parkingInfos.get(position).getRates() + "원/" + parkingInfos.get(position).getTime_RATE() + "분");
        String str_cng;
        if(parkingInfos.get(position).getColor().equals("많음")){
            holder.changePercentTxt.setTextColor(Color.parseColor("#97B9AD"));
            str_cng = "ic_green";
        }else if (parkingInfos.get(position).getColor().equals("보통")){
            holder.changePercentTxt.setTextColor(Color.parseColor("#FFF7E682"));
            str_cng = "ic_yellow";
        }else{ //적음
            holder.changePercentTxt.setTextColor(Color.parseColor("#FFD65745"));
            str_cng = "ic_red";
        }

        int drawableResourceID=holder.itemView.getContext().getResources()
                .getIdentifier(str_cng, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceID)
                .into(holder.pinImg);

        holder.img_findPath.setOnClickListener(v -> {
            Log.e("out lat", parkingInfos.get(position).getLat().toString());
            Log.e("out lng", parkingInfos.get(position).getLng().toString());
            launchTmapApp(parkingInfos.get(position).getPARKING_NAME(), parkingInfos.get(position).getLat(), parkingInfos.get(position).getLng());
            String curLoc = getCurloc();
            histories.add(new History(curLoc, parkingInfos.get(position).getPARKING_NAME()));
        });

        holder.constraintLayoutPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recomendActivity.drawPathAsync(parkingInfos.get(position).getLat(), parkingInfos.get(position).getLng());
            }
        });

        holder.detailTxt.setOnClickListener(v -> {
            Intent intent = new Intent(recomendActivity, ParkingDatailActivity.class);
            ParkingInfo parkingInfo = parkingInfos.get(position);
            intent.putExtra("parkingInfo", parkingInfo);
            recomendActivity.startActivity(intent);
        });


    }

    public void launchTmapApp(String name, double lat, double lng) {

        TMapTapi tMapTapi = new TMapTapi(recomendActivity);
        boolean isTmapApp = tMapTapi.isTmapApplicationInstalled();
        System.out.println(isTmapApp);   // -> 설치유무확인

        tMapTapi.invokeRoute(name, (float)lng, (float)lat);

    }

    private String getCurloc() {
        try {
            final String address = new TMapData().convertGpsToAddress(recomendActivity.getCur_lat(), recomendActivity.getCur_lng());
            handler.post(() -> Toast.makeText(recomendActivity, address, Toast.LENGTH_SHORT).show());
            return address;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "default";
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Toast.makeText(recomendActivity, "receive by server", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getItemCount() {
        return parkingInfos.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView parkingNameTxt, parkingStateTxt, changePercentTxt, parkingPriceTxt, detailTxt;
        ImageView pinImg, img_findPath;

        ConstraintLayout constraintLayoutPath;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            parkingNameTxt = itemView.findViewById(R.id.parkingNameTxt);
            parkingStateTxt = itemView.findViewById(R.id.parkingStateTxt);
            changePercentTxt = itemView.findViewById(R.id.changePercentTxt);
            parkingPriceTxt = itemView.findViewById(R.id.parkingPriceTxt);
            detailTxt = itemView.findViewById(R.id.detailTxt);
            pinImg = itemView.findViewById(R.id.pinImg);
            img_findPath = itemView.findViewById(R.id.img_findPath);
            constraintLayoutPath = itemView.findViewById(R.id.constraintLayoutPath);
        }
    }
}
