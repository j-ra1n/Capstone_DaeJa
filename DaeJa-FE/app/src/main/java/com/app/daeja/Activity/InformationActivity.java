package com.app.daeja.Activity;

import static com.app.daeja.Fragment.HistroyFragment.histories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.daeja.Activity.Domain.History;
import com.app.daeja.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapTapi;

public class InformationActivity extends AppCompatActivity {

    TextView tv_loc_name, tv_sub_name, tv_addr, telTxt;
    ImageView img_path;

    private RecomendActivity recomendActivity = new RecomendActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        init();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String subName = intent.getStringExtra("subName");
        String address = intent.getStringExtra("address");
        String tel = intent.getStringExtra("tel");
        double lat = intent.getDoubleExtra("lat", 37.5663507);
        double lng = intent.getDoubleExtra("lng", 126.9851113);

        tv_loc_name.setText(name);
        tv_sub_name.setText(subName);
        tv_addr.setText(address);
        telTxt.setText(tel);
        img_path.setOnClickListener(v -> {
            String curLoc = getCurloc();
            launchTmapApp(name, lat, lng);
            histories.add(new History(curLoc, name));
        });

    }

    public void launchTmapApp(String name, double lat, double lng) {

        TMapTapi tMapTapi = new TMapTapi(this);
        boolean isTmapApp = tMapTapi.isTmapApplicationInstalled();
        System.out.println(isTmapApp);   // -> 설치유무확인

        tMapTapi.invokeRoute(name, (float)lng, (float)lat);

    }

    private String getCurloc() {
        try {
            final String address = new TMapData().convertGpsToAddress(recomendActivity.getCur_lat(), recomendActivity.getCur_lng());
            return address;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return "default";
    }

    private void init() {
        tv_loc_name = findViewById(R.id.tv_loc_name);
        tv_sub_name = findViewById(R.id.tv_sub_name);
        tv_addr = findViewById(R.id.tv_addr);
        telTxt = findViewById(R.id.telTxt);
        img_path = findViewById(R.id.img_path);
    }
}