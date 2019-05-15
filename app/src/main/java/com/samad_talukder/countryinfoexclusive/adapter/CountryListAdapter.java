package com.samad_talukder.countryinfoexclusive.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.Placeholder;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ViewTarget;
import com.samad_talukder.countryinfoexclusive.R;
import com.samad_talukder.countryinfoexclusive.activity.CountryDetailsActivity;
import com.samad_talukder.countryinfoexclusive.activity.MainActivity;
import com.samad_talukder.countryinfoexclusive.model.CountryAll;
import com.samad_talukder.countryinfoexclusive.utils.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.Util;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> implements Filterable {
    private Context mContext;
    private List<CountryAll> countryAllList;
    private List<CountryAll> countryAllFilterList;


    public CountryListAdapter(Context mContext, List<CountryAll> countryAllList) {
        this.mContext = mContext;
        this.countryAllList = countryAllList;
        this.countryAllFilterList = countryAllList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_country, viewGroup, false);
        return new CountryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, final int i) {
        final CountryAll countryAll = countryAllList.get(i);
        Glide.with(mContext)
                .load(countryAll.getFlag())
                .apply(new RequestOptions().placeholder(R.drawable.worldwide))
                .into(countryViewHolder.country_image_iv);
        countryViewHolder.country_name_tv.setText(countryAll.getName());
        countryViewHolder.country_city_tv.setText(countryAll.getCapital());
        countryViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                // Snackbar.make(view, countryAll.getName(), Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, CountryDetailsActivity.class);
                intent.putExtra("country_name", countryAll.getName());
                intent.putExtra("country_capital", countryAll.getCapital());
                intent.putExtra("country_alpha3", countryAll.getAlpha3Code());
                intent.putExtra("country_calling_code", countryAll.getCallingCodes().get(0));
                intent.putExtra("country_population", String.valueOf(countryAll.getPopulation()));
                intent.putExtra("country_native_language", String.valueOf(countryAll.getLanguages().get(0).getNativeName()));
                intent.putExtra("country_native_name", String.valueOf(countryAll.getLanguages().get(0).getName()));
                intent.putExtra("country_currencies_code", String.valueOf(countryAll.getCurrencies().get(0).getCode()));
                intent.putExtra("country_currencies_name", String.valueOf(countryAll.getCurrencies().get(0).getName()));
                intent.putExtra("country_currencies_symbol", String.valueOf(countryAll.getCurrencies().get(0).getSymbol()));
                intent.putExtra("country_region", countryAll.getRegion());
                intent.putExtra("country_sub_region", countryAll.getSubregion());
                intent.putExtra("country_area", String.valueOf(countryAll.getArea()));
                if (countryAll.getGini() != null) {
                    intent.putExtra("country_gini", String.valueOf(countryAll.getGini()));
                } else {
                    intent.putExtra("country_gini", "");
                }
                if (countryAll.getRegionalBlocs().size() > 0) {
                    intent.putExtra("country_acronym", countryAll.getRegionalBlocs().get(0).getName());
                } else {
                    intent.putExtra("country_acronym", "");
                }
                intent.putExtra("country_latitude", String.valueOf(countryAll.getLatlng().get(0)));
                intent.putExtra("country_longitude", String.valueOf(countryAll.getLatlng().get(1)));
                intent.putExtra("country_latitude_map", countryAll.getLatlng().get(0));
                intent.putExtra("country_longitude_map", countryAll.getLatlng().get(1));
                intent.putExtra("country_timezone", String.valueOf(countryAll.getTimezones().get(0)));

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return countryAllFilterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charStr = constraint.toString();
                if (charStr.isEmpty()) {
                    countryAllFilterList = countryAllList;
                } else {
                    List<CountryAll> countryFilter = new ArrayList<>();
                    for (CountryAll countryAll : countryAllList) {
                        if (countryAll.getName().toLowerCase().contains(charStr.toLowerCase())) {
                            countryFilter.add(countryAll);
                        }
                    }
                    countryAllFilterList = countryFilter;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = countryAllFilterList;
                filterResults.count=countryAllFilterList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryAllList = (ArrayList<CountryAll>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView country_name_tv, country_city_tv;
        private ImageView country_image_iv;
        private ItemClickListener itemClickListener;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);

            country_name_tv = itemView.findViewById(R.id.country_name_tv);
            country_city_tv = itemView.findViewById(R.id.country_city_tv);
            country_image_iv = itemView.findViewById(R.id.country_image_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClickListener(view, getLayoutPosition());
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

    }
}
