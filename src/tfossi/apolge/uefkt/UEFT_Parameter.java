/**
 * 
 */
package tfossi.apolge.uefkt;

import static tfossi.apolge.common.constants.ConstValue.LFCR;

import java.util.ArrayList;
import java.util.List;

import tfossi.apolge.data.guide.GuideDigitData;
import tfossi.apolge.uefkt.glieder._Glied;

//import tfossi.apolge.common.state.StateBuilder;

/**
 * Definition der Parameter und Funktionen für eine Übertragungsfunktion.
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class UEFT_Parameter implements Cloneable {

	/** fkt */
	public _Glied fkt;
	/** K0 */
	public double K0 = Double.NaN;
	/** K1 */
	public double K1 = Double.NaN;
	/** Ki */
	public double Ki = Double.NaN;
	/** Kd */
	public double Kd = Double.NaN;
	/** Kp */
	public double Kp = Double.NaN;
	/** D */
	public double D = Double.NaN;
	/** V */
	public double V = 1.0;

	/**
	 * Liefert ein Array von Paramtern, die für 'getConstructor' der
	 * Übertragungs-Funktion in analyseUefk benötigt wird
	 * 
	 * @param para
	 *            ist der Parameter
	 * @return das Array der Klassen
	 * @modified - 
	 */
	@SuppressWarnings("unchecked")
	public static final Class<? extends Number>[] getClasslist(
			final UEFT_Parameter para) {
		
		List<Class<?>> lc = new ArrayList<Class<?>>();

		if (!Double.isNaN(para.Kp))
			lc.add(Double.class);
		if (!Double.isNaN(para.Kd))
			lc.add(Double.class);
		if (!Double.isNaN(para.Ki))
			lc.add(Double.class);
		if (!Double.isNaN(para.K1))
			lc.add(Double.class);
		if (!Double.isNaN(para.D))
			lc.add(Double.class);
		if (!Double.isNaN(para.K0))
			lc.add(Double.class);
		if (!Double.isNaN(para.V))
			lc.add(Double.class);

		lc.add(GuideDigitData.class);

		return lc.toArray(new Class[0]);
	}

	/**
	 * Liefert ein Array von Objekten für die Instanzierung durch den
	 * Constructor der Übertragungsfunktion.<br>
	 * Parameters:<br>
	 * initargs - array of objects to be passed as arguments to the constructor
	 * call; values of primitive types are wrapped in a wrapper object of the
	 * appropriate type (e.g. a float in a Float)
	 * 
	 * @param p ???
	 * @param vertex ???
	 * @return das Parameter-Objectarray zur Instanzierung
	 * @modified - 
	 */
	public final Object[] getList(final UEFT_Parameter p,
			final GuideDigitData vertex) {
		List<Object> lc = new ArrayList<Object>();

		if (!Double.isNaN(this.Kp))
			lc.add(new Double(this.Kp));
		if (!Double.isNaN(this.Kd))
			lc.add(new Double(this.Kd));
		if (!Double.isNaN(this.Ki))
			lc.add(new Double(this.Ki));
		if (!Double.isNaN(this.K1))
			lc.add(new Double(this.K1));
		if (!Double.isNaN(this.D))
			lc.add(new Double(this.D));
		if (!Double.isNaN(this.K0))
			lc.add(new Double(this.K0));
		if (!Double.isNaN(this.V))
			lc.add(new Double(this.V));

		lc.add(vertex);

		Object[] rc = new Object[lc.size()];
		for (int i = 0; i < lc.size(); i++)
			rc[i] = lc.get(i);

		return rc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public final String toString() {
		String str = new String();
		if (this.fkt == null)
			str += "fkt: -" + LFCR;
		else {
			str += " fkt: " + this.fkt.toString() + LFCR;
		}
		return str;

	}

	/**
	 * @return die Konstanten als String
	 */
	public final String getKtoString() {
		String str = new String();
		if (!Double.isNaN(this.Kp))
			str += " kp= " + this.Kp;
		if (!Double.isNaN(this.Ki))
			str += " ki= " + this.Ki;
		if (!Double.isNaN(this.Kd))
			str += " kd= " + this.Kd;
		if (!Double.isNaN(this.K1))
			str += " k1= " + this.K1;
		if (!Double.isNaN(this.D))
			str += " d= " + this.D;
		if (!Double.isNaN(this.K0))
			str += " K0= " + this.K0;
		if (this.V != 1.0)
			str += "  V= " + this.V;
		return str;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public UEFT_Parameter clone() {
		try {
			return (UEFT_Parameter) super.clone();
		} catch (CloneNotSupportedException e) {
			// Kann eigentlich nicht passieren, da Cloneable
			throw new InternalError();
		}
	}
}
