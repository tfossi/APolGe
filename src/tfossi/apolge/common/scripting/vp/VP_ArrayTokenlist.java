/**
 * VP_ArrayTokenlist.java
 * Branch scripting
 * APolGe
 * tfossi-team
 * licence GPLv3  
 */
package tfossi.apolge.common.scripting.vp;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import static tfossi.apolge.common.constants.ConstValue.*;
import java.util.ArrayList;

import javax.sound.midi.SysexMessage;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.t.Table;
import tfossi.apolge.common.scripting.t.TableMap;

/**
 * Liste der Tokens, die in ValueParser ausgewertet werden. Wertzuweisung einer Variable
 * Ist eine Erweiterung der ArrayList um Flags.
 * Script: 		a = 5 
 * Tokenline: 	a = INIT(5)
 * Eingetragen wird INIT, (, 5, )
 * 
 * @author tfossi
 * @version 16.08.2014
 * @modified -
 * @since Java 1.6
 */
public class VP_ArrayTokenlist <T> extends ArrayList <T> implements VP_Tokenlist <T> {

	{
//		if (LOGGER)
//			System.out.println(this.getClass().getSimpleName()+" V" + serialVersionUID);
	}
	
	public boolean add(T e){
		boolean rc = super.add((T) e);
//		
		
		return rc;
	}
//	public boolean add(Table e){
//		boolean rc = super.add(e);
//		
//		
//		return rc;
//	}
	/* (non-Javadoc)
	 * @see java.util.ArrayList#subList(int, int)
	 */
	@Override
	public  VP_Tokenlist <T> subList(int fromIndex, int toIndex) {
		VP_Tokenlist <T> r = new VP_ArrayTokenlist <T>();
		for(int index = fromIndex; index<toIndex; index++)
			r.add(this.get(index));
		return r;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#subList(int)
	 */
	@Override
	public VP_Tokenlist <T> subList(int fromIndex){
		return this.subList(fromIndex, this.size());
	}

	/**
	 * TODO Comment
	 * @modified -
	 */
	public VP_ArrayTokenlist() {
		super();
	}
	
	/**
	 * TODO Comment
	 * @param o -
	 * @modified -
	 */
	public VP_ArrayTokenlist(T o) {
		super();
		this.add(o);
//		this.setConstantsMarker();
	}


//	
	/* (non-Javadoc)
	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#isTable()
	 */
	@Override
	public final boolean isTable(){
		if(this.size()>0)
			return this.get(0) instanceof TableMap;
		return false;
	}

	/* (non-Javadoc)
	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getTable()
	 */
	@Override
	public final Table getTable(){
		if(this.isTable()){
			return (Table) this.get(0);
		}
		return null;
	}
	
//	/** complete */
	private boolean twoPass = false;

	@Override
	public final void setTwoPass(){
		this.twoPass = true;	
	}
	
	@Override
	public final void clrTwoPass(){
		this.twoPass = false;	
	}
	
	@Override
	public final boolean isTwoPass(){
		return this.twoPass;
	}
//	/** complete */
	private boolean threePass = false;

	@Override
	public final void setThreePass(){
		this.threePass = true;	
	}
	
	@Override
	public final void clrThreePass(){
		this.threePass = false;	
	}
	
	@Override
	public final boolean isThreePass(){
		return this.threePass;
	}
//	/** complete */
	private boolean init = false;

	@Override
	public final void setInit(){
		this.init = true;	
	}
	
	@Override
	public final void clrInit(){
		this.init = false;	
	}
	
	@Override
	public final boolean isInit(){
		return this.init;
	}
//	/** complete */
	private boolean flow = false;

	@Override
	public final void setFlow(){
		this.flow = true;	
	}
	
	@Override
	public final void clrFlow(){
		this.flow = false;	
	}
	
	@Override
	public final boolean isFlow(){
		return this.flow;
	}
//
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setMarker(java.lang.String, java.lang.String)
//	 */
//	@Override
//	public final String setMarker(String key, String newPreKey){
//	
//		if (key.startsWith(INDIKEY)) {
//			this.setINDIMarker();
//			return newPreKey+key.substring(1);
//		} else if (key.startsWith(SVARKEY)) {
//			this.setSVARMarker();
//			return newPreKey+key.substring(1);
//		} else if (key.startsWith(AUXVKEY)) {
//			this.setAUXVARMarker();
//			return newPreKey+key.substring(1);
//		}
//		this.setSCONMarker();
//		return newPreKey+key;	
//	}
//	
//	/** constantsmarker */
//	private boolean constantsmarker = false;
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setPass2Marker()
//	 */
//	@Override
//	public void setConstantsMarker() {	
//		this.constantsmarker = true;
//		this.svarMarker = false;
//		this.indiMarker = false;
//		this.auxvarMarker = false;	
//		this.sconMarker = false;
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getPass2Marker()
//	 */
//	@Override
//	public boolean isConstantsMarker() {
//		return this.constantsmarker;
//	}
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrPass2Marker()
//	 */
//	@Override
//	public void clrConstantsMarker() {
//		this.constantsmarker = false;
//	}
//	
//	/** definitionsmarker */
//	private boolean svarMarker = false;
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setPass2Marker()
//	 */
//	@Override
//	public void setSVARMarker() {
//		this.constantsmarker = false;
//		this.svarMarker = true;
//		this.indiMarker = false;
//		this.auxvarMarker = false;
//		this.sconMarker = false;
//	}
//
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getPass2Marker()
//	 */
//	@Override
//	public boolean isSVARMarker() {
//		return this.svarMarker;
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrPass2Marker()
//	 */
//	@Override
//	public void clrSVARMarker() {
//		this.svarMarker = false;
//	}
//	
//	/** individualsmarker */
//	private boolean indiMarker = false;
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setPass2Marker()
//	 */
//	@Override
//	public void setINDIMarker() {
//		this.constantsmarker = false;
//		this.svarMarker = false;
//		this.indiMarker = true;
//		this.auxvarMarker = false;
//		this.sconMarker = false;
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getPass2Marker()
//	 */
//	@Override
//	public boolean isINDIMarker() {
//		return this.indiMarker;
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrPass2Marker()
//	 */
//	@Override
//	public void clrINDIMarker() {
//		this.indiMarker = false;
//	}
//
//	/** addressmarker */
//	private boolean addressmarker = false;
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setAddressMarker()
//	 */
//	@Override
//	public void setAddressesMarker() {
//		this.addressmarker = true;
//	}
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getAddressMarker()
//	 */
//	@Override
//	public boolean isAddressesMarker() {
//		return this.addressmarker;
//	}
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrAddressMarker()
//	 */
//	@Override
//	public void clrAddressesMarker(){
//		this.addressmarker = false;
//	}
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#makeString()
//	 */
//	@Override
//	public String makeString(){
//		String str ="";
//		for ( Object s :this ){
//			if(s instanceof PreAddress)
//				str+= s.toString();
//			else if (s instanceof Number)
//				str+= ((Number)s).toString();
//			else if (s instanceof ArrayList)
//				str+= s.toString();
//			else
//		    str += (String)s;
//		}
//		return str;		
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getMarker()
//	 */
//	@Override
//	public String getMarker(){
//		String str ="";
//		if(this.addressmarker)
//		str+= "Addr: "+this.addressmarker;
//		if(this.constantsmarker)
//		str+= "  CONS: "+this.constantsmarker;
//		if(this.svarMarker)
//		str+= "  SVAR: "+this.svarMarker;
//		if(this.gfmarker)
//		str+= "  GFMa: "+this.gfmarker;
//		if(this.indiMarker)
//		str+= "  INDI: "+this.indiMarker;
//		if(this.readymarker)
//		str+= "  Read: "+this.readymarker;
//		if(this.auxvarMarker)
//		str+= "  AUXV: "+this.auxvarMarker;
//		if(this.sconMarker)
//		str+= "  SCON: "+this.sconMarker;
//		if(str.isEmpty())str = "./.";
//		return str;
//	}
//
//	/** temporesmarker */
//	private boolean auxvarMarker = false;
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setAUXVARMarker()
//	 */
//	@Override
//	public void setAUXVARMarker() {
//		this.constantsmarker = false;
//		this.svarMarker = false;
//		this.indiMarker = false;
//		this.auxvarMarker=true;			
//		this.sconMarker = false;
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#isAUXVARMarker()
//	 */
//	@Override
//	public boolean isAUXVMarker() {
//		return this.auxvarMarker;
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrAUXVARMarker()
//	 */
//	@Override
//	public void clrAUXVARMarker() {
//		this.auxvarMarker = false;		
//	}
//	/** spezconstantsmarker */
//	private boolean sconMarker = false;
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setPass2Marker()
//	 */
//	@Override
//	public void setSCONMarker() {
//		this.constantsmarker = false;
//		this.svarMarker = false;
//		this.indiMarker = false;
//		this.auxvarMarker = false;		
//		this.sconMarker = true;
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getPass2Marker()
//	 */
//	@Override
//	public boolean isSCONMarker() {
//		return this.sconMarker;
//	}
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrPass2Marker()
//	 */
//	@Override
//	public void clrSCONMarker() {
//		this.sconMarker = false;
//	}
//	/** readymarker */
//	private boolean readymarker = false;
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#setMAKEPassMarker()
//	 */
//	@Override
//	public void setMAKEPassMarker() {
//		this.readymarker=true;		
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#isMAKEPassMarker()
//	 */
//	@Override
//	public boolean isMAKEPassMarker() {
//		return this.readymarker;
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrMAKEPassMarker()
//	 */
//	@Override
//	public void clrMAKEPassMarker() {
//		this.readymarker = false;		
//	}
//	
//	/** gfmarker */
//	private boolean gfmarker = false;
//	
//	@Override
//	public void setGFPassMarker() {
//		this.gfmarker=true;		
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#isGFPassMarker()
//	 */
//	@Override
//	public boolean isGFPassMarker() {
//		return this.gfmarker;
//	}
//
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#clrGFPassMarker()
//	 */
//	@Override
//	public void clrGFPassMarker() {
//		this.gfmarker = false;		
//	}
//	
//	/* (non-Javadoc)
//	 * @see tfossi.apolge.common.scripting.VP_Tokenlist#getValue()
//	 */
//	@Override
//	public final Object getValue() {
//		return this.get(0);
//	}
	
	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(VP_ArrayTokenlist.class
			.getPackage().getName()+".VP_ArrayTokenlist");


}
