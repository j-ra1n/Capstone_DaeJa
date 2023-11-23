package com.app.daeja.Activity;

import static com.app.daeja.Fragment.HistroyFragment.histories;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.daeja.Activity.Domain.History;
import com.app.daeja.Activity.Domain.ParkingInfo;
import com.app.daeja.R;
import com.skt.Tmap.TMapTapi;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkingDatailActivity extends AppCompatActivity {

    TextView tv_park_name, tv_park_sub_name, tv_cur_parking, div, tv_capacity, tv_cur_parking_time, tv_parking_addr, tv_tel2, tv_pay_nm, tv_rates, tv_addRate, rate_div, tv_pay_nm_end, tv_holyday_pay_nm, tv_night_free_open_nm, tv_weekday_begin_time, tv_weekday_end_time, tv_weekend_begin_time, tv_weekend_end_time, tv_holyday_begin_time, tv_holyday_end_time, tv_sh_co, tv_sh_link;
    ImageView img_path;
    ParkingInfo parkingInfo;
    List<History> historyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_datail);

        Intent intent = getIntent();
        parkingInfo = (ParkingInfo) intent.getSerializableExtra("parkingInfo");
        //historyList = (List<History>) intent.getSerializableExtra("histories");
        init();
        setting();
    }

    private void setting() {
        tv_park_name.setText(parkingInfo.getPARKING_NAME());
        tv_park_sub_name.setText(parkingInfo.getParking_TYPE_NM());
        tv_cur_parking.setText(parkingInfo.getCur_PARKING()+ "");
        tv_capacity.setText(parkingInfo.getCapacity() + "");
        tv_cur_parking_time.setText(parkingInfo.getCur_PARKING_TIME());
        tv_parking_addr.setText(parkingInfo.getPARKING_ADDR());
        tv_tel2.setText(parkingInfo.getTel());
        tv_pay_nm.setText(parkingInfo.getPay_NM());
        tv_rates.setText(parkingInfo.getRates() + "원/" + parkingInfo.getTime_RATE() + "분");
        tv_addRate.setText(parkingInfo.getAdd_RATES() + "원/" + parkingInfo.getAdd_TIME_RATE() + "분");
        rate_div.setText("(최대: " + parkingInfo.getDay_MAXIMUM() + "원)");
        tv_holyday_pay_nm.setText(parkingInfo.getHolyday_PAY_NM());
        tv_night_free_open_nm.setText(parkingInfo.getNight_FREE_OPEN_NM());
        tv_weekday_begin_time.setText(parkingInfo.getWeekday_BEGIN_TIME());
        tv_weekday_end_time.setText(parkingInfo.getWeekday_END_TIME());
        tv_weekend_begin_time.setText(parkingInfo.getWeekend_BEGIN_TIME());
        tv_weekend_end_time.setText(parkingInfo.getWeekend_END_TIME());
        tv_holyday_begin_time.setText(parkingInfo.getHolyday_BEGIN_TIME());
        tv_holyday_end_time.setText(parkingInfo.getHolyday_END_TIME());
        tv_sh_co.setText(parkingInfo.getSh_CO());
        tv_sh_link.setText(parkingInfo.getSh_LINK());

        if(parkingInfo.getColor().equals("많음")){
            tv_cur_parking.setTextColor(Color.parseColor("#97B9AD"));
            div.setTextColor(Color.parseColor("#97B9AD"));
            tv_capacity.setTextColor(Color.parseColor("#97B9AD"));
        }else if (parkingInfo.getColor().equals("보통")){
            tv_cur_parking.setTextColor(Color.parseColor("#FFF7E682"));
            div.setTextColor(Color.parseColor("#FFF7E682"));
            tv_capacity.setTextColor(Color.parseColor("#FFF7E682"));
        }else{ //적음
            tv_cur_parking.setTextColor(Color.parseColor("#FFD65745"));
            div.setTextColor(Color.parseColor("#FFD65745"));
            tv_capacity.setTextColor(Color.parseColor("#FFD65745"));
        }

        img_path.setOnClickListener(v -> {
            launchTmapApp(parkingInfo.getPARKING_NAME(), parkingInfo.getLat(), parkingInfo.getLng());
            histories.add(new History("default", parkingInfo.getPARKING_NAME()));
        });

        Linkify.TransformFilter linktest = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return "";
            }
        };
        Pattern pattern = Pattern.compile(" ");
        Linkify.addLinks(tv_sh_link, pattern, parkingInfo.getSh_LINK(), null, linktest);

    }

    public void launchTmapApp(String name, double lat, double lng) {

        TMapTapi tMapTapi = new TMapTapi(this);
        boolean isTmapApp = tMapTapi.isTmapApplicationInstalled();
        System.out.println(isTmapApp);   // -> 설치유무확인

        tMapTapi.invokeRoute(name, (float)lng, (float)lat);

    }

//    private String getCurloc() {
//        try {
//            final String address = new TMapData().convertGpsToAddress(recomendActivity.getCur_lat(), recomendActivity.getCur_lng());
//            return address;
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return "default";
//    }

    private void init() {
        tv_park_name = findViewById(R.id.tv_park_name);
        tv_park_sub_name = findViewById(R.id.tv_park_sub_name);
        img_path = findViewById(R.id.img_path);
        tv_cur_parking = findViewById(R.id.tv_cur_parking);
        div = findViewById(R.id.div);
        tv_capacity = findViewById(R.id.tv_capacity);
        tv_cur_parking_time = findViewById(R.id.tv_cur_parking_time);
        tv_parking_addr = findViewById(R.id.tv_parking_addr);
        tv_tel2 = findViewById(R.id.tv_tel2);
        tv_pay_nm = findViewById(R.id.tv_pay_nm);
        tv_rates = findViewById(R.id.tv_rates);
        tv_addRate = findViewById(R.id.tv_addRate);
        rate_div = findViewById(R.id.rate_div);
        tv_pay_nm_end = findViewById(R.id.tv_pay_nm_end);
        tv_holyday_pay_nm = findViewById(R.id.tv_holyday_pay_nm);
        tv_night_free_open_nm = findViewById(R.id.tv_night_free_open_nm);
        tv_weekday_begin_time = findViewById(R.id.tv_weekday_begin_time);
        tv_weekday_end_time = findViewById(R.id.tv_weekday_end_time);
        tv_weekend_begin_time = findViewById(R.id.tv_weekend_begin_time);
        tv_weekend_end_time = findViewById(R.id.tv_weekend_end_time);
        tv_holyday_begin_time = findViewById(R.id.tv_holyday_begin_time);
        tv_holyday_end_time = findViewById(R.id.tv_holyday_end_time);
        tv_sh_co = findViewById(R.id.tv_sh_co);
        tv_sh_link = findViewById(R.id.tv_sh_link);
    }

}