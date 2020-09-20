package com.example.noq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class itemlist extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter1 adapter;
    ArrayList<String> items;
    ArrayList<String> items1;
    List<Integer> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        images = new ArrayList<>();
        items = new ArrayList<>();
        items1 = new ArrayList<>();

        items.add("Gram Flour");
        items.add("Flour");
        items.add("White sugar");
        items.add("Salt");
        items.add("Rice");
        items.add("Almond");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 50.00");
        items1.add("5 Kg"+"\n"+"\n"+"Rs 150.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 45.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 25.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 150.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 250.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 250.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 250.00");
        images.add(R.drawable.gramflour);
        images.add(R.drawable.flour);
        images.add(R.drawable.whitesugar);
        images.add(R.drawable.salt);
        images.add(R.drawable.rice);
        images.add(R.drawable.almond);
        images.add(R.drawable.almond);
        images.add(R.drawable.almond);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter1(this,items, items1, images);
        recyclerView.setAdapter(adapter);
    }
}