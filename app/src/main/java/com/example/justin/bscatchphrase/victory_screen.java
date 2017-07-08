package com.example.justin.bscatchphrase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class victory_screen extends AppCompatActivity implements View.OnClickListener {
    private static TextView victorText;
    private Button return_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory_screen);

        String victor = this.getIntent().getExtras().getString("winner");
        victorText = (TextView)findViewById(R.id.victorText);
        return_button = (Button) findViewById(R.id.return_button);
        return_button.setOnClickListener(this);
        victorText.setText(victor);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.return_button:
            {
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
