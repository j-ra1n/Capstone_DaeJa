package com.app.daeja.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.Adapter.ParkingInfoAdapter;
import com.app.daeja.Network.retrofit;
import com.app.daeja.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecomendActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private TMapGpsManager tMapGpsManager;
    Thread thread;
    Call<List<ParkingInfo>> call;

    private ImageView img_findPath;

    protected RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private LinearLayout linearLayoutTmap;
    private List<ParkingInfo> parkingInfos;
    private TMapView tMapView;
    private double cur_lat = 37.316108570624706;
    private double cur_lng = 127.12660302907162;
    private static final String tApiKey = "KbtV6K1LiCa2kYZ2ieDhU3pxBBS5A5gA5CL5O3el";

    private Intent secondIntent;
    private String who;
    private double loc_lat = 37.5663507;
    private double loc_lng = 126.9851113;

    public double getCur_lat() {
        return cur_lat;
    }

    public void setCur_lat(double cur_lat) {
        this.cur_lat = cur_lat;
    }

    public double getCur_lng() {
        return cur_lng;
    }

    public void setCur_lng(double cur_lng) {
        this.cur_lng = cur_lng;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomend);

        secondIntent = getIntent();
        who = secondIntent.getStringExtra("WHO");
        loc_lat = secondIntent.getDoubleExtra("lat", 37.5663507);
        loc_lng = secondIntent.getDoubleExtra("lng", 126.9851113);

        // 위치 권한 확인 및 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            // 권한이 이미 부여되었으면 지도 설정 시작
        }

        tMapViewInit1();

        if (who.equals("current")) {
            //tMapViewInit1();
            currentCallServerAsync();
        }else {
            Log.e("not current", "here");
            //tMapViewInit2();
            locationCallServerAsync();
        }


        ImageView img_refresh = findViewById(R.id.img_refresh);
        img_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {tMapView.removeAllMarkerItem();
                pointPin();
//                if (who.equals("current")) {
//                    tMapView.setLocationPoint(cur_lat, cur_lng);
//                }else {
//                    tMapView.setLocationPoint(loc_lat, loc_lng);
//                }
                recyclerViewParking();
            }
        });

    }

    // callServer() 메서드를 비동기로 실행하는 방법
    private void currentCallServerAsync() {
        new Thread(() -> {
            currentCallServer(); // 네트워크 작업 수행
        }).start();
    }
    private void locationCallServerAsync() {
        new Thread(() -> {
            locationCallServer(); // 네트워크 작업 수행
        }).start();
    }

    // drawPath() 메서드를 비동기로 실행하는 방법
    public void drawPathAsync(double des_lat, double des_lng) {
        new Thread(() -> {
            drawPath(des_lat,des_lng); // 경로 그리기 작업 수행
        }).start();
    }


    private void pointPin() {

        for(int i = 0; i < parkingInfos.size(); i++){
            TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
            TMapPoint tMapPoint = new TMapPoint(parkingInfos.get(i).getLat(), parkingInfos.get(i).getLng());
            String markerId;
            Bitmap bitmap;

            //marker setting
            if(parkingInfos.get(i).getColor().equals("많음")){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_green);
            }else if (parkingInfos.get(i).getColor().equals("보통")){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_yellow);
            }else if (parkingInfos.get(i).getColor().equals("적음")){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_red);
            }else{
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_gray);
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

    private void currentCallServer() {
        parkingInfos = new ArrayList<>();
//        parkingInfos.add(new ParkingInfo(1, 1, "구로디지털 단지역", "주소입니다.", "노상주차장", "시간제 주차장", "TEL:010", true, 180, 90, "업데이트 시간", "유료", "야간 무료개방", "09:00", "18:00", "09:00", "16:00", "12:00", "18:00", "무료", "무료", 0, "1500", "60", "100", "60", 60000, 37.48497, 126.9012, "", "https://www.naver.com/", false, "보통"));
//        parkingInfos.add(new ParkingInfo(2, 2, "올리브 모텔", "주소입니다.", "노상주차장", "시간제 주차장", "MOTEL:010", true, 34, 34, "업데이트 시간", "유료", "야간 개방 x", "09:00", "18:00", "09:00", "16:00", "12:00", "18:00", "무료", "무료", 100000, "10000", "5", "1000", "60", 60000, 37.48395, 126.9010, "", "https://github.com/codehooni/", false, "적음"));
//        parkingInfos.add(new ParkingInfo(3, 3, "나이스파크 주차장", "주소입니다.", "노상주차장", "시간제 주차장", "MOTEL:010", true, 60, 10, "업데이트 시간", "유료", "야간 개방 x", "09:00", "18:00", "09:00", "16:00", "12:00", "18:00", "무료", "무료", 100000, "2000", "60", "150", "60", 60000, 37.48578, 126.9017, "", "", false, "많음"));
        call = retrofit.getApiService().test_api_get_recommend(cur_lat, cur_lng);
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

    private void locationCallServer() {
        parkingInfos = new ArrayList<>();
//        parkingInfos.add(new ParkingInfo(1, 1, "구로디지털 단지역", "주소입니다.", "노상주차장", "시간제 주차장", "TEL:010", true, 180, 90, "업데이트 시간", "유료", "야간 무료개방", "09:00", "18:00", "09:00", "16:00", "12:00", "18:00", "무료", "무료", 0, "1500", "60", "100", "60", 60000, 37.48497, 126.9012, "", "", false, "보통"));
//        parkingInfos.add(new ParkingInfo(2, 2, "올리브 모텔", "주소입니다.", "노상주차장", "시간제 주차장", "MOTEL:010", true, 34, 34, "업데이트 시간", "유료", "야간 개방 x", "09:00", "18:00", "09:00", "16:00", "12:00", "18:00", "무료", "무료", 100000, "10000", "5", "1000", "60", 60000, 37.48395, 126.9010, "", "", false, "적음"));
//        parkingInfos.add(new ParkingInfo(3, 3, "나이스파크 주차장", "주소입니다.", "노상주차장", "시간제 주차장", "MOTEL:010", true, 60, 10, "업데이트 시간", "유료", "야간 개방 x", "09:00", "18:00", "09:00", "16:00", "12:00", "18:00", "무료", "무료", 100000, "2000", "60", "150", "60", 60000, 37.48578, 126.9017, "", "", false, "많음"));
        call = retrofit.getApiService().test_api_get_recommend(loc_lat, loc_lng);
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

    private void tMapViewInit1() {
        linearLayoutTmap = findViewById(R.id.linearLayoutTmap);

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(tApiKey);

        // TMapGpsManager를 사용하여 현재 위치 설정
        tMapGpsManager = new TMapGpsManager(this);
        tMapGpsManager.setMinTime(1000);
        tMapGpsManager.setMinDistance(5);
        tMapGpsManager.setProvider(TMapGpsManager.GPS_PROVIDER);
        tMapGpsManager.OpenGps();

        // 현재 위치를 나타내는 마커 추가
        TMapMarkerItem currentLocationMarker = new TMapMarkerItem();
        currentLocationMarker.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_gray));
        tMapView.addMarkerItem("currentLocationMarker", currentLocationMarker);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // 현재 위치의 좌표를 가져와서 마커 위치 업데이트
                Log.d("Recommend Location", "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());

                cur_lat = location.getLatitude();
                cur_lng = location.getLongitude();

                if (who.equals("current")) {
                    Log.e("in current", "in current");
                    tMapView.setCenterPoint(cur_lng, cur_lat);
                    tMapView.setLocationPoint(cur_lng, cur_lat );
                    currentCallServerAsync();
                }else {
                    Log.e("in local", "in local");
                    tMapView.setCenterPoint(loc_lng, loc_lat);
                    tMapView.setLocationPoint(cur_lng, cur_lat );
                }

                // Null 체크 추가하여 안전하게 호출하기
                if (tMapView != null && tMapView.getMarkerItemFromID("currentLocationMarker") != null) {
                    Log.e("log", "I got the current location");
                    TMapPoint currentLocation = new TMapPoint(cur_lat, cur_lng);
                    tMapView.setCenterPoint(cur_lng, cur_lat);
                    tMapView.setLocationPoint(cur_lng, cur_lat );
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // 위치 공급자 상태 변경 시 호출
            }

            @Override
            public void onProviderEnabled(String provider) {
                // 위치 공급자가 사용 가능한 상태로 변경 시 호출
            }

            @Override
            public void onProviderDisabled(String provider) {
                // 위치 공급자가 사용 불가능한 상태로 변경 시 호출
            }
        };

        // 위치 업데이트를 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, locationListener);
        }

        tMapView.setZoomLevel(16);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(tMapView.MAPTYPE_STANDARD);
        tMapView.setLocationPoint(cur_lng, cur_lat);
        tMapView.setCenterPoint(cur_lng, cur_lat);

        linearLayoutTmap.addView(tMapView);
    }

    private void tMapViewInit2() {
        linearLayoutTmap = findViewById(R.id.linearLayoutTmap);

        tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey(tApiKey);

        tMapView.setZoomLevel(16);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(tMapView.MAPTYPE_STANDARD);
        tMapView.setCenterPoint(loc_lng, loc_lat);

        linearLayoutTmap.addView(tMapView);
    }


    // 경로 찍는 메서드
    public void drawPath(double des_lat, double des_lng) {
        TMapPoint Start = new TMapPoint(cur_lat, cur_lng); // 현재위치
        TMapPoint End = new TMapPoint(des_lat, des_lng); // 목적지   //37.5663507, 126.9851113 서울타워  //37.322235, 127.12765166666667 단국대

        try {
            TMapPolyLine tMapPolyLine = new TMapData().findPathData(Start, End);
            tMapPolyLine.setLineColor(Color.MAGENTA);
            tMapPolyLine.setLineWidth(2);
            tMapView.addTMapPolyLine("Line1", tMapPolyLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recyclerViewParking() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<ParkingInfo> parkingInfoArrayList = new ArrayList<>();

        for(int i = 0; i < parkingInfos.size(); i++) {
            parkingInfoArrayList.add(parkingInfos.get(i));
        }

        adapter = new ParkingInfoAdapter(parkingInfoArrayList, this);
        recyclerView.setAdapter(adapter);
    }
}