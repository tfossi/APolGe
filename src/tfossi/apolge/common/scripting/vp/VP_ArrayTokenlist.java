/**
 * VP_ArrayTokenlist.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.t.TableMap;

/**
 * Liste der Tokens, die in ValueParser ausgewertet werden. Wertzuweisung einer
 * Variable Ist eine Erweiterung der ArrayList um Typen und Flags.
 * 
 * @author tfossi
 * @version 16.08.2014
 * @param <T>
 *            Datentyp
 * @modified -
 * @since Java 1.6
 */
public class VP_ArrayTokenlist<T> extends ArrayList<T> implements
		VP_Tokenlist<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	@Override
	public boolean add(T e) {
		boolean rc = super.add(e);
		return rc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#subList(int, int)
	 */
	@Override
	public VP_Tokenlist<T> subList(int fromIndex, int toIndex) {
		VP_Tokenlist<T> r = new VP_ArrayTokenlist<T>();
		for (int index = fromIndex; index < toIndex; index++)
			r.add(this.get(index));
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#subList(int)
	 */
	@Override
	public VP_Tokenlist<T> subList(int fromIndex) {
		return this.subList(fromIndex, this.size());
	}

	/**
	 * * Liste der Tokens, die in ValueParser ausgewertet werden. Wertzuweisung
	 * einer Variable Ist eine Erweiterung der ArrayList um Typen und Flags.
	 * 
	 * @modified -
	 */
	public VP_ArrayTokenlist() {
		super();
	}

	/**
	 * Liste der Tokens, die in ValueParser ausgewertet werden. Wertzuweisung
	 * einer Variable Ist eine Erweiterung der ArrayList um Typen und Flags.
	 * 
	 * @param o
	 *            Vorbelegen mit einem Wert
	 * @modified -
	 */
	public VP_ArrayTokenlist(T o) {
		super();
		this.add(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#isTable()
	 */
	@Override
	public final boolean isTable() {
		if (this.size() > 0)
			return this.get(0) instanceof TableMap;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getTable()
	 */
	@Override
	public final Table getTable() {
		if (this.isTable()) {
			return (Table) this.get(0);
		}
		return null;
	}

	/** twoPass */
	private boolean twoPass = false;

	@Override
	public final void setTwoPass() {
		this.twoPass = true;
	}

	@Override
	public final void clrTwoPass() {
		this.twoPass = false;
	}

	@Override
	public final boolean isTwoPass() {
		return this.twoPass;
	}

	/** threePass */
	private boolean threePass = false;

	@Override
	public final void setThreePass() {
		this.threePass = true;
	}

	@Override
	public final void clrThreePass() {
		this.threePass = false;
	}

	@Override
	public final boolean isThreePass() {
		return this.threePass;
	}


	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger
			.getLogger(VP_ArrayTokenlist.class.getPackage().getName()
					+ ".VP_ArrayTokenlist");

}
