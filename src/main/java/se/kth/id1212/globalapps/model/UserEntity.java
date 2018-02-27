package se.kth.id1212.globalapps.model;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;
import org.apache.commons.codec.binary.Hex;
import se.kth.id1212.globalapps.dtos.RegistrationDTO;

/**
 *
 * @author Johan Rosengren <jrosengr@kth.se>
 */
@Entity
public class UserEntity implements Serializable {
    
    @Id
    @Column(name = "USERNAME", nullable = true)
    @Size(min = 5, max = 255)
    private String username;

    @Column(name = "REGISTRATIONDATE", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date registrationDate;
    
    @Column(name = "EMAIL", nullable = true)
    @Size(min = 1, max = 255)
    private String email;
    
    @Column(name = "FIRSTNAME", nullable = true)
    @Size(min = 1, max = 255)
    private String firstName;
    
    @Column(name = "LASTNAME", nullable = true)
    @Size(min = 1, max = 255)
    private String lastName;

    @Column(name = "PASSWORD", nullable = true)
    @Size(min = 1, max = 255)
    private String hashedPassword;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "ACCOUNTTYPE")
    private AccountTypeEntity accountType;
    
    @Column(name="SSN", nullable = true)
    @Size(min = 13, max = 13)
    private String ssn;
    
    @Column(name="DATEOFBIRTH", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    
    /**
     * Null constructor for USerEntity
     */
    public UserEntity() {        
    }
    
    /**
     * Constructor for the <code>UserEntity</code> which is used to store and retrieve data in the database. It also hashes the password it is not stored in plain text.
     * @param registrationInformation The <code>UserEntity</code>'s registration information, such as username, first name, date of birth, etc.
     * @param accountType The <code>UserEntity</code>'s <code>AccountTypeEntity</code>. 
     */
    public UserEntity(RegistrationDTO registrationInformation, AccountTypeEntity accountType) {
        this.username = registrationInformation.getUsername();
        this.firstName = registrationInformation.getFirstname();
        this.lastName = registrationInformation.getLastname();
        this.email = registrationInformation.getMail();
        this.dateOfBirth = registrationInformation.getDateOfBirth();
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            String toHash = registrationInformation.getPassword();
            byte[] hash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
            this.hashedPassword = new String(Hex.encodeHex(hash));
        } catch (NoSuchAlgorithmException ex) {
            this.hashedPassword="couldNotHash";
            Logger.getLogger(UserEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.registrationDate = new Date();
        this.accountType = accountType;
    }

    /**
     * @return The <code>UserEntity</code>'s social security number. 
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @return The <code>UserEntity</code>'s date of birth.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return The <code>UserEntity</code>'s username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return The <code>UserEntity</code>'s date of creation..
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @return The <code>UserEntity</code>'s email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return The <code>UserEntity</code>'s first name.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @return The <code>UserEntity</code>'s last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return The <code>UserEntity</code>'s hashed password.
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * @return The <code>UserEntity</code>'s <code>AccountTypeEntity</code>, is either Recruiter or Applicant.
     */
    public AccountTypeEntity getAccountType() {
        return accountType;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        return !((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username)));
    }

    @Override
    public String toString() {
        return "se.kth.id1212.globalapps.model.UserEntity[ UserEntity username= " + username + " ]";
    }
    
}
