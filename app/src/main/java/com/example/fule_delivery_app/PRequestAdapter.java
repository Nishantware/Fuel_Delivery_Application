package com.example.fule_delivery_app;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class PRequestAdapter extends RecyclerView.Adapter<PRequestAdapter.ViewHolder> {



    ArrayList<PetrolRequest> list;
    ArrayList<PetrolRequest> fullList;
    Context context;


    public PRequestAdapter(Context context, ArrayList<PetrolRequest> list) {
        this.context = context;
        this.list = list;
        this.fullList = new ArrayList<>(list); // Backup list for searching
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.request_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        PetrolRequest model = list.get(position);

        holder.txtName.setText("Name: " + model.name);
        holder.txtNumber.setText("Phone: " + model.number);
        holder.txtCar.setText("Car: " + model.carNumber);
        holder.txtType.setText("Type: " + model.type);
        holder.txtQty.setText("Qty: " + model.quantity + " L");
        holder.txtLocation.setText("Location: " + model.latitude + ", " + model.longitude);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+model.number));
                v.getContext().startActivity(intent);
            }
        });

        holder.imgsend.setOnClickListener(new View.OnClickListener() {
            @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
            @Override
            public void onClick(View v) {

                String username = model.getName();
                String number = model.getNumber();
                String type = model.getType();
                String quantity = String.valueOf(model.getQuantity());

                // Location values
                double latitude = Double.parseDouble(String.valueOf(model.getLatitude()));
                double longitude = Double.parseDouble(String.valueOf(model.getLongitude()));

                String locationText = "Lat: " + latitude + ", Lon: " + longitude;

                // Create Notification Channel
                NotificationHelper.createNotificationChannel(context);

                // Create URI to open Google Maps
                String geoUri = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(geoUri));
                mapIntent.setPackage("com.google.android.apps.maps"); // open directly in google maps

                PendingIntent pendingIntent = PendingIntent.getActivity(
                        context,
                        0,
                        mapIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );

                // Create Notification Manager
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                // Notification text
                String message = "Name: " + username +
                        "\nNumber: " + number +
                        "\nType: " + type +
                        "\nQuantity: " + quantity +
                        "\nLocation: " + locationText;

                // Build Notification
                androidx.core.app.NotificationCompat.Builder builder =
                        new androidx.core.app.NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
                                .setSmallIcon(R.drawable.fule)
                                .setContentTitle("New Order Request")
                                .setContentText("Tap to open location")
                                .setStyle(new androidx.core.app.NotificationCompat.BigTextStyle().bigText(message))
                                .setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)
                                .setContentIntent(pendingIntent) // <<< open maps on tap
                                .setAutoCancel(true);

                // Show Notification
                int notificationId = (int) System.currentTimeMillis();
                notificationManager.notify(notificationId, builder.build());

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("9527586442", null, "New Order"+message, null, null);


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

            ArrayList<PetrolRequest> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(fullList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PetrolRequest item : fullList) {
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
            list.addAll((ArrayList<PetrolRequest>) results.values);
            notifyDataSetChanged();
        }


    };



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtNumber, txtCar, txtType, txtQty, txtLocation;

        ImageView img,imgsend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            txtCar = itemView.findViewById(R.id.txtCar);
            txtType = itemView.findViewById(R.id.txtType);
            txtQty = itemView.findViewById(R.id.txtQty);
            txtLocation = itemView.findViewById(R.id.txtLocation);
            img = itemView.findViewById(R.id.imglocation);
            imgsend = itemView.findViewById(R.id.imgsend);


        }
    }
}
