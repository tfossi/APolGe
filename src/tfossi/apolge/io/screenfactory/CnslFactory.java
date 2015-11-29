/**
 * CnslFactory.java
 * Branch io
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.io.screenfactory;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

/**
 * Konkrete Screenfactory für Cnsl-Produkte Loop, Storage und Widgets
 * 
 * @.pattern Abstract Factory: Concrete Factory
 * @see Cntr
 * @see AFactory
 * @see AStorage
 * @see ALoop
 * @see AWidget
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CnslFactory extends AFactory {

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.io.screenfactory.AFactory#loop(name.tfossi.apolge.common.io.screenfactory.Cntr)
	 */
	@Override
	final ALoop loop(Cntr cntr) {
		return new CnslLoop(cntr);
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.io.screenfactory.AFactory#storage(name.tfossi.apolge.common.io.screenfactory.Cntr)
	 */
	@Override
	final AStorage storage() {
		return new CnslStorage();
	}

	/* (non-Javadoc)
	 * @see name.tfossi.apolge.common.io.screenfactory.AFactory#widgets(name.tfossi.apolge.common.io.screenfactory.Cntr, name.tfossi.apolge.common.io.screenfactory.SWTGrafik)
	 */
	@Override
	final AWidget widgets(Cntr cntr, SWTGrafik swt) {
		return new CnslWidget(cntr, swt);
	}


	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CnslFactory.class.getPackage().getName());
	
	/**
	 * TODO Comment
	 * @modified -
	 */
	public CnslFactory(){
		if(LOGGER) logger.trace("Factory");
	}
}
