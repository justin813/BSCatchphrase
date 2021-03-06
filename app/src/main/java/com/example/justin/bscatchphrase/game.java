package com.example.justin.bscatchphrase;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class game extends AppCompatActivity implements View.OnClickListener {

    private static String[] word_list;
    private  TextView currentWord, team1score, team2score, catagoryLabel, timerTextView2;
    private  Button next_button, bs_button, add1, add2, startButton;
    private  boolean roundActive = false;
    private  boolean viewingBS = false;
    private  CountDownTimer Count;

    private int oldColor;
    private boolean pointAwarded = false;
    private boolean firstPoint = true;
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
        timerTextView2 = (TextView)findViewById(R.id.timerTextView2);
        currentWord = (TextView)findViewById(R.id.currentWord);

        next_button = (Button) findViewById(R.id.next_button);
        next_button.setOnClickListener(this);

        bs_button = (Button) findViewById(R.id.bs_button);
        bs_button.setOnClickListener(this);

        add1 = (Button) findViewById(R.id.add1);
        add1.setOnClickListener(this);

        add2 = (Button) findViewById(R.id.add2);
        add2.setOnClickListener(this);

        startButton = (Button) findViewById(R.id.startButton);

        Bundle extras = getIntent().getExtras();
        Bundle b2 = this.getIntent().getExtras();
        word_list = b2.getStringArray("word_list");
        String catagoryName = this.getIntent().getExtras().getString("catagory");
        catagoryLabel = (TextView)findViewById(R.id.catagoryLabel);
        catagoryLabel.setText(catagoryName);



        //countdown timer idk a better place to put this for now going to test it here
        final TextView textic = (TextView) findViewById(R.id.timerTextView2);

        Count = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                textic.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                textic.setText("Finished");
                roundActive = false;
            }
        };

        startButton.setOnClickListener(


                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        //if round is active the button acts as stop
                        if(roundActive){
                            stopRound();
                        } else { // else it acts as start
                            //cancel any score that gets added before the game
                            if(firstPoint){
                                firstPoint = false;
                                team1_total = 0;
                                team2_total = 0;
                                team1score.setText("" + team1_total);
                                team2score.setText("" + team2_total);
                            }
                            Count.start();
                            roundActive = true;
                            pointAwarded = false;

                            if(!viewingBS){
                                currentWord.setText(word_list[++counter]);
                            } else {
                                currentWord.setText(word_list[++counter]);
                            }
                            viewingBS = false;

                            currentWord.setTextColor(Color.GRAY);
                        }

                    }
                }
        );


    }

    private void stopRound() {
        Count.cancel();
        timerTextView2.setText("Stopped");
        roundActive = false;
    }

    //This method will handle any actions associated with buttons on the screen to add more buttons
    //initialize them above and add them as a case.
    public void onClick(View v){

        switch(v.getId())
        {
            case R.id.next_button:
            {
                //if you are checking for bs next reverts the word to the current word instead of the next one
                if(viewingBS){

                    currentWord.setText(word_list[counter]);
                    currentWord.setTextColor(Color.GRAY);
                    viewingBS = false;
                    break;

                }

                //only allow moving words if a round is active
                if (roundActive) {

                counter++;

                //loop back around in the list if you are at the end
                if (counter >= word_list.length) {
                    counter = 0;
                }

                //update current word
                currentWord.setText(word_list[counter]);
                }

                break;
            }

            case R.id.bs_button:
            {

                stopRound();
                if(counter == 0){
                    currentWord.setText(word_list[word_list.length - 1]);

                } else {
                    currentWord.setText(word_list[counter - 1]);
                }
                currentWord.setTextColor(Color.RED);
                viewingBS = true;
                break;
            }

            case R.id.add1:
            {
                //Points can only be added at the end of a round
                if(!roundActive){

                    if(!pointAwarded) {

                        team1_total++;
                        team1score.setText("" + team1_total);
                        if(team1_total == 7 && !firstPoint) {

                            Intent j = new Intent(this, victory_screen.class);
                            j.putExtra("winner", "Team 1");
                            startActivity(j);


                        }
                        pointAwarded = true;
                    }


                }

                break;
            }

            case R.id.add2:
            {
                //Points can only be added at the end of a round



                    if (!roundActive) {

                        if(!pointAwarded){
                            team2_total++;
                            team2score.setText("" + team2_total);
                            if (team2_total == 7 && !firstPoint) {

                                Intent j = new Intent(this, victory_screen.class);
                                j.putExtra("winner", "Team 2");
                                startActivity(j);
                            }
                            pointAwarded = true;
                        }


                    }

                break;
            }


        }
    }


}


