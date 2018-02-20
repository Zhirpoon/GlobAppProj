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
import se.kth.id1212.globalapps.view.DTOs.LoginCredentialsDTO;
import se.kth.id1212.globalapps.view.DTOs.RegistrationDTO;

/**
 *
 * @author Anders Klasson <aklasson@kth.se>
 */
@Stateless
public class Controller {
    
    @EJB
    DBAO dbao;
    
    public void register(RegistrationDTO registrationDTO) {
        dbao.addUser(new UserEntity(registrationDTO, 1, dbao.getAccountTypeApplicant()));
    }

    public void login(LoginCredentialsDTO loginCredentialsDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    
    public void saveApplication(ApplicationDTO application) {
        UserEntity user = dbao.findUserByUsername(application.getUsername());
        ApplicationEntity applicationEntity = new ApplicationEntity(user);
        dbao.saveApplication(applicationEntity);
        long applicationId = applicationEntity.getApplicationId();
        dbao.saveApplicationTimePeriods(applicationId, application.getAvailabilityPeriods());
        dbao.saveApplicationExpertises(applicationId, application.getExpertises());
    }

    public String getUsergroup(String username) {
        UserEntity user = dbao.findUserByUsername(username);
        return user.getAccountType().getName();
    }
    
    public void searchApplications(ApplicationSearchDTO searchCriteria) {
        dbao.searchApplications(searchCriteria);
    }
    
}
