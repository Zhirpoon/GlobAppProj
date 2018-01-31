package se.kth.id1212.globalapps.dtos;

import java.util.Date;

/**
 *
 * @author Diaco Uthman
 */
public interface ApplicationSearchDTO {
    public TimePeriodDTO getTimePeriod();
    public String[] getCompetences();
    public Date getRegistrationDate();
    public String getApplicantFirstname();
    public String getApplicantLastname();
}
