package com.team007.model;

/**
 * Class PlayerAttributes to set and get Table PlayerAttributes data
 */
public class PlayerAttributes {
    /**
     * Integer ID
     */
    private int id;
    /**
     * Integer player fifa api id
     */
    private int player_fifa_api_id;
    /**
     * Integer player api id
     */
    private int player_api_id;
    /**
     * Integer overall rating
     */
    private int overall_rating;
    /**
     * Integer potential
     */
    private int potential;
    /**
     * String preferred foot
     */
    private String preferred_foot;
    /**
     * String attacking work rate
     */
    private String attacking_work_rate;
    /**
     * String defensive work rate
     */
    private String defensive_work_rate;
    /**
     * Integer free kick accuracy
     */
    private int free_kick_accuracy;
    /**
     * Integer ball control
     */
    private int ball_control;
    /**
     * Integer shot power
     */
    private int shot_power;
    /**
     * Integer stamina
     */
    private int stamina;
    /**
     * Integer strength
     */
    private int strength;
    /**
     * Integer penalties
     */
    private int penalties;

    /**
     * Constructor to initialize PlayerAttribute objects
     */
    public PlayerAttributes(int id, int player_fifa_api_id, int player_api_id, int overall_rating,
                            int potential, String preferred_foot, String attacking_work_rate, String defensive_work_rate,
                            int free_kick_accuracy, int ball_control, int shot_power, int stamina, int strength, int penalties) {
        this.id = id;
        this.player_fifa_api_id = player_fifa_api_id;
        this.player_api_id = player_api_id;
        this.overall_rating = overall_rating;
        this.potential = potential;
        this.preferred_foot = preferred_foot;
        this.attacking_work_rate = attacking_work_rate;
        this.defensive_work_rate = defensive_work_rate;
        this.free_kick_accuracy = free_kick_accuracy;
        this.ball_control = ball_control;
        this.shot_power = shot_power;
        this.stamina = stamina;
        this.strength = strength;
        this.penalties = penalties;
    }

    /**
     * Get id
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
     * Get player fifa api id
     * @return Returns player fifa api id
     */
    public int getPlayer_fifa_api_id() {
        return player_fifa_api_id;
    }

    /**
     * Set player fifa api id
     * @param player_fifa_api_id Integer
     */
    public void setPlayer_fifa_api_id(int player_fifa_api_id) {
        this.player_fifa_api_id = player_fifa_api_id;
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
     * Get overall rating
     * @return Returns overall rating
     */
    public int getOverall_rating() {
        return overall_rating;
    }

    /**
     * Set overall rating
     * @param overall_rating Integer
     */
    public void setOverall_rating(int overall_rating) {
        this.overall_rating = overall_rating;
    }

    /**
     * Get potential
     * @return Returns potential
     */
    public int getPotential() {
        return potential;
    }

    /**
     * Set potential
     * @param potential Integer
     */
    public void setPotential(int potential) {
        this.potential = potential;
    }

    /**
     * Get preferred foot
     * @return Returns preferred foot
     */
    public String getPreferred_foot() {
        return preferred_foot;
    }

    /**
     * Set preferred foot
     * @param preferred_foot String
     */
    public void setPreferred_foot(String preferred_foot) {
        this.preferred_foot = preferred_foot;
    }

    /**
     * Get attacking work rate
     * @return Returns attacking work rate
     */
    public String getAttacking_work_rate() {
        return attacking_work_rate;
    }

    /**
     * Set attacking work rate
     * @param attacking_work_rate String
     */
    public void setAttacking_work_rate(String attacking_work_rate) {
        this.attacking_work_rate = attacking_work_rate;
    }

    /**
     * Get defensive work rate
     * @return Returns defensive work rate
     */
    public String getDefensive_work_rate() {
        return defensive_work_rate;
    }

    /**
     * Set defensive work rate
     * @param defensive_work_rate String
     */
    public void setDefensive_work_rate(String defensive_work_rate) {
        this.defensive_work_rate = defensive_work_rate;
    }

    /**
     * Get ball control
     * @return Returns ball control
     */
    public int getBall_control() {
        return ball_control;
    }

    /**
     * Set ball control
     * @param ball_control Integer
     */
    public void setBall_control(int ball_control) {
        this.ball_control = ball_control;
    }

    /**
     * Get shot power
     * @return Returns shot power
     */
    public int getShot_power() {
        return shot_power;
    }

    /**
     * Set shot power
     * @param shot_power Integer
     */
    public void setShot_power(int shot_power) {
        this.shot_power = shot_power;
    }

    /**
     * Get stamina
     * @return Returns stamina
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * Set stamina
     * @param stamina Integer
     */
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    /**
     * Get strength
     * @return Returns strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Set strength
     * @param strength Integer
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Get penalties
     * @return Returns penalties
     */
    public int getPenalties() {
        return penalties;
    }

    /**
     * Set penalties
     * @param penalties Integer
     */
    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    /**
     * Returns string with ID, Overall rating, Potential, Preferred foot, Attacking work rate,
     * Defensive work rate, Free ick accuracy, Ball control, Shot power, Stamina, Strength, and Penalties
     * @return Returns string with ID, Overall rating, Potential, Preferred foot, Attacking work rate,
     *         Defensive work rate, Free ick accuracy, Ball control, Shot power, Stamina, Strength, and Penalties
     */
    @Override
    public String toString(){
        return ("Player Attributes [ID = " + this.id +
                "\nOverall Rating = " + this.overall_rating +
                "\nPotential = " + this.potential +
                "\nPreferred Foot = " + this.preferred_foot +
                "\nAttacking Work Rate = " + this.attacking_work_rate +
                "\nDefensive Work Rate = " + this.defensive_work_rate +
                "\nFree Kick Accuracy = " + this.free_kick_accuracy +
                "\nBall Control = " + this.ball_control +
                "\nShot Power = " + this.shot_power +
                "\nStamina = " + this.stamina +
                "\nStrength = " + this.strength +
                "\nPenalties = " + this.penalties + "]\n");
    }
}
