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
public class AccountTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "NAME", nullable = true)
    private String name;
    
    public AccountTypeEntity() {
    }
    
    public AccountTypeEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AccountTypeEntity)) {
            return false;
        }
        AccountTypeEntity other = (AccountTypeEntity) object;
        return !((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name)));
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.AccountTypeEntity[ name=" + name + " ]";
    }
    
}
