package com.github.marketahalikova.vedenikuchyne.logika;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída Recept
 * 
 * Tato třída slouží po správu receptů
 * 
 * @author Johanna Švugerová, Markéta Halíková, Martin Weisser
 *
 */
@Entity
public class Recept implements Serializable {

	@Transient
	private static final long serialVersionUID = 9066672598984451474L;

	@Id
	@Column(nullable = false, unique = true)
	private String nazev;
	@Column(nullable = false)
	private String postup;
	@Column(nullable = false)
	private String kategorie;

	@ManyToMany(targetEntity = Surovina.class, fetch = FetchType.EAGER)
//	@JoinTable(name = "recept_surovina",
//			joinColumns = { @JoinColumn(name = "nazev") },
//			inverseJoinColumns = { @JoinColumn(name = "nazev")})
	@Transient
	private List < Surovina > seznamSurovinReceptu;

	public Recept() {
	}

	/**
	 * Konstruktor třídy Recept.
	 * 
	 * @param nazev - název recpetu
	 * @param postup - postup přípravy
	 * @param kategorie - kategorie receptu (předkrm/krm/zákrm)
	 */
	public Recept(String nazev, String postup, String kategorie, List < Surovina > seznamSurovinReceptu) {
		this.nazev = nazev;
		this.postup = postup;
		this.kategorie = kategorie;
		this.seznamSurovinReceptu = seznamSurovinReceptu;
	}

	public Recept(String nazev, String postup, String kategorie) {
		this.nazev = nazev;
		this.postup = postup;
		this.kategorie = kategorie;
	}

	/**
	 * Metoda získává název receptu
	 */
	public String getNazev() {
		return nazev;
	}

	/**
	 * Metoda nastavuje název receptu
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Metoda vrací postup receptu
	 */
	public String getPostup() {
		return postup;
	}

	/**
	 * Metoda nastavuje postup receptu
	 */
	public void setPostup(String postup) {
		this.postup = postup;
	}

	/**
	 * Metoda vrací kategorii receptu
	 */
	public String getKategorie() {
		return kategorie;
	}

	/**
	 * Metoda nastavuje kategorii receptu
	 */
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}

	/**
	 * Metoda nastavuje seznam surovin receptu
	 */
	public void setSeznamSurovinReceptu(List < Surovina > seznamSurovinReceptu) {
		this.seznamSurovinReceptu = seznamSurovinReceptu;
	}

	/**
	 * Metoda nastavuje novou surovinu receptu
	 */
	public void setNovouSurovinuReceptu(Surovina surovina) {
		this.seznamSurovinReceptu.add(surovina);
	}

	/**
	 * Metoda vrací seznam surovin receptu
	 */
	public List < Surovina > getSeznamSurovinReceptu() {
		return seznamSurovinReceptu;
	}

	/**
	 * Metoda vrací seznam názvů surovin v receptu.
	 * 
	 * @return List<String>
	 */
	public List < String > getSeznamJakoString() {
		List < String > seznam = new ArrayList < >();
		for (Surovina surovina: seznamSurovinReceptu) {
			seznam.add(surovina.getNazev() + ", " + surovina.getMnozstvi() + ", " + surovina.getJednotka());
		}
		return seznam;
	}

}