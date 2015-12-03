/**
 * _Atom.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core.attribute;


/**
 * Eigenschaft eines Elements
 *
 * @author tfossi
 * @version 26.10.2015
 * @modified -
 * @since Java 1.6
 * @param <T>
 * 			Type des Values
 */
public abstract class _Atom<T> {
	/** value */
	public T value;
	/** Atomtyp */
	protected _AType<T> type;

	/**
	 * Basisdaten mit den Basisinformationen 
	 * @param atomtype
	 *            Das zugrundeliegende Verhalten ist im Atomtyp festgelegt.
	 */
	public _Atom(_AType<T> atomtype) {
		this.type = atomtype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.value.toString();
	}

}
