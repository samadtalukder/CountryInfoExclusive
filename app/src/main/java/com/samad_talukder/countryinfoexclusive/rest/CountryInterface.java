package com.samad_talukder.countryinfoexclusive.rest;

import com.samad_talukder.countryinfoexclusive.model.CountryAll;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryInterface {
    @GET("all")
    Call<List<CountryAll>> getAllCountryList();

}
