package com.app.daeja.Activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.Adapter.ParkingInfoAdapter;
import com.app.daeja.R;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class RecomendActivity extends AppCompatActivity {

    protected RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutTmap;
    TMapView tMapView;
    private static final String tApiKey = "KbtV6K1LiCa2kYZ2ieDhU3pxBBS5A5gA5CL5O3el";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomend);

        linearLayoutTmap = (LinearLayout) findViewById(R.id.linearLayoutTmap);

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(tApiKey);
        tMapView.setZoomLevel(16);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(tMapView.MAPTYPE_STANDARD);
        tMapView.setLocationPoint(127.1276, 37.32335);

        linearLayoutTmap.addView(tMapView);

        //setContentView(linearLayoutTmap);

        recyclerViewWallet();


    }

    private void recyclerViewWallet() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<ParkingInfo> parkingInfoArrayList = new ArrayList<>();
        ArrayList<Integer> lineData = new ArrayList<>();
        lineData.add(1000);
        lineData.add(1100);
        lineData.add(1200);
        lineData.add(1100);

        ArrayList<Integer> lineData2 = new ArrayList<>();
        lineData2.add(2100);
        lineData2.add(2000);
        lineData2.add(1900);
        lineData2.add(2000);

        ArrayList<Integer> lineData3 = new ArrayList<>();
        lineData3.add(900);
        lineData3.add(1000);
        lineData3.add(1100);
        lineData3.add(1000);
        lineData3.add(1150);

//        parkingInfoArrayList.add(new ParkingInfo(23, "1234124","서울시 공영", "주차 잔여공간 많음", 80, 10, "150원/5분", 234.234, 4234.345, lineData));
//        parkingInfoArrayList.add(new ParkingInfo(12, "1234124", "구로디지털단지역", "주차 잔여공간 적음", 20, 20, "1000원/1시간",345.3534,345.3456, lineData2));
//        parkingInfoArrayList.add(new ParkingInfo(1545, "1234124","가짜", "주차 잔여공간 보통", 30, 15, "무료",345.3534,345.3456,  lineData3));
//        parkingInfoArrayList.add(new ParkingInfo(123, "1234124", "가짜", "주차 잔여공간 보통", 30, 15, "무료",345.3534,345.3456,  lineData3));

        adapter = new ParkingInfoAdapter(parkingInfoArrayList);
        recyclerView.setAdapter(adapter);
    }
}