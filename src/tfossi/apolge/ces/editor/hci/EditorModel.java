/** 
 * EditorModel.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.editor.hci;

import static tfossi.apolge.common.constants.ConstValue.*;
import static tfossi.apolge.common.constants.ConstValueExtension.*;
import org.apache.log4j.Logger;

import tfossi.apolge.common.hci.AModel;

/**
 * Enthält die Daten des EditorMenu und EditorView persistent vor. Als Singleton-Pattern
 * ausgelegt.
 * 
 * @.pattern Singleton
 * @.pattern MVC: concrete model
 * @see AModel
 * @see EditorMenu
 * @see EditorView
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class EditorModel extends AModel {


	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(EditorModel.class.getPackage().getName());

	/** itsInstance */
	private static EditorModel itsInstance = new EditorModel();

	/** Instanziert das Editormodel */
	private EditorModel() {
		super("Editor ");
		if(LOGGER) logger.debug("Habe Editormodel eingerichtet.");
	}

	/** @return Instanz */
	public static final synchronized EditorModel getInstance() {
		return itsInstance;
	}

	 /**
	 * TODO Comment
	 * @modified - 
	 */
	void wildewutz() {
//		 System.out.println("WILDWUTZ: EditorModel : CONSTRUCTOR");
//		 this.md.loadDaten();
//		 DataGame dg = this.md.getDataGame(0L);
//		 ITimesController ct = dg.getTc();
//		 for(Long dnuid : dg.getListUID()){
//			 DataNation dn = dg.getDsc().getDataNation(dnuid.longValue());
//			 for(Long dcuid : dn.getListUID()){
//				 DataClan dc = dg.getDsc().getDataClan(dcuid.longValue());
//				 for(Long dpuid : dc.getListUID()){
//					 DataPerson dp = dg.getDsc().getDataPerson(dpuid.longValue());
//					 PiT pit = dp.getDayOfBirth().clone();
//					 pit.year = dg.getTc().getActualdate().year;
//					 if(ConstMethod.before(pit, dg.getTc().getActualdate())){
//						 pit.year++;
//					 }
//					 pit.dayOfWeek = Tage.UNDEF;
//					 dg.getTc().addTermin(
//							 new YearlyEventPrototype("Geburtstag von "+dp.getName(), 
//									 pit, 
//									 dg.getTc()).clone());
//					 
//				 }
//			 }
//		 }
//
//		 
//		 ct.timestart();
//		 ct.timerestart();
		 
		 
	 }
}
