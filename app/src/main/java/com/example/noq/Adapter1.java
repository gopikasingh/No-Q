package com.example.noq;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.appcompat.app.AppCompatActivity;


import static androidx.core.content.ContextCompat.startActivity;

public class Adapter1 extends FirebaseRecyclerAdapter<itemgetter,Adapter1.ViewHolder>
{

    private LayoutInflater layoutInflater;
    int cc;
    String Shopkey;
    // List<Integer> images;
    //private List<String> data;
    // private List<String> data1;

    public Adapter1(@NonNull FirebaseRecyclerOptions<itemgetter> options, int i, String shopkey) {
        super(options);
        cc = i;
        Shopkey = shopkey;

    }

    //Gutkanatak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = layoutInflater.inflate(R.layout.items,parent,false);
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull itemgetter itemgetter) {

        //viewHolder.image.setImageResource(images.get(i));
        viewHolder.name.setText(itemgetter.getName());
        //viewHolder.price.setText(itemgetter.getPrice());
        viewHolder.price.setText( String.valueOf(itemgetter.getPrice()));
        viewHolder.desc.setText(itemgetter.getDesc());
        Picasso.get().load(itemgetter.getItemImage()).into(viewHolder.itemImage);
        //viewHolder.barcode.setText(itemgetter.getBarcode());
        //Glide.with(viewHolder.img.getContext()).load(itemgetter.getItemImage()).into(viewHolder.img);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, desc, price ;
        ImageView itemImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cc == 1) {
                        Log.i("tag","log is 1 !!!");
                        Intent i = new Intent(v.getContext(), Details.class);
                        String key=getRef(getAdapterPosition()).getKey();
                        Log.i("tag","log for key of items !!! "+key);
                        i.putExtra("itemid",key);
                        i.putExtra("shopkey", Shopkey);
                        //i.putExtra("title",data.get(getAdapterPosition()));
                        //Intent j = new Intent(v.getContext(),Details.class);
                        //j.putExtra("image",images.get(getAdapterPosition()));
                        Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                        v.getContext().startActivity(i);
                    }
                    else{
                        Log.i("tag","log is something !!!  it is "+ cc);
                    }

                }
            });
            name = itemView.findViewById(R.id.itemname);
            price= itemView.findViewById(R.id.itemprice);
            itemImage = itemView.findViewById(R.id.itemphoto);
            desc = itemView.findViewById(R.id.itemdesc);
        }
    }
    //public void openshopbuttonactivity() {
    //Intent intent = new Intent(this, demoshop.class);
    //startActivity(intent);
}

/**
 * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
 * {@link FirebaseRecyclerOptions} for configuration options.
 *
 * @param options
 */