package com.example.randomnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView,textView2,textView3;
    Button hbutton, lbutton, loadButton;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textId);
        textView2=findViewById(R.id.text2Id);
        textView3=findViewById(R.id.scoretextId);
        hbutton=findViewById(R.id.button1Id);
        lbutton=findViewById(R.id.button2Id);
        loadButton=findViewById(R.id.loadButton);

        hbutton.setOnClickListener(this);
        lbutton.setOnClickListener(this);
        loadButton.setOnClickListener(this);

        if (score!=0){
            textView3.setText("Score : "+loadScore());
        }

        r_number();

    }

    private void r2_number() {Random random=new Random();
        int number = random.nextInt(101);
        String randomNumber = Integer.toString(number);
        textView2.setText(randomNumber);
    }

    private void r_number() {
        Random random=new Random();
        int number = random.nextInt(101);
        String randomNumber = Integer.toString(number);
        textView.setText(randomNumber);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.button1Id){
            loadButton.setVisibility(View.INVISIBLE);
            r2_number();
            int t1= Integer.parseInt(textView.getText().toString());
            int t2= Integer.parseInt(textView2.getText().toString());
            if (t1<t2){
                textView2.setTextColor(Color.GREEN);
                score=score+10;
                textView3.setText("Score : "+score);
                saveScore(score);
                Toast.makeText(this, "You win", Toast.LENGTH_SHORT).show();
            }else{
                textView2.setTextColor(Color.RED);

                hbutton.setVisibility(View.INVISIBLE);
                lbutton.setVisibility(View.INVISIBLE);
                loadButton.setVisibility(View.VISIBLE);

                    textView3.setText("Score : "+score+"\nGame Over");
                }


            }


        if (v.getId()==R.id.button2Id){
            loadButton.setVisibility(View.INVISIBLE);
            r2_number();
            int t1= Integer.parseInt(textView.getText().toString());
            int t2= Integer.parseInt(textView2.getText().toString());
            if (t1>t2){
                textView2.setTextColor(Color.GREEN);
                score=score+10;
                textView3.setText("Score : "+score);
                saveScore(score);
                Toast.makeText(this, "You win", Toast.LENGTH_SHORT).show();
            }else{
                textView2.setTextColor(Color.RED);

                hbutton.setVisibility(View.INVISIBLE);
                lbutton.setVisibility(View.INVISIBLE);
                loadButton.setVisibility(View.VISIBLE);

                textView3.setText("Score : "+score+"\nGame Over");
            }


        }

        if (v.getId()==R.id.loadButton){
            r_number();
            textView2.setText("");
            textView3.setText("");
            hbutton.setVisibility(View.VISIBLE);
            lbutton.setVisibility(View.VISIBLE);
            loadButton.setVisibility(View.INVISIBLE);

        }

    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore",score);
        editor.commit();
    }
    private int loadScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("gameScore", Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt("lastScore",0);
        return score;
    }
}