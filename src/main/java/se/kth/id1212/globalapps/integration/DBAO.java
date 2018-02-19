package se.kth.id1212.globalapps.integration;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
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
import se.kth.id1212.globalapps.model.UserEntity;

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
    
    public void searchApplications(ApplicationSearchDTO searchCriteria) {
        QueryBuilder queryBuilder =  new QueryBuilder("application");
        queryBuilder.addNameCriteria(searchCriteria.getApplicantFirstname(), searchCriteria.getApplicantLastname());
        queryBuilder.addRegistrationDateCriteria(searchCriteria.getRegistrationDate());
        String[] competences = searchCriteria.getCompetences();
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
