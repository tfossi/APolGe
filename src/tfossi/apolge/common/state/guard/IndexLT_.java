/**
 * 
 */
package tfossi.apolge.common.state.guard;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.PreAddress;
import tfossi.apolge.data.core.ICoreData;
import tfossi.apolge.data.guide.IGuideData;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;




/** 
 * Guard überwacht, dass eine Transition einen kleineren Index hat. 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
*/
public class IndexLT_ implements _Guard{
//	private final int comp;
//
//	private final int compLow;
//	private final int compHigh;
	/**
 * Guard überwacht, dass eine Transition einen kleineren Index hat. 
 
	 *
	 * @param address
	 * 		ist Datenquelle
	 * @param value
	 * 		ist Referenzindex 
	 * @modified -
	 */
	public IndexLT_(final PreAddress address, Object value){
//		if (value instanceof Double) {
//			this.comp = ((Double) value).intValue();
//			this.compLow = this.comp;
//			this.compHigh = this.comp;
//		} else if (value instanceof Double[]) {
//			Double[] o = (Double[]) value;
//			this.compLow = o[0].intValue();
//			this.compHigh = o[1].intValue();
//			this.comp = this.compHigh;
//		} else {
//			assert false;
//			this.comp = 0;
//			this.compLow = 0;
//			this.compHigh = 0;
//		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.getClass().getSimpleName()+": "; //+Integer.toString(this.comp);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#tstIndexGuard(java.lang.Object)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final Object index,
			final double[] ykx){
		assert false;
//		if(this.compLow == this.compHigh)
//			return ((double[]) index)[0] < this.compHigh;
//		double[] yk = (double[]) index;
//
//		return !((yk[0] > this.compLow && yk[1] > yk[0])
//		|| (yk[0] >= this.compHigh ));
//
//	}
		return false;
		
		}
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.state.guard._Guard#tstGuard4Chg2Passiv(tfossi.apolge.data.core.ICoreData)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final ICoreData data) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#getVertex()
	 */
	@Override
	public final IGuideData getVertex(){
		return null;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#getGuardType()
	 */
	@Override
	public Class<?> getGuardType() {		
		return long.class;
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(IndexLT_.class
			.getPackage().getName());
}
