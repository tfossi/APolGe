/**
 * Macro.java
 * Branch macrorecorder
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.macrorecorder;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.NTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.Serializable;

import org.apache.log4j.Logger;

import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.CommandList;
import tfossi.apolge.common.hci.menu.IGeneralReceiver;

/**
 * Macro (Befehlsabfolge)<br>
 * Ein Makro enthält eine Reihe von Befehlen, mindestens aber einen.
 * 
 * @see ACmd
 * @see Recorder
 * @author tfossi
 * @version 11.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Macro implements Serializable {

	/** Receiverklasse, dem das Makro zugeordnet ist */
	private Class<? extends IGeneralReceiver> owner;

	/** Kommentar zum Makro */
	private String comment;

	/** Befehlsspeicher für ein oder mehrere Befehle */
	private CommandList dcrlist = null;

	/** @return Receiver, dem das Makro zugeordnet ist */
	final Class<? extends IGeneralReceiver> getOwner() {
		return this.owner;
	}

	/** @return Kommentar zum Makro */
	final String getComment() {
		return this.comment;
	}

	/**
	 * Liefert die Liste der Commands dieses Makros
	 * 
	 * @return Liste der Commands
	 */
	final CommandList getDcrlist() {
		return this.dcrlist;
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Macro.class.getPackage().getName());

	/**
	 * Ein Makro enthält eine Reihe von Befehlen incl. Parameter, mindestens aber einen.
	 * 
	 * @param commentIn
	 *            ist Kommentar des Makros
	 * @param ownerIn
	 *            Receiver, dem das Makro zugeordnet ist
	 */
	public Macro(final String commentIn, final Class<? extends IGeneralReceiver> ownerIn) {
		if(LOGGER) logger.trace("Macro Head" + NTAB + commentIn + NTAB + ownerIn);
		this.owner = ownerIn;
		this.comment = commentIn;
	}

	/**
	 * Ein Makro enthält eine Reihe von Befehlen incl. Parameter, mindestens aber einen.
	 * 
	 * @param commentIn
	 *            ist Kommentar des Macros
	 * @param ownerIn
	 *            Receiver, dem das Makro zugeordnet ist
	 * @param acListe
	 *            ist die Liste der Befehle dieses Macros
	 */
	public Macro(final String commentIn, final Class<? extends IGeneralReceiver> ownerIn,
			final CommandList acListe) {
		this(commentIn, ownerIn); // this()
		if(LOGGER) logger.trace("Macro Body" + NTAB + commentIn + NTAB + ownerIn + NTAB + acListe.size()
				+ " Befehle");
		this.dcrlist = acListe;

	}
}
