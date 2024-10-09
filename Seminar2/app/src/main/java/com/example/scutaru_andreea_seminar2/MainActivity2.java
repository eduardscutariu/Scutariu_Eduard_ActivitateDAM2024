package com.example.scutaru_andreea_seminar2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void Deschide(View view ) {
        Intent it = new Intent(getApplicationContext(), MainActivity3.class);
        startActivity(it);
    }
}