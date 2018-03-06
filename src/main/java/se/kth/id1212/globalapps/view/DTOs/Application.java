package se.kth.id1212.globalapps.view.DTOs;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import se.kth.id1212.globalapps.model.YearsWithExpertise;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 * Application is a DTO to be sent down to the model without model needing to know anything about the view.
 */
public class Application implements se.kth.id1212.globalapps.dtos.ApplicationDTO {

    private Collection <YearsWithExpertiseDTO> expertises = new HashSet<>();
    private Collection <TimePeriodDTO> availabilityPeriods = new HashSet<>();
    private String username;
    
    /**
     * Constructor of <code>Application</code> with a specified username of the owner.
     * @param username The <code>Application</code>'s owners username
     */
    public Application(String username){
        this.username = username;
    }
    
    /**
     *  Adds the user's periods of availability to the <code>Application</code>, it adds it to a list of availability periods.
     * @param availabilityPeriod The <code>Application</code>'s owner's periods of availability.
     */
    public void addAvailabilityPeriod(TimePeriodDTO availabilityPeriod){
        availabilityPeriods.add(availabilityPeriod);
    }
    
    /**
     * Adds the user's expertise to the <code>Application</code>, it adds it to a list of expertises.
     * @param expertise The <code>Application</code>'s owner's expertise.
     */
    public void addExpertises(YearsWithExpertiseDTO expertise){
        expertises.add(expertise);
    }
    
    @Override
    public YearsWithExpertiseDTO[] getExpertises() {
        return (YearsWithExpertiseDTO[]) expertises.toArray(new YearsWithExpertiseDTO[expertises.size()]);
    }

    @Override
    public TimePeriodDTO[] getAvailabilityPeriods() {
        return (TimePeriodDTO[]) availabilityPeriods.toArray(new TimePeriodDTO[availabilityPeriods.size()]);
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean getStatus() {
        return false;
    }

    @Override
    public String getFirstName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLastName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Date getDateOfBirth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getVersionNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getApplicationId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
