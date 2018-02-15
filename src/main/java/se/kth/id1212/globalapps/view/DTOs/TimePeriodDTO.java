package se.kth.id1212.globalapps.view.DTOs;

import java.util.Date;


/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class TimePeriodDTO implements se.kth.id1212.globalapps.dtos.TimePeriodDTO {
    Date startDate;
    Date endDate;
    
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
