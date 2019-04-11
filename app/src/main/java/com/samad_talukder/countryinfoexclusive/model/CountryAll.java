
package com.samad_talukder.countryinfoexclusive.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryAll {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("population")
    @Expose
    private Integer population;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("subregion")
    @Expose
    private String subregion;

    @SerializedName("area")
    @Expose
    private Double area;
    @SerializedName("gini")
    @Expose
    private Double gini;
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
    @SerializedName("regionalBlocs")
    @Expose
    private List<RegionalBloc> regionalBlocs = null;
    @SerializedName("timezones")
    @Expose
    private List<String> timezones = null;

    public String getName() {
        return name;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String getCapital() {
        return capital;
    }
    public String getFlag() {
        return flag;
    }

    public Integer getPopulation() {
        return population;
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

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public Double getArea() {
        return area;
    }

    public Double getGini() {
        return gini;
    }

    public List<RegionalBloc> getRegionalBlocs() {
        return regionalBlocs;
    }

    public List<String> getTimezones() {
        return timezones;
    }
}
