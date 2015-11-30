/**
 * 
 */
package tfossi.apolge.common.state.guard;

import static tfossi.apolge.time.pit.ConstCPPit.*;
import tfossi.apolge.common.scripting.PreAddress;
import tfossi.apolge.data.core.ICoreData;
import tfossi.apolge.data.guide.IGuideData;
import tfossi.apolge.time.pit.PPiT;


/** 
 * Guard überwacht, dass eine Transition nach einem Zeitpunkt stattfindet. 
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class Before_ implements _Guard{
	/** clock */
	private final PPiT clock;
	/**
	 * @param datetime ist Referenzzeitpunkt
	 */
	/**
	 *  Guard überwacht, dass eine Transition nach einem Zeitpunkt stattfindet.
	 *  
	 * @param address
	 * 		ist Datenquelle
	 * @param datetime
	 * 		ist Referenzzeitpunkt 
	 * @modified -
	 */
	public Before_(final PreAddress address, Object datetime){
		this.clock = string2DataTime((String)datetime);		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.getClass().getSimpleName()+": "+this.clock.getDatetime(DATE|TIME);
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#tstIndexGuard(java.lang.Object)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final Object index,
			final double[] yk){
		assert false;
//		CPiT tstClock = CM_DateTime.fill(this.clock, (CPiT)index);
//		return  CM_DateTime.before((CPiT)index, tstClock);
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
		return PPiT.class;
	}
	
}
