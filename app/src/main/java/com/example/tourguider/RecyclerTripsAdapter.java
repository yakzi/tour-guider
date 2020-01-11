package com.example.tourguider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerTripsAdapter extends RecyclerView.Adapter<RecyclerTripsAdapter.TripViewHolder> {

    private ArrayList<Trip> tripsList;
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    public static class TripViewHolder extends RecyclerView.ViewHolder {
        public ImageView tripImage;
        public TextView tripName;
        public TextView tripPlace;
        public TextView tripLikes;

        public TripViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tripImage = itemView.findViewById(R.id.tripImage);
            tripName = itemView.findViewById(R.id.tripName);
            tripPlace = itemView.findViewById(R.id.tripPlace);
            tripLikes = itemView.findViewById(R.id.tripLikes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public RecyclerTripsAdapter(ArrayList<Trip> newList) {
        tripsList = newList;
    }


    @Override
    public TripViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);
        TripViewHolder tripViewHolder = new TripViewHolder(v, onItemClickListener);
        return tripViewHolder;
    }


    @Override
    public void onBindViewHolder(TripViewHolder holder, int position) {
        Trip currentTrip = tripsList.get(position);
        Glide.with(holder.itemView)
                .load(currentTrip.getPhoto())
                .centerCrop()
                .into(holder.tripImage);
        //holder.tripImage.setImageBitmap(currentTrip.getPhoto());
        holder.tripName.setText(currentTrip.getTrip_name());
        holder.tripPlace.setText(currentTrip.getPlace_name());
        holder.tripLikes.setText(String.valueOf(currentTrip.getLikes()));
    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }
}