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

public class MainActivity extends AppCompatActivity implements allItems.DialogListen {

    private Button tax;
    private Button nontax;
    private Button groc;
    private Button cigg;
    private Button beer;
    private Button ref;
    private Button clr;
    private Button cash;
    private RecyclerView recyclerView;
    private TextView totSale;
    private TextView totTaxx;
    ItemAdapter it=null;


    ArrayList<OrderItems> items= new ArrayList<OrderItems>();




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
    public void applyTexts(String item, String price, double tax, double total) {

        OrderItems oi= new OrderItems(item,price, tax,total);
        items.add(oi);
        it= new ItemAdapter(items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(it);
        totSale.setText("Tax : $ "+String.format("%.2f",getAllTax()));
        totTaxx.setText("Total : $ "+String.format("%.2f",getAllTotal()));
    }

}