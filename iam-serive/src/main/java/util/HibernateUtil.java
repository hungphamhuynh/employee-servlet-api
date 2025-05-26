package util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class HibernateUtil {
    private static final EntityManagerFactory emf;

    static {
        try {
            Properties props = new Properties();
            try (InputStream in = HibernateUtil.class.getClassLoader()
                    .getResourceAsStream("hibernate.properties")) {
                if (in == null) {
                    throw new FileNotFoundException("hibernate.properties not found");
                }
                props.load(in);
            }
            Map<String, Object> settings = new HashMap<String, Object>();
            for (String key : props.stringPropertyNames()) {
                settings.put(key, props.getProperty(key));
            }

            emf = Persistence.createEntityManagerFactory("P", settings);
        } catch (Exception e){
            throw new ExceptionInInitializerError("EntityManagerFactory creation failed");
        }
    }

    private HibernateUtil() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
