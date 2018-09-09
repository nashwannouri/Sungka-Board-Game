package com.example.k1465128.sungka;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the name_entry.xml as well as its methods
 */
public class NameEntryMultiplayerActivity extends Activity {
    private EditText player1Name;
    private EditText player2Name;
    private Pattern lettersOnly = Pattern.compile("[a-zA-Z]+");

    /**
     * Default onCreate method
     * @param savedInstanceState is the current instance of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_entry);
        player1Name = (EditText) findViewById(R.id.playerOneName);
        player2Name =(EditText) findViewById(R.id.playerTwoName);
    }

    /**
     * Opens main menu when called
     * @param view is the view that was clicked in the graphical user interfaceView
     */
    public void returnHome(View view){
        Intent intent = new Intent(NameEntryMultiplayerActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Opens a 2 Playerr game if names entered are valid
     * @param view is the view that was clicked in the graphical user interfaceView
     */
    public void startGame(View view){

        Matcher p1Matcher = lettersOnly.matcher(player1Name.getText().toString());
        Matcher p2Matcher = lettersOnly.matcher(player2Name.getText().toString());
        Dialog dialog = new Dialog(this);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        if(player1Name.getText().toString().equals("")||player2Name.getText().toString().equals("")){
            //dialog setting
            alertDialog.setMessage("Please enter your names")
                    .setCancelable(false)
                            //return to game
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
        else if(p1Matcher.matches()&&p2Matcher.matches()){
            Intent intent = new Intent(NameEntryMultiplayerActivity.this, BoardActivity.class);
            Bundle b= new Bundle();
            b.putString("Player1",player1Name.getText().toString());
            b.putString("Player2",player2Name.getText().toString());
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }
        else if(player1Name.getText().equals(player2Name.getText())) {
            //dialog setting
            alertDialog.setMessage("Please enter different names.")
                    .setCancelable(false)
                            //return to game
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alert = alertDialog.create();
            alert.show();
        }
        else{
            //dialog setting
            alertDialog.setMessage("Only letters are accepted")
                    .setCancelable(false)
                            //return to game
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alert = alertDialog.create();
            alert.show();
        }

    }
}
