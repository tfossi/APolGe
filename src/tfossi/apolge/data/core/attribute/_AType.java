/**
 * _AType.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core.attribute;



/**
 * Datentyp der Eigenschaft
 * 
 * @see _Atom
 * 
 * @author tfossi
 * @version 26.10.2015 (conceptional)
 * @modified -
 * @since Java 1.6
 * @param <T>
 *            Datentyp
 */
public abstract class _AType<T> {

//	/** rand */
//	Random rand = new Random();
//	"Sub", "SubElemente", "Wert", "Bemerkung", null,
//	 atypeRegister, elementRegister
	/** name */
	public final String name;
//	/** nameDescription */
//	final protected String nameDescription;
	/** value */
	public final T value;
//	/** key */
//	final protected String key;
//	/** atypeRegister */
//	final protected Map<String, _AType<?>> atypeRegister;
//	/** keyValue */
//	final protected String[][] keyValue;
//
	public final int ordinal;
//	// Erzeuge Atom
//	/**
//	 * Erzeuge ein Default-Atom
//	 * 
//	 * @return das Atom 
//	 * @modified -
//	 */
//	public abstract _Atom<T> create();
//	
//	/**
//	 * Erzeuge ein Atom mit einem bestimmten Wert
//	 * 
//	 * @param v der bestimmte Wert
//	 * @return das Atom
//	 * @modified - 
//	 */
//	public abstract _Atom<T> create(T v) ;
//	
//	/**
//	 * Erzeuge ein Atom aus einer Liste mit bestimmten Wert
//	 * 
//	 * @param v eine Liste mit bestimmten Werten
//	 * @return das Atom
//	 * @modified - 
//	 */
//	public abstract _Atom<T> create(List<?> v); 
//	
//	/** 
//	 * Erzeuge ein Atom mit einer allgemeinen Map
//	 * 
//	 * @param v eine allgemeine Map
//	 * @return das Atom
//	 * @modified - 
//	 */
//	public abstract _Atom<T> create(Map<?, ?> v) ;
//
//
//
//	/**
//	 * TODO Comment
//	 * 
//	 * @return default Value
//	 * @modified -
//	 */
//	abstract T init();
//
//	/**
//	 * Nimmt einen zufälligen Wert aus einer Liste
//	 * 
//	 * @param liste
//	 *            möglicher Kandidaten
//	 * @return zufälliger Kandidat
//	 * @modified -
//	 */
//	public T init(List<T> liste) {
//		return liste.get(this.rand.nextInt(liste.size()));
//	}
//
//	/**
//	 * TODO Comment
//	 * @param liste TODO
//	 * @return TODO
//	 * @modified - 
//	 */
//	@SuppressWarnings("static-method")
//	public List<T> initListe(List<T> liste) {
//		assert false;
//		return liste; //liste.get(this.rand.nextInt(liste.size()));
//	}
//	/**
//	 * TODO Comment
//	 * 
//	 * @param liste
//	 *            möglicher Kandidaten
//	 * @param remove
//	 *            &lt;true&gt; Kandidat wird nach der Auswahl aus Liste gelöscht
//	 * @return zufälliger Kandidat
//	 * @modified -
//	 */
//	public T init(List<T> liste, boolean remove) {
//		assert false;
//		int index = 0;
//		if (remove)
//			index = this.rand.nextInt(liste.size());
//		T t = liste.get(index);
//		liste.remove(index);
//		if (!remove)
//			liste.add(t);
//		return t;
//	}
//	// Änderung
//	/**
//	 * TODO Comment
//	 * 
//	 * @param _a TODO
//	 * @modified -
//	 */
//	abstract void change(_Atom<?> _a);
//
//	/**
//	 * TODO Comment
//	 * 
//	 * @param _a TODO
//	 * @param o TODO
//	 * @modified -
//	 */
//	abstract void change(_Atom<?> _a, Object o);
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "_ATyp: "
//				+ this.atypeRegister.get(this.name).getClass().getSimpleName()
//				+ "\n\tTypename: " + this.name + "\t" + this.nameDescription
//				+ "\n\t" + this.value + "\t" + this.key;
//	}
//
	/**
	 * 1. _AType name (super)
	 * 
	 * @param name TODO
	 * @param value TODO
	 */
	public _AType(final String name
//			, final String nameDescription
			, final T value
//			, final String key
//			, final String[][] keyValue
//			, final Map<String, _AType<?>> atypeRegister
//			, Map<Number,Element> elementRegister
			, final int ordinal
			) {
//		"Sub", "SubElemente", "Wert", "Bemerkung", null,
//		 atypeRegister, elementRegister
		this.name = name;
//		this.nameDescription = nameDescription;
		this.value = value;
//		this.key = key;
//		this.atypeRegister = atypeRegister;
//		this.elementRegister = elementRegister;
//		this.keyValue = keyValue;
		this.ordinal = ordinal;
	}
//	/**
//	 * TODO Comment
//	 * @param parameter TODO
//	 * @return TODO
//	 * @modified - 
//	 */
//	@SuppressWarnings("static-method")
//	public _Atom<?> create(String parameter) {
//		assert false:">"+parameter+"<";
//		return null;
//	}

}
