/**
 * 
 */
package tfossi.apolge.uefkt.geber;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Queue;

import org.apache.log4j.Logger;

import tfossi.apolge.data.core.DataRoot;
import tfossi.apolge.data.core.ICoreData;
import tfossi.apolge.time.IExecuteTermin;

/**
 * Abstrakter Sollwert-Geber 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public abstract class _Geber implements IExecuteTermin, Cloneable {
//	protected final static CDC dsc = new CDC();

	/** Aktuelles Spiel */
	protected final DataRoot game;
//	/** Sollwertbericht */
//	protected final Queue<Double> queue = new LinkedList<Double>();

	/** Zähler für Abtastwerte */
	protected int k;
	/** Maximale Anzahl der Abtastwerte */
	protected int K;
	/** Null-Punktverschiebung um Y */
	protected double Y0;
	/** Null-Punktverschiebung um X-Achse (k) */
	protected final int K0;
	/** Sollwert für Geber */
	protected double wSoll;
	/** Proportionalitätsfaktor */
	protected double kp;
	/** Tastverhältnis*/
	protected final int TV;


	// Stammdaten für den Sollwert
	/** Konkreter Datensatz */
	protected ICoreData concreteData;
	/** Stategruppe */
	protected int sg_idx;
	/** IGL */
	protected int igl_idx;
//
//	// Die Datenquelle für die Sollwertbestimmung liegt nicht in den Stammdaten
//	/** Andere Klasse mit Daten bei indirekt. Anderer Vertex. Dann aber yk! */
	// FIXME Final und checken gegen PreAddress 
//	protected Address adress;
//	protected final Class<? extends CoreData> otherClass;
//	/** Anderer Vertex. Dann aber yk! */	
//	protected final int otherSG_idx;
//	/** Anderer Vertex. Dann aber yk! */
//	protected final int otherIGL_idx;	
//	/** Anderer Datensatz zur Lieferung der Geberdaten */ 
//	protected CoreData otherData;
//	
//	protected final Address adresse; 
//	protected PreAddress preAdresse; 
//	/**
//	 * Subliste von otherData, statt otherData selber.
//	 */
//	protected boolean subOfOtherData = false;
//	

	/**
	 * TODO Comment
	 * @param game 
	 * das aktuelle Game
	 * @param sg_idx
	 * Index der Stategruppe
	 * @param igl_idx	
	 * Index des States
	 * @param kp
	 * Verstärkungsfaktor. Mit o.g. Parametern entspricht das einer
	 *            Konstanten.
	 * @param K
	 *  Maximale Anzahl der Abtastwerte.
	 * @param K0
	 * Nullpunkt-Verschiebung auf der x-Achse. Default: 0
	 * @param Y0
	 * Nullpunktverschiebung auf der Y-Achse. Default: 0
	 * @param TV
	 * 			Tastverhältnis von 10-90%. Default: 50%
	 * @modified -
	 */
//	protected _Geber(DataRoot game, int sg_idx, int igl_idx, 
////			Class<? extends Data> otherClass, 
////			int othersg_index, 
////			int otherigl_idx,
//			Address adresse, double kp, int K, int K0, double Y0,
//			int TV) {
//		this.Y0 = Y0;
//		this.TV = TV;
//		this.K = K;
//		this.K0 = K0;
//		this.kp = kp;
//		this.game = game;
//		this.sg_idx = sg_idx;
//		this.igl_idx = igl_idx;
//
//		this.otherClass = null;
//		this.otherSG_idx = -1;
//		this.otherIGL_idx = -1;
//		this.adresse=adresse;
//	}	
	protected _Geber(DataRoot game, int sg_idx, int igl_idx, 
//			Class<? extends ICoreData> otherClass, 
//			int othersg_index, 
//			int otherigl_idx,
//			Address adresse, 
			double kp, int K, int K0, double Y0,
			int TV) {
		this.Y0 = Y0;
		this.TV = TV;
		this.K = K;
		this.K0 = K0;
		this.kp = kp;
		this.game = game;
		this.sg_idx = sg_idx;
		this.igl_idx = igl_idx;
	}

	/**
	 * @return die Berechnung der Geberfunktion
	 */
	protected abstract double fkt();


	/**
	 * @return der Sollwert <i>wsoll</i> des Gebers
	 */
//	protected 
	public abstract double soll();

//	/**
//	 * Klonen des Gebers
//
//	 * @param data
//	 *            der aktuelle Datensatz
//	 * @param oData
//	 * 			der indirekte Datensatz
//	 * @param subOfOtherData
//	 * 			Anzeiger, ob die registrierten Subdatas ausgewertet werden.
//	 * @return der Klone
//	 */
//	public _Geber clone(CoreData data, CoreData oData, boolean subOfOtherData) {
//		this.concreteData = data;
//		this.otherData = oData; 
//		this.subOfOtherData = subOfOtherData;
//
//		try {
//			return (_Geber) super.clone();
//		} catch (CloneNotSupportedException e) {
//			// Kann eigentlich nicht passieren, da Cloneable
//			throw new InternalError();
//		}
//	}
//
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.time.IExecuteTermin#execute()
	 * FIXME aktivieren 25.02.2014
	 */
	@Override
	public Queue<Object> execute() {
//		// Aktueller Sollwert 
//		double[] wk = dsc.getDigit(this.concreteData, dsc.WK, this.sg_idx, this.igl_idx);
//	
//		// Move Werte um eine Position, Ältesten Wert löschen. Platz für neuen Sollwert schaffen  
//		for(int i = wk.length; --i>0;)
//			wk[i] = wk[i-1];
//		
//		// Neuer Sollwert
//		wk[0] = this.fkt();
//		
//		// Sollwerte sichern
//		dsc.setDigit(this.concreteData, dsc.WK, this.sg_idx, this.igl_idx, wk);
//
//		// Sollwertbericht
//		this.queue.offer(new Double(wk[0]));
		return null; //this.queue;
	}
	
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	
	/** logger */
	final static Logger logger = Logger.getLogger(_Geber.class.getPackage()
			.getName());

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		String str = new String(this.getClass().getSimpleName());
//		str += TAB + this.sg_idx + "." + this.igl_idx;
//		return str;
//	}
	
	// XXX Since 25.02.2014 ff
	
	/**
	 * @return Beschreibung des Gebers
	 * 
	 */
	public abstract String description();

}
