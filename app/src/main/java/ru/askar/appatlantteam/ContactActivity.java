package ru.askar.appatlantteam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Сайида on 24.10.2017.
 */

public class ContactActivity extends AppCompatActivity {
    Button btnBack, btnNext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnBack.setClickable(true);
        btnNext.setClickable(false);
        btnBack.setBackgroundColor(Color.rgb(0,255,0));
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveBack();
            }
        });
    }

    public void moveBack(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
