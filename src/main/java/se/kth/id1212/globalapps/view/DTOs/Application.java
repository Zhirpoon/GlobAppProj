package se.kth.id1212.globalapps.view.DTOs;

import java.util.Collection;
import java.util.HashSet;
import se.kth.id1212.globalapps.model.YearsWithExpertise;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class Application implements se.kth.id1212.globalapps.dtos.ApplicationDTO {

    private Collection <YearsWithExpertiseDTO> expertises = new HashSet<>();
    private Collection <TimePeriodDTO> availabilityPeriods = new HashSet<>();
    private String username;
    
    public Application(String username){
        this.username = username;
    }
    
    public void addAvailabilityPeriod(TimePeriodDTO availabilityPeriod){
        availabilityPeriods.add(availabilityPeriod);
    }
    
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
    
}
