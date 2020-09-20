package com.example.noq;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import static androidx.core.content.ContextCompat.startActivity;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {

    private LayoutInflater layoutInflater;
    List<Integer> images;
    private List<String> data;
    private List<String> data1;

    public Adapter1(Context context, List<String> data, List<String> data1, List<Integer> images){
        this.layoutInflater = LayoutInflater.from(context);
        this.images = images;
        this.data = data;
        this.data1 = data1;;
    }

    //Gutkanatak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.items,parent,false);
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
                    Intent i = new Intent(v.getContext(),Details.class);
                    i.putExtra("title",data.get(getAdapterPosition()));
                    //Intent j = new Intent(v.getContext(),Details.class);
                    //j.putExtra("image",images.get(getAdapterPosition()));
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(i);
                }
            });
            textTitle = itemView.findViewById(R.id.itemname);
            gridIcon = itemView.findViewById(R.id.itemphoto);
            textDescription = itemView.findViewById(R.id.itemprice);
        }
    }
    //public void openshopbuttonactivity() {
    //Intent intent = new Intent(this, demoshop.class);
    //startActivity(intent);
}

