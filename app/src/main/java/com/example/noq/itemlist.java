package com.example.noq;

<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
=======
>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
=======
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc
public class itemlist extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter1 adapter;
    ArrayList<String> items;
    ArrayList<String> items1;
<<<<<<< HEAD
    ArrayList<String> barcodes;
=======
>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc
    List<Integer> images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        images = new ArrayList<>();
        items = new ArrayList<>();
        items1 = new ArrayList<>();
<<<<<<< HEAD
        barcodes = new ArrayList<>();
=======

>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc
        items.add("Gram Flour");
        items.add("Flour");
        items.add("White sugar");
        items.add("Salt");
        items.add("Rice");
        items.add("Almond");
<<<<<<< HEAD
        items.add("Cinthol Soap");
        items.add("Jeera Khari");
        items.add("Maggi Pichku");
=======
>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc
        items1.add("1 Kg"+"\n"+"\n"+"Rs 50.00");
        items1.add("5 Kg"+"\n"+"\n"+"Rs 150.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 45.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 25.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 150.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 250.00");
<<<<<<< HEAD
        items1.add("Pack of 5"+"\n"+"\n"+"Rs 100.00");
        items1.add("Pack of 200g"+"\n"+"\n"+"Rs 60.00");
        items1.add("Pack of 90g"+"\n"+"\n"+"Rs 15.00");

=======
        items1.add("1 Kg"+"\n"+"\n"+"Rs 250.00");
        items1.add("1 Kg"+"\n"+"\n"+"Rs 250.00");
>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc
        images.add(R.drawable.gramflour);
        images.add(R.drawable.flour);
        images.add(R.drawable.whitesugar);
        images.add(R.drawable.salt);
        images.add(R.drawable.rice);
        images.add(R.drawable.almond);
<<<<<<< HEAD
        images.add(R.drawable.cinthol);
        images.add(R.drawable.khari);
        images.add(R.drawable.pichku);
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("0");
        barcodes.add("8901023003622");
        barcodes.add("8904063254627");
        barcodes.add("8901058823660");



=======
        images.add(R.drawable.almond);
        images.add(R.drawable.almond);
>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter1(this,items, items1, images);
        recyclerView.setAdapter(adapter);
    }
<<<<<<< HEAD
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
    void showDialog(String s) {

        final int index1 = barcodes.indexOf(s);
        int c;
        if(index1==-1)
        {
            c=0;
        }
        else {
           c=1;
        }
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.barcodealertbox, null);

        Button acceptButton = view.findViewById(R.id.button12);
        Button cancelButton = view.findViewById(R.id.button13);
        Button cartButton = view.findViewById(R.id.button14);
        TextView name=view.findViewById(R.id.name);
        name.setText(items.get(index1));
        TextView desc=view.findViewById(R.id.desc);
        desc.setText(items1.get(index1));
        ImageView pic = view.findViewById(R.id.pic);
        pic.setImageResource(images.get(index1));

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.e(TAG, "onClick: accept button");
                //addtocart(items.get(index1), items1.get(index1));
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
            }
        });

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        alertDialog.show();



    }
    ArrayList<String> cartitems;
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

    }
=======
>>>>>>> ba0640c659c03926d4ad1c7a1d8d3bbdf17c0cfc
}