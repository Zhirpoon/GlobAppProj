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
public class SingletonLogger {
  
public static final Logger SingletonLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

private static SingletonLogger instance = null;
    
    public static SingletonLogger getInstance() {
        if(instance == null) {
            prepareLogger();
            instance = new SingletonLogger();
        }
        return instance;
    }

    @PostConstruct
    private static void prepareLogger() {
        try {
            FileHandler myFileHandler = new FileHandler("ettlattarenamnatthitta.txt", true);
            myFileHandler.setFormatter(new SimpleFormatter());
            SingletonLogger.addHandler(myFileHandler);
            SingletonLogger.setUseParentHandlers(false);
            SingletonLogger.setLevel(Level.WARNING);
        } catch (IOException | SecurityException loggerException) {
            System.err.println("Could not create fole for logging.\n" + loggerException.toString());
        }  
    }
  
 
}
