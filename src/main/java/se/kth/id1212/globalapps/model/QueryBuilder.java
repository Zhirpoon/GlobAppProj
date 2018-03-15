package se.kth.id1212.globalapps.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.model.Constants.DbConstants;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class QueryBuilder {
    DbConstants dbConstants = new DbConstants();
    private String query = "";
    
    /**
     * This is the constructor for the <code>QueryBuilder</code> which initiates the query to be returned later.
     * Right now only application queries are needed to be built, thus it is the only one supported.
     * @param queryType The <code>QueryBuilder</code>'s query type.
     */
    public QueryBuilder(String queryType) {
        if(queryType.equalsIgnoreCase(dbConstants.APPLICATIONENTITY_QUERY_NAME)) {
            query += "SELECT * "
                    + "FROM " + dbConstants.APPLICATIONENTITY_TABLE_NAME
                    + " " + dbConstants.APPLICATIONENTITY_QUERY_NAME 
                    + " WHERE ";
        }
    }
    
    /**
     * Adds name search criteria to the <code>QueryBuilder</code>, if both are null then it will add a query which accepts all names.
     * @param firstName This is the search criteria for the application's owner's first name.
     * @param lastName This is the search criteria for the application's owner's last name.
     */
    public void addNameCriteria(String firstName, String lastName) {  
        String nameQueryPart = dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_OWNER + " IN "
                + "(SELECT " + dbConstants.USERENTITY_QUERY_NAME + "." + dbConstants.USERENTITY_COLUMN_USERNAME 
                + " FROM " + dbConstants.USERENTITY_TABLE_NAME + " " + dbConstants.USERENTITY_QUERY_NAME 
                + " WHERE " + dbConstants.USERENTITY_QUERY_NAME + "." + dbConstants.USERENTITY_COLUMN_FIRSTNAME + " LIKE ";
        if(lastName.trim().equalsIgnoreCase("") && firstName.trim().equalsIgnoreCase("")) {
            nameQueryPart += "'%' "
                    + "AND " + dbConstants.USERENTITY_QUERY_NAME + "." + dbConstants.USERENTITY_COLUMN_LASTNAME + " LIKE '%')";
        } else {    
            if(firstName.trim().equalsIgnoreCase("")) {
                nameQueryPart += "'%' ";
            } else {
                nameQueryPart += "'" + firstName + "' ";
            }
            nameQueryPart += "AND " + dbConstants.USERENTITY_QUERY_NAME + "." + dbConstants.USERENTITY_COLUMN_LASTNAME +  " LIKE ";
            if(lastName.trim().equalsIgnoreCase("")) {
                nameQueryPart += "'%')";
            } else {
                nameQueryPart += "'" + lastName + "')";
            }
        }
        query += nameQueryPart;
    }
    
    /**
     * Adds the registration date as search criteria to the <code>QueryBuilder</code> as long as the parameter is not null.
     * @param registrationDate This is the registration date of the user who owns the application.
     */
    public void addRegistrationDateCriteria(Date registrationDate) {
        String dateQueryPart = " AND " + dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_OWNER + " IN "
                + "(SELECT " + dbConstants.USERENTITY_QUERY_NAME + "." + dbConstants.USERENTITY_COLUMN_USERNAME
                + " FROM " + dbConstants.USERENTITY_TABLE_NAME + " " + dbConstants.USERENTITY_QUERY_NAME
                + "WHERE " + dbConstants.USERENTITY_QUERY_NAME + "." + dbConstants.USERENTITY_COLUMN_REGISTRATIONDATE + " = ";
        if(registrationDate != null) {
            dateQueryPart +=  "'" + formatDateToQuery(registrationDate) + "')";
            query += dateQueryPart;
        }
    }
    
    /**
     * Formats a date into the structure needed to be compared to dates in the database.
     * @param date The date input to be formatted.
     * @return Returns a string representation of the date in proper format.
     */
    private String formatDateToQuery(Date date) {
        SimpleDateFormat yearMonthDayFormat = new SimpleDateFormat("yyyy-MM-dd");
        return yearMonthDayFormat.format(date);
    }
    
    /**
     * Adds expertise search criteria to the <code>QueryBuilder</code> as long as the input parameter isn't empty.
     * @param expertises This is an array of expertise names corresponding to those existing in the database.
     */
    public void addExpertiseCriteria(String[] expertises) {
        int amountOfExpertises = expertises.length;
        int position = 1;
        if(amountOfExpertises != 0) { 
            String expertiseQueryPart = " AND " + amountOfExpertises + 
                " = (SELECT COUNT(*) "
                + "FROM " + dbConstants.YEARSWITHEXPERTISE_TABLE_NAME + " " + dbConstants.YEARSWITHEXPERTISE_QUERY_NAME
                + " WHERE " + dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_ID 
                + " = " + dbConstants.YEARSWITHEXPERTISE_QUERY_NAME + "." + dbConstants.YEARSWITHEXPERTISE_COLUMN_APPLICATIONID
                + " AND " + dbConstants.YEARSWITHEXPERTISE_QUERY_NAME + "." + dbConstants.YEARSWITHEXPERTISE_COLUMN_EXPERTISE + " IN (";
            for(String expertise : expertises) {
                expertiseQueryPart += "'" + expertise + "'";
                expertiseQueryPart = addQueryComma(position, amountOfExpertises, expertiseQueryPart);
                position++;
            }
            expertiseQueryPart += "))";
            query += expertiseQueryPart;
        }
    }   
    
    /**
     * Concatenates commas into a query to get correct SQL-syntax for the <code>QueryBuilder</code>.
     * This is called when an array of search criteria needs to be comma separated to work.
     * @param position The current position of the array being handled.
     * @param lastPosition The last position of the array being handled.
     * @param queryC The query being concatenated with commas.
     * @return 
     */
    private String addQueryComma(int position, int lastPosition, String queryC) {
        if(position < lastPosition) {
            return queryC + ",";
        } else {
            return queryC;
        }
    }
    
    /**
     * Adds the availability search criteria to the <code>QueryBuilder</code> if the time period given isn't null.
     * @param timeperiod This is an object which consists of a start date and an end date which will be compared to the database to find acceptable application id's.
     */
    public void addAvailabilityCriteria(TimePeriodDTO timeperiod) {
        String availabilityPeriodQueryPart = " AND " + dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_ID + " IN "
                + "(SELECT " + dbConstants.TIMEPERIOD_QUERY_NAME + "." + dbConstants.TIMEPERIOD_COLUMN_APPLICATIONID
                + " FROM " + dbConstants.TIMEPERIOD_TABLE_NAME + " " + dbConstants.TIMEPERIOD_QUERY_NAME
                + " WHERE ";
        if(timeperiod != null) {            
            availabilityPeriodQueryPart += "'" + formatDateToQuery(timeperiod.getStartdate()) + "' " + 
                "< " + dbConstants.TIMEPERIOD_QUERY_NAME + "." + dbConstants.TIMEPERIOD_COLUMN_ENDDATE
                + " AND "
                + "'" + formatDateToQuery(timeperiod.getEnddate()) + "' "
                + "BETWEEN " + dbConstants.TIMEPERIOD_QUERY_NAME + "." + dbConstants.TIMEPERIOD_COLUMN_STARTDATE
                + " AND "+ dbConstants.TIMEPERIOD_QUERY_NAME + "." + dbConstants.TIMEPERIOD_COLUMN_ENDDATE + ")";
            query += availabilityPeriodQueryPart;
        }
    }
    
    /**
     * @return The <code>QueryBuilder</code>'s query.
     */
    public String getQuery() {
        return query;
    }
}
