package com.github.marketahalikova.vedenikuchyne.logika;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Třída Surovina
 * <p>
 * Třída znázorňující jednotlivou surovinu, uchovávající její název a
 * příslušnou jednotku.
 *
 * @author Markéta Halíková, Johanna Švugerová, Martin Weisser
 */
@Entity
public class Surovina implements Serializable {
    @Transient
    private static final long serialVersionUID = -914687629813799760L;
    @Id
    @Column(nullable = false, unique = true)
    private String nazev;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Jednotka jednotka;

    private double mnozstvi;

    private boolean sklad;

    /**
     * Konstruktor třídy Surovina bez mnozstvi
     *
     * @param nazev
     * @param jednotka
     */
    public Surovina(String nazev, Jednotka jednotka) {
        this.nazev = nazev;
        this.jednotka = jednotka;
    }

    public Surovina() {
    }

    /**
     * Konstruktor třídy Surovina s mnozstvim
     *
     * @param nazev
     * @param jednotka
     * @param mnozstvi
     */
    public Surovina(String nazev, Jednotka jednotka, double mnozstvi) {
        this.nazev = nazev;
        this.jednotka = jednotka;
        this.setMnozstvi(mnozstvi);
    }

    public Surovina(String nazev, Jednotka jednotka, double mnozstvi, boolean sklad) {
        this.nazev = nazev;
        this.jednotka = jednotka;
        this.mnozstvi = mnozstvi;
        this.sklad = sklad;
    }

    /**
     * Metoda definujicí enumerace Jednotka
     */
    public enum Jednotka {
        kg,
        l,
        ks,
    }

    /**
     * Metoda získávající název suroviny.
     *
     * @return řetezec s názvem suroviny
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metoda stanovující název suroviny.
     *
     * @param nazev
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * Metoda vracící jednotku dané suroviny.
     *
     * @return řetezec se jménem jednotky
     */
    public Jednotka getJednotka() {
        return jednotka;
    }

    /**
     * Metoda nastavující jednotku dané suroviny.
     *
     * @param jednotka řetezec se jménem suroviny
     */
    public void setJednotka(Jednotka jednotka) {
        this.jednotka = jednotka;
    }

    /**
     * Metoda vracící množství dané suroviny
     *
     * @return mnozstvi
     */
    public double getMnozstvi() {
        return mnozstvi;
    }

    /**
     * Metoda nastavující množství dle daného parametru
     *
     * @param mnozstvi
     */
    public void setMnozstvi(double mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public boolean isSklad() {
        return sklad;
    }

    public void setSklad(boolean sklad) {
        this.sklad = sklad;
    }

    /**
     * Metoda přepisuje funkci equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Surovina other = (Surovina) obj;
        if (jednotka != other.jednotka)
            return false;
        if (nazev == null) {
            return other.nazev == null;
        } else return nazev.equals(other.nazev);
    }

}