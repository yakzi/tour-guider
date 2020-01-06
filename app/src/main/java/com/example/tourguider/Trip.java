package com.example.tourguider;

public class Trip {

    private int id_trip;
    private String trip_name;
    private String place_name;
    private String description;
    private int id_user;
    private int likes;
    private int price;
    private boolean by_foot;
    private boolean by_bike;
    private boolean by_car;
    private boolean by_moto;
    private String email;
    private String photo_url;

    public Trip(int id_trip, String trip_name, String place_name, String description, int id_user, int likes, int price,
                boolean by_foot, boolean by_bike, boolean by_car, boolean by_moto,
                String email, String photo_url){
        this.id_trip = id_trip;
        this.trip_name = trip_name;
        this.place_name = place_name;
        this.by_foot = by_foot;
        this.by_bike = by_bike;
        this.by_car = by_car;
        this.by_moto = by_moto;
        this.description = description;
        this.id_user = id_user;
        this.likes = likes;
        this.price = price;
        this.email = email;
        this.photo_url = photo_url;
    }

    public int getId_trip() {
        return id_trip;
    }

    public void setId_trip(int id_trip) {
        this.id_trip = id_trip;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBy_foot() {
        return by_foot;
    }

    public void setBy_foot(boolean by_foot) {
        this.by_foot = by_foot;
    }

    public boolean isBy_bike() {
        return by_bike;
    }

    public void setBy_bike(boolean by_bike) {
        this.by_bike = by_bike;
    }

    public boolean isBy_car() {
        return by_car;
    }

    public void setBy_car(boolean by_car) {
        this.by_car = by_car;
    }

    public boolean isBy_moto() {
        return by_moto;
    }

    public void setBy_moto(boolean by_moto) {
        this.by_moto = by_moto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }
}
