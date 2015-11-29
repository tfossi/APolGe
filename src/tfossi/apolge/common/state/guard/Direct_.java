/**
 * 
 */
package tfossi.apolge.common.state.guard;


import org.apache.log4j.Logger;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import tfossi.apolge.common.scripting.PreAddress;
import tfossi.apolge.data.core.ICoreData;
import tfossi.apolge.data.guide.IGuideData;

/**
 * Guard überwacht, dass eine Transition einen größeren Index hat.
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class Direct_ implements _Guard {
//	private final static CDC dsc = new CDC();
//	private final VertexIndex vi;
//	private final int comp;
//
//	private final int compLow;
//	private final int compHigh;
//
	/**
	 * Guard überwacht, dass eine Transition einen größeren Index hat.
	 *
	 * @param address
	 * 		ist Datenquelle
	 * @param value
	 * 		ist Referenzindex 
	 * @modified -
	 */
	public Direct_(final PreAddress address, Object value) {
//		this.vi = vi;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
//		return this.getClass().getSimpleName() + ": "
//				+ Integer.toString(this.compLow) + "→"
//				+ Integer.toString(this.compHigh) + " "
//				+ vi.clazz.getTyp()+"."+vi.clazz.getDepth() + "/" + vi.grpname + "(" + vi.grpidx
//				+ ")" + "/" + vi.vtxname + "(" + vi.vtxidx + ")";
		return this.getClass().getSimpleName() + ": ";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tfossi.apolge.common.p_state.guard._Guard#tstIndexGuard(java.lang
	 * .Object)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final Object index,
			final double[] yk) {
		assert false;
//		if (LOGGER)
//			logger.debug("... Compare [" + ((Long) index).intValue()
//					+ "] with [" + this.toString() + "]");
//
//		if (this.compLow == this.compHigh)
//			return ((Long) index).intValue() > this.compHigh;
//		// double[] yk = (double[]) index;
//
//		return (((Long) index).intValue() > this.compLow && yk[1] > yk[0])
//				|| (((Long) index).intValue() >= this.compHigh);
//
//		// return this.comp < ((Double [])index)[0].longValue()? true: false;
	return false;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tfossi.apolge.common.state.guard._Guard#tstGuard4Chg2Passiv(name.
	 * frahm.apolge.data.Data)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final ICoreData data) {
		assert false;
//		assert this.vi.clazz.equals(data.getClass()) : "Nur gleicher Datensatz erlaubt!";
//		double[] yk = dsc.getDigit(data, dsc.YK, this.vi.grpidx, this.vi.vtxidx);
//
//		if (LOGGER)
//			logger.debug("... Compare [" + this.vi.vtxname + "= " + yk[0]+":"+yk[1]
//					+ "] with [" + this.toString() + "]");
//		
//
//		// Ohne Hysterese
//		// y > Grenzwert, dann deaktivieren(=true)
//		// > ist wegen Idle richtig, nicht >= !
//		if (this.compLow == this.compHigh){
//			if (LOGGER)
//				logger.trace("... RESULT: yk[0] > this.compHigh "+(yk[0] > this.compHigh ? "DEAKTIVATE":"ACTIVATE"));
//			return (yk[0] > this.compHigh);
//		}
//			
//		// Mit Hysterese
//		// y > oberer Grenzwert, dann deaktivieren(=true)
//		// unterer Grenzwert > y > oberer Grenzwert, dann deaktivieren(=true),
//		// wenn y fallend (y[0]<y[1]) ist.
//		// implizit:
//		// y < unterer Grenzwert, dann aktivieren(=false)
//		if (LOGGER)
//			logger.trace("... RESULT:yk[0] > this.compLow && yk[1] > yk[0] "+
//		(yk[0] > this.compLow && yk[1] > yk[0]));
//		if (LOGGER)
//			logger.trace("... RESULT:yk[0] > this.compHigh "+(yk[0] > this.compHigh));
////		if (LOGGER)
////			logger.trace("... RESULT ALL "+!((yk[0] > this.compLow && yk[1] > yk[0])
////					|| yk[0] >= this.compHigh));
// 
//		return (yk[0] > this.compHigh) ||
//				(yk[0] > this.compLow && yk[1] > yk[0]);
	return false;
	}
//
//	// /*
//	// * (non-Javadoc)
//	// *
//	// * @see tfossi.apolge.common.p_state.guard._Guard#getTreshhold()
//	// */
//	// @Override
//	// public int getTreshhold() {
//	// return this.comp;
//	// }
//
	/*
	 * (non-Javadoc)
	 * 
	 * @see tfossi.apolge.common.p_state.guard._Guard#getVertex()
	 */
	@Override
	public final IGuideData getVertex() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
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
	private final static Logger logger = Logger.getLogger(Direct_.class
			.getPackage().getName());
}
