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

    /**
     * @return The <code>ApplicationDTO</code>'s array of <code>YearsWithExpertiseDTO</code>s.
     */
    public YearsWithExpertiseDTO[] getExpertises();

    /**
     * @return The <code>ApplicationDTO</code>'s array of <code>TimePeriodDTO</code>s.
     */
    public TimePeriodDTO[] getAvailabilityPeriods();

    /**
     * @return The <code>ApplicationDTO</code>'s owner's username.
     */
    public String getUsername();
    /**
     * @return The <code>ApplicationDTO</code>'s status, this is either accepted or rejected.
     */
    public boolean getStatus();

    /**
     * @return The <code>ApplicationDTO</code>'s owner's first name.
     */
    public String getFirstName();

    /**
     * @return The <code>ApplicationDTO</code>'s owner's last name.
     */
    public String getLastName();

    /**
     * @return <code>ApplicationDTO</code>'s owner's date of birth.
     */
    public Date getDateOfBirth();

    /**
     * @return The <code>ApplicationDTO</code>'s version number.
     */
    public int getVersionNumber();
}
