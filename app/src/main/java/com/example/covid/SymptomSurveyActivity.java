package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.Queue;


//this class displays the survey, and sends the results to the database
public class SymptomSurveyActivity extends AppCompatActivity {

    private Button mSubmitBtn;
    private FirebaseDatabase database;
    private Bundle extras;

    public int numSymptoms;
    public Queue symptomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_survey);
        extras = getIntent().getExtras();
        mSubmitBtn = findViewById(R.id.button);
        mSubmitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                submitData();
            }
        });
    }

    private void submitData(){
        database = FirebaseDatabase.getInstance();
        String nameKey = extras.getString("DISPLAY_NAME");
        //stores the number of symptoms that "person" under Users/person
        DatabaseReference symRef = database.getReference("Users/"+nameKey+"/symptoms");
        symRef.setValue(numSymptoms);
        Date curTime = Calendar.getInstance().getTime();
        DatabaseReference timeRef = database.getReference("Users/"+nameKey+"/TimeCompleted");
        timeRef.setValue(curTime);
    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            numSymptoms++;
            //symptomList.add(view.getId());
        }
        else{
            //in case the box is unchecked
            numSymptoms--;
            //symptomList.remove(view.getId());
        }
    }

}