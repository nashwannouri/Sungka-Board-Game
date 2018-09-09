package com.example.k1465128.sungka;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Activity to display high scores
 */
public class HighScoresActivity extends Activity {
    private TextView name;
    private TextView score;

    /**
     * Default onCreate method
     * @param savedInstanceState is the current instance of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscores);
        name = (TextView) findViewById(R.id.HSfirstName);
        score = (TextView) findViewById(R.id.HSfirstScore);

        SharedPreferences scorePreference = getSharedPreferences(BoardActivity.GAME_STATS, 0);

        String oldScores = scorePreference.getString("highScores", "");
        Log.d("HSView", oldScores);
        String[] oldScoreArray = oldScores.split(",");
        StringBuilder nameBuild = new StringBuilder("");

        int i= 1;
        for(String score : oldScoreArray){
            nameBuild.append(i + "." + score + "\n");
            i++;
        }
        name.setText(nameBuild.toString());
    }

    /**
     * Invoked when home button is clicked
     * @param view is the view that was clicked in the graphical user interface
     */
    public void goToHome(View view){
        Intent intent = new Intent(HighScoresActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Invoked when player stats button is clicked
     * @param view is the view that was clicked in the graphical user interface
     */
    public void openPlayerStats(View view){
        Intent intent = new Intent(HighScoresActivity.this, PlayerStatsActivity.class);
        startActivity(intent);
        finish();
    }
}