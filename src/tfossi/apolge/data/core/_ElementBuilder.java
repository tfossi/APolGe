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
	// /** children */
	// private final Map<String, Table> firstPassMap = new HashMap<String,
	// Table>();
	/** children */
	public final Map<String, _ElementBuilder> _ElementBuilderMap = new HashMap<String, _ElementBuilder>();

	/** attributes */
	// private Table attributes = null;

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

	@SuppressWarnings("javadoc")
	enum nogo {
		CHILDS, XRAY;
	}

	/** nogolst */
	private final static// List<nogo>
	String nogolst = Arrays.asList(nogo.values()).toString();

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

					System.err.println("     2-Pass: " + ebl.isTwoPass());
					System.err.println("     3-Pass: " + ebl.isThreePass());
					System.err.println("        ebl: " + ebl.toString());
					ebl.clrTwoPass();
				}
				
				System.err.println("Anzahl subs: " + eb.attributes.getClass());
				// Anzahl der Subs?
				System.err.println("Anzahl subs: "
						+ LoadScript.getIntValue(eb.attributes, "count"));
				for (int nr = 0; nr < LoadScript.getIntValue(eb.attributes,
						"count"); nr++)
					eb.create(e, ebCounter);
			}
		}

		return e;
	}

	/**
	 * Bestandteile des Elements
	 * 
	 * @author tfossi
	 * @version 26.11.2015
	 * @modified -
	 * @since Java 1.6
	 */
	enum scpt {
		/** ELEMENT */
		ELEMENT, /** EIGENSCHAFT */
		EIGENSCHAFT, /** DATENTYP */
		DATENTYP, /** INITIALDATEN */
		INITIALDATEN, /** INITIALPARAMETER */
		INITIALPARAMETER;

	}

	/**
	 * TODO Comment
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

		// this.nogolst = Arrays.asList(nogo.values());

		this.name = name;
		this.parent = parent;
		this.path = path;
		this.attributes = attributes;

		// Object childs = this.attributes.get("CHILDS");
		Object childs = block.get("CHILDS");
		if (childs != null) {
			Table childtable = ((VP_Tokenlist<?>) childs).getTable();
			for (String childname : childtable.keySet()) {
				// this.firstPassMap.put(childname, ((VP_Tokenlist<?>)
				// childtable
				// .get(childname)).getTable());
				System.err.println(childname);
				try {
					System.err.println(LoadScript.getObjectValue(childtable,
							childname));
				} catch (ArrayIndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NullPointerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LoadScript ls;
				try {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		// for (String key : this.attributes.keySet()) {
		for (String key : block.keySet()) {
			if (nogolst.contains(key))
				continue;

			Object o = null;
			try {
				o = LoadScript.getObjectValue(block, key);
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			@SuppressWarnings("null")
			String clzzname = o.getClass().getSimpleName();
			if (clzzname.equals("Integer")) {
				this.atypeRegister.put(key, new N_AType<Integer>(key,
						(Integer) o));
			} else if (clzzname.equals("Long")) {
				this.atypeRegister.put(key, new N_AType<Long>(key, (Long) o));
			} else if (clzzname.equals("Float")) {
				this.atypeRegister.put(key, new N_AType<Float>(key, (Float) o));
			} else if (clzzname.equals("Double")) {
				this.atypeRegister.put(key,
						new N_AType<Double>(key, (Double) o));
			} else if (clzzname.equals("String")) {
				this.atypeRegister.put(key,
						new T_AType<String>(key, (String) o));
			} else if (clzzname.equals("ArrayList")) {
				this.atypeRegister.put(key, new A_AType<ArrayList>(key,
						(ArrayList) o));
			} else
				assert false : key + LFCR + clzzname + LFCR + o.toString();
			// _AType t = new _AType(key,block.keySet());
		}

	}

	/*
	 * String name = "root"; String parent = null; LoadScript ls = new
	 * LoadScript(TESTPATH + this.createElements[row][1], null,true);
	 * 
	 * _ElementBuilder eb = new _ElementBuilder(name, parent, ls.getTable());
	 */
	// /**
	// * Füge eine 0-Pass-Eigenschaft hinzu
	// * @param key
	// * Name der Eigenschaft
	// * @param valuetokens
	// * Wert der Eigenschaft
	// * @modified -
	// */
	// public void addEigenschaften(final String key, final VP_Tokenlist<?>
	// valuetokens){
	// if(LOGGER)
	// logger.info("Register "+key);
	// this.firstPassMap.put(key, valuetokens);
	//
	// }
	// public final Object getAttribut(final String name) {
	// try {
	// return LoadScript.getObjectValue(this.attributes, name);
	// } catch (ArrayIndexOutOfBoundsException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NullPointerException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NoSuchFieldException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return null;
	// }

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
		// for (int i = 0; i < iI.length; i++)
		// rc += nI[i] + "= " + iI[i] + LFCR;
		// for (int i = 0; i < iL.length; i++)
		// rc += nL[i] + "= " + iL[i] + LFCR;
		// for (int i = 0; i < iF.length; i++)
		// rc += nF[i] + "= " + iF[i] + LFCR;
		// for (int i = 0; i < iD.length; i++)
		// rc += nD[i] + "= " + iD[i] + LFCR;

		// assert false:nI[1];

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
