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

		
		/** Zähler für Anzahl der ElementBuilder<br>
		 * 	Der genutzte Zähler liegt nur im Root-ElementBuilder
		 */
		private int idCounter=0;		
		
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
		
		// ---- Konkrete Objecte erstellen ------------------------------------
		
		/**
		 * Erzeugt alle initialen Element-Objecte
		 * @param e Der Datenspeicher
		 * @modified - 
		 */
		public final void createRoot(Element e){
			
			
			// Ist cntrlData nicht definiert, dann erzeuge nur ein Object
			if( this.cntrlData==null){
				logger.trace("Erstelle von "+this.name+" 1 Object");
				createObject(e);
			}else{
				// Gehe alle cntrlData-Einträge durch
				for (String key : this.cntrlData.keySet()) {
					VP_Tokenlist<Object> ebl = LoadScript.getVP_List(this.cntrlData, key);
					
					// Prüfe, ob Eintrag noch aufgelöst werden muss 
					if (ebl.isTwoPass()) {
							new VP_Transfer().transfer(key, this.cntrlData, ebl,
								(List<String>) null, (byte) 1);
							ebl.clrTwoPass();
							logger.fatal("2-Pass-Result:"+LoadScript.getIntValue(this.cntrlData, key));
					}	
				}				
					
				logger.trace("Erstelle von "+this.name+" " 
							+ LoadScript.getIntValue(this.cntrlData, "count")+" Objects");	
				for(int objectID = 0; objectID < LoadScript.getIntValue(this.cntrlData, "count"); objectID++){
					createObject(e);
				}
			}
			
			// Gehe die Child-Elementbuilder durch
			for(_ElementBuilder eb : this._ElementBuilderMap.values()){				
				eb.createRoot(e);			
			}
			
		}

		/**
		 * Ein konkrete Object bauen
		 * @param e Der Datenspeicher
		 * @param objectID
		 * @modified - 
		 */
		private void createObject(Element e){
			
			int objectID = -1;
			
			for(_AType<?> at: this.atypeRegister.values()){

				if( at.value.getClass().equals(Integer.class))
					objectID = e.createAttribute(this.elementID, at.ordinal, (Integer)at.value);
				else if( at.value.getClass().equals(Double.class))
					objectID = e.createAttribute(this.elementID, at.ordinal, (Double)at.value);
				else if( at.value.getClass().equals(String.class))
					objectID = e.createAttribute(this.elementID, at.ordinal, (String)at.value);
				else
					objectID = e.createAttribute(this.elementID, at.ordinal, at.value);
				
				logger.trace("Gebaut: "+this.elementID+"/"+at.ordinal+"/"+at.name+"/"+objectID+"/"+at.value);
				
			}
			
		}
		
		// ---- Elemenbuilder erstellen -------------------------------------------
		
		/** Enthält die child-ElementBuilder */
		private final Map<String, _ElementBuilder> _ElementBuilderMap = new HashMap<String, _ElementBuilder>();

		/** Enthält die ID-Nummer des ElementBuilders */
		private final int elementID;
		
		/** Enthält den Namen des ElementBuilders */
		private final String name;
		
		/** Pfad zur APO-Script des ElementBuilders */
		private final String path;
		
		/** Steuerdaten zur Elementerzeugung */
		private final TableMap cntrlData;

		
		/**
		 * Erstelle einen neuen ElementBuilder<br>
		 * this.simpleTestdaten[row][0], null,null,  ls.getTable(), TESTPATH
		 * name, (TableMap)null, parent, ls.getTable(), TESTPATH
		 * 
		 * childname,LoadScript.getObjectValue(childtable, childname), (root==null?this:root), ls.getTable(), path
		 * TODO Comment
		 * @param name Name des Element
		 * @param attributes TODO
		 * @param root oberstes Element
		 * @param block APO-TabelleneintrÃ€ge des aktuellen Elements
		 * @param path Pfad zu den APO-Scripten (fÃŒr die untergeordneten Elemente)
		 * @modified -
		 */
		public _ElementBuilder(final String name, final TableMap cntrlData,
				final _ElementBuilder root, final Table block, final String path) {
			
			// Name des ElementBuilders
			this.name = name;
			//		this.parent = parent;
			
			// Pfad zum APO-Script
			this.path = path;
			
			// Steuerdaten zur Erzeugung der Childs und deren Scalierung
			this.cntrlData = cntrlData;
			
			// ElementID erzeugen und den Zähler initieren bzw. hochzählen 
			if(root==null){
				this.elementID=0;
				this.idCounter=0;
			}else{
				this.elementID = ++root.idCounter;
			}
				
			// Childs erzeugen
			createChilds(block,root);

			// Attribute für die Elemente dieses ElementBuilders anlegen
			createAttributes(block);		

		}

		// ---- Elementattributbeschreibung erstellen -----------------------------
		
		/** Register für alle Attribute dieses Elements */
		public final Map<String, _AType<?>> atypeRegister = new HashMap<String, _AType<?>>();
			
		private void createAttributes(final Table block){

			// Alle Attribute des Elements anlegen!
			for (String attrName : block.keySet()) {
				
				// Gesperrte Anweisungen ignorieren
				if (nogolst.contains(attrName))
					continue;

				// Lade Object aus Table
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
				
				// Füge Object in Typenregister ein
				addType(attrName, o);
							
			}
		}
		
		private void addType(String attrName, Integer value){
			int ordinal = 0;
			for(_AType<?> c : this.atypeRegister.values()){					
				if(c.value.getClass().equals(Integer.class))ordinal++;
			}
			
			this.atypeRegister.put(attrName, new N_AType<Integer>(attrName,
					(Integer) value, ordinal));
		}

		private void addType(String attrName, Double value){
			int ordinal = 0;
			for(_AType<?> c : this.atypeRegister.values()){					
				if(c.value.getClass().equals(Double.class))ordinal++;
			}
			
			this.atypeRegister.put(attrName, new N_AType<Double>(attrName,
					(Double) value, ordinal));
		}

		private void addType(String attrName, String value){
			int ordinal = 0;
			for(_AType<?> c : this.atypeRegister.values()){					
				if(c.value.getClass().equals(String.class))ordinal++;
			}
			
			this.atypeRegister.put(attrName, new T_AType<String>(attrName,
					(String) value, ordinal));
		}

		private void addType(String attrName, Object value){
			int ordinal = 0;
			for(_AType<?> c : this.atypeRegister.values()){					
				if(c.value.getClass().equals(Object.class))ordinal++;
			}
			
			this.atypeRegister.put(attrName, new O_AType<Object>(attrName,
					(Object) value, ordinal));
		}
		
		// ---- Child-ElementBuilder erstellen ------------------------------------
		
		private void createChilds(final Table block,final _ElementBuilder root){

			Object childs = block.get("CHILDS");
			// Childs erzeugen
			if (childs != null) {
				// Lege die Childs an.
				Table childtable = ((VP_Tokenlist<?>) childs).getTable();
				
				// Alle EintrÃ€ge durchgehen
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
