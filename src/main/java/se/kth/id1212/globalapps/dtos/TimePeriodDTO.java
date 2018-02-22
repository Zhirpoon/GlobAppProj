package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Diaco Uthman
 */
public interface TimePeriodDTO {
    
    /**
     * @return The <code>TimePeriodDTO</code>'s start date entry. 
     */
    public Date getStartdate();
    
    /**
     * @return The <code>TimePeriodDTO</code>'s end date entry. 
     */
    public Date getEnddate();
}
