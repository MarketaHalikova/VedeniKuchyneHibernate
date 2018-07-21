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

        Surovina jablko = new Surovina("jablko", Surovina.Jednotka.ks,3, true);
        Surovina jahoda = new Surovina("jahoda", Surovina.Jednotka.ks,4, false);

        Session session = HibernateConfig.getSessionJavaConfigFactory().openSession();
        session.beginTransaction();
        session.save(jablko);
        session.save(jahoda);
        session.getTransaction().commit();
        System.out.println("Surovina 1: " + jablko.getNazev());
        System.out.println("Surovina 2: " + jahoda.getNazev());
        session.close();

    }

    public static void saveRecepty(){
        Recept recept1 = new Recept("recept1", "vyrobit", "krm");
        Recept recept2 = new Recept("recept2", "vyrobit", "krm");
        Surovina ananas = new Surovina("ananas", Surovina.Jednotka.ks,3, true);
        Surovina meloun = new Surovina("meloun", Surovina.Jednotka.ks,4, false);
        recept1.getSeznamSurovinReceptu().add(ananas);
        recept1.getSeznamSurovinReceptu().add(meloun);

        Session session = HibernateConfig.getSessionJavaConfigFactory().openSession();
        session.beginTransaction();
        session.save(recept1);
        session.save(recept2);
        session.save(ananas);
        session.save(meloun);



        session.getTransaction().commit();

        session.close();
    }
}
