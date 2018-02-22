package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
    
    /**
     * The null constructor for AccountTypeEntity
     */
    public AccountTypeEntity() {
    }
    
    /**
     * The constructor for <code>AccountTypeEntity</code> that has a specified name.
     * @param name The newly created <code>AccountTypeEntity</code>'s name, which is also the primary key.
     */
    public AccountTypeEntity(String name) {
        this.name = name;
    }

    /**
     * @return The <code>AccountTypeEntity</code>'s name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The <code>AccountTypeEntity</code>'s new account type.
     */
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
        return "se.kth.id1212.globalapps.model.AccountTypeEntity[ AccountTypeEntity name= " + name + " ]";
    }
    
}
