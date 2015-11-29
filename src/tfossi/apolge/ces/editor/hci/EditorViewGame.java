/** 
 * EditorViewGame.java
 * Branch ces
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.ces.editor.hci;




/**
 * Datenaus- und -eingabe zur Bedienung des Editormenus. Ist Viewer im MVC
 * 
 * @.pattern MVC: concrete viewer
 * @.pattern Observer (EditorMenu)
 * @see EditorMenu
 * @see EditorModel
 * TODO Comment
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified -
 * @since Java 1.6
 */
public class EditorViewGame /*extends EditorView*/ {
//	public static int TABLE = 1, TREE=2, BROWSER=4, FOLDER=8;
//	
//	// class Table1 {
//	// String columnText;
//	// int columnWidth;
//	// }
//
//	// Table1 [] table1 = new Table1[this.columnCount];
//
//	@Override
//	public Map<String, Widget> buildSWTWidget(@SuppressWarnings("unused") IModel model,
//			Group basegrp, Widget widget) {
//
//		final Map<String, Widget> widgets = new HashMap<String, Widget>();
//
//		FormData data;
//
//		basegrp.setText("Gamebearbeitung");
//
//		if (widget == null || widget.isDisposed()) {
//			logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" + basegrp
//					+ "] anlegen");
//
//			// ---- Ordne auf dem Screen ein
//			Label gamelistlabel = new Label(basegrp, SWT.NONE);
//			gamelistlabel.setText("Liste aller Games");
//			gamelistlabel.setBackground(basegrp.getDisplay().getSystemColor(SWT.COLOR_WHITE));
//			gamelistlabel.setFont(new Font(basegrp.getDisplay(), "Arial", 12, SWT.ITALIC)); 
//			gamelistlabel.setAlignment(SWT.CENTER);
//			data = new FormData();
//			data.height = 20;
//			data.right = new FormAttachment(50, 0);
//			data.left = new FormAttachment(0, 0);
//			data.top = new FormAttachment(0, 0);
//			gamelistlabel.setLayoutData(data);
//			widgets.put("gamelistlabel", gamelistlabel);
//
//			Browser browser = new Browser(basegrp, SWT.NONE);
//			browser.setUrl(ConstValue.devPath + "html" + ConstValue.FS
//					+ this.getClass().getSimpleName() + ".html");
//			data = new FormData();
//			data.right = new FormAttachment(100, 0);
//			data.left = new FormAttachment(50, 0);
//			data.top = new FormAttachment(50, 0);
//			data.bottom = new FormAttachment(100, 0);
//			browser.setLayoutData(data);
//			widgets.put("Help", browser);
//
//			Label gametreelabel = new Label(basegrp, SWT.NONE);
//			gametreelabel.setText("Baum von ");
//			gametreelabel.setBackground(basegrp.getDisplay().getSystemColor(SWT.COLOR_WHITE));
//			gametreelabel.setFont(new Font(basegrp.getDisplay(), "Arial", 12, SWT.ITALIC)); 
//			gametreelabel.setAlignment(SWT.CENTER);
//			data = new FormData();
//			data.height = 20;
//			data.right = new FormAttachment(100, 0);
//			data.left = new FormAttachment(50, 0);
//			data.top = new FormAttachment(0, 0);
//			gametreelabel.setLayoutData(data);
//			widgets.put("gametreelabel", gametreelabel);
//			
//			
//			final Table table = new GameTable().createTable(basegrp);
//			data = new FormData();
//			data.right = new FormAttachment(50, 0);
//			data.left = new FormAttachment(0, 0);
//			data.top = new FormAttachment(gamelistlabel, -10, SWT.BOTTOM);
//			data.bottom = new FormAttachment(50, 0);
//			table.setLayoutData(data);
//			widgets.put("Table", table);
//
//			// TreeViewer treeViewer = new TreeViewer(basegrp);
//			// treeViewer.setContentProvider(new MovingBoxContentProvider());
//			// treeViewer.setLabelProvider(new MovingBoxLabelProvider());
//			// treeViewer.setInput(new MovingBoxView().getInitalInput());
//			// treeViewer.expandAll();
//			// Create the tree viewer to display the file tree
//			
//			final Tree tree = new GameTree().createTree(basegrp);
//			data = new FormData();
//			data.right = new FormAttachment(100, 0);
//			data.left = new FormAttachment(50, 0);
//			data.top = new FormAttachment(gametreelabel, -10, SWT.BOTTOM);
//			data.bottom = new FormAttachment(50, 0);
//			tree.setLayoutData(data);
//			widgets.put("Tree", tree);
//
//
//		} else if (!widget.isDisposed()) {
//			logger.debug("SWT::" + this.toString() + "\tVorhandene Widgetinstanz auf Gruppe ["
//					+ basegrp + "] gelegt");
//			((Control) widget).setParent(basegrp);
//			return widgets;
//		} else {
//			assert false : "GANZ DOOF";
//		}
//		return widgets;
//	}
//
//	@SuppressWarnings("boxing")
//	public StringBuffer output(@SuppressWarnings("unused") EditorMenu menu, long uid) {
//
//		MetaData md = MetaData.getInstance();
//		StringBuffer output;
//		if (uid < 0L) {
//			output = new StringBuffer("Die vorhandenen Games" + LFCR + LFCR);
//			for (DataGame dg : md.getDataGameTemplates()) {
//				// for (long gameUID : md.getDataGame()) {
//				// DataGame dg = md.getDataGame(gameUID);
//				String name = dg.getShortname();
//				if (dg.isReady())
//					output.append(name + "[green]" + TAB);
//				else
//					output.append(name + "[ red ]" + TAB);
//			}
//			output.append(LFCR + LFCR);
//			logger.trace("Return " + output.toString());
//			return output;
//		}
//		String pagePath = ConstValue.devPath + "html" + ConstValue.FS + "GameEditor.html";
//
//		StringBuffer sb = ConstMethod.loadAScreen(pagePath, true);
//		Map<String, String> map = new HashMap<String, String>();
//
//		DataGame dg = md.getDataGame(uid);
//		map.put("uid", Long.toString(dg.getUid()));
//		map.put("name", dg.getName());
//		map.put("shortname", dg.getShortname());
//		// output[0] += "Datensatz gespeichert: " + (dg.isSaved() ? "JA" : "NEIN") +
//		// LFCR;
//		// output[0] += "Datensatz vollständig: " + (dg.tstReady() ? "JA" : "NEIN") +
//		// LFCR;
//		// // output[0] += "(d $)           Beschreibung:" + LFCR + dg.getDescription() +
//		// // LFCR;
//		// output[0] += "Anzahl der Nationen  : " + dg.getCounterRegister() + LFCR;
//		// // + (dg.getTrsn() != null ? Integer.valueOf(dg.getTrsnEntries()) : "UNDEF") +
//		// // LFCR;
//		//
//		// for (int i = 0; i < dg.getRegisterUID().size(); i++) {
//		// long nuid = dg.getTrsnNationUID(i);
//		// DataNation dn = dg.getDsc().getDataNation(nuid);
//		//
//		// String player = dg.getTrsnRoleUID(i) == -1L ? "" + dg.getTrsnRoleUID(i) :
//		// MetaData
//		// .getInstance().getDataRole(dg.getTrsnRoleUID(i)).getShortname();
//		//
//		// String nation = nuid < 0L ? "UNDEF" : (dn == null ? "NULL" : dn.getShortname()
//		// + " ["
//		// + nuid + "]");
//		// String str = "Slot: " + dg.getTrsnSlot(i) + " : " + player + " : " + nation +
//		// LFCR;
//		// output[0] += str;
//		//
//		// }
//		// // output[0] += "Autor       : "+ dg.getAuthor().getName();
//		// // output[0] += "Leader      : "+ dg.getLeader().getName();
//		// if (dg.getTc().getStartdate() != null) {
//		// output[0] += "Start am             : " +
//		// dg.getTc().getStartdate().getDatetime()
//		// + LFCR;
//		// output[0] += "Es ist jetzt         : " +
//		// dg.getTc().getActualdate().getDatetime() + LFCR;
//		// }
//		// output[0] += "\n";
//
//		return ConstMethod.insertParameter(sb, map);
//
//	}
//
//	@Override
//	public Map<String, Widget> buildSWTWidget(Group group) {
//		for (ICmd cmd : this.statecommandlist) {
//			if (cmd instanceof CSPara) {
//				return ((CSPara) cmd).buildParameterWidgets(group);
//			}
//		}
//
//		return null; // group;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see name.tfossi.apolge.hci.IView#activateC_Widget(org.eclipse.swt.widgets.Widget,
//	 * java.lang.Object)
//	 */
//	@Override
//	public void activateC_Widget(@SuppressWarnings("unused") Map<String, Widget> map,
//			@SuppressWarnings("unused") Object content) {
//
//		assert false: content;
//		// final int rowCount=64;
//		final int columnCount = 5;
//		logger.debug(map.keySet());
//		final Table table = (Table) (map.get("Table"));
//		List<DataGame> liste = MetaData.getInstance().getDataGameTemplates();
//		for (int row = 0; row < liste.size() && table != null; row++) {
//			TableItem item = new TableItem(table, SWT.NONE);
//			DataGame dg = liste.get(row);
//			item.setText(4, "x");
//			item.setText(1, dg.getShortname());
//			item.setText(2, "...");
//			// item.setText(dg.getName());
//			item.setText(3, dg.isReady() ? "OK" : "NO");
//			item.setText(0, dg.toString());
//		}
//
//		// table.getColumn(0).setWidth(72);
//		// for (int i=0; i < columnCount; i++) {
//		// table.getColumn (i).pack ();
//		// }
//		// if(table!=null){
//		// Composite shell = table.getParent();
//		// for(Control c : shell.getChildren())
//		// ((Composite)c).layout();
//		// System.out.println(table.getBounds());
//		// System.out.println(table.getClientArea ());
//		//
//		// Rectangle clientArea = shell.getClientArea ();
//		// clientArea.width /= 2;
//		// clientArea.width -= 10;
//		// clientArea.height /= 2;
//		// clientArea.height -= 10;
//		//		
//		// table.setLocation (clientArea.x, clientArea.y);
//		// Point size = new Point(clientArea.width, clientArea.height); //
//		// table.computeSize (SWT.DEFAULT, 200);
//		// size.x = (size.x<clientArea.width?size.x:clientArea.width);
//		// size.y = (size.y<clientArea.height?size.y:clientArea.height);
//		// table.setSize (size);
//		// table.pack();
//		// System.out.println(table.getClientArea ()+"\n"+clientArea+"\n"+size);
//		// shell.pack ();
//		// }
//	}
//
//	// private void createTable() {
//	//
//	// Table table = new Table(top, SWT.NONE);
//	// table.setHeaderVisible(true);
//	// table.setLinesVisible(true);
//	// table.setBounds(new org.eclipse.swt.graphics.Rectangle(47,67,190,70));
//	//
//	// TableColumn tableColumn = new TableColumn(table, SWT.NONE);
//	// tableColumn.setWidth(100);
//	// tableColumn.setText("Check Column");
//	//
//	// TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
//	// tableColumn1.setWidth(100);
//	// tableColumn1.setText("Combo Column");
//	//
//	// TableItem tableItem=new TableItem(table,SWT.NONE);
//	// TableEditor editor = new TableEditor (table);
//	//
//	// Button checkButton = new Button(table, SWT.CHECK);
//	// checkButton.pack();
//	//
//	// editor.minimumWidth = checkButton.getSize ().x;
//	// editor.horizontalAlignment = SWT.CENTER;
//	// editor.setEditor(checkButton, tableItem, 0);
//	// editor = new TableEditor (table);
//	//
//	// Combo combo = new Combo(table, SWT.CHECK);
//	// combo.pack();
//	//
//	// editor.minimumWidth = combo.getSize ().x;
//	// editor.horizontalAlignment = SWT.CENTER;
//	// editor.setEditor(combo, tableItem, 1);
//	//
//	// }
//
//	// ---- Selbstverwaltung -----------------------------------------------------
//	private final static long serialVersionUID = ConstValue.VERSION;
//	private final static Logger logger = Logger.getLogger("name.tfossi.apolge.hci.editor");
//
//	/**
//	 * Datenaus- und -eingabe zur Bedienung des EditorMenus. Ist Viewer im MVP.
//	 * 
//	 * @param menu
//	 *            ist zu beobachtendes Object {@link EditorMenu}. Sobald sich dieses
//	 *            ändert wird die Anzeige aktualisiert.
//	 * @param statecontext
//	 *            ist der IStateContext.
//	 */
//	public EditorViewGame() {
//		super();
//		logger.trace("Constructor");
//	}
//}
//
///**
// * This class provides the content for the tree in FileTree
// */
//
//class FileTreeContentProvider implements ITreeContentProvider {
//	/**
//	 * Gets the children of the specified object
//	 * 
//	 * @param arg0
//	 *            the parent object
//	 * @return Object[]
//	 */
//	public Object[] getChildren(Object arg0) {
//		// Return the files and subdirectories in this directory
//		return ((File) arg0).listFiles();
//	}
//
//	/**
//	 * Gets the parent of the specified object
//	 * 
//	 * @param arg0
//	 *            the object
//	 * @return Object
//	 */
//	public Object getParent(Object arg0) {
//		// Return this file's parent file
//		return ((File) arg0).getParentFile();
//	}
//
//	/**
//	 * Returns whether the passed object has children
//	 * 
//	 * @param arg0
//	 *            the parent object
//	 * @return boolean
//	 */
//	public boolean hasChildren(Object arg0) {
//		// Get the children
//		Object[] obj = getChildren(arg0);
//
//		// Return whether the parent has children
//		return obj == null ? false : obj.length > 0;
//	}
//
//	/**
//	 * Gets the root element(s) of the tree
//	 * 
//	 * @param arg0
//	 *            the input data
//	 * @return Object[]
//	 */
//	public Object[] getElements(@SuppressWarnings("unused") Object arg0) {
//		// These are the root elements of the tree
//		// We don't care what arg0 is, because we just want all
//		// the root nodes in the file system
//		return File.listRoots();
//	}
//
//	/**
//	 * Disposes any created resources
//	 */
//	public void dispose() {
//		// Nothing to dispose
//	}
//
//	/**
//	 * Called when the input changes
//	 * 
//	 * @param arg0
//	 *            the viewer
//	 * @param arg1
//	 *            the old input
//	 * @param arg2
//	 *            the new input
//	 */
//	public void inputChanged(@SuppressWarnings("unused") Viewer arg0,
//			@SuppressWarnings("unused") Object arg1, @SuppressWarnings("unused") Object arg2) {
//		// Nothing to change
//	}
//}
//
///**
// * This class provides the labels for the file tree
// */
//
//class FileTreeLabelProvider implements ILabelProvider {
//	// The listeners
//	private List listeners;
//
//	// Images for tree nodes
//	private Image file;
//
//	private Image dir;
//
//	// Label provider state: preserve case of file names/directories
//	boolean preserveCase;
//
//	/**
//	 * Constructs a FileTreeLabelProvider
//	 */
//	public FileTreeLabelProvider() {
//		// Create the list to hold the listeners
//		this.listeners = new ArrayList();
//
//		// Create the images
//		try {
//			this.file = new Image(null, new FileInputStream("images/file.gif"));
//			this.dir = new Image(null, new FileInputStream("images/directory.gif"));
//		} catch (FileNotFoundException e) {
//			// Swallow it; we'll do without images
//		}
//	}
//
//	/**
//	 * Sets the preserve case attribute
//	 * 
//	 * @param preserveCase
//	 *            the preserve case attribute
//	 */
//	public void setPreserveCase(boolean preserveCase) {
//		this.preserveCase = preserveCase;
//
//		// Since this attribute affects how the labels are computed,
//		// notify all the listeners of the change.
//		LabelProviderChangedEvent event = new LabelProviderChangedEvent(this);
//		for (int i = 0, n = this.listeners.size(); i < n; i++) {
//			ILabelProviderListener ilpl = (ILabelProviderListener) this.listeners.get(i);
//			ilpl.labelProviderChanged(event);
//		}
//	}
//
//	/**
//	 * Gets the image to display for a node in the tree
//	 * 
//	 * @param arg0
//	 *            the node
//	 * @return Image
//	 */
//	public Image getImage(Object arg0) {
//		// If the node represents a directory, return the directory image.
//		// Otherwise, return the file image.
//		return ((File) arg0).isDirectory() ? this.dir : this.file;
//	}
//
//	/**
//	 * Gets the text to display for a node in the tree
//	 * 
//	 * @param arg0
//	 *            the node
//	 * @return String
//	 */
//	public String getText(Object arg0) {
//		// Get the name of the file
//		String text = ((File) arg0).getName();
//
//		// If name is blank, get the path
//		if (text.length() == 0) {
//			text = ((File) arg0).getPath();
//		}
//
//		// Check the case settings before returning the text
//		return this.preserveCase ? text : text.toUpperCase();
//	}
//
//	/**
//	 * Adds a listener to this label provider
//	 * 
//	 * @param arg0
//	 *            the listener
//	 */
//	@SuppressWarnings("unchecked")
//	public void addListener(ILabelProviderListener arg0) {
//		this.listeners.add(arg0);
//	}
//
//	/**
//	 * Called when this LabelProvider is being disposed
//	 */
//	public void dispose() {
//		// Dispose the images
//		if (this.dir != null)
//			this.dir.dispose();
//		if (this.file != null)
//			this.file.dispose();
//	}
//
//	/**
//	 * Returns whether changes to the specified property on the specified element would
//	 * affect the label for the element
//	 * 
//	 * @param arg0
//	 *            the element
//	 * @param arg1
//	 *            the property
//	 * @return boolean
//	 */
//	public boolean isLabelProperty(@SuppressWarnings("unused") Object arg0,
//			@SuppressWarnings("unused") String arg1) {
//		return false;
//	}
//
//	/**
//	 * Removes the listener
//	 * 
//	 * @param arg0
//	 *            the listener to remove
//	 */
//	public void removeListener(ILabelProviderListener arg0) {
//		this.listeners.remove(arg0);
//	}
//}
//
//class GameTable {
//	final int columnCount = 5;
//	enum cols {
//		UID("UID",64, true),
//		NAME("Name", 128, true),
//		DESC("Beschreibung", -1, true),
//		READY("Ready", 128, true),
//		ADRESS("Adress", 0, true);
//		
//		String titel;
//		int width;
//		boolean visible ;
//		cols(String titel , int width, boolean ready){
//			this.titel = titel;
//			this.width  = width;
//			this.visible = ready;
//		}
//	}
//
//	Table createTable(final Group basegrp) {
//		final Table table = new Table(basegrp, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL
//				| SWT.BORDER | SWT.SINGLE);
//		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
//
//		TableColumn column = new TableColumn(table, SWT.NONE);
//		column.setText(cols.UID.titel);
//		column.setWidth(cols.UID.width);
//		column.setResizable(false);
//
//		column = new TableColumn(table, SWT.NONE);
//		column.setText(cols.NAME.titel);
//		column.setWidth(cols.NAME.width);
//		column.setResizable(false);
//
//		column = new TableColumn(table, SWT.NONE);
//		column.setText(cols.DESC.titel);
//		Point size = basegrp.getSize();
//		int linienbreiten = table.getBorderWidth() * 2 + (2 + table.getColumnCount())
//				* table.getGridLineWidth();
//		column.setWidth(size.x / 2 - 64 - 128 - 128 - linienbreiten);
//		column.setResizable(false);
//
//		column = new TableColumn(table, SWT.NONE);
//		column.setText(cols.READY.titel);
//		column.setWidth(cols.READY.width);
//		column.setResizable(false);
//		
//		column = new TableColumn(table, SWT.NONE);
//		column.setText(cols.ADRESS.titel);		
//		column.setWidth(cols.ADRESS.width);
//		column.setResizable(false);
//
//		table.addSelectionListener(new SelectionAdapter() {
//			public void widgetDefaultSelected(SelectionEvent e) {
//				processSelection("Enter gedrückt: ");
//			}
//
//			public void widgetSelected(SelectionEvent e) {
//				processSelection("Tabellenelement ausgewählt: ");
//			}
//
//			private void processSelection(String message) {
//				// Ausgewählte Tabellenzeilen holen
//				TableItem[] selection = table.getSelection();
//				// Wegen SWT.SINGLE ist nur eine Zeile ausgewählt
//				TableItem selectedRow = selection[0];
//				// Die einzelnen Tabellenelemente für Ausgabe aufbereiten
//				
//				String s = new String();
//				for (int i = 0; i < 5; i++) {
//					s += "->"+selectedRow.getText(i)+"<-"+ConstValue.NTAB;
//				}
//				System.out.println(message + s);
//			}
//		});
//
//		// table.addListener(SWT.MouseDown, new Listener() {
//		// public void widgetDefaultSelected(SelectionEvent e) {
//		// processSelection("Enter gedrückt: ");
//		// System.out.println("Enter gedrückt: ");
//		// }
//		//
//		// public void widgetSelected(SelectionEvent e) {
//		// processSelection("Tabellenelement ausgewählt: ");
//		// System.out.println("Tabellenelement ausgewählt: ");
//		// }
//		//
//		// private void processSelection(String message) {
//		// // Ausgewählte Tabellenzeilen holen
//		// TableItem[] selection = table.getSelection();
//		// // Wegen SWT.SINGLE ist nur eine Zeile ausgewählt
//		// TableItem selectedRow = selection[0];
//		// // Die einzelnen Tabellenelemente für Ausgabe aufbereiten
//		// String s = selectedRow.getText(0) + ", " + selectedRow.getText(1) + ", "
//		// + selectedRow.getText(2);
//		// System.out.println(message + s);
//		// }
//		//
//		// @Override
//		// public void handleEvent(Event event) {
//		// // Rectangle clientArea = table.getClientArea();
//		// // Point pt = new Point(event.x, event.y);
//		// int index = table.getTopIndex();
//		// // boolean found = false;
//		// while (index < table.getItemCount()) {
//		// //
//		// // boolean visible = false;
//		// TableItem item = table.getItem(index);
//		// for (int i = 0; i < GameTable.this.columnCount; i++) {
//		// // Rectangle rect = item.getBounds(i);
//		// // if (rect.contains(pt)) {
//		// System.out.println("Item " +item.getText(i));
//		// // found = true;
//		// // }
//		// // if (!visible && rect.intersects(clientArea)) {
//		// // visible = true;
//		// // }
//		// }
//		// // if (!visible)
//		// // break;
//		// // index++;
//		// }
//		// // if (!found) {
//		// //
//		// // TextInputDialog dialog = new TextInputDialog(basegrp.getShell(), SWT.BORDER
//		// // | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
//		// // dialog.setMessage("Bitte Namen des Games eingeben (leer für KEIN)");
//		// // dialog.setText("Neues Game erzeugen");
//		// // dialog.setButtons();
//		// // String nameOfGame = dialog.open();
//		// // if (nameOfGame != null) {
//		// //
//		// // TableItem item = new TableItem(table, SWT.NONE);
//		// // item.setText(0, "-");
//		// // item.setText(1, "{NEU}");
//		// // item.setText(1, "-");
//		// // item.setText(3, "neu angelegt");
//		// //
//		// // }
//		// //
//		// // }
//		// //
//		// }
//		// });
//		return table;
//	}
//}
//
//class TextInputDialog extends Dialog {
//	private String message;
//	String input;
//	private boolean buttons = false;
//
//	/**
//	 * InputDialog constructor
//	 * 
//	 * @param parent
//	 *            the parent
//	 */
//	public TextInputDialog(Shell parent) {
//		// Pass the default styles here
//		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
//	}
//
//	/**
//	 * InputDialog constructor
//	 * 
//	 * @param parent
//	 *            the parent
//	 * @param style
//	 *            the style
//	 */
//	public TextInputDialog(Shell parent, int style) {
//		// Let users override the default styles
//		super(parent, style);
//		setText("Input Dialog");
//		setMessage("Please enter a value:");
//	}
//
//	/**
//	 * Gets the message
//	 * 
//	 * @return String
//	 */
//	public String getMessage() {
//		return this.message;
//	}
//
//	/**
//	 * Sets the message
//	 * 
//	 * @param message
//	 *            the new message
//	 */
//	public void setMessage(String message) {
//		this.message = message;
//	}
//
//	/**
//	 * Gets the input
//	 * 
//	 * @return String
//	 */
//	public String getInput() {
//		return this.input;
//	}
//
//	/**
//	 * Sets the input
//	 * 
//	 * @param input
//	 *            the new input
//	 */
//	public void setInput(String input) {
//		this.input = input;
//	}
//
//	/**
//	 * Sets the message
//	 * 
//	 * @param message
//	 *            the new message
//	 */
//	public void setButtons() {
//		this.buttons = true;
//	}
//
//	public void clearButtons() {
//		this.buttons = false;
//	}
//
//	/**
//	 * Opens the dialog and returns the input
//	 * 
//	 * @return String
//	 */
//	public String open() {
//		// Create the dialog window
//		Shell shell = new Shell(getParent(), getStyle());
//		shell.setText(getText());
//		createContents(shell);
//		shell.pack();
//		shell.open();
//		Display display = getParent().getDisplay();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//		// Return the entered value, or null
//		return this.input;
//	}
//
//	/**
//	 * Creates the dialog's contents
//	 * 
//	 * @param shell
//	 *            the dialog window
//	 */
//	private void createContents(final Shell shell) {
//		shell.setLayout(new GridLayout(2, true));
//
//		GridData data = new GridData();
//		// Show the message
//		if (this.message != null) {
//			Label label = new Label(shell, SWT.NONE);
//			label.setText(this.message);
//			data.horizontalSpan = 2;
//			label.setLayoutData(data);
//		}
//		// Display the input box
//		final Text text = new Text(shell, SWT.BORDER);
//		data = new GridData(GridData.FILL_HORIZONTAL);
//		data.horizontalSpan = 2;
//		text.setLayoutData(data);
//		if (!this.buttons) {
//			text.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent event) {
//					TextInputDialog.this.input = text.getText();
//					shell.close();
//				}
//			});
//		}
//		if (this.buttons) {
//			// Create the OK button and add a handler
//			// so that pressing it will set input
//			// to the entered value
//			Button ok = new Button(shell, SWT.PUSH);
//			ok.setText("OK");
//			data = new GridData(GridData.FILL_HORIZONTAL);
//			ok.setLayoutData(data);
//			ok.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent event) {
//					TextInputDialog.this.input = text.getText();
//					shell.close();
//				}
//			});
//
//			// Create the cancel button and add a handler
//			// so that pressing it will set input to null
//			Button cancel = new Button(shell, SWT.PUSH);
//			cancel.setText("Cancel");
//			data = new GridData(GridData.FILL_HORIZONTAL);
//			cancel.setLayoutData(data);
//			cancel.addSelectionListener(new SelectionAdapter() {
//				@Override
//				public void widgetSelected(SelectionEvent event) {
//					TextInputDialog.this.input = null;
//					shell.close();
//				}
//			});
//
//			// Set the OK button as the default, so
//			// user can type input and press Enter
//			// to dismiss
//			shell.setDefaultButton(ok);
//		}
//	}
//
//	public static void main(String[] args) {
//		Shell shell = new Shell();
//		TextInputDialog dialog = new TextInputDialog(shell, SWT.NONE);
//		dialog.setMessage(null);
//		System.out.println(dialog.open());
//	}
//}
//
//class GameTree {
////	final int columnCount = 5;
////	enum cols {
////		UID("UID",64, true),
////		NAME("Name", 128, true),
////		DESC("Beschreibung", -1, true),
////		READY("Ready", 128, true),
////		ADRESS("Adress", 0, true);
////		
////		String titel;
////		int width;
////		boolean visible ;
////		cols(String titel , int width, boolean ready){
////			this.titel = titel;
////			this.width  = width;
////			this.visible = ready;
////		}
////	}
//	void setItems(){
////		for (int i = 0; i < 4; i++) {
////			TreeItem iItem = new TreeItem(tv, 0);
////			iItem.setText("TreeItem (0) -" + i);
////			for (int j = 0; j < 4; j++) {
////				TreeItem jItem = new TreeItem(iItem, 0);
////				jItem.setText("TreeItem (1) -" + j);
////				for (int k = 0; k < 4; k++) {
////					TreeItem kItem = new TreeItem(jItem, 0);
////					kItem.setText("TreeItem (2) -" + k);
////					for (int l = 0; l < 4; l++) {
////						TreeItem lItem = new TreeItem(kItem, 0);
////						lItem.setText("TreeItem (3) -" + l);
////					}
////				}
////			}
////		}
//		// tv.setl.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
//		// tv.set.setContentProvider(new FileTreeContentProvider());
//		// tv.setLabelProvider(new FileTreeLabelProvider());
//		// tv.setInput("root"); // pass a non-null that will be ignored
//
//		
//		
//	}
//	
//	
//	Tree createTree(final Group basegrp) {
//		final Tree tree = new Tree(basegrp, SWT.BORDER);
////		
//
//		tree.addSelectionListener(new SelectionAdapter() {
//			public void widgetDefaultSelected(SelectionEvent e) {
//				processSelection("Enter gedrückt: ");
//			}
//
//			public void widgetSelected(SelectionEvent e) {
//				processSelection("Tabellenelement ausgewählt: ");
//			}
//
//			private void processSelection(String message) {
//				// Ausgewählte Tabellenzeilen holen
//				TreeItem[] selection = tree.getSelection();
//				// Wegen SWT.SINGLE ist nur eine Zeile ausgewählt
//				TreeItem selectedRow = selection[0];
//				// Die einzelnen Tabellenelemente für Ausgabe aufbereiten
//				
//				String s = new String();
//				for (int i = 0; i < 5; i++) {
//					s += "->"+selectedRow.getText(i)+"<-"+ConstValue.NTAB;
//				}
//				System.out.println(message + s);
//			}
//		});
//
//		// table.addListener(SWT.MouseDown, new Listener() {
//		// public void widgetDefaultSelected(SelectionEvent e) {
//		// processSelection("Enter gedrückt: ");
//		// System.out.println("Enter gedrückt: ");
//		// }
//		//
//		// public void widgetSelected(SelectionEvent e) {
//		// processSelection("Tabellenelement ausgewählt: ");
//		// System.out.println("Tabellenelement ausgewählt: ");
//		// }
//		//
//		// private void processSelection(String message) {
//		// // Ausgewählte Tabellenzeilen holen
//		// TableItem[] selection = table.getSelection();
//		// // Wegen SWT.SINGLE ist nur eine Zeile ausgewählt
//		// TableItem selectedRow = selection[0];
//		// // Die einzelnen Tabellenelemente für Ausgabe aufbereiten
//		// String s = selectedRow.getText(0) + ", " + selectedRow.getText(1) + ", "
//		// + selectedRow.getText(2);
//		// System.out.println(message + s);
//		// }
//		//
//		// @Override
//		// public void handleEvent(Event event) {
//		// // Rectangle clientArea = table.getClientArea();
//		// // Point pt = new Point(event.x, event.y);
//		// int index = table.getTopIndex();
//		// // boolean found = false;
//		// while (index < table.getItemCount()) {
//		// //
//		// // boolean visible = false;
//		// TableItem item = table.getItem(index);
//		// for (int i = 0; i < GameTable.this.columnCount; i++) {
//		// // Rectangle rect = item.getBounds(i);
//		// // if (rect.contains(pt)) {
//		// System.out.println("Item " +item.getText(i));
//		// // found = true;
//		// // }
//		// // if (!visible && rect.intersects(clientArea)) {
//		// // visible = true;
//		// // }
//		// }
//		// // if (!visible)
//		// // break;
//		// // index++;
//		// }
//		// // if (!found) {
//		// //
//		// // TextInputDialog dialog = new TextInputDialog(basegrp.getShell(), SWT.BORDER
//		// // | SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
//		// // dialog.setMessage("Bitte Namen des Games eingeben (leer für KEIN)");
//		// // dialog.setText("Neues Game erzeugen");
//		// // dialog.setButtons();
//		// // String nameOfGame = dialog.open();
//		// // if (nameOfGame != null) {
//		// //
//		// // TableItem item = new TableItem(table, SWT.NONE);
//		// // item.setText(0, "-");
//		// // item.setText(1, "{NEU}");
//		// // item.setText(1, "-");
//		// // item.setText(3, "neu angelegt");
//		// //
//		// // }
//		// //
//		// // }
//		// //
//		// }
//		// });
//		return tree;
//	}
}