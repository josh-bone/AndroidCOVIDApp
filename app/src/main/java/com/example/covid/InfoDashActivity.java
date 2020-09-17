package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoDashActivity extends AppCompatActivity {

    TextView mText;
    TextView mNumber;
    TextView mDateTime;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_dash);
        mText = findViewById(R.id.totalConfirmed);
        mNumber = findViewById(R.id.confirmedNumber);
        mDateTime = findViewById(R.id.dateText);
        loadData();
    }

    public void loadData(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        covidAPI api = retrofit.create(covidAPI.class);
        Call<APIPlaceholder> call = api.getPosts();
        call.enqueue(new Callback<APIPlaceholder>() {
            @Override
            public void onResponse(Call<APIPlaceholder> call, Response<APIPlaceholder> response) {

                if (response.isSuccessful()) {
                    APIPlaceholder bod = response.body();
                    Toast.makeText(InfoDashActivity.this, "Response Successful!", Toast.LENGTH_SHORT).show();
                    int numConfirmed = bod.getGlobalConfirmed();
                    if(numConfirmed == -1){
                        mNumber.setText("error: API gave null reference");
                    }
                    else {
                        String formatted = NumberFormat.getNumberInstance(Locale.US).format(numConfirmed);
                        mNumber.setText(formatted);
                    }
                    String date = bod.getDate(); //TODO: format this nicely before displaying
                    mDateTime.setText(date);
                } else {
                    Toast.makeText(InfoDashActivity.this, "Response Unsuccessful!", Toast.LENGTH_SHORT).show();
                    mNumber.setText("Code = " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<APIPlaceholder> call, Throwable t) {
                Toast.makeText(InfoDashActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                mNumber.setText(t.getMessage());
            }
        });
    }
}