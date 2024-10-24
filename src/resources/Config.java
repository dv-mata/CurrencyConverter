package resources;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties = new Properties();

    public Config() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se encontró el archivo de configuración.");
                return;
            }
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getAPIKEY() {
        return properties.getProperty("APIKEY");
    }

}
