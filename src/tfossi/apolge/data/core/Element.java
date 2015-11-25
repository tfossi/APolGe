/**
 * Element.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core;

import java.util.HashMap;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import java.util.Map;
import java.util.Set;

import tfossi.apolge.common.scripting.vp.VP_Tokenlist;

/**
 * Zu einem Element mit Atomen zusammengefassten Eigenschaften
 * 
 * @author tfossi
 * @version 26.10.2015
 * @modified -
 * @since Java 1.6
 */
public class Element {

	/** Datencontainer eines Elements */
	private Map<String, _Atom<?>> e = new HashMap<String, _Atom<?>>();
	/** e1 */
	private Map<String, VP_Tokenlist<Object>> e1 = new HashMap<String, VP_Tokenlist<Object>>();

	/**
	 * @return Liste der Eigenschaftsnamen
	 * @modified -
	 */
	public Set<String> getAtomnamen() {
		return this.e.keySet();
	}

	/**
	 * Trägt eine Elementeigenschaft ein
	 * 
	 * @param atomname
	 *            Name der Eigenschaft
	 * @param valuetokens
	 *            Wert(eliste) der Eigenschaft
	 * @modified -
	 */
	public void put(String atomname, VP_Tokenlist<Object> valuetokens) {
		this.e1.put(atomname, valuetokens);

	}

	/**
	 * @param Atomname
	 *            Name der Eigenschaft
	 * @return Datentyp der Eigenschaft
	 * @modified -
	 */
	_AType<?> getType(String Atomname) {
		return this.e.get(Atomname).type;
	}

	/**
	 * @param Atomname
	 *            Name der Eigenschaft
	 * @return Daten der Eigenschaft
	 * @modified -
	 */
	Object getValue(String Atomname) {
		return this.e.get(Atomname).value;
	}

	/**
	 * @param Atomname
	 *            Name der Eigenschaft
	 * @return Atom
	 * @modified -
	 */
	_Atom<?> getAtom(String Atomname) {
		return this.e.get(Atomname);
	}

	/**
	 * Abfrage, ob eine Eigenschaft existiert
	 * 
	 * @param string
	 *            Name der Eigenschaft
	 * @return <i>true</i> Eigenschaft ist vorhanden
	 * @modified -
	 */
	public boolean hasAtom(String string) {
		return this.e.containsKey(string);
	}

	/** parent */
	final Element parent;
	/** id */
	final int id;

	/**
	 * Element anlegen
	 * 
	 * @param p
	 *            Parent-Element
	 * @modified -
	 */
	public Element(Element p) {
		this.parent = p;
		if (p == null)
			this.id = 1;
		else
			this.id = p.getID();
	}

	/** subID */
	private int subID = 0;

	/**
	 * @return liefert nächste ID-Nummer und aktualisiert sie +1
	 * @modified -
	 */
	int getID() {
		return this.subID++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String rc = new String("Parent: ");
		if (this.parent != null)
			rc += this.e.toString() + LFCR;
		else
			rc += "-" + LFCR;
		rc += "ID: " + this.id + LFCR;
		rc += "Subs:" + this.subID + LFCR;

		for (String key : this.e.keySet()) {
			rc += key + ": " + this.e.get(key);
		}

		return rc;
	}

}
