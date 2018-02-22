package se.kth.id1212.globalapps.model;

import java.util.Date;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class TimePeriod implements se.kth.id1212.globalapps.dtos.TimePeriodDTO {
    private final Date startDate;
    private final Date endDate;
    
    /**
     * Constructor for the <code>TimePeriod</code>, this is used to send data from the model to the view.
     * @param startDate The <code>TimePeriod</code>'s start date.
     * @param endDate  The <code>TimePeriod</code>'s end date.
     */
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