package com.example.itprototypezero.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.itprototypezero.AssignedTaskList;
import com.example.itprototypezero.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private Context mContext;
    private List<AssignedTaskList> assignedTaskList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, timestamp;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            timestamp = (TextView) view.findViewById(R.id.timestamp);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public TaskAdapter(Context mContext, List<AssignedTaskList> assignedTaskList) {
        this.mContext = mContext;
        this.assignedTaskList = assignedTaskList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        AssignedTaskList assignedTask = assignedTaskList.get(position);
        holder.title.setText(assignedTask.getName());
        holder.timestamp.setText(assignedTask.getDateTime());

        Glide.with(mContext).load(assignedTask.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow, position);
            }
        });
    }


    /* Showing popup menu when tapping on 3 dots*/

    private void showPopupMenu(View view, int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_task_card, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }


    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        int position;

        public MyMenuItemClickListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.action_remove_task:

                    assignedTaskList.remove(position);

                    /**
                     * Update the DataBase here so that ArrayList can be updated completely and notify admin about the same
                     */

                    notifyDataSetChanged();
                    Toast.makeText(mContext, "Task Removed", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return assignedTaskList.size();
    }
}
