package com.planatechnologies.androidchallenge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.textview.MaterialTextView;
import com.planatechnologies.androidchallenge.Models.Countries;
import com.planatechnologies.androidchallenge.Models.Holidays;
import com.planatechnologies.androidchallenge.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class HolidayAdapter extends ArrayAdapter<Holidays> {


    public HolidayAdapter(Context context, ArrayList<Holidays> holidays) {
        super(context, R.layout.holiday_list_view, holidays);
    }

    private static class ViewHolder {
        MaterialTextView holiday;
        MaterialTextView holidayDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Holidays holidays = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.holiday_list_view, parent, false);
            viewHolder.holiday =  convertView.findViewById(R.id.holidayName);
            viewHolder.holidayDate = convertView.findViewById(R.id.holidayDate);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.holiday.setText(holidays.holidayName);
        viewHolder.holidayDate.setText(holidays.holidayDate);



        // Return the completed view to render on screen
        return convertView;
    }

}
