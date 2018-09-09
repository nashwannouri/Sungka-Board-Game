package com.example.k1465128.sungka;

import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * This class represents abstract player Class
 */
public abstract class Player{
    private String name;
    private ArrayList<ImageButton> buttonArrayList;
    private ArrayList<Tray> traySet;
    private boolean isTurn;
    private Tray selectedTray;
    private int counter;
    private boolean isAi=false;

    /**
     * Default constructor for abstract Player
     */
    public Player(){
        selectedTray = new Tray();
        counter = 0;
        buttonArrayList = new ArrayList<>();
    }

    /**
     * @return name of Player
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the new name of the Player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return true if Player is an AiPlayer
     */
    public boolean isAi() {
        return isAi;
    }

    /**
     * @param isAi sets Player to be an Ai player
     */
    public void setIsAi(boolean isAi) {
        this.isAi = isAi;
    }

    /**
     * @return true if it is Player's turn to make a move
     */
    public boolean isTurn() {
        return isTurn;
    }

    /**
     * @return traySet the ArrayList<Tray> of this Player
     */
    public ArrayList<Tray> get_traySet() {
        return traySet;
    }

    /**
     * Trays set is created
     * @param tt ArrayList<Tray> to be set up
     */
    public void setupTrayArray(ArrayList<Tray> tt) {
        for (int i = 0; i < 7; i++) {tt.add(new Tray());}
        Tray head = new Tray();
        head.setValue(0);
        tt.add(head);
    }


    /**
     * get the counter for firstTurn
     * @return returns counter number
     */
    public int getCounter() {
        return counter;
    }

    /**
     * set the counter for firstturn
     * @param n returns counter number;
     */
    public void setCounter(int n) {
        counter = n;
    }

    /**
     * add n to the counter
     * @param n to be added to counter
     */
    public void addCounter(int n) {
        counter = counter + n;
    }

    /**
     * get the Tray that was clicked
     * @return Tray that was clicked
     */
    public Tray get_selectedTray() {
        return selectedTray;
    }

    /**
     * returns true if it is the Player's turn
     * @return true if its the Player's turn
     */
    public boolean getIsTurn(){
        return  isTurn;
    }

    /**
     * state if its the Player's turn
     * @param b is the new boolean value for isTurn
     */
    public void setIsTurn(boolean b) {
        isTurn = b;
    }


}
