/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.id1212.globalapps.dtos;

/**
 *
 * @author Diaco Uthman
 */
public interface ApplicationDTO {
    public YearsWithExpertiseDTO[] getExpertises();
    public TimePeriodDTO[] getAvailabilityPeriods();
    public String getUsername();
    public UserDTO getUserDTO();
    // status is either accepted or rejected
    public boolean getStatus();
}
