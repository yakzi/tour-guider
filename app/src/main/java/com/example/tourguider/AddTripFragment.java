package com.example.tourguider;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddTripFragment extends Fragment {

    private Button buttonSelectImg;
    private Button buttonAddTrip;

    private EditText name;
    private EditText desc;
    private EditText place;

    private ImageView uploadImage;

    private RadioGroup radioGroup;
    private Bitmap bitmap;

    private Uri filePath;
    public  static final String UPLOAD_KEY = "image";
    private String type = "foot";
    private int PICK_IMAGE_REQUEST = 1;


    public AddTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);

        buttonSelectImg =  view.findViewById(R.id.buttonSelectImg);
        buttonAddTrip = view.findViewById(R.id.buttonAddTrip);
        name = view.findViewById(R.id.editTextTripName);
        place = view.findViewById(R.id.editTripPlace);
        desc = view.findViewById(R.id.editTextDescription);
        uploadImage = view.findViewById(R.id.uploadImage);
        radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.foot) {
                    type = "foot";
                } else if (checkedId == R.id.bike) {
                    type = "bike";
                } else if (checkedId == R.id.car) {
                    type = "car";
                } else if (checkedId == R.id.moto) {
                    type = "moto";
                }
            }
        });

        buttonSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        buttonAddTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage(getName(), getDesc(), getPlace(), getType());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private String getType()
    {
        return type;
    }

    private String getName()
    {
        return name.getText().toString();
    }

    private String getDesc()
    {
        return desc.getText().toString();
    }

    private String getPlace()
    {
        return place.getText().toString();
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                uploadImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(final String name, final String desc, final String place, final String type){
        class UploadImage extends AsyncTask<Bitmap,Void,String> {

            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                data.put(UPLOAD_KEY, uploadImage);
                data.put("tripname", name);
                data.put("description", desc);
                data.put("place", place);
                data.put("type", type);

                String result = rh.sendPostRequest(URLs.URL_ADD_TRIP,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

}
