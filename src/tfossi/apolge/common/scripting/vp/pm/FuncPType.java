/**
 * 
 */
package tfossi.apolge.common.scripting.vp.pm;

import java.lang.reflect.Type;
import java.util.Arrays;

import tfossi.apolge.common.scripting.vp.Function;

/**
 * Beschreibt die Parameter einer Funktion und legt die Aufrufmethode 'calculate' fest.
 * 
 * @author tfossi
 * @version 01.08.2014
 * @modified -
 * @since Java 1.6
 */
public class FuncPType {
	/** Array der parameterTypen des Methodenaufrufs */
	public Type[] parameterType;
	
	/** Aufrufmethode 'calculate' in der Schnittstelle Function */
	public Function function;
	
	/** values */
	public Object[] values;

	/**
	 * Stringpräsentaion der Funktion und ihrer Parametertypen
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		String rc;
		if(this.values==null)
		rc = "f:=" + this.function + "("
				+ Arrays.asList(this.parameterType) + ") Nur Typen, keine Parameter!";
		else{
			rc = "f:=" + this.function + "( ";
					
			if(this.values.length>0){
			for(int i = 0; i < this.values.length;i++){
				if(this.values[i].getClass().getComponentType()==null)
					rc += this.values[i]+", ";
				else
					rc += Arrays.asList((Object[])this.values[i])+", ";
			}
			}else{
				rc += " ";
			}
			rc = rc.substring(0, rc.length()-2)+" ) mit "+(this.function.threePass()?"Pass3":(this.function.twoPass()?"Pass2":"Pass?"))+"-Parameter!";
		}
		return rc;
	}
}

