/** 
 * CreditModel.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.subs.credit;


import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;

import org.apache.log4j.Logger;

import tfossi.apolge.common.hci.AModel;

/**
 * Daten für Credit-Menü<br />
 * Näheres {@link AModel}
 * 
 * 
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class CreditModel extends AModel {

	{	if (LOGGER)
		System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(CreditModel.class.getPackage().getName());

	/** itsinstance */
	private final static CreditModel itsinstance = new CreditModel();

	/** @return Instanz */
	final static synchronized CreditModel getInstance() {
		return CreditModel.itsinstance;
	}

	/** Instanziert das Model */
	private CreditModel() {
		super("Credit ");
		if(LOGGER) logger.debug("Habe Model eingerichtet.");
	}
}
