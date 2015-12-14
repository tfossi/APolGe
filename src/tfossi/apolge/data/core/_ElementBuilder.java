/**
 * _ElementBuilder.java
 * branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.ArrayIndexInnerBoundsException;
import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.LoadScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.t.TableMap;
import tfossi.apolge.common.scripting.vp.VP_ArrayTokenlist;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.common.scripting.vp.VP_Transfer;
import tfossi.apolge.common.scripting.vp.pm.FuncPType;
import tfossi.apolge.data.core.attribute.B_AType;
import tfossi.apolge.data.core.attribute.N_AType;
import tfossi.apolge.data.core.attribute.O_AType;
import tfossi.apolge.data.core.attribute.T_AType;
import tfossi.apolge.data.core.attribute._AType;

/**
 * Enthält alle Elementbaupläne und erstellt Elemente<br>
 * FIXME Testvariante für Parsertests
 * 
 * @author tfossi
 * @version 29.10.2015
 * @modified -
 * @since Java 1.6
 */
public class _ElementBuilder {

	/**
	 * Zähler für Anzahl der ElementBuilder<br>
	 * Der genutzte Zähler liegt nur im Root-ElementBuilder
	 */
	private int idCounter = 0;

	/**
	 * Liste der Steuerelement
	 * 
	 * @author tfossi
	 * @version 29.11.2015
	 * @modified -
	 * @since Java 1.6
	 */
	enum nogo {
		/** CHILDS */
		CHILDS;
	}

	/** Steuerelemente als Liste */
	private final static String nogolst = Arrays.asList(nogo.values())
			.toString();

	// ---- Konkrete Objecte erstellen ------------------------------------

	/** Zentraler Datencontainer */
	final private List<Element> elements = new ArrayList<Element>();

	public void insertElementInADR(Element element) {

		for(_AType<?> at: this.atypeRegister.values()){
			if (at.value.getClass().equals(TableMap.class)) {

				// Daten in Array einstellen

				Table attable = (Table) at.value;
				logger.trace(at.name+": "+attable.toString());
				
				VP_Tokenlist<Object> atvtl = LoadScript.getVP_List(attable, at.name);
				
				for(int i = 0; i < atvtl.size(); i++){
					
					if(atvtl.get(i).toString().contains("f:=ADR2")){
						FuncPType fpt = (FuncPType) atvtl.get(i);
						fpt.values[0] = element;
						
						System.err.println(fpt.values[0]);	
						
					}
					System.err.println(atvtl.get(i).toString());
						
					
				}
				
//				
//				// Prüfe, ob Eintrag noch aufgelöst werden muss
//				if (!atvtl.isThreePass()) {
//					new VP_Transfer().transfer(null, attable, atvtl,
//							(List<String>) null, (byte) 1);
//
//					atvtl.clrTwoPass();
//					this.addTypeZuordnung(key, atvtl.getValue());
//					
////					this.atypeRegister.remove(key);						
//					change=true;
//					break;
//				}else{
//					// Ist Pass3 und soll nicht aufgel�st werden!
//					arr[at.ordinal] = (VP_Tokenlist<?>) at.value;
//				}
			}
		}
		assert false;
	}
	
	/**
	 * Erzeugt alle initialen Element-Objecte
	 * 
	 * @param e
	 *            Der Datenspeicher
	 * @modified -
	 */
	public final void createElement(Element parent) {

		// Elements
		// x count (eb)
		// x subELement(parent)
		// x count (eb)
		// x subsubElement(parent)

		// Ein Element dieses Builders,das mit den Attributen gefüllt werden
		// soll
		// Element element = new Element(parent, this);
		//
		// // Ist cntrlData nicht definiert, dann erzeuge nur ein Object
		if (this.cntrlData == null) {
			logger.trace("Generiere genau 1 " + this.name
					+ "-Element mit der ElementBuilderID #"
					+ this.elementBuilderID);
			Element element = new Element(parent, this);
			elements.add(element);
			// Gehe die Child-Elementbuilder durch
			for (_ElementBuilder eb : this._ElementBuilderMap.values()) {
				eb.createElement(element);
			}
			// initAttributes(element);
			//
		} else {
			// Gehe alle cntrlData-Einträge durch
			for (String key : this.cntrlData.keySet()) {
				VP_Tokenlist<Object> ebl = LoadScript.getVP_List(
						this.cntrlData, key);
				// Prüfe, ob Eintrag noch aufgelöst werden muss
				if (ebl.isTwoPass()) {
					new VP_Transfer().transfer(key, this.cntrlData, ebl,
							(List<String>) null, (byte) 1);
					ebl.clrTwoPass();
					logger.trace("2-Pass-Result:"
							+ LoadScript.getIntValue(this.cntrlData, key));
				}

				logger.trace("Generiere "
						+ +LoadScript.getIntValue(this.cntrlData, "count")
						+ " " + this.name
						+ "-Elemente mit der ElementBuilderID #"
						+ this.elementBuilderID);
				for (int objectID = 0; objectID < LoadScript.getIntValue(
						this.cntrlData, "count"); objectID++) {
					// createElement(parent);
					Element element = new Element(parent, this);
					elements.add(element);
					// Gehe die Child-Elementbuilder durch
					for (_ElementBuilder eb : this._ElementBuilderMap.values()) {
						eb.createElement(element);
					}
				}
			}
		}
	}

	/**
	 * Ein konkrete Object bauen
	 * 
	 * @param e
	 *            Der Datenspeicher
	 * @param objectID
	 * @modified -
	 */
	private void createObject(Element e) {

		int objectID = -1;

		for (_AType<?> at : this.atypeRegister.values()) {

			// if( at.value.getClass().equals(Integer.class))
			// objectID = e.createAttribute(this.elementBuilderID, at.ordinal,
			// (Integer)at.value);
			// else if( at.value.getClass().equals(Double.class))
			// objectID = e.createAttribute(this.elementBuilderID, at.ordinal,
			// (Double)at.value);
			// else if( at.value.getClass().equals(String.class))
			// objectID = e.createAttribute(this.elementBuilderID, at.ordinal,
			// (String)at.value);
			// else
			// objectID = e.createAttribute(this.elementBuilderID, at.ordinal,
			// at.value);

			logger.trace("Gebaut: " + this.elementBuilderID + "/" + at.ordinal
					+ "/" + at.name + "/" + objectID + "/" + at.value);

		}

	}

	public String elementToString() {
		String rc = new String();
		for (Element e : elements)
			rc += e.toString() + LFCR;
		return rc;
	}

	/**
	 * Ein konkrete Object bauen Nummer Element/AType/ObjectID Vorlage???
	 * 
	 * @param e
	 *            Der Datenspeicher
	 * @param objectID
	 * @modified -
	 */
	final int[] initIntAttributes() {
		int maxOrdinal = 0;
		
		logger.trace(this.atypeRegister.toString());
		
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Integer.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				maxOrdinal = Math.max(maxOrdinal, at.ordinal);
				logger.trace(key + "/" + this.atypeRegister.get(key).value
						+ "/" + at.ordinal);
			}
		}

		int[] arr = new int[maxOrdinal + 1];

		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Integer.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				arr[at.ordinal] = ((Integer) at.value).intValue();
			}
		}
		return arr;
	}

	final double[] initDblAttributes() {
		int maxOrdinal = 0;
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Double.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				maxOrdinal = Math.max(maxOrdinal, at.ordinal);
				logger.trace(key + "/" + this.atypeRegister.get(key).value
						+ "/" + at.ordinal);
			}
		}

		double[] arr = new double[maxOrdinal + 1];

		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Double.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				arr[at.ordinal] = ((Double) at.value).doubleValue();
			}
		}
		return arr;
	}

	final boolean[] initBolAttributes() {
		int maxOrdinal = 0;
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Boolean.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				maxOrdinal = Math.max(maxOrdinal, at.ordinal);
				logger.trace(key + "/" + this.atypeRegister.get(key).value
						+ "/" + at.ordinal);
			}
		}

		boolean[] arr = new boolean[maxOrdinal + 1];

		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Boolean.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				arr[at.ordinal] = ((Boolean) at.value).booleanValue();
			}
		}
		return arr;
	}

	final String[] initStrAttributes() {
		int maxOrdinal = 0;
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					String.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				maxOrdinal = Math.max(maxOrdinal, at.ordinal);
				logger.trace(key + "/" + this.atypeRegister.get(key).value
						+ "/" + at.ordinal);
			}
		}

		String[] arr = new String[maxOrdinal + 1];

		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					String.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				arr[at.ordinal] = (String) at.value;
			}
		}
		return arr;
	}

	final VP_Tokenlist<?>[] initVtlAttributes() throws ArrayIndexInnerBoundsException {

		boolean change = false;
		// Array reservieren
		VP_Tokenlist<?>[] arr = null;
		
		do {
			change = false;
			// Bedarf Arraygr��e feststellen
			int maxOrdinal = 0;
			for (String key : this.atypeRegister.keySet()) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.value.getClass().equals(TableMap.class)) { // VP_ArrayTokenlist.class)){
					if (LOGGER)
						logger.trace("[" + key
								+ "] ist unaufgel�stes VP_Tokenline #"
								+ at.ordinal);
					maxOrdinal = Math.max(maxOrdinal, at.ordinal);
					logger.trace(key + "/" + this.atypeRegister.get(key).value
							+ "/" + at.ordinal);
				}
			}

			// Array reservieren XXX ungeschickt....
			arr = new VP_ArrayTokenlist[maxOrdinal + 1];

			for (String key : this.atypeRegister.keySet()) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.value.getClass().equals(TableMap.class)) { // VP_ArrayTokenlist.class)){

					// Daten in Array einstellen

					Table attable = (Table) at.value;
					VP_Tokenlist<Object> atvtl = LoadScript.getVP_List(attable,
							key);
					
					// Prüfe, ob Eintrag noch aufgelöst werden muss
					if (!atvtl.isThreePass()) {
						new VP_Transfer().transfer(null, attable, atvtl,
								(List<String>) null, (byte) 1);

						atvtl.clrTwoPass();
						this.addTypeZuordnung(key, atvtl.getValue());
						
//						this.atypeRegister.remove(key);						
						change=true;
						break;
					}else{
						// Ist Pass3 und soll nicht aufgel�st werden!
						arr[at.ordinal] = (VP_Tokenlist<?>) at.value;
					}
				}
			}
		} while (change);
		return arr;
	}

	final Object[] initObjAttributes() {
		int maxOrdinal = 0;
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Object.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				maxOrdinal = Math.max(maxOrdinal, at.ordinal);
				logger.trace(key + "/" + this.atypeRegister.get(key).value
						+ "/" + at.ordinal);
			}
		}

		Object[] arr = new Object[maxOrdinal + 1];

		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Object.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				arr[at.ordinal] = at.value;
			}
		}
		return arr;
	}

	final String getIntAttrName(int ordinal) {
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Integer.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.ordinal == ordinal)
					return at.name;
			}
		}
		return null;
	}

	final String getDblAttrName(int ordinal) {
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Double.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.ordinal == ordinal)
					return at.name;
			}
		}
		return null;
	}

	final String getBolAttrName(int ordinal) {
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Boolean.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.ordinal == ordinal)
					return at.name;
			}
		}
		return null;
	}

	final String getStrAttrName(int ordinal) {
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					String.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.ordinal == ordinal)
					return at.name;
			}
		}
		return null;
	}

	final String getVtlAttrName(int ordinal) {
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					VP_ArrayTokenlist.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.ordinal == ordinal)
					return at.name;
			}
		}
		return null;
	}

	final String getObjAttrName(int ordinal) {
		for (String key : this.atypeRegister.keySet()) {
			if (this.atypeRegister.get(key).value.getClass().equals(
					Object.class)) {
				_AType<?> at = this.atypeRegister.get(key);
				if (at.ordinal == ordinal)
					return at.name;
			}
		}
		return null;
	}

	// ---- Elemenbuilder erstellen -------------------------------------------

	/** Enthält die child-ElementBuilder */
	private final Map<String, _ElementBuilder> _ElementBuilderMap = new HashMap<String, _ElementBuilder>();

	/** Enthält die ID-Nummer des ElementBuilders */
	private final int elementBuilderID;

	/** Enthält den Namen des ElementBuilders */
	private final String name;

	/** Pfad zur APO-Script des ElementBuilders */
	private final String path;

	/** Steuerdaten zur Elementerzeugung */
	private final TableMap cntrlData;

	/**
	 * Erstelle einen neuen ElementBuilder<br>
	 * this.simpleTestdaten[row][0], null,null, ls.getTable(), TESTPATH name,
	 * (TableMap)null, parent, ls.getTable(), TESTPATH
	 * 
	 * childname,LoadScript.getObjectValue(childtable, childname),
	 * (root==null?this:root), ls.getTable(), path TODO Comment
	 * 
	 * @param name
	 *            Name des Element
	 * @param attributes
	 *            TODO
	 * @param root
	 *            oberstes Element
	 * @param block
	 *            APO-TabelleneintrÃ€ge des aktuellen Elements
	 * @param path
	 *            Pfad zu den APO-Scripten (fÃŒr die untergeordneten Elemente)
	 * @modified -
	 */
	public _ElementBuilder(final String name, final TableMap cntrlData,
			final _ElementBuilder root, final Table block, final String path) {

		// Name des ElementBuilders
		this.name = name;

		// Pfad zum APO-Script
		this.path = path;

		// Steuerdaten zur Erzeugung der Childs und deren Scalierung
		this.cntrlData = cntrlData;

		// ElementID erzeugen und den Zähler initieren bzw. hochzählen
		if (root == null) {
			this.elementBuilderID = 0;
			this.idCounter = 0;
		} else {
			this.elementBuilderID = ++root.idCounter;
		}

		// Childs erzeugen
		createChilds(block, root);

		// Attribute für die Elemente dieses ElementBuilders anlegen
		createAttributes(block);

	}

	// ---- Elementattributbeschreibung erstellen -----------------------------

	/** Register für alle Attribute dieses Elements */
	public final Map<String, _AType<?>> atypeRegister = new HashMap<String, _AType<?>>();

	
	private void createAttributes(final Table block) {

		// Alle Attribute des Elements anlegen!
		for (String attrName : block.keySet()) {

			logger.trace("Die Typenbeschreibung für " + attrName + " in "
					+ this.name + " anlegen!");

			// Gesperrte Anweisungen ignorieren
			if (nogolst.contains(attrName))
				continue;

			// Lade Object aus Table
			Object o = null;
			// VP_Tokenlist<?> vtl = null;
			Table vtl = null;
			try {
				o = LoadScript.getObjectValue(block, attrName);

				this.addTypeZuordnung(attrName, o);

				// // Füge Object in Typenregister ein
				// if (o.getClass().equals(Integer.class))
				// addType(attrName, (Integer) o);
				// else if (o.getClass().equals(Double.class))
				// addType(attrName, (Double) o);
				// else if (o.getClass().equals(String.class))
				// addType(attrName, (String) o);
				// else if (o.getClass().equals(VP_ArrayTokenlist.class))
				// addType(attrName, (VP_Tokenlist<?>) o);
				// else
				// addType(attrName, o);

			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				assert false;
			} catch (NullPointerException e) {
				e.printStackTrace();
				assert false;
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				assert false;
			} catch (ArrayIndexInnerBoundsException e) {
				o = null;
				vtl = block; // LoadScript.getVP_List(block, attrName);
			}

			logger.trace(attrName + ": " + o + NTAB + vtl + "/"
					+ (vtl != null ? vtl.getClass() : "") + NTAB + o + "/"
					+ (o != null ? o.getClass() : ""));
			// Füge Object in Typenregister ein
			if (o != null) {
				addType(attrName, o);
			} else if (vtl != null) {
				try {
					addTypeZuordnung(attrName, vtl);
				} catch (ArrayIndexInnerBoundsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
			} else
				assert false;
		}
		logger.trace("Die Typenbeschreibung in " + this.name
				+ " sind angelegt!");
	}

	private final void addTypeZuordnung(final String attrName, final Object o) throws ArrayIndexInnerBoundsException {
		
		logger.trace(attrName+": "+o+" / "+o.getClass());
		
		// Füge Object in Typenregister ein
		if (o.getClass().equals(Integer.class))
			addType(attrName, (Integer) o);
		else if (o.getClass().equals(Double.class))
			addType(attrName, (Double) o);
		else if (o.getClass().equals(String.class))
			addType(attrName, (String) o);
		else if (o.getClass().equals(VP_ArrayTokenlist.class))
			addType(attrName, (VP_Tokenlist<?>) o);
		else if (o.getClass().equals(FuncPType.class))
			throw new ArrayIndexInnerBoundsException("Ist Funktion");
		else
			addType(attrName, o);
	}

	private void addType(String attrName, Integer value) {
		int typeID = 0;
		for (_AType<?> at : this.atypeRegister.values()) {
			logger.trace(this.atypeRegister.toString()+NTAB+
					at.name+": "+at.value.getClass()+" <-> "+value.getClass());
			
			if (at.value.getClass().equals(value.getClass()))
				++typeID;
		}
		this.atypeRegister.put(attrName, new N_AType<Integer>(attrName, value,
				typeID));

		logger.trace(attrName+" Integer ordinal-check: " + this.atypeRegister.get(attrName).ordinal+NTAB+this.atypeRegister.toString());
	}

	private void addType(String attrName, Double value) {
		int typeID = 0;
		for (_AType<?> at : this.atypeRegister.values()) {
			if (at.value.getClass().equals(value.getClass()))
				++typeID;
		}
		this.atypeRegister.put(attrName, new N_AType<Double>(attrName, value,
				typeID));
		logger.trace("check: " + this.atypeRegister.get(attrName).ordinal);
	}

	private void addType(String attrName, Boolean value) {
		int typeID = 0;
		for (_AType<?> at : this.atypeRegister.values()) {
			if (at.value.getClass().equals(value.getClass()))
				++typeID;
		}
		this.atypeRegister.put(attrName, new B_AType<Boolean>(attrName, value,
				typeID));
		logger.trace("check: " + this.atypeRegister.get(attrName).ordinal);
	}

	private void addType(String attrName, String value) {
		int typeID = 0;
		for (_AType<?> at : this.atypeRegister.values()) {
			if (at.value.getClass().equals(value.getClass()))
				++typeID;
		}
		this.atypeRegister.put(attrName, new T_AType<String>(attrName, value,
				typeID));
		logger.trace("check: " + this.atypeRegister.get(attrName).ordinal);
	}

	// private void addType(String attrName, VP_Tokenlist<Object> value) {
	private void addType(String attrName, Table value) {
		int typeID = 0;
		for (_AType<?> at : this.atypeRegister.values()) {
			if (at.value.getClass().equals(value.getClass()))
				++typeID;
		}
		// this.atypeRegister.put(attrName, new
		// O_AType<VP_Tokenlist<Object>>(attrName,
		// value, typeID));
		this.atypeRegister.put(attrName, new O_AType<Table>(attrName, value,
				typeID));
		logger.trace("check: " + this.atypeRegister.get(attrName).ordinal);
	}

	private void addType(String attrName, Object value) {
		int typeID = 0;
		for (_AType<?> at : this.atypeRegister.values()) {
			if (at.value.getClass().equals(value.getClass()))
				++typeID;
		}
		this.atypeRegister.put(attrName, new O_AType<Object>(attrName, value,
				typeID));
		logger.trace("check: " + this.atypeRegister.get(attrName).ordinal);
	}

	// ---- Child-ElementBuilder erstellen ------------------------------------

	/**
	 * TODO Comment
	 * 
	 * @param block
	 * @param root
	 * @modified -
	 */
	private void createChilds(final Table block, final _ElementBuilder root) {

		Object childs = block.get("CHILDS");
		// Childs erzeugen
		if (childs != null) {
			// Lege die Childs an.
			Table childtable = ((VP_Tokenlist<?>) childs).getTable();

			// Alle Einträge durchgehen
			for (String childname : childtable.keySet()) {

				LoadScript ls;
				try {
					// Child-APO-Script im selben Pfad unter Childnamen!
					ls = new LoadScript(this.path + childname, null, true);
					this._ElementBuilderMap.put(
							childname,
							new _ElementBuilder(childname,
									(TableMap) LoadScript.getObjectValue(
											childtable, childname),
									(root == null ? this : root),
									ls.getTable(), this.path));
				} catch (LoadScriptException ex) {
					ex.printStackTrace();
					assert false;
				} catch (ParseException ex) {
					ex.printStackTrace();
					assert false;
				} catch (ArrayIndexOutOfBoundsException ex) {
					ex.printStackTrace();
					assert false;
				} catch (NullPointerException ex) {
					ex.printStackTrace();
					assert false;
				} catch (NoSuchFieldException ex) {
					ex.printStackTrace();
					assert false;
				} catch (ArrayIndexInnerBoundsException e) {
					e.printStackTrace();
					assert false;
				}
			}
			// Alle Childs sind angelegt!
		}
	}

	public void createCntrlData() {

		for (String key : this.cntrlData.keySet()) {
			@SuppressWarnings("unchecked")
			VP_Tokenlist<Object> ebl = (VP_Tokenlist<Object>) this.cntrlData
					.get(key);
			if (ebl.isTwoPass()) {
				logger.fatal("ACHTUNG: 2-PASS-Rechenschritt!"
						+ LFCR
						+ "--------------------------------------------------------"
						+ LFCR + LFCR);
				new VP_Transfer().transfer(key, this.cntrlData, ebl,
						(List<String>) null, (byte) 1);

				System.err.println("     2-Pass: " + ebl.isTwoPass());
				System.err.println("     3-Pass: " + ebl.isThreePass());
				System.err.println("        ebl: " + ebl.toString());
				ebl.clrTwoPass();
			}

			System.err.println("Anzahl subs: " + this.cntrlData.getClass());
			// Anzahl der Subs?
			System.err.println("Anzahl subs: "
					+ LoadScript.getIntValue(this.cntrlData, "count"));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String rc = new String("ElementBuildername: " + this.name + "("
				+ this.elementBuilderID + ")" + LFCR);

		for (String key : this.atypeRegister.keySet()) {
			_AType<?> t = this.atypeRegister.get(key);
			rc += key + ": " + t.name + "/" + t.ordinal + "/" + t.value + "/"
					+ t.value.getClass().getSimpleName() + LFCR;
		}

		for (_ElementBuilder eb : this._ElementBuilderMap.values()) {
			rc += LFCR + eb.toString();
		}
		return rc;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;

	/** logger */
	private final static Logger logger = Logger.getLogger(_ElementBuilder.class
			.getPackage().getName() + "._ElementBuilder");



}
