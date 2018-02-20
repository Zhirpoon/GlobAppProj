package se.kth.id1212.globalapps.view.DTOs;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import se.kth.id1212.globalapps.dtos.ApplicationSearchDTO;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class ApplicationSearch implements ApplicationSearchDTO {

    private TimePeriodDTO availabiltyPeriod;
    private final Collection<String> competences = new HashSet();
    private Date registrationDate;
    private String applicantFirstName;
    private String applicantLastName;
    
    public void addCompentece(String competence){
        competences.add(competence);
    }

    public void setAvailabiltyPeriod(TimePeriodDTO availabiltyPeriod) {
        this.availabiltyPeriod = availabiltyPeriod;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setApplicantFirstName(String applicantFirstName) {
        this.applicantFirstName = applicantFirstName;
    }

    public void setApplicantLastName(String applicantLastName) {
        this.applicantLastName = applicantLastName;
    }
    
    
    
    @Override
    public TimePeriodDTO getTimePeriod() {
        return availabiltyPeriod;
    }

    @Override
    public String[] getCompetences() {
        return competences.toArray(new String[competences.size()]);
    }

    @Override
    public Date getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String getApplicantFirstname() {
        return applicantFirstName;
    }

    @Override
    public String getApplicantLastname() {
        return applicantLastName;
    }
    
}
