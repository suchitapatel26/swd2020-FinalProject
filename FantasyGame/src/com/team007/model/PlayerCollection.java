package com.team007.model;

import java.util.ArrayList;

/**
 * Class PlayerCollection Stores Player data in ArrayList from Database
 */
public class PlayerCollection {

    /**
     * Create player list ArrayList object
     */
    private ArrayList<Player> playerList = new ArrayList<Player>();

    /**
     * Constructor with one argument
     * @param playerList ArrayList<Player></Player>
     */
    public PlayerCollection(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    /**
     * Get list of players
     * @return Returns list of players
     */
    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

    /**
     * Set list of players
     * @param playerList ArrayList<Player></Player>
     */
    public void setPlayerList(ArrayList<Player> playerList){
        this.playerList = playerList;
    }

    /**
     * Player list
     * @return Returns player list
     */
    @Override
    public String toString(){
        return ("PlayerCollection[players = " + this.playerList + "]");
    }
}
