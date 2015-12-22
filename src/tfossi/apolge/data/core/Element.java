/**
 * Element.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import static tfossi.apolge.common.constants.ConstValue.LFCR;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.ArrayIndexInnerBoundsException;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Zu einem Element mit Attributen zusammengefasster spezieller Datensatz
 * 
 * @author tfossi
 * @version 26.10.2015
 * @modified -
 * @since Java 1.6
 */
public class Element {

	// ---- Ordnungsdaten der Elemente ----------------------------------------
	
	/** Übergeordnetes parent-Element */
	private Element parent;

	/** Untergeordnete child-Element(e) */
	private final Map<String,List<Element>> childs = new HashMap<String, List<Element>>();

	public final Element getParent() {
		return this.parent;
	}

	public final void addParent(Element newParent) {
		parent = newParent;
		parent.addChild(this.elementBuilder.name, this);
	}

	public final void delParent() {
		parent.delChild(this.elementBuilder.name, this);
		parent = null;
	}

	public final void movParent(Element newParent) {
		parent.delParent();
		this.addParent(newParent);
	}

	private final void setChildBuilder(String builderName){
		this.childs.put(builderName, new ArrayList<Element>());
	}
	
	public final List<Element> getAllChild(String builderName){
		return this.childs.get(builderName);
	}
	
	public final void addChild(String builderName, Element child) {
		this.childs.get(builderName).add(child);
	}

	public final void delChild(String builderName, Element child) {
		this.childs.get(builderName).remove(child);
	}

	private final _ElementBuilder elementBuilder;

	// ---- Datenfelder -------------------------------------------------------
	
	// Erste Dimension: AType-Ordnungsnummer des speziellen Typen 
	
	private final int[] intArr;
//	private final long[] lngtArr;
//	private final float[] fltArr;
	private final double[] dblArr; // = new double[4][];		// uvwy 
	private final boolean[] bolArr;
	private final String[] strArr;
	private final VP_Tokenlist[] vtlArr;
	private final Object[] objArr;
	
	
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
	// Index (Y,W,U,E) : Reglerfelder: Stellgröße, Regelgröße, Störung,
	// Abweichung
	// _AType id :
	// n : 0...n historische Daten (für Regler)
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
	 /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	 @Override
	 public String toString() {
	 String rc = new String("");
	 if (this.parent != null)
	 rc += "Parent: "+this.parent.toString() + LFCR;
	 else
	 rc += (this.elementBuilder==null?"Element":this.elementBuilder.toString());
//	 rc += "ID: " + this.id + LFCR;
//	 rc += "Subs:" + this.subID + LFCR;
//	
//	 if(this.elementBuilder!=null){
//	 for(int i = 0; i < intArr.length; i++){
//		 rc+= this.elementBuilder.getIntAttrName(i)+"= "+intArr[i]+LFCR;
//	 }
//	 for(int i = 0; i < dblArr.length; i++){
//		 rc+= this.elementBuilder.getDblAttrName(i)+"= "+dblArr[i]+LFCR;		 
//	 }
//	 for(int i = 0; i < bolArr.length; i++){
//		 rc+= this.elementBuilder.getBolAttrName(i)+"= "+bolArr[i]+LFCR;
//	 }
//	 for(int i = 0; i < strArr.length; i++){
//		 rc+= this.elementBuilder.getStrAttrName(i)+"= "+strArr[i]+LFCR;
//	 }
//	 for(int i = 0; i < vtlArr.length; i++){
//		 rc+= this.elementBuilder.getVtlAttrName(i)+"= "+vtlArr[i]+LFCR;
//	 }
//	 for(int i = 0; i < objArr.length; i++){
//		 rc+= this.elementBuilder.getObjAttrName(i)+"= "+objArr[i]+LFCR;
//	 }
//	 for (String key : this.e.keySet()) {
//	 rc += key + ": " + this.e.get(key);
//	 }
//	 }
	 return rc;
	 }
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(Element.class
			.getPackage().getName() + ".Element");


	public Element() {
		this.parent = null;
		this.elementBuilder = null;
		
	
		this.vtlArr = null;
		
		this.intArr = null;
		this.dblArr = null;
		this.bolArr = null;
		this.strArr = null;
	
		this.objArr = null;
	}
	public Element(Element p, _ElementBuilder eb) {
		this.parent = p;
		this.elementBuilder = eb;

		for (_ElementBuilder childEB: eb._ElementBuilderMap.values()){
			this.setChildBuilder(childEB.name);
		}
		
		
		logger.trace(eb.atypeRegister.toString());
		
		eb.insertElementInADR(this);
		
	// Muss zuerst kommen, da nachher bei Pass2 aus den Anweisungen Werte geworden sind.
		 VP_Tokenlist[] v = null;
		try {
			v = eb.initVtlAttributes();
			
		} catch (ArrayIndexInnerBoundsException e) {
			
			e.printStackTrace();
			System.exit(0);
		}
		this.vtlArr = v; 
		logger.trace(eb.atypeRegister.toString());
		
		this.intArr = eb.initIntAttributes();
		this.dblArr = eb.initDblAttributes();
		this.bolArr = eb.initBolAttributes();
		this.strArr = eb.initStrAttributes();
	
		this.objArr = eb.initObjAttributes();
	
	}

	public Object getAttributValue(String attribut) {
		System.err.println(this.elementBuilder.atypeRegister.get(attribut));
		return 12;
	}

}
