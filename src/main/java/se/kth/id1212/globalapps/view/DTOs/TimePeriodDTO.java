package se.kth.id1212.globalapps.view.DTOs;

import java.util.Date;


/**
 * Represents a time period based on two dates
 * 
 */
public class TimePeriodDTO implements se.kth.id1212.globalapps.dtos.TimePeriodDTO {
    Date startDate;
    Date endDate;
    
    /**
     * Constructor of <code>TimePeriodDTO</code> with specified start date and end date.
     * The arguments can not be <code>null</code> and <code>startDate</code> must 
     * preceed or refer to the same date as <code>endDate</code>
     * @param startDate The start date of the <code>TimePeriodDTO</code>.
     * @param endDate The end date of the <code>TimePeriodDTO</code>. 
     * @throws TimePeriodDTOException 
     */
    public TimePeriodDTO(Date startDate, Date endDate) throws TimePeriodDTOException{
        this.startDate = startDate;
        this.endDate = endDate;
        
        validArguments();
        
        
        
        
    }
    
    
    
    @Override
    public Date getStartdate() {
        return startDate;
    }

    @Override
    public Date getEnddate() {
        return endDate;
    }
    
    private boolean nullArguements(){
        return (startDate == null) && (endDate == null);
    }
    
    private void validArguments() throws TimePeriodDTOException{
        if(nullArguements()) throw new TimePeriodDTOException("argument can not be null");
        if(!startDateBeforeEndate()) throw new TimePeriodDTOException("startDate must preceed or be the same as endDate");
    }
    
    private boolean startDateBeforeEndate(){
        return this.startDate.compareTo(this.endDate) <= 0;
    }
    
    public class TimePeriodDTOException extends Exception{

        public TimePeriodDTOException(String message){
            super(message);
        }
        
        public TimePeriodDTOException() {
            super("invalid date arguements");
        }
        
    }
    
}
