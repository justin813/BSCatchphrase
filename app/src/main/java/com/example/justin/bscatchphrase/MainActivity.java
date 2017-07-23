package com.example.justin.bscatchphrase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import static android.R.attr.buttonBarButtonStyle;
import static android.R.attr.text;
import static android.R.attr.value;
import static android.app.PendingIntent.getActivity;
import static com.example.justin.bscatchphrase.R.id.bs_button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] meme_list = {"Dat boi", "One does not simply", "Arrow to the knee", "Ermagherd", "trololololol", "Pepe the frog","Nyan cat", "Harlem shake",
                                    "Grumpy Cat", "Gangnam Style", "Good Guy Greg", "Double Rainbow", "U mad bro?", "Numa Numa","Honey Badger", "Doge"};
    private String[] nine_cartoon_list = {"Rugrats","Doug","Pokemon","Duck Tales","Dexters Laboratory","Hey Arnold","Captain Planet","Johnny Bravo",
                                    "Taz-Mania","Animaniacs","Beavis and Butt-Head","2 Stupid Dogs","Roco\'s Modern Life","Spider-Man","Arthur","Johnny Bravo"};
    private String[] nineties_movies_list = {"Goodfellas", "The Big Lebowski", "The Shawshank Redemption", "Forest Gump", "Se7en", "Titanic", "True Lies", "Pulp Fiction",
                                    "The Matrix", "The Sixth Sense", "Fight Club", "The Mask", "Payback", "Double Jeopardy", "Schindler's List", "Reservoir Dogs"};

    private static RadioGroup radioGroup;
    private static RadioButton radioButton1, radioButton2;
    private static Button button;
    private static TextView textView;
    private static Button rules_info_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        rules_info_button = (Button) findViewById(R.id.rules_info_button);
        rules_info_button.setOnClickListener(this);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        button = (Button)findViewById(R.id.button);
        rules_info_button = (Button) findViewById(R.id.rules_info_button);


    }


    public void startGame(String x){

        //create a bundle to store the list of words for the game to use
        Bundle b=new Bundle();
        if(x.equals("Memes")){
            b.putStringArray("word_list", meme_list);
        } else if(x.equals("90's Cartoons")){
            b.putStringArray("word_list", nine_cartoon_list);
        } else {
            b.putStringArray("word_list", nineties_movies_list);
        }

        Intent i=new Intent(this, game.class);
        i.putExtras(b);
        i.putExtra("catagory", x);
        Intent myIntent = new Intent(this, game.class);
        myIntent.putExtra("word_list", meme_list); //Optional parameters
        this.startActivity(i);


    }


    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            //start game button
            case R.id.button:
            {
                int selected_id = radioGroup.getCheckedRadioButtonId();
                button = (RadioButton)findViewById(selected_id);
                Toast.makeText(MainActivity.this, button.getText().toString(), Toast.LENGTH_SHORT).show();
                textView = (TextView)findViewById(R.id.textView);
                //textView.setText("" + selected_id);
                startGame(button.getText().toString());
                break;
            }

            case R.id.rules_info_button:
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle(R.string.Rules_info);
                builder.setMessage(R.string.Rules);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }
        }

    }

}
