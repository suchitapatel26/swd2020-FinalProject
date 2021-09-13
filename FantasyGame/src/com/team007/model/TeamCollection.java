package com.team007.model;

import java.util.ArrayList;

/**
 * Class TeamCollection stores Team data in ArrayList from database
 */
public class TeamCollection {

    /**
     * Create team list ArrayList object
     */
    private ArrayList<Team> teamList = new ArrayList<Team>();

    /**
     * Constructor with one argument
     * @param teamList ArrayList<Team></Team>
     */
    public TeamCollection(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    /**
     * Get list of team
     * @return Returns list of team
     */
    public ArrayList<Team> getTeamList(){
        return teamList;
    }

    /**
     * Set list of team
     * @param teamList ArrayList<Team></Team>
     */
    public void setTeamList(ArrayList<Team> teamList){
        this.teamList = teamList;
    }

    /**
     * Team List
     * @return Returns list of team
     */
    @Override
    public String toString(){
        return ("TeamCollection[teams = " + this.teamList + "]\n");
    }
}
