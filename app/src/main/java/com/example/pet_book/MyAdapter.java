package com.example.pet_book;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

    public MyAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataClass data = dataList.get(position);

        Glide.with(context).load(data.getUploadImage()).into(holder.recImage);
        holder.recCategory.setText(data.getUploadCategory());
        holder.recName.setText(data.getUploadName());
        holder.recAgg.setText(data.getUploadAgg());
        holder.recBreeding.setText(data.getUploadBreeding());
        holder.recSex.setText(data.getUploadSex());
        holder.recColor.setText(data.getUploadColor());
        holder.recNote.setText(data.getUploadNote());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getUploadImage());
                intent.putExtra("Category", dataList.get(holder.getAdapterPosition()).getUploadCategory());
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getUploadName());
                intent.putExtra("Age", dataList.get(holder.getAdapterPosition()).getUploadAgg());
                intent.putExtra("Breed", dataList.get(holder.getAdapterPosition()).getUploadBreeding());
                intent.putExtra("Sex", dataList.get(holder.getAdapterPosition()).getUploadSex());
                intent.putExtra("Color", dataList.get(holder.getAdapterPosition()).getUploadColor());
                intent.putExtra("Note", dataList.get(holder.getAdapterPosition()).getUploadNote());

                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView recImage;
    TextView recCategory, recName, recAgg, recBreeding, recSex, recColor, recNote;

    CardView recCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        recImage =itemView.findViewById(R.id.recImage);
        recCard =itemView.findViewById(R.id.recCard);
        recCategory=itemView.findViewById(R.id.recCategory);
        recName =itemView.findViewById(R.id.recName);
        recAgg =itemView.findViewById(R.id.recAgg);
        recBreeding =itemView.findViewById(R.id.recBreeding);
        recSex =itemView.findViewById(R.id.recSex);
        recColor =itemView.findViewById(R.id.recColor);
        recNote =itemView.findViewById(R.id.recNote);

    }
}
