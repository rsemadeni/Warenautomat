package warenautomat;


/**
 * @author RSemadeni
 * @version 1.0
 * @created 20-Mai-2014 19:20:21
 */
public class Fach {

	public Ware m_Ware;

	/** 
	 * Constructor: Initialisiert das Fach leer (null).
	 */
	public Fach(){
		m_Ware = null;
	}

	/** 
	 * Prüft ob das Fach leer ist.
	 * 
	 * @return true, wenn das Fach leer ist, false, falls nicht
	 */
	public Boolean istLeer(){
		if (m_Ware == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gibt die aktuelle Ware aus dem Fach zurück
	 * 
	 * @return aktuelle Ware
	 */
	public Ware getWare() {
		if (istLeer()) {
			return null;
//			return new Ware("---", 0, new Date(1900, 01, 01));
		} else {
			return m_Ware;
		}
	}
	
	public void setWare(Ware pWare) {
		m_Ware = pWare;
	}
}