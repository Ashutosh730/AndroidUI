package com.example.androidui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private Button True;
    private Button False;
    private int qIndex;
    private int quizQuestion;
    private ProgressBar mbar;
    private TextView stat;
    private  int score;
    private final String SCORE_KEY="SCORE";
    private final String INDEX_KEY="INDEX";

    private QuizModel[] questioncollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,true),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,true),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,true),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,true),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,true)
    };

    final int bar=(int)100/10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if(savedInstanceState!=null)
            {
                score=savedInstanceState.getInt(SCORE_KEY);
                qIndex=savedInstanceState.getInt(INDEX_KEY);
            }
            else
            {
                score=0;
                qIndex=0;
            }

            txt=findViewById(R.id.quiz);
            True = findViewById(R.id.True);
            False = findViewById(R.id.False);
            stat=findViewById(R.id.stat);
            stat.setText(score+"");
            mbar=findViewById(R.id.progress);

            QuizModel q = questioncollection[qIndex];

            True.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    evaluation(true);
                    questionChange();

                }
            });

            False.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    evaluation(false);
                    questionChange();
                }
            });
    }

    private void questionChange()
    {
        qIndex=(qIndex+1)%10;
        if(qIndex==0)
        {
            AlertDialog.Builder quizAlert=new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("QUIZ is finished");
            quizAlert.setMessage("Your Score is "+ score);
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
        }
        quizQuestion=questioncollection[qIndex].getQuestion();
        txt.setText(quizQuestion);
        mbar.incrementProgressBy(bar);
    }

    private void evaluation(boolean userans)
    {
        if(userans==questioncollection[qIndex].isAns())
        {
            Toast.makeText(getApplicationContext(), "GREAT", Toast.LENGTH_SHORT).show();
            score++;
            stat.setText(score+"");
        }
        else
            Toast.makeText(getApplicationContext(), "OOPs", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("SCORE_KEY",score);
        outState.putInt("INDEX_KEY",qIndex);
    }
}