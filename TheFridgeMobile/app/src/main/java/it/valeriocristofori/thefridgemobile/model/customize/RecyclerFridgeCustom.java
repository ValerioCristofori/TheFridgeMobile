package it.valeriocristofori.thefridgemobile.model.customize;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import it.valeriocristofori.thefridgemobile.R;
import it.valeriocristofori.thefridgemobile.model.entity.Food;

public class RecyclerFridgeCustom extends RecyclerView.Adapter<RecyclerFridgeCustom.MyViewHolder> {

    private Context context;
    private ArrayList<Food> listFood;
    private int images[];


    public RecyclerFridgeCustom(Context context, ArrayList<Food> listFood, int images[]) {
        this.context = context;
        this.listFood = listFood;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerFridgeCustom.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.food_box_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerFridgeCustom.MyViewHolder holder, int position) {
        holder.tvFridgeFood.setText(listFood.get(position).getName());
        holder.ivFridgeFood.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return this.listFood.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvFridgeFood;
        ImageView ivFridgeFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFridgeFood = itemView.findViewById(R.id.tvFridgeFood);
            ivFridgeFood = itemView.findViewById(R.id.ivFridgeFood);

        }
    }



}
