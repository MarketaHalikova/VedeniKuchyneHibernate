package com.github.marketahalikova.vedenikuchyne.repositories;

import com.github.marketahalikova.vedenikuchyne.database.HibernateConfig;
import com.github.marketahalikova.vedenikuchyne.logika.Recept;
import com.github.marketahalikova.vedenikuchyne.logika.Surovina;
import org.hibernate.Session;

public class SetUpRepository {
    public static void main(String[] args) {
        saveSuroviny();
        saveRecepty();
        HibernateConfig.getSessionJavaConfigFactory().close();
    }

    private static void saveSuroviny() {

        Surovina surovina1 = new Surovina("jablko", Surovina.Jednotka.ks,3, true);
        Surovina surovina2 = new Surovina("jahoda", Surovina.Jednotka.ks,4, false);

        Session session = HibernateConfig.getSessionJavaConfigFactory().openSession();
        session.beginTransaction();
        session.save(surovina1);
        session.save(surovina2);
        session.getTransaction().commit();
        System.out.println("Surovina 1: " + surovina1.getNazev());
        System.out.println("Surovina 2: " + surovina2.getNazev());
        session.close();

    }

    public static void saveRecepty(){
        Recept recept1 = new Recept("jablko", "vyrobit", "krm");
        Recept recept2 = new Recept("jahoda", "vyrobit", "krm");

        Session session = HibernateConfig.getSessionJavaConfigFactory().openSession();
        session.beginTransaction();
        session.save(recept1);
        session.save(recept2);
        session.getTransaction().commit();
        System.out.println("Recept 1: " + recept1.getNazev());
        System.out.println("Recept 2: " + recept2.getNazev());
        session.close();
    }
}
