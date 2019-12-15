package com.example.tourguider;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RegisterFragment extends Fragment {


    public RegisterFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        view.findViewById(R.id.registerUser).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment newFragment = new RegisterUserFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.registerFragment, newFragment)
                        .addToBackStack(this.getClass().getName())
                        .commit();
            }
        });

        view.findViewById(R.id.registerGuide).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Fragment newFragment = new RegisterGuideFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.registerFragment, newFragment)
                        .addToBackStack(this.getClass().getName())
                        .commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
