package com.example.fule_delivery_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {


    ArrayList<GarajeRequest> list;
    ArrayList<GarajeRequest> fullList;
    Context context;

    public RequestAdapter(Context context, ArrayList<GarajeRequest> list) {
        this.context = context;
        this.list = list;
        this.fullList = new ArrayList<>(list); // Backup list for searching
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.garaje_request_list, parent, false);
        return new RequestAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GarajeRequest model = list.get(position);

        holder.txtName.setText("Name: " + model.name);
        holder.txtNumber.setText("Phone: " + model.number);
        holder.txtCar.setText("Car: " + model.carNumber);
        holder.txtType.setText("Description: " + model.type);

        holder.imglocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uri = "https://www.google.com/maps/search/?api=1&query=" + model.latitude + "," + model.longitude;

                Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(uri));

                context.startActivity(intent);
            }
        });

        holder.imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+model.number));
                v.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ---- FILTER LOGIC ----

    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<GarajeRequest> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GarajeRequest item : fullList) {
                    if (item.type.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<GarajeRequest>) results.values);
            notifyDataSetChanged();
        }


    };


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtNumber, txtCar, txtType, txtQty, txtLocation;

        ImageView imglocation,imgcall;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            txtCar = itemView.findViewById(R.id.txtCar);
            txtType = itemView.findViewById(R.id.txtType);

            txtLocation = itemView.findViewById(R.id.txtLocation);
            imglocation = itemView.findViewById(R.id.imglocation);
            imgcall = itemView.findViewById(R.id.imgcall);



        }
    }


}
