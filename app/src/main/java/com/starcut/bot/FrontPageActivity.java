package com.starcut.bot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.starcut.bot.Map.MapsActivity;

/**
 * Created by Ankush on 9/27/17.
 */

public class FrontPageActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);
    }


    void showSimpleQuestionButtonAction(View v) {

        Intent myIntent = new Intent(FrontPageActivity.this, DetailQuestionActivity.class);
        startActivity(myIntent);
    }

    void showMap(View v) {

        Intent myIntent = new Intent(FrontPageActivity.this, MapsActivity.class);
        startActivity(myIntent);
    }
}
