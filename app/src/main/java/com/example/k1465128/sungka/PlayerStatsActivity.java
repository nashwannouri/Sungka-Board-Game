package com.example.k1465128.sungka;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This class represents the stats.xml as well as its methods
 */
public class PlayerStatsActivity extends Activity {
    TextView stats;

    /**
     * Default onCreate method that sets stats.xml to be the content viewed
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        stats = (TextView) findViewById(R.id.stats);

        SharedPreferences statPreferences = getSharedPreferences(BoardActivity.GAME_STATS, 0);
        String oldStats = statPreferences.getString("playerStats", "");
        String[] oldStatArray = oldStats.split(",");

        StringBuilder statBuild = new StringBuilder("");
        int i= 1;
        for(String stat : oldStatArray){
            statBuild.append(i + "." + stat+"\n");
            i++;
        }
        stats.setText(statBuild.toString());
    }

    /**
     * Method that opens HighScoresActivity class when called
     * @param view is the view that was clicked in the graphical user interfaceView
     */
    public void openHighScores(View view) {
        Intent intent = new Intent(PlayerStatsActivity.this, HighScoresActivity.class);
        //Put your id to your next Intent
        startActivity(intent);
        finish();
    }

    /**
     * Method that opens MainActivity class when calld
     * @param view is the view that was clicked in the graphical user interfaceView
     */
    public void homeButton(View view){
        Intent intent = new Intent(PlayerStatsActivity.this, MainActivity.class);
        //Put your id to your next Intent
        startActivity(intent);
        finish();
    }
}
