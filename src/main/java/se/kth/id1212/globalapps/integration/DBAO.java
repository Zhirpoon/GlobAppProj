package se.kth.id1212.globalapps.integration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.eclipse.persistence.exceptions.DatabaseException;
import se.kth.id1212.globalapps.dtos.ApplicationDTO;
import se.kth.id1212.globalapps.dtos.ApplicationSearchDTO;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;
import se.kth.id1212.globalapps.model.AccountTypeEntity;
import se.kth.id1212.globalapps.model.ApplicationEntity;
import se.kth.id1212.globalapps.model.Constants.DbConstants;
import se.kth.id1212.globalapps.model.Constants.ErrorConstants;
import se.kth.id1212.globalapps.model.ExpertiseEntity;
import se.kth.id1212.globalapps.model.QueryBuilder;
import se.kth.id1212.globalapps.model.TimePeriod;
import se.kth.id1212.globalapps.model.UserEntity;
import se.kth.id1212.globalapps.model.YearsWithExpertise;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 * The database access object which retrieves data from the database and also stores data to the database.
 */
@Stateless
//This class should only be called with an active transation
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class DBAO {
    DbConstants dbConstants = new DbConstants();
    ErrorConstants errors = new ErrorConstants();

    @PersistenceContext(unitName = "GlobalAppPU")
    private EntityManager em;
  
    /**
     * Adds a <code>UserEntity</code> to the database.
     * @param user The <code>UserEntity</code> to be stored.
     * @throws java.lang.Exception
     */
    public void addUser(UserEntity user) throws Exception {
        boolean userAlreadyExists;
        try {
            userAlreadyExists = findUserByUsername(user.getUsername()) != null;
        } catch (Exception e) {
            throw e;
        }
        if(!userAlreadyExists) {
            em.persist(user);
            em.flush();
        } else {
            throw new Exception(errors.DUPLICATE_KEY);
        }
    }
    
    public ApplicationEntity getApplicationEntityById(long applicationId) {
        return em.find(ApplicationEntity.class, applicationId);
    }
    
    /**
     * Gets the <code>AccountTypeEntity</code> with the primary key APPLICANT, if it exists.
     * @return The <code>AccountTypeEntity</code> which has the primary key APPLICANT.
     */
    public AccountTypeEntity getAccountTypeApplicant() {
        return em.find(AccountTypeEntity.class,"APPLICANT");
    }
    
    /**
     * Gets all <code>ExpertiseEntity</code> that exists in the database.
     * @return A collection of <code>ExpertiseEntity</code>. 
     */
    public Collection<ExpertiseEntity> getAllExpertises() {
        Query query = em.createQuery("SELECT " + dbConstants.EXPERTISEENTITY_QUERY_NAME
                + " FROM " + dbConstants.EXPERTISEENTITY_TABLE_NAME + " " + dbConstants.EXPERTISEENTITY_QUERY_NAME
                , ExpertiseEntity.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            System.out.println("Something went wrong when retrieving all expertises.\n" + e.getMessage());
            return null;
        }
    }
    
    /**
     * Find a <code>UserEntity</code> based on its primary key.
     * @param username The username that is used to search for a specific <code>UserEntity</code>.
     * @return The <code>UserEntity</code> with the specified username.
     */
    public UserEntity findUserByUsername(String username) {
        try {
            return em.find(UserEntity.class, username);
        } catch (Exception findException) {
            throw findException;
        }
    }
    
    public ApplicationEntity findApplicationById(long applicationId) {
        return em.find(ApplicationEntity.class, applicationId);
    }

    /**
     * Save an <code>ApplicationEntity</code> to the database.
     * @param application The <code>ApplicationEntity</code> that is to be stored.
     */
    public void saveApplication(ApplicationEntity application) throws Exception {
            try {
                em.persist(application);
                em.flush();
            } catch (PersistenceException | DatabaseException timeoutException) {
                throw new Exception(ErrorConstants.TIMEOUT);
            }
            
            
    }
    
    /**
     * Saves an <code>ApplicationEntity</code>'s <code>YearsWithExpertise</code>s to the database.
     * @param applicationId The <code>ApplicationEntity</code>'s application ID.
     * @param expertises The <code>ApplicationEntity</code>'s <code>YearsWithExpertise</code>s to be stored.
     * @throws java.lang.Exception Most likely a timeout error.
     */
    public void saveApplicationExpertises(long applicationId, YearsWithExpertiseDTO[] expertises) throws Exception {
        for(YearsWithExpertiseDTO expertise : expertises) {
            Query query = em.createNativeQuery(
                    "INSERT INTO " + dbConstants.YEARSWITHEXPERTISE_TABLE_NAME
                        + " (" + dbConstants.YEARSWITHEXPERTISE_COLUMN_EXPERTISE + "," 
                        + dbConstants.YEARSWITHEXPERTISE_COLUMN_APPLICATIONID + ","
                        + dbConstants.YEARSWITHEXPERTISE_COLUMN_YEARS_OF_EXPERIENCE + ") VALUES ("
                    + "(SELECT " + dbConstants.EXPERTISEENTITY_QUERY_NAME + "." + dbConstants.EXPERTISEENTITY_COLUMN_EXPERTISENAME
                            + " FROM " + dbConstants.EXPERTISEENTITY_TABLE_NAME + " " + dbConstants.EXPERTISEENTITY_QUERY_NAME
                            + " WHERE "+ dbConstants.EXPERTISEENTITY_QUERY_NAME + "." + dbConstants.EXPERTISEENTITY_COLUMN_EXPERTISENAME + " LIKE ?),"
                    + "(SELECT " + dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_ID
                            + " FROM " + dbConstants.APPLICATIONENTITY_TABLE_NAME + " " + dbConstants.APPLICATIONENTITY_QUERY_NAME
                            + " WHERE " + dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_ID + " = ?),"
                    + "?)"
            );
            query.setParameter(1, expertise.getExpertise());
            query.setParameter(2, applicationId);
            query.setParameter(3, expertise.getYears());
            try {
                query.executeUpdate();
            } catch (PersistenceException | DatabaseException timeoutException) {
                throw new Exception(ErrorConstants.TIMEOUT);
            }
        }
    }
    
    /**
     * Saves an <code>ApplicationEntity</code>'s <code>TimePeriod</code>s to the database.
     * @param applicationId The <code>ApplicationEntity</code>'s application ID.
     * @param timePeriods The <code>ApplicationEntity</code>'s <code>YearsWithExpertise</code>s to be stored, this represents the periods of availability.
     * @throws java.lang.Exception Most likely a timeout error.
     */
    public void saveApplicationTimePeriods(long applicationId, TimePeriodDTO[] timePeriods) throws Exception {
        for(TimePeriodDTO timePeriod : timePeriods) {
            Query query = em.createNativeQuery(
                    "INSERT INTO " + dbConstants.TIMEPERIOD_TABLE_NAME
                        + " (" + dbConstants.TIMEPERIOD_COLUMN_APPLICATIONID + "," 
                        + dbConstants.TIMEPERIOD_COLUMN_STARTDATE + ","
                        + dbConstants.TIMEPERIOD_COLUMN_ENDDATE + ") VALUES ("
                        + "(SELECT " + dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_ID
                            + " FROM " + dbConstants.APPLICATIONENTITY_TABLE_NAME + " " + dbConstants.APPLICATIONENTITY_QUERY_NAME
                            + " WHERE " + dbConstants.APPLICATIONENTITY_QUERY_NAME + "." + dbConstants.APPLICATIONENTITY_ID + " = ?),"
                        + "?,?)"
            );
            query.setParameter(1, applicationId);
            query.setParameter(2, timePeriod.getStartdate());
            query.setParameter(3, timePeriod.getEnddate());
            try {
                query.executeUpdate();
            } catch (PersistenceException | DatabaseException timeoutException) {
                throw new Exception(ErrorConstants.TIMEOUT);
            }
        }
    }
    
    /**
     * Searches and retrieves <code>ApplicationEntity</code> based on <code>ApplicationSearchDTO</code>.
     * These criteria are then appended to a query with the help of <code>QueryBuilder</code>.
     * @param searchCriteria The search criteria stored in the <code>ApplicationSearchDTO</code>.
     * @return A collection <code>ApplicationEntity</code> based on the query built.
     */
    public Collection<ApplicationEntity> searchApplications(ApplicationSearchDTO searchCriteria) {
        QueryBuilder queryBuilder =  new QueryBuilder(dbConstants.APPLICATIONENTITY_QUERY_NAME);
        queryBuilder.addNameCriteria(searchCriteria.getApplicantFirstname(), searchCriteria.getApplicantLastname());
        queryBuilder.addRegistrationDateCriteria(searchCriteria.getRegistrationDate());
        queryBuilder.addExpertiseCriteria(searchCriteria.getCompetences());
        queryBuilder.addAvailabilityCriteria(searchCriteria.getTimePeriod());
        
        Query query = em.createNativeQuery(queryBuilder.getQuery(), ApplicationEntity.class);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }   
    }
    
    /**
     * Retrieves all <code>TimePeriod</code>s associated with a specific <code>ApplicationEntity</code>.
     * @param applicationId The <code>ApplicationEntity</code>'s application ID.
     * @return A collection of <code>TimePeriod</code>s that corresponded to the applicationId.
     */
    public Collection<TimePeriod> getPeriodsOfAvailabilityById(long applicationId) {
        List<TimePeriod> timePeriods = new ArrayList<TimePeriod>();
        Query query = em.createNativeQuery("SELECT " + dbConstants.TIMEPERIOD_QUERY_NAME + "." + dbConstants.TIMEPERIOD_COLUMN_STARTDATE + ","
                + dbConstants.TIMEPERIOD_QUERY_NAME + "." + dbConstants.TIMEPERIOD_COLUMN_ENDDATE
                + " FROM " + dbConstants.TIMEPERIOD_TABLE_NAME + " " + dbConstants.TIMEPERIOD_QUERY_NAME
                + " WHERE " + dbConstants.TIMEPERIOD_QUERY_NAME + "." + dbConstants.TIMEPERIOD_COLUMN_APPLICATIONID + " = " + applicationId);
        List<Object[]> result;
        try {
            result = query.getResultList();
        } catch (Exception e) {
            System.out.println("Something went wrong when finding PeriodsOfAvailabilityById,\n" + e.getMessage());
            return null;
        }
        DateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {   
            for(Object[] obj : result) {
                TimePeriod timePeriod = new TimePeriod(sourceFormat.parse(obj[0].toString()), sourceFormat.parse(obj[1].toString()));
                timePeriods.add(timePeriod);
                return timePeriods;
            }
        } catch (ParseException e) {
            System.err.println(e);
        }
        return timePeriods;
    }
    
    /**
     * Retrieves all <code>YearsWithExpertise</code>s associated with a specific <code>ApplicationEntity</code>.
     * @param applicationId The <code>ApplicationEntity</code>'s application ID.
     * @return A collection of <code>YearsWithExpertise</code>s that corresponded to the applicationId.
     */
    public Collection<YearsWithExpertise> getYearsWithExpertiseByApplicationId(long applicationId) {
        List<YearsWithExpertise> competences = new ArrayList<YearsWithExpertise>();
        Query query = em.createNativeQuery("SELECT " + dbConstants.YEARSWITHEXPERTISE_QUERY_NAME + "." + dbConstants.YEARSWITHEXPERTISE_COLUMN_EXPERTISE + ","
                + dbConstants.YEARSWITHEXPERTISE_QUERY_NAME + "." + dbConstants.YEARSWITHEXPERTISE_COLUMN_YEARS_OF_EXPERIENCE
                + " FROM " + dbConstants.YEARSWITHEXPERTISE_TABLE_NAME + " " + dbConstants.YEARSWITHEXPERTISE_QUERY_NAME
                + " WHERE " + dbConstants.YEARSWITHEXPERTISE_QUERY_NAME + "." + dbConstants.YEARSWITHEXPERTISE_COLUMN_APPLICATIONID + " = " + applicationId);
        List<Object[]> result; 
        try {
            result = query.getResultList();
        } catch (Exception noResultException) {
            return null;
        }
        for(Object[] obj : result) {
            YearsWithExpertise competence = new YearsWithExpertise(obj[0].toString(), 
                    Integer.parseInt(obj[1].toString()));
            competences.add(competence);
        }
        return competences;
    }
    
    /**
     * Edits/updated an <code>ApplicationEntity</code>'s status if this has now already been changed by someone else.
     * @param application The application to be changed.
     * @throws Exception Either a timeout or an outdated version.
     */
    public void editApplicationStatus(ApplicationDTO application) throws Exception {
        ApplicationEntity applicationEntity = em.find(ApplicationEntity.class, application.getApplicationId());
        if(application.getVersionNumber() == applicationEntity.getVersionNumber()) {
            int newVersionNumber = application.getVersionNumber() + 1;
            applicationEntity.setStatus(application.getStatus());
            applicationEntity.setVersionNumber(newVersionNumber);
        } else {
            throw new Exception(ErrorConstants.OUTDATED_VERSION);
        }
    }
//    private final EntityManagerFactory emFactory;
    // private final ThreadLocal<EntityManager> threadEM = new ThreadLocal<>();

//    public DBAO(){
//        this.emFactory = Persistence.createEntityManagerFactory("GlobalAppPU");
//    }
////    
//    public void addUser(UserEntity user){
//        try{
//            EntityManager em = beginTransaction();
//            em.persist(user);
//        }finally{
//            commitTransaction();
//        }
//    }
    /*    public UserEntity getUser(){
    }
    
    public String[] getAllExpertises(){
    }
    
    public void addApplication(ApplicationEntity application){
    }
    
    public ApplicationEntity[] getApplicationByCriteria(ApplicationSearchDTO searchCriterias){
    }
    
    public void updateApplicationStatusRejected(){
    }
    
    public void updateApplicationStatusAccepted(){
    }*/
//    private EntityManager beginTransaction() {
//         EntityManager em = emFactory.createEntityManager();
//         this.threadEM.set(em);
//         EntityTransaction transaction = em.getTransaction();
//         if(!transaction.isActive()){
//             transaction.begin();
//         }
//         return em;
//    }
//
//    private void commitTransaction() {
//        this.threadEM.get().getTransaction().commit();
//    }
}
