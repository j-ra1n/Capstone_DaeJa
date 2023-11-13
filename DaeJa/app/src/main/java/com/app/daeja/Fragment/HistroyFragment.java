package com.app.daeja.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.Adapter.ParkingInfoAdapter;
import com.app.daeja.R;

import java.util.ArrayList;

public class HistroyFragment extends Fragment {
    private View view;
    protected RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_histroy, container, false);

        //ecyclerViewWallet();

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);

    }

//    private void recyclerViewWallet() {
//        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(view.this, LinearLayoutManager.VERTICAL, false);
//        recyclerView = view.findViewById(R.id.view);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        ArrayList<ParkingInfo> parkingInfoArrayList = new ArrayList<>();
//        ArrayList<Integer> lineData = new ArrayList<>();
//        lineData.add(1000);
//        lineData.add(1100);
//        lineData.add(1200);
//        lineData.add(1100);
//
//        ArrayList<Integer> lineData2 = new ArrayList<>();
//        lineData2.add(2100);
//        lineData2.add(2000);
//        lineData2.add(1900);
//        lineData2.add(2000);
//
//        ArrayList<Integer> lineData3 = new ArrayList<>();
//        lineData3.add(900);
//        lineData3.add(1000);
//        lineData3.add(1100);
//        lineData3.add(1000);
//        lineData3.add(1150);
//
//        parkingInfoArrayList.add(new ParkingInfo("서울시 공영", "주차 잔여공간 많음", 80, 10, "150원/5분", lineData));
//        parkingInfoArrayList.add(new ParkingInfo("구로디지털단지역", "주차 잔여공간 적음", 20, 20, "1000원/1시간", lineData2));
//        parkingInfoArrayList.add(new ParkingInfo("가짜", "주차 잔여공간 보통", 30, 15, "무료", lineData3));
//        parkingInfoArrayList.add(new ParkingInfo("가짜", "주차 잔여공간 보통", 30, 15, "무료", lineData3));
//
//        adapter = new ParkingInfoAdapter(parkingInfoArrayList);
//        recyclerView.setAdapter(adapter);
//    }

}
