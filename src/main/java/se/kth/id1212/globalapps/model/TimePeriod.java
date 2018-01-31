package se.kth.id1212.globalapps.model;

import java.util.Date;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class TimePeriod {
    private final Date startDate;
    private final Date endDate;

    public TimePeriod(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
}
