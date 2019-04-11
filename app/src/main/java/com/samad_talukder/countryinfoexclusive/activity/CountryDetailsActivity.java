package com.samad_talukder.countryinfoexclusive.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.samad_talukder.countryinfoexclusive.R;

import java.text.DecimalFormat;

public class CountryDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private TextView countryNameTV, capitalNameTV, alphaCodeTV, callingCodeTV, populationTV, nativeLanguageTV, languageNameTV, currenciesCodeTV,
            currenciesNameTV, currenciesSymbolTV, regionTV, subRegionTV, areaTV, giniRatioTV, latitudeTV, longitudeTV, timezoneTV;
    private GoogleMap mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();
        getDataFromIntent();
    }

    private void initView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
        countryNameTV = findViewById(R.id.countryNameTV);
        capitalNameTV = findViewById(R.id.capitalNameTV);
        alphaCodeTV = findViewById(R.id.alphaCodeTV);
        callingCodeTV = findViewById(R.id.callingCodeTV);
        populationTV = findViewById(R.id.populationTV);
        nativeLanguageTV = findViewById(R.id.nativeLanguageTV);
        languageNameTV = findViewById(R.id.languageNameTV);
        currenciesCodeTV = findViewById(R.id.currenciesCodeTV);
        currenciesNameTV = findViewById(R.id.currenciesNameTV);
        currenciesSymbolTV = findViewById(R.id.currenciesSymbolTV);
        regionTV = findViewById(R.id.regionTV);
        subRegionTV = findViewById(R.id.subRegionTV);
        areaTV = findViewById(R.id.areaTV);
        giniRatioTV = findViewById(R.id.giniRatioTV);
        latitudeTV = findViewById(R.id.latitudeTV);
        longitudeTV = findViewById(R.id.longitudeTV);
        timezoneTV = findViewById(R.id.timezoneTV);

    }

    public String formatPopulation(Integer population) {
        String formatArr[] = {"K", "M", "B", "T"};
        int index = 0;
        while ((population / 1000) >= 1) {
            population /= 1000;
            index++;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s %s", decimalFormat.format(population), formatArr[index]);

    }

    private void getDataFromIntent() {
        countryNameTV.setText(getIntent().getStringExtra("country_name"));
        capitalNameTV.setText(getIntent().getStringExtra("country_capital"));
        alphaCodeTV.setText(getIntent().getStringExtra("country_alpha3"));
        callingCodeTV.setText(getIntent().getStringExtra("country_calling_code"));
        //populationTV.setText(getIntent().getStringExtra("country_population"));
        populationTV.setText(formatPopulation(Integer.valueOf(getIntent().getStringExtra("country_population"))));
        nativeLanguageTV.setText(getIntent().getStringExtra("country_native_language"));
        languageNameTV.setText(getIntent().getStringExtra("country_native_name"));
        currenciesCodeTV.setText(getIntent().getStringExtra("country_currencies_code"));
        currenciesNameTV.setText(getIntent().getStringExtra("country_currencies_name"));
        currenciesSymbolTV.setText(getIntent().getStringExtra("country_currencies_symbol"));
        regionTV.setText(getIntent().getStringExtra("country_region"));
        subRegionTV.setText(getIntent().getStringExtra("country_sub_region"));
        areaTV.setText(getIntent().getStringExtra("country_area"));
        giniRatioTV.setText(getIntent().getStringExtra("country_gini"));
        latitudeTV.setText(getIntent().getStringExtra("country_latitude"));
        longitudeTV.setText(getIntent().getStringExtra("country_longitude"));
        timezoneTV.setText(getIntent().getStringExtra("country_timezone"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapView = googleMap;
        mapView.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng countryLatLng = new LatLng(getIntent().getDoubleExtra("country_latitude_map", 0), getIntent().getDoubleExtra("country_longitude_map", 0));
        googleMap.addMarker(new MarkerOptions().position(countryLatLng).title(getIntent().getStringExtra("country_name")));
        mapView.moveCamera(CameraUpdateFactory.newLatLng(countryLatLng));
    }


}
