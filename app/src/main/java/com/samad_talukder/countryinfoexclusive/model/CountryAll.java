
package com.samad_talukder.countryinfoexclusive.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryAll {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("latlng")
    @Expose
    private List<Double> latlng = null;
    @SerializedName("currencies")
    @Expose
    private List<Currency> currencies = null;
    @SerializedName("callingCodes")
    @Expose
    private List<String> callingCodes = null;
    @SerializedName("languages")
    @Expose
    private List<Language> languages = null;

    public String getName() {
        return name;
    }
    public String getCapital() {
        return capital;
    }
    public String getFlag() {
        return flag;
    }
    public List<Double> getLatlng() {
        return latlng;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public List<Language> getLanguages() {
        return languages;
    }
}
