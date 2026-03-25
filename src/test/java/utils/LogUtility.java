package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtility {

    public static Logger logger = LogManager.getLogger(LogUtility.class);

    // anunță începutul testului
    public static void startTest(String testName) {
        logger.info("***** EXECUTION STARTED: " + testName + " *****");
    }

    // log INFO
    public static void infoLog(String message) {
        logger.info(message);
    }

    // log ERROR
    public static void errorLog(String message) {
        logger.error(message);
    }

    // anunță finalul testului
    public static void finishTest(String testName) {
        logger.info("***** EXECUTION FINISHED: " + testName + " *****");
    }
}
