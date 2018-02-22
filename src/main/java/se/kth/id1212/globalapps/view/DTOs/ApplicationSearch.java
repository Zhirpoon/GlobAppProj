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
 * <code>ApplicationSearch</code> is used to pack data to be sent down to the model.
 */
public class ApplicationSearch implements ApplicationSearchDTO {

    private TimePeriodDTO availabiltyPeriod;
    private final Collection<String> competences = new HashSet();
    private Date registrationDate;
    private String applicantFirstName;
    private String applicantLastName;
    
    /**
     * Adds an <code>ExpertiseEntity</code>'s name to a list of competences required.
     * @param competence An <code>ExpertiseEntity</code>'s name. 
     */
    public void addCompentece(String competence){
        competences.add(competence);
    }

    /**
     * Sets a required period of availability in the <code>ApplicationSearch</code>.
     * @param availabiltyPeriod A <code>TimePeriod</code> to be required.
     */
    public void setAvailabiltyPeriod(TimePeriodDTO availabiltyPeriod) {
        this.availabiltyPeriod = availabiltyPeriod;
    }

    /**
     * Sets a registration date to be required in the <code>ApplicationSearch</code>. 
     * @param registrationDate The registration date to be required of users to have.
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Sets a first name to be required in the <code>ApplicationSearch</code>.
     * @param applicantFirstName The first name to be required of users to have.
     */
    public void setApplicantFirstName(String applicantFirstName) {
        this.applicantFirstName = applicantFirstName;
    }

    /**
     * Sets a last name to be required in the <code>ApplicationSearch</code>.
     * @param applicantLastName The last name to be required of users to have.
     */
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
