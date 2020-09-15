package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.ListView;

public class AdminActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final ListView list = findViewById(R.id.list);
    }
}