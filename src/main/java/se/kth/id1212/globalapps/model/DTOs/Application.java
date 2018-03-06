package se.kth.id1212.globalapps.model.DTOs;

import java.util.Collection;
import java.util.Date;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;
import se.kth.id1212.globalapps.model.ApplicationEntity;
import se.kth.id1212.globalapps.model.TimePeriod;
import se.kth.id1212.globalapps.model.YearsWithExpertise;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
public class Application implements se.kth.id1212.globalapps.dtos.ApplicationDTO {

    private final boolean status;
    private final long id;
    private final YearsWithExpertise[] competences;
    private final TimePeriod[] periodsOfAvailability;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;
    private final int versionNumber;
    
    public Application(ApplicationEntity application, Collection<TimePeriod> timePeriods, Collection<YearsWithExpertise> yearsWithExpertise) {
        this.username = application.getUserEntity().getUsername();
        this.status = application.isStatus();
        this.firstName = application.getUserEntity().getFirstName();
        this.lastName = application.getUserEntity().getLastName();
        this.versionNumber = application.getVersionNumber();
        this.dateOfBirth = application.getUserEntity().getDateOfBirth();
        this.competences = yearsWithExpertise.toArray(new YearsWithExpertise[0]);
        this.periodsOfAvailability = timePeriods.toArray(new TimePeriod[0]);
        this.id = application.getApplicationId();
    }
    
    @Override
    public YearsWithExpertiseDTO[] getExpertises() {
        return this.competences;
    }

    @Override
    public TimePeriodDTO[] getAvailabilityPeriods() {
        return this.periodsOfAvailability;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    @Override
    public int getVersionNumber() {
        return this.versionNumber;
    }

    @Override
    public long getApplicationId() {
        return this.id;
    }
    
}
