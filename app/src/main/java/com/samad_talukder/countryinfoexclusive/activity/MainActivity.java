package com.samad_talukder.countryinfoexclusive.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.samad_talukder.countryinfoexclusive.utils.OnItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView countryRV;
    private List<CountryAll> countryAlls;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryRV = findViewById(R.id.countryListRV);
        refreshLayout = findViewById(R.id.swipeDataRefresh);
        /* Swipe Data Refresh */
        refreshLayout.setOnRefreshListener(this);
        /* Load All Country Data From API */
        loadAllCountryData();

    }

    private void loadAllCountryData() {
        refreshLayout.setRefreshing(true);
        ApiClient.getClient().getAllCountryList().enqueue(new Callback<List<CountryAll>>() {
            @Override
            public void onResponse(@NonNull Call<List<CountryAll>> call, @NonNull Response<List<CountryAll>> response) {
                refreshLayout.setRefreshing(false);
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
        countryRV.setAdapter(new CountryListAdapter(this, countryAlls, new OnItemClickListener() {
            @Override
            public void onItemClick(CountryAll countryAllItem) {
                Intent intent = new Intent(MainActivity.this, CountryDetailsActivity.class);
                intent.putExtra("country_name", countryAllItem.getName());
                intent.putExtra("country_capital", countryAllItem.getCapital());
                intent.putExtra("country_alpha3", countryAllItem.getAlpha3Code());
                intent.putExtra("country_calling_code", countryAllItem.getCallingCodes().get(0));
                intent.putExtra("country_population", String.valueOf(countryAllItem.getPopulation()));
                intent.putExtra("country_native_language", String.valueOf(countryAllItem.getLanguages().get(0).getNativeName()));
                intent.putExtra("country_native_name", String.valueOf(countryAllItem.getLanguages().get(0).getName()));
                intent.putExtra("country_currencies_code", String.valueOf(countryAllItem.getCurrencies().get(0).getCode()));
                intent.putExtra("country_currencies_name", String.valueOf(countryAllItem.getCurrencies().get(0).getName()));
                intent.putExtra("country_currencies_symbol", String.valueOf(countryAllItem.getCurrencies().get(0).getSymbol()));
                intent.putExtra("country_region", countryAllItem.getRegion());
                intent.putExtra("country_sub_region", countryAllItem.getSubregion());
                intent.putExtra("country_area", String.valueOf(countryAllItem.getArea()));
                if (countryAllItem.getGini() != null) {
                    intent.putExtra("country_gini", String.valueOf(countryAllItem.getGini()));
                } else {
                    intent.putExtra("country_gini", "");
                }
                if (countryAllItem.getRegionalBlocs().size() > 0) {
                    intent.putExtra("country_acronym", countryAllItem.getRegionalBlocs().get(0).getName());
                } else {
                    intent.putExtra("country_acronym", "");
                }

                intent.putExtra("country_latitude", String.valueOf(countryAllItem.getLatlng().get(0)));
                intent.putExtra("country_longitude", String.valueOf(countryAllItem.getLatlng().get(1)));

                intent.putExtra("country_latitude_map", countryAllItem.getLatlng().get(0));
                intent.putExtra("country_longitude_map", countryAllItem.getLatlng().get(1));
                intent.putExtra("country_timezone", String.valueOf(countryAllItem.getTimezones().get(0)));

                startActivity(intent);
            }
        }));
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
        loadAllCountryData();
    }
}
