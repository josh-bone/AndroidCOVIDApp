package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SymptomDashboard extends AppCompatActivity {

    Button toComplete;
    ListView listView;
    ArrayList<String> mlist;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_dashboard);
        listView = findViewById(R.id.myListView);
        toComplete = findViewById(R.id.toSymptoms);
        mlist = new ArrayList<String>();

        toComplete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                switchDash();
            }
        });

        //note: final keyword means it can't be assigned more than once
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Symptomatic");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Object isSymp = snapshot.getValue(); //whether or not they are symptomatic
                    if(isSymp.toString().equals("true")) {
                        String name = snapshot.getKey();
                        mlist.add(name);
                    }
                }
                //NOTE: VERY IMPORTANT! This code needs to be here because the API is asynchronous.
                //If we set the adapter back in the onCreate() method, even if it comes after this
                //code, it will execute before the list is populated!!
                //For more info see https://stackoverflow.com/questions/47847694/how-to-return-datasnapshot-value-as-a-result-of-a-method/47853774
                adapter = new ArrayAdapter<String>(SymptomDashboard.this, android.R.layout.simple_list_item_1, mlist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed to read. Error = " + error.getCode());
            }
        });
    }

    private void switchDash(){
        Intent intent = new Intent(SymptomDashboard.this, AdminActivity.class);
        startActivity(intent);
    }
}