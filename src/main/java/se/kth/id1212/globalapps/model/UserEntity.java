package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import se.kth.id1212.globalapps.integration.DBAO;

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
    private String hashedPassword;
    
    @ManyToOne
    @JoinColumn(name = "ACCOUNTTYPE", nullable = false)
    private AccountTypeEntity accountType;
    
    public UserEntity() {        
    }
    
    public UserEntity(RegistrationDTO registrationInformation, int hashedPassword, AccountTypeEntity accountType) {
        this.username = registrationInformation.getUsername();
        this.firstName = registrationInformation.getFirstname();
        this.lastName = registrationInformation.getLastname();
        this.email = registrationInformation.getMail();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            String toHash = registrationInformation.getPassword();
            byte[] hash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
            this.hashedPassword = new String(hash);
        } catch (NoSuchAlgorithmException ex) {
            this.hashedPassword="couldNotHash";
            Logger.getLogger(UserEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.hashedPassword = registrationInformation.getPassword();
        this.registrationDate = new Date();
        this.accountType = accountType;
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

    public String getHashedPassword() {
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
