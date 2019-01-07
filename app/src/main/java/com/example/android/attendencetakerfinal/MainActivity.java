package com.example.android.attendencetakerfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton attendence=(ImageButton) findViewById(R.id.attendence);
        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Attendence.class);
                startActivity(intent);
            }
        });

        ImageButton reportShow=(ImageButton) findViewById(R.id.report);
        reportShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,reportTableInfo.class);
                startActivity(intent);
            }
        });



    }
}
