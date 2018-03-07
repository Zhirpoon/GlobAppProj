package se.kth.id1212.globalapps.utility;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;


@Startup
@Singleton
public class TestLogger {
  
public static final Logger FingLogging = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

private static TestLogger instance = null;
    
    public static TestLogger getInstance() {
        if(instance == null) {
            prepareLogger();
            instance = new TestLogger();
        }
        return instance;
    }

    @PostConstruct
    private static void prepareLogger() {
        try {
            FileHandler myFileHandler = new FileHandler("ettlattarenamnatthitta.txt", true);
            myFileHandler.setFormatter(new SimpleFormatter());
            FingLogging.addHandler(myFileHandler);
            FingLogging.setUseParentHandlers(false);
            FingLogging.setLevel(Level.WARNING);
        } catch (IOException | SecurityException ex) {
    //        Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
  
 
}
