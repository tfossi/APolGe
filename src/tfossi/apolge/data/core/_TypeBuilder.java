/**
 * _TypeBuilder.java
 * TODO branch
 * APolGe
 * tfossi-team
 * licence GPLv3   
 */
package tfossi.apolge.data.core;


/**
 * TODO Comment
 *
 * @author tfossi
 * @version 28.10.2015
 * @modified -
 * @since Java 1.6
 */
class _TypeBuilder {
//	/**
//	 * Erstelle Basisdatentypen<br>
//	 * B_AType:BoolFalse<br>
//	 * B_AType:BoolTrue <br>
//	 * B_AType:BoolRandom<br>
//	 * <br>
//	 * T_AType<String>:Text<br>
//	 * T_AType<String>:TextRemove<br>
//	 * T_AType<String>:TextSerial<br>
//	 * T_AType<List<String>>:TextListe<br>
//	 *<br>
//	 * L_AType<Element>:Link<br>
//	 * L_AType<Element>:LinkRemove<br>
//	 * L_AType<Element>:LinkSerial<br>
//	 * L_AType<List<Element>>:LinkListe<br>
//	 * L_AType<Map<Number,Element>>:LinkListe<br>
//	 * 	<br>
//	 * N_AType<Number>:Number<br>
//	 *<br>
//	 * S_AType:Sub<br>
//	 * D_AType:Date<br>
//	 * <br>
//	 * @param atypeRegister  TODO
//	 * @param elementRegister  TODO
//	 * 
//	 * @modified -
//	 */
//	@SuppressWarnings("unused")
//	static void builder(Map<String, _AType<?>> atypeRegister, final Map<Number, Element> elementRegister) {
//		// Builder bauen
//
//		// Erzeugt einen Bool-Typen mit default-Wert false
//		new B_AType("Bool", "boolesche Werte", "Wert", "Bedeutung", null,
//				atypeRegister, elementRegister);
//		// Erzeugt einen Bool-Typen mit default-Wert true
//		new B_AType("BoolTrue", "boolesche Werte", "Wert", "Bedeutung", null,
//				atypeRegister, elementRegister) {
//			@SuppressWarnings("boxing")
//			@Override
//			public Boolean init() {
//				return true;
//			}
//		};
//		// Erzeugt einen Bool-Typen mit zufälligem default-Wert
//		new B_AType("BoolRandom", "boolesche Werte", "Wert", "Bedeutung", null,
//				atypeRegister, elementRegister) {
//			@SuppressWarnings("boxing")
//			@Override
//			public Boolean init() {
//				 return this.rand.nextBoolean();
//			}
//
////			/*
////			 * (non-Javadoc)
////			 * 
////			 * @see
////			 * tfossi.apolge.data.core.B_AType#change(tfossi.apolge.data.core
////			 * ._Atom)
////			 */
////			@Override
////			public void change(_Atom<?> a) {
////				// Input:
////				// 1. Daten zusammenstellen
////				// 2. Entscheidungsbedingungen festlegen
////				// 3. Anhand der Daten Entscheidungen treffen
////				// 4. Maßnahmen einleiten
////				//
////
////				Object o = elementRegister.get(new Integer(0)).getValue("Gender");
////
////				System.out.println("Änderung auf Basis " + o);
////				@SuppressWarnings("unchecked")
////				_Atom<Boolean> _a = (_Atom<Boolean>) a;
////				_a.value = new Boolean(false);
////			}
//		};
//
//		new T_AType<String>("Text", "textuale Werte", "Wert", "Bemerkung", null,
//				atypeRegister, elementRegister);
////		new T_AType<String>("TextRemove", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<String> create(List<?> liste) {
////				return this.atypeRegister.get(this.name).getClass().cast(this)
////						.create(liste, true);
////			}
////		};
////		new T_AType<String>("TextRandom", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<String> create(List<?> liste) {
////					return this.atypeRegister.get(this.name).getClass().cast(this)
////						.create(liste);
////			}
////		};
////		new T_AType<String>("TextSerial", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<String> create(List<?> liste) {
////				return this.atypeRegister.get(this.name).getClass().cast(this)
////						.create(liste, false);
////			}
////		};
////		new T_AType<List<String>>("TextListe", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<List<String>> create( List<?> liste) {
////				return this.atypeRegister.get(this.name).getClass().cast(this)
////						.createListe(liste);
////			}
////		};
//
//
//		new L_AType<Element>("Link", "textuale Werte", "Wert", "Bemerkung", null,
//				atypeRegister, elementRegister);
////		new L_AType<Element>("LinkRemove", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<Element> create(List<?> liste) {
////				return this.atypeRegister.get(this.name).getClass().cast(this)
////						.create(liste, true);
////			}
////		};
////		new L_AType<Element>("LinkSerial", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<Element> create(List<?> liste) {
////				return this.atypeRegister.get(this.name).getClass().cast(this)
////						.create(liste, false);
////			}
////		};
////		new L_AType<List<Element>>("LinkListe", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<List<Element>> create( List<?> liste) {
////				return this.atypeRegister.get(this.name).getClass().cast(this)
////						.createListe(liste);
////			}
////		};
////		new L_AType<Map<Number,Element>>("LinkListe", "textuale Werte", "Wert", "Bemerkung", null,
////				atypeRegister, elementRegister){
////			
////			@Override
////			@SuppressWarnings("unchecked")
////			public _Atom<Map<Number,Element>> create( Map<?,?> map) {
////				return this.atypeRegister.get(this.name).getClass().cast(this)
////						.create(map);
////			}
////		};
//		
//		new N_AType<Number>("Number", "Zahlenwerte", "Wert", "Bemerkung", null,
//				atypeRegister, elementRegister);
////		 atypeRegister, elementRegister);
////		// new D_AType("Date", "Datumswerte", "Wert", "Bemerkung", null,
////		// atypeRegister);
//		 new S_AType("Sub", "SubElemente", "Wert", "Bemerkung", null,
//				 atypeRegister, elementRegister);
//		 new D_AType("Date", "Datum", "Wert", "Bemerkung", null,
//				 atypeRegister, elementRegister);
////		 new E_AType<Element>("Element", "Element", "Wert", "Bemerkung", null,
////				 atypeRegister, elementRegister);
//	}
}
