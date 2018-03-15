package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
@Entity
public class ExpertiseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "EXPERTISENAME", nullable= true)
    @Size(min = 1, max = 255)
    private String expertiseName;
    
    /**
     * Null constructor of <code>ExpertiseEntity</code>
     */
    public ExpertiseEntity() {
    }
    
    /**
     * Constructor of <code>ExpertiseEntity</code> with specified expertise name, used to store data in database.
     * @param expertiseName The <code>ExpertiseEntity</code>'s name.
     */
    public ExpertiseEntity(String expertiseName) {
        this.expertiseName = expertiseName;
    }

    /**
     * @return The <code>ExpertiseEntity</code>'s name.
     */
    public String getExpertiseName() {
        return expertiseName;
    }

    /**
     * @param expertiseName Sets the <code>ExpertiseEntity</code>'s name.
     */
    public void setExpertiseName(String expertiseName) {
        this.expertiseName = expertiseName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expertiseName != null ? expertiseName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ExpertiseEntity)) {
            return false;
        }
        ExpertiseEntity other = (ExpertiseEntity) object;
        return !((this.expertiseName == null && other.expertiseName != null) || (this.expertiseName != null && !this.expertiseName.equals(other.expertiseName)));
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.ExpertiseEntity[ ExpertiseEntity name=" + expertiseName + " ]";
    }
    
}
