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
import tfossi.apolge.data.core.attribute.A_AType;
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

	/** atypeRegister */
	public final Map<String, _AType<?>> atypeRegister = new HashMap<String, _AType<?>>();
	
	/** children */
	public final Map<String, _ElementBuilder> _ElementBuilderMap = new HashMap<String, _ElementBuilder>();

	/** Name des Elementbuilders */
	private String name;

	/** parent */
	private _ElementBuilder parent;

	public final int elementID;
	
	public int idCounter=0;
	
	/** path */
	@SuppressWarnings("unused")
	private String path;

	/** cntrlData zur Elementerzeugung */
	private TableMap cntrlData;

	
	
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

	public final void createRoot(Element e){
		this.create(e,1);
	}
	/**
	 * Erzeuge initial die Elemente 
	 * @param p TODO
	 * @param ebCounter TODO
	 * @return TODO
	 * @modified - 
	 */
	private final void create(Element e, int count) {
		
		
		
//	}
		
//		Element e = new Element(p, ebCounter.id++, this);
//		
//		System.out.println(LFCR + "#" + ebCounter.id + " Anlage: " + this.name);
//		System.out.println(this.toString());
//		for (String key : this.atypeRegister.keySet()) {
//			System.out.println("Lege an: " + key);
//
//		}
//
//		for (String ebname : this._ElementBuilderMap.keySet()) {
//			_ElementBuilder eb = this._ElementBuilderMap.get(ebname);
		
		createObject(e, count);
		for(Object o: this.cntrlData.values()){

			System.out.println("--_"+o.toString());

		}	


		
	}

	private void createObject(Element e, int count){
		for(int nr = 0; nr < count; nr++){
		int objectID = -1;
		for(_AType at: this.atypeRegister.values()){

			if( at.value.getClass().equals(Integer.class))
				objectID = e.createAttribute(elementID, at.ordinal, objectID, (Integer)at.value);
			else if( at.value.getClass().equals(Double.class))
				objectID = e.createAttribute(elementID, at.ordinal, objectID, (Double)at.value);
			else if( at.value.getClass().equals(String.class))
				objectID = e.createAttribute(elementID, at.ordinal, objectID, (String)at.value);
			else
				objectID = e.createAttribute(elementID, at.ordinal, objectID, at.value);
			
			System.out.println(this.elementID+"/"+at.ordinal+"/"+at.name+"/"+objectID+"/"+at.value);
			
		}
		}
	}
	/**
	 * this.simpleTestdaten[row][0], null,null,  ls.getTable(), TESTPATH
	 * name, (TableMap)null, parent, ls.getTable(), TESTPATH
	 * 
	 * childname,LoadScript.getObjectValue(childtable, childname), (root==null?this:root), ls.getTable(), path
	 * TODO Comment
	 * @param name Name des Element
	 * @param attributes TODO
	 * @param root oberstes Element
	 * @param block APO-Tabelleneinträge des aktuellen Elements
	 * @param path Pfad zu den APO-Scripten (für die untergeordneten Elemente)
	 * @modified -
	 */
	@SuppressWarnings("rawtypes")
	public _ElementBuilder(final String name, final TableMap cntrlData,
			final _ElementBuilder root, final Table block, final String path) {
		
		this.name = name;
		this.parent = parent;
		this.path = path;
		this.cntrlData = cntrlData;
		
		if(root==null){
			this.elementID=0;
			this.idCounter=0;
		}else{
			this.elementID = ++root.idCounter;
		}
			

		

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
											childtable, childname), (root==null?this:root), ls
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
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(Integer.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName, new N_AType<Integer>(attrName,
						(Integer) o, ordinal));
				
			} else if (clzzname.equals("Long")) {
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(Long.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName, new N_AType<Long>(attrName, (Long) o, ordinal));
				
			} else if (clzzname.equals("Float")) {
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(Float.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName, new N_AType<Float>(attrName, (Float) o, ordinal));
				
			} else if (clzzname.equals("Double")) {
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(Double.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName,
						new N_AType<Double>(attrName, (Double) o, ordinal));
				
			} else if (clzzname.equals("Boolean")) {
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(Boolean.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName,
						new B_AType<Boolean>(attrName, (Boolean) o, ordinal));
				
			} else if (clzzname.equals("String")) {
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(String.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName,
						new T_AType<String>(attrName, (String) o, ordinal));
				
			} else if (clzzname.equals("ArrayList")) {
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(ArrayList.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName, new A_AType<ArrayList>(attrName,
						(ArrayList) o, ordinal));
				
				
			} else if (clzzname.equals("Object")) {
				int ordinal = 0;
				for(_AType<?> c : this.atypeRegister.values()){					
					if(c.value.getClass().equals(Object.class))ordinal++;
				}
				
				this.atypeRegister.put(attrName, new O_AType<Object>(attrName,
						o, ordinal));
				
			} else
				assert false : attrName + LFCR + clzzname + LFCR + o.toString();
		}
	}

	public void createCntrlData(){
	
	for (String key : this.cntrlData.keySet()) {
		@SuppressWarnings("unchecked")
		VP_Tokenlist<Object> ebl = (VP_Tokenlist<Object>) this.cntrlData.get(key);
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
		String rc = new String("Elementname: "
				+ this.name+"("+this.elementID+")"+LFCR);

		for (String key : this.atypeRegister.keySet()) {
			_AType<?> t = this.atypeRegister.get(key);
			rc += key + ": " + t.name +"/"+t.ordinal+ "/" + t.value + "/"
					+ t.value.getClass().getSimpleName() + LFCR;
		}
		
		for(_ElementBuilder eb: this._ElementBuilderMap.values()){
			rc+=LFCR+eb.toString();
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
