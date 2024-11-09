package Common;


import Utilities.PropertiesLoader;

public class Properties {
    public static final String URL = getPropertyFromEnv("URL");
    public static final String browser = getPropertyFromEnv("browser");



    private static String getPropertyFromEnv(String propertyName) {
        return System.getProperty(propertyName, PropertiesLoader.readEnvFile(propertyName));
    }



}
