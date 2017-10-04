package com.starcut.bot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ankush on 9/29/17.
 */

public class DetailQuestionActivity extends AppCompatActivity {

    String[] questions;
    String[] answers;
    String TAG = "DetailQuestionActivity";

    int  index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_question);


        Toolbar toolbar = (Toolbar) findViewById(R.layout.tool_bar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.top_bar);


        updateUI();

    }

    void leftButtonAction(View v) {

        if (index > 0){

            index--;

            updateUI();

        }

    }

    void rightButtonAction(View v) {

        if (index < questions.length-1) {

            index++;

           updateUI();
        }
    }

    void updateUI(){

        questions = getResources().getStringArray(R.array.questions);
        answers = getResources().getStringArray(R.array.answers);

        TextView questionTextView = (TextView) findViewById(R.id.textViewQuestion);
        TextView answerTextView = (TextView) findViewById(R.id.textViewAnswer);
        TextView titelTextView = (TextView) findViewById(R.id.textViewTitle);

        questionTextView.setText(questions[index]);
        answerTextView.setText(answers[index]);

        titelTextView.setText(index + "/" + (questions.length-1));
    }

}
