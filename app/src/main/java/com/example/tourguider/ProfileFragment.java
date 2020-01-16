package com.example.tourguider;

import android.content.Intent;
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

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;

import java.math.BigDecimal;

public class ProfileFragment extends Fragment {

    Button logoutButton;
    Button buttonAddTrip;
    Button payButton;
    TextView  textViewUsername, textViewEmail;

    String amount = "25";
    private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    @Override
    public void onDestroy(){
        getContext().stopService(new Intent(getContext(), PayPalService.class));
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewEmail =  view.findViewById(R.id.textViewEmail);
        User user = SharedPrefManager.getInstance(getContext()).getUser();
        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());

        payButton = view.findViewById(R.id.buttonAddSub);
        payButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                processPayment();
            }
        });



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


        Intent intent = new Intent(getContext(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        getActivity().startService(intent);

        return view;
    }

    private void processPayment() {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(30), "PLN", "Renew TourGuider subscription for 30 days",
                PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(getContext(), PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        getActivity().startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }



}
