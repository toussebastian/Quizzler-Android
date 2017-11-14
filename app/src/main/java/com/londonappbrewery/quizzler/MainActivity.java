package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {



    // TODO: Declare member variables here:

    Button true_botton;
    Button false_button;
    TextView mQuestionView;
    int mIndex;
    int mScore;
    int mQuestion;
    TextView mScoreTextView;
    ProgressBar mProgressBar;


    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };

    // TODO: Declare constants here
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil(100.0/ mQuestionBank.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");

        }else {
            mScore = 0;
            mIndex = 0;
        }

        true_botton = (Button) findViewById(R.id.true_button);
        false_button = (Button) findViewById(R.id.false_button);
        mQuestionView = (TextView) findViewById(R.id.question_text_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mScoreTextView = (TextView) findViewById(R.id.score);

        mQuestion = mQuestionBank[mIndex].getQuestionID();

        mQuestionView.setText(mQuestion);
        mScoreTextView.setText("Score" + mScore + "/" + mQuestionBank.length);


        true_botton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
                Indexupdate();

            }
        });

        false_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(false);
                Indexupdate();

            }
        });

    }

    public void Indexupdate(){
        mIndex = (mIndex + 1) % mQuestionBank.length;

        if(mIndex == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your Score" + mScore + "Points!");
            alert.setPositiveButton("Close Applicacion", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score" + mScore + "/" + mQuestionBank.length);

    }

    public void checkAnswer( boolean userSelection){

        if (userSelection == mQuestionBank[mIndex].isAnswer()) {
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_LONG).show();
            mScore = mScore + 1;
        }else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey",mScore);
        outState.putInt("IndexKey", mIndex);



    }
}
