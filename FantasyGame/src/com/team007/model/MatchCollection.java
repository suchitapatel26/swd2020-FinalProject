package com.team007.model;

import java.util.ArrayList;

/**
 * Class MatchCollection stores MatchGame data in ArrayList from Database
 */
public class MatchCollection {

    /**
     * Create match list ArrayList object
     */
    private ArrayList<Match> matchArrayList = new ArrayList<Match>();

    /**
     * Constructor with one argument
     * @param matchArrayList ArrayList<Match></Match>
     */
    public MatchCollection(ArrayList<Match> matchArrayList){
        this.matchArrayList = matchArrayList;
    }

    /**
     * Get Match information
     * @return Returns match information
     */
    public ArrayList<Match> getMatchArrayList() {
        return matchArrayList;
    }

    /**
     * Set Match information
     * @param matchArrayList ArrayList<Match></Match>
     */
    public void setMatchArrayList(ArrayList<Match> matchArrayList) {
        this.matchArrayList = matchArrayList;
    }

    /**
     * Match Information
     * @return Return match information
     */
    @Override
    public String toString() {
        return ("MatchCollection{Matches = " + this.matchArrayList + "]\n");
    }
}
