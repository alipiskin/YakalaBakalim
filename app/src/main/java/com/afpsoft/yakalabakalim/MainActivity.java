package com.afpsoft.yakalabakalim;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    Button restartButton;
    int score;
    ImageView [] imageArray;
    Handler handler;
    Runnable runnable;
    Handler handler2;
    Runnable runnable2;
    Boolean isInGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setVisibility(View.INVISIBLE);
        imageArray = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        hideImages();

        score = 0;
        isInGame = true;





        new CountDownTimer(50000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText = (TextView) findViewById(R.id.textTime);
                timeText.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                timeText = (TextView) findViewById(R.id.textTime);
                timeText.setText("Time's Off");
                restartButton.setVisibility(View.VISIBLE);
                handler.removeCallbacks(runnable);
                isInGame=false;

            }
        }.start();

    }



    public void increaseScore(View view) {

        if (isInGame==true) {

            for (ImageView image : imageArray) {
                image.setImageResource(R.drawable.boom);

            }

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {

                    for (ImageView image : imageArray) {

                        image.setImageResource(R.drawable.cat);
                    }

                }

                ;

            };
            handler2.postDelayed(runnable2, 500);



            scoreText = (TextView) findViewById(R.id.textScore);

            score++;

            scoreText.setText("Score: " + score);

        }


    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                    image.setImageResource(R.drawable.cat);
                }

                Random r = new Random();
                int i = r.nextInt(8-0); 

                imageArray[i].setImageResource(R.drawable.cat);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this, 500);

            }
        };

        handler.post(runnable);

    }

    public void restart(View view){

        Intent i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }





}
