package com.example.k1465128.sungka;


/**
 * This class represents each shell that will be stored in Tray objects
 * It also stores the x and y co-ordinates the shell object
 */
public class Shell {
    float xcord;
    float ycord;

    /**
     * Default constructor
     */
    public Shell() {
        xcord = 0;
        ycord = 0;
    }

    /**
     * Retrieves x co-ordinate of this shell
     * @return xcord which is the x co-ordinate of this shell
     */
    public float getXcord() {
        return xcord;
    }

    /**
     * Retrieves y co-ordinate of this shell
     * @return ycord which is the y co-ordinate of this shell
     */
    public float getYcord() {
        return ycord;
    }

    /**
     * Sets x co-ordinate of this shell
     * @param x is the new x co-ordinate of this shell
     */
    public void setXcord(float x) {
        xcord = x;
    }

    /**
     * Sets y co-ordinate of this shell
     * @param y is the new y co-ordinate of this shell
     */
    public void setYcord(float y) {
        ycord = y;
    }

}
