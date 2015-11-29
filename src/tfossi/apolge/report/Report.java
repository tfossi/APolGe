/**
 * Report.java
 * Branch report
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.report;
import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Queue;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.ValueParser;
import tfossi.apolge.common.scripting.vp.pm.Operation;
import tfossi.apolge.common.scripting.vp.pm.PatternMaps;


/**
 * FIXME Reportkonzeption erarbeiten
 * @author tfossi
 * @version 16.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Report implements IReport {
	{
		if (LOGGER)
			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	/** rformat */
	private final RFormat rformat = new RFormat();
	/** data */
	private final RDaten data = new RDaten(this.rformat);
	

	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#formatNew(java.lang.String[][])
	 */
	@Override
	public final void formatNew(final String[][] colArr)
			throws ClassNotFoundException {
		this.rformat.init(colArr);
		assert false ; // rd.width(rf.get().length);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#formatNew(java.lang.String[])
	 */
	@Override
	public final void formatNew(final String[] col) throws ClassNotFoundException {
		this.rformat.init(col);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#formatAdd(java.lang.String[][])
	 */
	@Override
	public final void formatAdd(final String[][] colArr)
			throws ClassNotFoundException {
		this.rformat.add(colArr);

		assert false ; // rd.width(rf.get().length);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#formatAdd(java.lang.String[])
	 */
	@Override
	public final void formatAdd(final String[] col) throws ClassNotFoundException {
		this.rformat.add(col);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#formatGet()
	 */
	@Override
	public final RColFormat[] formatGet() {
		return this.rformat.get();
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#reporting(java.util.Queue)
	 */
	@Override
	public void reporting(Queue<Object> report) {
		this.data.reporting(report);
	}
	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#reporting(java.util.Queue<java.lang.Object>[])
	 */
	@Override
	public void reporting(Queue<Object> [] report) {
		this.data.reporting(report);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#calc(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void calc(final String sop1, final String sop2, final String sop){
		
		Operation op = null;
//				PatternMaps.finalOperationPattern.equals("\\"+sop);
//				ValueParser.operations.get("\\"+sop);
		assert op!=null;
		
		int iop1 = -1;
		for(int i=0 ;i < this.rformat.get().length; i++){
			if(sop1.equalsIgnoreCase(this.rformat.get()[i].colName)){
				iop1 = i;
				break;
			}
		}	
		int iop2 = -1;
		for(int i=0 ;i < this.rformat.get().length; i++){
			if(sop2.equalsIgnoreCase(this.rformat.get()[i].colName)){
				iop2 = i;
				break;
			}
		}	
		
		this.rformat.addx(new String[]{sop1+sop+sop2, this.rformat.get()[iop1].colTyp}, this.rformat.get()[iop1].colClass, iop1, iop2, op);

		for(int row = 0; row < this.data.rows(); row++){
			int iop3 = this.data.inc(row);
			this.data.get(row)[iop3] = op.calculate(this.data.get(row)[iop1], this.data.get(row)[iop2]);
		}
	}


	
	/* (non-Javadoc)
	 * @see tfossi.apolge.report.IReport#auswertung()
	 */
	@Override
	public final String auswertung() {
		int wi = 0;
		int []w = new int[this.rformat.get().length];
		
		String str = "Auswertung:"+LFCR;
		for (RColFormat rcol : this.rformat.get()) {
			w[wi++] = rcol.getWidth();
			String entry = rcol.colName;
			for(int i = entry.length(); i < rcol.getWidth(); i++)
				entry += " ";
			
			str +=entry + " | ";
		}
		
		wi=0;
		str+= LFCR;
		
		for (int oi = 0; oi < this.data.rows(); oi++){			
			for(int col = 0; col < this.data.get(oi).length; col++){
				String entry = "-";
				
				if(this.data.get(oi)[col]==null){
					RColFormat rcf = this.rformat.get()[col];
					
					Operation op = rcf.op;
					int iop1 = rcf.iop1;
					int iop2 = rcf.iop2;
						this.data.get(oi)[col] = op.calculate(this.data.get(oi)[iop1], this.data.get(oi)[iop2]);
				}
					entry = this.data.get(oi)[col].toString();
				
				for(int i = entry.length(); i < w[col]; i++)
					entry += " ";
				str += entry + " | ";
			}
		str += LFCR;
		}
		return str;
	}	

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	protected final static Logger logger = Logger.getLogger(Report.class
			.getPackage().getName() + ".Report.class");

	/**
	 * TODO Comment
	 * @modified -
	 */
	public Report(){
		logger.debug("VERSION: "+serialVersionUID);
	}
}
