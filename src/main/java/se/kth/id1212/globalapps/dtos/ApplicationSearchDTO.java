package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Diaco Uthman
 */
public interface ApplicationSearchDTO {
    
    /**
     * @return The <code>ApplicationSearchDTO</code>'s array of <code>TimePeriod</code>s search criteria. 
     */
    public TimePeriodDTO getTimePeriod();
    
    /**
     * @return The <code>ApplicationSearchDTO</code>'s array of expertises search criteria.
     */
    public String[] getCompetences();
    
    /**
     * @return The <code>ApplicationSearchDTO</code>'s registration date search criteria.
     */
    public Date getRegistrationDate();
    
    /**
     * @return The <code>ApplicationSearchDTO</code>'s first name search criteria. 
     */
    public String getApplicantFirstname();
    
    /**
     * @return The <code>ApplicationSearchDTO</code>'s last name search criteria.
     */
    public String getApplicantLastname();
}
