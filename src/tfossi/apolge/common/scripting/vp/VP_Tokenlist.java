/**
 * VP_Tokenlist.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import java.util.List;

import tfossi.apolge.common.scripting.t.Table;

/**
 * Schnittstelle zur Liste der Tokens, die in ValueParser ausgewertet werden.<br>
 * Es können sich um einen neuen Block (Table) oder Valueanweisungen List<String>) handeln.
 * 
 * Ist eine Erweiterung der List um Flags.
 *
 * @author tfossi
 * @version 16.08.2014
 * @param <T> Datentyp
 * @modified -
 * @since Java 1.6
 */
public interface VP_Tokenlist <T> extends List<T> {
	/* (non-Javadoc)
	 * @see java.util.List#subList(int, int)
	 */
	@Override
	public VP_Tokenlist <T> subList(int fromIndex, int toIndex);

	/**
	 * Typensichere Sublist
	 * @param fromIndex von
	 * @return Teiliste
	 * @modified - 
	 */
	public VP_Tokenlist <T> subList(int fromIndex);
	@Override
	public int indexOf(Object o);
	

	/**
	 * Setze 2-Passmarker
	 * @modified - 
	 */
	public void setTwoPass();
	/**
	 * Lösche 2-Passmarker
	 * @modified - 
	 */
	public void clrTwoPass();
	/**
	 * @return <i>true</i> wenn es 2-Pass ist
	 * @modified - 
	 */
	public boolean isTwoPass();
	/**
	 * Setze 3-Passmarker
	 * @modified - 
	 */
	public void setThreePass();
	/**
	 * Lösche 3-Passmarker
	 * @modified - 
	 */
	public void clrThreePass();
	/**
	 * @return <i>true</i> wenn es 2-Pass ist
	 * @modified - 
	 */
	public boolean isThreePass();

// FIXME  Wo und wie auf Init und Flow reagieren?
//	public void setInit();

//	public void clrInit();

//	public boolean isInit();

//	public void setFlow();

//	public void clrFlow();

//	public boolean isFlow();
		
	/**
	 * @return <i>true</i> Eintrag ist Table
	 * @modified - 
	 */
	public boolean isTable();
	
	/**
	 * Liefert Table, wenn es sich um ein Table handelt. Sonst <code>null<code>
	 * @return Table or null
	 */
	public Table getTable();

}
