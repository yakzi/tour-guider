package com.example.tourguider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ProfileFragment extends Fragment {

    Button logoutButton;
    Button buttonAddTrip;
    TextView  textViewUsername, textViewEmail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewEmail =  view.findViewById(R.id.textViewEmail);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());



        buttonAddTrip = view.findViewById(R.id.buttonAddTrip);
        buttonAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new AddTripFragment();
                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.profileFragment, newFragment)
                        .addToBackStack(this.getClass().getName())
                        .commit();
            }
        });


        logoutButton = view.findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).logout();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });

        return view;
    }
}
