package com.app.daeja.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.Network.TestDomain;
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
//import retrofit2.Response;


public class HomeFragment extends Fragment {
    TextView textview;

    private View view;
    private Context ct;

    //for server
    private Thread thread;
    private boolean isThread = false;
    Call<List<TestDomain>> call;
    List<ParkingInfo> parkingInfos;
    ArrayList<Integer> lineData;
    //List<TMapMarkerItem> tMapMarkerItems;

    //for tmap
    private LinearLayout linearLayoutTmap;
    private TMapView tMapView;
    private static final String tApiKey = "KbtV6K1LiCa2kYZ2ieDhU3pxBBS5A5gA5CL5O3el";

    //util
    Button btn, btn2, btn3, btn4;

    @SuppressLint("CutPasteId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        ct = container.getContext();

        //tmapview
        linearLayoutTmap = (LinearLayout) view.findViewById(R.id.linearLayoutTmap);

        tMapView = new TMapView(ct);
        tMapView.setSKTMapApiKey(tApiKey);
        tMapView.setZoomLevel(14);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(tMapView.MAPTYPE_STANDARD);
        tMapView.setLocationPoint(127.1276, 37.32335);

//        lineData = new ArrayList<>();
//        lineData.add(1000);
//        lineData.add(1100);
//        lineData.add(1200);
//        lineData.add(1100);

        //server
        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create Thread
                isThread = true;
                thread = new Thread(){
                    public void run(){
                        while(isThread){
                            handler.sendEmptyMessage(0);
                            callServer();
                            try {
                                sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                };
                thread.start();
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread = false;
                tMapView.removeAllMarkerItem();
                pointPin();
            }
        });

        btn3 = view.findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapView.removeAllMarkerItem();
            }
        });

        linearLayoutTmap.addView(tMapView);

        return view;
    }
    private void pointPin() {

        for(int i = 0; i < parkingInfos.size(); i++){
            TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
            TMapPoint tMapPoint = new TMapPoint((double)parkingInfos.get(i).getLatitude(), (double)parkingInfos.get(i).getLongitude());
            String markerId;
            Bitmap bitmap;

            //marker setting
            if(parkingInfos.get(i).getRgb().equals("G")){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_green);
            }else if (parkingInfos.get(i).getRgb().equals("Y")){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_yellow);
            }else if (parkingInfos.get(i).getRgb().equals("R")){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_red);
            }else{
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_gray);
            }

            tMapMarkerItem.setIcon(bitmap);
            tMapMarkerItem.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
            tMapMarkerItem.setTMapPoint( tMapPoint );
            tMapMarkerItem.setName(parkingInfos.get(i).getParkingNameTxt()); // 마커의 타이틀 지정
            tMapMarkerItem.setCanShowCallout(true); // 풍선뷰
            tMapMarkerItem.setCalloutTitle(parkingInfos.get(i).getParkingNameTxt());
            tMapMarkerItem.setCalloutSubTitle((int)parkingInfos.get(i).getCurrentParking() + "/" + (int)parkingInfos.get(i).getTotalParking());
            tMapMarkerItem.setCalloutLeftImage(bitmap);
            tMapMarkerItem.setCalloutRightButtonImage(bitmap);
            tMapMarkerItem.setEnableClustering(true);
            markerId = "marker: " + parkingInfos.get(i).getParkingNameTxt();

            //add mark to maps
            tMapView.addMarkerItem(markerId, tMapMarkerItem);
        }
    }


    private void callServer() {
        parkingInfos = new ArrayList<>();

        call = retrofit.getApiService().test_api_get_all();
        call.enqueue(new Callback<List<TestDomain>>() {
            @Override
            public void onResponse(Call<List<TestDomain>> call, Response<List<TestDomain>> response) {
                List<TestDomain> resultList = response.body();
                ParkingInfo parkingInfo;

                for (TestDomain result : resultList) {
                    parkingInfo = new ParkingInfo();

                    parkingInfo.setParkingId(Integer.parseInt(result.getId()));
                    parkingInfo.setParkingCodeTxt(result.getParkingCode());
                    parkingInfo.setParkingNameTxt(result.getParkingName());
                    parkingInfo.setParkingStateTxt("");
                    parkingInfo.setTotalParking((int)result.getCapacity());
                    parkingInfo.setCurrentParking((int)result.getCurParking());
                    parkingInfo.setParkingPriceTxt("");
                    parkingInfo.setLatitude(result.getLat());
                    parkingInfo.setLongitude(result.getLng());
                    //parkingInfo.setLineData(lineData);
                    parkingInfo.setRgb(result.getColor());

                    parkingInfos.add(parkingInfo);

                }
            }

            @Override
            public void onFailure(Call<List<TestDomain>> call, Throwable t) {
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
