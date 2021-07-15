package com.horseman.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView cases,tvTodayCases,tvDeaths,tvTodayDeaths,tvRecovered,tvActive,tvCritical;

    private int positionCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of "+AffectedCountry.countryModelList.get(positionCountry).getCountry());

        cases = findViewById(R.id.tvCases1);
        tvTodayCases = findViewById(R.id.tvTodayCases1);
        tvDeaths = findViewById(R.id.deaths1);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths1);
        tvRecovered = findViewById(R.id.tvRecovered1);
        tvActive = findViewById(R.id.tvActive1);
        tvCritical = findViewById(R.id.tvCritical1);

        cases.setText(AffectedCountry.countryModelList.get(positionCountry).getCases());
        tvTodayCases.setText(AffectedCountry.countryModelList.get(positionCountry).getTodaycases());
        tvDeaths.setText(AffectedCountry.countryModelList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(AffectedCountry.countryModelList.get(positionCountry).getTodaydeaths());
        tvRecovered.setText(AffectedCountry.countryModelList.get(positionCountry).getRecovered());
        tvActive.setText(AffectedCountry.countryModelList.get(positionCountry).getActive());
        tvCritical.setText(AffectedCountry.countryModelList.get(positionCountry).getCritical());

    }
}