package com.example.itprototypezero;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.itprototypezero.adapter.TaskAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import java.util.Locale;
import java.util.Random;
import java.sql.Timestamp;
import java.time.Instant;

public class ActivityMainAssignedTask extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<AssignedTaskList> assignedTaskList;

    /**
     * Give value of Number of Assigned Task From Database to int numOfAssignedTasks
     */

    private int numOfAssignedTasks = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_assigned_task_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        assignedTaskList = new ArrayList<>();
        adapter = new TaskAdapter(this, assignedTaskList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareTasks();

        try {
            Glide.with(this).load(R.drawable.taskgear).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.backdrop_title_taskList));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }


    private void prepareTasks() {

        AssignedTaskList a;

        for(int i = 0; i < numOfAssignedTasks; i++){

            Random random = new Random();                    // Remove random variable when task ID is taken from Database

            int task_ID = random.nextInt(1500);                 // Enter the Unique Task ID here from Database
            String card_title = "Task ID: " + task_ID;

            //The commented code was done by Shreyansh,Vaibhav updated to convert timestamp
            // to dd/mm/yy hh::mm e.g 28/04/2020 08:22 PM
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Calendar calendar1 = Calendar.getInstance(Locale.getDefault());
            calendar1.setTimeInMillis(Long.parseLong(timestamp.toString()));
            String dateTime = ""+ DateFormat.format("dd/MM/yyyy hh:mm:aa",calendar1);

//           Timestamp timestamp = new Timestamp(System.currentTimeMillis());  // Remove this random timestamp when database is connected
//            String dateTime = "Date: " + timestamp.toString();       // Enter timestamp here from Database

            a = new AssignedTaskList(card_title, dateTime, R.drawable.gears512);
            assignedTaskList.add(a);
        }

        adapter.notifyDataSetChanged();
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public void TaskDetails(View view){
        Intent intent = new Intent(this, TaskCardDetails.class);
        startActivity(intent);
        Toast.makeText(ActivityMainAssignedTask.this,"Task Details",Toast.LENGTH_SHORT).show();
    }
}
