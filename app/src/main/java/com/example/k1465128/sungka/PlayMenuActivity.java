package com.example.k1465128.sungka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This class represents how the play_menu.xml as well as its methods
 */
public class PlayMenuActivity extends Activity {

    /**
     * Default onCreate method that sets the play_menu to be the content  viewed
     * @param savedInstanceState current instance of the application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_menu);
    }

    /**
     * Opens NameEntryMultiplayerActivity when this method is called
     * @param view is the view that was clicked in the graphical user interface
     */
    public void twoPlayerGame(View view) {
        Intent intent = new Intent(PlayMenuActivity.this, NameEntryMultiplayerActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Opens MainActivity when this method is called
     * @param view is the view that was clicked in the graphical user interfaceView
     */
    public void returnHomePlay(View view) {
        Intent intent = new Intent(PlayMenuActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Opens NameEntryAiGameActivity when this method is called
     * @param view is the view that was clicked in the graphical user interface
     */
    public void vsAi(View view) {
        Intent intent = new Intent(PlayMenuActivity.this, NameEntryAiGameActivity.class);
        startActivity(intent);
        finish();
    }

}
