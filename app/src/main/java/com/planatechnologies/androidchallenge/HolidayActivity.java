package com.planatechnologies.androidchallenge;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.planatechnologies.androidchallenge.Adapters.HolidayAdapter;
import com.planatechnologies.androidchallenge.Helpers.APIClient;
import com.planatechnologies.androidchallenge.Helpers.Helper;
import com.planatechnologies.androidchallenge.Models.Holidays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class HolidayActivity extends AppCompatActivity {
    AppCompatImageView back;
    View container;
    ListView holiday_view;
    HolidayAdapter adapter;
    String countryName,countryCode;
    AppCompatTextView title;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This is to hide the status bar and navigation bar.
        Helper.hideSystemUI(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday);

        // This is the code that is used to link the xml file to the java file.
        back = findViewById(R.id.back);
        container = findViewById(R.id.layout);
        holiday_view = findViewById(R.id.holiday_view);
        title = findViewById(R.id.title);

        // This returns to previous activity.
        back.setOnClickListener(v -> onBackPressed());

        // This is getting the intent from the previous activity and getting the country name and
        // country code from the intent.
        Intent intent = getIntent();
        countryName = intent.getStringExtra("countryName");
        countryCode = intent.getStringExtra("countryCode");

        year = Calendar.getInstance().get(Calendar.YEAR);

        title.setText(String.format(Locale.ENGLISH,"Holidays in %s for %d", countryName, year));

        getHolidays getHolidays = new getHolidays();
        getHolidays.execute();

    }

    private class getHolidays extends AsyncTask<String,Void,String> {

        String resp = "";

        @Override
        protected void onPreExecute() {
            Snackbar.make(container,"Loading... Please wait.", BaseTransientBottomBar.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                resp =  APIClient.post("/v1/holidays/List", "{\n" +
                        "  \"country_code\": \""+countryCode+"\",\n" +
                        "  \"year\": "+year+"\n" +
                        "}");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("Resp "+resp);
            // This is creating an arraylist of the Holidays class.
            ArrayList<Holidays> holidaysArrayList = new ArrayList<>();
            try {
                // This is to convert the response string to a JSONObject.
                JSONObject jsonObj = new JSONObject(resp);


                // This is to get the holidays array from the response string.
                JSONArray jsonArray = new JSONArray(jsonObj.getString("holidays"));


                // This is to get the name and date of the holidays from the response string and add it
                // to the arraylist.
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    Holidays holidays = new Holidays(jObj.getString("name"), jObj.getString("date"));
                    holidaysArrayList.add(holidays);
                }

                // This is to set the adapter to the listview.
                adapter = new HolidayAdapter(HolidayActivity.this, holidaysArrayList);
                holiday_view.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}