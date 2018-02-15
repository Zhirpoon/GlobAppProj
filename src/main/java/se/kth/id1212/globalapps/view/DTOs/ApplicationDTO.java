package se.kth.id1212.globalapps.view.DTOs;

import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.UserDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class ApplicationDTO implements se.kth.id1212.globalapps.dtos.ApplicationDTO {

    private YearsWithExpertiseDTO Expertises;
    
    
    @Override
    public YearsWithExpertiseDTO[] getExpertises() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimePeriodDTO[] getAvailabilityPeriods() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDTO getUserDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
