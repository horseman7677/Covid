package com.horseman.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountry extends AppCompatActivity {
    EditText editSearch;
    ListView listView;

    public static List<CountryModel> countryModelList = new ArrayList<>();
    CountryModel countryModel;
    CustomAdapter myCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_country);

        getSupportActionBar().setTitle("Affected Countrie");

        editSearch = findViewById(R.id.editSearch);
        listView = findViewById(R.id.listView);

        fetchData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getApplicationContext(),DetailActivity.class).putExtra("position",position));
            }
        });

        // search 1part
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myCustomAdapter.getFilter().filter(s);
                myCustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void fetchData(){

        String url = "https://corona.lmao.ninja/v2/countries/";

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String countryName = jsonObject.getString("country");
                                String cases = jsonObject.getString("cases");
                                String todaycases = jsonObject.getString("todayCases");
                                String deaths = jsonObject.getString("deaths");
                                String todaydeaths = jsonObject.getString("todayDeaths");
                                String Recovered = jsonObject.getString("recovered");
                                String Active = jsonObject.getString("active");
                                String Critical = jsonObject.getString("critical");

                                JSONObject object = jsonObject.getJSONObject("countryInfo");
                                String flag = object.getString("flag");

                                countryModel = new CountryModel(flag,countryName,cases,todaycases,deaths,todaydeaths,Recovered,Active,Critical);
                                countryModelList.add(countryModel);

                            }
                            myCustomAdapter = new CustomAdapter(AffectedCountry.this,countryModelList);
                            listView.setAdapter(myCustomAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AffectedCountry.this, "Check Internet Connectivity", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}