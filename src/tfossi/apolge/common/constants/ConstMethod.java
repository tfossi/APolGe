/** 
 * ConstMethod.java
 * Branch master
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.constants;

import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;
import static tfossi.apolge.common.constants.ConstValueExtension.W_68;
import static tfossi.apolge.common.constants.ConstValueExtension.W_95;
import static tfossi.apolge.common.constants.ConstValueExtension.W_99;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Button;

import tfossi.apolge.common.constants.ConstValueExtension.Sigma;
import tfossi.apolge.common.scripting.LoadScript;
import tfossi.apolge.common.scripting.LoadScriptException;
import tfossi.apolge.common.scripting.ScriptException;
import tfossi.apolge.common.scripting.p.ParseException;
import tfossi.apolge.common.scripting.t.Filter;
import tfossi.apolge.common.scripting.vp.pm.PatternMaps;
import tfossi.apolge.data.core.Element;
import tfossi.apolge.io.Screen;
import Jama.Matrix;

/**
 * Globale Methoden
 * 
 * @author tfossi
 * @since Java 1.6
 * @version 10.08.2014
 * @modified -
 */
public class ConstMethod extends ConstValue {

	/**
	 * Testmethode Element-ID
	 * 
	 * @return ID
	 * @modified -
	 */
	public static int ident() {
		return 1;
	}	
	/**
	 * Testmethode Element-ID
	 * 
	 * @return ID
	 * @modified -
	 */
	public static Integer WENN(Boolean a, Integer b, Integer c) {
		return (a?b:c);
	}
	public static Long WENN(Boolean a, Long b, Long c) {
		return (a?b:c);
	}
	public static Float WENN(Boolean a, Float b, Float c) {
		return (a?b:c);
	}
	public static Double WENN(Boolean a, Double b, Double c) {
		return (a?b:c);
	}
	public static String WENN(Boolean a, String b, String c) {
		return (a?b:c);
	}


	/**
	 * Addressenzugriff: Auf Attribut des Elements zugreifen
	 * 
	 * @param element
	 * 			das Element
	 * @param attribut
	 *            Attribut im aktuellen Element
	 * @return Attributvalue
	 * @modified -
	 */
	public static Object ADR1(Element element, String attribut) {
		Object rc = element.getAttributValue(attribut);
		
		return new Integer(10);
	}
	
	/**
	 * Addressenzugriff: Auf Attribut eines übergeordneten Elements des gleichen Zweigs zugreifen
	 * 
	 * @param e
	 * 			das Element 
	 * @param elementBuilder
	 * 			das über- / untergeordnete Element
	 * @param attribute
	 *            Attribut im aktuellen Element
	 * @return Attributvalue
	 * @modified -
	 */
	public static Object ADR3(Element e, String elementBuilder, String attribute ) {
//		for (String s : str)
//			System.err.println("CM:ADR=" + s);
		return new Long(5);
	}
	/**
	 * Addressenzugriff: Auf Attribut eines übergeordneten Elements des gleichen Zweigs zugreifen
	 * 
	 * @param e
	 * 			das Element 
	 * @param elementBuilder
	 * 			das über- / untergeordnete Element
	 * @param attribute
	 *            Attribut im aktuellen Element
	 * @return Attributvalue
	 * @modified -
	 */
	public static Object ADR3(Element e, String elementBuilder, String element, String attribute ) {
//		for (String s : str)
//			System.err.println("CM:ADR=" + s);
		return new Long(5);
	}
	/**
	 * Addressenzugriff: Auf Attribut eines nebengeordneten Elements eines anderen Zweigs zugreifen
	 * 
	 * @param e
	 * 			das Element 
	 * @param direction
	 * 			das über- / untergeordnete Element
	 * @param attribut
	 *            Attribut im aktuellen Element
	 * @return Attributvalue
	 * @modified -
	 */
	public static Object ADR3(Element e, String eBuilder, String element, String subelement, String attribute ) {
//		for (String s : str)
//			System.err.println("CM:ADR=" + s);
		return new Long(5);
	}

	public static List<Element> LIST(Element e, String eBuilder){
		return e.getAllChild(eBuilder);		
	}
	
	public static Filter FILTER(Element e, String attribute, String op, Object compareObject){
		return new Filter(new Element(), attribute, op, compareObject);
	}
//	public static Object ADR2(Element e, String SameEbene, String attribute ) {
////		for (String s : str)
////			System.err.println("CM:ADR=" + s);
//		return new Long(5);
//	}

	/** rand */
	public final static Random rand = new Random();

	/**
	 * Konvertiert einen HTML-Text in Text.
	 * 
	 * @param sb
	 *            ist der HTML-Text. Ergebnis steht wieder in <code>sb</code>
	 * @pre HTML-Text
	 * @post TEXT
	 */
	public final static void html2txt(final StringBuffer sb) {
		if (LOGGER)
			logger.debug("DEBUG");
		int zhead = sb.indexOf("</head>");
		sb.delete(0, zhead + 7);
		sb.insert(0, " ");
		cutIt(sb, "<body>", "");
		cutIt(sb, "</body>", "");
		cutIt(sb, "</html>", "");
		cutIt(sb, "\t", "");
		cutIt(sb, "<br>", LFCR);
		cutIt(sb, "<br />", LFCR);
		cutIt(sb, "<p>", "");
		cutIt(sb, "</p>", LFCR);

		between(sb, "<h1>", "</h1>", null);
		between(sb, "<h2>", "</h2>", null);
		between(sb, "<h3>", "</h3>", null);
		cutIt(sb, "<h1>", LFCR);
		cutIt(sb, "</h1>", LFCR);
		cutIt(sb, "<h2>", LFCR);
		cutIt(sb, "</h2>", LFCR);
		cutIt(sb, "<h3>", LFCR);
		cutIt(sb, "</h3>", LFCR);

		between(sb, "<", ">", "");

	}

	/**
	 * Schneidet aus Text etwas heraus und ersetzt es
	 * 
	 * @param sb
	 *            der StringBuffer mit dem Text
	 * @param cut
	 *            was herauszuschneiden ist
	 * @param replace
	 *            was zu ersetzen ist
	 */
	private final static void cutIt(final StringBuffer sb, final String cut,
			final String replace) {
		try {
			int br = 0;
			int len = cut.length();
			while (true) {
				br = sb.indexOf(cut, br);
				sb.replace(br, br + len, replace);
			}
		} catch (StringIndexOutOfBoundsException e) {
			// leer
		}
	}

	/**
	 * Schneidet etwas zwischen zwei Marken heraus (inkl. der Marken) und
	 * ersetzt es
	 * 
	 * @param sb
	 *            der StringBuffer
	 * @param head
	 *            erste Marke
	 * @param tail
	 *            zweite Marke
	 * @param replace
	 *            Einzusetzender String oder null
	 */
	private final static void between(final StringBuffer sb, final String head,
			final String tail, final String replace) {

		try {
			int aindex = -1, zindex = -1;
			int alen = head.length();
			int zlen = tail.length();
			while ((aindex = sb.indexOf(head, aindex + 1)) != -1
					&& (zindex = sb.indexOf(tail, aindex)) != -1) {
				if (replace == null) {
					sb.replace(aindex + alen, zindex,
							sb.substring(aindex + alen, zindex).toUpperCase());
				} else {
					sb.delete(aindex, zindex + zlen);
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
			assert false : "Unsauber, einen Ausstieg mittels Exception";
		}
	}

	/**
	 * Daten eines beliebigen Liste vom Type
	 * <code>ArrayList &lt;String&gt; </code> filtern
	 * 
	 * @param orgListe
	 *            ist die Ausgangsliste
	 * @param feldname
	 *            ist das Feld, nachdem gefiltert werden soll
	 * @param pattern
	 *            ist der Patter
	 * @return ist die neue Liste
	 */
	public final static List<Object> filter(final List<?> orgListe,
			final String feldname, final String pattern) {
		Object[] o = { pattern, null };
		return filter(orgListe, feldname, o);
	}

	/**
	 * Filtert Daten einer beliebigen Liste vom Type
	 * <code>ArrayList &lt;?&gt; </code>.
	 * 
	 * @param orgListe
	 *            ist die Ausgangsliste
	 * @param feldname
	 *            ist das Feld, nachdem gefiltert werden soll
	 * @param pattern
	 *            ist das Array mit unteren[0] und oberen[1] inklusivem
	 *            Grenzwert. Gibt es keinen Grenzwert, ist der Wert
	 *            <code>null</code> einzusetzen. Bei Strings ist der untere
	 *            Genzwert das Pattern, der oberer Grenzwert ist null.
	 * @return die gefilterte Liste
	 */
	public final static List<Object> filter(final List<?> orgListe,
			final String feldname, final Object[] pattern) {
		@SuppressWarnings("unused")
		List<Object> rc = new ArrayList<Object>(orgListe.size());
		if (orgListe.size() == 0)
			// Abbruch, wenn die Arbeitsliste leer ist.
			return rc;
		try {
			// Wir benötigen für das Feld, nach dem gefiltert werden soll,
			// die Class. Über Generics geht das nicht, da diese zur Laufzeit
			// nicht zur verfügung stehen. Also wird das erste Element
			// herangezogen
			Class<? extends Object> clazz = orgListe.get(0).getClass();

			// for(Field fd : clazz.getDeclaredFields())
			// System.out.println(fd.getName());
			Field field = clazz.getDeclaredField(feldname);
			field.setAccessible(true);

			// Jetzt alle Felder durchgehen
			for (Object value : orgListe) {
				// DEV invers == true, wenn genau das Gegenteil gefiltert werden
				// soll
				if (value instanceof String) {
					// Value ist keine spezielle Klasse, sondern String!
					// Direkt verarbeiten!
					if (tstValue((String) value, pattern))
						rc.add(value);
				} else
					assert false : value.getClass();
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.exit(0);
			// } catch (IllegalAccessException e) {
			// e.printStackTrace();
			// System.exit(0);
		}
		return rc;
	}

	/**
	 * Testet den Wert, ob er im Range oder bei Strings im Pattern liegt
	 * 
	 * @param value
	 *            der Wert
	 * @param pattern
	 *            das reguläre Pattern in <code>pattern[0]</code>
	 * @return true, wenn der Wert im Pattern matched
	 */

	private final static boolean tstValue(final String value,
			final Object[] pattern) {
		boolean rc = false;
		for (Matcher m = Pattern.compile((String) pattern[0]).matcher(value); m
				.find();) {
			rc = true;
			break;
		}
		return rc;
	}

	/**
	 * Fügt einen oder mehrere Werte in einem Text hinzu. Die Stellen sind mit
	 * <code>#'Zahl'</code> gekennzeichnet, wobei die Zahl dem Feldindix des
	 * geweiligen Arraywertes ist, der eingesetzt werden soll.<br>
	 * Damit können in statischen Texten (HTML, ASCII) dynamische Informationen
	 * untergebracht werden. Ist das Kennzeichen <code>#'Zahl'</code> mehrfach
	 * vorhanden, werden in Allen der Parameter eingetragen.
	 * 
	 * @param screen
	 *            Ist der (Text-)Screen.
	 * @param parameter
	 *            ist das Array der einzutragenden Werte
	 * @return screen mit Parametereinträgen
	 */
	public final static StringBuffer insertParameter(final StringBuffer screen,
			final String[] parameter) {
		assert screen != null : "Screen == null";
		assert parameter != null : "Parameter == null";
		StringBuffer sb = screen;
		for (int i = 0; i < parameter.length; i++) {
			int pos = -1;
			String strw = "#" + i;
			int strwl = strw.length();
			while ((pos = sb.indexOf(strw, ++pos)) >= 0) {
				sb = sb.replace(pos, pos + strwl, parameter[i]);
			}
		}
		return sb;
	}

	/**
	 * TODO Comment
	 * 
	 * @param screen
	 *            -
	 * @param parameter
	 *            -
	 * @return -
	 * @modified -
	 */
	public final static StringBuffer insertParameter(final StringBuffer screen,
			final Map<String, String> parameter) {
		assert false;
		assert screen != null : "Screen == null";
		assert parameter != null : "Parameter == null";
		StringBuffer sb = screen;
		for (String key : parameter.keySet()) {
			int pos = -1;
			String strw = "#" + key;
			int strwl = strw.length();
			String content = (parameter.get(key) == null ? "***NDEF***"
					: parameter.get(key));
			while ((pos = sb.indexOf(strw, ++pos)) >= 0) {

				sb = sb.replace(pos, pos + strwl, content);
			}
		}
		return sb;
	}

	// if(value instanceof Integer){
	// int lv = Integer.parseInt(this.lowerValue.toString());
	// int uv = Integer.parseInt(this.upperValue.toString());
	// if( ((Integer) value).intValue()>= lv &&
	// ((Integer) value).intValue()<= uv ){
	// return true;
	// }
	// }else if(value instanceof Gender){
	// try {
	// Gender lv = Gender.valueOf(this.lowerValue.toString());
	// // Da reicht der erste Wert
	// if( ((Gender) value).equals(lv)){
	// return true;
	// }
	// } catch (IllegalArgumentException e) {
	// jlog.logp(Level.WARNING, this.getClass().getSimpleName(), "tst",
	// "Ist "+this.lowerValue.toString()+" eine Fehleingabe?");
	// return true;
	// }
	// }else if(value instanceof Familienstand){
	// if(this.lowerValue==null)
	// return true;
	// try {
	// Familienstand lv = Familienstand.valueOf(this.lowerValue.toString());
	// Familienstand uv = null;
	// if(this.upperValue!=null)
	// uv = Familienstand.valueOf(this.upperValue.toString());
	// // Ein oder zwei Familienstandswerte sind möglich
	// if( ((Familienstand) value).equals(lv) ||
	// ((Familienstand) value).equals(uv)){
	// return true;
	// }
	// } catch (IllegalArgumentException e) {
	// jlog.logp(Level.WARNING, this.getClass().getSimpleName(), "tst",
	// "Ist "+this.lowerValue.toString()+" oder so eine Fehleingabe?");
	// return true;
	// }
	// }else if(value instanceof DataTime){
	// // Zwischen den Datum-Lower und Datum-Upper
	// DataTime lv = string2DataTime(this.lowerValue.toString());
	// if(lv.getYear()==-1) lv.setYear(((DataTime) value).getYear());
	// DataTime uv = string2DataTime(this.upperValue.toString());
	// if(uv.getYear()==-1) uv.setYear(((DataTime) value).getYear());
	// if( ((DataTime) value).after(lv) &&
	// ((DataTime) value).before(uv)){
	// return true;
	// }
	// }else{
	// throw new NullPointerException(value.getClass()+" ist nicht definiert.");
	// }

	// ---- Globale statische Methoden
	// -------------------------------------------
	// public final static Matrix MATRIX(int n, int m, Object [][] o) {
	// return new Matrix(n, m, o);
	// }
	// // n x m
	// // o[n][m]
	// // o[n]
	// public final static Matrix MATRIX(Object[] ... o) {
	// int n = o.length;
	// int m = 1;
	// Object [][] matrix = new Matrix[n][];
	// for (int i=0; i< n;i++){
	// m = o[i].length>m?o[i].length:m;
	// }
	// for (int i=0; i< n;i++){
	// matrix[i] = new Matrix[m];
	// m = o[i].length>m?o[i].length:m;
	// }
	// matrix[i] = o[i];
	// m =
	// return null;
	// }// n x m

	// Zeitgeschichten

	// public final static long difference(final PiT dt1, final PiT dt2) {
	// // Die Gesamtsumme in ms
	// long diff = 0;
	//
	// diff = dt1.shift.ms - dt2.shift.ms;
	//
	// System.out.println(diff);
	// // boolean sh = (diff==0 ? false:true);
	//
	// // Sekunden
	// // if( !(dt1.second < 0 || dt2.second < 0 ))
	// diff += 1000L * (dt1.second - dt2.second);
	// System.out.println(diff);
	// // if(sh) System.out.println(diff);
	// // Minuten
	// // if(!(dt1.minute < 0 || dt2.minute < 0 ))
	// diff += 60L * 1000L * (dt1.minute - dt2.minute);
	// System.out.println(diff);
	// // Stunden
	// // if(!(dt1.hour < 0 || dt2.hour < 0 ))
	// diff += 60L * 60L * 1000L * (dt1.hour - dt2.hour);
	// System.out.println(diff);
	// // int hourl = (dt1.hour - dt2.hour);
	// // if(!(dt1.day < 0 || dt2.day < 0 ))
	// diff += 24L * 60L * 60L * 1000L * (dt1.day - dt2.day);
	// System.out.println(diff);
	// // int dayl = (dt1.day - dt2.day);
	//
	// // Monat
	// if (dt1.month < 0)
	// return diff;
	// Monate m1 = Monate.values()[dt1.month];
	// Monate m2 = Monate.values()[dt2.month];
	//
	// if (dt1.year < 0)
	// return diff;
	// // Die Anzahl der Tage zwischen zwei Jahren!
	// // Year
	// int y1 = dt1.year;
	// int y2 = dt2.year;
	// // Tage in diesem Jahr
	// int d1 = m1.getDaysFromToYear(y2, y1);
	//
	// System.out.println("Y2 = "+y2+LFCR+"Y1 = "+y1+LFCR+"D1 = "+d1);
	// // if(LOGGER) logger.finest("DIFF: "+y2+" - "+y1+" = "+d1);
	// if (d1 == Integer.MIN_VALUE)
	// return d1;
	// diff += 60L * 60L * 24L * 1000L * d1;
	// System.out.println(diff);
	// // long xl = d1;
	//
	// // Die Anzahl der Tage in einem Jahr
	// int md1 = m1.getDaysToMonth(y1);
	// int md2 = m2.getDaysToMonth(y2);
	// int d2 = (md1 - md2);
	// System.out.println("M1 = "+md1+LFCR+"M2 = "+md2+LFCR+"D2 = "+d2);
	//
	// diff += 60L * 60L * 24L * 1000L * (md1 - md2);
	// System.out.println(diff);
	// // long ml = (md1 - md2);
	// // if(LOGGER)
	// logger.finest(xl+"l "+ml+"s "+dayl+"."+hourl+":"+min+"'"+sec+"\" ==>
	// // "+diff+"s");
	// // }
	// // */
	// // assert diff!=0: dt1.getDatetime(DATE|TIME|DOW|SHIFT);
	// return diff;
	// }

	/**
	 * Läd einen Screen
	 * 
	 * @param which
	 *            -
	 * @param html
	 *            -
	 * @return den Screen which
	 */
	@SuppressWarnings("resource")
	public final static StringBuffer loadAScreen(final String which,
			final boolean html) {
		assert false;
		FileInputStream fis = null;
		StringBuffer filename = new StringBuffer(which);
		try {
			fis = new FileInputStream(filename.toString());
		} catch (FileNotFoundException e1) {
			assert false : "if(LOGGER) logger.warn(Die Seite existiert nicht.);";
		}
		String str = new String();
		// Lege neuen StringBuffer an, den den Stream aus File aufnimmnt
		StringBuffer screen = new StringBuffer();
		try {
			InputStream in = new BufferedInputStream(fis);
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader d = new BufferedReader(isr);
			while ((str = d.readLine()) != null) {
				screen.append(str);
			}
			if (!html)
				ConstMethod.html2txt(screen); // HTML-Text in TEXT
			// wandeln
		} catch (FileNotFoundException e) {
			// Geht daneben
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			// Geht daneben
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					// leer
				}
		}
		return screen;
	}

	/**
	 * TODO Comment
	 * 
	 * @param para
	 *            -
	 * @param dim
	 *            -
	 * @param delim
	 *            -
	 * @return -
	 * @modified -
	 */
	public final static String[] para2Array(String para, int dim, String delim) {
		String[] local = para.substring(1).split(delim, dim);
		String[] l2 = new String[dim];
		for (int i = 0; i < dim; i++) {
			if (i < local.length)
				l2[i] = local[i].trim();
			else
				l2[i] = null;
		}
		return l2;
	}

	/**
	 * Normalverteilung
	 * 
	 * @param max
	 *            Maximalwert
	 * @param min
	 *            Minimalwert
	 * @param sigma
	 *            Sigma 1,2,3
	 * @return Zufallswert
	 */
	public final static double NV(double max, double min, Sigma sigma) {
		double range = (max - min) / 2;
		double mu = min + range;
		double w = 0;
		switch (sigma) {
		case S6827:
			w = W_68;
			break;
		case S9545:
			w = W_95;
			break;
		case S9973:
			w = W_99;
			break;
		default:
			assert false;
		}
		double e;
		do {
			e = Math.round(rand.nextGaussian() * range / w + mu);
		} while (e < min ? true : e > max ? true : false);

		return e;

	}

	/**
	 * Lineare Wahrscheinlichkeit
	 * 
	 * @param x
	 *            -
	 * @param start
	 *            -
	 * @param end
	 *            -
	 * @return -
	 * @modified -
	 */
	public final static double GleichP(int x, int start, int end) {
		double range = end - start;
		double p;
		if (x < start)
			p = 0.0;
		else if (x > end)
			p = 0.0;
		else
			p = 1 / range;
		return p;
	}

	/**
	 * TODO Comment
	 * 
	 * @param x
	 *            -
	 * @param start
	 *            -
	 * @param end
	 *            -
	 * @return -
	 * @modified -
	 */
	public final static double GleichF(int x, int start, int end) {
		double f = 0.0;
		for (int i = start; i <= x && i <= end; i++)
			f += GleichP(i, start, end);
		return f;
	}

	/**
	 * TODO Comment
	 * 
	 * @param x
	 *            -
	 * @param start
	 *            -
	 * @param max
	 *            -
	 * @param end
	 *            -
	 * @return -
	 * @modified -
	 */
	public final static double DreiecksP(int x, int start, int max, int end) {
		double p;
		if (x < start)
			p = 0.0;
		else if (x > end)
			p = 0.0;
		else if (x <= max)
			p = 2.0 * (x - start) / ((end - start) * (max - start));
		else
			p = 2.0 * (end - x) / ((end - start) * (end - max));

		// Wenn x dicht bei p, dann

		return p;
	}

	/**
	 * TODO Comment
	 * 
	 * @param x
	 *            -
	 * @param start
	 *            -
	 * @param max
	 *            -
	 * @param end
	 *            -
	 * @return -
	 * @modified -
	 */
	public final static double DreiecksF(int x, int start, int max, int end) {
		double f = 0.0;
		for (int i = start; i <= x && i <= end; i++)
			f += DreiecksP(i, start, max, end);
		return f;
	}

	// Gleichverteilung
	// f(x)=\begin{cases} 0 & x < a\\ \frac 1{b-a} & a \le x \le b\\ 0 & x > b
	// \end{cases}
	// F(x)= \begin{cases} 0 & x \le a\\ \frac{x-a}{b-a} & a < x < b\\ 1 & x\ge
	// b
	// \end{cases}

	/**
	 * TODO Comment
	 * 
	 * @param generationen
	 *            -
	 * @return -
	 * @modified -
	 */
	public static int GenerationenJahre(int generationen) {
		return Math.max(1, Math.abs(generationen)) * 25;
	}

	/**
	 * TODO Comment
	 * 
	 * @param x
	 *            -
	 * @return -
	 * @modified -
	 */
	public static double rest(double x) {
		return x - Math.floor(x);
	}

	// FIXME Tests erforderlich 20.02.2014

	/**
	 * Stelle Liste der Screen zusammen
	 * 
	 * @param screens
	 *            Screengruppe
	 * @return Liste der Screen
	 */
	public static Screen[] getScreens(Screen[]... screens) {
		 List<Screen> scr = new ArrayList<Screen>();
		 for (Screen[] screen : screens)
		 scr.addAll(Arrays.asList(screen));
		 return scr.toArray(new Screen[0]);
		 }

	//
	// public static final Gender setRandomGender(DataPerson dp) {
	// if (rand.nextBoolean()) {
	// return dp.setGender(Gender.w);
	// }
	// return dp.setGender(Gender.m);
	// }
	//
	// public static final void setRandomBirthday(DataGame dg, DataPerson dp,
	// int min,
	// int max,
	// String sigma) {// , int length, LuaObject table ) {
	// setRandomBirthday(dg, dp, (int) Math.round(ConstMethod.NV(max, min,
	// ConstValue.Sigma
	// .valueOf(sigma))));
	// // String dann = dp.getDayOfBirth().getDatetime(DATE)+" 0:0:0 NORMAL";
	// // ITermin ep = new YearlyEP("Geburtstag von "+dp.getName(), dann,
	// dg.getTc(),
	// // length, table);
	// // dg.getTc().addTermin(ep);
	// }
	//
	// public static final void setRandomBirthday(DataGame dg, DataPerson dp,
	// int alter)
	// {
	//
	// int year = dg.getTc().getStartdate().year - alter;
	// int month = rand.nextInt(12) + 1;
	// int day = rand.nextInt(Monate.values()[month].getDays(year)) + 1;
	// PiT pit = new PiT(year, month, day, 0, 0, 0, Shift.NORMAL, null);
	// if (month > dg.getTc().getStartdate().month)
	// pit.year--;
	// else if (month == dg.getTc().getStartdate().month && day >
	// dg.getTc().getStartdate().day)
	// pit.year--;
	// pit.dayOfWeek = Tage.NODAY;
	// dp.setDayOfBirth(pit);
	// DataClan dc = dg.getDsc().getDataClan(dp.getSuperUID());
	// DataNation dn = dg.getDsc().getDataNation(dc.getSuperUID());
	// // dn.addPerson(dp, dn.getFist(), dn.getFsoll());
	// }
	//
	// public static final Familienstand setRandomFamilienstand(DataGame dg,
	// DataPerson
	// dp) {
	// // Wie wahrscheinlich ist es, das die Person verheiratet ist?
	// int alter = alter(dp.getDayOfBirth(), dg.getTc().getStartdate());
	// if (alter < 16) {
	// return dp.setFamilienstand(Familienstand.NDEF);
	// } else if (!pMarried(alter)) {
	// return dp.setFamilienstand(Familienstand.ledig);
	// }
	// double zufall = rand.nextDouble();
	// double P = 1.0 - ConstMethod.DreiecksF(alter, 15, 119, 120);
	// if (zufall <= P) {
	// return dp.setFamilienstand(Familienstand.verheiratet);
	// }
	// return dp.setFamilienstand(Familienstand.verwitwet);
	//
	// }
	//
	// private static final boolean pMarried(int alter) {
	// double d1 = 0.92 * ConstMethod.DreiecksF(alter, 15, 20, 40);
	// double d2 = 0.12 * ConstMethod.DreiecksF(alter, 30, 31, 70);
	// double d = d1 > d2 ? d1 : d2;
	// double zufall = rand.nextDouble();
	// boolean erg = zufall <= d;
	// return erg;
	// }
	//
	// public static final long setRandomPartner(DataGame dg, DataPerson dp) {
	// long partneruid = -1L;
	// // if (dp.getFamilienstand() != Familienstand.verheiratet)
	// // return partneruid;
	// //
	// // DataClan dc = dg.getDsc().getDataClan(dp.getSuperUID());
	// // DataPerson dpartner;
	// //
	// // // Erzeuge eine Person
	// // partneruid = 0L; //this.createDataPerson(dc);
	// // dpartner = this.getDataPerson(partneruid);
	// //
	// // // Verheiraten!
	// // dp.setPartnerUID(partneruid);
	// // dpartner.setPartnerUID(dp.getUid());
	// // dpartner.setFamilienstand(Familienstand.verheiratet);
	// //
	// // dpartner.setGender(dp.getGender() == Gender.w ? Gender.m : Gender.w);
	// //
	// // // Object table = this.script.getTable("nation" + dn.getTrsn());
	// // int mannJuenger = 0;// = (int) this.script.getNumber(table,
	// // // "MannAlter");
	// // int frauJuenger = 0;// = (int) this.script.getNumber(table,
	// // // "FrauAlter");
	// // int abstand = 1;// = (int) this.script.getNumber(table,
	// // // "Altersabstand");
	// // //
	// // int nullj = dp.getGender() == Gender.w ? mannJuenger : frauJuenger;
	// //
	// // // Setze Geburtstag ein
	// // int alter = dp.getDayOfBirth().year + nullj
	// // + this.rand.nextInt(abstand);
	// // int year = alter;
	// // int month = this.rand.nextInt(12) + 1;
	// // int day = this.rand.nextInt(Monate.values()[month].getDays(year)) +
	// // 1;
	// // dpartner.setDayOfBirth(new PiT(year, month, day, 0, 0, 0, 0, null));
	// // // } catch (LuaException e) {
	// // // ErrApp.LUAEXCEPTION.erraufruf(logger, "");
	// // // }
	// return partneruid;
	//
	// }

	/**
	 * TODO Comment
	 * 
	 * @param button
	 *            -
	 * @modified -
	 */
	public static void setMenuFont(Button button) {
		// button.setFont(new Font(Display.getCurrent(), FONT, FONTSIZE,
		// SWT.BOLD));
		// button.setBackground(Display.getCurrent().getSystemColor(
		// SWT.COLOR_DARK_GRAY));
		// button.setForeground(Display.getCurrent().getSystemColor(
		// SWT.COLOR_WHITE));
	}

	/**
	 * TODO Comment
	 * 
	 * @param button
	 *            -
	 * @modified -
	 */
	public static void setTestMenuFont(Button button) {
		// button.setFont(new Font(Display.getCurrent(), FONT, FONTSIZE,
		// SWT.NORMAL));
		// button.setBackground(Display.getCurrent().getSystemColor(
		// SWT.COLOR_DARK_MAGENTA));
		// button.setForeground(Display.getCurrent().getSystemColor(
		// SWT.COLOR_WHITE));

	}

	/**
	 * TODO Comment
	 * 
	 * @param filename
	 *            -
	 * @modified -
	 */
	public void doParameterTemplate(String filename) {
		@SuppressWarnings("resource")
		Writer bw = null;

		try {
			bw = new BufferedWriter(new FileWriter(filename));
			BeanInfo beanInfo;
			try {
				beanInfo = Introspector.getBeanInfo(this.getClass());

				for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
					bw.write(pd.getDisplayName() + "=\n");

					System.out.println(pd.getDisplayName() + "=");
				}
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// public void loadParameterTemplate(String filename) {
	// LoadScript script = new LoadScript(filename, null);
	// BeanInfo beanInfo;
	// try {
	// beanInfo = Introspector.getBeanInfo(this.getClass());
	//
	// for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
	// if (pd.getDisplayName().equals("class"))
	// continue;
	// String[] str1 = new String[] { script.getString(pd.getDisplayName()) };
	// Method meth = pd.getWriteMethod();
	// try {
	// meth.invoke(this, (Object[]) str1);
	// } catch (IllegalArgumentException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// } catch (IntrospectionException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	// @SuppressWarnings("unchecked")
	// public final static Object[] loadParameterTemplate(String filename, Class
	// clazz) {
	// @SuppressWarnings("unused")
	// LoadScript script = new LoadScript(filename, null);
	// BeanInfo beanInfo;
	// try {
	// beanInfo = Introspector.getBeanInfo(clazz);
	// System.out.println(beanInfo);
	// System.out.println(beanInfo.getPropertyDescriptors().length);
	//
	// for(Field fd :clazz.getDeclaredFields())
	// System.out.println(fd.getName());
	//
	// for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
	//
	// System.out.println(pd.getDisplayName());
	// // if (pd.getDisplayName().equals("class"))
	// // continue;
	// // String[] str1 = new String[] { script.getString(pd.getDisplayName())
	// };
	// // Method meth = pd.getWriteMethod();
	// // try {
	// // meth.invoke(this, (Object[]) str1);
	// // } catch (IllegalArgumentException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // } catch (IllegalAccessException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // } catch (InvocationTargetException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// }
	// } catch (IntrospectionException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.exit(0);
	// return new Object[]{null};
	// }
	/**
	 * TODO Comment
	 * 
	 * @param name
	 *            -
	 * @return -
	 * @throws ParseException
	 *             -
	 * @modified -
	 */
	public static Object print(final String name) throws ParseException {
		System.err.println(name);
		return name;
	}

	/**
	 * TODO Comment
	 * 
	 * @param nameArr
	 *            -
	 * @return -
	 * @throws ParseException
	 *             -
	 * @modified -
	 */
	public static Object[] print(final Object[] nameArr) throws ParseException {
		System.err.println(Arrays.asList(nameArr));
		return nameArr;
	}

	/**
	 * TODO Comment
	 * 
	 * @param name
	 *            -
	 * @return -
	 * @throws ParseException
	 *             -
	 * @modified -
	 */
	public static Object[] LoadListe(final String name) throws ParseException {
		// DEVELOP_PATH = System.getProperty("user.dir") + FS;
		// INSTALLATION_PATH = DEVELOP_PATH;
		// SCRIPT_PATH = INSTALLATION_PATH + "scripts" + FS;
		// FIXME Hard verdrahteter Pfad"
		logger.warn("Hard verdrahteter Pfad");
		String path = System.getProperty("user.dir") + FS + "scripts" + FS;
		if (LOGGER)
			logger.info("... Filename: ");
		// String filename = SCRIPT_PATH + "data" + FS +
		// ((String)name[0]).toLowerCase()
		// + SCRIPT_EXTENSION;
		String filename = path + "data" + FS + name.toLowerCase()
				+ SCRIPT_EXTENSION;
		// String filename = SCRIPT_PATH + "data" + FS + name.toLowerCase()
		// + SCRIPT_EXTENSION;
		if (LOGGER)
			logger.info("... Filename: " + filename);
		PatternMaps script = null;
//		try {
			script = null; //new LoadScript(filename, null).valueParser(0);
//		} catch (ScriptException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			// } catch (DateiNotFoundException e) {
			// logger.fatal("Script [" + filename + "] nicht gefunden: "
			// + e.getMessage());
			// System.exit(-2);
			// } catch (FolderNotFoundException e) {
			// logger.fatal("Ordner [" + filename + "] nicht gefunden: "
			// + e.getMessage());
			// System.exit(-3);
			// }
//		} catch (LoadScriptException e) {
//			System.err.println("Abbruch: " + e.getMessage());
//			System.exit(-2);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (script == null)
			// new
			// DateiNotFoundException("Script ["+filename+"] nicht gefunden!");
			return null;

		if (LOGGER)
			logger.info("File: " + script);
		if (LOGGER)
			logger.info("File: "); // + script.getStringArray("liste"));
		if (LOGGER)
			logger.info("File: ");
//					+ Arrays.asList(script.getStringArray("liste")));
		return null; //script.getStringArray("liste");

	}

	/**
	 * TODO Comment
	 * 
	 * @param arr
	 *            -
	 * @return -
	 * @modified -
	 */
	public static Object Random(final Object[] arr) {
		if (LOGGER)
			logger.debug(Arrays.asList(arr));
		return arr[Math.abs(rand.nextInt()) % arr.length];
	}

	/**
	 * TODO Comment
	 * 
	 * @param arr
	 *            -
	 * @return -
	 * @modified -
	 */
	public static Object TestRandom(final Object[] arr) {
		if (LOGGER)
			logger.debug(Arrays.asList(arr));
		return arr[arr.length - 1];
	}

	public static final Double[] darr(final Number... numbers) {
		Double[] arr = new Double[numbers.length];
		for (int m = 0; m < numbers.length; m++) {
			arr[m] = new Double(((Number) numbers[m]).doubleValue());
		}
		return arr;
	}

	/**
	 * Matrix(double[][] A) Construct a matrix from a 2-D array.
	 * Matrix(double[][] A, int m, int n) Construct a matrix quickly without
	 * checking arguments. Matrix(double[] vals, int m) Construct a matrix from
	 * a one-dimensional packed array Matrix(int m, int n) Construct an m-by-n
	 * matrix of zeros. Matrix(int m, int n, double s) Construct an m-by-n
	 * constant matrix.
	 * 
	 * @param arr
	 * @return
	 */
	// public static Matrix MATRIX(final Double[] ...numbers){ // final
	// double[][] arr) {
	// assert false:"Stop";
	// return null;
	// }
	public static Matrix MATRIX(final Double[]... numbers) { // final double[][]
																// arr) {
		double[][] arr = new double[numbers.length][];
		for (int n = 0; n < numbers.length; n++) {
			arr[n] = new double[numbers[n].length];
			for (int m = 0; m < numbers[n].length; m++) {
				System.err.println("n x m " + n + " x " + m);
				arr[n][m] = ((Number) numbers[n][m]).doubleValue();
			}
		}
		return new Matrix(arr);
	}

	// public static Matrix MATRIX(final Double ...numbers){ // final double[][]
	// arr) {
	// assert false:"Stop";
	// return null;
	// }
	public static Matrix MATRIX(final Double... o) { // final double[][] arr) {
		assert false : "Stop";

		System.err.println(o);
		System.err.println(o.getClass());
		System.err.println(o.length);
		int m = o.length;
		double[][] arr = new double[m][];
		// for(int i = 0; i < m; i++){
		// Number[] number = (Number[]) o[i];
		// int n = number.length;
		// arr[i] = new double[n];
		// for(int j = 0; j < n; j++){
		// arr[i][j] = number[j].doubleValue();
		// }
		// }
		// // for(int n = 0; n < o.length; n++){
		// // Double[] o2 = (Double [])o[n];
		// // arr[n] = new double[numbers[n].length];
		// // for(int m = 0; m < numbers[n].length; m++){
		// // System.err.println("n x m "+n+" x "+m);
		// // arr[n][m] = ((Number)numbers[n][m]).doubleValue();
		// // }
		// // }
		return new Matrix(arr);

		//
		// double [][] arr = new double[1][];
		// arr[0] = new double[numbers.length];
		// for(int m = 0; m < numbers.length; m++){
		// System.err.println("n x m "+0+" x "+m);
		//
		// arr[0][m] = ((Number)numbers[m]).doubleValue();
		// }
		// return new Matrix(arr);
	}

	/**
	 * TODO Comment
	 * 
	 * @param arr
	 * @return
	 * @modified -
	 */
	public static Matrix MATRIXT(final Double[]... numbers) { // final
																// double[][]
																// arr) {
		double[][] arr = new double[numbers.length][];
		for (int n = 0; n < numbers.length; n++) {
			arr[n] = new double[numbers[n].length];
			for (int m = 0; m < numbers[n].length; m++) {
				arr[n][m] = ((Number) numbers[n][m]).doubleValue();
			}
		}
		return new Matrix(arr).transpose();
	}

	public static Matrix MATRIXT(final Double... numbers) { // final double[][]
															// arr) {
		double[][] arr = new double[1][];
		arr[0] = new double[numbers.length];
		for (int m = 0; m < numbers.length; m++) {
			arr[0][m] = ((Number) numbers[m]).doubleValue();
		}
		return new Matrix(arr).transpose();
	}

	// ---- Selbstverwaltung --------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	@SuppressWarnings("unused")
	private final static Logger logger = Logger.getLogger(ConstMethod.class
			.getPackage().getName() + ".ConstMethod");

}
