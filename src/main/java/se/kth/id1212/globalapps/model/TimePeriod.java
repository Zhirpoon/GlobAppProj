package se.kth.id1212.globalapps.model;

import java.util.Date;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class TimePeriod implements se.kth.id1212.globalapps.dtos.TimePeriodDTO {
    private final Date startDate;
    private final Date endDate;
    
    public TimePeriod(TimePeriodDTO timePeriodDTO) {
        this.endDate = timePeriodDTO.getEnddate();
        this.startDate = timePeriodDTO.getStartdate();
    }
    
    public TimePeriod(Date startDate, Date endDate) {
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