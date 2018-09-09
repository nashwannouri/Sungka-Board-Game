package com.example.k1465128.sungka;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity to enter player 1's name. Invoked when AI game is chosen.
 */

public class NameEntryAiGameActivity extends Activity {
    EditText playername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oneplayername_entry);
        playername = (EditText) findViewById(R.id.playerOneName);
    }

    public void ReturnHome(View view) {
        Intent intent = new Intent(NameEntryAiGameActivity.this, MainActivity.class);
        Bundle b = new Bundle();
        startActivity(intent);
        finish();
    }

    /**
     * Starts the Ai game
     * @param v the view clicked
     */
    public void startAiGame(View v){
        Intent intent = new Intent(NameEntryAiGameActivity.this, BoardAiActivity.class);
        Bundle b = new Bundle();

        b.putString("Player1", playername.getText().toString());
        b.putString("Player2","Computer");
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

    /**
     * returns to home screen
     * @param v the view clicked
     */
    public void homeButton(View v){
        Intent intent = new Intent(NameEntryAiGameActivity.this, MainActivity.class);
        //Put your id to your next Intent
        startActivity(intent);
        finish();
    }
}