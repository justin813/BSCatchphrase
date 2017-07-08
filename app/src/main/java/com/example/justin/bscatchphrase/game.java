package com.example.justin.bscatchphrase;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.CollationElementIterator;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.R.attr.button;
import static android.R.attr.start;
import static android.R.attr.value;

public class game extends AppCompatActivity implements View.OnClickListener {

    private static String[] word_list;
    private  TextView currentWord, team1score, team2score, catagoryLabel;
    private  Button next_button, bs_button, add1, add2, startButton;

    private int team1_total = 0;
    private int team2_total = 0;
    private int counter = 0;
    TextView timerTextView;
    long startTime = 0;
    Intent i = new Intent(this, victory_screen.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        team1score = (TextView)findViewById(R.id.score1);
        team2score = (TextView)findViewById(R.id.score2);

        currentWord = (TextView)findViewById(R.id.currentWord);

        next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(this);

        bs_button = (Button) findViewById(R.id.bs_button);
        bs_button.setOnClickListener(this);

        add1 = (Button) findViewById(R.id.add1);
        add1.setOnClickListener( this);

        add2 = (Button) findViewById(R.id.add2);
        add2.setOnClickListener(this);

        startButton = (Button) findViewById(R.id.startButton);
        //startButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        Bundle b2 = this.getIntent().getExtras();
        word_list = b2.getStringArray("word_list");
        String catagoryName = this.getIntent().getExtras().getString("catagory");
        catagoryLabel = (TextView)findViewById(R.id.catagoryLabel);
        catagoryLabel.setText(catagoryName);

        //countdown timer idk a better place to put this for now going to test it here
        final TextView textic = (TextView) findViewById(R.id.timerTextView2);

        final CountDownTimer Count = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                textic.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textic.setText("Finished");
            }
        };

        startButton.setOnClickListener(


                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {

                        Count.start();
                        currentWord.setText(word_list[++counter]);

                    }
                }
        );


    }

    public void onClick(View v){

        switch(v.getId())
        {
            case R.id.next_button:
            {
                counter++;
                if(counter >= word_list.length){

                    counter = 0;
                }

                currentWord.setText(word_list[counter]);
                break;
            }

            case R.id.bs_button:
            {
                if(counter == 0){
                    currentWord.setText(word_list[word_list.length - 1]);
                } else {
                    currentWord.setText(word_list[counter - 1]);
                }
                break;
            }

            case R.id.add1:
            {
                team1_total++;
                team1score.setText("" + team1_total);
                if(team1_total == 7) {

                    Intent j = new Intent(this, victory_screen.class);
                    j.putExtra("winner", "Team 1");
                    startActivity(j);


                }
                break;
            }

            case R.id.add2:
            {
                team2_total++;
                team2score.setText("" + team2_total);
                if(team2_total == 7) {

                    Intent j = new Intent(this, victory_screen.class);
                    j.putExtra("winner", "Team 2");
                    startActivity(j);


                }
                break;
            }


        }
    }


}


