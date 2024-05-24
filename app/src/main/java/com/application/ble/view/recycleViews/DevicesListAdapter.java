package com.application.ble.view.recycleViews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.application.ble.R;
import com.application.ble.model.DevicesModel;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DevicesListAdapter extends RecyclerView.Adapter<DevicesListHolder> {

    ArrayList<DevicesModel> list;
    Consumer<Integer> listener;
    int id;

    public DevicesListAdapter(ArrayList<DevicesModel> list, Consumer<Integer> listener) {
        this.list = list;
        this.listener = listener;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @Override
    public DevicesListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.devices_view_holder, parent, false);
        return new DevicesListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesListHolder holder, int position) {
        holder.txtDevicesName.setText(String.valueOf(list.get(position).getName()));
//        holder.layout.setBackgroundColor(androidx.cardview.R.color.cardview_dark_background);
//        holder.btnConnect.setText(String.valueOf(list.get(position).getConnectStatus()));
//        if (position == operationNum){
//            holder.layout.setBackgroundColor(R.color.purple_200);
//        } else {
//            holder.layout.setBackgroundColor(androidx.cardview.R.color.cardview_dark_background);
//        }
        holder.btnConnect.setOnClickListener(view -> listener.accept(position));
//        holder.layout.setOnClickListener(view -> listener.accept(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
