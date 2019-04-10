package com.samad_talukder.countryinfoexclusive.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.Placeholder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.samad_talukder.countryinfoexclusive.R;
import com.samad_talukder.countryinfoexclusive.model.CountryAll;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import okhttp3.internal.Util;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {
    private Context mContext;
    private List<CountryAll> countryAllList;

    public CountryListAdapter(Context mContext, List<CountryAll> countryAllList) {
        this.mContext = mContext;
        this.countryAllList = countryAllList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_country, viewGroup, false);
        return new CountryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int i) {
        final CountryAll countryAll = countryAllList.get(i);
        //Toast.makeText(mContext, ""+countryAllList.get(i).getName(), Toast.LENGTH_SHORT).show();

        Glide.with(mContext)
                .load(countryAll.getFlag())
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_black_24dp))
                .into(countryViewHolder.country_image_iv);
        countryViewHolder.country_name_tv.setText(countryAll.getName());
        countryViewHolder.country_city_tv.setText("City: " + countryAll.getCapital());
        if (countryAll.getLanguages().size() > 0) {
            countryViewHolder.country_languages_tv.setText("Languages: " + countryAll.getLanguages().get(0).getName()+" , "+countryAll.getLanguages().get(0).getNativeName());
        }

        countryViewHolder.country_calling_tv.setText("Calling Code: " + countryAll.getCallingCodes().get(0));
        countryViewHolder.country_lat_tv.setText("Currency: " + countryAll.getCurrencies().get(0).getName() + ": " + countryAll.getCurrencies().get(0).getSymbol());
        //countryViewHolder.country_lat_tv.setText(String.valueOf(countryAll.getLatlng().get(0))+","+String.valueOf(countryAll.getLatlng().get(1)));

    }

    @Override
    public int getItemCount() {
        return countryAllList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        private TextView country_name_tv, country_city_tv, country_lat_tv, country_calling_tv, country_languages_tv;
        private ImageView country_image_iv;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            country_name_tv = itemView.findViewById(R.id.country_name_tv);
            country_city_tv = itemView.findViewById(R.id.country_city_tv);
            country_image_iv = itemView.findViewById(R.id.country_image_iv);
            country_lat_tv = itemView.findViewById(R.id.country_lat_tv);
            country_calling_tv = itemView.findViewById(R.id.country_calling_tv);
            country_languages_tv = itemView.findViewById(R.id.country_languages_tv);
        }
    }
}
