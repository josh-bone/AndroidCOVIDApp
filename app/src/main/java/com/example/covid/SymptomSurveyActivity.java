package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SymptomSurveyActivity extends AppCompatActivity {

    public int numSymptoms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_survey);
    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        if(checked){
            numSymptoms++;
        }
        else{
            //in case the box is unchecked
            numSymptoms--;
        }
    }

}