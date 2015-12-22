/**
 * 
 */
package tfossi.apolge.common.scripting.t;

import java.util.ArrayList;
import java.util.List;

import tfossi.apolge.common.scripting.vp.pm.Operation;
import tfossi.apolge.data.core.Element;
import tfossi.apolge.data.core._ElementBuilder;

/**
 *
 *
 */
public class Filter {
	private Element e;
	private final String attribute;
	private final String operation;
	private final Object compareObject;
	
	public Filter(Element e, String attribute, String op, Object compareObject){
		this.e = e;
		this.attribute = attribute;
		this.operation = op;
		this.compareObject = compareObject;
	}
	public void setElement(Element e){
		this.e = e;
	}
	public final List<Element> doFilter(Element element, _ElementBuilder eb, List<Element> liste){
		List<Element> rc = new ArrayList<Element>();
		if(liste==null){
			for(Element e: element.getAllChild(eb.name)){
				Object value = element.getAttributValue(attribute);
				
				
			}
		}else{
			assert false;
		}
		
		return rc;
	}
}
