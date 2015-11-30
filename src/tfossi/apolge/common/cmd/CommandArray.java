/** 
 * CommandArray.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.IContent;

/**
 * Erweitert das {@link java.util.ArrayList} für die Commands mit dem festem Typen
 * <code>ArrayList &lt;ICmd&gt;</code> und implementiert die Sc.
 * 
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CommandArray extends ArrayList<ICmd> implements CommandList {

	// ---- Screen ------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.command.CommandList#getInformation()
	 */
	@Override
	public final IContent getContent() {
		return new ContentString(this.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ArrayList#clone()
	 */
	@Override
	public final CommandArray clone() {
		return (CommandArray) super.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public final String toString() {
		// assert false;
		String rc = "";
		for (Iterator<ICmd> iter = super.iterator(); iter.hasNext();) {
			ICmd ac = iter.next();
			if(! (ac.isUnvisible()))
			rc += ac.getCode() + TAB;
		}
		return rc;
	}

	// ---- SWT ------------------------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge200.common.gui.IBuildSWTWidget#buildSWTWidget(org.eclipse.swt
	 * .widgets.Group)
	 */
	@Override
	public final List<Widget> buildSWTWidget(final Group group) {
		// assert false;
		assert group != null;
		List <Widget> listOfWidgets = new ArrayList<Widget>();
		for (Iterator<ICmd> iter = super.iterator(); iter.hasNext();) {
			ICmd ac = iter.next();
			if(LOGGER) logger.trace("SWT::\tBaue Parameter für [" + ac.getClass().getSimpleName()
					+ "] zusammen.");
			List<Widget> acList = ac.buildSWTWidget(group);
			if (acList != null) {
				listOfWidgets.addAll(acList);
			} else
				if(LOGGER) logger.debug("GUI::\tCommand [" + ac.getClass().getSimpleName()
						+ "] nicht anzeigen!");
		}
		return listOfWidgets;
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger
			.getLogger(CommandArray.class.getPackage().getName());

	/**
	 * Erweitert das {@link java.util.ArrayList} für die Commands mit dem festem Typen
	 * <code>Collection &lt;? extends AbstractCmd&gt;</code>
	 * 
	 * @param c
	 *            ist initiale Belegung mit der Collection
	 * @see java.util.ArrayList#ArrayList(Collection)
	 */
	public CommandArray(final Collection<? extends ICmd> c) {
		super(c);
	}

	/**
	 * Erweitert das {@link java.util.ArrayList} für die Commands mit dem festem Typen
	 * <code>ArrayList &lt;AbstractCmd&gt;</code>
	 * 
	 * @see java.util.ArrayList#ArrayList()
	 */
	public CommandArray() {
		super();

	}
}
