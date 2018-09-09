package com.example.k1465128.sungka;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents each tray where shells are stored
 */
public class Tray {
    private int shells;
    private boolean isChanged;
    private float xcord;
    private float ycord;
    private ArrayList<Shell> shellArrayList;
    private Random random;

    /**
     *   Overrides the built-in toString() method
     */
    @Override
    public String toString() {
        return "Tray{" +
                "shells=" + shells +
                '}';
    }

    /**
     *   Default Constructor
     */
    public Tray() {
        shellArrayList=new ArrayList<>();
        shells = 7;
        isChanged=false;
        random = new Random();
    }

    /**
     *  Returns all the shells stored by this Tray
     *  @return shellArrayList that contains the ArrayList<Shells> in this tray.
     */
    public ArrayList<Shell> getShellArrayList() {
        return shellArrayList;
    }

    /**
     *   Adds a tray with its x,y co-ordinates to this Tray
     *   @param  Shell object is added to shellArrayList
     */
    public void addtoArray(Shell shell){
        float xrandom =xcord+random.nextInt(40)-random.nextInt(50);
        float yrandom =ycord+random.nextInt(40)-random.nextInt(50);
        shell.setXcord(xrandom);
        shell.setYcord(yrandom);
        shellArrayList.add(shell);
    }

    /**
     *   Retrieves one shell from this Tray
     *   @return the shell at the index 0 from the ArrayList
     */
    public Shell getShellFromArray(){
        Shell temp = shellArrayList.get(0);
        shellArrayList.remove(0);
        return temp;
    }

    /**
     *  Sets new value of shells in this Tray
     *   @param n is the new value of the number of shells this Tray stores
     */
    public void setValue(int n) {
        shells = n;
    }

    /**
     *   Returns total shells in this tray
     *   return shells which is the number of shells of this Tray
     */
    public int getShells() {
        return shells;
    }

    /**
     *   removes all shells from this tray
     */
    public void removeShell() {
        shells=0;
    }

    /**
     *   @param the shells of this tray will be increased by n
     */
    public void addShell(int n){
        shells += n;
    }

    /**
     *   @return returns true/false if this tray has been changed
     */
    public boolean isChanged() {
        return isChanged;
    }

    /**
     *   @param isChanged is the new boolean value for the variable isChanged
     */
    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    /**
     *   When this Tray is stolen from, the number of shells this Tray stores is returned
     *   @return returns the number of shells this tray stores without changing the value of
     *   the shells contained
     */
    public int steal(){
        int s = shells;
        shells = 0;
        return s;
    }

    /**
     *   Sets c,y co-ordinates for this tray
     *   @param x is the new x co-ordinate of this tray object which will be required for the
     *   representation of this tray in the graphical user interface
     */
    public void setCordinates(float x, float y){
        xcord=x;
        ycord=y;
    }

    /**
     *   calibrates the x and y co-ordinates of the Tray object
     */
    public void recalibrateCoordinates(){
        for (int i = 0; i <shellArrayList.size() ; i++) {
            {
                float xrandom =xcord+random.nextInt(40)-random.nextInt(50);
                float yrandom =ycord+random.nextInt(40)-random.nextInt(50);
                shellArrayList.get(i).setXcord(xrandom);
                shellArrayList.get(i).setYcord(yrandom);
            }
        }
    }
}

