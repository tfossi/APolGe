/**
 * RDaten.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.report;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Arrays;
import java.util.Queue;

import org.apache.log4j.Logger;
/**
 * @author tfossi
 * @since Java 1.6
 * @version 0.1 
 *
 */
public class RDaten {

	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** rf */
	private final RFormat rf;
	/** rfx */
	private final RFormat rfx;
	/** data */
	private Object[][] data = new Object[0][0];

	
	/**
	 * Daten einer Zeie einlesen
	 * @param report -
	 */
	public void reporting(Queue<?> report){
		if(report==null) return;
		// Speicher für eine Zeile von Spaltendaten
		// Die maximale Spaltenzahl wird im Format festgelegt und über length eingetragen!
		Object[] colarr = new Object[this.rf.cols()+this.rfx.cols()];
		
		// aktuelle Spaltennummer
		int col = 0;
		
		// Alle Daten durchgehen
		for(Object o : report){		
			
			colarr[col] = o;
			if(col<=this.rf.initCols())
				this.rf.get()[col].calcWidth(o.toString().length());
			col = (col + 1) % (this.rf.initCols());
			if(col==0){
				
				int l = this.data.length+1;
				
				// 	Anzahl der Spaltenwerte erreicht. Die extended berechnen und Zeile speichern.
				this.data = Arrays.copyOf(this.data, l);
				this.data[l-1] = colarr;				
			}			
		}	
		report.clear();
	}
	/**
	 * TODO Comment
	 * @param report -
	 * @modified - 
	 */
	public void reporting(Queue<Object>[] report){
		
		// Speicher für eine Zeile von Spaltendaten
		// Die maximale Spaltenzahl wird im Format festgelegt und über length eingetragen!
		Object[] colarr = new Object[this.rf.cols()+this.rfx.cols()];
		
		// aktuelle Spaltennummer
		int col = 0;
		
		// Alle Daten durchgehen
		for(Object o : report){		
			
			colarr[col] = o;
			if(col<=this.rf.cols())
				this.rf.get()[col].calcWidth(o.toString().length());
			col = (col + 1) % (this.rf.initCols());
			
			if(col==0){
				int l = this.data.length;
				// 	Anzahl der Spaltenwerte erreicht. Die extended berechnen und Zeile speichern.
				this.data = Arrays.copyOf(this.data, l+1);
				this.data[l] = colarr;				
			}			
		}			
	}

	/**
	 * TODO Comment
	 * @param row -
	 * @return -
	 * @modified - 
	 */
	public final int inc(int row){
		this.data[row] = Arrays.copyOf(this.data[row], this.data[row].length+1);
		return this.data[row].length-1;
	}
	
	
	/**
	 * @return liefert Anzahl der Zeileneinträge
	 */
	public final int rows(){
		return this.data.length;
	}
	
	/**
	 * @param rowNr -
	 * @return liefert Zeileneintrag #rowData
	 */
	public final Object[] get(int rowNr){
		return this.data[rowNr];
	}
	
	/**
	 * @param rf - 
	 */
	public RDaten(final RFormat rf){
		this.rf = rf;	
		this.rfx = new RFormat();
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(RDaten.class
			.getPackage().getName());
}
