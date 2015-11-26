/**
 * VP_Tokenlist.java
 * Branch scripting
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import java.util.List;

import tfossi.apolge.common.scripting.t.Table;

/**
 * Schnittstelle zur Liste der Tokens, die in ValueParser ausgewertet werden.<br>
 * Es k�nnen sich um einen neuen Block (Table) oder Valueanweisungen List<String>) handeln.
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
	 * TODO Comment
	 * @param fromIndex ????
	 * @return ????
	 * @modified - 
	 */
	public VP_Tokenlist <T> subList(int fromIndex);
	@Override
	public int indexOf(Object o);
	

	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void setTwoPass();
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void clrTwoPass();
	/**
	 * TODO Comment
	 * @return TODO
	 * @modified - 
	 */
	public boolean isTwoPass();
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void setThreePass();
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void clrThreePass();
	/**
	 * TODO Comment
	 * @return TODO
	 * @modified - 
	 */
	public boolean isThreePass();
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void setInit();
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void clrInit();
	/**
	 * TODO Comment
	 * @return TODO
	 * @modified - 
	 */
	public boolean isInit();
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void setFlow();
	/**
	 * TODO Comment
	 * @modified - 
	 */
	public void clrFlow();
	/**
	 * TODO Comment
	 * @return TODO
	 * @modified - 
	 */
	public boolean isFlow();
		
	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public boolean isTable();
	
	/**
	 * Liefert Table, wenn es sich um ein Table handelt. Sonst <code>null<code>
	 * @return Table or null
	 */
	public Table getTable();

}
