package com.app.daeja.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.Network.retrofit;
import com.app.daeja.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.poi_item.TMapPOIItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private TMapGpsManager tMapGpsManager;

    private View view;
    private Context ct;

    //for server
    Thread thread;
    boolean isThread;
    Call<List<ParkingInfo>> call;
    List<ParkingInfo> parkingInfos;

    //for tmap
    private LinearLayout linearLayoutTmap;
    private TMapView tMapView;
    private double cur_lat = 37.4849732;
    private double cur_lng = 126.9012370;
    private static final String tApiKey = "KbtV6K1LiCa2kYZ2ieDhU3pxBBS5A5gA5CL5O3el";


    //util
    Button btn;
    Button btn2;
    Button btn3;
    ImageView img_search;
    EditText search_barEt;
    String str_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        ct = container.getContext();

        if (ContextCompat.checkSelfPermission(ct, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            // 권한이 이미 부여되었으면 지도 설정 시작
            TMapViewInit();
        }

        isThread = true;
        thread = new Thread(() -> {
            while(isThread){
                callServer();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                tMapView.removeAllMarkerItem();
                pointPin();
                resetServer();
                try {
                    Thread.sleep(120000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();

        //button
//        btn = view.findViewById(R.id.btn);
//        btn.setOnClickListener(v -> {
//        });
//
//        btn2 = (Button) view.findViewById(R.id.btn2);
//        btn2.setOnClickListener(v -> {
//            isThread = false;
//            tMapView.removeAllMarkerItem();
//            pointPin();
//        });
//
//        btn3 = view.findViewById(R.id.btn3);
//        btn3.setOnClickListener(v ->{
//            tMapView.removeAllMarkerItem();
//        });

        img_search = view.findViewById(R.id.img_search);
        search_barEt = view.findViewById(R.id.search_barEt);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_search = search_barEt.getText().toString();
                tMapView.removeAllMarkerItem();

                tMapView.setLocationPoint(cur_lng, cur_lat);
                tMapView.setCenterPoint(cur_lng, cur_lat);

                ArrayList<String> poiKeywords = new ArrayList<>();
                poiKeywords.add(str_search);

                searchPOI(poiKeywords);
            }
        });

        return view;
    }

    private Bitmap createMarkerIcon(int image)  // 이미지 크기조정
    {
        Bitmap bitmap = BitmapFactory.decodeResource(ct.getApplicationContext().getResources(),
                image);
        bitmap = Bitmap.createScaledBitmap(bitmap, 100, 100,false);

        return bitmap;
    }

    private void setMultiMarkers(ArrayList<TMapPoint> Point, ArrayList<String> Name,
                                 ArrayList<String> Address)
    {
        for( int i = 0; i < Point.size(); i++ )
        {
            Bitmap bitmapIcon = createMarkerIcon(R.drawable.ic_gray);

            TMapMarkerItem tMapMarkerItem = new TMapMarkerItem();
            tMapMarkerItem.setIcon(bitmapIcon);

            tMapMarkerItem.setTMapPoint(Point.get(i));

            tMapView.addMarkerItem("markerItem" + i, tMapMarkerItem);
            setBalloonView(tMapMarkerItem, Name.get(i), Address.get(i));

        }
    }

    private void searchPOI(ArrayList<String> arrPOI)
    {
        final TMapData tMapData = new TMapData();
        final ArrayList<TMapPoint> Point = new ArrayList<>();
        final ArrayList<String> Name = new ArrayList<>();
        final ArrayList<String> Address = new ArrayList<>();

        for(int i = 0; i < arrPOI.size(); i++ )
        {
            tMapData.findTitlePOI(arrPOI.get(i), new TMapData.FindTitlePOIListenerCallback()
            {
                @Override
                public void onFindTitlePOI(ArrayList<TMapPOIItem> arrayList)
                {
                    for(int j = 0; j < arrayList.size(); j++ )
                    {
                        TMapPOIItem tMapPOIItem = arrayList.get(j);
                        Point.add(tMapPOIItem.getPOIPoint());
                        Name.add(tMapPOIItem.getPOIName());
                        Address.add(tMapPOIItem.upperAddrName + " " +
                                tMapPOIItem.middleAddrName + " " + tMapPOIItem.lowerAddrName);
                    }
                    setMultiMarkers(Point, Name, Address);
                }
            });
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
    private void resetServer() {
        call = retrofit.getApiService().test_api_get_reset();
        call.enqueue(new Callback<List<ParkingInfo>>() {
            @Override
            public void onResponse(Call<List<ParkingInfo>> call, Response<List<ParkingInfo>> response) {
            }
            @Override
            public void onFailure(Call<List<ParkingInfo>> call, Throwable t) {
            }
        });
    }

    private void callServer() {
        parkingInfos = new ArrayList<>();
        handler.sendEmptyMessage(0);
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

    private void TMapViewInit(){
        linearLayoutTmap = view.findViewById(R.id.linearLayoutTmap);

        tMapView = new TMapView(ct);
        tMapView.setSKTMapApiKey(tApiKey);

        // TMapGpsManager를 사용하여 현재 위치 설정
        tMapGpsManager = new TMapGpsManager(this.getContext());
        tMapGpsManager.setMinTime(1000);
        tMapGpsManager.setMinDistance(5);
        tMapGpsManager.setProvider(TMapGpsManager.GPS_PROVIDER);
        tMapGpsManager.OpenGps();

        // 현재 위치를 나타내는 마커 추가
        TMapMarkerItem currentLocationMarker = new TMapMarkerItem();
        currentLocationMarker.setIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_gray));
        tMapView.addMarkerItem("currentLocationMarker", currentLocationMarker);

        LocationManager locationManager = (LocationManager) ct.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                // 현재 위치의 좌표를 가져와서 마커 위치 업데이트
                Log.d("Home Location", "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());

                cur_lat = location.getLatitude();
                cur_lng = location.getLongitude();

                tMapView.setCenterPoint(cur_lng, cur_lat);
                tMapView.setLocationPoint(cur_lng, cur_lat );

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
        if (ContextCompat.checkSelfPermission(ct, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 5, locationListener);
        }

        tMapView.setZoomLevel(14);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(tMapView.MAPTYPE_STANDARD);
        tMapView.setLocationPoint(cur_lng, cur_lat);
        tMapView.setCenterPoint(cur_lng, cur_lat);

        linearLayoutTmap.addView(tMapView);
    }

    private void setBalloonView(TMapMarkerItem marker, String title, String address)
    {
        marker.setCanShowCallout(true);

        if( marker.getCanShowCallout() )
        {
            marker.setCalloutTitle(title);
            marker.setCalloutSubTitle(address);

            Bitmap bitmap = createMarkerIcon(R.drawable.ic_gray);
            marker.setCalloutRightButtonImage(bitmap);
        }
    }
}
