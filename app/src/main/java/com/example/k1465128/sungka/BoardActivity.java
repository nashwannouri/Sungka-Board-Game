package com.example.k1465128.sungka;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
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
import java.util.Collections;
import java.util.Random;

/**
 * Created by nashwan on 10/21/2015.
 */
public class BoardActivity extends Activity {

    //================================================Fields====================================================================


    public static final String GAME_STATS = "SungkaStats";
    private ArrayList<Shell> arrayList;
    private ArrayList<ImageButton> _playerOneViewSet, _playerTwoViewSet;   //players ButtonViews
    private ArrayList<TextView> _playerOneTextViews, _playerTwoTextViews;
    private ArrayList<Tray> _playerOneTray, _playerTwoTray;
    private ArrayList<ImageView> _images;
    private TextView _displayPlayer, _player1Text, _player2Text;
    private RealPlayer _player1, _player2;
    private Game game;
    private Animation _fadeAnimation;
    private MediaPlayer _mediaPlayer;
    private int counter = 0;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private String _player1Name, _player2Name;
    private Button _musicButton;

    //===============================================Constructor================================================================

    public BoardActivity() {
        _mediaPlayer = new MediaPlayer();
        _images = new ArrayList<>();
        _player1 = new RealPlayer();
        _player2 = new RealPlayer();
        _playerOneViewSet = new ArrayList<>();
        _playerTwoViewSet = new ArrayList<>();
        _playerOneTray = _player1.get_traySet();
        _playerTwoTray = _player2.get_traySet();
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
        _player1Name = b.getString("Player1");
        _player2Name = b.getString("Player2");
        _player1Text = (TextView) findViewById(R.id.playerOne);
        _player2Text = (TextView) findViewById(R.id.playerTwo);

        _player1.setName(_player1Name);
        _player1Text.setText(_player1.getName());
        _player2.setName(_player2Name);
        _player2Text.setText(_player2.getName());
        //references for player 1 Trays in xml
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

        //references for player 1 TextView in xml
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v0));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v1));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v2));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v3));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v4));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v5));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v6));
        _playerOneTextViews.add((TextView) findViewById(R.id.p1v7));

        //references for player 2 TextView in xml
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v0));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v1));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v2));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v3));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v4));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v5));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v6));
        _playerTwoTextViews.add((TextView) findViewById(R.id.p2v7));


        //Shell _images are created and added to the board
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayoutBoard);
        frameLayout.bringToFront();
        frameLayout.bringToFront();
        Random random = new Random();
        for (int i = 0; i < 98; ++i) {
            FrameLayout.LayoutParams da = new FrameLayout.LayoutParams(20, 20);
            ImageView temp = new ImageView(this);
            temp.setImageResource(R.drawable.shell);
            temp.setLayoutParams(da);
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            temp.setColorFilter(color);
            _images.add(temp);
            frameLayout.addView(temp);
        }

        //other ultities are referenced
        _fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeanimation);

        //music for the activity is set and started
        _mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.sound2);
        _mediaPlayer.start();
        _mediaPlayer.pause();
        _musicButton = (Button) findViewById(R.id.buttonMusic);
        _musicButton.setBackgroundResource(R.drawable.soundoff);
    }

    /**
     * starts and pauses the music
     *
     * @param v the music button
     */
    public void MusicButton(View v) {
        if (_mediaPlayer.isPlaying()) {
            _mediaPlayer.pause();
            _musicButton.setBackgroundResource(R.drawable.soundoff);

        } else {
            _mediaPlayer.start();
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
            //sets the name of each player to display


            //position of buttons is linked to the corrsponding trays
            int[] hi = new int[2];
            for (int i = 0; i < 8; ++i) {

                //trays are given coordinates
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

            //Shell imageViews are set to the appropriate coordinate;
            for (int i = 0; i < 98; ++i) {
                int c = i;
                _images.get(c).setX(arrayList.get(c).getXcord());
                _images.get(c).setY(arrayList.get(c).getYcord());
                Log.d("_images", "Xcord" + _images.get(c).getX() + _images.get(c).getY());
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
                        _mediaPlayer.stop();

                        //closes current activity and opens main menu
                        Intent intent = new Intent(BoardActivity.this, MainActivity.class);
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
                        Intent intent = new Intent(BoardActivity.this, BoardActivity.class);
                        Bundle b = new Bundle();
                        b.putString("Player1",_player1.getName());
                        b.putString("Player2",_player2.getName());
                        _mediaPlayer.stop();
                        intent.putExtras(b);
                        //Put your id to your next Intent
                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(BoardActivity.this, MainActivity.class);
                        Bundle b = new Bundle();
                        _mediaPlayer.stop();
                        //Put your id to your next Intent
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();

    }

    /**
     * the tray clicked listener
     * @param v the selected tray
     */
    public void move(View v) {
        //checks who clicked and runs the game accordingly
        ImageButton b = (ImageButton) v;
        if (_playerOneViewSet.contains(b)) {
            _player1.set_selectedTray(_playerOneViewSet.indexOf(b));
            game.processMove(_player1, _player2);
        } else if (_playerTwoViewSet.contains(b)) {
            _player2.set_selectedTray(_playerTwoViewSet.indexOf(b));
            game.processMove(_player2, _player1);
        }

        //update the board
        updateButtons();

        //checks for gameOver
        if (game.isGameOver()) {
            GameOver();
        }

    }

    /**
     * starts the gameover sequence and updating highscore and player stats
     */
    public void GameOver() {
        Log.d("gameOver", "Game is over");
        int currentStore = _player1.get_traySet().get(7).getShells();
        int opponentStore = _player2.get_traySet().get(7).getShells();
        if (currentStore == opponentStore) {
            gameOverScreen("draw");
            Log.d("gameOver", "Game is tied + " + currentStore + " " + opponentStore);
        } else {
            if (currentStore > opponentStore) {
                gameOverScreen(_player1.getName());
                updateHighScore(currentStore, _player1);
                updatePlayerStats(_player1, _player2);
                Log.d("gameOver", "Player 1 won");
            } else {
                gameOverScreen(_player2.getName());
                updateHighScore(opponentStore, _player2);
                updatePlayerStats(_player2, _player1);
                Log.d("gameOver", "Player 2 won");
            }
        }
    }


    /**
     * Updates high name
     *
     * @param score is the winner's score, p is the winning player
     */
    public void updateHighScore(int score, Player player) {
        Log.d("highScores", "High scores is being updated");
        String scores = sharedPref.getString("highScores", "");
        Log.d("highScores", "old scores are " + scores);
        if (scores.length() > 0) {
            ArrayList<Score> scoreStrings = new ArrayList<Score>();
            String[] oldScores = scores.split(",");
            for (String s : oldScores) {
                String[] parts = s.split(" - ");
                Score temp = new Score(parts[0], Integer.parseInt(parts[1]));
                scoreStrings.add(temp);
            }
            Score currentScore = new Score(player.getName(), score);
            scoreStrings.add(currentScore);
            Collections.sort(scoreStrings);
            StringBuilder scoreBuild = new StringBuilder("");
            for (int i = 0; i < scoreStrings.size(); i++) {
                if(i==5) break;
                if(i > 0) scoreBuild.append(",");
                scoreBuild.append(scoreStrings.get(i).toString());
            }
            editor.putString("highScores", scoreBuild.toString());
            editor.commit();
            Log.d("highScores", "new high scores are " + scoreBuild.toString());
        } else {
            editor.putString("highScores", player.getName() + " - " + score);
            editor.commit();
            Log.d("highScores", "First high score has been saved!");
        }
    }

    /**
     * Updates the players starts
     * @param winner the winning player
     * @param loser the losing player
     */
    public void updatePlayerStats(Player winner, Player loser) {
        Log.d("playerStats", "Player stats is starting");
        String playerStats = sharedPref.getString("playerStats", "");
        Log.d("playerStats", "old stats are " + playerStats);
        SharedPreferences.Editor statEditor = sharedPref.edit();

        if (playerStats.length() > 0) {
            //Populate statString with previous playerStats
            ArrayList<PlayerStats> statString = new ArrayList<PlayerStats>();
            String[] oldStats = playerStats.split(",");
            for (String s : oldStats) {
                String[] parts = s.split(" - ");
                PlayerStats temp = new PlayerStats(parts[0]);
                temp.setWins(Integer.parseInt(parts[1]));
                temp.setGamesPlayed(Integer.parseInt(parts[2]));
                statString.add(temp);
            }

            Boolean winnerExists = false;
            Boolean loserExists = false;

            Log.d("statLoop", "There will be " + statString.size() + " loops");
            for(int i =0; i< statString.size(); i++){
                PlayerStats s = statString.get(i);
                if(!winnerExists){
                    Log.d("statLoopWinner", s.getName() + " " + winner.getName());
                    if (s.getName().toLowerCase().equals(winner.getName().toLowerCase())) {
                        Log.d("winner", statString.get(i).toString());
                        statString.get(i).increaseWins();
                        statString.get(i).increaseGamesPlayed();
                        Log.d("winner", "Winner updated to " + statString.get(i).toString());
                        winnerExists = true;
                    }
                }
                if(!loserExists){
                    Log.d("statLoopLoser", s.getName() + " " + loser.getName());
                    if (s.getName().toLowerCase().equals(loser.getName().toLowerCase())) {
                        Log.d("loser", statString.get(i).toString());
                        statString.get(i).increaseGamesPlayed();
                        Log.d("loser", "Loser updated to " + statString.get(i).toString());
                        loserExists = true;
                    }
                }
                if(winnerExists==true && loserExists==true) {
                    Log.d("playerStats", "Winner and losers exist!");
                    break;
                }
            }

            if (!winnerExists) {
                PlayerStats winnerPlayerStats = new PlayerStats(winner.getName());
                winnerPlayerStats.increaseGamesPlayed();
                winnerPlayerStats.increaseWins();
                statString.add(winnerPlayerStats);
            }

            if(!loserExists){
                PlayerStats loserPlayerStats = new PlayerStats(loser.getName());
                loserPlayerStats.increaseGamesPlayed();
                statString.add(loserPlayerStats);
            }

            //Sort out top player stats according to wins
            Collections.sort(statString);
            StringBuilder statBuild = new StringBuilder("");
            for (int s = 0; s < statString.size(); s++) {
                if (s > 0) statBuild.append(",");
                statBuild.append(statString.get(s).toString());
            }
            statEditor.putString("playerStats", statBuild.toString());
            statEditor.commit();
            Log.d("playerStats", "Player stats has been updated");
        } else {
            Log.d("playerStats", "Stat sheet used to be empty");
            statEditor.putString("playerStats", winner.getName() + " - 1 - 1," + loser.getName() + " - 0 - 1");
            statEditor.commit();
            Log.d("playerStats", "First two player stats entry has been updated");
        }
    }


    /**
     * checks the game status and updates the buttons and shells accordingly
     */
    public void updateButtons() {

        //set up buttons for next turn
        for (int i = 0; i < 8; ++i) {
            if (_playerOneTray.get(i).getShells() == 0 || !_player1.getIsTurn()) {
                _playerOneViewSet.get(i).setClickable(false);
            } else {
                _playerOneViewSet.get(i).setClickable(true);
            }
            _playerOneTextViews.get(i).setText(_playerOneTray.get(i).getShells() + "");


            if (_playerTwoTray.get(i).getShells() == 0 || !_player2.getIsTurn()) {
                _playerTwoViewSet.get(i).setClickable(false);
            } else {
                _playerTwoViewSet.get(i).setClickable(true);
            }
            _playerTwoTextViews.get(i).setText(_playerTwoTray.get(i).getShells() + "");

            //changed buttons are animated
            if (_playerOneTray.get(i).isChanged()) {
                _playerOneViewSet.get(i).startAnimation(_fadeAnimation);
                _playerOneTray.get(i).setIsChanged(false);
            }
            if (_playerTwoTray.get(i).isChanged()) {
                _playerTwoViewSet.get(i).startAnimation(_fadeAnimation);
                _playerTwoTray.get(i).setIsChanged(false);
            }
        }

        //shells _images are moved to new location
        for (int i = 0; i < 98; ++i) {
            int c = i;
            _images.get(c).setX(arrayList.get(c).getXcord());
            _images.get(c).setY(arrayList.get(c).getYcord());
        }

        //displays whos turn it is now
        if (_player1.isTurn() && _player2.isTurn()) {
            _displayPlayer.setText("Both Players Turn");
        } else if (_player1.isTurn()) {
            _displayPlayer.setText(_player1.getName() + "'s Turn");
        } else {
            _displayPlayer.setText(_player2.getName() + "'s Turn");
        }

    }

}
