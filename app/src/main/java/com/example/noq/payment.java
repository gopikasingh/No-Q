package com.example.noq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class payment extends AppCompatActivity {
    EditText orderaddress;
    TextView txtordername;
    TextView txtorderemail, txtamount;
    Button buttonconfirmorfer;
    String tot, userid;
    FirebaseAuth fauth;

    String name, mail, total, shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent i = getIntent();
        tot = i.getStringExtra("totalprice");
        shop = i.getStringExtra("sn");

        fauth = FirebaseAuth.getInstance();
        userid = Objects.requireNonNull(fauth.getCurrentUser()).getUid();
        //final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("customer").child(userid);

        buttonconfirmorfer = (Button) findViewById(R.id.buttonconfirmorfer);
        txtorderemail = (TextView) findViewById(R.id.txtorderemail);
        txtamount = (TextView) findViewById(R.id.txtamount);
        txtordername = (TextView) findViewById(R.id.txtordername);
        orderaddress = findViewById(R.id.orderaddress);

        //txtordername.setText(ref.child("name").toString());
        //txtorderemail.setText(ref.child("email").toString());
        //txtamount.setText(tot);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("customer").child(userid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    //itemgetter itemgetter = snapshot.getValue(itemgetter.class);
                    name = snapshot.child("name").getValue(String.class);
                    mail =snapshot.child("email").getValue(String.class);
                    total = tot;
                    //url=snapshot.child("itemImage").getValue(String.class);
                    //price=snapshot.child("price").getValue(String.class);


                    //detailTitle.setText(name);
                    txtordername.setText("Name: "+name);
                    //detaildesc.setText(desc);
                    txtorderemail.setText("E-mail: "+mail);
                    txtamount.setText("Rs. "+tot);

                    //detailprice.setText(String.valueOf(price));
                    //Picasso.get().load(itemgetter.getItemImage().into())
                    //Picasso.get().load(url).into(shopphoto1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}