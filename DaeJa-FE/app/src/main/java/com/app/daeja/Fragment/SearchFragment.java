package com.app.daeja.Fragment;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.daeja.Activity.Domain.Location;
import com.app.daeja.Adapter.SearchLocationAdapter;
import com.app.daeja.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.poi_item.TMapPOIItem;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private View view;
    public Context ct;
    private ImageView img_search_btn;
    private EditText et_search_bar;

    String str_search;

    protected RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    public static List<Location> locations = new ArrayList<>();;
    ArrayList POIItem;
    private TMapPoint curLoc = new TMapPoint(37.5663507, 126.9851113);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);
        ct = container.getContext();

        init();

        img_search_btn.setOnClickListener(v -> {
            str_search = et_search_bar.getText().toString();

            ArrayList<String> poiKeywords = new ArrayList<>();
            poiKeywords.add(str_search);
            searchPOI(poiKeywords);
            Log.e("d", poiKeywords.get(0).toString());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            recyclerViewLocation();
        });

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void recyclerViewLocation() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(ct, LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.view);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<Location> locationArrayList = new ArrayList<>();

        for(int i = 0; i < locations.size(); i++) {
            locationArrayList.add(locations.get(i));
        }

        adapter = new SearchLocationAdapter(locationArrayList, this);
        recyclerView.setAdapter(adapter);
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
                    locations = new ArrayList<>();
                    for(int j = 0; j < arrayList.size(); j++ )
                    {
                        TMapPOIItem tMapPOIItem = arrayList.get(j);
                        locations.add(new Location(tMapPOIItem.getPOIName(),
                                tMapPOIItem.secondNo,
                                tMapPOIItem.upperAddrName + " " +
                                        tMapPOIItem.middleAddrName + " " + tMapPOIItem.lowerAddrName,
                                tMapPOIItem.telNo,
                            tMapPOIItem.getPOIPoint().getLatitude(),
                                tMapPOIItem.getPOIPoint().getLongitude())
                        );
                        Log.e("error", locations.get(0).getName());
                    }
                }
            });
        }
    }

    private void init() {
        img_search_btn = view.findViewById(R.id.img_search_btn);
        et_search_bar = view.findViewById(R.id.et_search_bar);
    }
}
