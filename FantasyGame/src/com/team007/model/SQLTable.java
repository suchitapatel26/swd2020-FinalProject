package com.team007.model;

/**
 * Class SQLTable to set abd get Table name
 */
public class SQLTable {

    /**
     * String table name
     */
    private String tableName;

    /**
     * Constructor with one String argument
     * @param tableName String
     */
    public SQLTable(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get table name
     * @return Returns table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Set table name
     * @param tableName String
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
