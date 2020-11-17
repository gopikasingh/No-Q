package com.example.noq;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noq.Interface.ItemClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class cartAdapter extends RecyclerView.ViewHolder {

    public TextView name, description, price, qty, sn;
    public ImageView image;
    private ItemClickListener itemClickListener;
    private static final String TAG = "cartAdapter";
    Context c;
    mycart m = new mycart();

    public cartAdapter(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);
        price = itemView.findViewById(R.id.price);
        qty = itemView.findViewById(R.id.qty);
        image = itemView.findViewById(R.id.image);
        sn = itemView.findViewById(R.id.sname);
        Log.i("TAG", "inside cartAdapter:");

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

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] options = new CharSequence[] {
                        "Edit", //index of Edit is 0
                        "Remove" // index of Remove is 1
                };
                final AlertDialog.Builder builder = new AlertDialog.Builder(mycart.this);
                builder.setTitle("Cart Options: ");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent(mycart.this, Details.class);
                            intent.putExtra("itemid", model.getItemid());
                            intent.putExtra("sn", shop);
                            startActivity(intent);
                        }
                        if (which == 1) {
                            cartlistref.child("Cart List").child(model.getItemid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(mycart.this, ""+model.getPname()+"Item removed from your cart!", Toast.LENGTH_SHORT).show();
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
    }
}



//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent i = new Intent(v.getContext(),demoshop.class);
//                //i.putExtra("title",data.get(getAdapterPosition()));
//                Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                //v.getContext().startActivity(i);
//                Log.i("TAG", "inside Onclicklistener up:");
//            }
//        });


//        itemView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (position ==  0) {
//                    Toast.makeText(mycart.this, "Facebook Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  0) {
//                    Toast.makeText(mycart.this, "Whatsapp Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  0) {
//                    Toast.makeText(mycart.this, "Twitter Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  0) {
//                    Toast.makeText(mycart.this, "Instagram Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  0) {
//                    Toast.makeText(mycart.this, "Youtube Description", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    /*@Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);

    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }*/


//    @Override
//    public void onClick(View v) {
//        Toast.makeText(v.getContext(), "Clicked ->>> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
//        Log.i("TAG", "inside Onclicklistener down:");
//
//
//    }




    //private ItemClickListener itemClickListener;

    /*private LayoutInflater layoutInflater;
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
            /*textTitle = itemView.findViewById(R.id.cartname);
            textDescription = itemView.findViewById(R.id.cartdesc);
        }
    }
    //public void openshopbuttonactivity() {
    //Intent intent = new Intent(this, demoshop.class);
    //startActivity(intent);*/




