package com.example.k1465128.sungka;

import android.util.Log;

import java.util.ArrayList;

/**
 * Logic of game.
 */
public class Game {
    private Player _current, _opponent;
    private int _currentShells, _opponentShells;
    private boolean _simultaneousTurn, _gameOver;

    public Game() {
        _gameOver =false;
        _opponentShells = 0;
        _currentShells = 0;
        _simultaneousTurn = true;
    }

    /**
     * set the turn as a simultaneous turn
     * @param _simultaneousTurn
     */
    public void setSimultaneousTurn(boolean _simultaneousTurn) {
        this._simultaneousTurn = _simultaneousTurn;
    }

    /**
     * Returns true if its a simultaneous turn
     * @return if simultaneousTurn is true
     */
    public boolean getIsFirstTime() {
        return _simultaneousTurn;
    }

    public void setGameOver(boolean _gameOver) {
        this._gameOver = _gameOver;
    }

    public boolean isGameOver() {
        return _gameOver;
    }

    /**
     * @param p Player
     * @return true if all trays are empty
     */
    public boolean checkIfAllTraysEmpty(Player p) {
        ArrayList<Tray> pArrat = p.get_traySet();
        for (int i = 0; i < 7; ++i) {
            if (pArrat.get(i).getShells() != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Processes a player's move
     * @param player     the current player
     * @param other      the opponent
     */
    public void processMove(final Player player, Player other) {
        Log.d("checks", "do processMove");
        _current = player;
        _opponent = other;

        Gamer run = new Gamer() {
        };
        run.run();
    }

    /**
     * Thread that handles the Game Logic
     */
    public class Gamer implements Runnable {
        /**
         * Distrbutes shells during normal turns or nonsimultaneous turns
         */
        public void normalUpdate(ArrayList<Tray> curTArray, ArrayList<Tray> oppTArray) {

            //clicked Tray is referenced and updated
            Tray clicked = _current.get_selectedTray();
            clicked.removeShell();
            clicked.setIsChanged(true);
            Log.d("checksNormalUpdate", "the currentshells is" + _currentShells + "");

            //shells distributed among current player trays
            _currentShells = distributeShellsToOpponentSide(clicked, curTArray, oppTArray, curTArray.indexOf(clicked) + 1, _currentShells);

            Log.d("checks", _currentShells + "");


            Log.d("checkNormalUpdate", "before while " + _currentShells);
            while (_currentShells > 0) {
                Log.d("checkNormalUpdate", "inside whileLoop  firstLine " + _currentShells + "at index ");

                //Check for turn repeats and distributed to head of _current player
                if (_currentShells == 1) {
                    _current.setIsTurn(true);
                    _opponent.setIsTurn(false);
                }
                if (_currentShells != 0) {
                    curTArray.get(7).addShell(1);
                    curTArray.get(7).addtoArray(clicked.getShellFromArray());
                    curTArray.get(7).setIsChanged(true);
                    _currentShells--;
                }


                Log.d("checks", _currentShells + "");

                //if shells remains, distrbute among enemy trays except head
                if (_currentShells != 0) {
                    //Other player's tray changes
                    for (int i = 0; i < 7; ++i) {
                        Log.d("checkNormalUpdate", "inside _opponent for Loop " + _currentShells + "at index " + i);
                        if (_currentShells == 0) {
                            break;
                        }
                        oppTArray.get(i).addShell(1);
                        oppTArray.get(i).addtoArray(clicked.getShellFromArray());
                        oppTArray.get(i).setIsChanged(true);
                        _currentShells--;
                    }
                }

                //if shell remains, distribute among _current player trays except head
                if (_currentShells != 0) {
                    _currentShells = distributeShellsToOpponentSide(clicked, curTArray, oppTArray, 0, _currentShells);
                }
            }
        }

        /**
         * Distrbutes shells from Current Tray Array to Opponnent
         * @param tobeRemoved shells from this tray are removed
         * @param pC           TrayArray of current Player
         * @param pO           TrayArray of opponent
         * @param index         Starting index for distribution
         * @param shellsRemaining   how many shells remaining
         * @return  how many shells remaining for distribution
         */
        public int distributeShellsToOpponentSide(Tray tobeRemoved, ArrayList<Tray> pC, ArrayList<Tray> pO, int index, int shellsRemaining) {
            int counter = index;
            int remaingShells = shellsRemaining;
            Log.d("playerMove", "remaing shells" + remaingShells);
            Log.d("arraySize", tobeRemoved.getShellArrayList().size() + "");

            for (int i = counter; i < 7; ++i) {
                Log.d("playerMove", "remaing shells inside for" + remaingShells);
                //break if no shells remaining
                if (remaingShells == 0) {
                    break;
                }

                //checks for steal
                if (remaingShells == 1 && pC.get(i).getShells() == 0) {
                    if (pO.get(6 - i).getShells() != 0) {
                        pC.get(7).addShell(pO.get(6 - i).steal());
                        Log.d("stringSize", pO.size() + "");
                        int s = pO.get(6 - i).getShellArrayList().size();
                        for (int j = 0; j < s; ++j) {
                            Log.d("stringSize", pO.get(6 - i).getShellArrayList().size() + "");
                            pC.get(7).addtoArray(pO.get(6 - i).getShellFromArray());
                        }

                    }
                    //trays are updated
                    pO.get(6 - i).setIsChanged(true);
                    pC.get(7).setIsChanged(true);
                    pC.get(7).addShell(1);
                    pC.get(7).addtoArray(tobeRemoved.getShellFromArray());
                }

                //normal update
                else if (remaingShells != 0) {
                    pC.get(i).addShell(1);
                    Log.d("arraySize", tobeRemoved.getShellArrayList().size() + "");
                    pC.get(i).addtoArray(tobeRemoved.getShellFromArray());
                }

                //counter
                remaingShells--;
                pC.get(i).setIsChanged(true);
            }

            Log.d("playerMove", "remaing shells" + remaingShells);
            return remaingShells;
        }

        /**
         * Simultaneous first turns
         * @param currentPTray
         * @param opponentPTray
         */
        public void processSimultaneousFirstTurn(ArrayList<Tray> currentPTray, ArrayList<Tray> opponentPTray) {
            Log.d("check", "entering processSimultaneousFirstTurn");
            //_current picked second so opponent gets priority
            _current.setIsTurn(false);
            _opponent.setIsTurn(true);

            //each players selected tray
            Tray clicked = _current.get_selectedTray();
            Tray opTray = _opponent.get_selectedTray();

            //opponents shells from selected tray is retrieved
            _opponentShells = opTray.getShells();

            //trays are emptied
            opTray.removeShell();
            clicked.removeShell();

            //selected trays will be animated
            clicked.setIsChanged(true);
            opTray.setIsChanged(true);
            //counter reset to allow another simultaneous turn
            _current.setCounter(0);
            _opponent.setCounter(0);

            Log.d("_simultaneousTurn", "before head-_current" + currentPTray.indexOf(clicked) + 1 + "    " + _currentShells);

            //shells distributed to _current players own trays first
            _currentShells = distributeShellsToOpponentSide(clicked, currentPTray, opponentPTray, currentPTray.indexOf(clicked) + 1, _currentShells);
            Log.d("_simultaneousTurn", "before head-_current" + _currentShells);


            Log.d("_simultaneousTurn", "before head-_opponent" + _opponentShells);

            //shells distributed to opponents players own trays first
            _opponentShells = distributeShellsToOpponentSide(opTray, opponentPTray, currentPTray, opponentPTray.indexOf(opTray) + 1, _opponentShells);
            Log.d("_simultaneousTurn", "before head-_opponent" + _opponentShells);

            //bonus rounds are  calbrated
            if (_currentShells == 1 || _opponentShells == 1) {
                //another simultaneous turn
                if (_currentShells == 1 && _opponentShells == 1) {
                    _simultaneousTurn = true;
                    _current.setIsTurn(true);
                    _opponent.setIsTurn(true);

                } else if (_currentShells == 1) {
                    _current.setIsTurn(true);
                    _opponent.setIsTurn(false);

                } else {

                    _current.setIsTurn(false);
                    _opponent.setIsTurn(true);
                }

            } else {
                _current.setIsTurn(false);
                _opponent.setIsTurn(true);
            }
            Log.d("_simultaneousTurn", "before head-_current" + _currentShells);
            Log.d("_simultaneousTurn", "before head-_opponent" + _opponentShells);
            //added to heads
            if (_currentShells != 0) {
                currentPTray.get(7).addShell(1);
                currentPTray.get(7).addtoArray(clicked.getShellFromArray());

                opponentPTray.get(7).setIsChanged(true);
                _currentShells--;
            }
            if (_opponentShells != 0) {
                opponentPTray.get(7).addShell(1);
                opponentPTray.get(7).addtoArray(opTray.getShellFromArray());
                opponentPTray.get(7).setIsChanged(true);
                _opponentShells--;
            }

            Log.d("_simultaneousTurn", "before _current dis-_current " + _currentShells);
            Log.d("_simultaneousTurn", "before enemy dist-_opponent " + _opponentShells);

            //if shells remains, distrbute among enemy trays except head
            if (_currentShells != 0) {
                //Other player's tray changes
                for (int i = 0; i < 7; ++i) {
                    Log.d("_simultaneousTurn", "before in if= _current" + _currentShells);
                    if (_currentShells == 0) {
                        break;
                    }
                    opponentPTray.get(i).addShell(1);
                    opponentPTray.get(i).addtoArray(clicked.getShellFromArray());
                    _currentShells--;
                    opponentPTray.get(i).setIsChanged(true);

                }
            }

            if (_opponentShells != 0) {
                //Other player's tray changes
                for (int i = 0; i < 7; ++i) {
                    Log.d("_simultaneousTurn", "before in if= _current" + _currentShells);
                    if (_opponentShells == 0) {
                        break;
                    }
                    currentPTray.get(i).addShell(1);
                    currentPTray.get(i).addtoArray(opTray.getShellFromArray());
                    _opponentShells--;
                    currentPTray.get(i).setIsChanged(true);

                }
            }

            if(_currentShells!=0){
                _currentShells = distributeShellsToOpponentSide(clicked, currentPTray, opponentPTray, currentPTray.indexOf(clicked) + 1, _currentShells);
            }
            if(_opponentShells!=0){
                _opponentShells = distributeShellsToOpponentSide(opTray, opponentPTray, currentPTray, opponentPTray.indexOf(opTray) + 1, _opponentShells);
            }
        }

        @Override
        public void run() {
            Log.d("checks", "entered run class");
            ArrayList<Tray> currentPTray = _current.get_traySet();
            ArrayList<Tray> opponentPTray = _opponent.get_traySet();

            //get shells from _current tray
            _currentShells = _current.get_selectedTray().getShells();
            Log.d("checks", _currentShells + "     " + currentPTray.indexOf(_current.get_selectedTray()));

            //simultaneous Turn is considered
            if (_simultaneousTurn) {
                _current.addCounter(1);
                if (_current.getCounter() > 0 && _opponent.getCounter() > 0) {
                    _simultaneousTurn = false;
                    processSimultaneousFirstTurn(currentPTray, opponentPTray);
                }
            }
            //normal turn
            else {
                _current.setIsTurn(false);
                _opponent.setIsTurn(true);
                normalUpdate(currentPTray,opponentPTray);
            }

            //if trays are empty
            if (checkIfAllTraysEmpty(_current)) {
                if (checkIfAllTraysEmpty(_opponent)) {
                    _current.setIsTurn(false);
                    _opponent.setIsTurn(false);
                    setGameOver(true);
                } else {
                    _current.setIsTurn(false);
                    _opponent.setIsTurn(true);
                }
            } else if (checkIfAllTraysEmpty(_opponent)) {
                _current.setIsTurn(true);
                _opponent.setIsTurn(false);
            }
        }
    }
}