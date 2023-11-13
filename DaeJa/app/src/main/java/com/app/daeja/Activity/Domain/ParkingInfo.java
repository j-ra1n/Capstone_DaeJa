package com.app.daeja.Activity.Domain;

import java.util.ArrayList;

public class ParkingInfo {
    private Integer parkingId;
    private String parkingCodeTxt;
    private String parkingNameTxt;
    private String parkingStateTxt;
    private Integer totalParking;
    private Integer currentParking;
    private String parkingPriceTxt;
    private Double latitude;
    private Double longitude;
    private String rgb;
    //private ArrayList<Integer> lineData;

    public ParkingInfo() {
        this.parkingId = 0;
        this.parkingCodeTxt = "";
        this.parkingNameTxt = "";
        this.parkingStateTxt = "";
        this.totalParking = 0;
        this.currentParking = 0;
        this.parkingPriceTxt = "";
        this.latitude = 0.0;
        this.longitude = 0.0;
        this.rgb = "";
        //this.lineData = null;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    public ParkingInfo(int parkingId, String parkingCodeTxt, String parkingNameTxt, String parkingStateTxt, Integer totalParking, Integer currentParking, String parkingPriceTxt, Double latitude, Double longitude, ArrayList<Integer> lineData, String rgb) {
        this.parkingId = parkingId;
        this.parkingCodeTxt = parkingCodeTxt;
        this.parkingNameTxt = parkingNameTxt;
        this.parkingStateTxt = parkingStateTxt;
        this.totalParking = totalParking;
        this.currentParking = currentParking;
        this.parkingPriceTxt = parkingPriceTxt;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.lineData = lineData;
        this.rgb = rgb;
    }

    public Integer getId() {
        return parkingId;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingCodeTxt() {
        return parkingCodeTxt;
    }

    public void setParkingCodeTxt(String parkingCodeTxt) {
        this.parkingCodeTxt = parkingCodeTxt;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setId(Integer parkingId){
        this.parkingId = parkingId;
    }

    public String getParkingNameTxt() {

        return parkingNameTxt;
    }

    public void setParkingNameTxt(String parkingNameTxt) {
        this.parkingNameTxt = parkingNameTxt;
    }

    public String getParkingStateTxt() {
        return parkingStateTxt;
    }

    public void setParkingStateTxt(String parkingStateTxt) {
        this.parkingStateTxt = parkingStateTxt;
    }

    public Integer getTotalParking() {
        return totalParking;
    }

    public void setTotalParking(Integer totalParking) {
        this.totalParking = totalParking;
    }

    public Integer getCurrentParking() {
        return currentParking;
    }

    public void setCurrentParking(Integer currentParking) {
        this.currentParking = currentParking;
    }

    public String getParkingPriceTxt() {
        return parkingPriceTxt;
    }

    public void setParkingPriceTxt(String parkingPriceTxt) {
        this.parkingPriceTxt = parkingPriceTxt;
    }

//    public ArrayList<Integer> getLineData() {
//        return lineData;
//    }
//
//    public void setLineData(ArrayList<Integer> lineData) {
//        this.lineData = lineData;
//    }
}
