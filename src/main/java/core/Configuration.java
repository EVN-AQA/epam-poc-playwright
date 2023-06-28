package core;

import core.enums.ENVIRONMENT;
import utils.PropertyReader;

import java.util.Properties;

public class Configuration {

    private static Properties props;

    public static void load(ENVIRONMENT env) {
        props = PropertyReader.getInstance().read(env.name().toLowerCase().concat(".properties"));
        if (props != null) {
            updateEnvKey("browserName");
            updateEnvKey("isHeadless");
            updateEnvKey("isMobile");
        }
    }

    public static Properties get() {
        return props;
    }

    public static void update(String key, String value) {
        get().setProperty(key, value);
    }

    private static void updateEnvKey(String key) {
        String value = System.getProperty(key);
        if (value != null) {
            get().setProperty(key, value);
        }
    }

}
