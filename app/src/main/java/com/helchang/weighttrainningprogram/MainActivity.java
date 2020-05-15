package com.helchang.weighttrainningprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.user_data), Context.MODE_PRIVATE);
        float defValue = 0;
        float check = sharedPref.getFloat("input0", defValue);
        if(check != defValue){
            for(int i = 0; i < SetInfo.inputValues.length; ++i){
                if(i < 3){
                    SetInfo.big3Weights[i] = sharedPref.getInt("ex1Reps" + i, (int)defValue);
                }
                if(i != SetInfo.inputValues.length - 1){
                    SetInfo.startWeights[i] = sharedPref.getFloat("start" + i, defValue);
                }
                SetInfo.inputValues[i] = sharedPref.getFloat("input" + i, defValue);
            }
        }
    }

    public void SetInfo(View v){
        Intent it = new Intent(this, SetInfo.class);
        startActivity(it);
    }

    public void StartTraining(View v){
        Intent it = new Intent(this, Training.class);
        startActivity(it);
    }

    public void ShowRanking(View v){
        Intent it = new Intent(this, RankingChart.class);
        startActivity(it);
    }

}
