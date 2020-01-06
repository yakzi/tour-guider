package com.example.tourguider;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        loadTrips();
        return view;
    }

    private void loadTrips(){

        class TripLoad extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                try {
                    JSONObject obj = new JSONObject(s);

                    if (!obj.getBoolean("error")) {

                        JSONArray tripsJson = obj.getJSONArray("trips");

                        for (int i = 0; i < tripsJson.length(); i++) {
                            JSONObject tripJson = tripsJson.getJSONObject(i);

                            Trip trip = new Trip(
                                    tripJson.getInt("id_trip"),
                                    tripJson.getString("trip_name"),
                                    tripJson.getString("place_name"),
                                    tripJson.getString("description"),
                                    tripJson.getInt("id_user"),
                                    tripJson.getInt("likes"),
                                    tripJson.getInt("price"),
                                    tripJson.getInt("by_foot") == 1,
                                    tripJson.getInt("by_bike") == 1,
                                    tripJson.getInt("by_car") == 1,
                                    tripJson.getInt("by_moto") == 1,
                                    tripJson.getString("email"),
                                    tripJson.getString("photo_url")
                            );
                            Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(getContext(), "Server error (Piotrek's fault)", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler requestHandler = new RequestHandler();
                return requestHandler.sendPostRequest(URLs.URL_HOMEVIEW, null);
            }
        }

        TripLoad tl = new TripLoad();
        tl.execute();

    }
}
