/** 
 * CommandList.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;

import java.util.List;

import tfossi.apolge.io.IContent;
import tfossi.apolge.io.screenfactory.IBuildSWTWidget;


/**
 * Schnittstelle für {@link CommandArray} und Erweiterung von {@link java.util.List} um
 * den Typen {@link ACmd}. Damit wird eine typsichere Verarbeitung ermöglciht.
 * 
 * @see CommandArray
 * @author tfossi
 * @version 11.08.2014
 * @modified tfossi, 14.08.2014, clone() Usersession
 * @since Java 1.6
 */
public interface CommandList extends List<ICmd>, Cloneable, IBuildSWTWidget 
{
	/** @return den Clone des CommandArrays */
	public CommandArray clone();

//	/**
//	 * @return Liefert Liste der SimpleName() der im Array enthaltenen Befehlsklassen.
//	 *         Zeilenweise String mit LFCR
//	 */
//	@Override
//	public String toString();
//
	/**
	 * Liefert den Content für die Screeninformation.
	 * 
	 * @return Stringzeilen für den Screen.
	 */
	public IContent getContent();
}
