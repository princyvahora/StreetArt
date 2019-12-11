package com.example.streetartapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Art_data {
    String art_name,art_style,art_date,art_summary,art_address,art_cname;
    int art_id,art_period;

    public Art_data(int art_id, String art_name, String art_style,int art_period, String art_date, String art_summary, String art_address, String art_cname) {
        this.art_id = art_id;
        this.art_name = art_name;
        this.art_style = art_style;
        this.art_period = art_period;
        this.art_date = art_date;
        this.art_summary = art_summary;
        this.art_address = art_address;
        this.art_cname = art_cname;
    }

public int getArt_id() {
        return art_id;
    }

public String getArt_name(){
        return art_name;
    }

public String getArt_style(){
        return art_style;
    }

public String getArt_date(){
        return art_date;
    }

public String getArt_summary(){
        return art_summary;
    }

public String getArt_address(){
        return art_address;
    }

public String getArt_cname(){
        return art_cname;
    }

public int getArt_period() {
        return art_period;
    }
}
