package se.kth.id1212.globalapps.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class QueryBuilder {
    private String query = "";
    
    public QueryBuilder(String queryType) {
        if(queryType.equalsIgnoreCase("application")) {
            query += "SELECT * FROM ApplicationEntity application WHERE ";
        }
    }
    
    public void addNameCriteria(String firstName, String lastName) {  
        String nameQueryPart = "application.OWNER IN "
                    + "(SELECT userEntity.USERNAME "
                    + "FROM UserEntity userEntity "
                    + "WHERE userEntity.FIRSTNAME LIKE ";
        if(lastName == null && firstName == null) {
            nameQueryPart += "'%' AND userEntity.LASTNAME LIKE '%')";
        } else {    
            if(firstName == null) {
                nameQueryPart += "'%' ";
            } else {
                nameQueryPart += "'" + firstName + "' ";
            }
            nameQueryPart += "AND userEntity.LASTNAME LIKE ";
            if(lastName == null) {
                nameQueryPart += "'%')";
            } else {
                nameQueryPart += "'" + lastName + "')";
            }
        }
        query += nameQueryPart;
    }
    
    public void addRegistrationDateCriteria(Date registrationDate) {
        String dateQueryPart = " AND application.OWNER IN "
                + "(SELECT us.USERNAME "
                + "FROM UserEntity us "
                + "WHERE us.REGISTRATIONDATE = ";
        if(registrationDate != null) {
            dateQueryPart +=  "'" + formatDateToQuery(registrationDate) + "')";
            query += dateQueryPart;
        }
    }
    
    private String formatDateToQuery(Date date) {
        SimpleDateFormat dayMonthYearFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dayMonthYearFormat.format(date);
    }
    
    public void addExpertiseCriteria(String[] expertises) {
        int amountOfExpertises = expertises.length;
        int position = 1;
        if(amountOfExpertises != 0) { 
            String expertiseQueryPart = " AND " + amountOfExpertises + 
                " = (SELECT COUNT(*) FROM YearsWithExpertise exp "
                + "WHERE application.APPLICATIONID = exp.applicationid "
                + "AND exp.expertise IN (";
            for(String expertise : expertises) {
                expertiseQueryPart += "'" + expertise + "'";
                expertiseQueryPart = addQueryComma(position, amountOfExpertises, expertiseQueryPart);
                position++;
            }
            expertiseQueryPart += "))";
            query += expertiseQueryPart;
        }
    }   
    
    private String addQueryComma(int position, int lastPosition, String queryC) {
        if(position < lastPosition) {
            return queryC + ",";
        } else {
            return queryC;
        }
    }
    
    public void addAvailabilityCriteria(TimePeriodDTO timeperiod) {
        String availabilityPeriodQueryPart = " AND application.APPLICATIONID IN "
                + "(SELECT avail.applicationid "
                + "FROM periodofavailability avail "
                + "WHERE ";
        if(timeperiod != null) {            
            availabilityPeriodQueryPart += "'" + formatDateToQuery(timeperiod.getStartdate()) + "' " + 
                "< avail.enddate AND " +
                "'" + formatDateToQuery(timeperiod.getEnddate()) + "' " +
                "BETWEEN avail.startdate AND avail.enddate)";
            query += availabilityPeriodQueryPart;
        }
    }
   
    public String getQuery() {
        return query;
    }
}
