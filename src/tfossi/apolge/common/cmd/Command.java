/** 
 * Command.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd;



import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.LOGTAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.Arrays;

import org.apache.log4j.Logger;

import tfossi.apolge.common.macrorecorder.IRecorder;

/**
 * generische Auswertung der Parameter
 * 
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Command {

	/**
	 * Ruft {@link ICmd#call(String, String...)} auf.
	 * 
	 * @param cmd
	 *            das auszuwertende Command
	 * @param record
	 *            true, wenn ein Record aufgezeichnet werden darf
	 */
	public static final void parameterCheck(ICmd cmd, boolean record) {//Command(ICmd cmd, boolean record) {
		/* @modified tfossi, 14.08.2014, Interface IRecorder */
		// Recorder läuft
		if (IRecorder.recorder.isRecord() && record) {
			IRecorder.recorder.setRecord(cmd);
		}

		// Parameterqueue ist leer, default ausführen
		if (cmd.getParameter().isEmpty()) {
			cmd.call("sw", (String[]) null);
		} else {
			// Parameterqueue ist nicht leer
			while (!cmd.getParameter().isEmpty()) {
				if (!(cmd.getParameter().element().startsWith("-")))
					assert false:cmd.getData()+LOGTAB+cmd.getParameter();
				
				String[] parameter = (cmd.getParameter().poll().split(" ", 2));
				parameter[0] = parameter[0].substring(1);
				if(LOGGER) logger.debug("Teste Order \'" + parameter[0] + "\'");

				if ((parameter[0] = cmd.testParameter(parameter[0])) == null)
					continue;
				int max = cmd.maxParameter(parameter[0]);
				int min = cmd.minParameter(parameter[0]);
				String[] value = null;
				if (parameter.length > 1)
					value = parameter[1].split(" ", max - min);

				if(LOGGER) logger.debug("Aufruf Order \'" + parameter[0] + "\'"+LOGTAB+"Value: "+Arrays.toString(value));
				cmd.call(parameter[0], value);
			}
		}
	}

	// ---- Selbstverwaltung -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(Command.class.getPackage().getName());

}
