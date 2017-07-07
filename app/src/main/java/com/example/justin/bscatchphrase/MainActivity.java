package com.example.justin.bscatchphrase;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    private String[] meme_list = {"Dat boi", "One does not simply", "Arrow to the knee", "Ermagherd", "trololololol", "Pepe the frog","Nyan cat", "Harlem shake",
                                    "Grumpy Cat", "Gangnam Style", "Good Guy Greg", "Double Rainbow", "U mad bro?", "Numa Numa","Honey Badger", "Doge"};
    private String[] cartoon_list = {"Rugrats","Doug","Pokemon","Duck Tales","Dexters Laboratory","Hey Arnold","Captain Planet","Johnny Bravo",
                                    "Taz-Mania","Animaniacs","Beavis and Butt-Head","2 Stupid Dogs","Roco\'s Modern Life","Spider-Man","Arthur","Johnny Bravo"};

    private static RadioGroup radioGroup;
    private static RadioButton radioButton1, radioButton2;
    private static Button button;
    private static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickListenerButton();
    }

    public void onClickListenerButton() {

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        int selected_id = radioGroup.getCheckedRadioButtonId();
                        button = (RadioButton)findViewById(selected_id);
                        Toast.makeText(MainActivity.this, button.getText().toString(), Toast.LENGTH_SHORT).show();
                        textView = (TextView)findViewById(R.id.textView);
                        //textView.setText("" + selected_id);
                        startGame(button.getText().toString());
                    }
                }
        );
    }

    public void startGame(String x){

        //create a bundle to store the list of words for the game to use
        Bundle b=new Bundle();
        if(x.equals("Memes")){
            b.putStringArray("word_list", meme_list);
        } else {
            b.putStringArray("word_list", cartoon_list);
        }

        Intent i=new Intent(this, game.class);
        i.putExtras(b);
        i.putExtra("catagory", x);
        Intent myIntent = new Intent(this, game.class);
        myIntent.putExtra("word_list", meme_list); //Optional parameters
        this.startActivity(i);


    }

}
