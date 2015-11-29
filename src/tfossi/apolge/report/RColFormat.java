/**
 * RColFormat.java
 * Branch report
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.report;

import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.pm.Operation;

/**
 * Enthält das Spaltenformat einer Report-Spalte
 * @author tfossi
 * @version 16.08.2014
 * @modified -
 * @since Java 1.6
 */
public class RColFormat {

	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** Spaltenname */
	public final String colName;
	/** Spaltendatentypname */
	public final String colTyp;
	/** SpaltendatenKlasse */
	public final Class<?> colClass;
	/** Spaltenbreite */
	private int width = 1; 
	/** Spaltenoperator */
	public final Operation op; 
	/** Spaltenoperator */
	public final int iop1; 
	/** Spaltenoperator */
	public final int iop2; 
	
	/** pack */
	// XXX Verlagerung in dynm. Erzeugung
	private final static String[] pack = new String[]{
			"java.lang",
			"tfossi.apolge.time.pit"
	};
	
	/**
	 * Erzeuge eine Beschreibung eines Spalteneintrags 
	 * @param colName
	 * 			Name der Spalte
	 * @param colTyp
	 * 			Typname der Spalte
	 * @throws ClassNotFoundException
	 * 			Typ des Typnamens nicht gefunden! 
	 */
	public RColFormat(final String colName, final String colTyp) throws ClassNotFoundException{
		Class<?> tempColClass = null;
		this.colName = colName;
		this.colTyp = colTyp;
		this.op = null;
		this.iop1=-1;
		this.iop2=-1;
				
		
		for(String p : pack){
			try{
				tempColClass = Class.forName(p+"."+colTyp);
			}catch(ClassNotFoundException e){
				continue;
			}
			break;
		}
		if(tempColClass==null)
			throw new ClassNotFoundException(colTyp+" nicht gefunden!");
		this.colClass = tempColClass;
		this.width = this.colName.length();
	}	
	
	/**
	 * Erzeuge eine Beschreibung eines Spalteneintrags 
	 * @param colName
	 * 			Name der Spalte
	 * @param colTyp
	 * 			Typname der Spalte	 
	 * @param colClass
	 * 			Typ der Spalte
	 * @param iop1 -
	 * @param iop2 -
	 * @param op -
	 */
	public RColFormat(final String colName, final String colTyp, final Class<?> colClass, final int iop1, final int iop2, final Operation op){
		this.colName = colName;
		this.colTyp = colTyp;
		this.colClass = colClass;
		this.width = this.colName.length();
		this.op = op;
		this.iop1 = iop1;
		this.iop2 = iop2;
	}
	
	/**
	 * maximale Spaltenbreite eintragen
	 * 
	 * @param widthProbe
	 * 			Spaltenbreite der Probe
	 * @return
	 * 			maximale Spaltenbreite
	 */
	public final int calcWidth(final int widthProbe){
		this.width = (this.width > widthProbe?this.width:widthProbe); 
		return this.width;
	}
	
	/**
	 * @return aktuelle Spaltenbreite 
	 */
	public final int getWidth(){
		return this.width;
	}	
	
	@Override
	public String toString(){
		return this.colName+" | "+this.colTyp+" | "+this.colClass+" | "+this.width;
	}
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(RColFormat.class
			.getPackage().getName());
}
