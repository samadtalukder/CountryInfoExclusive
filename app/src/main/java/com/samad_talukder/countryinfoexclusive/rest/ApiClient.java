package com.samad_talukder.countryinfoexclusive.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://restcountries.eu/rest/v2/";
    private static Retrofit retrofit = null;

    public static CountryInterface getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CountryInterface.class);
    }

}
