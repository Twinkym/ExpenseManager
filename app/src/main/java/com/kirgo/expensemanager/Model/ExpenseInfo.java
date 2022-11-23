package com.kirgo.expensemanager.Model;

import java.util.ArrayList;

public class ExpenseInfo {
    public String description;
    public String date;
    public int totalAmount;
    public ArrayList<PayerInfo> payers;

    public ExpenseInfo(String description, String date, int Amount, ArrayList<PayerInfo> payers) {
        this.description = description;
        this.date = date;
        this.totalAmount = Amount;
        this.payers = payers;
    }
}
