package tfossi.apolge.data.core.attribute;




/**
 * TODO Comment
 *
 * @author tfossi
 * @version 26.10.2015
 * @param <T> TODO
 * @modified -
 * @since Java 1.6
 */
public class O_AType <T> extends _AType<T> {
	/**
	 * TODO Comment
	 * @param name TODO
	 * @param value TODO
	 * @modified -
	 */
	public O_AType(
			final String name 
//			final String nameDescription,
			,final T value
//			,final String key
//			final String[][] keyValue,
//			final Map<String, _
//			AType<?>> atypeRegister, 
//			final Map<Number,Element> elementRegister
			, final int ordinal
			) {
		super(
				name
//				nameDescription, 
				,value				 
//				,key
//				keyValue, 
//				atypeRegister, 
//				elementRegister
				,ordinal
		);
	}
}
