package com.example.noq;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Details extends AppCompatActivity {
    //TextView textTitle;
    private Button buttonaddtocart;
    private ImageView shopphoto1;
    private ElegantNumberButton number_btn;
    private TextView detailTitle, detailprice, detaildesc, detailshopname;
    public String itemid = "";
    public String name, desc, url;
    public String key="";
    public String sn="";
    public float price;
    FirebaseAuth fauth;
    String userid;
    String jugadurl;
    String datetime;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        itemid = getIntent().getStringExtra("itemid");
        key = getIntent().getStringExtra("shopkey");
        sn = getIntent().getStringExtra("sn");
        Log.i("TAG", "MyClass.getView() — get item number " + key);
        Log.i("TAG", "MyClass.getView() — get item number " + itemid);

        ///getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        fauth = FirebaseAuth.getInstance();

        //Intent j = getIntent();
        //String title = i.getStringExtra("title");
        //String key=getRef(getAdapterPosition()).getKey();
        //int image = j.getIntExtra("image");
        //textTitle = findViewById(R.id.detailTitle);
        //textTitle.setText(title);
        buttonaddtocart = (Button) findViewById(R.id.buttonaddtocart);
        number_btn = (ElegantNumberButton) findViewById(R.id.number_btn);
        shopphoto1 = (ImageView) findViewById(R.id.shopphoto1);
        detailTitle = (TextView) findViewById(R.id.detailTitle);
        detailprice = (TextView) findViewById(R.id.detailprice);
        detaildesc = (TextView) findViewById(R.id.detaildesc);
        detailshopname = (TextView) findViewById(R.id.detailshopname);

        getProductdetails(itemid);
        buttonaddtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartList();
            }
        });



    }

    private void addToCartList() {
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());
        userid = fauth.getCurrentUser().getUid();

        final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("customer").child(userid).child("Cart List");  //we are creating new node

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("itemid", itemid);
        cartMap.put("pname", detailTitle.getText().toString());

        final float floatprice = Float.valueOf(detailprice.getText().toString());

        cartMap.put("price", floatprice);
        cartMap.put("itemImage", jugadurl);
        cartMap.put("desc", detaildesc.getText().toString());
        cartMap.put("qty", number_btn.getNumber());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);
        cartMap.put("sn", sn);

        datetime = ""+saveCurrentDate+" "+saveCurrentTime;

        cartlistref.child(datetime).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FancyToast.makeText(Details.this,""+detailTitle.getText().toString()+" added to cart successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                    Intent intent = new Intent(Details.this, itemlistshopper.class);
                    intent.putExtra("key", key);
                    intent.putExtra("shop", sn);
                    startActivity(intent);

                }
            }
        });

    }

    public void getProductdetails(final String itemid) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("shops").child(key);
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                     //itemgetter itemgetter = snapshot.getValue(itemgetter.class);
                    name = snapshot.child("items").child(itemid).child("name").getValue(String.class);
                    desc =snapshot.child("items").child(itemid).child("desc").getValue(String.class);
                    price=snapshot.child("items").child(itemid).child("price").getValue(float.class);
                    url=snapshot.child("items").child(itemid).child("itemImage").getValue(String.class);
                    sn = snapshot.child("sn").getValue(String.class);
                    jugadurl = url;
                    //price=snapshot.child("price").getValue(String.class);
                    detailTitle.setText(name);
                    detaildesc.setText(desc);
                    detailprice.setText(String.valueOf(price));
                    //Picasso.get().load(itemgetter.getItemImage().into())
                    Picasso.get().load(url).into(shopphoto1);
                    detailshopname.setText(sn);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void OpenCart(View view) {
        Intent intent = new Intent(Details.this, mycart.class);
        intent.putExtra("key", key);
        intent.putExtra("shop", sn);
        intent.putExtra("cartid", datetime);
        startActivity(intent);
    }
}
