package com.kirgo.expensemanager.Model;

public class PayerInfo {
    public String image_url;
    public String name;
    public String email;
    public int amount;

    public PayerInfo(String image, String name, String email, int amount) {
        this.image_url = image;
        this.name = name;
        this.email = email;
        this.amount = amount;
    }
}
