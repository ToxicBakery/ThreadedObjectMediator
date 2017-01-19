package com.ToxicBakery.thread.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    IWriteStuff writeStuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.click_me).setOnClickListener(this);

        writeStuff = new WriteStuffMediator();
    }

    @Override
    public void onClick(View v) {
        writeStuff.thingOne();
        writeStuff.thingTwo();
        writeStuff.thingOne();
        writeStuff.thingTwo();
        writeStuff.thingOne();
        writeStuff.thingTwo();
        writeStuff.thingOne();
        writeStuff.thingTwo();
        writeStuff.thingOne();
        writeStuff.thingTwo();
        writeStuff.thingOne();
        writeStuff.thingTwo();
    }

}
