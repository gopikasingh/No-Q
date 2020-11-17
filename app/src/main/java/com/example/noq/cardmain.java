package com.example.noq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class cardmain extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> items;
    ArrayList<String> items1;
    List<Integer> images;
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> arrayadapter;
    ImageButton scanner;
    private ImageButton imageButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardmain);

        imageButton=(ImageButton) findViewById(R.id.imageButton);

        searchView = (SearchView) findViewById(R.id.searchView2);
        // scanner = (ImageButton) findViewById(R.id.imageButton);

        /*scanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openbarcodescannerpage();

            }
        });*/


        //Toolbar toolbar1 =findViewById(R.id.toolbar2);
        //setSupportActionBar(toolbar1);
        /*images = new ArrayList<>();
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
        images.add(R.drawable.demoshop4);*/

        //CollectionReference query = FirebaseFirestore.getInstance().collection("shopname");
        /*FirebaseRecyclerOptions<itemgetter> options =
                new FirebaseRecyclerOptions.Builder<itemgetter>()
                        .setQuery(query, shopgetter.class)
                        .build();*/

        final FirebaseRecyclerOptions<shopgetter> options =
                new FirebaseRecyclerOptions.Builder<shopgetter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("shops"), shopgetter.class)
                        .build();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(options);
        recyclerView.setAdapter(adapter);


        /*recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this,items, items1, images);
        recyclerView.setAdapter(adapter);*/

        //arrayadapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, items);
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(items.contains(query)){
                    arrayadapter.getFilter().filter(query);
                }
                else{
                    Toast.makeText(cardmain.this, "No Match found",Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/


        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openprofile2();

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void openprofile2() {
        Intent intent = new Intent(cardmain.this, profile2.class);
        startActivity(intent);
    }

}


// public void openbarcodescannerpage() {
// Intent intent = new Intent(this, barcode_scanner.class);
// startActivity(intent);
//}


