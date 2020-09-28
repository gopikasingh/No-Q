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
}
