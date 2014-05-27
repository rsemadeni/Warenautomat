package warenautomat;

import java.util.ArrayList;
import java.util.Date;

import warenautomat.SystemSoftware;

/**
 * Die Kasse verwaltet das eingenommene Geld sowie das Wechselgeld. <br>
 * Die Kasse hat fÃ¼nf MÃ¼nz-SÃ¤ulen fÃ¼r: <br>
 * - 10 Rappen <br>
 * - 20 Rappen <br>
 * - 50 Rappen <br>
 * - 1 Franken <br>
 * - 2 Franken <br>
 */
public class Kasse {
	
	private static final int ZWEIFRANKEN = 200;
	private static final int EINFRANKEN = 100;
	private static final int FUENFZIGER = 50;
	private static final int ZWANZIGER = 20;
	private static final int ZEHNER = 10;
	private static int[] arrMuenzen = {200,100,50,20,10};
	
	private static final int NR_MUENZSAEULE = 5;
	private Muenzsaeule m_Muenzsaeule[];
	private int m_GeldBetrag;
	private int m_TotalGeldBetrag;
	int[] m_RetourMuenzen;
	
	public ArrayList<Transaktion> m_TransaktionList;
	

  /**
   * Standard-Konstruktor. <br>
   * Führt die nötigen Initialisierungen durch.
   */
  public Kasse() {
    
	  m_Muenzsaeule = new Muenzsaeule[NR_MUENZSAEULE];
	  m_Muenzsaeule[0] = new Muenzsaeule(ZWEIFRANKEN);
	  m_Muenzsaeule[1] = new Muenzsaeule(EINFRANKEN);
	  m_Muenzsaeule[2] = new Muenzsaeule(FUENFZIGER);
	  m_Muenzsaeule[3] = new Muenzsaeule(ZWANZIGER);
	  m_Muenzsaeule[4] = new Muenzsaeule(ZEHNER);
	  
	  m_RetourMuenzen = new int[NR_MUENZSAEULE];
	  
	  m_TransaktionList = new ArrayList<Transaktion>();
	  
	  m_GeldBetrag = 0;
	  m_TotalGeldBetrag = 0;
  }

  /**
   * Diese Methode wird aufgerufen nachdem das Personal beim GeldauffÃ¼llen die
   * MÃ¼nzart und die Anzahl der MÃ¼nzen Ã¼ber die Tastatur eingegeben hat (siehe
   * Use-Case "Kasse auffÃ¼llen").
   * 
   * @param pMuenzenBetrag Betrag der MÃ¼nzart in Franken.
   * @param pAnzahl Anzahl der neu eingelegten MÃ¼nzen.
   * @return Wenn es genÃ¼gend Platz in der MÃ¼nzsÃ¤ule hat: die Anzahl MÃ¼nzen
   *         welche eingelegt werden (d.h. pAnzahl). <br>
   *         Wenn es nicht genÃ¼gend Platz hat: die Anzahl MÃ¼nzen welche nicht
   *         Platz haben werden, als negative Zahl (z.B. -20). <br>
   *         Wenn ein nicht unterstÃ¼tzter MÃ¼nzbetrag Ã¼bergeben wurde: -200
   */
  public int fuelleKasse(double pMuenzenBetrag, int pAnzahl) {
    int muenzArt = inRappen(pMuenzenBetrag);
    int retVal = 0;
    switch (muenzArt) {
    case ZWEIFRANKEN:
    	retVal = m_Muenzsaeule[0].pruefeMuenzbestand(pAnzahl);
    	System.out.println("*** fuelleKasse: Münzsäule 2.00: " + retVal + ", alter Münzbestand: " + m_Muenzsaeule[0].getMuenzBestand());
    	break;
    case EINFRANKEN:
    	retVal = m_Muenzsaeule[1].pruefeMuenzbestand(pAnzahl);
    	System.out.println("*** fuelleKasse: Münzsäule 1.00: " + retVal + ", alter Münzbestand: " + m_Muenzsaeule[1].getMuenzBestand());
    	break;
    case FUENFZIGER:
    	retVal = m_Muenzsaeule[2].pruefeMuenzbestand(pAnzahl);
    	System.out.println("*** fuelleKasse: Münzsäule 0.50: " + retVal + ", alter Münzbestand: " + m_Muenzsaeule[2].getMuenzBestand());
    	break;
    case ZWANZIGER:
    	retVal = m_Muenzsaeule[3].pruefeMuenzbestand(pAnzahl);
    	System.out.println("*** fuelleKasse: Münzsäule 0.20: " + retVal + ", alter Münzbestand: " + m_Muenzsaeule[3].getMuenzBestand());
    	break;
    case ZEHNER:
    	retVal = m_Muenzsaeule[4].pruefeMuenzbestand(pAnzahl);
    	System.out.println("*** fuelleKasse: Münzsäule 0.10: " + retVal + ", alter Münzbestand: " + m_Muenzsaeule[4].getMuenzBestand());
    	break;
    default:
    	retVal = -200;
    }
    
    return retVal;
  }

  /**
   * Diese Methode wird aufgerufen nachdem das Personal beim GeldauffÃ¼llen den
   * Knopf "BestÃ¤tigen" gedrÃ¼ckt hat. (siehe Use-Case "Kasse auffÃ¼llen"). <br>
   * Verbucht die MÃ¼nzen gemÃ¤ss dem vorangegangenen Aufruf der Methode <code> fuelleKasse() </code>.
   */
  public void fuelleKasseBestaetigung() {
	  m_TotalGeldBetrag = 0;
	  for (int i = 0; i < NR_MUENZSAEULE; i++) {
		  m_Muenzsaeule[i].bestaetigeMuenzbestand();
		  m_TotalGeldBetrag += m_Muenzsaeule[i].getTotalBetrag();
//		  System.out.println("*** kasse.Überflüssiger Müenzbestand Säule " 
//				  + (i+1) + ": " + m_Muenzsaeule[i].getUeberfluessigerMuenzBestand());
		  SystemSoftware.zeigeMuenzenInGui(inFranken(m_Muenzsaeule[i].getMuenzArt()), m_Muenzsaeule[i].getMuenzBestand());

 	  }
	  System.out.println("*** kasse.fuelleKasseBestaetigung(): " + inFranken(m_TotalGeldBetrag));
  }

  /**
   * Diese Methode wird aufgerufen wenn ein Kunde eine MÃ¼nze eingeworfen hat. <br>
   * FÃ¼hrt den eingenommenen Betrag entsprechend nach. <br>
   * Stellt den nach dem Einwerfen vorhandenen Betrag im Kassen-Display dar. <br>
   * Eingenommenes Geld steht sofort als Wechselgeld zur VerfÃ¼gung. <br>
   * Die MÃ¼nzen werden von der Hardware-Kasse auf Falschgeld, FremdwÃ¤hrung und
   * nicht unterstÃ¼tzte MÃ¼nzarten geprÃ¼ft, d.h. diese Methode wird nur
   * aufgerufen wenn ein MÃ¼nzeinwurf soweit erfolgreich war. <br>
   * Ist die MÃ¼nzsÃ¤ule voll (d.h. 100 MÃ¼nzen waren vor dem Einwurf bereits darin
   * enthalten), so wird mittels
   * <code> SystemSoftware.auswerfenWechselGeld() </code> unmittelbar ein
   * entsprechender MÃ¼nz-Auswurf ausgefÃ¼hrt. <br>
   * Hinweis: eine Hardware-MÃ¼nzsÃ¤ule hat jeweils effektiv Platz fÃ¼r 101 MÃ¼nzen.
   * 
   * @param pMuenzenBetrag Der Betrag der neu eingeworfenen MÃ¼nze in Franken.
   * @return <code> true </code>, wenn er Einwurf erfolgreich war. <br>
   *         <code> false </code>, wenn MÃ¼nzsÃ¤ule bereits voll war.
   */
  public boolean einnehmen(double pMuenzenBetrag) {
	  if (fuelleKasse(pMuenzenBetrag, 1) > 0) {
		  fuelleKasseBestaetigung();
		  m_GeldBetrag += inRappen(pMuenzenBetrag);
		  System.out.println("*** kasse.einnehmen: " + inFranken(m_GeldBetrag));
	  } else {
		  switch (inRappen(pMuenzenBetrag)) {
		  case ZWEIFRANKEN:
			  SystemSoftware.auswerfenWechselGeld(
					  m_Muenzsaeule[0].getUeberfluessigerMuenzBestand() * inFranken(ZWEIFRANKEN));
			  break;
		  case EINFRANKEN:
			  SystemSoftware.auswerfenWechselGeld(
					  m_Muenzsaeule[1].getUeberfluessigerMuenzBestand() * inFranken(EINFRANKEN));
			  break;
		  case FUENFZIGER:
			  SystemSoftware.auswerfenWechselGeld(
					  m_Muenzsaeule[2].getUeberfluessigerMuenzBestand() * inFranken(FUENFZIGER));
			  break;
		  case ZWANZIGER:
			  SystemSoftware.auswerfenWechselGeld(
					  m_Muenzsaeule[3].getUeberfluessigerMuenzBestand() * inFranken(ZWANZIGER));
			  break;
		  case ZEHNER:
			  SystemSoftware.auswerfenWechselGeld(
					  m_Muenzsaeule[4].getUeberfluessigerMuenzBestand() * inFranken(ZEHNER));
			  break;
		  }
	  }
	  SystemSoftware.zeigeBetragAn(inFranken(m_GeldBetrag));  
	  
	  return false;
    
  }

  /**
   * Bewirkt den Auswurf des Restbetrages.
   */
  public void gibWechselGeld() {
    
	  if (pruefeRetourGeld(0)) {
		  for (int i = 0; i < NR_MUENZSAEULE; i++) {
			  fuelleKasse(inFranken(arrMuenzen[i]), -m_RetourMuenzen[i]);
			  fuelleKasseBestaetigung();
		  }
		  SystemSoftware.auswerfenWechselGeld(inFranken(m_GeldBetrag));
		  reduziereGeldbetrag(m_GeldBetrag);
	  } else {
		  System.out.println("*** Failure Kasse.gibWechselGeld");
	  }
  }

  /**
   * Gibt den Gesamtbetrag der bisher verkauften Waren zurÃ¼ck. <br>
   * Analyse: Abgeleitetes Attribut.
   * 
   * @return Gesamtbetrag der bisher verkauften Waren.
   */
  public double gibBetragVerkaufteWaren() {
	  int retVal = 0;
	  for (int iter = 0; iter < m_TransaktionList.size(); iter++) {
    	retVal += m_TransaktionList.get(iter).getPreis();
	  }
	  return inFranken(retVal);
  }
  
  /**
   * Aktualisiert die Statistik nach jedem verkauften Objekt
   * Design: Wird in einer ArrayList untergebracht
   * 
   * @param pWarenname Name der verkauften Ware
   * @param pPreis Preis der verkauften Ware
   * @param pVerkaufsdatum Verkaufsdatum der verkauften Ware
   */
  public void aktualisiereStatistik(String pWarenname, int pPreis, Date pVerkaufsdatum) {
	  Transaktion neueTransaktion = new Transaktion(pWarenname, pPreis, pVerkaufsdatum);
	  m_TransaktionList.add(neueTransaktion);
  }

  /**
   * Retourniert die Anzahl einer bestimmten verkauften Waren seit
   * einem bestimmten Datum (>=) 
   * @param pName Wie lautet der Warenname
   * @param pDatum Das Datum seit welchem gesucht werden soll.
   * @return Die Anzahl verkaufter Waren
   */
  public int gibAnzahlVerkaufteWarenSeit(String pName, Date pDatum) {
	  int retVal = 0;
	  for (int iter = 0; iter < m_TransaktionList.size(); iter++) {
		  if (	pName.equals(m_TransaktionList.get(iter).getName())
			 && !pDatum.after(m_TransaktionList.get(iter).getVerkaufsDatum())) {
			  retVal++;
		  }
	  }
	  return retVal;
  }
  
  /**
   * Vergleicht den Preis der gewünschten Ware mit dem 
   * eingeworfenen Geldbetrag
   * @param pPreis
   * @return true für genügend Geld
   * 		 false falls zu wenig Geld eingeworfen wurde.
   */
  public boolean genuegendGeld(int pPreis) {
	  if (pPreis <= m_GeldBetrag) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  /**
   * Berechnet die Münzen für eine Wechselgeld Rückgabe und 
   * bestimmt, ob genügend Münzen vorhanden sind.
   * @param pPreis
   * @return true für genügend Wechselgeld
   * 		 false falls Wechselgeld nicht möglich
   * 
   * 		 Füllt ein Integer array mit Anzahl Münzen, die
   * 	 	 retourniert werden müssen.
   */
  public boolean pruefeRetourGeld(int pPreis) {	  

	  for (int i = 0; i < NR_MUENZSAEULE; i++) {
		  m_RetourMuenzen[i] = 0;
	  }
	  int rueckGeld = m_GeldBetrag - pPreis;
	  System.out.println("*** kasse.pruefeRetourGeld: " + rueckGeld);
	  
	  for (int i = 0; i < NR_MUENZSAEULE; i++) {
		  int tmpMuenzBestand = m_Muenzsaeule[i].getMuenzBestand();
		  while (	 (rueckGeld / arrMuenzen[i]) > 0
				  && (tmpMuenzBestand > 0)			) {
			  m_RetourMuenzen[i]++;
			  rueckGeld -= arrMuenzen[i];
			  tmpMuenzBestand--;
		  }
	  }
	  
	  if (rueckGeld == 0) {
		  return true;
	  } else {
		  return false;
	  }
  }
  
  /**
   * Reduziert den eingeworfenen Geldbetrag, nach einem
   * Kauf oder der Rückgabe des Wechselgeldes
   *
   * Ausgabe des aktuellen Betrags in der SystemSoftware
   * 
   * @param pPreis
   */
  public void reduziereGeldbetrag(int pPreis) {
	  m_GeldBetrag -= pPreis;
	  SystemSoftware.zeigeBetragAn(m_GeldBetrag/100.0);
  }

  /**
   * Berechnet einen übergebenen Betrag in Franken in Rappen um.
   * @param pWert
   * @return Betrag in Rappen
   */
  public int inRappen(double pWert) {
	  int rappenWert = (int) Math.round(pWert * 100);
	  return rappenWert;
  }
  
  /** 
   * Berechnet einen übergebenen Betrag in Rappen in Franken um.
   * @param pWert
   * @return Betrag in Franken
   */
  public double inFranken(int pWert) {
	  double frankenWert = pWert / 100.0;
	  return frankenWert;
  }
}
