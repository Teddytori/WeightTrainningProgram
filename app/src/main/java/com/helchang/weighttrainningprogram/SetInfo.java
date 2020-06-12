package com.helchang.weighttrainningprogram;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetInfo extends AppCompatActivity {

    public static float[] inputValues = new float[6];
    public static float[] startWeights = new float[5];
    public static int[] big3Weights = new int[3];
    private final EditText inputs[] = new EditText[6];

    SharedPreferences sharedPref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_info);

        sharedPref = SetInfo.this.getSharedPreferences(getString(R.string.user_data), Context.MODE_PRIVATE);

        inputs[0] = (EditText)findViewById(R.id.sq_input);
        inputs[1] = (EditText)findViewById(R.id.bp_input);
        inputs[2] = (EditText)findViewById(R.id.dl_input);
        inputs[3] = (EditText)findViewById(R.id.pr_input);
        inputs[4] = (EditText)findViewById(R.id.ohp_input);
        inputs[5] = (EditText)findViewById(R.id.plate);

        if(inputValues[0] != 0){
            for(int i = 0; i < inputs.length; ++i){
                inputs[i].setText(String.valueOf(inputValues[i]));
            }
        }

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i = 0; i < inputs.length; ++i){
                    if(inputs[i].getText().toString().equals("") || inputs[i].getText().toString().equals("0")){
                        Toast msg = Toast.makeText(SetInfo.this, getString(R.string.not_enough_info), Toast.LENGTH_SHORT);
                        msg.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 500);
                        msg.show();
                        return;
                    }
                }

                SharedPreferences.Editor editor = sharedPref.edit();

                inputValues = new float[inputs.length];
                for(int i = 0; i < inputs.length; ++i) {
                    float value = Float.parseFloat(inputs[i].getText().toString());
                    if(i < 3){
                        int ex1Reps = (int)(value / (1.0278f - (0.0278f * 5)));
                        big3Weights[i] = ex1Reps;
                        editor.putInt("ex1Reps" + i, ex1Reps);
                    }
                    editor.putFloat("input" + i, value);
                    inputValues[i] = value;
                }
                for(int i = 0; i < inputs.length - 1; ++i){
                    startWeights[i] = Math.round(inputValues[i] * (float)Math.pow((1 / 1.025), 3.0) / (2 * inputValues[5])) * (2 * inputValues[5]);
                    editor.putFloat("start" + i, startWeights[i]);
                    editor.putInt("curWeek" + i, 1);
                    editor.putInt("maxWeek" + i, 1);
                }

                editor.commit();
                Toast msg = Toast.makeText(SetInfo.this, getString(R.string.save_success), Toast.LENGTH_SHORT);
                msg.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 500);
                msg.show();
            }
        });

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                onBackPressed();
            }
        });

    }
    
    public void loadData(View v){
        float main;
        int week;
        for(int i = 0; i < 5; ++i){
            main = 0;
            week = sharedPref.getInt("curWeek" + i, 0);
            if(week != 0){
                main = Math.round( SetInfo.startWeights[i] * (float)(Math.pow(1.025, week - 1) / (2 * SetInfo.inputValues[5])) ) * (2 * SetInfo.inputValues[5]);
            }
            if(main <= SetInfo.startWeights[i]) main = inputValues[i];
            inputs[i].setText(String.valueOf(main));
        }
    }

}
