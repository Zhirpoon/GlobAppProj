package se.kth.id1212.globalapps.model;

import java.util.Date;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class TimePeriod {
    private final Date startDate;
    private final Date endDate;
    
    public TimePeriod(TimePeriodDTO timePeriodDTO) {
        this.endDate = timePeriodDTO.getEnddate();
        this.startDate = timePeriodDTO.getStartdate();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
