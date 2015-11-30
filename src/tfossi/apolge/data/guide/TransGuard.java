/**
 * 
 */
package tfossi.apolge.data.guide;

import java.util.ArrayList;
import java.util.List;

import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.common.state.guard.GuardList;

/** 
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
abstract public class TransGuard extends GuideData {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ---- Wächter -----------------------------------------------------------


	/**
	 * TODO Comment
	 * @param dgl aktueller DGL
	 * @param parameter Parameter
	 * @modified -
	 */
	public TransGuard(final DataGuidelineLevel dgl,
			final VP_Tokenlist[] parameter) {
		super(dgl, parameter, false, false, true);
	} 

	/** guard */
	final List<GuardList> guard = new ArrayList<GuardList>();
}
