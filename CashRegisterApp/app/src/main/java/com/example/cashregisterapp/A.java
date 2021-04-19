package com.example.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class A extends AppCompatActivity implements allItems.DialogListen {

    private Button tax;
    private Button nontax;
    private Button groc;
    private Button cigg;
    private Button beer;
    private Button ref;
    private Button clr;
    private Button cash;
    private RecyclerView recyclerView;
    static TextView totSale;
    static TextView totTaxx;
    static ItemAdapter it=null;

    static ArrayList<OrderItems> items= new ArrayList<OrderItems>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_items);

        tax = (Button) findViewById(R.id.button7);
        nontax = (Button) findViewById(R.id.button10);
        groc = (Button) findViewById(R.id.button9);
        cigg = (Button) findViewById(R.id.button13);
        beer = (Button) findViewById(R.id.button12);
        ref = (Button) findViewById(R.id.button14);
        clr = (Button) findViewById(R.id.button17);
        cash = (Button) findViewById(R.id.button18);
        recyclerView= (RecyclerView) findViewById(R.id.rv12);
        totSale= (TextView) findViewById(R.id.tvv1);
        totTaxx= (TextView) findViewById(R.id.textView2);




        tax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allItems ai=new allItems("tax");
           ai.show(getSupportFragmentManager(),"Insert");

            }
        });

        nontax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allItems ai=new allItems("nontax");
                ai.show(getSupportFragmentManager(),"Insert");

            }
        });

        groc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allItems ai=new allItems("groc");
                ai.show(getSupportFragmentManager(),"Insert");

            }
        });

        cigg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allItems ai=new allItems("cigg");
                ai.show(getSupportFragmentManager(),"Insert");

            }
        });

        beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allItems ai=new allItems("beer");
                ai.show(getSupportFragmentManager(),"Insert");

            }
        });

        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();
                it.notifyDataSetChanged();
                totSale.setText("Total: $ 0.00");
                totTaxx.setText("Tax: $ 0.00");

            }
        });

        cash.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(items.size()>0) {
                    double thisTotal = getAllTotal();
                    CashHandler ch = new CashHandler(thisTotal);
                    ch.show(getSupportFragmentManager(), "Insert");


                }

            }
        });

        ref.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                    refund rf = new refund();
                    rf.show(getSupportFragmentManager(), "Insert");


            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitems, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.report1: {
                double tSale = CashHandler.total_nontaxable + CashHandler.total_tax + CashHandler.total_taxable;
                String txs = String.format("%.2f", CashHandler.total_taxable);
                String ntxs = String.format("%.2f", CashHandler.total_nontaxable);
                String txx = String.format("%.2f", CashHandler.total_tax);
                String ttl_sale = String.format("%.2f", tSale);
                report rp = new report(ntxs, txs, txx, ttl_sale);
                rp.show(getSupportFragmentManager(), "Insert");
            }

            case R.id.close:
                double tSale= CashHandler.total_nontaxable+CashHandler.total_tax+CashHandler.total_taxable;
                String txs= String.format("%.2f",CashHandler.total_taxable);
                String ntxs= String.format("%.2f",CashHandler.total_nontaxable);
                String txx=String.format("%.2f",CashHandler.total_tax);
                String ttl_sale = String.format("%.2f",tSale);
                CloseShift cs= new CloseShift(ntxs,txs,txx,ttl_sale);
                cs.show(getSupportFragmentManager(),"Insert");

        }
        return true;
    }

    public double getAllTotal(){
        double tot=0;
        for(int i=0;i<items.size();i++){
            tot +=items.get(i).getTotal();
        }

        return tot;
    }

    public double getAllTax(){
        double totTax=0;
        for(int i=0;i<items.size();i++){
            totTax +=items.get(i).getTax();
        }

        return totTax;
    }





    @Override
    public void applyTexts(String item, String price, double tax, double total,double taxable, double nontax) {

        OrderItems oi= new OrderItems(item,price, tax,total, taxable, nontax);
        items.add(oi);
        it= new ItemAdapter(items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(it);
        totSale.setText("Tax : $ "+String.format("%.2f",getAllTax()));
        totTaxx.setText("Total : $ "+String.format("%.2f",getAllTotal()));

    }



}