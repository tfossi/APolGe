/** 
 * Transmission.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.net;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.net.SocketException;

/**
 * 1. Transmission<br>
 * <br>
 * 1. Abfrage mit erwarteter Antwort<br>
 * 1. Abfrage Xyz<br>
 * 1. Fehlercounter=MAXTRY<br>
 * 2. Anfrage_ID<br>
 * 3. Command<br>
 * <br>
 * 2. Erwarte Antwort auf Xyz<br>
 * 1. Anfrage_ID<br>
 * 2. Daten<br>
 * 3. TimeOut Meldung<br>
 * 1. Fehlercounter--<br>
 * 2. Anfrage_ID<br>
 * 3. Command<br>
 * 4. Error Meldung<br>
 * 1. Fehlercounter--<br>
 * 2. Anfrage_ID <br>
 * 3. Command <br>
 * <br>
 * 2. Antwort auf Abfrage <br>
 * 1. Antwort auf Abc <br>
 * 1. Anfrage_ID <br>
 * 2. Daten <br>
 * 2. Gegenstelle meldet TimeOut / Error <br>
 * 1. Anfrage_ID <br>
 * 2. Daten <br>
 * 3. Error <br>
 * 1. Anfrage_ID <br>
 * 2. Errormeldung <br>
 * <br>
 * 3. Statement ohne erwarteter Antwort<br>
 * 1. Statement
 * 
 * @author tfossi
 * @version 12.08.2014
 * @modified -
 * @since Java 1.6
 */
public class Transmission {

	/** socket */
	private Socket socket = null;

	/** in */
	private ObjectInputStream in;

	/** out */
	private ObjectOutputStream out;

	// private final Set<Integer> sendID = new HashSet<Integer>();
	//
	// private final Set<Integer> receiveID = new HashSet<Integer>();

	/*
	 * Abfrage Xyz 1. Fehlercounter=MAXTRY 2. Anfrage_ID 3. Command
	 */
	// public final Object abfrage(Object question) {
	// assert question != null;
	// int counter = 3;
	// TransO o = new TransO(question);
	// int id = question.hashCode();
	// this.send(o);
	// TransO answer = this.receive(id);
	// return answer.o;
	// }

	/**
	 * TODO Comment
	 * @return -
	 * @throws ClassNotFoundException -
	 * @throws InvalidClassException -
	 * @throws StreamCorruptedException -
	 * @throws OptionalDataException -
	 * @throws EOFException -
	 * @throws IOException -
	 * @modified - 
	 */
	public final TransO auskunft() throws ClassNotFoundException,
			InvalidClassException, StreamCorruptedException,
			OptionalDataException, EOFException, IOException {
		return this.receive();
	}

	/**
	 * <code>
	1. Transmission<br>
    1. Abfrage mit erwarteter Antwort
    1. Abfrage Xyz
          1. Fehlercounter=MAXTRY
          2. Anfrage_ID
          3. Command
    2. Erwarte Antwort auf Xyz
          1. Anfrage_ID
          2. Daten
    3. TimeOut Meldung
          1. Fehlercounter--
          2. Anfrage_ID
          3. Command        
    4. Error Meldung
          1. Fehlercounter--
          2. Anfrage_ID
          3. Command        
2. Antwort auf Abfrage
    1. Antwort auf Abc
          1. Anfrage_ID
          2. Daten
    2. Gegenstelle meldet TimeOut / Error
          1. Anfrage_ID
          2. Daten
    3. Error
          1. Anfrage_ID
          2. Errormeldung
3. Statement ohne erwarteter Antwort
    1. Statement
	 2. Antwort auf Abfrage
     1. Antwort auf Abc
           1. Anfrage_ID
           2. Daten
     2. Gegenstelle meldet TimeOut / Error
           1. Anfrage_ID
           2. Daten
     3. Error
           1. Anfrage_ID
           2. Errormeldung
         </code>
	 *
	 * TODO Comment
	 * @param command -
	 * @throws SocketException -
	 * @throws InvalidClassException -
	 * @throws IOException -
	 * @modified - 
	 */
	public final void statement(String command) throws SocketException,
			InvalidClassException, IOException {
		try {
			TransO to = new TransO(command);
			this.send(to);
		} catch (NotSerializableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * NetStatement (Command) abgeben:
	 * 
	 * @param cmdparameter
	 *            Command mit String-Parametern
	 * @param data
	 *            Binäre Daten
	 * @throws SocketException -
	 * @throws InvalidClassException -
	 * @throws IOException -
	 */
	public final void statement(String cmdparameter, Object data)
			throws SocketException, InvalidClassException, IOException {
		try {
			TransO to = new TransO(cmdparameter, data);
			this.send(to);
		} catch (NotSerializableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sende die Daten aus TransO an den Client
	 * 
	 * @see TransO
	 * @param o
	 *            TransO
	 * @throws SocketException -
	 * @throws InvalidClassException -
	 * @throws NotSerializableException -
	 * @throws IOException -
	 */
	private final void send(TransO o) throws SocketException,
			InvalidClassException, NotSerializableException, IOException {

		this.out.reset();
		this.out.writeInt(o.getID());
		this.out.writeUTF(o.getCmd());
		this.out.writeObject(o.getData());
		this.out.flush();
	}

	/**
	 * Lese Daten vom Client
	 * 
	 * @return TransO
	 * @throws ClassNotFoundException -
	 * @throws InvalidClassException -
	 * @throws StreamCorruptedException -
	 * @throws OptionalDataException -
	 * @throws EOFException -
	 * @throws IOException -
	 */
	private final TransO receive() throws ClassNotFoundException,
			InvalidClassException, StreamCorruptedException,
			OptionalDataException, EOFException, IOException {
		return new TransO(this.in.readInt(),this.in.readUTF(),this.in.readObject());
	}

	/**
	 * @return <code>true</code> wenn Verbindung besteht
	 */
	protected final boolean isConnected() {
		return ((this.in != null) && (this.out != null) && (this.socket != null));
	}

	/**
	 * Beende Verbindung
	 * TODO Comment
	 * @throws IOException -
	 * @modified - 
	 */
	protected final void closeConnections() throws IOException {
		this.socket.close();
		this.socket = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected final void finalize() throws IOException {
		if (this.socket != null) {
			this.socket.close();
			this.socket = null;
		}
	}

	/**
	 * Anlage Transmission ohne Sockets
	 */
	public Transmission() {
		// Nothing
	}

	/**
	 * Socket einstellen
	 * @param s
	 * 			Socket
	 * @throws IOException -
	 */
	protected void setSocket(Socket s) throws IOException {
		if (this.socket != null)
			return;
		this.socket = s;
		OutputStream os = s.getOutputStream();
		InputStream is = s.getInputStream();
		// Reihenfolge ...
		this.out = new ObjectOutputStream(os);
		// ... wichtig!
		this.in = new ObjectInputStream(is);
	}

	/**
	 * Anlage Transmission mit Sockets
	 * @param s
	 * 			Socket
	 * @throws IOException -
	 * 
	 */
	public Transmission(Socket s) throws IOException {
		this.setSocket(s);
	}

}
