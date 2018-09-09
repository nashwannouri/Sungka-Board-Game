package com.example.k1465128.sungka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Activity of main menu
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    public static Context contextOfApplication;
    private  boolean appQuit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        setContentView(R.layout.menu);
        Log.d(TAG, "onCreate() Restoring previous state");
    }

    @Override
    protected void onDestroy() {
        appQuit=true;
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * @return true if activity is finished
     */
    public boolean isAppQuit() {
        return appQuit;
    }

    /**
     * @return context of application
     */
    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    /**
     * Invoked when play button is clicked
     * @param v
     */
    public void goToPlayMenu(View v) {
        Intent intent = new Intent(MainActivity.this, PlayMenuActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Invoked when high scores button is clicked
     * @param v
     */
    public void openHighScores(View v) {
        Intent intent = new Intent(MainActivity.this, HighScoresActivity.class);
        //Put your id to your next Intent
        startActivity(intent);
        finish();
    }

    /**
     * Invoked when exit button is clicked
     * @param v
     */
    public void exitGame(View v){
        finish();
    }


}