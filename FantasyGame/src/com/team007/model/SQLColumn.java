package com.team007.model;

/**
 * Class SQLColumn to set and get column name and column type from Database
 */
public class SQLColumn {

    /**
     * String column name
     */
    private String colName;
    /**
     * String column type
     */
    private String colType;

    /**
     * Constructor with two string arguments
     * @param colName String
     * @param colType String
     */
    public SQLColumn(String colName, String colType) {
        this.colName = colName;
        this.colType = colType;
    }

    /**
     * Get column name
     * @return Returns column name
     */
    public String getColName() {
        return colName;
    }

    /**
     * Set column name
     * @param colName String
     */
    public void setColName(String colName) {
        this.colName = colName;
    }

    /**
     * Get column type
     * @return Returns column type
     */
    public String getColType() {
        return colType;
    }

    /**
     * Set column type
     * @param colType String
     */
    public void setColType(String colType) {
        this.colType = colType;
    }

}
