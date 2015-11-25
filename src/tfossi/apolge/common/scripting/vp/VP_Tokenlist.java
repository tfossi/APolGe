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
 * Verwaltet die Flags:
 * Complete
 * Table
 * ConstantsMarker
 * DefinitionsMarker
 * IndividualsMarker
 * AddressesMarker
 * TemporesMarker
 * SpezConstantsMarker
 * MAKEPassMarker
 * GFPassMarker
 *
 * @author tfossi
 * @version 16.08.2014
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
	
//
	public void setTwoPass();
	public void clrTwoPass();
	public boolean isTwoPass();
	public void setThreePass();
	public void clrThreePass();
	public boolean isThreePass();
	public void setInit();
	public void clrInit();
	public boolean isInit();
	public void setFlow();
	public void clrFlow();
	public boolean isFlow();
	
	
	
	
//	/**
//	 * Setzt die Liste auf 'Complete'. Damit ist sie komplett umgesetzt. 
//	 * @modified - 
//	 */
//	public void setComplete();
//	
//	/**
//	 * Setzt die Liste auf Nicht-'Complete'.  
//	 * @modified - 
//	 */
//	public void clrComplete();
//	
//	/**
//	 * @return Hole den Status von 'Complete'
//	 * @modified - 
//	 */
//	public boolean isComplete();
//		
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
//
//	/**
//	 * TODO Comment
//	 * @param key ????
//	 * @param newPreKey ????
//	 * @return ????
//	 * @modified - 
//	 */
//	public String setMarker(String key, String newPreKey);
//	
//	// CONSTANTS
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setConstantsMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isConstantsMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrConstantsMarker();
//	
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setSVARMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isSVARMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrSVARMarker();
//
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setINDIMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isINDIMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrINDIMarker();
//	
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setAddressesMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isAddressesMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrAddressesMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setAUXVARMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isAUXVMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrAUXVARMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setSCONMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isSCONMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrSCONMarker();
//	
//	// FIXME Ab hier ist Bedarf zu checken!
//	
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setMAKEPassMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isMAKEPassMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrMAKEPassMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void setGFPassMarker();
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public boolean isGFPassMarker();
//	/**
//	 * TODO Comment
//	 * @modified - 
//	 */
//	public void clrGFPassMarker();
//	
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public String makeString();
//
//	/**
//	 * TODO Comment
//	 * @return -
//	 * @modified - 
//	 */
//	public String getMarker();
//
//	/**
//	 * TODO Comment
//	 * @return ????
//	 * @modified 8.12.2014 / BuildGuideline 
//	 */
//	public Object getValue();
}
