package com.example.k1465128.sungka;

/**
 * This class represents each Score object
 */

public class Score implements Comparable<Score> {
    private String name;
    private int score;

    /**
     * Default constructor
     * @param n is the name variable for each Score object
     * @param num is the score variable for each Score object
     */
    public Score(String n, int num){
        name=n;
        score=num;
    }

    /**
     * Overrides the compareTo method
     * @param s is a Score object to be compared with this Score class
     * @return 1 if this class has a higher larger variable than object s,
     *        -1 if it has smaller score and 0 if they have the same score variable
     */
    @Override
    public int compareTo(Score s){
        return s.score>score? 1 : s.score<score? -1 : 0;
    }

    /**
     * Overrides default toString() method
     * @return name and its score in a String format
     */
    @Override
    public String toString(){
        return name+" - "+score;
    }
}