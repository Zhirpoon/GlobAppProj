package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import se.kth.id1212.globalapps.dtos.RegistrationDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
@Entity
public class UserEntity implements Serializable {
    
    @Id
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "REGISTRATIONDATE", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registrationDate;
    
    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    @Column(name = "FIRSTNAME", nullable = false)
    private String firstName;
    
    @Column(name = "LASTNAME", nullable = false)
    private String lastName;

    @Column(name = "PASSWORD", nullable = false)
    private int hashedPassword;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNTTYPE", nullable = false)
    private AccountTypeEntity accountType;
    
    public UserEntity() {        
    }
    
    public UserEntity(RegistrationDTO registrationInformation, int hashedPassword) {
        this.username = registrationInformation.getUsername();
        this.firstName = registrationInformation.getFirstname();
        this.lastName = registrationInformation.getLastname();
        this.email = registrationInformation.getMail();
        this.hashedPassword = hashedPassword;
        this.registrationDate = new Date();
        //this.accountType = new AccountTypeEntity("APPLICANT");
    }

    public String getUsername() {
        return username;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getHashedPassword() {
        return hashedPassword;
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
        return !((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)));
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.UserEntity[ id=" + username + " ]";
    }
    
}
