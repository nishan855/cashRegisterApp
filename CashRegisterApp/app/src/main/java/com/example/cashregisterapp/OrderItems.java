package com.example.cashregisterapp;

public class OrderItems {
    private String itemName;
    private String itemprice;
    private double tax;
    private double total;
    private double taxable;
    private double nontax;


    public OrderItems(String itn, String itp, double tx, double total,double taxable, double nontax){
        this.itemprice = itp;
        this.itemName=itn;
        this.tax=tx;
        this.total=total;
        this.taxable=taxable;
        this.nontax=nontax;
    }

    public double getTaxable(){return taxable;}

    public double getNontax(){return nontax;}

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
