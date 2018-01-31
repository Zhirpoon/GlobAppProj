package se.kth.id1212.globalapps.integration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import se.kth.id1212.globalapps.model.ApplicationEntity;
import se.kth.id1212.globalapps.model.UserEntity;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
public class DBAO {
    private final EntityManagerFactory emFactory;
    private final ThreadLocal<EntityManager> threadEM = new ThreadLocal<>();
    
    public DBAO(){
        this.emFactory = Persistence.createEntityManagerFactory("GlobalAppPU");
    }
    
    public void addUser(UserEntity user){
        
    }
    
    public UserEntity getUser(){
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
    }
    
    
    
}
