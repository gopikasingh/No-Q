package com.example.noq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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

import java.util.Objects;

public class mycart extends AppCompatActivity {


    FirebaseAuth fauth = FirebaseAuth.getInstance();
    String userid = fauth.getCurrentUser().getUid();
    //String userid;
    String key, total, shop, cartid, keyyy;
    //ListView listView;
    RecyclerView listView;
    RecyclerView.LayoutManager layoutManager;
    Button button01; //confirmorder
    TextView textView016; //subtotal
    TextView textView014; //delivery charges
    TextView textView018; //total price = delivery + mrp
    private static final String TAG = "mycart";

    private float overallprice = 0;
    tlist t = new tlist();
    payment d = new payment();
    String k;

    // userid = Objects.requireNonNull(fauth.getCurrentUser()).getUid();

    final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("customer").child(userid);

    //Button
    //String mTitle[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    //String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
    //int images[] = {};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycart);

        Intent i = getIntent();
        //Intent j = getIntent();
        key = i.getStringExtra("key");
        shop = i.getStringExtra("shop");
        cartid = i.getStringExtra("cartid");

        //getKey h=new getKey();
        //key=h.getKey();
        Log.i("TAG", "MyClass.getView() — get item number " + key);

        listView = findViewById(R.id.listViewww);
        listView.setHasFixedSize(true);
        button01 = findViewById(R.id.button01);
        textView016 = findViewById(R.id.textView016);
        textView014 = findViewById(R.id.textView014);
        textView018 = findViewById(R.id.textView018);
        layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        //fauth = FirebaseAuth.getInstance();
        //userid = Objects.requireNonNull(fauth.getCurrentUser()).getUid();

        // now create an adapter class

        //MyAdapter adapter = new MyAdapter(this, mTitle, mDescription);
        //listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                if (position ==  0) {
////                    Toast.makeText(mycart.this, "Facebook Description", Toast.LENGTH_SHORT).show();
////                }
////                if (position ==  0) {
////                    Toast.makeText(mycart.this, "Whatsapp Description", Toast.LENGTH_SHORT).show();
////                }
////                if (position ==  0) {
////                    Toast.makeText(mycart.this, "Twitter Description", Toast.LENGTH_SHORT).show();
////                }
////                if (position ==  0) {
////                    Toast.makeText(mycart.this, "Instagram Description", Toast.LENGTH_SHORT).show();
////                }
////                if (position ==  0) {
////                    Toast.makeText(mycart.this, "Youtube Description", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
        // so item click is done now check list view
    }

//    class MyAdapter extends ArrayAdapter<String> {
//
//        Context context;
//        String rTitle[];
//        String rDescription[];
//        //int rImgs[];

//        MyAdapter (Context c, String title[], String description[]) {
//            //super(c, R.layout.cartdesign, R.id.textView1, title);
//            this.context = c;
//            this.rTitle = title;
//            this.rDescription = description;
//            //this.rImgs = imgs;
//
//        }

//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row = layoutInflater.inflate(R.layout.cartdesign, parent, false);
//            //ImageView images = row.findViewById(R.id.image);
//            //TextView myTitle = row.findViewById(R.id.textView1);
//            //TextView myDescription = row.findViewById(R.id.textView2);
//
//            // now set our resources on views
//            //images.setImageResource(rImgs[position]);
//            //myTitle.setText(rTitle[position]);
//            //myDescription.setText(rDescription[position]);
//
//
//
//
//            return row;
//        }
    //}


    @Override
    protected void onStart() {
        super.onStart();


        //final DatabaseReference cartlistref = FirebaseDatabase.getInstance().getReference().child("customer").child(userid);// .child("Cart List");

        FirebaseRecyclerOptions<tlist> options =
                new FirebaseRecyclerOptions.Builder<tlist>()
                .setQuery(cartlistref.child("Cart List"), tlist.class)
                .build();

        FirebaseRecyclerAdapter<tlist, cartAdapter> adapter = new FirebaseRecyclerAdapter<tlist, cartAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final cartAdapter holder, int position, @NonNull final tlist model) {
                holder.qty.setText("Quantity: "+model.getQty());
                holder.name.setText(model.getPname());
                holder.description.setText(model.getDesc());
                holder.price.setText(model.getPrice()+"");
                holder.sn.setText(model.getSn());
                //shop = model.getSn();
                Picasso.get().load(model.getItemImage()).into(holder.image);

                //float pricee = model.getPrice();

                float oneproducttotalprice = model.getPrice() * Integer.parseInt(model.getQty());
                overallprice = overallprice + oneproducttotalprice;
                total = String.valueOf(overallprice);
                textView016.setText(total);
                textView018.setText(total);


                /*itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                        Log.i("tag ", "click hora h");
                        //m.ajeeb(getAdapterPosition());
                        CharSequence[] options = new CharSequence[]{
                                "Edit", //index of Edit is 0
                                "Remove" // index of Remove is 1
                        };
                        final AlertDialog.Builder builder = new AlertDialog.Builder(mycart.this);
                        builder.setTitle("Cart Options: ");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    Intent intent = new Intent(cartAdapter.this, Details.class);
                                    intent.putExtra("itemid", model.getItemid());
                                    intent.putExtra("sn", shop);
                                    startActivity(intent);
                                }
                                if (which == 1) {
                                    cartlistref.child("Cart List").child(model.getItemid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(mycart.this, "" + model.getPname() + "Item removed from your cart!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(mycart.this, itemlistshopper.class);
                                                intent.putExtra("key", key);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                    builder.show();
                                }
                            }
                        });

                    }
                });*/



                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Clicked -> " + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(v.getContext(), "Clicked -> " + cartid, Toast.LENGTH_SHORT).show();
                        k=getRef(holder.getAdapterPosition()).getKey();

                        Log.i("tag ", "click hora h");

                        CharSequence[] options = new CharSequence[] {
                          "Edit", //index of Edit is 0
                          "Remove" // index of Remove is 1
                        };
                        final AlertDialog.Builder builder = new AlertDialog.Builder(mycart.this);
                        builder.setTitle("Cart Options: ").setItems(options, new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (which == 0) {
                                    Intent intent = new Intent(mycart.this, Details.class);
                                    intent.putExtra("itemid", model.getItemid());
                                    intent.putExtra("sn", shop);
                                    intent.putExtra("shopkey", key);
                                    startActivity(intent);
                                }
                                if (which == 1) {
                                    DatabaseReference ref = cartlistref.child("Cart List").child(model.getItemid());
                                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                keyyy = snapshot.getKey();
                                                Toast.makeText(mycart.this, "failed" + keyyy, Toast.LENGTH_SHORT).show();

//                                                String keyyy = snapshot.getKey();
//                                                snapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<Void> task) {
//                                                        if (task.isSuccessful()) {
//                                                            Toast.makeText(mycart.this, ""+model.getPname()+"Item removed from your cart!", Toast.LENGTH_SHORT).show();
//                                                            Intent intent = new Intent(mycart.this, itemlistshopper.class);
//                                                            intent.putExtra("key", key);
//                                                            startActivity(intent);
//                                                        }
//
//                                                    }
//                                                });
                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(mycart.this, "failed", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    final DatabaseReference cartlistreffff = FirebaseDatabase.getInstance().getReference().child("customer").child(userid).child("Cart List");
                                    cartlistreffff.child(k).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                //FancyToast.makeText(Details.this,""+detailTitle.getText().toString()+" added to cart successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                                                Toast.makeText(mycart.this, ""+model.getPname()+"Item removed from your cart!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(mycart.this, itemlistshopper.class);
                                                intent.putExtra("key", key);
                                                startActivity(intent);

                                            }
                                        }
                                    });
                                    /*cartlistref.child("Cart List").child(model.getItemid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(mycart.this, ""+model.getPname()+"Item removed from your cart!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(mycart.this, itemlistshopper.class);
                                                intent.putExtra("key", key);
                                                startActivity(intent);
                                            }
                                        }
                                    });*/

                                }
                            }
                        });
                        builder.create();
                        builder.show();



                    }
                });

            }


            @NonNull
            @Override
            public cartAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartdesign, parent, false);

                cartAdapter holder = new cartAdapter(view);
                return holder;
            }
        };
        listView.setAdapter(adapter);
        adapter.startListening();

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mycart.this, payment.class);
                intent.putExtra("totalprice", total);
                intent.putExtra("sn", shop);
                startActivity(intent);
            }
        });
    }
}
