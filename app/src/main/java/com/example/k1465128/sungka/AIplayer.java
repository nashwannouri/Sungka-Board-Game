package com.example.k1465128.sungka;

import android.util.Log;

import java.util.ArrayList;

/**
 *  AI Player logic
 */
public class AIplayer extends Player {
    private int[] _trayScore;           //value of the Ai trays
    private ArrayList<Tray> _playerTray;//the Ai trays
    private Tray _selectedTray;         //the _selectedTray Tray

    /**
     * Ai player is created
     */
    public AIplayer() {
        setIsAi(true);

        _playerTray = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            _playerTray.add(new Tray());
        }
        Tray head = new Tray();
        head.setValue(0);
        _playerTray.add(head);

        _trayScore = new int[7];

        _selectedTray =new Tray();
        setIsTurn(true);
        for (int w = 0; w < 7; ++w) {
            for (int i = 0; i < 7; ++i) {
                Shell ss = new Shell();
                _playerTray.get(w).addtoArray(ss);
            }
        }
    }

    /**
     * @param i index of _selectedTray tray
     */
    public void setSelectedTray(int i) {
        _selectedTray = _playerTray.get(i);
    }


    /**
     *    get the _selectedTray Tray
     */
    public Tray get_selectedTray() {
        return _selectedTray;
    }

    /**
     * @return ArrayList of Ai Trays
     */
    public ArrayList<Tray> get_traySet() {
        return _playerTray;
    }

    /**
     * @return _trayScore Array of trays with
     */
    public int[] getTrayScore() {
        return _trayScore;
    }

    /**
     * Checks if 7 trays have 7 shells and the store has 0 shells
     * @return if shell distribution is correct
     */
    public boolean checkStartTray(){
        for(int i=0; i<8;i++){
            if(i!=7) {
                if (_playerTray.get(i).getShells() == 7) {
                } else {
                    return false;
                }
            }
            else{
                if(_playerTray.get(i).getShells()==0){}
                else{
                    return false;
                }
            }
        }
        return true;

    }

    /**
     * Selects the best tray for the turn - AI Logic
     */
    private Runnable runner = new Runnable() {
        @Override
        public void run() {

            calibrateAiMoves();
            chooseBestMove();
        }
    };


    /**
     * Runs the Ai logic thread to find best move
     */
    public void makeMove(){

        runner.run();

    }


    /**
     * Used to test bonus start function. Bonus start function is when AI player has a second first turn
     * @return index of bonus turn tray
     */
    public int testBonusTurnStart(){
        calibrateAiMoves();
        chooseBestMove();
        return (_playerTray.indexOf(_selectedTray));
    }

    /**
     * each trays score is calculated
     */
    public void calibrateAiMoves(){
        for (int i = 0; i < 7; ++i) {
            _trayScore[i] = i + _playerTray.get(i).getShells();

            if(_playerTray.get(i).getShells()==0)
            {
                checkSteal(i);
            }

            if(_trayScore[i]==7)
            {
                _trayScore[i]=100;
            }
        }
    }

    /**
     * Chooses best move
     */
    private void chooseBestMove(){
        int best =0;
        int index=0;
        for(int i=0; i< 7;++i)
        {
            if(_trayScore[i]>=best)
            {
                index=i;
                best = _trayScore[i];
                Log.d("calbrating move","chooseBestMove at " +index+" value of " +best);
            }
        }

        setSelectedTray(index);
    }

    /**
     * Checks to see if a steal is possible
     * @param stealIndex index at which tray is 0;
     * @return whether or not steal is possible;
     */
    public boolean checkSteal(int stealIndex) {
        _trayScore[stealIndex]=0;

        for(int i =0; i<stealIndex;++i)
        {
            if(_trayScore[i] ==stealIndex)
            {
                _trayScore[i] =200;
                return true;
            }
        }

        return false;
    }
}