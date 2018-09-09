package com.example.k1465128.sungka;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BoardAiActivity extends BoardActivity {

    //================================================Fields====================================================================


    public static final String GAME_STATS = "SungkaStats";
    private ArrayList<Shell> arrayList;
    private ArrayList<ImageButton> _playerOneViewSet, _playerTwoViewSet;   //players ButtonViews
    private ArrayList<TextView> _playerOneTextViews, _playerTwoTextViews;
    private ArrayList<Tray> _playerOneTray, _playerTwoTray;
    private ArrayList<ImageView> images;
    private TextView _displayPlayer, player1Text, player2Text;
    private RealPlayer player1;
    private AIplayer player2;
    private Game game;
    private Button home;
    private Animation zoomanimation;
    private Animation fadeAnimation;
    private FrameLayout frameLayout;
    private Random random;
    private MediaPlayer mediaPlayer;
    private int counter = 0;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String player1Name, player2Name;
    private Button _musicButton;


    //===============================================Constructor================================================================

    /**
     * Board game vs Ai
     */
    public BoardAiActivity() {
        mediaPlayer = new MediaPlayer();
        random = new Random();
        images = new ArrayList<>();
        player1 = new RealPlayer();
        player2 = new AIplayer();
        _playerOneViewSet = new ArrayList<>();
        _playerTwoViewSet = new ArrayList<>();
        _playerOneTray = player1.get_traySet();
        _playerTwoTray = player2.get_traySet();
        _playerOneTextViews = new ArrayList<>();
        _playerTwoTextViews = new ArrayList<>();
        game = new Game();
        arrayList = new ArrayList<>();
        sharedPref = MainActivity.getContextOfApplication().getSharedPreferences(GAME_STATS, 0);
        editor = sharedPref.edit();


    }

    //===============================================Methods=====================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        Bundle b = getIntent().getExtras();



        _displayPlayer = (TextView) findViewById(R.id.turnTextView);

        player1Name = b.getString("Player1");
        player2Name = b.getString("Player2");
        player1Text = (TextView) findViewById(R.id.playerOne);
        player2Text = (TextView) findViewById(R.id.playerTwo);

        player1.setName(player1Name);
        player1Text.setText(player1.getName());
        player2.setName(player2Name);
        player2Text.setText(player2.getName());
        //references for player1 Trays in xml
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t0));
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t1));
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t2));
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t3));
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t4));
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t5));
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t6));
        _playerOneViewSet.add((ImageButton) findViewById(R.id.p1t7));


        //references for player 2 Trays in xml
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t0));
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t1));
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t2));
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t3));
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t4));
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t5));
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t6));
        _playerTwoViewSet.add((ImageButton) findViewById(R.id.p2t7));

        //references for player 2 TextView in xml
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v0));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v1));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v2));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v3));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v4));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v5));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v6));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v7));

        //references for player 1 TextView in xml
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v0));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v1));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v2));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v3));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v4));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v5));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v6));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v7));


        //Shell images are created and added to the board
        frameLayout = (FrameLayout) findViewById(R.id.frameLayoutBoard);
        frameLayout.bringToFront();
        frameLayout.bringToFront();
        for (int i = 0; i < 98; ++i) {
            FrameLayout.LayoutParams da = new FrameLayout.LayoutParams(20, 20);
            ImageView temp = new ImageView(this);
            temp.setImageResource(R.drawable.shell);
            temp.setLayoutParams(da);
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            temp.setColorFilter(color);
            images.add(temp);
            frameLayout.addView(temp);
        }

        //other ultities are referenced
        home = (Button) findViewById(R.id.homeButtonBoard);
        zoomanimation = AnimationUtils.loadAnimation(this, R.anim.fadeanimation);
        fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeanimation);

        //music for the activity is set and started
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound2);
        mediaPlayer.start();
        mediaPlayer.pause();
        _musicButton = (Button) findViewById(R.id.buttonMusic);
        _musicButton.setBackgroundResource(R.drawable.soundoff);

    }

    /**
     * starts and pauses the music
     *
     * @param v the music button
     */
    public void MusicButton(View v) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            _musicButton.setBackgroundResource(R.drawable.soundoff);
        } else {
            mediaPlayer.start();
            _musicButton.setBackgroundResource(R.drawable.soundon);
        }
    }

    /**
     * Occurs after the board is created. Shells are given correct coordinates here
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);
        //needs to happen once maximum
        if (counter == 0) {
            player1.setName(player1Name);
            player1Text.setText(player1.getName());
            player2.setName(player2Name);
            player2Text.setText(player2.getName());

            //position of buttons is linked to the corrsponding trays
            int[] hi = new int[2];
            for (int i = 0; i < 8; ++i) {

                _playerOneViewSet.get(i).getLocationInWindow(hi);
                hi[0] = hi[0] + (_playerOneViewSet.get(i).getWidth() / 2);
                hi[1] = hi[1] + (_playerOneViewSet.get(i).getHeight() / 5);
                _playerOneTray.get(i).setCordinates(hi[0], hi[1]);
                Log.d("coordinatesoftrayp1", "index " + i + "x " + hi[0] + "y " + hi[1]);

                _playerTwoViewSet.get(i).getLocationInWindow(hi);
                hi[0] = hi[0] + (_playerTwoViewSet.get(i).getWidth() / 2);
                hi[1] = hi[1] + (_playerTwoViewSet.get(i).getHeight() / 5);
                _playerTwoTray.get(i).setCordinates(hi[0], hi[1]);
                Log.d("coordinatesoftrayp2", "index " + i + "x " + hi[0] + "y " + hi[1]);
            }

            //shells are given the correct random position coordinates
            for (int w = 0; w < 7; ++w) {
                _playerOneTray.get(w).recalibrateCoordinates();
                _playerTwoTray.get(w).recalibrateCoordinates();

                for (int i = 0; i < 7; ++i) {
                    //shells added to shell arrayList
                    arrayList.add(_playerOneTray.get(w).getShellArrayList().get(i));
                    arrayList.add(_playerTwoTray.get(w).getShellArrayList().get(i));
                }
            }

            //Shell imageViews are set to the appropetie coordinate;
            for (int i = 0; i < 98; ++i) {
                int c = i;
                images.get(c).setX(arrayList.get(c).getXcord());
                images.get(c).setY(arrayList.get(c).getYcord());
                Log.d("images", "Xcord" + images.get(c).getX() + images.get(c).getY());
                // frameLayout.addView(images.get(c));
            }

            //prevents onWindowFocusChanged from rerunning this section of code
            counter++;
        }
    }


    /**
     * displays option to return to  the main enu
     *
     * @param view view when clicked opens dialog
     */
    public void ReturnHome(View view) {

        Dialog dialog = new Dialog(this);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //dialog setting
        alertDialog.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        //music is stopped
                        mediaPlayer.stop();

                        //closes current activity and opens main menu
                        Intent intent = new Intent(BoardAiActivity.this, MainActivity.class);
                        Bundle b = new Bundle();
                        startActivity(intent);
                        finish();

                    }
                })
                        //return to game
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    /**
     * displays a dialog after the game has ended
     *
     * @param winner determines who won or if draw
     */
    public void gameOverScreen(String winner) {
        Dialog dialog = new Dialog(this);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        //appriopate message is written
        String message = "";
        if (winner.equals("draw")) {
            alertDialog.setMessage("Game ended in draw. \nReturn to main menu or play again?");
        } else if (winner.equals("Ai")) {
            alertDialog.setMessage("You have lost.\n Return to main menu or play again?");

        } else {
            alertDialog.setMessage("Congratulations " + winner + ", you have won the game! \n Return to main menu or play again?");
        }

        //options that lead to main menu and rematch
        alertDialog.setCancelable(false)
                .setPositiveButton("Rematch", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(BoardAiActivity.this, BoardAiActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Player1", player1.getName());
                        b.putString("Player2", "Computer");
                        mediaPlayer.stop();
                        intent.putExtras(b);
                        //Put your id to your next Intent
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(BoardAiActivity.this, MainActivity.class);
                        Bundle b = new Bundle();
                        mediaPlayer.stop();
                        //Put your id to your next Intent
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();

    }


    @Override
    public void move(View v) {
        ImageButton b = (ImageButton) v;
        ImageButton[] barray = {b};
        AsyncDon nn = new AsyncDon();
        nn.execute(barray);
        // nn.cancel(true);
        Log.d("gameOver", "Game is over");

    }


    public void updateButtons(boolean b) {

        for (int i = 0; i < 8; ++i) {
            if (b) {
                if (_playerOneTray.get(i).getShells() != 0 && player1.getIsTurn() && i != 7) {
                    _playerOneViewSet.get(i).setClickable(true);
                } else {
                    _playerOneViewSet.get(i).setClickable(false);
                }

                if (_playerTwoTray.get(i).getShells() != 0 && player2.getIsTurn() && i != 7) {
                    _playerTwoViewSet.get(i).setClickable(true);
                } else {
                    _playerTwoViewSet.get(i).setClickable(false);
                }
            }
            _playerOneTextViews.get(i).setText(_playerOneTray.get(i).getShells() + "");
            _playerTwoTextViews.get(i).setText(_playerTwoTray.get(i).getShells() + "");
            if (_playerOneTray.get(i).isChanged()) {
                _playerOneViewSet.get(i).startAnimation(fadeAnimation);
                _playerOneTray.get(i).setIsChanged(false);
            }
            if (_playerTwoTray.get(i).isChanged()) {
                _playerTwoViewSet.get(i).startAnimation(fadeAnimation);
                _playerTwoTray.get(i).setIsChanged(false);
            }
        }
        Log.d("updating", "updating the buttons");
        for (int i = 0; i < 98; ++i) {
            int c = i;
            images.get(c).setX(arrayList.get(c).getXcord());
            images.get(c).setY(arrayList.get(c).getYcord());
            Log.d("images", "Xcord" + images.get(c).getX() + images.get(c).getY());
        }

        if (player1.isTurn() && player2.isTurn()) {
            _displayPlayer.setText("Both Players Turn");
        } else if (player1.isTurn()) {
            _displayPlayer.setText(player1.getName() + "'s Turn");
        } else {
            _displayPlayer.setText("Computer's Turn");
        }

        if (player1.get_traySet().get(7).getShells()+player2.get_traySet().get(7).getShells()>=98) {
            int currentStore = player1.get_traySet().get(7).getShells();

            int opponentStore = player2.get_traySet().get(7).getShells();

            if (currentStore == opponentStore) {
                gameOverScreen("draw");
                Log.d("gameOver", "Game is tied + " + currentStore + " " + opponentStore);
            } else {
                if (currentStore > opponentStore) {
                    gameOverScreen(player1.getName());
                    Log.d("gameOver", "Player 1 won");
                } else {
                    gameOverScreen("Ai");
                }
            }

        }
    }

    private class AsyncDon extends AsyncTask<ImageButton, Void, Void> {
        private boolean cc;

        public void waitMethod() {
            long t0 = System.currentTimeMillis();
            long millisLeft = 1000;
            long millis = 1000;
            while (millisLeft > 0) {
                long t1 = System.currentTimeMillis();
                millisLeft = millis - (t1 - t0);
            }
        }

        @Override
        protected Void doInBackground(ImageButton... params) {
            ImageButton b = params[0];


            cc = false;
            Log.d("countcount", "current count" + Thread.activeCount());

            if (_playerOneViewSet.contains(b)) {
                player1.set_selectedTray(_playerOneViewSet.indexOf(b));
                game.processMove(player1, player2);
                publishProgress();

                cc = true;
                waitMethod();
                //firstTime
                if (game.getIsFirstTime()) {
                    player2.makeMove();
                    game.processMove(player2, player1);
                    Log.d("board", "selectedTray" + _playerTwoTray.indexOf(player2.get_selectedTray()));
                    publishProgress();

                    if (player2.getIsTurn() && !player1.getIsTurn()) {
                        waitMethod();
                        player2.makeMove();
                        Log.d("board", "selectedTray" + _playerTwoTray.indexOf(player2.get_selectedTray()));
                        game.processMove(player2, player1);

                    }

                } else {
                    cc = true;
                    while (player2.getIsTurn() && !game.checkIfAllTraysEmpty(player2)) {
                        {
                            waitMethod();
                            publishProgress();
                            player2.makeMove();
                            Log.d("board", "selectedTray" + _playerTwoTray.indexOf(player2.get_selectedTray()));
                            game.processMove(player2, player1);

                        }

                    }
                }
                publishProgress();
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            if (cc) {
                updateButtons(true);
            } else {
                updateButtons(false);
            }

        }
    }
}