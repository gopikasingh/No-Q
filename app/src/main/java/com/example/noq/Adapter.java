package com.example.noq;

/*import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    List<Integer> images;
    private List<String> data;
    private List<String> data1;

    public Adapter(Context context, List<String> data, List<String> data1, List<Integer> images){
        this.layoutInflater = LayoutInflater.from(context);
        this.images = images;
        this.data = data;
        this.data1 = data1;;
    }

    //Gutkanatak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cardlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.gridIcon.setImageResource(images.get(i));
        String title = data.get(i);
        String desc = data1.get(i);
        viewHolder.textTitle.setText(title);
        viewHolder.textDescription.setText(desc);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textTitle,textDescription;
        ImageView gridIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //openshopbuttonactivity();
                    Intent i = new Intent(v.getContext(),demoshop.class);
                    //i.putExtra("title",data.get(getAdapterPosition()));
                    //Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(i);
                }
            });
            textTitle = itemView.findViewById(R.id.shopname);
            gridIcon = itemView.findViewById(R.id.shopphoto);
            textDescription = itemView.findViewById(R.id.shopaddress);
        }
    }
    //public void openshopbuttonactivity() {
    //Intent intent = new Intent(this, demoshop.class);
    //startActivity(intent);
}*/

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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

//import com.bumptech.glide.Glide;

public class Adapter extends FirebaseRecyclerAdapter<shopgetter,Adapter.ViewHolder>
{

    private LayoutInflater layoutInflater;
    private static final String TAG = "Adapter";
    // List<Integer> images;
    //private List<String> data;
    // private List<String> data1;

    public Adapter(@NonNull FirebaseRecyclerOptions<shopgetter> options) {
        super(options);
    }

    //Gutkanatak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = layoutInflater.inflate(R.layout.items,parent,false);
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull shopgetter shopgetter) {
        //viewHolder.image.setImageResource(images.get(i));
        viewHolder.name.setText(shopgetter.getSn());

        viewHolder.address.setText(shopgetter.getAdd());
        Picasso.get().load(shopgetter.getShopImage()).into(viewHolder.shopImage);
        //viewHolder.barcode.setText(itemgetter.getBarcode());
        //Glide.with(viewHolder.img.getContext()).load(itemgetter.getItemImage()).into(viewHolder.img);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name, address ;
        ImageView shopImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),demoshop.class);
                    String key=getRef(getAdapterPosition()).getKey();
                    i.putExtra("key",key);
                    //i.putExtra("title",data.get(getAdapterPosition()));
                    //Intent j = new Intent(v.getContext(),Details.class);
                    //j.putExtra("image",images.get(getAdapterPosition()));
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

                    Log.i(TAG,"key h ya nhi"+key);
                    //getKey g= new getKey();
                    //g.setKey(key);
                    v.getContext().startActivity(i);
                }
            });
            name = itemView.findViewById(R.id.shopname);
            address= itemView.findViewById(R.id.shopaddress);
            shopImage = itemView.findViewById(R.id.shopphoto);
        }
    }
    //public void openshopbuttonactivity() {
    //Intent intent = new Intent(this, demoshop.class);
    //startActivity(intent);
}