package com.app.daeja.Network;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestDomain {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("parkingCode")
    @Expose
    private String parkingCode;
    @SerializedName("parkingName")
    @Expose
    private String parkingName;
    @SerializedName("capacity")
    @Expose
    private double capacity;
    @SerializedName("curParking")
    @Expose
    private double curParking;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;

    @SerializedName("color")
    @Expose
    private String color;

    public String getId(){
        return id;
    }
    public String getParkingCode(){
        return parkingCode;
    }
    public String getParkingName(){
        return parkingName;
    }
    public double getCapacity(){
        return capacity;
    }
    public double getCurParking(){
        return curParking;
    }
    public double getLat(){
        return lat;
    }
    public double getLng(){
        return lng;
    }
    public String getColor(){
        return color;
    }
}
