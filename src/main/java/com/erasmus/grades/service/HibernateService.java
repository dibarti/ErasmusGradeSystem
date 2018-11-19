package com.erasmus.grades.service;

import com.erasmus.grades.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateService {

    static SessionFactory sessionFactoryObj;

    protected static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
//        Configuration configObj = new Configuration();
//        configObj.configure("/hibernate.cfg.xml");
//
//        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
//        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
//
//        // Creating Hibernate SessionFactory Instance
//        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
//        return sessionFactoryObj;


        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("/hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(standardRegistry)
                .addAnnotatedClass(User.class)
                .addResource("/User.hbm.xml")
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        sessionFactoryObj = metadata.getSessionFactoryBuilder()
                .build();
        return sessionFactoryObj;
    }

}
