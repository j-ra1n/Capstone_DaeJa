package com.app.daeja.Thread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.Network.retrofit;
import com.app.daeja.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallAndPointThread extends Thread{

    boolean isThread;
    Context ct;
    Call<List<ParkingInfo>> call;
    List<ParkingInfo> parkingInfos;
    TMapView tMapView;

    public CallAndPointThread(String name, Context ct, List<ParkingInfo> parkingInfos, TMapView tMapView){
        super(name);
        this.isThread = true;
        this.ct = ct;
        this.parkingInfos = parkingInfos;
        this.tMapView = tMapView;
    }

    public void run(){
        while(isThread){
            handler.sendEmptyMessage(0);
            callServer();
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tMapView.removeAllMarkerItem();
            pointPin();
        }
    }

    private void pointPin() {

        for(int i = 0; i < parkingInfos.size(); i++){
            TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
            TMapPoint tMapPoint = new TMapPoint(parkingInfos.get(i).getLat(), parkingInfos.get(i).getLng());
            String markerId;
            Bitmap bitmap;

            //marker setting
            if(parkingInfos.get(i).getColor().equals("많음")){
                bitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.ic_green);
            }else if (parkingInfos.get(i).getColor().equals("보통")){
                bitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.ic_yellow);
            }else if (parkingInfos.get(i).getColor().equals("적음")){
                bitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.ic_red);
            }else{
                bitmap = BitmapFactory.decodeResource(ct.getResources(), R.drawable.ic_gray);
            }

            tMapMarkerItem.setIcon(bitmap);
            tMapMarkerItem.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
            tMapMarkerItem.setTMapPoint( tMapPoint );
            tMapMarkerItem.setName(parkingInfos.get(i).getPARKING_NAME()); // 마커의 타이틀 지정
            tMapMarkerItem.setCanShowCallout(true); // 풍선뷰
            tMapMarkerItem.setCalloutTitle(parkingInfos.get(i).getPARKING_NAME());
            tMapMarkerItem.setCalloutSubTitle(parkingInfos.get(i).getCur_PARKING() + "/" + parkingInfos.get(i).getCapacity());
            tMapMarkerItem.setCalloutLeftImage(bitmap);
            //tMapMarkerItem.setCalloutRightButtonImage(bitmap);
            tMapMarkerItem.setEnableClustering(true);
            markerId = "marker: " + parkingInfos.get(i).getPARKING_NAME();

            //add mark to maps
            tMapView.addMarkerItem(markerId, tMapMarkerItem);
        }
    }


    private void callServer() {
        parkingInfos = new ArrayList<>();

        call = retrofit.getApiService().test_api_get_all();
        call.enqueue(new Callback<List<ParkingInfo>>() {
            @Override
            public void onResponse(Call<List<ParkingInfo>> call, Response<List<ParkingInfo>> response) {
                List<ParkingInfo> resultList = response.body();
                for (ParkingInfo parkingInfo : resultList) {
                    parkingInfos.add(parkingInfo);
                }
            }
            @Override
            public void onFailure(Call<List<ParkingInfo>> call, Throwable t) {
                // 오류 처리
            }
        });
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Toast.makeText(ct, "receive by server", Toast.LENGTH_SHORT).show();
        }
    };
}
