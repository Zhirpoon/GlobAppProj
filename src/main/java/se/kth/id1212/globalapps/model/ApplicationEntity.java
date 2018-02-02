package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
@Entity
public class ApplicationEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationId;
    
    @ManyToOne
    @JoinColumn(name= "owner", nullable= false)
    private UserEntity userEntity;
    
    @NotNull
    private TimePeriod[] periodsOfAvailability;
    
    @NotNull
    private YearsWithExpertise[] expertises;
    
    @NotNull
    private boolean status;
    
    @NotNull
    private int versionNumber;
    
    public ApplicationEntity() {
    }
    
    public ApplicationEntity(ApplicationDTO applicationDTO , UserEntity userEntity) {
            this.userEntity = userEntity;
            this.expertises = new YearsWithExpertise[applicationDTO.getExpertises().length];
            this.periodsOfAvailability = new TimePeriod[applicationDTO.getAvailabilityPeriods().length];
            for(int i=0;i<this.expertises.length;i++) {
                this.expertises[i] = new YearsWithExpertise(applicationDTO.getExpertises()[i]);
            }
            for(int i=0;i<this.periodsOfAvailability.length;i++) {
                this.periodsOfAvailability[i] = new TimePeriod(applicationDTO.getAvailabilityPeriods()[i]);
            }
            this.status = applicationDTO.getStatus();
            this.versionNumber = 1;
    }
    
    public Long getApplicationId() {
        return applicationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ApplicationEntity)) {
            return false;
        }
        ApplicationEntity other = (ApplicationEntity) object;
        return !((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId)));
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public TimePeriod[] getPeriodsOfAvailability() {
        return periodsOfAvailability;
    }

    public YearsWithExpertise[] getExpertises() {
        return expertises;
    }

    public boolean isStatus() {
        return status;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.ApplicationEntity[ id=" + applicationId + " ]";
    }
    
}
