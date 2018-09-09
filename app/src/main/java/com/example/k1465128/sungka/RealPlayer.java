package com.example.k1465128.sungka;

import android.util.Log;

import java.util.ArrayList;

/**
 * This class represents a human player
 * It extends Player class
 */
public class RealPlayer extends Player{
    private ArrayList<Tray> _traySet;
    private Tray _selectedTray;
    private String name;


    /**
     * Default constructor
     */
    public RealPlayer() {
        name="";
        _traySet =new ArrayList<>();
        setupTrayArray(_traySet);
        _traySet = get_traySet();
        setIsAi(false);
        setIsTurn(true);
        _selectedTray = new Tray();
        for (int w = 0; w < 7; ++w) {
            for (int i = 0; i < 7; ++i) {
                Shell ss = new Shell();
                _traySet.get(w).addtoArray(ss);
            }
        }
    }

    /**
     * if tray Array is properly updated
     * @return true if properly updated
     */
    public boolean checkstartTray(){

        for(int i=0; i<8;i++){
            if(i!=7) {
                if (_traySet.get(i).getShells() == 7) {
                } else {
                    return false;
                }
            }
            else{
                if(_traySet.get(i).getShells()==0){}
                else{
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The tray that has been clicked by a player is selected
     * @param i index of selected Tray
     */
    public void set_selectedTray(int i) {
        _selectedTray = _traySet.get(i);
        Log.d("checks", "in if" + _selectedTray.getShells() + "");
    }

    /**
     * Retrieves the Tray that was selected
     * @return _selectedTray the Tray that was selected by a Player
     */
    @Override
    public Tray get_selectedTray() {
        return _selectedTray;
    }

    /**
     * returns name of this RealPlayer
     * @return name the name of this RealPlayer
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * set name of this RealPlayer
     * @param name is the new name of this RealPlayer
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the Tray ArrayList of this RealPlayer
     * @return _traySet the ArrayList<Tray> of this RealPlayer
     */
    @Override
    public ArrayList<Tray> get_traySet() {
        return _traySet;
    }



}
