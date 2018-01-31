package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import se.kth.id1212.globalapps.dtos.RegistrationDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
@Entity
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USERNAME")
    private String username;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "REGISTRATIONDATE")
    private Date registrationDate;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FIRSTNAME")
    private String firstName;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LASTNAME")
    private String lastName;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "PASSWORD")
    private int hashedPassword;
    
    public UserEntity() {        
    }
    
    public UserEntity(RegistrationDTO registrationInformation, int hashedPassword) {
        this.username = registrationInformation.getUsername();
        this.firstName = registrationInformation.getFirstname();
        this.lastName = registrationInformation.getLastname();
        this.email = registrationInformation.getMail();
        this.hashedPassword = hashedPassword;
        this.registrationDate = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.UserEntity[ id=" + username + " ]";
    }
    
}
