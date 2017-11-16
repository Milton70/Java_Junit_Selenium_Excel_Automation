package Utilities;

import org.apache.log4j.Logger;

public class Log {
    private static Logger Log = Logger.getLogger(Log.class.getName());

    public static void startTestCase(String sTestCaseName) {
        Log.info("************************************************************************");
        Log.info("************************************************************************");
        Log.info("$$$$$$$$$$$$$$              " + sTestCaseName + "          $$$$$$$$$$$$$");
        Log.info("************************************************************************");
        Log.info("************************************************************************");
    }

    public static void endTestCase(String sTestCaseName) {
        Log.info("XXXXXXXXXXXXXX              " + "-E---N---D-" + "          XXXXXXXXXXXXX");
        Log.info("X");
    }

    public static void info(String msg) {
        Log.info(msg);
    }

    public static void warn(String msg) {
        Log.warn(msg);
    }

    public static void error(String msg) {
        Log.error(msg);
    }

    public static void fatal(String msg) {
        Log.fatal(msg);
    }

    public static void debug(String msg) {
        Log.debug(msg);
    }
}
