package com.samad_talukder.countryinfoexclusive.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.samad_talukder.countryinfoexclusive.R;
import com.samad_talukder.countryinfoexclusive.adapter.CountryListAdapter;
import com.samad_talukder.countryinfoexclusive.model.CountryAll;
import com.samad_talukder.countryinfoexclusive.rest.ApiClient;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView countryRV;
    private List<CountryAll> countryAlls;
    private SwipeRefreshLayout refreshLayout;
    private SearchView searchView;
    private CountryListAdapter countryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryAlls = new ArrayList<>();
        countryRV = findViewById(R.id.countryListRV);
        refreshLayout = findViewById(R.id.swipeDataRefresh);
        /* Swipe Data Refresh */
        refreshLayout.setOnRefreshListener(this);
        countryRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
                Log.e("Response: ", String.valueOf(response.body().size()));
                countryListAdapter = new CountryListAdapter(MainActivity.this, countryAlls);
                countryRV.setAdapter(countryListAdapter);
                countryListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(@NonNull Call<List<CountryAll>> call, @NonNull Throwable throwable) {
                Log.e("Error", throwable.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                countryListAdapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                countryListAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Refreshing...", Toast.LENGTH_SHORT).show();
        loadAllCountryData();
    }
}
