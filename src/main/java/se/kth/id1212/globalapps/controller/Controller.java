package se.kth.id1212.globalapps.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.logging.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import org.eclipse.persistence.exceptions.DatabaseException;
import se.kth.id1212.globalapps.common.exception.CodedException;
import se.kth.id1212.globalapps.common.exception.ExceptionEnumerator;
import se.kth.id1212.globalapps.integration.DBAO;
import se.kth.id1212.globalapps.model.ApplicationEntity;
import se.kth.id1212.globalapps.model.ExpertiseEntity;
import se.kth.id1212.globalapps.model.UserEntity;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;
import se.kth.id1212.globalapps.dtos.ApplicationSearchDTO;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;
import se.kth.id1212.globalapps.model.Constants.ErrorConstants;
import se.kth.id1212.globalapps.model.DTOs.Application;
import se.kth.id1212.globalapps.model.TimePeriod;
import se.kth.id1212.globalapps.model.YearsWithExpertise;
import se.kth.id1212.globalapps.view.DTOs.RegistrationDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 * The controller that handles all conversations and conversions between the view and the model layers.
 */
@Stateless
//There should be no pre-existing transaction when this bean is called, 
//and a new transation must be started and a new transation must be started
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class Controller {
    
    @EJB
    DBAO dbao;
    
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    
    /**
     * Creates a new <code>UserEntity</code> to be stored on the database.
     * @param registrationDTO The <code>RegistrationDTO</code> to be converted into a UserEntity, contains data such as password, username, etc.
     * @throws se.kth.id1212.globalapps.common.exception.CodedException Has an enumerator which tells view how to handle the exception.
     */
    public void register(RegistrationDTO registrationDTO) throws CodedException {
        try {
            dbao.addUser(new UserEntity(registrationDTO, dbao.getAccountTypeApplicant()));
        } catch (SQLIntegrityConstraintViolationException constraintException) {
            LOGGER.log(Level.SEVERE, constraintException.toString());
            throw constraintViolation();
        } catch (Exception registrationException) {
            LOGGER.log(Level.SEVERE, registrationException.toString());
            throw createCodedException(registrationException);
        }
    }
    
    public ApplicationDTO getApplicationById(long applicationId) throws CodedException {
        ApplicationEntity applicationEntity;
        try {
            applicationEntity = dbao.getApplicationEntityById(applicationId);
        } catch (Exception unexpeException) {
            LOGGER.log(Level.SEVERE, unexpeException.toString());
            throw createCodedException(unexpeException);
        }
        if(applicationEntity != null) {
            Collection<YearsWithExpertise> competences = dbao.getYearsWithExpertiseByApplicationId(applicationEntity.getApplicationId());
            Collection<TimePeriod> timePeriods = dbao.getPeriodsOfAvailabilityById(applicationEntity.getApplicationId());
            Application application = new Application(applicationEntity, timePeriods, competences);
            return application;
        } else {
            throw noResultsFound();
        }
    }

    /**
     * Gets all <code>ExpertiseEntity</code> stored on the database and sends back their names to the view.
     * @return An array of <code>ExpertiseEntity</code> names.
     * @throws se.kth.id1212.globalapps.common.exception.CodedException Has an enumerator which tells view how to handle the exception.
     */
    public String[] getAllExpertises() throws CodedException {
        Collection<ExpertiseEntity> expertiseEntities;
        try {
            expertiseEntities = dbao.getAllExpertises();
        } catch (Exception unexpectedException) {
            LOGGER.log(Level.SEVERE, unexpectedException.toString());
            throw createCodedException(unexpectedException);
        }    
        if(expertiseEntities == null) {
            throw noResultsFound();
        }
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
     * @throws se.kth.id1212.globalapps.common.exception.CodedException Has an enumerator which tells view how to handle the exception.
     */
    public void saveApplication(ApplicationDTO application) throws CodedException {
        try {
            UserEntity user = dbao.findUserByUsername(application.getUsername());
            ApplicationEntity applicationEntity = new ApplicationEntity(user);
            dbao.saveApplication(applicationEntity);
            long applicationId = applicationEntity.getApplicationId();
            saveApplicationExpertises(applicationId, application.getExpertises());
            saveApplicationTimePeriods(applicationId, application.getAvailabilityPeriods());
        } catch (SQLIntegrityConstraintViolationException constraintException) {
            LOGGER.log(Level.SEVERE, constraintException.toString());
            throw constraintViolation();
        } catch (Exception saveApplicationException) {
            LOGGER.log(Level.SEVERE, saveApplicationException.toString());
            throw createCodedException(saveApplicationException);
        }
    }
    
    private void saveApplicationTimePeriods(long applicationId, TimePeriodDTO[] timePeriods) throws Exception {
        try {
            dbao.saveApplicationTimePeriods(applicationId, timePeriods);
        } catch (Exception timeoutException) {
            throw timeoutException;
        }
    }
    
    private void saveApplicationExpertises(long applicationId, YearsWithExpertiseDTO[] expertises) throws Exception {
        try {
            dbao.saveApplicationExpertises(applicationId, expertises);
        } catch (Exception timeoutException) {
            throw timeoutException;
        }
    }

    /**
     * Retrieves the <code>AccountType</code> of a specified <code>UserEntity</code> found by searching on the username.
     * @param username The username of a possible <code>UserEntity</code>.
     * @return The name of the <code>UserEntity</code>'s <code>AccountType</code>.
     * @throws se.kth.id1212.globalapps.common.exception.CodedException Has an enumerator which tells view how to handle the exception.
     */
    public String getUsergroup(String username) throws CodedException {
        UserEntity user;
        try {
            user = dbao.findUserByUsername(username);
            
        } catch (Exception dbException) {
            LOGGER.log(Level.SEVERE, dbException.toString());
            throw createCodedException(dbException);
        }   
        if(user != null) {
            return user.getAccountType().getName();
        } else {
            throw noResultsFound();
        }
    }
    
    /**
     * Retrieves a collection of <code>ApplicationEntity</code> based of the criterias defined by <code>ApplicationSearchDTO</code>.
     * For every <code>ApplicationEntity</code> found, the corresponding <code>TimePeriod</code>s and <code>YearsWithExpertise</code>s are retrieved.
     * The collections are then wrapped to an array of <code>ApplicationDTO</code>s to be sent to the view.
     * @param searchCriteria The <code>ApplicationSearchDTO</code> sent from the view, contains such things as expertise names</code, dates, etc.
     * @return An array <code>ApplicationDTO</code> which contains all of the data needed from each <code>ApplicationEntity</code> and its corresponding <code>TimePeriod</code>s and <code>YearsWithExpertise</code>s. 
     * @throws se.kth.id1212.globalapps.common.exception.CodedException Has an enumerator which tells view how to handle the exception.
     */
    public ApplicationDTO[] searchApplications(ApplicationSearchDTO searchCriteria) throws CodedException {
        Collection<ApplicationEntity> retrievedApplications;
        try {
            retrievedApplications = dbao.searchApplications(searchCriteria);
        } catch (Exception unexpectedException) {
            LOGGER.log(Level.SEVERE, unexpectedException.toString());
            throw createCodedException(unexpectedException);
        }
        if(retrievedApplications != null) {
            ApplicationDTO[] applications = new ApplicationDTO[retrievedApplications.size()];
            int position = 0;
            for (ApplicationEntity appl : retrievedApplications) {
                Collection<YearsWithExpertise> competences = dbao.getYearsWithExpertiseByApplicationId(appl.getApplicationId());
                Collection<TimePeriod> timePeriods = dbao.getPeriodsOfAvailabilityById(appl.getApplicationId());
                applications[position] = new Application(appl, timePeriods, competences);
                position++;
            }
            return applications;
        } else {
            throw noResultsFound();
        }
    }
    
    /**
     * Updates an application's status.
     * @param application The application to be updated.
     * @throws CodedException Has an enumerator which tells view how to handle the exception.
     */
    public void updateApplicationStatus(ApplicationDTO application) throws CodedException {
        try {
        dbao.editApplicationStatus(application);
        } catch (Exception updateException) {
            throw createCodedException(updateException);
        }
    }
    
    /**
     * Creates a <code>CodedException</code> based on the exception put in.
     * @param baseException The original exception that was caught.
     * @return A categorized <code>CodedException</code>.
     */
    private CodedException createCodedException(Exception baseException) {
        CodedException codedException = new CodedException(baseException.getMessage());
        codedException.categorizeException(baseException);
        return codedException;
    }
    
    /**
     * Is called when a null result is given, this to show view that no result was found for a given search.
     * @return <code>CodedException</code> which has an enumerator which tells the view how to handle the exception.
     */
    private CodedException noResultsFound() {
        CodedException codedException = new CodedException(ErrorConstants.NULL);
        codedException.setErrorCode(ExceptionEnumerator.NULL);
        return codedException;
    }
    
    /**
     * Is called when a constraint exception is given, this to show view that a constraint was violated when trying to create an entity.
     * @return <code>CodedException</code> which has an enumerator which tells the view how to handle the exception.
     */
    private CodedException constraintViolation() {
        CodedException codedException = new CodedException(ErrorConstants.CONSTRAINT);
        codedException.setErrorCode(ExceptionEnumerator.CONSTRAINT);
        return codedException;
    }
}
