/**
 * Element.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Zu einem Element mit Attributen zusammengefasster spezieller Datensatz
 * 
 * @author tfossi
 * @version 26.10.2015
 * @modified -
 * @since Java 1.6
 */
public class Element {
	// /** Übergeordnetes parent-Element */
	// final Element parent;
	//
	// // /** Untergeordnete child-Element(e) */
	// // final List<Element>childs;
	//
	// /** id */
	// final int id;
	//
	// /** Typenbeschreibung vom _ElementBuilder */
	// final _ElementBuilder type;

	// // ---- Geber: Datenfelder
	// ------------------------------------------------
	//
	// /**
	// * Array mit allen aktiven Sollwertgebern nach grpnr und vtxnr
	// */
	// _Geber[][] _gActive;
	// /**
	// * Array mit allen passiven Sollwertgebern nach grpnr und vtxnr
	// */
	// _Geber[][] _gPassive;
	//
	// ---- Values: Datenfelder -----------------------------------------------
	//
	// Index (Y,W,U,E)	: Reglerfelder: Stellgröße, Regelgröße, Störung,
	// Abweichung
	// _AType id 		:		 
	// n 				: 0...n historische Daten (für Regler)
	// double[y,w,u,e][stg][igl][n] digits;
	// double[][][][] digits;
	//
	// System.err.println("Digit      : ");
	// System.err.println("       yuwe : " + this.digits.length);
	// for (int yuwei = 0; yuwei < this.digits.length; yuwei++) {
	// System.err.println("       st : " + this.digits[yuwei].length);
	// for (int sti = 0; sti < this.digits[yuwei].length; sti++) {
	// System.err.println("      sti : "
	// + this.digits[yuwei][sti].length);
	// for (int idxi = 0; idxi < this.digits[sti].length; idxi++) {
	// System.err.println("     idxi : "
	// + this.digits[yuwei][sti][idxi].length);
	// System.err.print("       ki : ");
	// for (int ki = 0; ki < this.digits[sti][idxi].length; ki++) {
	// System.err.print(ki + ": "
	// + this.digits[yuwei][sti][idxi][ki] + TAB);
	// }
	// System.err.println();
	// }
	// }
	// }


	// Einfache Daten
	// []1: ElementID
	// []1: uvwy
	// []0: ObjectID
	// []2: ATypeID
	
	int[][][] intArr;
	double[][][] doubleArr;  //+[uvwy]
	boolean[][] boolArr;
	String[][][] strArr;
	Object[][][] oArr;

	
	/**
	 * TODO Comment
	 * @param elementID
	 * @param typeID
	 * @param objectID 
	 * @param value
	 * @return Object Nummer
	 * @modified - 
	 */
	public int createAttribute(final int elementID, final int typeID, final Integer value){
		int objectID = checkInt(elementID, typeID);		
		createAttribute(elementID, typeID, objectID, value.intValue());
		return objectID;
	}
	
	private final int checkInt(final int elementID, final int typeID ){
		if(this.intArr==null) return 0;
		if(this.intArr.length<= elementID) return 0;
		if(this.intArr[elementID]== null) return 0;
		if(this.intArr[elementID].length<= typeID) return 0;
		
		for(int objectID=0; objectID<this.intArr[elementID].length;objectID++){
			if(this.intArr[elementID][objectID]== null) return objectID;
		}	
		return this.intArr[elementID].length;
	}
	public void createAttribute(final int elementID, final int typeID, final int objectID, final int value){
		
		// Erste Dimension um die Elemente auf nötige Anzahl + Reserve bringen 
		if(this.intArr == null){
			this.intArr = new int[elementID+11][][];	
		}else if(this.intArr.length<elementID+11){
			this.intArr = Arrays.copyOf(this.intArr, elementID+1);		
		}
		
		// Die zweite Dimension der Objects, die angelegt werden sollen
		if(this.intArr[elementID] == null){
			this.intArr[elementID] = new int[objectID+1][];
		}else if(this.intArr[elementID].length<objectID+1){
			this.intArr[elementID] = Arrays.copyOf(this.intArr[elementID], objectID+1);	
		}
		
		// Die dritte Dimension der ATypen auf nötige Anzahl + Reserve bringen
		if(this.intArr[elementID][objectID] == null){
			this.intArr[elementID][objectID] = new int[typeID+3];	
		}else if(this.intArr[elementID][objectID].length<typeID+3){
			this.intArr[elementID][objectID] = Arrays.copyOf(this.intArr[elementID][objectID], typeID+3);	
		}
		this.intArr[elementID][objectID][typeID] = value;
		return;
	}
	
	public int createAttribute(final int elementID, final int typeID, final Double value){
		int objectID = checkDbl(elementID, typeID);		
		createAttribute(elementID, typeID, objectID, value.doubleValue());
		return objectID;		
	}

	private final int checkDbl(final int elementID, final int typeID ){
		if(this.doubleArr==null) return 0;
		if(this.doubleArr.length<= elementID) return 0;
		if(this.doubleArr[elementID]== null) return 0;
		if(this.doubleArr[elementID].length<= typeID) return 0;
		
		for(int objectID=0; objectID<this.doubleArr[elementID].length;objectID++){
			if(this.doubleArr[elementID][objectID]== null) return objectID;
		}	
		return this.doubleArr[elementID].length;
	}
	
	public int createAttribute(final int elementID, final int typeID, final int objectID, final double value){
		
		int id = objectID;  
		// Erste Dimension um die Elemente auf nötige Anzahl + Reserve bringen 
		if(this.doubleArr == null){
			this.doubleArr = new double[elementID+11][][];	
		}else if(this.doubleArr.length<elementID+11){
			this.doubleArr = Arrays.copyOf(this.doubleArr, elementID+1);		
		}
		
		// Die zweite Dimension der ATypen auf nötige Anzahl + Reserve bringen
		if(this.doubleArr[elementID] == null){
			this.doubleArr[elementID] = new double[typeID+3][];
		}else if(this.doubleArr[elementID].length<typeID+1){
			this.doubleArr[elementID] = Arrays.copyOf(this.doubleArr[elementID], typeID+1);	
		}
		
		// Die dritte Dimension der Objects, die angelegt werden sollen
		if(this.doubleArr[elementID][typeID] == null){
			this.doubleArr[elementID][typeID] = new double[1];	
			id = 0;
		}else if(this.doubleArr[elementID][typeID].length<objectID+1){
			this.doubleArr[elementID][typeID] = Arrays.copyOf(this.doubleArr[elementID][typeID], objectID+1);	
		}
		this.doubleArr[elementID][typeID][id] = value;
		return id;
	}
	
	public int createAttribute(final int elementID, final int typeID, final String value){
		
		int objectID = checkInt(elementID, typeID);		
		
		int id = objectID;  
		// Erste Dimension um die Elemente auf nötige Anzahl + Reserve bringen 
		if(this.strArr == null){
			this.strArr = new String[elementID+11][][];	
		}else if(this.strArr.length<elementID+11){
			this.strArr = Arrays.copyOf(this.strArr, elementID+1);		
		}
		
		// Die zweite Dimension der ATypen auf nötige Anzahl + Reserve bringen
		if(this.strArr[elementID] == null){
			this.strArr[elementID] = new String[typeID+3][];
		}else if(this.strArr[elementID].length<typeID+1){
			this.strArr[elementID] = Arrays.copyOf(this.strArr[elementID], typeID+1);	
		}
		
		// Die dritte Dimension der Objects, die angelegt werden sollen
		if(this.strArr[elementID][typeID] == null){
			this.strArr[elementID][typeID] = new String[1];	
			id = 0;
		}else if(this.strArr[elementID][typeID].length<objectID+1){
			this.strArr[elementID][typeID] = Arrays.copyOf(this.strArr[elementID][typeID], objectID+1);	
		}
		try{
		this.strArr[elementID][typeID][id] = value;
		}catch(ArrayIndexOutOfBoundsException e){

			logger.trace("Adresse: "+elementID+"."+typeID+"."+id+"= " + value+"  "+objectID);
			assert false;
		}
		return id;
	}

	private final int checkStr(final int elementID, final int typeID ){
		if(this.strArr==null) return 0;
		if(this.strArr.length<= elementID) return 0;
		if(this.strArr[elementID]== null) return 0;
		if(this.strArr[elementID].length<= typeID) return 0;
		
		for(int objectID=0; objectID<this.strArr[elementID].length;objectID++){
			if(this.strArr[elementID][objectID]== null) return objectID;
		}	
		return this.strArr[elementID].length;
	}
	
	
public int createAttribute(final int elementID, final int typeID, final Object value){
	int objectID = checkO(elementID, typeID);	
		
		int id = objectID;  
		// Erste Dimension um die Elemente auf nötige Anzahl + Reserve bringen 
		if(this.oArr == null){
			this.oArr = new Object[elementID+11][][];	
		}else if(this.oArr.length<elementID+11){
			this.oArr = Arrays.copyOf(this.oArr, elementID+1);		
		}
		
		// Die zweite Dimension der ATypen auf nötige Anzahl + Reserve bringen
		if(this.oArr[elementID] == null){
			this.oArr[elementID] = new Object[typeID+3][];
		}else if(this.oArr[elementID].length<typeID+1){
			this.oArr[elementID] = Arrays.copyOf(this.oArr[elementID], typeID+1);	
		}
		
		// Die dritte Dimension der Objects, die angelegt werden sollen
		if(this.oArr[elementID][typeID] == null){
			this.oArr[elementID][typeID] = new Object[1];	
			id = 0;
		}else if(this.oArr[elementID][typeID].length<objectID+1){
			this.oArr[elementID][typeID] = Arrays.copyOf(this.oArr[elementID][typeID], objectID+1);	
		}
		this.oArr[elementID][typeID][id] = value;
		return id;
	}

private final int checkO(final int elementID, final int typeID ){
	if(this.oArr==null) return 0;
	if(this.oArr.length<= elementID) return 0;
	if(this.oArr[elementID]== null) return 0;
	if(this.oArr[elementID].length<= typeID) return 0;
	
	for(int objectID=0; objectID<this.oArr[elementID].length;objectID++){
		if(this.oArr[elementID][objectID]== null) return objectID;
	}	
	return this.oArr[elementID].length;
}
	//
	// // ---- ActiveFlag: Datenfelder
	// -------------------------------------------
	//
	// /** Stategroup, IGL */
	// boolean[][] activeVertex;
	//
	// // ---- ActiveFlag: Context -------------------------------------------
	//
	// /** Aktiver Context */
	// int[] activeContext;

	// // FIXME 21.02.2014 Testweise herausgenommen
	// // System.err.println("Trigger    : "+triggerliste);
	// System.err.println("Active _G  : " + this._gActive);
	// System.err.println("Passive _G : " + this._gPassive);
	// System.err.println("Active V   : " + this.activeVertex);
	// System.err.println("Active C   : " + this.activeContext);

	//
	// /** Datencontainer eines Elements */
	// private Map<String, _Atom<?>> e = new HashMap<String, _Atom<?>>();
	// /** e1 */
	// private Map<String, VP_Tokenlist<Object>> e1 = new HashMap<String,
	// VP_Tokenlist<Object>>();
	//
	// /**
	// * @return Liste der Eigenschaftsnamen
	// * @modified -
	// */
	// public Set<String> getAtomnamen() {
	// return this.e.keySet();
	// }
	//
	// /**
	// * Trägt eine Elementeigenschaft ein
	// *
	// * @param atomname
	// * Name der Eigenschaft
	// * @param valuetokens
	// * Wert(eliste) der Eigenschaft
	// * @modified -
	// */
	// public void put(String atomname, VP_Tokenlist<Object> valuetokens) {
	// this.e1.put(atomname, valuetokens);
	//
	// }
	//
	// /**
	// * @param Atomname
	// * Name der Eigenschaft
	// * @return Datentyp der Eigenschaft
	// * @modified -
	// */
	// _AType<?> getType(String Atomname) {
	// return this.e.get(Atomname).type;
	// }
	//
	// /**
	// * @param Atomname
	// * Name der Eigenschaft
	// * @return Daten der Eigenschaft
	// * @modified -
	// */
	// Object getValue(String Atomname) {
	// return this.e.get(Atomname).value;
	// }
	//
	// /**
	// * @param Atomname
	// * Name der Eigenschaft
	// * @return Atom
	// * @modified -
	// */
	// _Atom<?> getAtom(String Atomname) {
	// return this.e.get(Atomname);
	// }
	//
	// /**
	// * Abfrage, ob eine Eigenschaft existiert
	// *
	// * @param string
	// * Name der Eigenschaft
	// * @return <i>true</i> Eigenschaft ist vorhanden
	// * @modified -
	// */
	// public boolean hasAtom(String string) {
	// return this.e.containsKey(string);
	// }
	//

	//
	// /**
	// * Element anlegen
	// *
	// * @param p
	// * Parent-Element
	// * @modified -
	// */
	// public Element(Element p) {
	// this.parent = p;
	// if (p == null)
	// this.id = 1;
	// else
	// this.id = p.getID();
	// }
	//
	// /** subID */
	// private int subID = 0;
	//
	// /**
	// * @return liefert nächste ID-Nummer und aktualisiert sie +1
	// * @modified -
	// */
	// int getID() {
	// return this.subID++;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see java.lang.Object#toString()
	// */
	// @Override
	// public String toString() {
	// String rc = new String("Parent: ");
	// if (this.parent != null)
	// rc += this.e.toString() + LFCR;
	// else
	// rc += "-" + LFCR;
	// rc += "ID: " + this.id + LFCR;
	// rc += "Subs:" + this.subID + LFCR;
	//
	// for (String key : this.e.keySet()) {
	// rc += key + ": " + this.e.get(key);
	// }
	//
	// return rc;
	// }
// ---- Selbstverwaltung --------------------------------------------------
/** serialVersionUID */
@SuppressWarnings("unused")
private final static long serialVersionUID = VERSION;

/** logger */
private final static Logger logger = Logger.getLogger(Element.class
		.getPackage().getName() + ".Element");



}
