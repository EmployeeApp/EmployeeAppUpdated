package com.example.itprototypezero.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itprototypezero.R;
import com.example.itprototypezero.TaskCardDetails;
import com.example.itprototypezero.TaskContent;

public class TaskContentAdapter extends RecyclerView.Adapter<TaskContentAdapter.HolderTask>{

    private Context context;
    private ArrayList<TaskContent> taskContent;

    public TaskContentAdapter(Context context, ArrayList<TaskContent> taskContent) {
        this.context = context;
        this.taskContent = taskContent;
    }

    @NonNull
    @Override
    public HolderTask onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
//        View view = LayoutInflater.from(context).inflate(R.layout.task_card, parent,false);
//
//        return new HolderTask(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTask holder, int position) {
        //get data, set data, handle view clicks in this method

        //get data
        TaskContent task = taskContent.get(position);
        final String taskID = task.getTaskId();
        String organisationID = task.getOrganisationID();
        String inventoryName = task.getInventoryName();
        String inventoryID = task.getInventoryID();
        String location = task.getLocation();
        String remarks = task.getRemarks();
        long addedCost = task.getAddedCost();

        holder.organisationIdTv.setText(organisationID);
        holder.inventoryNameTv.setText(inventoryName);
        holder.inventoryIdTv.setText(inventoryID);
        holder.locationTv.setText(location);
        holder.remarksTv.setText(remarks);
        holder.addedCostTv.setText(String.valueOf(addedCost));

        //handle item clicks
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //pass task id to next activity to show details of that task
                Intent intent = new Intent(context, TaskCardDetails.class);
                intent.putExtra("TASK_ID",taskID);
                context.startActivity(intent);
            }
        });

//        //handle more button click listener
//        holder.

    }

    @Override
    public int getItemCount() {
        return taskContent.size();
    }

    class HolderTask extends RecyclerView.ViewHolder {
        //views
        TextView organisationIdTv,inventoryNameTv,inventoryIdTv,locationTv,remarksTv,addedCostTv;

        public HolderTask(@NonNull View itemView) {
            super(itemView);

            //init views
            organisationIdTv = itemView.findViewById(R.id.organistationIdTv);
            inventoryNameTv = itemView.findViewById(R.id.inventoryNameTv);
            inventoryIdTv = itemView.findViewById(R.id.inventoryIdTv);
            locationTv = itemView.findViewById(R.id.locationTv);
            remarksTv = itemView.findViewById(R.id.remarksTv);
            addedCostTv = itemView.findViewById(R.id.addedCostTv);

        }
    }


}
