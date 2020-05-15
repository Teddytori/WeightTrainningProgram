package com.helchang.weighttrainningprogram;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Training  extends AppCompatActivity {

    private enum Day{Mon, Wed, Fri};
    private enum Exer{
        SqH(0), Bp(1), Dl(2), Pr(3), Ohp(4), SqL(0);
        public final int val;
        private Exer(int val){
            this.val = val;
        }
    };

    Day curDay = null;
    Exer curExer = null;

    Button monButton;
    Button wedButton;
    Button friButton;

    LinearLayout monExerSelection;
    LinearLayout wedExerSelection;

    Button sqHeavyButton;
    Button bpButton;
    Button prButton;
    Button dlButton;
    Button ohpButton;
    Button sqLightButton;

    LinearLayout exerTable;
    TextView exerWeek;
    TextView exerName;
    TextView setWeights[];
    TextView set4Reps;
    TextView set5Reps;
    TextView set6Reps;
    Button completeButton;

    SharedPreferences sharedPref;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);

        sharedPref = this.getSharedPreferences(getString(R.string.user_data), MODE_PRIVATE);

        monButton = (Button)findViewById(R.id.mon);
        wedButton = (Button)findViewById(R.id.wed);
        friButton = (Button)findViewById(R.id.fri);

        monExerSelection = (LinearLayout)findViewById(R.id.mon_exer_selection);
        monExerSelection.setVisibility(LinearLayout.INVISIBLE);
        wedExerSelection = (LinearLayout)findViewById(R.id.wed_exer_selection);
        wedExerSelection.setVisibility(LinearLayout.INVISIBLE);

        sqHeavyButton = (Button)findViewById(R.id.sq_heavy);
        bpButton = (Button)findViewById(R.id.bp);
        prButton = (Button)findViewById(R.id.pr);
        dlButton = (Button)findViewById(R.id.dl);
        ohpButton = (Button)findViewById(R.id.ohp);
        sqLightButton = (Button)findViewById(R.id.sq_light);

        exerWeek = (TextView)findViewById(R.id.exer_week);
        exerName = (TextView)findViewById(R.id.exer_name);
        setWeights = new TextView[6];
        for(int i = 0; i < setWeights.length; ++i){
            String name = "set" + (i + 1) + "_weight";
            setWeights[i] = (TextView)findViewById(getResources().getIdentifier(name, "id", "com.helchang.weighttrainningprogram"));
        }
        set4Reps = (TextView)findViewById(R.id.set4_reps);
        set5Reps = (TextView)findViewById(R.id.set5_reps);
        set6Reps = (TextView)findViewById(R.id.set6_reps);
        exerTable = (LinearLayout)findViewById(R.id.exer_table);
        exerTable.setVisibility(View.INVISIBLE);
        completeButton = (Button)findViewById(R.id.complete);
        completeButton.setVisibility(LinearLayout.INVISIBLE);

    }

    public void selectDay(View v){
        int id = v.getId();

        monButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        wedButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        friButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        monExerSelection.setVisibility(LinearLayout.INVISIBLE);
        wedExerSelection.setVisibility(LinearLayout.INVISIBLE);

        if(id == R.id.mon){
            monButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            monExerSelection.setVisibility(LinearLayout.VISIBLE);
            if(curDay == Day.Mon) return;
            curDay = Day.Mon;
        }
        else if(id == R.id.wed){
            wedButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            wedExerSelection.setVisibility(LinearLayout.VISIBLE);
            if(curDay == Day.Wed) return;
            curDay = Day.Wed;
        }
        else{
            friButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            monExerSelection.setVisibility(LinearLayout.VISIBLE);
            if(curDay == Day.Fri) return;
            curDay = Day.Fri;
        }

        sqHeavyButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        bpButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        prButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        dlButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        ohpButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        sqLightButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));

        exerTable.setVisibility(View.INVISIBLE);
        completeButton.setVisibility(View.INVISIBLE);
    }

    public void selectExer(View v){
        int id = v.getId();

        sqHeavyButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        bpButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        prButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        dlButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        ohpButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));
        sqLightButton.setBackgroundTintList(getColorStateList(R.color.colorPrimaryDark));

        if(id == R.id.sq_heavy){
            curExer = Exer.SqH;
            sqHeavyButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            exerName.setText(getString(R.string.sq));
        }
        else if(id == R.id.bp){
            curExer = Exer.Bp;
            bpButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            exerName.setText(getString(R.string.bp));
        }
        else if(id == R.id.pr){
            curExer = Exer.Pr;
            prButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            exerName.setText(getString(R.string.pr));
        }
        else if(id == R.id.dl){
            curExer = Exer.Dl;
            dlButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            exerName.setText(getString(R.string.dl));
        }
        else if(id == R.id.ohp){
            curExer = Exer.Ohp;
            ohpButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            exerName.setText(getString(R.string.ohp));
        }
        else{
            curExer = Exer.SqL;
            sqLightButton.setBackgroundTintList(getColorStateList(R.color.colorPrimary));
            exerName.setText(getString(R.string.sq));
        }
        setExerInfo();
    }

    public float getMainWeight(Day day, int exer, int week){
        float main;
        if(week == 1 && day != Day.Fri){
            main = SetInfo.startWeights[exer];
        }
        else if(day == Day.Fri){
            main = Math.round( SetInfo.startWeights[exer] * (float)(Math.pow(1.025, week) / (2 * SetInfo.inputValues[5])) ) * (2 * SetInfo.inputValues[5]);
        }
        else{
            main = Math.round( SetInfo.startWeights[exer] * (float)(Math.pow(1.025, week - 1) / (2 * SetInfo.inputValues[5])) ) * (2 * SetInfo.inputValues[5]);
        }
        return main;
    }

    public void setExerInfo(){
        exerTable.setVisibility(View.VISIBLE);
        float plate = SetInfo.inputValues[5];
        int curWeek, maxWeek;
        float main;
        curWeek = sharedPref.getInt("curWeek" + curExer.val, 0);
        maxWeek = sharedPref.getInt("maxWeek" + curExer.val, 0);
        main = getMainWeight(curDay, curExer.val, curWeek);
        if(curExer == Exer.SqH || curExer == Exer.Bp || curExer == Exer.Pr){
            set4Reps.setText("5");
            set6Reps.setText(getString(R.string.max_reps));
            for(int i = 0; i < 4; ++i){
                //setWeights[i].setText(String.valueOf(Math.round( main * (1 - 0.125 * (4 - i)) / (2 * plate) ) * (2 * plate)));
                setSetWeightsText(i, Math.round( main * (1 - 0.125 * (4 - i)) / (2 * plate) ) * (2 * plate));
            }
            //setWeights[4].setText(String.valueOf(main));
            setSetWeightsText(4, main);
            if(curDay == Day.Mon){
                set5Reps.setText(getString(R.string.main_set5));
                if(curExer == Exer.Pr){
                    set6Reps.setText("");
                    setWeights[5].setText("");
                }else{
                    setWeights[5].setText(setWeights[1].getText());
                }
            }
            else{
                set5Reps.setText(getString(R.string.main_set3));
                setWeights[5].setText(setWeights[2].getText());
            }
        }
        else if(curExer == Exer.Dl){
            set4Reps.setText(getString(R.string.main_set3));
            set5Reps.setText("");
            set6Reps.setText("");
            for(int i = 0; i < 2; ++i){
                //setWeights[i].setText(String.valueOf(Math.round( main * (1 - 0.125 * (2 - i)) / (2 * plate) ) * (2 * plate)));
                setSetWeightsText(i,Math.round( main * (1 - 0.125 * (2 - i)) / (2 * plate) ) * (2 * plate));
            }
            //setWeights[2].setText(String.valueOf(main));
            setSetWeightsText(2, main);
            //setWeights[3].setText(String.valueOf(getMainWeight(curDay, curExer.val, curWeek + 1)));
            setSetWeightsText(3, getMainWeight(curDay, curExer.val, curWeek + 1));
            setWeights[4].setText("");
            setWeights[5].setText("");
        }
        else if(curExer == Exer.Ohp){
            set4Reps.setText(getString(R.string.main_set5));
            set5Reps.setText(getString(R.string.max_reps));
            set6Reps.setText("");
            for(int i = 0; i < 3; ++i){
                //setWeights[i].setText(String.valueOf(Math.round( main * (1 - 0.125 * (3 - i)) / (2 * plate) ) * (2 * plate)));
                setSetWeightsText(i,Math.round( main * (1 - 0.125 * (3 - i)) / (2 * plate) ) * (2 * plate));
            }
            //setWeights[3].setText(String.valueOf(main));
            setSetWeightsText(3, main);
            setWeights[4].setText(setWeights[0].getText());
            setWeights[5].setText("");
        }
        else{
            set4Reps.setText(getString(R.string.max_reps));
            set5Reps.setText(getString(R.string.body_building));
            set6Reps.setText("");
            for(int i = 0; i < 3; ++i){
                //setWeights[i].setText(String.valueOf(Math.round( main * (1 - 0.125 * (4 - i)) / (2 * plate) ) * (2 * plate)));
                setSetWeightsText(i, Math.round( main * (1 - 0.125 * (4 - i)) / (2 * plate) ) * (2 * plate));
            }
            setWeights[3].setText(setWeights[1].getText());
            setWeights[4].setText(setWeights[0].getText());
            setWeights[5].setText("");
        }
        exerWeek.setText(String.valueOf(curWeek));
        if(curWeek == maxWeek && curDay != Day.Mon && curExer != Exer.SqL){
            completeButton.setVisibility(View.VISIBLE);
        }else{
            completeButton.setVisibility(View.INVISIBLE);
        }
    }

    public void toNextWeek(View v){
        int curWeek = sharedPref.getInt("curWeek" + curExer.val, 0);
        int maxWeek = sharedPref.getInt("maxWeek" + curExer.val, 0);

        if(curWeek == maxWeek) return;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("curWeek" + curExer.val, ++curWeek);
        editor.commit();
        setExerInfo();
    }

    public void toPriorWeek(View v){
        int curWeek = sharedPref.getInt("curWeek" + curExer.val, 0);

        if(curWeek == 1) return;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("curWeek" + curExer.val, --curWeek);
        editor.commit();
        setExerInfo();
    }

    public void complete(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.check_to_next_week)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int curWeek = sharedPref.getInt("curWeek" + curExer.val, 0);
                        if (curWeek == 12) {
                            Toast msg = Toast.makeText(Training.this, getString(R.string.restart_week1), Toast.LENGTH_SHORT);
                            msg.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 500);
                            msg.show();
                            return;
                        }
                        curWeek++;
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("curWeek" + curExer.val, curWeek)
                                .putInt("maxWeek" + curExer.val, curWeek);
                        float main = getMainWeight(Day.Mon, curExer.val, curWeek);
                        int ex1Reps = (int)(main / (1.0278 - (0.0278 * 5)));
                        if(curExer.val < 3 && SetInfo.big3Weights[curExer.val] < ex1Reps){
                            SetInfo.big3Weights[curExer.val] = ex1Reps;
                            editor.putInt("ex1Reps" + curExer.val, ex1Reps);
                        }
                        editor.commit();
                        setExerInfo();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        builder.show();
    }

    public void setSetWeightsText(int exer, float weight){
        if(weight > (int)weight){
            setWeights[exer].setText(String.valueOf(weight));
        }else{
            setWeights[exer].setText(String.valueOf((int)weight));
        }
    }

    public void back(View v){
        onBackPressed();
    }
}


