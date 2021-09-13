package com.team007.model;

import java.util.ArrayList;

/**
 * Class PlayerAttributesCollection stores PlayerAttributes data in in ArrayList from Database
 */
public class PlayerAttributesCollection {

    /**
     * Create player attributes list ArrayList object
     */
    private ArrayList<PlayerAttributes> playerAttributesArrayList = new ArrayList<PlayerAttributes>();

    /**
     * Constructor with one argument
     * @param playerAttributesArrayList ArrayList<PlayerAttributes></PlayerAttributes>
     */
    public PlayerAttributesCollection(ArrayList<PlayerAttributes> playerAttributesArrayList){
        this.playerAttributesArrayList = playerAttributesArrayList;
    }

    /**
     * Get PlayerAttributes information
     * @return Returns player attributes information
     */
    public ArrayList<PlayerAttributes> getPlayerAttributesArrayList(){
        return playerAttributesArrayList;
    }

    /**
     * Set PlayerAttributes information
     * @param playerAttributesArrayList ArrayList<PlayerAttributes></PlayerAttributes>
     */
    public void setPlayerAttributesArrayList(ArrayList<PlayerAttributes> playerAttributesArrayList){
        this.playerAttributesArrayList = playerAttributesArrayList;
    }

    /**
     * Player attributes Information
     * @return Returns player attributes information
     */
    @Override
    public String toString(){
        return ("PlayerAttributesCollection[playerAttributes = " + this.playerAttributesArrayList + "]\n");
    }
}
