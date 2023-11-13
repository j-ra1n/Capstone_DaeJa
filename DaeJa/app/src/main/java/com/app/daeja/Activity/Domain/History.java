package com.app.daeja.Activity.Domain;

import java.util.ArrayList;

public class History {
    private String parkingNameTxt;
    private String parkingStateTxt;
    private Integer totalParking;
    private Integer currentParking;
    private String parkingPriceTxt;
    private ArrayList<Integer> lineData;

    public History(String parkingNameTxt, String parkingStateTxt, Integer totalParking, Integer currentParking, String parkingPriceTxt, ArrayList<Integer> lineData) {
        this.parkingNameTxt = parkingNameTxt;
        this.parkingStateTxt = parkingStateTxt;
        this.totalParking = totalParking;
        this.currentParking = currentParking;
        this.parkingPriceTxt = parkingPriceTxt;
        this.lineData = lineData;
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

    public ArrayList<Integer> getLineData() {
        return lineData;
    }

    public void setLineData(ArrayList<Integer> lineData) {
        this.lineData = lineData;
    }
}
