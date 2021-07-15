package com.horseman.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodaycases,tvTodaydeaths,tvTotaldeaths,tvAffectedCountries;
    ScrollView scrollView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvActive = findViewById(R.id.tvActive);
        tvCritical = findViewById(R.id.tvCritical);
        tvTodaycases = findViewById(R.id.tvTodayCases);
        tvTodaydeaths = findViewById(R.id.tvTodayDeaths);
        tvTotaldeaths = findViewById(R.id.tvTotalDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);

        scrollView = findViewById(R.id.scrollStats);
        pieChart = findViewById(R.id.piechart);

        fetchData();

    }

    private void fetchData() {
        String url = "https://corona.lmao.ninja/v2/all";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodaycases.setText(jsonObject.getString("todayCases"));
                            tvTotaldeaths.setText(jsonObject.getString("deaths"));
                            tvTodaydeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvTotaldeaths.getText().toString()), Color.parseColor("#EF5350")));
                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
                            pieChart.startAnimation();

                            scrollView.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Check Internet Connectivity", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void goTrackCountries(View view){
        startActivity(new Intent(MainActivity.this,AffectedCountry.class));
}
}