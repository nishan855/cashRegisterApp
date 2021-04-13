package com.example.cashregisterapp;

public class OrderItems {
    private String itemName;
    private String itemprice;
    private double tax;
    private double total;


    public OrderItems(String itn, String itp, double tx, double total){
        this.itemprice = itp;
        this.itemName=itn;
        this.tax=tx;
        this.total=total;
    }
    public String getItemName() {
        return itemName;
    }

    public String getItemprice() {
        return itemprice;
    }

    public double getTotal() {
        return total;
    }

    public double getTax() {
        return tax;
    }

}
