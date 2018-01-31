package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @Column(name = "EXPERTISENAME")
    @Size(min = 1, max = 255)
    private String expertiseName;
    
    public ExpertiseEntity() {
    }

    public String getExpertiseName() {
        return expertiseName;
    }

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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExpertiseEntity)) {
            return false;
        }
        ExpertiseEntity other = (ExpertiseEntity) object;
        return !((this.expertiseName == null && other.expertiseName != null) || (this.expertiseName != null && !this.expertiseName.equals(other.expertiseName)));
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.ExpertiseEntity[ expertise name=" + expertiseName + " ]";
    }
    
}
