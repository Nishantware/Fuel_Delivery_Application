package com.example.fule_delivery_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PetrolpumpAdapter extends RecyclerView.Adapter<PetrolpumpAdapter.ViewHolder> {


    private Context context;
    private List<Petrolpump> doctorList;
    private List<Petrolpump> fullList;

    public PetrolpumpAdapter(Context context, List<Petrolpump> doctorList) {
        this.context = context;
        this.doctorList = doctorList;
        this.fullList = new ArrayList<>(doctorList);
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.petrolpump_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Petrolpump d = doctorList.get(position);


        holder.tvName.setText("Name:- "+d.getName());
        holder.tvAddress.setText("Address:- "+d.getAddress());
        holder.tvType.setText("Time:- "+d.getTime());
        holder.tvNumber.setText("Number:- "+d.getNumber());
        holder.tvCharges.setText("Charges:- "+d.getCharges());


        if (d.getImageuri() != null) {
            holder.doctorImage.setImageURI(Uri.parse(d.getImageuri()));
        }
        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+d.getNumber()));
                v.getContext().startActivity(intent);
            }
        });

        holder.ivRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,fuelrequest.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public void filter(String keyword) {
        keyword = keyword.toLowerCase();
        doctorList.clear();


        if (keyword.isEmpty()) {
            doctorList.addAll(fullList);
        } else {
            for (Petrolpump d : fullList) {
                if (d.getName().toLowerCase().contains(keyword) ||
                        d.getAddress().toLowerCase().contains(keyword)) {
                    doctorList.add(d);
                }
            }
        }


        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView doctorImage, ivCall,ivRequest;
        TextView tvName, tvAddress, tvType,tvNumber,tvCharges;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            doctorImage = itemView.findViewById(R.id.doctorImage);
            ivCall = itemView.findViewById(R.id.ivCall);


            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvType = itemView.findViewById(R.id.tvType);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvCharges = itemView.findViewById(R.id.tvCharges);
            ivRequest = itemView.findViewById(R.id.ivrequest);
        }
    }
}
