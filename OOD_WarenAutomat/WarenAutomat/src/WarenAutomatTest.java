//==============================================================================
// Project   : Master of Advanced Studies in Software-Engineering 2014
// Modul     : Projektarbeit OO Softwareentwicklung "Warenautomat"
//             Teil: Design&Implementation
// Title     : Test-Applikation
// Author    : `Ihr Name`
// Tab-Width : 2
/*///===========================================================================

* Description:
Test-Applikation um die Klassen-Bibliothek des Waren-Automaten zu testen.

CVS: $Revision: 1.9 $  $Date: 2014/05/17 18:10:15 $ 
/*///===========================================================================
//       1         2         3         4         5         6         7         8
//345678901234567890123456789012345678901234567890123456789012345678901234567890
//==============================================================================

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import warenautomat.*;

public class WarenAutomatTest { 

  public static void main(String[] args) throws ParseException {

    DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMAN);

    { 
      Automat automat = new Automat();
      Kasse kasse = automat.gibKasse();
      SystemSoftware.erzeugeGUI(automat);
    
      System.out.println("\n=== Kasse füllen-Test: =====================================");
      System.out.println("*** kasse.fuelleKasse(2.00,1)");
      kasse.fuelleKasse(2.00, 10);
      System.out.println("*** kasse.fuelleKasse(1.00, 1)");
      kasse.fuelleKasse(1.00, 10);
      System.out.println("*** kasse.fuelleKasse(0.50, 1)");
      kasse.fuelleKasse(0.50, 10);
      System.out.println("*** kasse.fuelleKasse(0.20, 1)");
      kasse.fuelleKasse(0.20, 10);
      System.out.println("*** kasse.fuelleKasse(0.10, 100)");
      kasse.fuelleKasse(0.10, 100);
      System.out.println("*** kasse.fuelleKasse(0.10, 10)");
      kasse.fuelleKasse(0.10, 10);
      
      System.out.println("*** kasse.fuelleKasseBestatigung()");
      kasse.fuelleKasseBestaetigung();
          
      System.out.println("\n=== Drehteller füllen"
      		+ "-Test: =====================================");
      SystemSoftware.output(false);
      automat.fuelleFach(1, "Mars", 1.50, df.parse("01.01.2010"));
      automat.fuelleFach(2, "Snickers", 1.80, df.parse("01.01.2200"));
      automat.fuelleFach(3, "MilkyWay", 1.20, df.parse("01.01.2300"));
      automat.fuelleFach(4, "Ragusa", 2.00, df.parse("01.01.2004"));
      automat.drehen();
      automat.fuelleFach(1, "Mars", 1.50, df.parse("01.01.2005"));
      automat.fuelleFach(2, "Snickers", 1.80, df.parse("01.01.2200"));
      automat.fuelleFach(3, "MilkyWay", 1.20, df.parse("01.01.2300"));
      automat.fuelleFach(4, "Ragusa", 2.00, df.parse("01.01.2024"));
      automat.drehen();
      automat.fuelleFach(1, "Mars", 1.50, df.parse("01.01.2015"));
      automat.fuelleFach(2, "Snickers", 1.80, df.parse("01.01.2200"));
      automat.fuelleFach(3, "MilkyWay", 1.20, df.parse("01.01.2300"));
      automat.fuelleFach(4, "Ragusa", 2.00, df.parse("01.01.2024"));
      automat.drehen();
      automat.fuelleFach(1, "Mars", 1.50, df.parse("01.01.2015"));
      automat.fuelleFach(2, "Snickers", 1.80, df.parse("01.01.2200"));
      automat.fuelleFach(3, "MilkyWay", 1.20, df.parse("01.01.2300"));
      automat.fuelleFach(4, "Ragusa", 2.00, df.parse("01.01.2024"));
      SystemSoftware.output(true);
      
      
      System.out.println("\n=== Total Warenwert-Test: =====================================");
      System.out.println("*** Automat.gibTotalenWarenWert() = " 
              + automat.gibTotalenWarenWert());
      
      System.out.println("\n=== Einnehmen-Test: =====================================");
      System.out.println("*** kasse.einnehmen(2.00):");
      kasse.einnehmen(2.00);
      System.out.println("*** kasse.einnehmen(1.00):");
      kasse.einnehmen(1.00);
      System.out.println("*** kasse.einnehmen(0.50):");
      kasse.einnehmen(0.50);
      System.out.println("*** kasse.einnehmen(0.20):");
      kasse.einnehmen(0.20);
      System.out.println("*** kasse.einnehmen(0.10):");
      kasse.einnehmen(0.10);
      
      System.out.println("\n=== Drehteller-Test. =================================");
      System.out.println("*** Drehen bis Fach Nr.1 vor der Öffnung ist:");
      SystemSoftware.output(false);
      for (int i = 4; i <= 16; i++) {
    	  automat.drehen();
      }
      SystemSoftware.output(true);
      
      System.out.println("\n=== Öffnen-Test: =====================================");
      System.out.println("*** automat.oeffnen(1):");
      System.out.println("*** automat.oeffnen(1): " + automat.oeffnen(1));
      System.out.println("*** automat.oeffnen(2):");
      System.out.println("*** automat.oeffnen(2): " + automat.oeffnen(2));
      System.out.println("*** automat.oeffnen(3):");
      System.out.println("*** automat.oeffnen(3): " + automat.oeffnen(3));
      
      System.out.println("\n=== Refill-Test: =====================================");
      automat.fuelleFach(1, "Mars 2.0", 1.50, df.parse("01.01.2016"));
      automat.fuelleFach(2, "Snickers 2.0", 1.80, df.parse("01.01.2200"));
      automat.fuelleFach(3, "MilkyWay 2.0", 1.20, df.parse("01.01.2300"));
      automat.fuelleFach(4, "Ragusa 2.0", 2.00, df.parse("01.01.2024"));
      
      automat.fuelleFach(1, "Mars 3.0", 1.50, df.parse("01.04.2014"));
      
      System.out.println("*** Drehen bis Fach Nr.2 vor der Öffnung ist:");
      SystemSoftware.output(false);
      automat.drehen();
      SystemSoftware.output(true);
      System.out.println("*** automat.oeffnen(1):");
      System.out.println("*** automat.oeffnen(1): " + automat.oeffnen(1));
      System.out.println("*** automat.oeffnen(2):");
      System.out.println("*** automat.oeffnen(2): " + automat.oeffnen(2));
      
      System.out.println("\n=== GibWechselgeld-Test: =====================================");
      kasse.gibWechselGeld();
      
      System.out.println("\n=== Total Warenwert-Test: =====================================");
      System.out.println("*** Automat.gibTotalenWarenWert() = " 
              + automat.gibTotalenWarenWert());
      
      System.out.println("\n=== Verkaufte Waren-Test: =====================================");
      System.out.println("*** Verkaufte Waren: " + kasse.gibBetragVerkaufteWaren());

      System.out.println("\n=== gibVerkaufsStatistik-Test: =====================================");
      System.out.println("*** Anzahl \"MilkyWay\" seit 25.05.2014: "
    		  + automat.gibVerkaufsStatistik("MilkyWay", df.parse("25.05.2014")));
    }
    
  }

}


/* Session-Log:

=== Drehteller-Test: =================================
*** Drehteller Nr.1 mit einem "Eins" fÃ¼llen:
SystemSoftware::zeigeWarenPreisAn():  1: 1.0
SystemSoftware::zeigeVerfallsDatum(): 1: 2
*** automat.drehen():
SystemSoftware::zeigeWarenPreisAn():  1: 0.0
SystemSoftware::zeigeVerfallsDatum(): 1: 0
SystemSoftware::zeigeWarenPreisAn():  2: 0.0
SystemSoftware::zeigeVerfallsDatum(): 2: 0
SystemSoftware::zeigeWarenPreisAn():  3: 0.0
SystemSoftware::zeigeVerfallsDatum(): 3: 0
SystemSoftware::zeigeWarenPreisAn():  4: 0.0
SystemSoftware::zeigeVerfallsDatum(): 4: 0
SystemSoftware::zeigeWarenPreisAn():  5: 0.0
SystemSoftware::zeigeVerfallsDatum(): 5: 0
SystemSoftware::zeigeWarenPreisAn():  6: 0.0
SystemSoftware::zeigeVerfallsDatum(): 6: 0
SystemSoftware::zeigeWarenPreisAn():  7: 0.0
SystemSoftware::zeigeVerfallsDatum(): 7: 0
*** Drehteller Nr.2 mit einem "Zwei" fÃ¼llen:
SystemSoftware::zeigeWarenPreisAn():  2: 2.0
SystemSoftware::zeigeVerfallsDatum(): 2: 2
SystemSoftware::output(): false
*** automat.drehen():
SystemSoftware::output(): true
*** Drehteller Nr.3 mit einem "Drei" fÃ¼llen:
SystemSoftware::zeigeWarenPreisAn():  3: 3.0
SystemSoftware::zeigeVerfallsDatum(): 3: 2
SystemSoftware::output(): false
*** Drehen bis Fach Nr.16 vor der Ã–ffnung ist:
SystemSoftware::output(): true
*** automat.drehen(): jetzt Fach Nr. 1:
SystemSoftware::zeigeWarenPreisAn():  1: 1.0
SystemSoftware::zeigeVerfallsDatum(): 1: 2
SystemSoftware::zeigeWarenPreisAn():  2: 0.0
SystemSoftware::zeigeVerfallsDatum(): 2: 0
SystemSoftware::zeigeWarenPreisAn():  3: 0.0
SystemSoftware::zeigeVerfallsDatum(): 3: 0
SystemSoftware::zeigeWarenPreisAn():  4: 0.0
SystemSoftware::zeigeVerfallsDatum(): 4: 0
SystemSoftware::zeigeWarenPreisAn():  5: 0.0
SystemSoftware::zeigeVerfallsDatum(): 5: 0
SystemSoftware::zeigeWarenPreisAn():  6: 0.0
SystemSoftware::zeigeVerfallsDatum(): 6: 0
SystemSoftware::zeigeWarenPreisAn():  7: 0.0
SystemSoftware::zeigeVerfallsDatum(): 7: 0
*** automat.drehen(): jetzt Fach Nr. 2:
SystemSoftware::zeigeWarenPreisAn():  1: 0.0
SystemSoftware::zeigeVerfallsDatum(): 1: 0
SystemSoftware::zeigeWarenPreisAn():  2: 2.0
SystemSoftware::zeigeVerfallsDatum(): 2: 2
SystemSoftware::zeigeWarenPreisAn():  3: 0.0
SystemSoftware::zeigeVerfallsDatum(): 3: 0
SystemSoftware::zeigeWarenPreisAn():  4: 0.0
SystemSoftware::zeigeVerfallsDatum(): 4: 0
SystemSoftware::zeigeWarenPreisAn():  5: 0.0
SystemSoftware::zeigeVerfallsDatum(): 5: 0
SystemSoftware::zeigeWarenPreisAn():  6: 0.0
SystemSoftware::zeigeVerfallsDatum(): 6: 0
SystemSoftware::zeigeWarenPreisAn():  7: 0.0
SystemSoftware::zeigeVerfallsDatum(): 7: 0
*** automat.drehen(): jetzt Fach Nr. 3:
SystemSoftware::zeigeWarenPreisAn():  1: 0.0
SystemSoftware::zeigeVerfallsDatum(): 1: 0
SystemSoftware::zeigeWarenPreisAn():  2: 0.0
SystemSoftware::zeigeVerfallsDatum(): 2: 0
SystemSoftware::zeigeWarenPreisAn():  3: 3.0
SystemSoftware::zeigeVerfallsDatum(): 3: 2
SystemSoftware::zeigeWarenPreisAn():  4: 0.0
SystemSoftware::zeigeVerfallsDatum(): 4: 0
SystemSoftware::zeigeWarenPreisAn():  5: 0.0
SystemSoftware::zeigeVerfallsDatum(): 5: 0
SystemSoftware::zeigeWarenPreisAn():  6: 0.0
SystemSoftware::zeigeVerfallsDatum(): 6: 0
SystemSoftware::zeigeWarenPreisAn():  7: 0.0
SystemSoftware::zeigeVerfallsDatum(): 7: 0
=== Drehteller-Test. =================================
=== Ã–ffnen-Test: =====================================
*** Drehteller Nr.5 mit einem Mars fÃ¼llen:
SystemSoftware::zeigeWarenPreisAn():  5: 1.5
SystemSoftware::zeigeVerfallsDatum(): 5: 1
*** Automat.gibTotalenWarenWert() = 1.5
*** kasse.einnehmen(1.00):
SystemSoftware::zeigeBetragAn(): 1.0
*** kasse.einnehmen(0.50):
SystemSoftware::zeigeBetragAn(): 1.5
*** automat.oeffnen(5):
Drehteller::oeffnen(): mDrehtellerNr = 5 / mFachVorOeffnung = 3
SystemSoftware::zeigeBetragAn(): 0.0
SystemSoftware::entriegeln(): 5
SystemSoftware::zeigeWarenPreisAn():  5: 0.0
SystemSoftware::zeigeVerfallsDatum(): 5: 0
*** automat.oeffnen(5): true
=== Ã–ffnen-Test. =====================================

*/
