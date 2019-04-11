package com.samad_talukder.countryinfoexclusive.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.Placeholder;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.samad_talukder.countryinfoexclusive.R;
import com.samad_talukder.countryinfoexclusive.model.CountryAll;
import com.samad_talukder.countryinfoexclusive.utils.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import okhttp3.internal.Util;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {
    private Context mContext;
    private List<CountryAll> countryAllList;
    public OnItemClickListener onItemClickListener;

    public CountryListAdapter(Context mContext, List<CountryAll> countryAllList, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.countryAllList = countryAllList;
        this.onItemClickListener = onItemClickListener;
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
        countryViewHolder.bind(countryAllList.get(i), onItemClickListener);
        /*if (countryAll.getLanguages().size() > 0) {
            countryViewHolder.country_languages_tv.setText("Languages: " + countryAll.getLanguages().get(0).getName()+" , "+countryAll.getLanguages().get(0).getNativeName());
        }
        countryViewHolder.country_calling_tv.setText("Calling Code: " + countryAll.getCallingCodes().get(0));
        countryViewHolder.country_lat_tv.setText("Currency: " + countryAll.getCurrencies().get(0).getName() + ": " + countryAll.getCurrencies().get(0).getSymbol());
        countryViewHolder.country_lat_tv.setText(String.valueOf(countryAll.getLatlng().get(0))+","+String.valueOf(countryAll.getLatlng().get(1)));*/

    }

    @Override
    public int getItemCount() {
        return countryAllList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        private CardView countryNameCV;
        private TextView country_name_tv, country_city_tv;
        private ImageView country_image_iv;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameCV = itemView.findViewById(R.id.countryNameCV);
            country_name_tv = itemView.findViewById(R.id.country_name_tv);
            country_city_tv = itemView.findViewById(R.id.country_city_tv);
            country_image_iv = itemView.findViewById(R.id.country_image_iv);

        }

        public void bind(final CountryAll countryAll, final OnItemClickListener onItemClickListener) {
            Glide.with(mContext)
                    .load(countryAll.getFlag())
                    .apply(new RequestOptions().placeholder(R.drawable.worldwide))
                    .into(country_image_iv);
            country_name_tv.setText(countryAll.getName());
            country_city_tv.setText(countryAll.getCapital());
            countryNameCV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(countryAll);
                }
            });
        }
    }
}
