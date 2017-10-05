package com.starcut.bot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.starcut.bot.Map.MapsActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final List<String> input = Arrays.asList(getResources().getStringArray(R.array.items));


        mAdapter = new RecycleriVewAdapter(input, new RecycleriVewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {

                Logger.i(item);

                switch (input.indexOf(item))
                {
                    case 0:
                        Intent myIntent = new Intent(RecyclerViewActivity.this, MapsActivity.class);
                        startActivity(myIntent);
                        break;

                    case 1:
                        myIntent = new Intent(RecyclerViewActivity.this, DetailQuestionActivity.class);
                        startActivity(myIntent);
                        break;
                }


            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}
