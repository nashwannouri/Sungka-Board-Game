package com.example.k1465128.sungka;

/**
 * This class represents the stats of each RealPlayer
 */
public class PlayerStats implements Comparable<PlayerStats> {
    private String name;
    private int wins = 0;
    private int gamesPlayed =0;

    /**
     * Default constructor
     * @param name is the name of this PlayerStats class
     */
    public PlayerStats(String name){
        this.name=name;
    }

    /**
     * Increments number of wins by 1
     */
    public void increaseWins(){
        wins++;
    }

    /**
     * Increments the number of gamesPlayed by 1
     */
    public void increaseGamesPlayed(){
        gamesPlayed++;
    }

    /**
     * Gets the name of this class
     * @return the name of this class
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the number of wins to the value received in the parameters
     * @param w the new number of wins of this class
     */
    public void setWins(int w){
        wins = w;
    }

    /**
     * Sets the number of games played to the value received in the parameters
     * @param g the new number of gamesPlayed of this class
     */
    public void setGamesPlayed(int g){
        gamesPlayed = g;
    }

    /**
     * Overrides the built-in compareTo method
     * @param p is the PlayerStats to compare this class with
     * @return 1 if this class has higher wins than p, -1 if this class has less wins, 0 if the two
     *         objects have the same wins
     */
    @Override
    public int compareTo(PlayerStats p){
        return p.wins>wins? 1 : p.wins<wins? -1 : 0;
    }

    /**
     * Overrides built-in toString method
     * @return name, wins and gamesPlayed in a valid format
     */
    public String toString(){
        return name+" - "+ wins + " - " + gamesPlayed;
    }
}