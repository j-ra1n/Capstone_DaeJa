package com.app.daeja.Activity.Domain;

public class Location {
    private String name;
    private String subName;
    private String address;
    private String tel;
    private double lat;
    private double lng;

    public Location(String name, String subName, String address, String tel, double lat, double lng) {
        this.name = name;
        this.subName = subName;
        this.address = address;
        this.tel = tel;
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
