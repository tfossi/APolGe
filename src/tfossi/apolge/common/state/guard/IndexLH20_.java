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
public class IndexLH20_ implements _Guard {
//	private final int compLow;
//	private final int compHigh;
//
	/**
 * Guard überwacht, dass eine Transition einen kleineren Index hat.
	 *
	 * @param address
	 * 		ist Datenquelle
	 * @param value
	 * 		ist Referenzindex 
	 * @modified -
	 */
	public IndexLH20_(final PreAddress address, Object value) {
//		this.compLow = Math.max(0, ((Double) value).intValue() - 10);
//		this.compHigh = Math.min(100, ((Double) value).intValue() + 10);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		return this.getClass().getSimpleName() + ": "; // + Integer.toString(this.compLow) + " - "
//				+ Integer.toString(this.compHigh);
	}

	/*
	 * (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#tstIndexGuard(java.lang.Object)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final Object index,
			final double[] ykx) {
		assert false;
//		Double[] yk = (Double[]) index;
//
//		// Hysterese wird eingeschaltet, wenn yk[0] <= Min und yk[1] > yk[0]
//		// Hysterese bleibt eingeschaltet, wenn yk[0] < Max und yk[1] < yk[0]
//		return (yk[0].doubleValue() < this.compHigh && yk[1].doubleValue() < yk[0].doubleValue())
//				|| (yk[0].doubleValue() <= this.compLow && yk[1].doubleValue() >= yk[0]
//						.doubleValue()) ? true : false;
		return false;
		}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.state.guard._Guard#tstGuard4Chg2Passiv(tfossi.apolge.data.core.ICoreData)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final ICoreData data) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#getVertex()
	 */
	@Override
	public final IGuideData getVertex() {
		return null;
	}

	/*
	 * (non-Javadoc)
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
	private final static Logger logger = Logger.getLogger(IndexLH20_.class
			.getPackage().getName());
}
