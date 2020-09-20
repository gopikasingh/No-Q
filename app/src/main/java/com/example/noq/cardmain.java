package com.example.noq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class cardmain extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;
    ArrayList<String> items1;
    List<Integer> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardmain);
        Toolbar toolbar1 =findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar1);
        images = new ArrayList<>();
        items = new ArrayList<>();
        items1 = new ArrayList<>();

        items.add("Demo Bazaar");
        items.add("Demo Mart");
        items.add("Demo Store");
        items.add("Demo Shopping Zone");

        items1.add("Demonic Mall, Shop No. 7, Delhi NCR");
        items1.add("Demonic Plaza, Shop No. 3, Delhi 6");
        items1.add("Demonic Emporium, Shop No. 2, Greater Noida");
        items1.add("Demonic Hotel, Shop No. 1, Gurugram");

        images.add(R.drawable.demoshop1);
        images.add(R.drawable.demoshop2);
        images.add(R.drawable.demoshop3);
        images.add(R.drawable.demoshop4);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,items, items1, images);
        recyclerView.setAdapter(adapter);
    }
}