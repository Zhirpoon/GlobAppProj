package se.kth.id1212.globalapps.model;

import java.util.Date;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class QueryBuilder {
    private String query = "";
    
    public QueryBuilder(String queryType) {
        if(queryType.equalsIgnoreCase("application")) {
            query += "SELECT application FROM ApplicationEntity application WHERE ";
        }
    }
    
    public void addNameCriteria(String firstName, String lastName) {  
        if(!(firstName.equalsIgnoreCase(lastName) && firstName.equalsIgnoreCase(""))) {
            String nameQueryPart = "application.OWNER LIKE (SELECT USERNAME FROM UserEntity WHERE FIRSTNAME LIKE ";
            if(firstName.equalsIgnoreCase("")) {
                nameQueryPart += "'%' ";
            } else {
                nameQueryPart += "'" + firstName + "' ";
            }
            nameQueryPart += "AND LASTNAME LIKE ";
            if(lastName.equalsIgnoreCase("")) {
                nameQueryPart += "'%' ";
            } else {
                nameQueryPart += "'" + lastName + "')";
            }
            query += nameQueryPart;
        }
    }
    
    //Fix format of date to maybe SQL DATE and add default value if left blank
    public void addRegistrationDateCriteria(Date registrationDate) {
        String dateQueryPart = " AND OWNER LIKE (SELECT USERNAME FROM UserEntity us WHERE us.REGISTRATIONDATE >= ";
        /*
        if(!registrationDate.compareTo(defaultDate)) {
            dateQueryPart += "'" + registrationDate.toString() + "')";
        }
        */
    }
    
    public String getQuery() {
        return query;
    }
}
