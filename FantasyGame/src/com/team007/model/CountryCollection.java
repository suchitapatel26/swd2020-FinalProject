package com.team007.model;

import java.util.ArrayList;

/**
 * Class CountryCollection stores Table Country data in ArrayList from Database
 */
public class CountryCollection {

    /**
     * Create country list ArrayList object
     */
    private ArrayList<Country> countryList = new ArrayList<Country>();

    /**
     * Constructor with one argument
     * @param countryList ArrayList<Country></Country>
     */
    public CountryCollection( ArrayList<Country> countryList){
        this.countryList =  countryList;
    };

    /**
     * Get list of country
     * @return Returns list of country
     */
    public ArrayList<Country> getCountryList() {
        return countryList;
    }

    /**
     * Set list of country
     * @param countryList ArrayList<Country></Country>
     */
    public void setCountryList(ArrayList<Country> countryList) {
        this.countryList = countryList;
    }

    /**
     * Country List
     * @return Return list of country
     */
    @Override
    public String toString(){
        return ("CountryCollection[countries = " + this.countryList + "]\n");
    }

}
