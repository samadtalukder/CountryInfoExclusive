package com.samad_talukder.countryinfoexclusive.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.samad_talukder.countryinfoexclusive.R;
import com.samad_talukder.countryinfoexclusive.adapter.CountryListAdapter;
import com.samad_talukder.countryinfoexclusive.model.CountryAll;
import com.samad_talukder.countryinfoexclusive.rest.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView countryRV;
    private List<CountryAll> countryAlls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryRV = findViewById(R.id.countryListRV);
        //
        loadAllCountryData();
        //
    }

    private void loadAllCountryData() {
        ApiClient.getClient().getAllCountryList().enqueue(new Callback<List<CountryAll>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryAll>> call, @NonNull Response<List<CountryAll>> response) {
                    countryAlls = response.body();
                    setDataInRecyclerView();

            }

            @Override
            public void onFailure(@NonNull Call<List<CountryAll>> call, @NonNull Throwable throwable) {
                Log.e("Error", throwable.toString());
            }
        });

    }

    private void setDataInRecyclerView() {
        countryRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        countryRV.setAdapter(new CountryListAdapter(this, countryAlls));
    }
}
