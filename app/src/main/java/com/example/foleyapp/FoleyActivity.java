package com.example.foleyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Locale;

public class FoleyActivity extends AppCompatActivity {
    private AudioManager audioManager;
    private SoundCategory currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foley);

        audioManager = new AudioManager(this);


        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        currentCategory = SoundCategory.valueOf(category.toLowerCase());

        System.out.println("current catagory " + currentCategory);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();


        int width = getWindow().getDecorView().getWidth();
        int height = getWindow().getDecorView().getHeight();


        Log.i("FoleyActivity", "onTouchEvent: window size: " + width + "x" + height);


        String action = "";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "started";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "moved";
                break;
            case MotionEvent.ACTION_UP:
                action = "ended";
                break;
        }

        Position position = Position.top_left;
        if (action.equals("started")) {
            if (x < width / 2.0f && y < height / 2.0f) {
                position = Position.top_left;
            } else if (x > width / 2.0f && y < height / 2.0f) {
                position = Position.top_right;
            } else if (x < width / 2.0f && y > height / 2.0f) {
                position = Position.bottom_left;
            } else if (x > width / 2.0f && y > height / 2.0f) {
                position = Position.bottom_right;
            }
            System.out.println(" position" + position);

            audioManager.play(currentCategory, position);
        }


            Log.i("TouchableActivity", String.format(Locale.getDefault(), "%.2f %.2f %s", x, y, action));

            return super.onTouchEvent(event);

    }
}