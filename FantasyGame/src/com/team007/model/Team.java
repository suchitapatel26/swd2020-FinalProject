package com.team007.model;

/**
 * Class Team to set and get Table Team data from database
 */
public class Team {
    /**
     * Integer if
     */
    private int id;
    /**
     * Integer team api id
     */
    private int team_api_id;
    /**
     * Integer team fifa id
     */
    private int team_fifa_api_id;
    /**
     * String team long name
     */
    private String team_long_name;

    /**
     * String team short name
     */
    private String team_short_name;

    /**
     * Constructor to initialize Table Team objects
     * @param id Integer
     * @param team_api_id Integer
     * @param team_fifa_api_id Integer
     * @param team_long_name String
     * @param team_short_name String
     */
    public Team(int id, int team_api_id, int team_fifa_api_id, String team_long_name, String team_short_name) {
        this.id = id;
        this.team_api_id = team_api_id;
        this.team_fifa_api_id = team_fifa_api_id;
        this.team_long_name = team_long_name;
        this.team_short_name = team_short_name;
    }

    /**
     * get id
     * @return Returns id
     */
    public int getId() {
        return id;
    }

    /**
     * Set id
     * @param id Integer
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get team api id
     * @return Returns team api id
     */
    public int getTeam_api_id() {
        return team_api_id;
    }

    /**
     * Set team api id
     * @param team_api_id Integer
     */
    public void setTeam_api_id(int team_api_id) {
        this.team_api_id = team_api_id;
    }

    /**
     * Get team fifa id
     * @return Returns team fifa id
     */
    public int getTeam_fifa_api_id() {
        return team_fifa_api_id;
    }

    /**
     * Set team fifa id
     * @param team_fifa_id Integer
     */
    public void setTeam_fifa_api_id(int team_fifa_id) {
        this.team_fifa_api_id = team_fifa_id;
    }

    /**
     * Get team long name
     * @return Returns team long name
     */
    public String getTeam_long_name() {
        return team_long_name;
    }

    /**
     * Set team long name
     * @param team_long_name String
     */
    public void setTeam_long_name(String team_long_name) {
        this.team_long_name = team_long_name;
    }

    /**
     * Get team short name
     * @return Returns team short name
     */
    public String getTeam_short_name() {
        return team_short_name;
    }

    /**
     * Set team short name
     * @param team_short_name String
     */
    public void setTeam_short_name(String team_short_name) {
        this.team_short_name = team_short_name;
    }

    /**
     * Returns id, team_long_name, team_short_name
     * @return Returns id, team_long_name, team_short_name
     */
    @Override
    public String toString() {
        return ("Team [ID = " + this.id
                + "\nTeam Long Name = " + this.team_long_name
                + "\nTeam Short Name = " + this.team_short_name + "]\n");
    }
}
