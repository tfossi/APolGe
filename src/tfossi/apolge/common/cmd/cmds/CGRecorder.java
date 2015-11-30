/** 
 * CGRecorder.java
 * Branch cmd
 * APolGe
 * tfossi-team
 * licence GPLv3 
 */
package tfossi.apolge.common.cmd.cmds;


import static tfossi.apolge.common.constants.ConstMethod.setTestMenuFont;
import static tfossi.apolge.common.constants.ConstValue.LOGGER;
import static tfossi.apolge.common.constants.ConstValue.TAB;
import static tfossi.apolge.common.constants.ConstValueExtension.VERSION;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import tfossi.apolge.common.cmd.ACmd;
import tfossi.apolge.common.cmd.Command;
import tfossi.apolge.common.cmd.ICmd;
import tfossi.apolge.common.hci.AMenu;
import tfossi.apolge.common.hci.IMenu;
import tfossi.apolge.common.hci.IModel;
import tfossi.apolge.common.hci.menu.IGeneralReceiver;
import tfossi.apolge.common.hci.menu.IReceiver;
import tfossi.apolge.common.macrorecorder.IRecorder;
import tfossi.apolge.io.ContentString;
import tfossi.apolge.io.IContent;
import tfossi.apolge.io.screenfactory.Cntr;

/**
 * Befehl: Recorderfunktionen play, record usw Gehört zur Gruppe der allgemeinen
 * Befehle.<br/>
 * <b>Befehl: </b>recorder<br/>
 * <b>Optionen:</b><br>
 * <p>
 * 
 * @.pattern Command: concrete command
 * @see ACmd
 * @see AMenu
 * @see IRecorder
 *
 * @author tfossi
 * @version 13.08.2014
 * @modified tfossi, 14.08.2014, Insert Interface IRecorder
 * @since Java 1.6
 */
public class CGRecorder extends ACmd {

	// ---- Command Pattern
	// ------------------------------------------------------

	/**
	 * TODO Comment
	 *
	 * @author tfossi
	 * @version 13.08.2014
	 * @modified -
	 * @since Java 1.6
	 */
	public enum c {

		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		chk(false, null, null, 0, 0) {
			@Override
			// @SuppressWarnings("synthetic-access")
			public void call(ICmd cmd, String... value) {
				try {
					if(LOGGER) logger.info(IRecorder.recorder.chkMakro(value[0]));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		del(false, "del", null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				IRecorder.recorder.delRecord(value[0]);
			}
		},

		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		l(false, "l", null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				((IGeneralReceiver) cmd.getReceiver()).contentRecorder();
			}
		},
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		list(true, "list", null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				assert false;
				((IGeneralReceiver) cmd.getReceiver()).contentRecorder();
			}

			@Override
			public ContentString getParameter() {
				return new ContentString(new String[] {});
			}
			// @Override
			// public void call(ICmd cmd, @SuppressWarnings("unused") String...
			// value) {
			// assert cmd != null;
			// assert cmd.getReceiver() != null;
			// assert cmd.getReceiver().toString() != null;
			// IModel model = ((IMenu)cmd.getReceiver()).getModel();
			// String[][] information = cmd.getReceiver().contentRecorder(
			// model.getReceiverFilter(),
			// model.getPatternFilter(),
			// model.isDetailsFilter(),
			// model.isResetFilter()
			// );
			// int length = information[0].length +
			// information[1].length +
			// information[2].length +
			// information[3].length;
			// String[] info = new String[length];
			// int cnt=0;
			// for(int i = 0; i < 4; i++)
			// for(String str : information[i])
			// info[cnt++] = str;
			// ((IMenu)cmd.getReceiver()).setInformation(((IMenu)cmd.getReceiver()).getStateContext().getCntr(),
			// Screen.VI, new ContentString(info), true, false);
			// }
		},
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		d(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				IModel model = ((IMenu) cmd.getReceiver()).getModel();
				model.setDetailsFilter(!model.isDetailsFilter());
			}
		},
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		detail(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				IModel model = ((IMenu) cmd.getReceiver()).getModel();
				model.setDetailsFilter(!model.isDetailsFilter());
			}
		},
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		rcv(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				@SuppressWarnings("hiding")
				IReceiver receiver = ((CGRecorder) cmd).getReceiver();
				IModel model = ((IMenu) receiver).getModel();

				if (model.getReceiverFilter() == null)
					model.setReceiverFilter(receiver.toString());
				else
					model.setReceiverFilter(null);
			}
		},
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		receiver(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				@SuppressWarnings("hiding")
				IReceiver receiver = ((CGRecorder) cmd).getReceiver();
				IModel model = ((IMenu) receiver).getModel();

				if (model.getReceiverFilter() == null)
					model.setReceiverFilter(receiver.toString());
				else
					model.setReceiverFilter(null);
			}
		},
		/**
		 * TODO Comment
		 *
		 * @author tfossi
		 * @version 13.08.2014
		 * @modified -
		 * @since Java 1.6
		 */
		reset(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				IModel model = ((IMenu) cmd.getReceiver()).getModel();
				model.setResetFilter(!model.isResetFilter());
				@SuppressWarnings("hiding")
				IRecorder rec = IRecorder.recorder;
				rec.contentReset();
				model.setPatternFilter(".*");
				
			}
		},
		/** f */
		f(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				IModel model = ((IMenu) cmd.getReceiver()).getModel();
				if(LOGGER) logger.trace("Pattern: "+value[0]);
				model.setPatternFilter(value[0]);
			}
		},
		/** filter */
		filter(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				IModel model = ((IMenu) cmd.getReceiver()).getModel();
				if(LOGGER) logger.trace("Pattern: "+value[0]);
				model.setPatternFilter(value[0]);
			}
		},
		/** play */
		play(true, ">", "Hilfe", 1, 1) {
			@Override
			public void call(ICmd cmd, String... value) {
				((IGeneralReceiver) cmd.getReceiver()).playRecorder(value);
			}
		},
		/** rec */
		rec(true, "o_o", "Record", 2, 2) {
			@Override
			public void call(ICmd cmd, String... value) {
				((IGeneralReceiver) cmd.getReceiver()).recRecorder(value[0],
						value[1]);
			}
		},
		/** stopp */
		stopp(true, "x|", "Record", 2, 2) {
			@Override
			public void call(ICmd cmd, String... value) {
				((IGeneralReceiver) cmd.getReceiver()).stopRecorder();
			}
		},
		// // all(true, "all", "Hilfe", 0,0),
		//
		// ---- Standards
		/** sw */
		sw(false, null, null, 0, 0) {
			@Override
			public void call(ICmd cmd, String... value) {
				cmd.help();
			}
		},
		/** h */
		h(true, "?", "Hilfe", 0, 1) {
			// @Override
			// public ContentString getParameter() {
			// return new ContentString(new String[] {});
			// }
			@Override
			public void call(ICmd cmd, String... value) {
				cmd.help();
			}
		},
		/** help */
		help(false, "Hilfe", "Hilfe", 0, 1) {
			/* (non-Javadoc)
			 * @see tfossi.apolge.common.cmd.cmds.CGRecorder.c#getParameter()
			 */
			@Override
			public ContentString getParameter() {
				return new ContentString(new String[] {});
			}

			@Override
			public void call(ICmd cmd, String... value) {
				cmd.help();
			}
		};

		/** show */
		final boolean show;
		/** buttontext */
		public final String buttontext;
		/** tooltiptext */
		final String tooltiptext;
		/** parameterMin */
		public final int parameterMin;
		/** parameterMax */
		public final int parameterMax;

		/**
		 * @param show
		 *            Anzeige im GUI
		 * @param buttontext
		 *            Test des Buttons
		 * @param tooltiptest
		 *            Hilfetext
		 * @param parameterMin
		 *            Minimalzahl der Parameter
		 * @param parameterMax
		 *            Maximalzahl der Parameter
		 */
		private c(boolean show,
				String buttontext,
				String tooltiptest,
				int parameterMin,
				int parameterMax) {
			this.show = show;
			this.buttontext = buttontext;
			this.tooltiptext = tooltiptest;
			this.parameterMin = parameterMin;
			this.parameterMax = parameterMax;
		}

		/**
		 * TODO Comment
		 * @return -
		 * @modified - 
		 */
		@SuppressWarnings("static-method")
		public IContent getParameter() {
			assert false; //ErrApp.NI_X.erraufruf("[public IContent getParameter()]");
			return new ContentString(new String[] {});
		}

		/**
		 * Aufruf der Parameterfunktion
		 * 
		 * @param cmd -
		 * @param value -
		 */
		@SuppressWarnings("static-method")
		public void call(ICmd cmd, String... value) {
			assert false; //ErrApp.NI_X.erraufruf("[public void call(ICmd cmd, String... value)]");
		}

		/** @return bei <code>true</code> anzeigen, sonst nicht. */
		public boolean getShow() {
			return this.show;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#command()
	 */
	@Override
	public final void command() {
		if (this.grayed)
			return;
		Command.parameterCheck(this, false);
		// // assert this.getReceiver() != null;
		// @SuppressWarnings("unused")
		// String receiver = this.getReceiver().toString();
		// @SuppressWarnings("unused")
		// boolean reset = false;
		// @SuppressWarnings("unused")
		// boolean details = false;
		// String pattern = "-.*";
		// if (this.getParameter().size() == 0) {
		// if(LOGGER) logger.debug("Content HELP");
		// this.help();
		// return;
		// }
		// // Alle Parameter aus 'c' durchgehen
		// for (Object o : this.getParameter()) {
		// String parameter = null;
		//
		// if (o instanceof Text) {
		// Text t = (Text) o;
		// parameter = t.getText();
		// } else
		// parameter = (String) o;
		//
		// if(LOGGER) logger.debug("Teste Parameter \'" + parameter + "\'");
		//
		// if (parameter.equals("-h") || parameter.equals("-?")) {
		// if(LOGGER) logger.debug("Content HELP");
		// this.help();
		// return;
		// }
		// if (parameter.equals("-play") || parameter.equals("-p2")) {
		//
		// FileDialog fd = new FileDialog(Display.getCurrent().getActiveShell(),
		// SWT.OPEN);
		//
		// assert false : parameter;
		// fd.setFilterExtensions(new String[] { "*.ser.xml", "*.*" });
		// fd.setText("DIALOG");
		// fd.setText("Datei öffnen");
		// @SuppressWarnings("unused")
		// String strFile = fd.open();
		// assert false;
		// return;
		// }
		// assert false : parameter;
		//
		// if (parameter.equals("-rec")) {
		// assert false;
		// TextInputDialog dialog = new
		// TextInputDialog(Display.getCurrent().getActiveShell(),
		// SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		// dialog.setMessage("MESSAGE");
		// dialog.setButtons();
		// System.out.println(dialog.open());
		// @SuppressWarnings("unused")
		// String macroname = null, comment = null;
		// // this.getReceiver().recRecorder(macroname, comment);
		// return;
		// }
		// if (parameter.equals("-s")) {
		// if(LOGGER) logger.debug("Content *");
		// receiver = this.getReceiver().toString();
		// continue;
		// }
		//
		// if (parameter.equals("-all")) {
		// if(LOGGER) logger.debug("Content -all");
		// receiver = null;
		// continue;
		// }
		// if (parameter.equalsIgnoreCase("-reset")) {
		// if(LOGGER) logger.debug("Content -reset");
		// reset = true;
		// continue;
		// }
		// if (parameter.endsWith("Menu")) {
		// if(LOGGER) logger.debug("Content ..Menu");
		// receiver = parameter;
		// continue;
		// }
		// if (parameter.endsWith("+")) {
		// if(LOGGER) logger.debug("Content +");
		// details = true;
		// pattern = parameter.substring(0, parameter.length() - 1).trim();
		// continue;
		// }
		// pattern = parameter;
		// if(LOGGER) logger.debug("Pattern " + pattern);
		// assert false;
		// }
		// if (IRecorder.recorder.isRecord()) {
		// IRecorder.recorder.setRecord(this);
		// }
		// assert false;
		// // this.result = this.getReceiver().contentRecorder(receiver,
		// pattern,
		// details, reset);
		// // ((IMenu)this.getReceiver()).setInformation(null, Screen.C,
		// this.result,
		// // false, true);
		// return;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#help()
	 */
	@Override
	public final void help() {
		this.help(new String[] {
				"Aufruf",
				TAB + "Recorder " + "[-h" + "|-s" + "|-p \"RECORD\""
						+ "|-r \"RECORD\" \"COMMENT\""
						+ "|-f [all|reset|..Menu]+| REGEX] ",
				"-h    " + TAB + "Diese Hilfe",
				"-p \"RECORD\"" + TAB + "Play \"RECORD\"",
				"-r \"RECORD\" \"COMMENT\"" + TAB
						+ "Record \"RECORD\" Kommentar \"COMMENT\"",
				"-l    " + TAB + "Show Records",
				"-f    " + TAB + "Filter",
				"." + TAB + "all   " + TAB + "Alle Makros",
				"-f " + TAB + "reset " + TAB + "Filter zurücksetzen",
				"-f " + TAB + "..Menu" + TAB
						+ "Makros eines bestimmten Menüs anzeigen",
				"-f " + TAB + "REGEX " + TAB
						+ "Regulärer Ausdruck zum Filtern der Anzeige" });
	}

	// ---- Parameteranfragen --------------------------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#testParameter(java.lang.String)
	 */
	@Override
	public String testParameter(String parameter) {
		try {
			// Testen, ob Parameter bekannt ist
			c.valueOf(parameter);
		} catch (IllegalArgumentException e) {
			// Testen, ob es ein Sonderzeichen '?' im Button etc ist
			for (c cp : c.values()) {
				if (cp.buttontext != null && cp.buttontext.equals(parameter)) {
					return cp.name();
				}
			}
			return null;
		} catch (Exception e) {
			assert false; //ErrApp.NDEF.erraufruf("Unerwartete Exception");
		}
		return parameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#maxParameter(java.lang.String)
	 */
	@Override
	public int maxParameter(String parameter) {
		try {
			return c.valueOf(parameter).parameterMax;
		} catch (IllegalArgumentException e) {
			for (CGRecorder.c c : CGRecorder.c.values()) {
				if (c.buttontext.equals(parameter)) {
					return c.parameterMax;
				}
			}
			assert false; //ErrApp.NDEF.erraufruf("Falscher Parameter \'" + parameter
//					+ "\'");
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#minParameter(java.lang.String)
	 */
	@Override
	public int minParameter(String parameter) {
		return c.valueOf(parameter).parameterMin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see name.tfossi.apolge.common.cmd.ICmd#call(java.lang.String,
	 * java.lang.String[])
	 */
	@Override
	public void call(String parameter, String... value) {
		c.valueOf(parameter).call(this, value);
	}

	//
	// /** Ergebnisliste TOC */
	// @SuppressWarnings("unused")
	// private String[] result;
	//
	// /** Pattern für Receiver */
	// @SuppressWarnings("unused")
	// private Text receiverText;
	//
	// /** Pattern für Makronamen */
	// @SuppressWarnings("unused")
	// private Text patternText;
	//
	// // /** Resetcheckbox */
	// @SuppressWarnings("unused")
	// private Button reset;
	//
	// /** Inhaltsangabe */
	// @SuppressWarnings("unused")
	// private List tocList;
	//
	// /** Detailangabe */
	// @SuppressWarnings("unused")
	// private Text detailText;
	//
	// /** Abgeleitet Dialogshell */
	// @SuppressWarnings("unused")
	// private Shell dialog;
	//
	// /** Aufruf des Commands via SWT */
	// final void SWTCommand() {
	// // IMenu menu = (IMenu)this.getReceiver();
	// // final IGrfCntr session =
	// // MetaData.getInstance().getSession(menu.getStateContext());
	// // session.getGrafik().getGroup(Screen.MI);
	// //
	// // this.dialog = new
	// Shell(session.getGrafik().getGroup(Screen.MI).getShell(),
	// // SWT.DIALOG_TRIM
	// // | SWT.APPLICATION_MODAL);
	// //
	// // this.dialog.setText("Dialog Shell");
	// // this.dialog.setLayout(new GridLayout(3, false));
	// //
	// // // ---- 1. Zeile Receiver / Play
	// //
	// // Label receiverLabel = new Label(this.dialog, SWT.NONE);
	// // receiverLabel.setText("Receiver [*]");
	// //
	// // this.receiverText = new Text(this.dialog, SWT.BORDER);
	// // GridData gridData = new GridData();
	// // gridData.widthHint = 100;
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // this.receiverText.setLayoutData(gridData);
	// // this.receiverText.setText("*");
	// //
	// // gridData = new GridData();
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // gridData.verticalAlignment = SWT.FILL;
	// // gridData.grabExcessVerticalSpace = true;
	// //
	// // Button play = new Button(this.dialog, SWT.RADIO);
	// // play.setText("PLAY");
	// // if (IRecorder.recorder.isPlay())
	// // play.setSelection(ConstValue.ON);
	// //
	// // play.addSelectionListener(new SelectionAdapter() {
	// // @SuppressWarnings({ "synthetic-access" })
	// // @Override
	// // public void widgetSelected(SelectionEvent e) {
	// // if (!((Button) e.widget).getSelection())
	// // return;
	// // int[] selection = CGRecorder.this.tocList.getSelectionIndices();
	// // if (selection.length == 1) {
	// // int index = selection[0];
	// // String content = CGRecorder.this.tocList.getItem(index);
	// // if(LOGGER) logger.debug("User play Record " + content);
	// // getReceiver().playRecorder(content.substring(0,
	// content.indexOf(":")));
	// // CGRecorder.this.dialog.close();
	// // }
	// // }
	// //
	// // @Override
	// // public void widgetDefaultSelected(SelectionEvent e) {
	// // assert false;
	// // }
	// // });
	// // // ---- 2. Zeile Pattern / Record
	// //
	// // Label patternLabel = new Label(this.dialog, SWT.NONE);
	// // patternLabel.setText("Pattern [.*]");
	// //
	// // this.patternText = new Text(this.dialog, SWT.BORDER);
	// // gridData = new GridData();
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // this.patternText.setLayoutData(gridData);
	// // this.patternText.setText(".*");
	// //
	// // gridData = new GridData();
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // gridData.verticalAlignment = SWT.FILL;
	// // gridData.grabExcessVerticalSpace = true;
	// //
	// // Button rec = new Button(this.dialog, SWT.RADIO);
	// // rec.setText("REC");
	// // if (IRecorder.recorder.isRecord())
	// // rec.setSelection(ConstValue.ON);
	// //
	// // rec.addSelectionListener(new SelectionAdapter() {
	// // @SuppressWarnings({ "synthetic-access" })
	// // @Override
	// // public void widgetSelected(SelectionEvent e) {
	// // if (!((Button) e.widget).getSelection())
	// // return;
	// // if(LOGGER) logger.debug("User record Record " + new String[] { "ONE",
	// "HARDVERDRAHTET"
	// // });
	// // getReceiver().recRecorder("ONE", "HARDVERDRAHTET");
	// // CGRecorder.this.dialog.close();
	// // }
	// //
	// // @Override
	// // public void widgetDefaultSelected(SelectionEvent e) {
	// // assert false;
	// // }
	// // });
	// //
	// // // ---- 3. Zeile Reset / Stopp
	// //
	// // Label resetLabel = new Label(this.dialog, SWT.NONE);
	// // resetLabel.setText("Daten zurücksetzen");
	// // this.reset = new Button(this.dialog, SWT.CHECK);
	// //
	// // gridData = new GridData();
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // gridData.verticalAlignment = SWT.FILL;
	// // gridData.grabExcessVerticalSpace = true;
	// // Button stopp = new Button(this.dialog, SWT.RADIO);
	// // stopp.setText("STOPP");
	// // if (!(IRecorder.recorder.isPlay() ||
	// IRecorder.recorder.isRecord()))
	// // stopp.setSelection(ConstValue.ON);
	// //
	// // stopp.addSelectionListener(new SelectionAdapter() {
	// // @SuppressWarnings({ "synthetic-access" })
	// // @Override
	// // public void widgetSelected(SelectionEvent e) {
	// // if (!((Button) e.widget).getSelection())
	// // return;
	// // // Stoppen und Speichern
	// // getReceiver().stopRecorder();
	// // CGRecorder.this.dialog.close();
	// // }
	// // @Override
	// // public void widgetDefaultSelected(SelectionEvent e) {
	// // assert false;
	// // }
	// // });
	// //
	// // // ---- 4. Zeile Delete
	// //
	// // Button delete = new Button(this.dialog, SWT.PUSH);
	// // gridData = new GridData();
	// // gridData.horizontalSpan = 3;
	// // gridData.horizontalAlignment = SWT.LEFT;
	// // delete.setText("Delete");
	// // delete.setLayoutData(gridData);
	// //
	// // delete.addSelectionListener(new SelectionAdapter() {
	// // @SuppressWarnings({ "synthetic-access" })
	// // @Override
	// // public void widgetSelected(SelectionEvent e) {
	// // int[] selection = CGRecorder.this.tocList.getSelectionIndices();
	// // if (selection.length == 1) {
	// // int index = selection[0];
	// // String content = CGRecorder.this.tocList.getItem(index);
	// // if(LOGGER) logger.debug("User delete Record " + content);
	// // getReceiver().delRecorder(content.substring(0, content.indexOf(":")));
	// // getParameter().add(CGRecorder.this.receiverText);
	// // getParameter().add(CGRecorder.this.patternText);
	// // command();
	// // CGRecorder.this.detailText.setText("");
	// // CGRecorder.this.tocList.setItems(CGRecorder.this.result);
	// // } else {
	// // MessageBox mdia = new
	// // MessageBox(session.getGrafik().getGroup(Screen.MI).getShell(),
	// // SWT.ICON_ERROR| SWT.OK);
	// // mdia.setText("Eingabefehler!");
	// //
	// mdia.setMessage("Bitte genau EIN Makro auswählen, das gelöscht werden soll.");
	// // mdia.open();
	// // }
	// // }
	// // });
	// //
	// // // ---- 5. Zeile TOC
	// //
	// // Label tocLabel = new Label(this.dialog, SWT.NONE);
	// // tocLabel.setText("Makroliste:");
	// // gridData = new GridData();
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // tocLabel.setLayoutData(gridData);
	// //
	// // this.tocList = new List(this.dialog, SWT.BORDER | SWT.SINGLE |
	// SWT.V_SCROLL);
	// // gridData = new GridData();
	// // gridData.horizontalSpan = 2;
	// // gridData.heightHint = 150;
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // gridData.verticalAlignment = SWT.FILL;
	// // gridData.grabExcessVerticalSpace = true;
	// // this.tocList.setLayoutData(gridData);
	// // if (this.result != null)
	// // this.tocList.setItems(this.result);
	// //
	// // this.tocList.addListener(SWT.Selection, new Listener() {
	// // @SuppressWarnings({ "synthetic-access" })
	// // public void handleEvent(Event e) {
	// // int[] selection = CGRecorder.this.tocList.getSelectionIndices();
	// // if (selection.length == 1) {
	// // int index = selection[0];
	// // String content = CGRecorder.this.tocList.getItem(index);
	// // String text = getReceiver().detailRecorder(
	// // content.substring(0, content.indexOf(":")));
	// // if(LOGGER) logger.debug("User detailed " + content);
	// // CGRecorder.this.detailText.setText(text);
	// // }
	// // }
	// // });
	// // CGRecorder.this.tocList.addListener(SWT.DefaultSelection, new
	// // Listener() {
	// // public void handleEvent(Event e) {
	// // assert false;
	// // }
	// // });
	// //
	// // // ---- 6. Zeile Detaildaten
	// //
	// // Label detailLabel = new Label(CGRecorder.this.dialog, SWT.NONE);
	// // detailLabel.setText("Makrodaten:");
	// // gridData = new GridData();
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // detailLabel.setLayoutData(gridData);
	// //
	// // this.detailText = new Text(CGRecorder.this.dialog, SWT.BORDER |
	// // SWT.V_SCROLL);
	// // gridData = new GridData();
	// // gridData.horizontalSpan = 2;
	// // gridData.heightHint = 150;
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // gridData.verticalAlignment = SWT.FILL;
	// // gridData.grabExcessVerticalSpace = true;
	// // this.detailText.setLayoutData(gridData);
	// // this.detailText.setText("");
	// //
	// // // ---- 7. Zeile OK / Cancel
	// //
	// // gridData = new GridData();
	// // gridData.horizontalSpan = 2;
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // gridData.verticalAlignment = SWT.FILL;
	// // gridData.grabExcessVerticalSpace = true;
	// // Button ok = new Button(CGRecorder.this.dialog, SWT.PUSH);
	// // ok.setText("Laden");
	// // ok.setLayoutData(gridData);
	// //
	// // Button cancel = new Button(CGRecorder.this.dialog, SWT.PUSH);
	// // cancel.setText("Ende");
	// // gridData = new GridData();
	// // gridData.horizontalAlignment = SWT.FILL;
	// // gridData.grabExcessHorizontalSpace = true;
	// // gridData.verticalAlignment = SWT.FILL;
	// // gridData.grabExcessVerticalSpace = true;
	// // cancel.setLayoutData(gridData);
	// //
	// // cancel.addSelectionListener(new SelectionAdapter() {
	// // @SuppressWarnings("synthetic-access")
	// // @Override
	// // public void widgetSelected(SelectionEvent e) {
	// // if(LOGGER) logger.debug("User leaved dialog");
	// // getParameter().clear();
	// // CGRecorder.this.dialog.close();
	// // }
	// // });
	// //
	// // ok.addSelectionListener(new SelectionAdapter() {
	// // @SuppressWarnings("synthetic-access")
	// // @Override
	// // public void widgetSelected(SelectionEvent e) {
	// // if(LOGGER) logger.debug("User reload");
	// // if (CGRecorder.this.reset.getSelection())
	// // getParameter().add("reset");
	// // getParameter().add(CGRecorder.this.receiverText);
	// // getParameter().add(CGRecorder.this.patternText);
	// // command();
	// // CGRecorder.this.tocList.setItems(CGRecorder.this.result);
	// // }
	// // });
	// //
	// // CGRecorder.this.dialog.setDefaultButton(ok);
	// // CGRecorder.this.dialog.pack();
	// // CGRecorder.this.dialog.open();
	// //
	// // while (!CGRecorder.this.dialog.isDisposed()) {
	// // if (!CGRecorder.this.dialog.getDisplay().readAndDispatch())
	// // CGRecorder.this.dialog.getDisplay().sleep();
	// // }
	// }

	// ---- SWT
	// ------------------------------------------------------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.common.cmd.ACmd#showParameterCommand(org.eclipse.swt
	 * .events. SelectionEvent)
	 */
	@Override
	public void showParameterCommand(SelectionEvent e) {
		IMenu menu = (IMenu) this.getReceiver();
		Cntr cntr = menu.getStateContext().getCntr();

		Group basegrp = cntr.getCPMBaseGroup();
		if(LOGGER) logger.trace("SWT:: Baue Parameterwidgets für [" + basegrp
				+ "] zuammen.");

		// cntr.storeCPMWidget(menu.getView().getClass(), bPW);
		// cntr.storeWidget(
		// menu.getView(),
		// null,
		this.buildParameterWidgets(basegrp);
		// ,
		// Screen.CPM);
		if(LOGGER) logger.trace("SWT:: Der Befehl [" + this.getClass().getSimpleName()
				+ "] hat keine Parameter." + e.text);
		cntr.layout();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.common.cmd.ACmd#buildSWTWidget(org.eclipse.swt.widgets
	 * .Group)
	 */
	@Override
	public final List<Widget> buildSWTWidget(final Group grp) {
		assert grp != null;
		if (this.widget == null || this.widget.size() == 0) {
			if(LOGGER) logger.debug("SWT::" + this.toString()
					+ "\tNeue Widgetinstanz auf [" + grp + "] anlegen");
			Button button = new Button(grp, SWT.PUSH);

			setTestMenuFont(button);

			button.setText(this.getCode());
			button.setToolTipText("Liste der Macros in CGRecorder");
			button.setEnabled(true);
			// this.widget.put(this.getCode(), button);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					showParameterCommand(e);
				}
			});
			this.widget.add(button);
		} else {
			for (Widget w : this.widget) {
				if (!w.isDisposed()) {
					if(LOGGER) logger.debug("SWT::" + this.toString()
							+ "\tVorhandene Widgetinstanz auf Gruppe [" + grp
							+ "] gelegt");
					((Button) w).setParent(grp);
				}
			}
		}
		return this.widget;
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// *
	// name.tfossi.apolge.common.cmd.ACmd#buildParameterWidgets(org.eclipse.swt.widgets
	// * .Group)
	// */
	// @Override
	// public final void /*List<Widget>*/ buildParameterWidgets(final Group grp)
	// {
	//
	// assert grp != null;
	// this.group = grp;
	// for (final c elements : c.values()) {
	// if (!elements.showGui)
	// continue;
	// if (!this.parameterwidget.containsKey(elements)
	// || this.parameterwidget.get(elements).isDisposed()) {
	// if(LOGGER) logger.debug("SWT::" + this.toString() + "\tNeue Widgetinstanz auf [" +
	// grp
	// + "] anlegen");
	// this.parameterwidget.put(elements, new Button(grp, SWT.PUSH));
	// ((Button)
	// this.parameterwidget.get(elements)).setText(elements.buttontext);
	// ((Button)
	// this.parameterwidget.get(elements)).setToolTipText(elements.tooltiptext);
	// ((Button) this.parameterwidget.get(elements)).setEnabled(true);
	//
	// ConstMethod.setTestMenuFont((Button) this.parameterwidget.get(elements));
	//
	// ((Button) this.parameterwidget.get(elements))
	// .addSelectionListener(new SelectionAdapter() {
	// @Override
	// public void widgetSelected(@SuppressWarnings("unused") SelectionEvent e)
	// {
	// CGRecorder.this.clearParameter();
	// CGRecorder.this.setParameter('-' + elements.name());
	// if (elements.getParameter().getString() != null)
	// for (String s : elements.getParameter().getString())
	// CGRecorder.this.setParameter(s);
	//
	// command();
	// }
	// });
	// } else if (!this.parameterwidget.get(elements).isDisposed()) {
	// if(LOGGER) logger.debug("SWT::" + this.toString() +
	// "\tVorhandene Widgetinstanz auf Gruppe ["
	// + grp + "] gelegt");
	// ((Button) this.parameterwidget.get(elements)).setParent(grp);
	//
	// } else {
	// if(LOGGER) logger.trace("Buttoninstanz ist disposed!");
	// }
	// }
	// // return null;
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * name.tfossi.apolge.common.cmd.ACmd#buildParameterWidgets(org.eclipse.swt
	 * .widgets.Group)
	 */
	@Override
	public final void buildParameterWidgets(final Group grp) {

		assert grp != null;
		for (final c elements : c.values()) {
			if (!elements.show)
				continue;
			boolean found = false;
			for (Widget w : this.parameterwidget) {
				Button wb = (Button) w;
				if (wb.getText().equals(elements.buttontext)) {
					if (w.isDisposed()) {
						if(LOGGER) logger.debug("SWT::" + this.toString()
								+ "\tVorhandene Widgetinstanz auf Gruppe ["
								+ grp + "] gelegt");
						wb.setParent(grp);
					} else {
						if(LOGGER) logger.trace("Buttoninstanz ist disposed!");
					}
					found = true;
				}
			}
			if (!found) {
				if(LOGGER) logger.debug("SWT::" + this.toString()
						+ "\tNeue Widgetinstanz auf [" + grp + "] anlegen");
				Button button = new Button(grp, SWT.PUSH);
				button.setText(elements.buttontext);
				button.setToolTipText(elements.tooltiptext);
				button.setEnabled(true);
				setTestMenuFont(button);
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						CGRecorder.this.clearParameter();
						CGRecorder.this.setParameter('-' + elements.name());
						if (elements.getParameter().getString() != null)
							for (String s : elements.getParameter().getString())
								CGRecorder.this.setParameter(s);

						command();
					}
				});
				this.parameterwidget.add(button);
			}
		}
	}

	// ---- Selbstverwaltung
	// -----------------------------------------------------
	/** serialVersionUID */
	private final static long serialVersionUID = VERSION;
	/** logger */
	final static Logger logger = Logger.getLogger(CGRecorder.class.getPackage()
			.getName());

	/**
	 * Befehl: Liefere die Liste der generellen und Menu-Macros
	 *
	 * @param name -
	 * @param unvisibleMode -
	 * @modified -
	 */
	public CGRecorder(final String name, boolean unvisibleMode) {
		super(name, unvisibleMode);
	}

}
