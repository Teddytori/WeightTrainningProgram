package com.helchang.weighttrainningprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RankingChart  extends AppCompatActivity {

    EditText weightInfo;
    ConstraintLayout rankInfo;
    boolean isRankInfoShowing = false;
    TextView[] ranks = new TextView[3];

    int ex1RepsValues[] = new int[3];
    int big3, weight;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking_chart);

        weightInfo = (EditText)findViewById(R.id.weight_input);
        rankInfo = (ConstraintLayout)findViewById(R.id.rank_info);

        rankInfo.setVisibility(View.INVISIBLE);

        for(int i = 0; i < ranks.length; ++i){
            ranks[i] = (TextView)findViewById(getResources().getIdentifier("rank" + i, "id", "com.helchang.weighttrainningprogram"));
        }

        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.user_data), Context.MODE_PRIVATE);
        int sum = 0;
        TextView[] ex1Reps = new TextView[3];
        for(int i = 0; i < 3; ++i){
            ex1Reps[i] = (TextView)findViewById(getResources().getIdentifier("ex1Reps" + i, "id", "com.helchang.weighttrainningprogram"));
            int value = sharedPref.getInt("ex1Reps" + i, 0);
            ex1Reps[i].setText(String.valueOf(value));
            ex1RepsValues[i] = value;
            sum += value;
        }
        TextView exBig3 = (TextView)findViewById(R.id.exBig3);
        exBig3.setText(String.valueOf(sum));
        big3 = sum;

        weight = sharedPref.getInt(getString(R.string.user_weight), 0);
        if(weight != 0){
            weightInfo.setText(String.valueOf(weight));
            setRanks();
        }
    }

    public void back(View v){
        onBackPressed();
    }

    public void showRankInfo(View v){
        if(isRankInfoShowing){
            rankInfo.setVisibility(View.INVISIBLE);
            isRankInfoShowing = false;
        }
        else{
            rankInfo.setVisibility(View.VISIBLE);
            isRankInfoShowing = true;
        }
    }

    public void saveWeight(View v){
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.user_data), Context.MODE_PRIVATE);
        String weightStr = weightInfo.getText().toString();
        if(weightStr.equals("")){
            Toast.makeText(this, getString(R.string.weight_input_error), Toast.LENGTH_SHORT).show();
            return;
        }
        weight = Integer.parseInt(weightStr);
        sharedPref.edit().putInt(getString(R.string.user_weight), weight).commit();
        setRanks();
    }

    public void setRanks(){

        String newbie = "newbie";
        String beginner = "beginner";
        String intermediate = "intermediate";
        String advanced = "advanced";
        String elite = "elite";

        String sqRank;
        String bpRank;
        String dlRank;

        if(weight < 56){
            if(ex1RepsValues[0] < 65){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 79){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 109){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 145){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 49){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 59){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 81){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 101){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 81){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 93){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 136){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 176){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 60){
            if(ex1RepsValues[0] < 70){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 86){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 117){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 157){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 53){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 64){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 88){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 110){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 88){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 101){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 145){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 188){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 67){
            if(ex1RepsValues[0] < 76){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 93){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 126){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 167){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 57){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 69){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 94){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 118){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 95){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 108){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 155){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 199){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 75){
            if(ex1RepsValues[0] < 85){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 104){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 142){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 186){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 64){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 78){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 106){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 132){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 106){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 122){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 172){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 219){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 82){
            if(ex1RepsValues[0] < 93){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 113){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 155){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 202){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 69){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 85){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 116){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 145){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 115){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 133){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 186){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 235){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 90){
            if(ex1RepsValues[0] < 100){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 122){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 166){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 217){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 74){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 91){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 125){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 156){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 124){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 143){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 199){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 249){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 100){
            if(ex1RepsValues[0] < 105){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 129){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 176){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 229){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 78){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 97){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 131){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 164){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 131){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 151){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 207){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 257){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 110){
            if(ex1RepsValues[0] < 111){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 137){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 186){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 241){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 83){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 102){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 139){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 173){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 138){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 159){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 217){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 266){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 125){
            if(ex1RepsValues[0] < 116){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 141){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 192){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 250){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 86){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 105){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 143){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 179){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 144){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 165){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 222){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 270){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else if(weight < 145){
            if(ex1RepsValues[0] < 118){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 145){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 197){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 257){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 89){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 108){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 147){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 185){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 148){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 169){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 226){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 273){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }else{
            if(ex1RepsValues[0] < 121){
                sqRank = newbie;
            }else if(ex1RepsValues[0] < 148){
                sqRank = beginner;
            }else if(ex1RepsValues[0] < 202){
                sqRank = intermediate;
            }else if(ex1RepsValues[0] < 263){
                sqRank = advanced;
            }else{
                sqRank = elite;
            }
            if(ex1RepsValues[1] < 90){
                bpRank = newbie;
            }else if(ex1RepsValues[1] < 111){
                bpRank = beginner;
            }else if(ex1RepsValues[1] < 151){
                bpRank = intermediate;
            }else if(ex1RepsValues[1] < 189){
                bpRank = advanced;
            }else{
                bpRank = elite;
            }
            if(ex1RepsValues[2] < 151){
                dlRank = newbie;
            }else if(ex1RepsValues[2] < 173){
                dlRank = beginner;
            }else if(ex1RepsValues[2] < 230){
                dlRank = intermediate;
            }else if(ex1RepsValues[2] < 276){
                dlRank = advanced;
            }else{
                dlRank = elite;
            }
        }

        ranks[0].setText(getString(getResources().getIdentifier(sqRank, "string", "com.helchang.weighttrainningprogram")));
        ranks[1].setText(getString(getResources().getIdentifier(bpRank, "string", "com.helchang.weighttrainningprogram")));
        ranks[2].setText(getString(getResources().getIdentifier(dlRank, "string", "com.helchang.weighttrainningprogram")));

    }

}
