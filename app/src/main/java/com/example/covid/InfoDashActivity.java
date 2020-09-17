package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    Button mToLogin;
    TextView mNumDeaths;
    TextView mNumRec;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_dash);
        mText = findViewById(R.id.confirmedTitle);
        mNumber = findViewById(R.id.confirmedNumber);
        mNumDeaths = findViewById(R.id.numDeaths);
        mNumRec = findViewById(R.id.numRecovered);
        mToLogin = findViewById(R.id.toLogin);
        mDateTime = findViewById(R.id.dateText);
        mToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goToMain();
            }
        });
        loadData();
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
                    int numDeaths = bod.getGlobalDeaths();
                    int numRecovered = bod.getGlobalRecovered();
                    if(numConfirmed == 0 && numDeaths == 0 && numRecovered == 0){
                        Toast.makeText(InfoDashActivity.this, "YOU HAVE HIT THE RATE-LIMIT. SUBSCRIBE AT COVID-19API.COM", Toast.LENGTH_LONG).show();
                    }
                    if(numConfirmed == -1){
                        mNumber.setText("error: API gave null reference");
                    }
                    else {
                        String formatted = NumberFormat.getNumberInstance(Locale.US).format(numConfirmed);
                        mNumber.setText(formatted);
                    }

                    String fRec = NumberFormat.getNumberInstance(Locale.US).format(numDeaths);
                    mNumRec.setText(fRec);

                    String fDeaths = NumberFormat.getNumberInstance(Locale.US).format(numRecovered);
                    mNumDeaths.setText(fDeaths);

                    String date = bod.getDate(); //TODO: format this nicely before displaying
                    mDateTime.setText(date.substring(5,10));

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