package se.kth.id1212.globalapps.view.DTOs;

import java.util.Date;


/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class TimePeriodDTO implements se.kth.id1212.globalapps.dtos.TimePeriodDTO {
    Date startDate;
    Date endDate;
    
    /**
     * Constructor of <code>TimePeriodDTO</code> with specified start date and end date.
     * @param startDate The start date of the <code>TimePeriodDTO</code>.
     * @param endDate The end date of the <code>TimePeriodDTO</code>. 
     */
    public TimePeriodDTO(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    @Override
    public Date getStartdate() {
        return startDate;
    }

    @Override
    public Date getEnddate() {
        return endDate;
    }
    
}
