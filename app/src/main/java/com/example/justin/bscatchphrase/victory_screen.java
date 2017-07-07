package com.example.justin.bscatchphrase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class victory_screen extends AppCompatActivity {
    private static TextView victorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victory_screen);

        String victor = this.getIntent().getExtras().getString("winner");
        victorText = (TextView)findViewById(R.id.catagoryLabel);
        victorText.setText(victor);
    }
}
