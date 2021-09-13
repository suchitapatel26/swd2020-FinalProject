package com.team007.model;

/**
 * Class Player to set and get Table Player data
 */
public class Player {
    /**
     * Integer id
     */
    private int id;
    /**
     * Integer player api id
     */
    private int player_api_id;
    /**
     * String player name
     */
    private String player_name;
    /**
     * Integer player fifa api id
     */
    private int player_fifa_api_id;
    /**
     * String birthday
     */
    private String birthday;
    /**
     * Integer height
     */
    private int height;
    /**
     * Integer weight
     */
    private int weight;
    /**
     * Integer soccer goal
     */
    private int fantasy_soccer_goal;


    /**
     * Constructor with no argument
     */
    public Player(){}


    /**
     * Constructor to initialize Table Player objects from database
     * @param id Integer
     * @param player_api_id Integer
     * @param player_name String
     * @param player_fifa_api_id Integer
     * @param birthday String
     * @param height Integer
     * @param weight Integer
     * @param fantasy_soccer_goal Integer
     */
    public Player(int id, int player_api_id, String player_name, int player_fifa_api_id, String birthday, int height, int weight, int fantasy_soccer_goal) {
        this.id = id;
        this.player_api_id = player_api_id;
        this.player_name = player_name;
        this.player_fifa_api_id = player_fifa_api_id;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.fantasy_soccer_goal = fantasy_soccer_goal;
    }

    /**
     * Get player api id
     * @return Returns player api id
     */
    public int getPlayer_api_id() {
        return player_api_id;
    }

    /**
     * Set player api id
     * @param player_api_id Integer
     */
    public void setPlayer_api_id(int player_api_id) {
        this.player_api_id = player_api_id;
    }

    /**
     * Get player name
     * @return Return player name
     */
    public String getPlayer_name() {
        return player_name;
    }

    /**
     * Set player name
     * @param player_name String
     */
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    /**
     * Get player fifa appi ID
     * @return Returns player fifa api ID
     */
    public int getPlayer_fifa_api_id() {
        return player_fifa_api_id;
    }

    /**
     * Set player fifa api ID
     * @param player_fifa_api_id Integer
     */
    public void setPlayer_fifa_api_id(int player_fifa_api_id) {
        this.player_fifa_api_id = player_fifa_api_id;
    }

    /**
     * Get height
     * @return Returns height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set height
     * @param height Integer
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get weight
     * @return Returns weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set weight
     * @param weight Integer
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Get ID
     * @return Returns ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID
     * @param id Integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get birthday
     * @return Returns birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Set Birthday
     * @param birthday String
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Get fantasy soccer goal
     * @return Returns fantasy soccer goal
     */
    public int getFantasy_soccer_goal() {return fantasy_soccer_goal; }

    /**
     * Set fantasy soccer goal
     * @param fantasy_soccer_goal Integer
     */
    public void setFantasy_soccer_goal(int fantasy_soccer_goal) { this.fantasy_soccer_goal = fantasy_soccer_goal; }

    /**
     * Returns string with player ID, Player name, Birthday, Height, Weight, Fantasy soccer goal
     * @return Returns string with player ID, Player name, Birthday, Height, Weight, Fantasy soccer goal
     */
    @Override
    public String toString(){
        return("Player [ID = " + this.id +
                "\nPlayer Name = " + this.player_name +
                "\nBirthday = " + this.birthday +
                "\nHeight = " + this.height +
                "\nWeight = " + this.weight +
                "\nFantasy Soccer Goal = " + this.fantasy_soccer_goal + "]\n");
    }
}
