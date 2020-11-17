package com.example.noq;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class itemlistshopper extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter1 adapter;
    ArrayList<String> items;
    ArrayList<String> items1;
    List<Integer> images;
    ArrayList<String> barcodes=new ArrayList<>();
    private Button additem;
    FirebaseAuth fauth;
    FirebaseDatabase fref;
    String key, itemid;
    String name, sn;
    String des;
    String url;
    float price=0;
    int c;
    private static final String TAG = "itemlistshopper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlistshopper);
        /*additem=findViewById(R.id.additembutton);
        additem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openaddItembuttonactivity(); }});*/


        images = new ArrayList<>();
        items = new ArrayList<>();
        items1 = new ArrayList<>();
        //barcodes = new ArrayList<>();
        items.add("Gram Flour");
        items.add("Flour");
        items.add("White sugar");
        items.add("Salt");
        items.add("Rice");
        items.add("Almond");
        items.add("Cinthol Soap");
        items.add("Jeera Khari");
        items.add("Maggi Pichku");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 50.00");
        items1.add("5 Kg"+"\n"+"\n"+"Rs 150.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 45.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 25.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 150.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 250.00");
        items1.add("Pack of 5"+"\n"+"\n"+"Rs 100.00");
        items1.add("Pack of 200g"+"\n"+"\n"+"Rs 60.00");
        items1.add("Pack of 90g"+"\n"+"\n"+"Rs 15.00");

        images.add(R.drawable.gramflour);
        images.add(R.drawable.flour);
        images.add(R.drawable.whitesugar);
        images.add(R.drawable.salt);
        images.add(R.drawable.rice);
        images.add(R.drawable.almond);
        images.add(R.drawable.cinthol);
        images.add(R.drawable.khari);
        images.add(R.drawable.pichku);
        /*barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("8901023003622");
        barcodes.add("8904063254627");
        barcodes.add("8901058823660");*/

        Intent i = getIntent();
        //Intent j = getIntent();
        key = i.getStringExtra("key");


        //getKey h=new getKey();
        //key=h.getKey();
        Log.i(TAG, "MyClass.getView() — get item number " + key);

        fauth=FirebaseAuth.getInstance();

        FirebaseRecyclerOptions<itemgetter> options =
                new FirebaseRecyclerOptions.Builder<itemgetter>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("shops").child(key).child("items"), itemgetter.class)
                        .build();


        recyclerView = findViewById(R.id.recyclerView11);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter1(options, 1, key);
        recyclerView.setAdapter(adapter);

        FirebaseDatabase.getInstance().getReference().child("shops").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sn = snapshot.child("sn").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
    public void ScanButton(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null){
                //textView.setText("Cancelled");
            }else {
                //textView.setText(intentResult.getContents());
                //Scannerresult exampleDialog = new Scannerresult();
                //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                //showDialog(intentResult.getContents());
                showDialog(intentResult.getContents());

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    void showDialog(final String s) {

        Log.i(TAG,"barcode h ya nhi"+s);
        //String name;

        FirebaseDatabase.getInstance().getReference().child("shops").child(key)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.child("items").child(s).child("price").child("items").child(s).getValue(float.class)!=null && snapshot.child("items").child(s).child("name").getValue(String.class)!=null && snapshot.child("desc").getValue(String.class)!=null){
                            name = snapshot.child("items").child(s).child("name").getValue(String.class);
                            des=snapshot.child("items").child(s).child("desc").getValue(String.class);

                            //price=snapshot.child("price").getValue(float.class);
                            url=snapshot.child("items").child(s).child("itemImage").getValue(String.class);
                            sn = snapshot.child("sn").getValue(String.class);
                            Log.i(TAG,"key h ya nhi"+name);
                            Log.i(TAG,"key h ya nhi"+des);
                            Log.i(TAG,"key h ya nhi"+price);
                            setdata(s);
                        }
                        else
                        {
                            c=0;
                        }


                        /*if(snapshot.hasChildren())
                        {
                            barcodes.clear();
                            for(DataSnapshot des: snapshot.getChildren())
                            {
                                final itemgetter op= des.getValue(itemgetter.class);
                                barcodes.add(op);
                            }
                        }*/
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.i(TAG,"nhi mila");

                    }
                });

        //final int index1 = barcodes.indexOf(s);
        if(c!=0) {

        }



    }

    private void setdata(String s) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.barcodealertbox, null);

        Button acceptButton = view.findViewById(R.id.button12);
        Button cancelButton = view.findViewById(R.id.button13);
        Button cartButton = view.findViewById(R.id.button14);
        TextView namee = view.findViewById(R.id.name);
        namee.setText(name);
        TextView desc = view.findViewById(R.id.desc);
        desc.setText(des);
        ImageView pic = view.findViewById(R.id.pic);
        Picasso.get().load(url).into(pic);
        itemid = s;

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.e(TAG, "onClick: accept button");
                //addtocart(items.get(index1), items1.get(index1));
                addtocart();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.e(TAG, "onClick: cancel button" );

            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.e(TAG, "onClick: cancel button" );
                // viewcart();
                opencart();
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        alertDialog.show();


    }

    private void addtocart() {
        Intent intent = new Intent(itemlistshopper.this, Details.class);
        intent.putExtra("itemid", itemid);
        intent.putExtra("shopkey", key);
        intent.putExtra("sn", sn);
        startActivity(intent);

    }

    public void opencart() {
        Intent intent = new Intent(itemlistshopper.this, mycart.class);
        intent.putExtra("sn", sn);
        intent.putExtra("key", key);
        startActivity(intent);
    }

    public void opencart(View view) {
        Intent intent = new Intent(itemlistshopper.this, mycart.class);
        intent.putExtra("sn", sn);
        intent.putExtra("key", key);
        startActivity(intent);
    }
    /*ArrayList<String> cartitems;
    ArrayList<String> cartitems1;
    cartAdapter adapter1;
    RecyclerView recyclerView1;
    void addtocart(String name, String desc){
        cartitems = new ArrayList<>();
        cartitems1 = new ArrayList<>();
        cartitems.add(name);
        cartitems1.add(desc);
    }
    void viewcart()
    {
        setContentView(R.layout.cartlayout);
        recyclerView1 = findViewById(R.id.recyclerView2);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        adapter1 = new cartAdapter(this,cartitems, cartitems1);
        recyclerView1.setAdapter(adapter1);

    }*/

    /*
    void showDialog(String s) {

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.barcodealertbox, null);

        Button acceptButton = view.findViewById(R.id.button12);
        Button cancelButton = view.findViewById(R.id.button13);
        TextView barcode=view.findViewById(R.id.barcodeno);
        barcode.setText(s);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.e(TAG, "onClick: accept button");
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.e(TAG, "onClick: cancel button" );
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        alertDialog.show();



    }*/
    /*public void openaddItembuttonactivity() {
        Intent intent = new Intent(this, addItem.class);
        startActivity(intent);
    }*/
}