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
 * TODO Comment
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class Pre_ implements _Guard{
//	private final static GDC gdc = new GDC();
//	private final GuideData vertex;
//	/**
//	 * @param vertex ist Herkunftvertext der Transition
//	 */
	 /**
	  *  @param address
	 * 		ist Datenquelle
	 * @param value
	 * 		ist Referenzindex 
	 * @modified -
	 */
	public Pre_(final PreAddress address, Object value){
//		this.vertex = vertex;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.getClass().getSimpleName()+": "; //+gdc.getName(vertex);
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#tstIndexGuard(java.lang.Object)
	 */
	@Override
	public final boolean tstGuard4Chg2Passiv(final Object index,
			final double[] yk){
			return true;
	}

	@Override
	public final boolean tstGuard4Chg2Passiv(final ICoreData data) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#getVertex()
	 */
	@Override
	public final IGuideData getVertex(){
//		return this.vertex;
		return null;
	}
	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.p_state.guard._Guard#getGuardType()
	 */
	@Override
	public Class<?> getGuardType() {		
		return this.getClass();
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(Pre_.class
			.getPackage().getName());
}
