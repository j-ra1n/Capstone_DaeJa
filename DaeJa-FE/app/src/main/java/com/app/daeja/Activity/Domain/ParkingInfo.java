package com.app.daeja.Activity.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParkingInfo implements Serializable {
    @SerializedName("id")
    @Expose
    Integer ID;
    @SerializedName("parking_CODE")
    @Expose
    Integer PARKING_CODE;
    @SerializedName("parking_NAME")
    @Expose
    String PARKING_NAME;
    @SerializedName("parking_ADDR")
    @Expose
    String PARKING_ADDR;
    @SerializedName("parking_TYPE_NM")
    @Expose
    String parking_TYPE_NM; //노외(NW) or 노상(NS)
    @SerializedName("operation_RULE_NM")
    @Expose
    String operation_RULE_NM; //시간제 주차장 or 버스전용 주차장 or 시간제+버스전용 주차장
    @SerializedName("tel")
    @Expose
    String TEL;
    @SerializedName("que_STATUS")
    @Expose
    boolean que_STATUS; //제공 true, 미제공 false
    @SerializedName("capacity")
    @Expose
    Integer capacity;
    @SerializedName("cur_PARKING")
    @Expose
    Integer cur_PARKING;
    @SerializedName("cur_PARKING_TIME")
    @Expose
    String cur_PARKING_TIME;
    @SerializedName("pay_NM")
    @Expose
    String pay_NM;
    @SerializedName("night_FREE_OPEN_NM")
    @Expose
    String night_FREE_OPEN_NM;
    @SerializedName("weekday_BEGIN_TIME")
    @Expose
    String weekday_BEGIN_TIME; //HH:MM
    @SerializedName("weekday_END_TIME")
    @Expose
    String weekday_END_TIME; //HH:MM
    @SerializedName("weekend_BEGIN_TIME")
    @Expose
    String weekend_BEGIN_TIME; //HH:MM
    @SerializedName("weekend_END_TIME")
    @Expose
    String weekend_END_TIME; //HH:MM
    @SerializedName("holyday_BEGIN_TIME")
    @Expose
    String holyday_BEGIN_TIME; //HH:MM
    @SerializedName("holyday_END_TIME")
    @Expose
    String holyday_END_TIME; //HH:MM
    @SerializedName("saturday_PAY_NM")
    @Expose
    String saturday_PAY_NM;
    @SerializedName("holyday_PAY_NM")
    @Expose
    String holyday_PAY_NM;
    @SerializedName("fulltime_MONTHLY")
    @Expose
    Integer fulltime_MONTHLY;
    @SerializedName("rates")
    @Expose
    String rates;
    @SerializedName("time_RATE")
    @Expose
    String time_RATE;
    @SerializedName("add_RATES")
    @Expose
    String add_RATES;
    @SerializedName("add_TIME_RATE")
    @Expose
    String add_TIME_RATE;
    @SerializedName("day_MAXIMUM")
    @Expose
    Integer day_MAXIMUM;
    @SerializedName("lat")
    @Expose
    Double lat;
    @SerializedName("lng")
    @Expose
    Double lng;
    @SerializedName("sh_CO")
    @Expose
    String sh_CO;
    @SerializedName("sh_LINK")
    @Expose
    String sh_LINK;
    @SerializedName("sh_TYPE")
    @Expose
    boolean sh_TYPE; // (true:공유주차장 false:공유주차장 아님)
    @SerializedName("color")
    @Expose
    String color; // "많음", "보통", "적음"

    public ParkingInfo() {
    }

    public ParkingInfo(Integer ID, Integer PARKING_CODE, String PARKING_NAME, String PARKING_ADDR, String parking_TYPE_NM, String operation_RULE_NM, String TEL, boolean que_STATUS, Integer capacity, Integer cur_PARKING, String cur_PARKING_TIME, String pay_NM, String night_FREE_OPEN_NM, String weekday_BEGIN_TIME, String weekday_END_TIME, String weekend_BEGIN_TIME, String weekend_END_TIME, String holyday_BEGIN_TIME, String holyday_END_TIME, String saturday_PAY_NM, String holyday_PAY_NM, Integer fulltime_MONTHLY, String rates, String time_RATE, String add_RATES, String add_TIME_RATE, Integer day_MAXIMUM, Double lat, Double lng, String sh_CO, String sh_LINK, boolean sh_TYPE, String color) {
        this.ID = ID;
        this.PARKING_CODE = PARKING_CODE;
        this.PARKING_NAME = PARKING_NAME;
        this.PARKING_ADDR = PARKING_ADDR;
        this.parking_TYPE_NM = parking_TYPE_NM;
        this.operation_RULE_NM = operation_RULE_NM;
        this.TEL = TEL;
        this.que_STATUS = que_STATUS;
        this.capacity = capacity;
        this.cur_PARKING = cur_PARKING;
        this.cur_PARKING_TIME = cur_PARKING_TIME;
        this.pay_NM = pay_NM;
        this.night_FREE_OPEN_NM = night_FREE_OPEN_NM;
        this.weekday_BEGIN_TIME = weekday_BEGIN_TIME;
        this.weekday_END_TIME = weekday_END_TIME;
        this.weekend_BEGIN_TIME = weekend_BEGIN_TIME;
        this.weekend_END_TIME = weekend_END_TIME;
        this.holyday_BEGIN_TIME = holyday_BEGIN_TIME;
        this.holyday_END_TIME = holyday_END_TIME;
        this.saturday_PAY_NM = saturday_PAY_NM;
        this.holyday_PAY_NM = holyday_PAY_NM;
        this.fulltime_MONTHLY = fulltime_MONTHLY;
        this.rates = rates;
        this.time_RATE = time_RATE;
        this.add_RATES = add_RATES;
        this.add_TIME_RATE = add_TIME_RATE;
        this.day_MAXIMUM = day_MAXIMUM;
        this.lat = lat;
        this.lng = lng;
        this.sh_CO = sh_CO;
        this.sh_LINK = sh_LINK;
        this.sh_TYPE = sh_TYPE;
        this.color = color;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getPARKING_CODE() {
        return PARKING_CODE;
    }

    public void setPARKING_CODE(Integer PARKING_CODE) {
        this.PARKING_CODE = PARKING_CODE;
    }

    public String getPARKING_NAME() {
        return PARKING_NAME;
    }

    public void setPARKING_NAME(String PARKING_NAME) {
        this.PARKING_NAME = PARKING_NAME;
    }

    public String getPARKING_ADDR() {
        return PARKING_ADDR;
    }

    public void setPARKING_ADDR(String PARKING_ADDR) {
        this.PARKING_ADDR = PARKING_ADDR;
    }

    public String getParking_TYPE_NM() {
        return parking_TYPE_NM;
    }

    public void setParking_TYPE_NM(String parking_TYPE_NM) {
        this.parking_TYPE_NM = parking_TYPE_NM;
    }

    public String getOperation_RULE_NM() {
        return operation_RULE_NM;
    }

    public void setOperation_RULE_NM(String operation_RULE_NM) {
        this.operation_RULE_NM = operation_RULE_NM;
    }

    public String getTel() {
        return TEL;
    }

    public void setTel(String tel) {
        this.TEL = tel;
    }

    public boolean isQue_STATUS() {
        return que_STATUS;
    }

    public void setQue_STATUS(boolean que_STATUS) {
        this.que_STATUS = que_STATUS;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCur_PARKING() {
        return cur_PARKING;
    }

    public void setCur_PARKING(Integer cur_PARKING) {
        this.cur_PARKING = cur_PARKING;
    }

    public String getCur_PARKING_TIME() {
        return cur_PARKING_TIME;
    }

    public void setCur_PARKING_TIME(String cur_PARKING_TIME) {
        this.cur_PARKING_TIME = cur_PARKING_TIME;
    }

    public String getPay_NM() {
        return pay_NM;
    }

    public void setPay_NM(String pay_NM) {
        this.pay_NM = pay_NM;
    }

    public String getNight_FREE_OPEN_NM() {
        return night_FREE_OPEN_NM;
    }

    public void setNight_FREE_OPEN_NM(String night_FREE_OPEN_NM) {
        this.night_FREE_OPEN_NM = night_FREE_OPEN_NM;
    }

    public String getWeekday_BEGIN_TIME() {
        return weekday_BEGIN_TIME;
    }

    public void setWeekday_BEGIN_TIME(String weekday_BEGIN_TIME) {
        this.weekday_BEGIN_TIME = weekday_BEGIN_TIME;
    }

    public String getWeekday_END_TIME() {
        return weekday_END_TIME;
    }

    public void setWeekday_END_TIME(String weekday_END_TIME) {
        this.weekday_END_TIME = weekday_END_TIME;
    }

    public String getWeekend_BEGIN_TIME() {
        return weekend_BEGIN_TIME;
    }

    public void setWeekend_BEGIN_TIME(String weekend_BEGIN_TIME) {
        this.weekend_BEGIN_TIME = weekend_BEGIN_TIME;
    }

    public String getWeekend_END_TIME() {
        return weekend_END_TIME;
    }

    public void setWeekend_END_TIME(String weekend_END_TIME) {
        this.weekend_END_TIME = weekend_END_TIME;
    }

    public String getHolyday_BEGIN_TIME() {
        return holyday_BEGIN_TIME;
    }

    public void setHolyday_BEGIN_TIME(String holyday_BEGIN_TIME) {
        this.holyday_BEGIN_TIME = holyday_BEGIN_TIME;
    }

    public String getHolyday_END_TIME() {
        return holyday_END_TIME;
    }

    public void setHolyday_END_TIME(String holyday_END_TIME) {
        this.holyday_END_TIME = holyday_END_TIME;
    }

    public String getSaturday_PAY_NM() {
        return saturday_PAY_NM;
    }

    public void setSaturday_PAY_NM(String saturday_PAY_NM) {
        this.saturday_PAY_NM = saturday_PAY_NM;
    }

    public String getHolyday_PAY_NM() {
        return holyday_PAY_NM;
    }

    public void setHolyday_PAY_NM(String holyday_PAY_NM) {
        this.holyday_PAY_NM = holyday_PAY_NM;
    }

    public Integer getFulltime_MONTHLY() {
        return fulltime_MONTHLY;
    }

    public void setFulltime_MONTHLY(Integer fulltime_MONTHLY) {
        this.fulltime_MONTHLY = fulltime_MONTHLY;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getTime_RATE() {
        return time_RATE;
    }

    public void setTime_RATE(String time_RATE) {
        this.time_RATE = time_RATE;
    }

    public String getAdd_RATES() {
        return add_RATES;
    }

    public void setAdd_RATES(String add_RATES) {
        this.add_RATES = add_RATES;
    }

    public String getAdd_TIME_RATE() {
        return add_TIME_RATE;
    }

    public void setAdd_TIME_RATE(String add_TIME_RATE) {
        this.add_TIME_RATE = add_TIME_RATE;
    }

    public Integer getDay_MAXIMUM() {
        return day_MAXIMUM;
    }

    public void setDay_MAXIMUM(Integer day_MAXIMUM) {
        this.day_MAXIMUM = day_MAXIMUM;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getSh_CO() {
        return sh_CO;
    }

    public void setSh_CO(String sh_CO) {
        this.sh_CO = sh_CO;
    }

    public String getSh_LINK() {
        return sh_LINK;
    }

    public void setSh_LINK(String sh_LINK) {
        this.sh_LINK = sh_LINK;
    }

    public boolean isSh_TYPE() {
        return sh_TYPE;
    }

    public void setSh_TYPE(boolean sh_TYPE) {
        this.sh_TYPE = sh_TYPE;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
