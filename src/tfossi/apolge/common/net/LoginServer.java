/** 
 * LoginServer.java
 * Branch net
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.net;

import static tfossi.apolge.common.constants.ConstValueExtension.*;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import org.apache.log4j.Logger;

import tfossi.apolge.ces.AApplication;
import tfossi.apolge.ces.server.hci.ServerMenu;
import tfossi.apolge.common.error.ErrApp;
import tfossi.apolge.common.system.PreLoad;
import tfossi.apolge.io.Screen;

/**
 * Der LoginServer steuert das Einwählen der Clients und die Anlage der UserSessions

 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class LoginServer extends Thread {
	/** menu */
	private final ServerMenu menu;

	/** preload */
	@SuppressWarnings("unused")
	private final PreLoad preload;

	/** props */
	@SuppressWarnings("unused")
	private final Properties props;

	/** server */
	private ServerSocket server = null;

	// Liefert einen Thread-Pool mit wachsender Größe.
	/** executor */
	ExecutorService executor = Executors.newCachedThreadPool();

	
	
	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	@SuppressWarnings("unused")
	private final static long serialVersionUID = VERSION;
	/** logger */
	private final static Logger logger = Logger.getLogger(LoginServer.class
			.getPackage().getName());

	/**
	 * @param servermenu -
	 * @throws IOException -
	 */
	public LoginServer(ServerMenu servermenu) throws IOException {
		super();
		this.menu = servermenu;
		this.preload = ((AApplication) this.menu.getStateContext())
				.getPreLoad();
		this.props = ((AApplication) this.menu.getStateContext())
				.getProperties();
		try {
			this.server = new ServerSocket(PORT_NETWORK);
		} catch (BindException e) {
			ErrApp.NETEXCEPTION.erraufruf(
					"Port ist belegt! Anderen Port versuchen!!");
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@SuppressWarnings("resource")
	@Override
	public final void run() {
		
		if(LOGGER) logger.info("");
		while (!this.server.isClosed()) {

			Socket client = null;
			Runnable newSession;

			try {

				this.menu.setInformation("Warte auf Port " + PORT_NETWORK, Screen.M);

				client = this.server.accept();
				this.menu.setInformation("Client meldet sich...", Screen.M);
				
				newSession = new UserSession(this.menu, client);
				this.executor.execute(newSession);

			} catch (SocketTimeoutException e) {
				System.out.println("Socket timed out!");
				e.printStackTrace();
				break;
			} catch (InterruptedIOException e) {
				System.err.println("Timeout nach einer Minute!");
				e.printStackTrace();
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		this.executor.shutdown();
		this.menu.setInformation("Connector lost", Screen.M);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	public void finalize() {
		if (this.server != null) {
			try {
				this.server.close();
			} catch (IOException e) {
				System.out.println("Couldn’t close socket:" + e);
				e.printStackTrace();
			} finally {
				this.server = null;
			}
		}
		if (this.executor.isTerminated()) {
					this.executor.shutdownNow();
		}
	}
}