package se.kth.id1212.globalapps.view;

import java.lang.annotation.Documented;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Date utility class that parses a <code>String</code> into a date
 * and returns the generated <code>java.util.Date</code> object
 * 
 */
public class DateUtil {

    public final String DATEFORMAT = "dd-MM-yyyy";
    private final SimpleDateFormat dateFormat;
    private Date date;

    /**
     * Creates an instance of <code>DateUtil</code>
     */
    public DateUtil() {
        System.out.println("---------------");
        System.out.println("DAteutil created");
        dateFormat = new SimpleDateFormat(DATEFORMAT);
        dateFormat.setLenient(false);
    }

    /**
     * Returns the the <code>String</code> representation of the 
     * instance of the <code>Java.util.Date</code> instance, if there is one,
     * otherwise <code> null</null>
     * 
     * @return  <code>String</code> or <code> null </code>
     */
    public String getDateString() {
        return convertDateToString();
    }

    /**
     * Converts a string representation of a date into a <code>Java.util.Date</code> 
     * object
     * 
     * @param dateString the <code>String</code> to convert
     * @throws se.kth.id1212.globalapps.view.DateUtil.DateObjectParsingError
     */
    public void setDatefromString(String dateString) throws DateObjectParsingError {
        try {
            System.out.println("----------------------------");
            System.out.println("SetDateCalled");
            parseString(dateString);
            System.out.println("Date is set to " + getDateString());
        } catch (ParseException ex) {
            throw new DateObjectParsingError();
        }

    }

    private void parseString(String dateString) throws ParseException {
        this.date = dateFormat.parse(dateString);
    }

    /**
     * Returns the <code>Java.util.Date</code> instance of 
     * this object.
     * @return the <code>Java.util.Date</code> instance
     */
    public Date getDate() {
        return date;
    }

    private String convertDateToString() {
        if (date == null) {
            return null;
        }
        return dateFormat.format(date);
    }
    
    public class DateObjectParsingError extends Exception {
    
        public DateObjectParsingError() {
            super("Date is not of the form " + DATEFORMAT);

        }

    }
}
