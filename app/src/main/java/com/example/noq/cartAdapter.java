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

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> data;
    private List<String> data1;

    public cartAdapter(Context context, List<String> data, List<String> data1){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.data1 = data1;;
    }

    //Gutkanatak
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cartlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),Details.class);
                    i.putExtra("title",data.get(getAdapterPosition()));
                    //Intent j = new Intent(v.getContext(),Details.class);
                    //j.putExtra("image",images.get(getAdapterPosition()));
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    v.getContext().startActivity(i);
                }
            });*/
            textTitle = itemView.findViewById(R.id.cartname);
            textDescription = itemView.findViewById(R.id.cartdesc);
        }
    }
    //public void openshopbuttonactivity() {
    //Intent intent = new Intent(this, demoshop.class);
    //startActivity(intent);
}

