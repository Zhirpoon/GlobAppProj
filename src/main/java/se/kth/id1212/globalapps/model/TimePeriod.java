package se.kth.id1212.globalapps.model;

import java.util.Date;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class TimePeriod {
    private final Date startDate;
    private final Date endDate;
    
    public TimePeriod(Date startDate, Date endDate) {
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
