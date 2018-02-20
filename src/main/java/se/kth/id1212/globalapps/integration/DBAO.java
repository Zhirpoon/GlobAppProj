package se.kth.id1212.globalapps.integration;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.id1212.globalapps.dtos.ApplicationSearchDTO;
import se.kth.id1212.globalapps.dtos.TimePeriodDTO;
import se.kth.id1212.globalapps.dtos.YearsWithExpertiseDTO;
import se.kth.id1212.globalapps.model.AccountTypeEntity;
import se.kth.id1212.globalapps.model.ApplicationEntity;
import se.kth.id1212.globalapps.model.ExpertiseEntity;
import se.kth.id1212.globalapps.model.QueryBuilder;
import se.kth.id1212.globalapps.model.TimePeriod;
import se.kth.id1212.globalapps.model.UserEntity;
import se.kth.id1212.globalapps.model.YearsWithExpertise;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Stateless
public class DBAO {

    @PersistenceContext(unitName = "GlobalAppPU")
    private EntityManager em;
    
    public void addUser(UserEntity user){
        em.persist(user);
    }
    
    public AccountTypeEntity getAccountTypeApplicant(){
        return em.find(AccountTypeEntity.class,"APPLICANT");
    }
    
    public Collection<ExpertiseEntity> getAllExpertises() {
        Query query = em.createQuery("SELECT entities FROM ExpertiseEntity entities", ExpertiseEntity.class);
        return query.getResultList();
    }
    
    public UserEntity findUserByUsername(String username) {
        return em.find(UserEntity.class, username);
    }

    public void saveApplication(ApplicationEntity application) {
        em.persist(application);
    }
    
    public void saveApplicationExpertises(long applicationId, YearsWithExpertiseDTO[] expertises) {
        for(YearsWithExpertiseDTO expertise : expertises) {
            Query query = em.createNativeQuery(
                    "INSERT INTO YearsWithExpertise (expertise, applicationid, yearsofexperience) VALUES ("
                            + "(SELECT exp.EXPERTISENAME FROM ExpertiseEntity exp WHERE exp.EXPERTISENAME LIKE ?),"
                            + "(SELECT appl.APPLICATIONID FROM ApplicationEntity appl WHERE appl.APPLICATIONID = ?),"
                            + "?)"
            );
            query.setParameter(1, expertise.getExpertise());
            query.setParameter(2, applicationId);
            query.setParameter(3, expertise.getYears());
            query.executeUpdate();
        }
    }
    
    public void saveApplicationTimePeriods(long applicationId, TimePeriodDTO[] timePeriods) {
        for(TimePeriodDTO timePeriod : timePeriods) {
            Query query = em.createNativeQuery(
                    "INSERT INTO periodofavailability (applicationid, startdate, enddate) VALUES ("
                            + "(SELECT appl.APPLICATIONID FROM ApplicationEntity appl WHERE appl.APPLICATIONID = ?),"
                            + "?,?)"
            );
            query.setParameter(1, applicationId);
            query.setParameter(2, timePeriod.getStartdate());
            query.setParameter(3, timePeriod.getEnddate());
            query.executeUpdate();
        }
    }
    
    public Collection<ApplicationEntity> searchApplications(ApplicationSearchDTO searchCriteria) {
        QueryBuilder queryBuilder =  new QueryBuilder("application");
        queryBuilder.addNameCriteria(searchCriteria.getApplicantFirstname(), searchCriteria.getApplicantLastname());
        queryBuilder.addRegistrationDateCriteria(searchCriteria.getRegistrationDate());
        queryBuilder.addExpertiseCriteria(searchCriteria.getCompetences());
        queryBuilder.addAvailabilityCriteria(searchCriteria.getTimePeriod());
        
        Query query = em.createNativeQuery(queryBuilder.getQuery(), ApplicationEntity.class);
        return query.getResultList();
    }
    
    public Collection<TimePeriod> getPeriodsOfAvailabilityById(long applicationId) {
        List<TimePeriod> timePeriods = new ArrayList<TimePeriod>();
        Query query = em.createNativeQuery("SELECT period. startdate, period.enddate"
                + "FROM periodofavailability period "
                + "WHERE years.applicationid = " + applicationId);
        List<Object[]> result = query.getResultList();
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
    
    public Collection<YearsWithExpertise> getYearsWithExpertiseByApplicationId(long applicationId) {
        List<YearsWithExpertise> competences = new ArrayList<YearsWithExpertise>();
        Query query = em.createNativeQuery("SELECT years.expertise, years.yearsofexperience "
                + "FROM YearsWithExpertise years "
                + "WHERE years.applicationid = " + applicationId);
        List<Object[]> result = query.getResultList();
        for(Object[] obj : result) {
            YearsWithExpertise competence = new YearsWithExpertise(obj[0].toString(), 
                    Integer.parseInt(obj[1].toString()));
            competences.add(competence);
        }
        return competences;
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
