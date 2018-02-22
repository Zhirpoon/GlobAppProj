/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Diaco Uthman
 */
public interface ApplicationDTO {
    public YearsWithExpertiseDTO[] getExpertises();
    public TimePeriodDTO[] getAvailabilityPeriods();
    public String getUsername();
    // status is either accepted or rejected
    public boolean getStatus();
    public String getFirstName();
    public String getLastName();
    public Date getDateOfBirth();
    public int getVersionNumber();
}
