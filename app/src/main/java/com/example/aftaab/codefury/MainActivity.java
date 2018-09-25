package com.example.aftaab.codefury;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        final Bundle bundle;
        bundle=getIntent().getExtras();

        Button emergency_btn=findViewById(R.id.emergency_btn);
        Button complaints_btn=findViewById(R.id.complaints_btn);

        emergency_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, EmergencyActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        complaints_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ComplaintActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
