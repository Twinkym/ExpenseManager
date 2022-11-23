package com.kirgo.expensemanager.Model;

public class TripInfo {
    public String image_url;
    public String date;
    public String description;
    public int tripID;

    public TripInfo(String image, String date, String description, int tripID) {
        this.image_url = image;
        this.date = date;
        this.description = description;
        this.tripID = tripID;
    }
}
