package com.example.tourguider;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GuideInfoFragment extends Fragment {
    private TextView tripName;
    private TextView usernameGuide;
    private TextView nameText;
    private TextView surnameText;
    private TextView emailText;
    private TextView phoneText;
    private Guide guide;


    public GuideInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_guide_info, container, false);
        usernameGuide = view.findViewById(R.id.usernameGuide);
        nameText = view.findViewById(R.id.nameText);
        surnameText = view.findViewById(R.id.surnameText);
        emailText = view.findViewById(R.id.emailText);
        phoneText = view.findViewById(R.id.phoneText);

        Bundle bundle = getArguments();
        guide = (Guide) bundle.getSerializable("GUIDE");

        usernameGuide.setText("Username: " + guide.getUsername());
        nameText.setText("Name: " + guide.getName());
        surnameText.setText("Surname: " + guide.getSurname());
        emailText.setText("Email: " + guide.getEmail());
        phoneText.setText("Phone: " + guide.getPhone());


        return view;
    }

}
