package com.example.streetartapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    private final ArrayList<Art_data> ArtDataList;
    private ArrayList<Art_data> trainDataList;
    private Context context;
    private View.OnClickListener RecAdapterListener;

    public RecycleAdapter(ArrayList<Art_data> ArtDataList, Context context) {
        this.ArtDataList = ArtDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list__art_item, parent, false);

        return new RecycleAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {

        holder.item_id.setText("Art Id: " + ArtDataList.get(position).getArt_id());
        holder.item_name.setText("Art Name: " + ArtDataList.get(position).getArt_name());
        holder.item_style.setText("Style of Art: " + ArtDataList.get(position).getArt_style());
        holder.item_period.setText("Period Of Art: " + ArtDataList.get(position).getArt_period());
        holder.item_date.setText("Creation date: " + ArtDataList.get(position).getArt_date());
        holder.item_summary.setText("Summary: " + ArtDataList.get(position).getArt_summary());
        holder.item_address.setText("Address: " + ArtDataList.get(position).getArt_address());
        holder.item_cname.setText("Category name: " + ArtDataList.get(position).getArt_cname());


    }

    public void setOnClickListener(View.OnClickListener clickListener) {

        RecAdapterListener = clickListener;

    }

    @Override
    public int getItemCount() {
        return ArtDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView item_id, item_name, item_style, item_period, item_date, item_summary, item_address, item_cname;

        public ViewHolder(View itemView) {
            super(itemView);

            item_id = itemView.findViewById(R.id.item_id);
            item_name = itemView.findViewById(R.id.item_name);
            item_style = itemView.findViewById(R.id.item_style);
            item_period = itemView.findViewById(R.id.item_period);
            item_date = itemView.findViewById(R.id.item_date);
            item_summary = itemView.findViewById(R.id.item_summary);
            item_address = itemView.findViewById(R.id.item_address);
            item_cname = itemView.findViewById(R.id.item_cname);

            itemView.setTag(this);

            itemView.setOnClickListener(RecAdapterListener);

        }
    }
}




