package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    
    /**
     * The null constructor for ApplicationEntity.
     */
    public ApplicationEntity() {
    }
    
    /**
     * The constructor for <code>ApplicationEntity</code>, it is the entity in which applications are saved to the database.
     * It automatically initiates the version number to 1 and sets the accepted status to false.
     * UserEntity in this case is a foreign key in the ApplicationEntity.
     * @param userEntity The <code>ApplicationEntity</code>'s owner.
     */
    public ApplicationEntity(UserEntity userEntity) {
            this.userEntity = userEntity;
            this.status = false;
            this.versionNumber = 1;
    }
    
    /**
     * @return The <code>ApplicationEntity</code>'s generated ID.
     */
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

    /**
     * @return The <code>ApplicationEntity</code>'s owner.
     */
    public UserEntity getUserEntity() {
        return userEntity;
    }

    /**
     * @return Says if the <code>ApplicationEntity</code> is accepted or not.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @return The <code>ApplicationEntity</code>'s version number.
     */
    public int getVersionNumber() {
        return versionNumber;
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.ApplicationEntity[ ApplicationEntity id= " + applicationId + " ]";
    }
    
}
