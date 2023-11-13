package com.app.daeja.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @GET("response/{parking_code}")
    Call<TestDomain> test_api_get(@Path("parking_code") String parking_code);

//    @GET("response")
    @GET("response")
    Call<List<TestDomain>> test_api_get_all();

    @GET("near")
    Call<List<TestDomain>> staticFindNearbyLocations();
}