package warenautomat;

import java.util.Date;


/**
 * @author RSemadeni
 * @version 1.0
 * @created 20-Mai-2014 19:20:34
 */
public class Ware {

	private String m_Name;
	private int m_Preis;
	private Date m_VerfallsDatum;

	/**
	 * Constructor: Initialize a specific Product.
	 * 
	 * @param pName
	 * @param pPreis
	 * @param pVerfallsdatum
	 */
	public Ware(String pName, int pPreis, Date pVerfallsdatum){
		m_Name = pName;
		m_Preis = pPreis;
		m_VerfallsDatum = pVerfallsdatum;
	}

	/** 
	 * Retourniert den Warenname.
	 * 
	 * @return Warenname
	 */
	public String getName() {
		return m_Name;
	}
	
	/** 
	 * Retourniert das Verfallsdatum
	 * 
	 * @return Verfallsdatum
	 */
	public Date getVerfallsDatum() {
		return m_VerfallsDatum;
	}
	
	/** 
	 * Retourniert den Warenpreis
	 * 
	 * @return Warenpreis
	 */
	public int getPreis() {
		return m_Preis;
	}
}