/**
 * ContentString.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;

import org.eclipse.swt.widgets.Widget;

/**
 * Screencontent für Cnsl und im Einzelfall GUI
 *
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class ContentString implements IContent {
	/** Speichert zeilenweise den Screencontent */
	private java.lang.String[] s;

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.io.IContent#getString()
	 */
	@Override
	public java.lang.String[] getString() {
		return this.s;
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.io.IContent#getWidget()
	 */
	@Override
	public List<Widget> getWidget(){
		return null;
	}
	
	/**
	 * @param str
	 * 			Einzeiler anlegen
	 */
	public ContentString(String str) {
		this.s = new String[] { str };
	}

	/**
	 * @param str
	 * 			Mehrzeiler anlegen
	 */
	public ContentString(String[] str) {
		this.s = str;
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
}
