package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;

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
    @JoinColumn(name= "OWNER", nullable= true)
    private UserEntity userEntity;
  
    @Column(name = "STATUS", nullable = true)
    private boolean status;
    
    @Column(name = "VERSION", nullable = true)
    private int versionNumber;
    
    public ApplicationEntity() {
    }
    
    public ApplicationEntity(ApplicationDTO applicationDTO , UserEntity userEntity) {
            this.userEntity = userEntity;
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

    public boolean isStatus() {
        return status;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.ApplicationEntity[ applicationId=" + applicationId + " ]";
    }
    
}
