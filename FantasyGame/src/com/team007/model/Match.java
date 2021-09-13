package com.team007.model;

/**
 * Class Match to set and get Table MatchGame data
 */
public class Match {
    /**
     * Integer ID
     */
    private int id;
    /**
     * Integer country ID
     */
    private int country_id;
    /**
     * Integer leauge ID
     */
    private int leauge_id;
    /**
     * String season
     */
    private String season;
    /**
     * Integer stage
     */
    private int stage;
    /**
     * String date
     */
    private String date;
    /**
     * String home team name
     */
    private String home_team_name;
    /**
     * String away team name
     */
    private String away_team_name;
    /**
     * Integer away team goal
     */
    private int away_team_goal;
    /**
     * Integer home team goal
     */
    private int home_team_goal;
    /**
     * String goal
     */
    private String goal;
    /**
     * String shot on
     */
    private String shoton;
    /**
     * String shot off
     */
    private String shotoff;
    /**
     * String foul commit
     */
    private String foulcommit;
    /**
     * String card
     */
    private String card;
    /**
     * String cross
     */
    private String cross;
    /**
     * String corner
     */
    private String corner;
    /**
     * String possession
     */
    private String possession;

    /**
     * Constructor with no argument
     */
    public Match() { }

    /**
     * Constructor to initialize Table Match objects from database
     * @param id Integer
     * @param country_id Integer
     * @param leauge_id Integer
     * @param season String
     * @param stage Integer
     * @param date String
     * @param home_team_name String
     * @param away_team_name String
     * @param away_team_goal Integer
     * @param home_team_goal Integer
     * @param goal String
     * @param shoton String
     * @param shotoff String
     * @param foulcommit String
     * @param card String
     * @param cross String
     * @param corner String
     * @param possession String
     */
    public Match(int id, int country_id, int leauge_id, String season, int stage, String date, String home_team_name, String away_team_name,
                 int away_team_goal, int home_team_goal, String goal, String shoton, String shotoff, String foulcommit, String card, String cross,
                 String corner, String possession) {
        this.id = id;
        this.country_id = country_id;
        this.leauge_id = leauge_id;
        this.season = season;
        this.stage = stage;
        this.date = date;
        this.home_team_name = home_team_name;
        this.away_team_name = away_team_name;
        this.away_team_goal = away_team_goal;
        this.home_team_goal = home_team_goal;
        this.goal = goal;
        this.shoton = shoton;
        this.shotoff = shotoff;
        this.foulcommit = foulcommit;
        this.card = card;
        this.cross = cross;
        this.corner = corner;
        this.possession = possession;
    }

    /**
     * Get ID
     * @return Return ID
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
     * Get County ID
     * @return Returns Country ID
     */
    public int getCountry_id() {
        return country_id;
    }

    /**
     * Set Country ID
     * @param country_id Integer
     */
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    /**
     * Get Leauge ID
     * @return Returns leauge ID
     */
    public int getLeauge_id() {
        return leauge_id;
    }

    /**
     * Set Leauge ID
     * @param leauge_id Integer
     */
    public void setLeauge_id(int leauge_id) {
        this.leauge_id = leauge_id;
    }

    /**
     * Get Season
     * @return Returns Season
     */
    public String getSeason() {
        return season;
    }

    /**
     * Set Season
     * @param season String
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Get Stage
     * @return Returns stage
     */
    public int getStage() {
        return stage;
    }

    /**
     * Set stage
     * @param stage Integer
     */
    public void setStage(int stage) {
        this.stage = stage;
    }

    /**
     * Get date
     * @return Returns date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set date
     * @param date String
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get home team name
     * @return Returns home team name
     */
    public String getHome_team_name() {
        return home_team_name;
    }

    /**
     * Set home team name
     * @param home_team_name String
     */
    public void setHome_team_name(String home_team_name) {
        this.home_team_name = home_team_name;
    }

    /**
     * Get away team name
     * @return Returns away team name
     */
    public String getAway_team_name() {
        return away_team_name;
    }

    /**
     * Set away team name
     * @param away_team_name String
     */
    public void setAway_team_name(String away_team_name) {
        this.away_team_name = away_team_name;
    }

    /**
     * Get away team goal
     * @return Returns away team goal
     */
    public int getAway_team_goal() {
        return away_team_goal;
    }

    /**
     * Set away team goal
     * @param away_team_goal Integer
     */
    public void setAway_team_goal(int away_team_goal) {
        this.away_team_goal = away_team_goal;
    }

    /**
     * Get home team goal
     * @return Returns home team goal
     */
    public int getHome_team_goal() {
        return home_team_goal;
    }

    /**
     * Set home team goal
     * @param home_team_goal Integer
     */
    public void setHome_team_goal(int home_team_goal) {
        this.home_team_goal = home_team_goal;
    }

    /**
     * Get goal
     * @return Returns goal
     */
    public String getGoal() {
        return goal;
    }

    /**
     * Set gaoal
     * @param goal String
     */
    public void setGoal(String goal) {
        this.goal = goal;
    }

    /**
     * Get shot on
     * @return Returns shot on
     */
    public String getShoton() {
        return shoton;
    }

    /**
     * Set shot on
     * @param shoton String
     */
    public void setShoton(String shoton) {
        this.shoton = shoton;
    }

    /**
     * Get shot off
     * @return Returns shot off
     */
    public String getShotoff() {
        return shotoff;
    }

    /**
     * Set shot off
     * @param shotoff String
     */
    public void setShotoff(String shotoff) {
        this.shotoff = shotoff;
    }

    /**
     * Get foul commit
     * @return Returns foul commit
     */
    public String getFoulcommit() {
        return foulcommit;
    }

    /**
     * Set foul commit
     * @param foulcommit String
     */
    public void setFoulcommit(String foulcommit) {
        this.foulcommit = foulcommit;
    }

    /**
     * Get card
     * @return Returns card
     */
    public String getCard() {
        return card;
    }

    /**
     * Set card
     * @param card String
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * Get cross
     * @return Returns cross
     */
    public String getCross() {
        return cross;
    }

    /**
     * Set cross
     * @param cross String
     */
    public void setCross(String cross) {
        this.cross = cross;
    }

    /**
     * Get corner
     * @return Returns corner
     */
    public String getCorner() {
        return corner;
    }

    /**
     * Set corner
     * @param corner String
     */
    public void setCorner(String corner) {
        this.corner = corner;
    }

    /**
     * Get possession
     * @return Returns possession
     */
    public String getPossession() {
        return possession;
    }

    /**
     * Set possession
     * @param possession String
     */
    public void setPossession(String possession) {
        this.possession = possession;
    }

    /**
     * Return Country ID, Home team name, Away team name, Home team goal, and Away Team Goal
     * @return Retuns a string with Country ID, Home team name, Away team name, Home team goal, and Away Team Goal
     */
    @Override
    public String toString() {
        return ("Match [ID = " + this.id
                + ", Country ID = " + this.country_id
                + ", Home Team Name = " + this.home_team_name
                + ", Away Team Name = " + this.away_team_name
                + ", Home Team Goal = " + this.home_team_goal
                + ", Away Team Goal = " + this.away_team_goal + "]\n");
    }
}
