package se.kth.id1212.globalapps.model.Constants;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class DbConstants {
    public final String ACCOUNTTYPEENTITY_TABLE_NAME = "AccountTypeEntity";
    public final String ACCOUNTTYPEENTITY_NAME = "NAME";
    public final String ACCOUNTTYPEENTITY_QUERY_NAME = "accountType";
    
    public final String APPLICATIONENTITY_TABLE_NAME = "ApplicationEntity";
    public final String APPLICATIONENTITY_ID = "APPLICATIONID";
    public final String APPLICATIONENTITY_OWNER = "OWNER";
    public final String APPLICATIONENTITY_STATUS = "STATUS";
    public final String APPLICATIONENTITY_VERSION = "VERSION";
    public final String APPLICATIONENTITY_QUERY_NAME = "application";
    
    public final String EXPERTISEENTITY_TABLE_NAME = "ExpertiseEntity";
    public final String EXPERTISEENTITY_COLUMN_EXPERTISENAME = "EXPERTISENAME";
    public final String EXPERTISEENTITY_QUERY_NAME = "expertise";
    
    public final String TIMEPERIOD_TABLE_NAME = "periodofavailability";
    public final String TIMEPERIOD_COLUMN_APPLICATIONID = "applicationid";
    public final String TIMEPERIOD_COLUMN_STARTDATE = "startdate";
    public final String TIMEPERIOD_COLUMN_ENDDATE = "enddate";
    public final String TIMEPERIOD_QUERY_NAME = "period";
    
    public final String YEARSWITHEXPERTISE_TABLE_NAME = "YearsWithExpertise";
    public final String YEARSWITHEXPERTISE_COLUMN_EXPERTISE = "expertise";
    public final String YEARSWITHEXPERTISE_COLUMN_APPLICATIONID = "applicationid";
    public final String YEARSWITHEXPERTISE_COLUMN_YEARS_OF_EXPERIENCE = "yearsofexperience";
    public final String YEARSWITHEXPERTISE_QUERY_NAME = "yearsWithExpertise";
    
    public final String USERENTITY_TABLE_NAME = "UserEntity";
    public final String USERENTITY_COLUMN_USERNAME = "USERNAME";
    public final String USERENTITY_COLUMN_REGISTRATIONDATE = "REGISTRATIONDATE";
    public final String USERENTITY_COLUMN_EMAIL = "EMAIL";
    public final String USERENTITY_COLUMN_FIRSTNAME = "FIRSTNAME";
    public final String USERENTITY_COLUMN_LASTNAME = "LASTNAME";
    public final String USERENTITY_COLUMN_PASSWORD = "PASSWORD";
    public final String USERENTITY_COLUMN_ACCOUNTTYPE = "ACCOUNTTYPE";
    public final String USERENTITY_COLUMN_SSN = "SSN";
    public final String USERENTITY_COLUMN_DATE_OF_BIRTH = "DATEOFBIRTH";
    public final String USERENTITY_QUERY_NAME = "userEntity";
}
