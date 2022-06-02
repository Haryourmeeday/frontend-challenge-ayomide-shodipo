package com.planatechnologies.androidchallenge.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.textview.MaterialTextView;
import com.planatechnologies.androidchallenge.Models.Countries;
import com.planatechnologies.androidchallenge.R;

import java.util.ArrayList;

public class CountryAdapter extends ArrayAdapter<Countries> {

    public CountryAdapter(Context context, ArrayList<Countries> countries) {
        super(context, R.layout.country_list_view, countries);
    }

    private static class ViewHolder {
        MaterialTextView country;
        MaterialTextView countrycode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Countries countries = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.country_list_view, parent, false);
            viewHolder.country =  convertView.findViewById(R.id.countryName);
            viewHolder.countrycode = convertView.findViewById(R.id.countryCode);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.country.setText(countries.countryName);
        viewHolder.countrycode.setText(countries.countryCode);

        // Return the completed view to render on screen
        return convertView;
    }

}
