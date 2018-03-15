package se.kth.id1212.globalapps.dtos;

/**
 *
 * @author Diaco Uthman
 */
public interface YearsWithExpertiseDTO {
    
    /**
     * @return The <code>YearsWithExpertiseDTO</code>'s expertise entry. 
     */
    public String getExpertise();
    
    /**
     * @return <code>YearsWithExpertiseDTO</code>'s amount of years with said expertise. 
     */
    public int getYears();
}
