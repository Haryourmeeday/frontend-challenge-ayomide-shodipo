package com.planatechnologies.androidchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.planatechnologies.androidchallenge.Adapters.CountryAdapter;
import com.planatechnologies.androidchallenge.Helpers.APIClient;
import com.planatechnologies.androidchallenge.Helpers.Helper;
import com.planatechnologies.androidchallenge.Models.Countries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import de.mrapp.android.dialog.MaterialDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    View container;
    AppCompatImageView logout;
    ListView country_view;
    CountryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // This is to hide the status bar and navigation bar.
        Helper.hideSystemUI(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is to initialize the views.
        logout = findViewById(R.id.logout);
        container = findViewById(R.id.layout);
        country_view = findViewById(R.id.country_view);

        // This is a logout dialog.
        logout.setOnClickListener(v -> {
            MaterialDialog mBottomSheetDialog = new MaterialDialog.Builder(this)
                    .setTitle("Logout!!!")
                    .setMessage("Please confirm you want to Logout.")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialogInterface, which) -> {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    })
                    .setNegativeButton("No", (dialogInterface, which) ->
                            dialogInterface.dismiss()
                    )
                    .setCancelable(false)
                    .setHeaderBackgroundColor(getResources().getColor(R.color.colorPrimary, null))
                    .showHeader(false)
                    .setHeaderIcon(R.drawable.ic_outline_notifications_active_24)
                    .showHeaderDivider(false)
                    .setHeaderHeight(150)
                    .setHeaderIconTint(getResources().getColor(R.color.white, null))
                    .setBackgroundColor(getResources().getColor(R.color.colorPrimary, null))
                    .setMessageColor(getResources().getColor(R.color.white, null))
                    .setButtonTypeface(Typeface.SANS_SERIF)
                    .setMessageTypeface(Typeface.SANS_SERIF)
                    .setTitleTypeface(Typeface.SANS_SERIF)
                    .show();
            mBottomSheetDialog.show();
        });

        // This is to get the view of the item clicked in the listview.
        country_view.setOnItemClickListener((parent, view, position, id) -> {

            // This is to get the view of the item clicked in the listview.
            View view1 = parent.getAdapter().getView(position,view,parent);

            MaterialTextView codeText = view1.findViewById(R.id.countryCode);
            MaterialTextView countryText = view1.findViewById(R.id.countryName);
            String code = codeText.getText().toString();
            String country = countryText.getText().toString();

            // This is to start the HolidayActivity and pass the country code and country name to the
            // HolidayActivity.
            Intent i = new Intent(getApplicationContext(), HolidayActivity.class);
            i.putExtra("countryCode",code);
            i.putExtra("countryName",country);
            startActivity(i);

        });


        // This is an AsyncTask that is used to get the countries from the API.
        getCountries countries = new getCountries();
        countries.execute();

    }

    private class getCountries extends AsyncTask<String,Void,String> {

        String resp = "";

        @Override
        protected void onPreExecute() {
            Snackbar.make(container,"Loading... Please wait.",BaseTransientBottomBar.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                resp =  APIClient.post("/v1/holidays/Countries", "{}");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {

            // This is to create an arraylist of countries.
            ArrayList<Countries> countriesArrayList = new ArrayList<>();
            try {
                // This is to convert the response string to a JSONObject.
                JSONObject jsonObj = new JSONObject(resp);

        // This is to get the countries array from the response.
        JSONArray jsonArray = new JSONArray(jsonObj.getString("countries"));

        // This is to loop through the countries array and get the name and code of each country.
        for (int i = 0; i < jsonArray.length(); i++){
        JSONObject jObj = jsonArray.getJSONObject(i);
        Countries newUser = new Countries(jObj.getString("name"), jObj.getString("code"));
        countriesArrayList.add(newUser);
        }

        // This is to set the adapter to the listview.
        adapter = new CountryAdapter(MainActivity.this, countriesArrayList);
        country_view.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}