package se.kth.id1212.globalapps.controller;

import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.kth.id1212.globalapps.integration.DBAO;
import se.kth.id1212.globalapps.model.ApplicationEntity;
import se.kth.id1212.globalapps.model.ExpertiseEntity;
import se.kth.id1212.globalapps.model.UserEntity;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;
import se.kth.id1212.globalapps.dtos.ApplicationSearchDTO;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;
import se.kth.id1212.globalapps.model.DTOs.Application;
import se.kth.id1212.globalapps.model.TimePeriod;
import se.kth.id1212.globalapps.model.YearsWithExpertise;
import se.kth.id1212.globalapps.view.DTOs.LoginCredentialsDTO;
import se.kth.id1212.globalapps.view.DTOs.RegistrationDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 * The controller that handles all conversations and conversions between the view and the model layers.
 */
@Stateless
public class Controller {
    
    @EJB
    DBAO dbao;
    
    /**
     * Creates a new <code>UserEntity</code> to be stored on the database.
     * @param registrationDTO The <code>RegistrationDTO</code> to be converted into a UserEntity, contains data such as password, username, etc.
     */
    public void register(RegistrationDTO registrationDTO) {
        dbao.addUser(new UserEntity(registrationDTO, dbao.getAccountTypeApplicant()));
    }

    /**
     * Gets all <code>ExpertiseEntity</code> stored on the database and sends back their names to the view.
     * @return An array of <code>ExpertiseEntity</code> names.
     */
    public String[] getAllExpertises() {
        Collection<ExpertiseEntity> expertiseEntities = dbao.getAllExpertises();
        String[] expertises = new String[expertiseEntities.size()];
        int position = 0;
        for(ExpertiseEntity exp : expertiseEntities) {
            expertises[position] = exp.getExpertiseName();
            position++;
        }
        return expertises;
    }
    
    /**
     * Calls the <code>DBAO</code> to save an <code>ApplicationEntity</code> as well as the corresponding <code>YearsWithExpertise</code>s and the <code>TimePeriod</code>s to the database.
     * All this data is retrieved from the <code>ApplicationDTO</code>. It must also find a <code>UserEntity</code> to bind the application to, this is gotten from the <code>ApplicationDTO</code>'s username.
     * This will rollback the server if any of the save methods fail.
     * @param application The <code>ApplicationDTO</code> where all the data is stored.
     */
    public void saveApplication(ApplicationDTO application) {
        UserEntity user = dbao.findUserByUsername(application.getUsername());
        ApplicationEntity applicationEntity = new ApplicationEntity(user);
        dbao.saveApplication(applicationEntity);
        long applicationId = applicationEntity.getApplicationId();
        saveApplicationExpertises(applicationId, application.getExpertises());
        saveApplicationTimePeriods(applicationId, application.getAvailabilityPeriods());
    }
    
    private void saveApplicationTimePeriods(long applicationId, TimePeriodDTO[] timePeriods) {
        dbao.saveApplicationTimePeriods(applicationId, timePeriods);
    }
    
    private void saveApplicationExpertises(long applicationId, YearsWithExpertiseDTO[] expertises) {
        dbao.saveApplicationExpertises(applicationId, expertises);
    }

    /**
     * Retrieves the <code>AccountType</code> of a specified <code>UserEntity</code> found by searching on the username.
     * @param username The username of a possible <code>UserEntity</code>.
     * @return The name of the <code>UserEntity</code>'s <code>AccountType</code>.
     */
    public String getUsergroup(String username) {
        UserEntity user = dbao.findUserByUsername(username);
        return user.getAccountType().getName();
    }
    
    /**
     * Retrieves a collection of <code>ApplicationEntity</code> based of the criterias defined by <code>ApplicationSearchDTO</code>.
     * For every <code>ApplicationEntity</code> found, the corresponding <code>TimePeriod</code>s and <code>YearsWithExpertise</code>s are retrieved.
     * The collections are then wrapped to an array of <code>ApplicationDTO</code>s to be sent to the view.
     * @param searchCriteria The <code>ApplicationSearchDTO</code> sent from the view, contains such things as expertise names</code, dates, etc.
     * @return An array <code>ApplicationDTO</code> which contains all of the data needed from each <code>ApplicationEntity</code> and its corresponding <code>TimePeriod</code>s and <code>YearsWithExpertise</code>s. 
     */
    public ApplicationDTO[] searchApplications(ApplicationSearchDTO searchCriteria) {
        Collection<ApplicationEntity> retrievedApplications = dbao.searchApplications(searchCriteria);
        ApplicationDTO[] applications = new ApplicationDTO[retrievedApplications.size()];
        int position = 0;
        for (ApplicationEntity appl : retrievedApplications) {
            Collection<YearsWithExpertise> competences = dbao.getYearsWithExpertiseByApplicationId(appl.getApplicationId());
            Collection<TimePeriod> timePeriods = dbao.getPeriodsOfAvailabilityById(appl.getApplicationId());
            applications[position] = new Application(appl, timePeriods, competences);
            position++;
        }
        return applications;
    }

    /**
     * Doesn't need a javadoc really.
     */
    public void johansDummyFunction() {
        System.out.println("-----------------------------------");
        System.out.println("Johans funktion");
        System.out.println("-----------------------------------");
    }
}
