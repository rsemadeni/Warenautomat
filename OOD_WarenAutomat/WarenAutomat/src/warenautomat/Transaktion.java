package warenautomat;

import java.util.Date;


/**
 * @author RSemadeni
 * @version 1.0
 * @created 24-Mai-2014 09:03:16
 */
public class Transaktion {

	private String m_Name;
	private int m_Preis;
	private Date m_VerkaufsDatum;

	/**
	 * Constructor
	 * 
	 * @param pName Warenname
	 * @param pPreis Warenpreis
	 * @param pVerkaufsdatum Verkaufsdatum
	 */
	public Transaktion(String pName, int pPreis, Date pVerkaufsdatum){
		m_Name = pName;
		m_Preis = pPreis;
		m_VerkaufsDatum = pVerkaufsdatum;
		System.out.println("*** Transaktion(): " + m_Name + " " + m_Preis + " " + m_VerkaufsDatum);
	}

	/**
	 * Retourniert den Warenname der Transaktion.
	 * 
	 * @return Warenname
	 */
	public String getName() {
		return m_Name;
	}
	
	/** 
	 * Retourniert den Warenpreis der Transaktion
	 * 
	 * @return Warenpreis in Rappen
	 */
	public int getPreis() {
		return m_Preis;
	}
	
	/** 
	 * Retourniert das Verkaufsdatum der Transaktion
	 * 
	 * @return Verkaufsdatum
	 */
	public Date getVerkaufsDatum() {
		return m_VerkaufsDatum;
	}
}