package com.team007.model;

/**
 * Class Country to set and get Table County data
 */
public class Country {

    /**
     * Integer id
     */
    private int id;

    /**
     * String country
     */
    private String country;

    /**
     * Constructor with two arguments
     * @param id Int
     * @param country String
     */
    public Country(int id, String country) {
        this.id = id;
        this.country = country;
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
     * Get country
     * @return Returns country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Return ID and String data
     * @return String ID and County
     */
    @Override
    public String toString(){
        return ("Country [ID = " + this.id + ", CountryName = " + this.country + "]\n");
    }
}
