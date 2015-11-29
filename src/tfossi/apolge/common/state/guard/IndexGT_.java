/**
 * 
 */
package tfossi.apolge.common.state.guard;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.PreAddress;
import tfossi.apolge.data.core.ICoreData;
import tfossi.apolge.data.guide.IGuideData;

/**
 * Guard überwacht, dass eine Transition einen größeren Index hat.
 * 
 * @.hist 0.00.212 _Guards Umstellung auf State-Map
 * @.reviewedby
 * @author Thomas Frahm &lt;<a href="mailto:tfrahm@stones.com"
 *         >tfrahm@stones.com</a>&gt;
 * @version 1.0 States
 * @since java version "1.6.0_0"
 * @.license GPL
 */
public class IndexGT_ implements _Guard {
//
//	private final CDC dsc = new CDC();
//	private final GDC gdc = new GDC();
//	
	/** address */
	private final PreAddress address;
//	private  GuideData vi;
	/** comp */
	private final int comp;
//
	/** compLow */
	private final int compLow;
	/** compHigh */
	private final int compHigh;

 /**
	 * TODO Comment
	 * @param address ???
	 * @param value ist der Referenzwert
	 * @modified -
	 */
	public IndexGT_(/*GuideData vi,*/final PreAddress address,  Object value) {
//		this.vi = vi;
		this.address = address;
		if (value instanceof Double) {
			this.comp = ((Double) value).intValue();
			this.compLow = this.comp;
			this.compHigh = this.comp;
		} else if (value instanceof Double[]) {
			Double[] o = (Double[]) value;
			this.compLow = o[0].intValue();
			this.compHigh = o[1].intValue();
			this.comp = this.compHigh;
		} else {
			this.comp = 0;
			this.compLow = 0;
			this.compHigh = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": "
				+ Integer.toString(this.compLow) + "→"
				+ Integer.toString(this.compHigh) + " "
				+ (this.address==null?"@null":"@"+this.address.toString())+" "
//				+ gdc..vi.clazz.getTyp()+"."+vi.clazz.getDepth() + "/" + vi.grpname + "(" + vi.grpidx
//				+ ")" + "/" + gdc.getName(vi) + "(" + gdc.getIDX(vi) + ")"
				;
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
//		if (LOGGER){
//			logger.info("");
//			logger.info("- Guard -");
//		}
//		if (LOGGER)
//			logger.debug("... Compare "+LOGTAB+"[" + ((Long) index).intValue()
//					+ "] with "+LOGTAB+"[" + this.toString() + "]");
//
//		// Ist einfacher Schwellwert   
//		if (this.compLow == this.compHigh)
//			return ((Long) index).intValue() > this.compHigh;
//	
//		// Ist Hysterese
//		return (((Long) index).intValue() > this.compLow && yk[1] > yk[0])
//				|| (((Long) index).intValue() >= this.compHigh);
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
////FIXME 		assert gdc.this.vi.clazz.equals(dsc.getDatenklasse(data)) : "Nur gleicher Datensatz erlaubt!";
//		double[] yk = dsc.getDigit(data, dsc.YK, gdc.getIDX(gdc.getSuperGuideData(vi)), gdc.getIDX(vi));
//
//		if (LOGGER)
//			logger.debug("......... Compare "+LOGTAB+"[" + gdc.getName(vi) + "= " + yk[0]+":"+yk[1]
//					+ "] with "+LOGTAB+"[" + this.toString() + "]");
//		
//
//		// Ohne Hysterese
//		// y > Grenzwert, dann deaktivieren(=true)
//		// > ist wegen Idle richtig, nicht >= !
//		if (this.compLow == this.compHigh){
//			if (LOGGER)
//				logger.trace("......... RESULT: "+LOGTAB+"Idle=yk[0] ? IndexGT.compHigh "+(yk[0] > this.compHigh ? "DEAKTIVATE":"ACTIVATE"));
//			return (yk[0] > this.compHigh);
//		}
//			assert false;
//		// Mit Hysterese
//		// y > oberer Grenzwert, dann deaktivieren(=true)
//		// unterer Grenzwert > y > oberer Grenzwert, dann deaktivieren(=true),
//		// wenn y fallend (y[0]<y[1]) ist.
//		// implizit:
//		// y < unterer Grenzwert, dann aktivieren(=false)
//		if (LOGGER)
//			logger.trace("... RESULT:"+LOGTAB+"yk[0] > this.compLow && yk[1] > yk[0] "+
//		(yk[0] > this.compLow && yk[1] > yk[0]));
//		if (LOGGER)
//			logger.trace("... RESULT:"+LOGTAB+"yk[0] > this.compHigh "+(yk[0] > this.compHigh));
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
	private final static Logger logger = Logger.getLogger(IndexGT_.class
			.getPackage().getName());
}
