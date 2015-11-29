/**
 * RFormat.java
 * Branch report
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.report;

import java.util.Arrays;

import static tfossi.apolge.common.constants.ConstValueExtension.*;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.pm.Operation;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 16.08.2014
 * @modified -
 * @since Java 1.6
 */
public class RFormat {
	/** rcol */
	private RColFormat[] rcol = new RColFormat[0];
	/** rcolInitLength */
	private int rcolInitLength = 0;
	
	/**
	 * TODO Comment
	 * @modified -
	 */
	public RFormat(){
		
	}
	/**
	 * @return liefert die Anzahl der Spalten 
	 */
	public final int cols(){
		return this.rcol.length;
	}
	/**
	 * @return liefert die Anzahl der Spalten 
	 */
	public final int initCols(){
		return this.rcolInitLength;
	}
	
	/**
	 * TODO Comment
	 * @param colArr -
	 * @throws ClassNotFoundException -
	 * @modified - 
	 */
	public final void init(final String[][] colArr) throws ClassNotFoundException{
		this.rcol = new RColFormat[0];
		if(colArr !=null){
			for( String [] col :  colArr){
				this.add(col);
			}
		
		this.rcolInitLength = colArr[0].length;
		}
		assert colArr!=null;
	}
	
	/**
	 * TODO Comment
	 * @param col -
	 * @throws ClassNotFoundException -
	 * @modified - 
	 */
	public final void init(final String[] col) throws ClassNotFoundException{
		this.rcol = new RColFormat[0];

				if(col!=null && col.length==2){
					String name = col[0];
					String typ = col[1];
					this.rcol = Arrays.copyOf(this.rcol, this.rcol.length+1);
					this.rcol[this.rcol.length-1] = new RColFormat(name, typ);
					this.rcolInitLength = this.rcol.length;
				}
	}
	
	/**
	 * TODO Comment
	 * @param colArr -
	 * @throws ClassNotFoundException -
	 * @modified - 
	 */
	public final void add(final String[][] colArr) throws ClassNotFoundException{
		if(colArr !=null){
			for( String [] col :  colArr){
				this.add(col);
			}
		this.rcolInitLength = colArr[0].length;
		}
		assert colArr!=null;
	}
	
	/**
	 * TODO Comment
	 * @param col -
	 * @throws ClassNotFoundException -
	 * @modified - 
	 */
	public final void add(final String[] col) throws ClassNotFoundException{

				if(col!=null && col.length==2){
					String name = col[0];
					String typ = col[1];
					this.rcol = Arrays.copyOf(this.rcol, this.rcol.length+1);
					this.rcol[this.rcol.length-1] = new RColFormat(name, typ);
					this.rcolInitLength = this.rcol.length;
				}
	}
	/**
	 * TODO Comment
	 * @param col -
	 * @param colClass -
	 * @param iop1 -
	 * @param iop2 -
	 * @param op -
	 * @modified - 
	 */
	public final void addx(final String[] col, final Class<?> colClass, final int iop1, final int iop2, final Operation op){

		if(col!=null && col.length==2){
			String name = col[0];
			String typ = col[1];
			this.rcol = Arrays.copyOf(this.rcol, this.rcol.length+1);
			this.rcol[this.rcol.length-1] = new RColFormat(name, typ, colClass, iop1, iop2, op);
		}
}

	/**
	 * TODO Comment
	 * @return -
	 * @modified - 
	 */
	public final RColFormat[] get(){
		return this.rcol;
	}
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(RFormat.class
			.getPackage().getName());
}
