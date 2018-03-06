/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.globalapps.view.DTOs;

import java.util.Date;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;

/**
 * Implementation of se.kth.id1212.globalapps.dtos.ApplicationDTO
 * for updating applications
 */
public class ApplicationUpdate implements ApplicationDTO {

    private final long applicationId;
    private final int versionNumber;
    private final boolean status;

    /**
     * Create a new Application update
     * @param applicationId application's Id
     * @param versionNumber application's version number
     * @param status application's status
     */
    public ApplicationUpdate(long applicationId, int versionNumber, boolean status) {
        this.applicationId = applicationId;
        this.versionNumber = versionNumber;
        this.status = status;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public long getApplicationId() {
        return applicationId;
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

}
