package com.app.daeja.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.daeja.R;

public class Intro_Frist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_frist);
    }

    public void onGoBtnClick(View view) {
        // Start the next activity (Intro_Second)
        Intent intent = new Intent(this, Intro_Second.class);
        startActivity(intent);
    }
}