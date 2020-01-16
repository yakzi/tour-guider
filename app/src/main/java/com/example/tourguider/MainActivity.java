package com.example.tourguider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    public static int daysLeft = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListner);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_observed:
                            selectedFragment = new ObservedFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new LoginFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 7171)
        {
            if(resultCode == RESULT_OK)
            {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null)
                {
                    try{
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        Toast.makeText(this, "Payment confirmed \n" + paymentDetails + "\n" + "30", Toast.LENGTH_LONG).show();
                        daysLeft += 30;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        } else if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID){
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();

        }

    }
}
