package com.example.tourguider;


import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class DisplayTripFragment extends Fragment {

    private ImageView tripImage;
    private TextView tripName;
    private TextView tripDesc;
    private TextView tripType;
    private TextView tripPlace;
    private TextView tripPrice;

    public DisplayTripFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_trip, container, false);

        tripImage = view.findViewById(R.id.tripImage);
        tripDesc = view.findViewById(R.id.tripDesc);
        tripName = view.findViewById(R.id.tripName);
        tripType = view.findViewById(R.id.tripType);
        tripPlace = view.findViewById(R.id.tripPlace);
        tripPrice = view.findViewById(R.id.tripPrice);

        Bundle bundle = getArguments();
        Trip trip= (Trip) bundle.getSerializable("TRIP");

        Glide.with(this)
                .load(trip.getPhoto())
                .centerCrop()
                .into(tripImage);
        tripName.setText(trip.getTrip_name());
        tripPlace.setText(trip.getPlace_name());
        tripDesc.setText(trip.getDescription());
        tripPrice.setText(trip.getPrice() + " PLN");

        if(trip.isBy_bike()) tripType.setText("Bike");
        if(trip.isBy_car()) tripType.setText("Car");
        if(trip.isBy_foot()) tripType.setText("Foot");
        if(trip.isBy_moto()) tripType.setText("Motorcycle");



        return view;
    }

}
