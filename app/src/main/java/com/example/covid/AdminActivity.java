package com.example.covid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//this class pulls info from the database and displays it to admins
public class AdminActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> mlist;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        listView = findViewById(R.id.myListView);
        mlist = new ArrayList<String>();

        //note: final keyword means it can't be assigned more than once
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Completed");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Object completed = snapshot.getValue(); //whether or not they completed the survey
                    if(completed.toString().equals("false")) {
                        String name = snapshot.getKey();
                        mlist.add(name);
                    }
                }
                //NOTE: VERY IMPORTANT! This code needs to be here because the API is asynchronous.
                //If we set the adapter back in the onCreate() method, even if it comes after this
                //code, it will execute before the list is populated!!
                //For more info see https://stackoverflow.com/questions/47847694/how-to-return-datasnapshot-value-as-a-result-of-a-method/47853774
                adapter = new ArrayAdapter<String>(AdminActivity.this, android.R.layout.simple_list_item_1, mlist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Failed to read. Error = " + error.getCode());
            }
        });


    }

}