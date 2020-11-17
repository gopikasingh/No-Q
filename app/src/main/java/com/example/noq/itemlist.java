package com.example.noq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
public class itemlist extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter1 adapter;
    private Button additem;
    FirebaseAuth fauth;
    private static final String TAG = "itemlist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        additem=findViewById(R.id.additembutton);
        additem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openaddItembuttonactivity(); }});
        fauth=FirebaseAuth.getInstance();

        FirebaseRecyclerOptions<itemgetter> options =
                new FirebaseRecyclerOptions.Builder<itemgetter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("shops").child(fauth.getCurrentUser().getUid()).child("items"), itemgetter.class)
                        .build();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter1(options, 2, "hello");
        recyclerView.setAdapter(adapter);
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
    public void openaddItembuttonactivity() {
        Intent intent = new Intent(this, addItem.class);
        startActivity(intent);
    }
}