/**
 * 
 */
package tfossi.apolge.data.core;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.io.Serializable;

import org.apache.log4j.Logger;

import tfossi.apolge.data.guide.IGuideData;
/**
 * 
 * Grundgerüst für Daten
 * 
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class CoreData implements Cloneable, Serializable, ICoreData {
//
//	/** serialVersionUID */
//	private static final long serialVersionUID = 1L;
//
//	/** guidedata enthält das Regelwerk {@link GuideData} dieses Datensatzes */
//	protected final GuideData guidedata;
//	
//
//	/** Controller: Datenklasse des Datensatzes: Root, Nation, Clan, Person,... */
//	private Datenklasse datenklasse;
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.data.core.ICoreData#setDatenklasse(tfossi.apolge.data.state.Datenklasse)
//	 */
//	@Override
//	public final Datenklasse setDatenklasse(Datenklasse dk){
//		return this.datenklasse = dk;
//	}
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.data.core.ICoreData#getDatenklasse()
//	 */
//	@Override
//	public final Datenklasse getDatenklasse(){
//		return this.datenklasse;
//	}
//	/**
//	 * Controller: Universal Identifier. Die Identifikationsnummer eines
//	 * Datensatzes ist einmalig pro State und eine laufende Nummer
//	 */
//	short uid = -1;
//
//	// // ---- Triggerliste --------------------------------------------------
//	//
//	// /**
//	// * Controller: Liste der Objecte, die bei Ausführung ebenfalls ausgeführt
//	// werden
//	// */
//	// // FIXME 21.02.2014 Testweise herausgenommen List<Trigger> triggerliste =
//	// new ArrayList<Trigger>(0);
//
//	// ---- Geber: Datenfelder ------------------------------------------------
//
//	/**
//	 * Array mit allen aktiven Sollwertgebern nach grpnr und vtxnr
//	 */
//	_Geber[][] _gActive;
//	/**
//	 * Array mit allen passiven Sollwertgebern nach grpnr und vtxnr
//	 */
//	_Geber[][] _gPassive;
//
//	// ---- Values: Datenfelder -----------------------------------------------
//
//	// Index (Y,W,U,E) , Stategroup, IGL, Wertehistorie 0...n
//	double[][][][] digits;
//
//	// Stategroup, IGL
//	Object[][] nondigits;
//
//	// ---- ActiveFlag: Datenfelder -------------------------------------------
//
//	/** Stategroup, IGL */
//	boolean[][] activeVertex;
//
//	// ---- ActiveFlag: Context -------------------------------------------
//
//	/** Aktiver Context */
//	int[] activeContext;

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(CoreData.class
			.getPackage().getName() + ".CoreData");
	/**
	 * TODO Comment
	 * 
	 * @param guidedata ???
	 * @modified -
	 */
	public CoreData(final IGuideData guidedata) {
//		this.guidedata = guidedata;
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see tfossi.apolge.data.core.ICoreData#tst(java.lang.String)
//	 */
//	@Override
//	public void tst(final String who) {
//		if (who != null)
//			System.err.println(who);
//		System.err.println();
//		System.err.println("Guide" + this.guidedata.toString());
//		
//		System.err.println();
//		System.err.println("Daten");
//		System.err.println("UID        : " + this.uid);
//		System.err.println("Datenklasse: " + this.datenklasse);
//
//		// System.err.println("R-Counter  : "+counterRegister);
//		// System.err.println("Liste R    : "+registerCoreData);
//		// System.err.println("Super      : "+superCoreData);
//		if (this.nondigits == null)
//			System.err.println("NonDigit   : " + this.nondigits);
//		else {
//			System.err.println("NonDigit   : ");
//			System.err.println("        st : " + this.nondigits.length);
//			for (int sti = 0; sti < this.nondigits.length; sti++) {
//				System.err
//						.println("       idx : " + this.nondigits[sti].length);
//				for (int idxi = 0; idxi < this.nondigits[sti].length; idxi++)
//					System.err.println("           : "
//							+ this.nondigits[sti][idxi]);
//			}
//		}
//		if (this.digits == null)
//			System.err.println("Digit      : " + this.digits);
//		else {
//			System.err.println("Digit      : ");
//			System.err.println("       yuwe : " + this.digits.length);
//			for (int yuwei = 0; yuwei < this.digits.length; yuwei++) {
//				System.err.println("       st : " + this.digits[yuwei].length);
//				for (int sti = 0; sti < this.digits[yuwei].length; sti++) {
//					System.err.println("      sti : "
//							+ this.digits[yuwei][sti].length);
//					for (int idxi = 0; idxi < this.digits[sti].length; idxi++) {
//						System.err.println("     idxi : "
//								+ this.digits[yuwei][sti][idxi].length);
//						System.err.print("       ki : ");
//						for (int ki = 0; ki < this.digits[sti][idxi].length; ki++) {
//							System.err.print(ki + ": "
//									+ this.digits[yuwei][sti][idxi][ki] + TAB);
//						}
//						System.err.println();
//					}
//				}
//			}
//		}
//		// FIXME 21.02.2014 Testweise herausgenommen
//		// System.err.println("Trigger    : "+triggerliste);
//		System.err.println("Active _G  : " + this._gActive);
//		System.err.println("Passive _G : " + this._gPassive);
//		System.err.println("Active V   : " + this.activeVertex);
//		System.err.println("Active C   : " + this.activeContext);
//
//	}

}
