package se.kth.id1212.globalapps.model.DTOs;

import java.util.Collection;
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
    private final YearsWithExpertise[] competences;
    private final TimePeriod[] periodsOfAvailability;
    private final String username;
    
    public Application(ApplicationEntity application, Collection<TimePeriod> timePeriods, Collection<YearsWithExpertise> yearsWithExpertise) {
        this.username = application.getUserEntity().getUsername();
        this.status = application.isStatus();
        this.competences = yearsWithExpertise.toArray(new YearsWithExpertise[0]);
        this.periodsOfAvailability = timePeriods.toArray(new TimePeriod[0]);
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
    
}
