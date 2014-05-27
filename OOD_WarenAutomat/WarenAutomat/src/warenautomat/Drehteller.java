
package warenautomat;

import java.util.Date;

import warenautomat.SystemSoftware;

public class Drehteller {
  
	private static final int NR_FACH = 16;
	private Fach m_Fach[];
	private int m_aktuelleFachNr;
	private Kasse m_Kasse;
	
	private int m_eigeneDrehtellerNr;
	
	/**
	 * Standard cstor: f�hrt die n�tigen Initialisierungen durch,
	 * unteranderem das aktuelle Fach f�r weitere Zugriffe.
	 */
	public Drehteller(int pNr, Kasse pKasse) {
		// kreiere 16 F�cher und f�lle sie mit null
		m_Fach = new Fach[NR_FACH];
		
		for (int i = 0; i < NR_FACH; i++) {
			m_Fach[i] = new Fach();
		}
		// setze Startposition
		m_aktuelleFachNr = 0;
		m_eigeneDrehtellerNr = pNr;
		m_Kasse = pKasse;
	}
	
	/**
	 * Der Automat ruft diese Funktion auf, um den Drehteller auf
	 * die n�chste Position zu drehen. 
	 * 
	 * Anschliessend werden die Anzeigen f�r das Verfallsdatum und 
	 * f�r den Warenpreis aktualisiert.
	 */
	public void drehen() {
		m_aktuelleFachNr++;
		
		if (m_aktuelleFachNr >= (NR_FACH)) {
			m_aktuelleFachNr = 0;
		}
				
		aktualisiereVerfallsdatum();
		aktualisiereWarenpreis();
	}
	
	/**
	 * Wird am Automaten versucht ein Fach zu �ffnen, pr�ft der jeweilige
	 * Drehteller, ob das Fach leer ist, ob das Produkt abgelaufen
	 * ist, ob gen�gend Geld eingeworfen wurde, ob gen�gend Wechselgeld
	 * im Automaten vorhanden ist. Dabei werden jeweils die n�tigen 
	 * Anzeigen ausgel�st.
	 * 
	 * Falls keiner dieser Tests fehl schl�gt, wird der Preis vom
	 * eingeworfenen Betrag abgezogen, die Statistik aktualisiert 
	 * und die Anzeigen f�rs Verfallsdatum und den Warenpreis
	 * aktualisiert.
	 * 
	 * @return true, falls das Fach ge�ffnet werden darf
	 * 		   false, falls ein Test fehlschl�gt
	 */
	public boolean oeffnen() {
	
		// Fach leer
		if (m_Fach[m_aktuelleFachNr].istLeer()) {
			System.out.println("*** Drehteller leer");
			return false;
		}
		// Verfallsdatum abgelaufen
		if (istAbgelaufen(m_aktuelleFachNr)) {
			System.out.println("*** Ware abgelaufen");
			return false;
		}
		// Genug Geld eingeworfen
		if (!m_Kasse.genuegendGeld(m_Fach[m_aktuelleFachNr].getWare().getPreis())) {
			System.out.println("*** Nicht gen�gend Geld");
			SystemSoftware.zeigeZuWenigGeldAn();
			return false;
		}
		// Zu wenig Wechselgeld
		if (!m_Kasse.pruefeRetourGeld(m_Fach[m_aktuelleFachNr].getWare().getPreis())) {
			System.out.println("*** Zu wenig Wechselgeld");
			SystemSoftware.zeigeZuWenigWechselGeldAn();
			return false;
		}
		
		System.out.println("*** Drehteller.oeffnen erfolgreich");
		// reduziere Geldbetrag
		m_Kasse.reduziereGeldbetrag(m_Fach[m_aktuelleFachNr].getWare().getPreis());
		// aktualisiere Statistik
		m_Kasse.aktualisiereStatistik(
				m_Fach[m_aktuelleFachNr].getWare().getName(), 
				m_Fach[m_aktuelleFachNr].getWare().getPreis(), 
				SystemSoftware.gibAktuellesDatum());
		// elimiere Ware im Fach
		m_Fach[m_aktuelleFachNr].setWare(null);
		// aktualisiere die Anzeigen
		aktualisiereVerfallsdatum();
		aktualisiereWarenpreis();
		
		return true;
	}
	
	/**
	 * Das aktuell aktive Fach wird mit der �bergebenen Ware bef�llt.
	 * 
	 * Anschliessend werden die Anzeigen f�r das Verfallsdatum und
	 * des Warenpreises aktualisiert.
	 * 
	 * @param pWarenName Name der Ware
	 * @param pPreis Preis der Ware
	 * @param pVerfallsDatum Verfallsdatum der Ware
	 */
	public void fuelleAktuellesFach(String pWarenName, 
			int pPreis, Date pVerfallsDatum) {
		m_Fach[m_aktuelleFachNr].setWare(
				new Ware(pWarenName, pPreis, pVerfallsDatum));
		
		aktualisiereVerfallsdatum();
		aktualisiereWarenpreis();
	}
	
	/**
	 * Berechnet den gesamten Warenwert dieses Drehtellers exklusive
	 * abgelaufener Ware.
	 * 
	 * @return Gesamter Warenwert des Drehtellers
	 */
	public int gibDrehtellerWarenwert() {
		int mDrehtellerWarenwert = 0;
		for (int i = 0; i < NR_FACH; i++) {
			if (m_Fach[i].istLeer()) {
				continue;
			} else if (!istAbgelaufen(i)) {
				mDrehtellerWarenwert += m_Fach[i].getWare().getPreis();
			}
		}
		return mDrehtellerWarenwert;
	}

	/**
	 * Sendet den richtigen Status an die Verfallsdatum's Anzeige.
	 * 0 = leer, 1 = nicht abgelaufen, 2 = abgelaufen
	 */
	public void aktualisiereVerfallsdatum() {
		int status = 0;
		if (m_Fach[m_aktuelleFachNr].istLeer()) {
			status = 0;
		}else if (istAbgelaufen(m_aktuelleFachNr)) {
			status = 2;
		} else {
			status = 1;
		}
		SystemSoftware.zeigeVerfallsDatum(m_eigeneDrehtellerNr, status);

	}
	
	/** 
	 * Sendet den Warenpreis der Ware aus dem Fach an die 
	 * Warenpreis Anzeige in Franken.
	 */
	public void aktualisiereWarenpreis() {
		int preis = 0;
		if (m_Fach[m_aktuelleFachNr].istLeer()) {
			preis = 0;
		} else {
			preis = m_Fach[m_aktuelleFachNr].getWare().getPreis();
		}
		SystemSoftware.zeigeWarenPreisAn(m_eigeneDrehtellerNr, 
				m_Kasse.inFranken(preis));		
	}
	
	/**
	 * Gib das aktuelle Fach zur�ck.
	 * 
	 * @return aktuelles Fach
	 */
	public Fach getAktuellesFach() {
		return m_Fach[m_aktuelleFachNr];
	}
	
	/**
	 * Gib die aktuelle aktive Fachnummer zur�ck
	 * 
	 * @return aktive Fachnummer
	 */
	public int getAktuelleFachNr() {
		return m_aktuelleFachNr+1;
	}

/**
 * Pr�ft ob Waren abgelaufen sind anhand des aktuellen Datums <br>
 * @param pFachNr
 * @return true f�r abgelaufen, false f�r _nicht_ abgelaufen
 */
	private boolean istAbgelaufen(int pFachNr) {
		Date aktDatum = SystemSoftware.gibAktuellesDatum();
		Date verfallsDatum = m_Fach[pFachNr].getWare().getVerfallsDatum();
		
		if (verfallsDatum.after(aktDatum)) {
			return false;
		} else {
			return true;
		}
	}
}
