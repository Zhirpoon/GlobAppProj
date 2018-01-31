package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
@Entity
public class ApplicationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationId;
    
    @NotNull
    @ManyToOne
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
    
    public ApplicationEntity(ApplicationDTO application) {
            
    }
    
    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long id) {
        this.applicationId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationId != null ? applicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationEntity)) {
            return false;
        }
        ApplicationEntity other = (ApplicationEntity) object;
        return !((this.applicationId == null && other.applicationId != null) || (this.applicationId != null && !this.applicationId.equals(other.applicationId)));
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.ApplicationEntity[ id=" + applicationId + " ]";
    }
    
}
