/**
 * 
 */
package tfossi.apolge.uefkt;

import static tfossi.apolge.common.constants.ConstValue.LFCR;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import tfossi.apolge.common.scripting.vp.VP_Tokenlist;
import tfossi.apolge.data.guide.GuideDigitData;
import tfossi.apolge.uefkt.glieder._Glied;

/**
 * TODO Comment
 *
 * @author tfossi
 * @version 26.01.2015
 * @modified -
 * @since Java 1.6
 */
public class BuildUEF {
	
	/** uefParameterStructure */
	static Map<String, List<String>> uefParameterStructure = new LinkedHashMap<String, List<String>>();

	static {
		uefParameterStructure.put("P", Arrays.asList(new String[] { "Kp" }));
		uefParameterStructure.put("PD",
				Arrays.asList(new String[] { "Kp", "Kd", "V" }));
		uefParameterStructure.put("PDT1",
				Arrays.asList(new String[] { "Kp", "Kd", "K1", "V" }));
		uefParameterStructure.put("PI",
				Arrays.asList(new String[] { "Kp", "Ki", "V" }));
		uefParameterStructure.put("PID",
				Arrays.asList(new String[] { "Kp", "Ki", "Kd", "V" }));
		uefParameterStructure.put("PIDT1",
				Arrays.asList(new String[] { "Kp", "Ki", "Kd", "K1", "V" }));
		uefParameterStructure.put("PT1",
				Arrays.asList(new String[] { "Kp", "K1", "V" }));
		uefParameterStructure.put("PT2",
				Arrays.asList(new String[] { "Kp", "K1", "D", "V" }));
		uefParameterStructure.put("I",
				Arrays.asList(new String[] { "Ki", "V" }));
		uefParameterStructure.put("D",
				Arrays.asList(new String[] { "Kd", "V" }));
		uefParameterStructure.put("DT1",
				Arrays.asList(new String[] { "Kd", "K1", "V" }));
		uefParameterStructure.put("T",
				Arrays.asList(new String[] { "K0", "V" }));
	}

	/**
	 * Einlesen der Übertragungsfunktion in den Parametersatz des States.<br>
	 * Ist <code>uefkt.isEmpty()==true</code> dann wird ein Default eingetragen:<br>
	 * 
	 * <pre>
	 * uefkt.put(&quot;fkt&quot;, &quot;P&quot;);
	 * uefkt.put(&quot;kp&quot;, new Double(1.0));
	 * </pre>
	 * 
	 * @param uefkt
	 *            ist die Scripttabelle mit den Werten
	 * @param codename ???
	 * @param vertex ??? 
	 * @return die Uebertragungsfunktion
	 * @throws SecurityException ???
	 * @throws NoSuchFieldException ???
	 */
	public static final UEFT_Parameter analyseUefkt(
			VP_Tokenlist uefkt, String codename,
			GuideDigitData vertex) throws NoSuchFieldException,
			SecurityException 
	{
		if (uefkt == null)
			return null;
		String fkt = null;
		if (LOGGER)
			logger.info("Analysiere Übertragungsfunktion" + LOGTAB + uefkt
					+ LOGTAB + "und baue die Parameter zusammen.");
		UEFT_Parameter parameter = new UEFT_Parameter();

		if (uefkt.isTable()) {

			 // Die Funktion identifizieren
			fkt = (String) ((VP_Tokenlist) uefkt.getTable().get(
					codename + ".\\?0")).getValue();
			
			if (LOGGER)
				logger.info(fkt);

			// Parameter auflösen
			Field f = null;
			for (String para : uefParameterStructure.get(fkt)) {
				f = parameter.getClass().getField(para);

				if (uefkt.getTable().containsKey(codename + "." + para)) {
					try {
						Double value = (Double) ((VP_Tokenlist) uefkt
								.getTable().get(codename + "." + para))
								.getValue();
						f.set(parameter, value);
					} catch (Exception e) {
						assert false : e.getMessage();
					}
				} else {
					try {
						if (f.getDouble(parameter) == Double.NaN)
							throw new java.lang.NoSuchFieldException(
									"Parameter [" + para + "] ist ungültig!"
											+ LFCR + uefkt);
						logger.warn("Parameter [" + para
								+ "] nicht definiert. Nehme Default="
								+ f.getDouble(parameter));

					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			assert false;
		}
		 // Was ist individuell und was ist Gruppe? Value ist individuell!
		// Funktion: P, I, D,....

		String gname = "-";
		String c = "-";
		Class<?> afkt = null;
		try {
			// Funktionen sind immer vom Typ '_Glied'
			gname = _Glied.class.getName();			

			// Namen der Klasse zusammensetzen
			c = gname.substring(0, gname.lastIndexOf(".") + 1) + fkt + "_";
//			logger.trace(c);
			
			afkt = Class.forName(c);	
//			logger.trace(afkt);
			Constructor<?> constructor = afkt.getConstructor(UEFT_Parameter
					.getClasslist(parameter));

			Object[] parameterValues = parameter.getList(parameter, vertex);

			parameter.fkt = (_Glied) constructor.newInstance(parameterValues);
		} catch (Exception e) {
			if (LOGGER)
				logger.error("Error: "
						+ e.getMessage()
						+ LFCR
						+ gname
						+ LFCR
						+ c
						+ LFCR
						+ (afkt != null ? Arrays.asList(afkt.getConstructors())
								: "fkt=null"));

			e.printStackTrace();
			assert false;
		}
		return parameter;
	}

	// ---- Selbstverwaltung --------------------------------------------------

	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(BuildUEF.class
			.getPackage().getName() + ".BuildUEF");
}
