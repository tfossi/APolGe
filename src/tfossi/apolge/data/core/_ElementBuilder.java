/**
 * _ElementBuilder.java
 * branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.LoadScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.t.TableMap;
import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.common.scripting.vp.VP_Transfer;

/**
 * Enthält einen Elementbauplan und erstellt Elemente<br>
 * FIXME Testvariante für Parsertests
 * 
 * @author tfossi
 * @version 29.10.2015
 * @modified -
 * @since Java 1.6
 */
public class _ElementBuilder {

	/** atypeRegister */
	public final Map<String, _AType<?>> atypeRegister = new HashMap<String, _AType<?>>();
	
	/** children */
	public final Map<String, _ElementBuilder> _ElementBuilderMap = new HashMap<String, _ElementBuilder>();

	/** Name des Elementbuilders */
	private String name;

	/** parent */
	private _ElementBuilder parent;

	/** path */
	@SuppressWarnings("unused")
	private String path;

	/** id */
	public int id = 0;
	
	/** attributes */
	private TableMap attributes;

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
	private final static String nogolst = Arrays.asList(nogo.values()).toString();

	/**
	 * TODO Comment
	 * @param p TODO
	 * @param ebCounter TODO
	 * @return TODO
	 * @modified - 
	 */
	public Element create(Element p, _ElementBuilder ebCounter) {
		Element e = new Element(p, ebCounter.id++);
		
		System.out.println(LFCR + "#" + ebCounter.id + " Anlage: " + this.name);
		System.out.println(this.toString());
		for (String key : this.atypeRegister.keySet()) {
			System.out.println("Lege an: " + key);

		}

		for (String ebname : this._ElementBuilderMap.keySet()) {
			_ElementBuilder eb = this._ElementBuilderMap.get(ebname);

			for (String key : eb.attributes.keySet()) {
				@SuppressWarnings("unchecked")
				VP_Tokenlist<Object> ebl = (VP_Tokenlist<Object>) eb.attributes
						.get(key);
				if (ebl.isTwoPass()) {
					logger.fatal("ACHTUNG: 2-PASS-Rechenschritt!"
							+ LFCR
							+ "--------------------------------------------------------"
							+ LFCR + LFCR);
					new VP_Transfer().transfer(key, eb.attributes, ebl,
							(List<String>) null, (byte) 1);

//					System.err.println("     2-Pass: " + ebl.isTwoPass());
//					System.err.println("     3-Pass: " + ebl.isThreePass());
//					System.err.println("        ebl: " + ebl.toString());
					ebl.clrTwoPass();
				}
				
//				System.err.println("Anzahl subs: " + eb.attributes.getClass());
//				// Anzahl der Subs?
//				System.err.println("Anzahl subs: "
//						+ LoadScript.getIntValue(eb.attributes, "count"));
				for (int nr = 0; nr < LoadScript.getIntValue(eb.attributes,
						"count"); nr++)
					eb.create(e, ebCounter);
			}
		}

		return e;
	}

	/**
	 * Gegen einfachen Zugriff sperren
	 * @modified -
	 */
	@SuppressWarnings("unused")
	private _ElementBuilder() {
//
	}

	/**
	 * TODO Comment
	 * @param name TODO
	 * @param attributes TODO
	 * @param parent TODO
	 * @param block TODO
	 * @param path TODO
	 * @modified -
	 */
	@SuppressWarnings("rawtypes")
	public _ElementBuilder(final String name, final TableMap attributes,
			final _ElementBuilder parent, final Table block, final String path) {

		this.name = name;
		this.parent = parent;
		this.path = path;
		this.attributes = attributes;

		

		Object childs = block.get("CHILDS");
		if (childs != null) {
			// Lege die Childs an.
			Table childtable = ((VP_Tokenlist<?>) childs).getTable();
			
			// Alle Einträge durchgehen
			for (String childname : childtable.keySet()) {
				
				LoadScript ls;
				try {
					// Child-APO-Script im selben Pfad unter Childnamen!
					ls = new LoadScript(path + childname, null, true);
					this._ElementBuilderMap.put(
							childname,
							new _ElementBuilder(childname,
									(TableMap) LoadScript.getObjectValue(
											childtable, childname), this, ls
											.getTable(), path));
				} catch (LoadScriptException e) {
					e.printStackTrace();
					assert false;
				} catch (ParseException e) {
					e.printStackTrace();
					assert false;
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
					assert false;
				} catch (NullPointerException e) {
					e.printStackTrace();
					assert false;
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
					assert false;
				}
			}
			// Alle Childs sind angelegt!
		}

		// Alle Attribute des Elements anlegen!
		for (String attrName : block.keySet()) {
			
			// Gesperrte Anweisungen ignorieren
			if (nogolst.contains(attrName))
				continue;

			Object o = null;
			try {
				o = LoadScript.getObjectValue(block, attrName);
			} catch (ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				assert false;
			} catch (NullPointerException e) {
				e.printStackTrace();
				assert false;
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
				assert false;
			}
			
			@SuppressWarnings("null")
			String clzzname = o.getClass().getSimpleName();
			if (clzzname.equals("Integer")) {
				this.atypeRegister.put(attrName, new N_AType<Integer>(attrName,
						(Integer) o));
				
			} else if (clzzname.equals("Long")) {
				this.atypeRegister.put(attrName, new N_AType<Long>(attrName, (Long) o));
				
			} else if (clzzname.equals("Float")) {
				this.atypeRegister.put(attrName, new N_AType<Float>(attrName, (Float) o));
				
			} else if (clzzname.equals("Double")) {
				this.atypeRegister.put(attrName,
						new N_AType<Double>(attrName, (Double) o));
				
			} else if (clzzname.equals("Boolean")) {
				this.atypeRegister.put(attrName,
						new B_AType<Boolean>(attrName, (Boolean) o));
				
			} else if (clzzname.equals("String")) {
				this.atypeRegister.put(attrName,
						new T_AType<String>(attrName, (String) o));
				
			} else if (clzzname.equals("ArrayList")) {
				this.atypeRegister.put(attrName, new A_AType<ArrayList>(attrName,
						(ArrayList) o));
				
			} else
				assert false : attrName + LFCR + clzzname + LFCR + o.toString();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String rc = new String("Elementname: "
				+ this.name
				+ LFCR
				+ (this.parent == null ? "" : "Parentname: " + this.parent.name
						+ LFCR));

		for (String key : this.atypeRegister.keySet()) {
			_AType<?> t = this.atypeRegister.get(key);
			rc += key + ": " + t.name + "/" + t.value + "/"
					+ t.value.getClass().getSimpleName() + LFCR;
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
