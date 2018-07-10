package com.github.marketahalikova.vedenikuchyne.database;


import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfig {
    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/kuchyne" +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false" +
                    "&serverTimezone=UTC");
            props.put("hibernate.connection.username", "root");
            props.put("hibernate.connection.password", "admin");
            props.put("hibernate.current_session_context_class", "thread");
            props.put("hibernate.show_sql", "true");
            // Jak se hibernate chova k datum v databazi
            props.put("hibernate.hbm2ddl.auto", "create");
            configuration.setProperties(props);

            //we can set mapping file or class with annotation
            //addClass(Employee1.class) will look for resource
            // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)

            configuration.addAnnotatedClass(Recept.class);
            configuration.addAnnotatedClass(Surovina.class);


            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Java Config serviceRegistry created");

            return configuration.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }
}
