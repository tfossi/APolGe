/**
 * 
 */
package tfossi.apolge.common.state;

//import tfossi.apolge.common.state.guard._Guard;
//import tfossi.apolge.common.state.vertex.IGuideline;
//import tfossi.apolge.data.core.Data;
import tfossi.apolge.common.scripting.PreAddress;


/**
 * TODO Comment
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class TriggerScript {
//	public enum TRIGGERDATA {
//		OC, OS, OV; 
//	}
//	
//	public final String[] triggerparameter = new String[TRIGGERDATA.values().length]; 
//		private final static GDC gdc = new GDC();
//	
//		// Ausgefüllt durch "buildVertexTriggerGuideline"
////		public IGuideline igl;
//		public GuideData igl;
//		public StateGroupGuideline osg;
//		public Datenklasse oclazz;
//		
//		public Data oData;
//		
//		 Ausgefüllt durch "VertexGuideline" 
//		public String OC = null;
//		public String OS = null;
//		public String OV = null;
	/** adr */
	public PreAddress adr = null;
//		
//		public _Guard guard = null; 
//		
//		/**
//		 * Prüfe, ob Trigger ausgelöst werden soll
//		 * @param yk
//		 * 			Steuerdaten
//		 * @return true, wenn der Change aus der Liste gelöscht werden soll
//		 */
////		public boolean check(double[] yk) {
////			// Sind die Grenzwerte erreicht?
////			// a. GT
////			// b. LT
////			// c. UP
////			// d. DOWN
////			// e. IMMER
////			cv.execute();
////			return false;
////		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString(){
//			String str = new String("OC = "+this.OC+"/"+(this.oclazz==null?null:this.oclazz.getTyp()+"."+this.oclazz.getDepth())+", ");
//			str += "OS = "+this.OS+"/"+(this.osg==null?null:(gdc.getName(this.osg)+"("+gdc.getIDX(this.osg)+")"))+", ";
//			str += "OV = "+this.OV+"/"+(this.igl==null?null:gdc.getName(igl)+"("+gdc.getIDX(igl)+")")+", ";
//			str += "Guard = "+(this.guard==null?null:this.guard);		;
			return this.adr.toString();
			
		}
}
