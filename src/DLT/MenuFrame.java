package DLT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.joda.time.LocalDate;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;

/**
 * MenuFrame class
 * 
 * This class defines the main JFrame for the application. It contains 
 * the File menu and handles importing/exporting data.
 * 
 * @author Trent
 * @author Bryan
 *
 */
public class MenuFrame extends JFrame implements WindowListener {
	
	private static final long serialVersionUID = -8248541759702200986L;
	
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem export = new JMenuItem("Export");
	private JMenu imprt = new JMenu("Import");
	private JMenu cmmnd = new JMenu("Commands");
	private JMenuItem srt = new JMenuItem("Sort");
	private JMenuItem importCSV = new JMenuItem("CSV");
	private JMenuItem importXML = new JMenuItem("XML");
	private JMenuItem commandCPY = new JMenuItem("Copy selected case");
	private JMenuItem commandDEL = new JMenuItem("Delete selected case");
	private JMenuItem commandNEW = new JMenuItem("Add new case");
	
	private JButton jbSearch = new JButton("Search");
	
	public MenuFrame() {
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);
		
		JMenu editMenu = new JMenu("Edit");
		menu.add(editMenu);
		
		if (Main.BRANCH == -1) {
			fileMenu.add(save);
			KeyStroke ctrl_s = KeyStroke.getKeyStroke(
					KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
			save.setAccelerator(ctrl_s);			
		}
		
		if (Main.BRANCH == -1) {
			fileMenu.add(export);
			KeyStroke ctrl_e = KeyStroke.getKeyStroke(
					KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
			export.setAccelerator(ctrl_e);	
		}
		
		fileMenu.add(imprt);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		imprt.add(importCSV);
		imprt.add(importXML);
		
		editMenu.add(cmmnd);
		editMenu.setMnemonic(KeyEvent.VK_E);
		
		cmmnd.add(commandNEW);
		cmmnd.add(commandCPY);
		cmmnd.add(commandDEL);
		cmmnd.add(srt);
		
		//set keystrokes
		KeyStroke ctrl_o = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		importCSV.setAccelerator(ctrl_o);
		
		KeyStroke ctrl_i = KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK);
		importXML.setAccelerator(ctrl_i);
		
		KeyStroke ctrl_b = KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK);
		commandCPY.setAccelerator(ctrl_b);
		
		KeyStroke ctrl_d = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK);
		commandDEL.setAccelerator(ctrl_d);
		
		KeyStroke ctrl_n = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
		commandNEW.setAccelerator(ctrl_n);
		
		KeyStroke alt_s = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK);
		srt.setAccelerator(alt_s);
		
		//user clicked save
		save.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (e.getSource() == save) {
					
					//update object
					Main.SAVE_CHANGEABLE_FIELDS();
					
					//update list model
					Main.UPDATE_LIST_MODEL(Main.SELECTED_INDEX);
					
					//write to file
					try {
						FileOutputStream fileStream = new FileOutputStream("Data.ser");
						ObjectOutputStream os = new ObjectOutputStream(fileStream);
						for (int i = 0; i < Main.FIELDS.size(); i++) {
							os.writeObject(Main.FIELDS.get(i));
						}
						Main.HAS_UNSAVED_CHANGES = false;
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		
		//user clicked export
		export.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (e.getSource() == export) {
					write();
				}
			}
		});
		
		//import
		
		//...csv
		importCSV.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				from_csv_file();
			}
		});
		
		//...xml
		importXML.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				from_file();
			}
		});
		
		//commands
		
		//...copy
		commandCPY.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				case_copy();
			}
		});
		
		//...del
		commandDEL.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (Main.FIELDS.size() == 1) {
					//clear all
					Main.FIELDS.clear();
					Main.LIST_MODEL.clear();
					Main.SELECTED_INDEX = 0;
					
					//add new element
					Main.LIST_MODEL.add(Main.SELECTED_INDEX, "NEW");
					Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
					Main.FIELDS.add(new DataField());
					LocalDate today = new LocalDate();
					int days = 1;
					while (days < 4) {
						if (Main.FIELDS.get(Main.SELECTED_INDEX).workingDaysBetween(today, today.plusDays(days)) == 1)
							break;
						days++;
					}
					Main.FIELDS.get(Main.SELECTED_INDEX).setCommittedDate(today.plusDays(days));
					Main.FIELDS.get(Main.SELECTED_INDEX).setStatus(Main.STATUS_IS_TOUCHED);
					Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
				}
				
				if (Main.FIELDS.size() > 1) {
					//remove selected element
					Main.FIELDS.remove(Main.SELECTED_INDEX);
					Main.LIST_MODEL.remove(Main.SELECTED_INDEX);
					if (Main.SELECTED_INDEX == Main.FIELDS.size())
						Main.SELECTED_INDEX -= 1;
					
					//update view
					Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
					Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
				}
			}
		});
		
		//...new
		commandNEW.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent arg0) {
				//update object
				Main.SAVE_CHANGEABLE_FIELDS();
				
				//update list model
				Main.UPDATE_LIST_MODEL(Main.SELECTED_INDEX);
				
				//add item
				Main.FIELDS.add(new DataField());
				Main.SELECTED_INDEX = Main.FIELDS.size() - 1;
				
				//set initial status to touched and committed date to next business day
				LocalDate today = new LocalDate();
				int days = 1;
				while (days < 4) {
					if (Main.FIELDS.get(Main.SELECTED_INDEX).workingDaysBetween(today, today.plusDays(days)) == 1)
						break;
					days++;
				}
				Main.FIELDS.get(Main.SELECTED_INDEX).setCommittedDate(today.plusDays(days));
				Main.FIELDS.get(Main.SELECTED_INDEX).setStatus(Main.STATUS_IS_TOUCHED);
				
				//add new item to case list
				Main.LIST_MODEL.addElement("NEW");
				Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
				Main.LIST.ensureIndexIsVisible(Main.SELECTED_INDEX);
				
				Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
			}
		});
		
		//...sort
		srt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				//save the service request number of the currently selected case for later
				String selectedIndexSR = Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest();
				
				//first arrange by committed date
				int noSwapCounter = 0;
				while (noSwapCounter < 2) {
					boolean didSwap = false;
					for (int i = 0; i < Main.FIELDS.size() - 1; i++) {
						if (Main.FIELDS.get(i).getCommittedDate().isAfter(
								Main.FIELDS.get(i + 1).getCommittedDate())) {
							DataField temp = new DataField(Main.FIELDS.get(i));
							Main.FIELDS.set(i, new DataField(Main.FIELDS.get(i + 1)));
							Main.FIELDS.set(i + 1, temp);
							didSwap = true;
						}
					}
					if (didSwap == false) {
						noSwapCounter++;
					}
				}
				
				//then bubble down closed cases
				noSwapCounter = 0;
				while (noSwapCounter < 2) {
					boolean didSwap = false;
					for (int i = 0; i < Main.FIELDS.size() - 1; i++) {
						if ((Main.FIELDS.get(i).getStatus() == Main.STATUS_IS_CLOSED) && 
							(Main.FIELDS.get(i + 1).getStatus() != Main.STATUS_IS_CLOSED)) {
							DataField temp = new DataField(Main.FIELDS.get(i));
							Main.FIELDS.set(i, new DataField(Main.FIELDS.get(i + 1)));
							Main.FIELDS.set(i + 1, temp);
							didSwap = true;
						}
					}
					if (didSwap == false) {
						noSwapCounter++;
					}
				}
				
				//then, for cases with the same committed date, sort by case age
				noSwapCounter = 0;
				while (noSwapCounter < 2) {
					boolean didSwap = false;
					for (int i = 0; i < Main.FIELDS.size() - 1; i++) {
						if (Main.FIELDS.get(i).getCommittedDate().equals(
								Main.FIELDS.get(i + 1).getCommittedDate())) {
							if (Main.FIELDS.get(i).getOpenedDate().isAfter(
									Main.FIELDS.get(i + 1).getOpenedDate())) {
								DataField temp = new DataField(Main.FIELDS.get(i));
								Main.FIELDS.set(i, new DataField(Main.FIELDS.get(i + 1)));
								Main.FIELDS.set(i + 1, temp);
								didSwap = true;
							}
						}
					}
					if (didSwap == false) {
						noSwapCounter++;
					}
				}
				
				//update list model
				for (int i = 0; i < Main.FIELDS.size(); i++) {
					StringBuilder sb = new StringBuilder();
					if (!Main.FIELDS.get(i).getServiceRequest().isEmpty()) {
						sb.append(Main.FIELDS.get(i).getServiceRequest());
					}
					
					if (!Main.FIELDS.get(i).getServiceRequest().isEmpty() &&
							!Main.FIELDS.get(i).getPrimaryFirstName().isEmpty()) {
						 sb.append(" / ");
					}
					
					if (!Main.FIELDS.get(i).getPrimaryFirstName().isEmpty()) {
						sb.append(Main.FIELDS.get(i).getPrimaryFirstName()
								+ " " 
								+ Main.FIELDS.get(i).getPrimaryLastName());
					}
					
					if (!sb.toString().isEmpty()) {
						Main.LIST_MODEL.set(i, sb.toString());
					} 
					else Main.LIST_MODEL.set(i, "NEW");
				}
				
				//set selected index
				if (!selectedIndexSR.isEmpty()) {
					for (int i = 0; i < Main.FIELDS.size(); i++) {
						if (Main.FIELDS.get(i).getServiceRequest().equals(selectedIndexSR)) {
							Main.SELECTED_INDEX = i;
							break;
						}
					}
				}
				Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
				Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
				
				//flag unsaved changes
				Main.HAS_UNSAVED_CHANGES = true;
			
			}
			
		}
		
				);
		
		
		JScrollPane jspInfo = new JScrollPane(new InfoPanel());
		jspInfo.getVerticalScrollBar().setUnitIncrement(16);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(new EmptyBorder(5, 4, 5, 4));
		searchPanel.setLayout(new BorderLayout());
		jbSearch.setEnabled(false);
		searchPanel.add(jbSearch, BorderLayout.EAST);
		searchPanel.add(Main.JTF_SEARCH_FIELD, BorderLayout.CENTER);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(true);
		leftPanel.setBackground(Color.WHITE);
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(searchPanel, BorderLayout.NORTH);
		leftPanel.add(new ListPanel(), BorderLayout.CENTER);
		
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, jspInfo);
		
		this.setSize(Main.F_WIDTH, Main.F_HEIGHT);
		this.setMinimumSize(new Dimension(Main.F_MIN_WIDTH, Main.F_MIN_HEIGHT));
		this.setTitle("BLT Tool 2.3 \"Wheat Bread\""); //Backup Logging Tool... Tool!
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setContentPane(jsp);
		this.setResizable(true);
		this.addWindowListener(this);
		this.setVisible(true);
		
		Main.JTF_SEARCH_FIELD.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void changedUpdate(DocumentEvent e) {
				if (Main.JTF_SEARCH_FIELD.getText().isEmpty())
					jbSearch.setEnabled(false);
				else jbSearch.setEnabled(true);
			}

			@Override public void insertUpdate(DocumentEvent e) {
				if (Main.JTF_SEARCH_FIELD.getText().isEmpty())
					jbSearch.setEnabled(false);
				else jbSearch.setEnabled(true);
			}

			@Override public void removeUpdate(DocumentEvent e) {
				if (Main.JTF_SEARCH_FIELD.getText().isEmpty())
					jbSearch.setEnabled(false);
				else jbSearch.setEnabled(true);
			}
		});
		
		//search
		jbSearch.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				int newSelectedIndex = 0;
				for (DataField d: Main.FIELDS) {
					boolean foundMatch = false;
					
					if (Main.JTF_SEARCH_FIELD.getText().toLowerCase().contains(
							d.getServiceRequest().toLowerCase()) &&
							!d.getServiceRequest().isEmpty()) {
						foundMatch = true;
					}
					
					if (Main.JTF_SEARCH_FIELD.getText().toLowerCase().contains(
							d.getServiceTag().toLowerCase()) &&
							!d.getServiceTag().isEmpty()) {
						foundMatch = true;
					}
					
					if (foundMatch) {
						boolean hasUnsavedChanges = Main.HAS_UNSAVED_CHANGES;
						Main.SAVE_CHANGEABLE_FIELDS();
						Main.UPDATE_LIST_MODEL(Main.SELECTED_INDEX);
						Main.HAS_UNSAVED_CHANGES = hasUnsavedChanges;
						
						//move to found index
						Main.SELECTED_INDEX = newSelectedIndex;
						Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
						Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
						Main.LIST.ensureIndexIsVisible(Main.SELECTED_INDEX);
						
						break;
					}			
					newSelectedIndex++;
				}				
			}
		});
	}

	@Override public void windowActivated(WindowEvent e) {
		
	}

	@Override public void windowClosed(WindowEvent e) {
		
	}

	@Override public void windowClosing(WindowEvent e) {
		if (Main.BRANCH == -1) {
			//no unsaved changes; exit without saving
			if (!Main.HAS_UNSAVED_CHANGES) {
				System.exit(0);
			}
			
			//show save dialog
			int choice = JOptionPane.showConfirmDialog(this, 
					"Would you like to save your changes?", "Save", 
					JOptionPane.YES_NO_CANCEL_OPTION);
			
			//...user selected yes; save and exit
			if (choice == JOptionPane.YES_OPTION) {
				Main.SAVE_CHANGEABLE_FIELDS();
				try { //write to file
					FileOutputStream fileStream = new FileOutputStream("Data.ser");
					ObjectOutputStream os = new ObjectOutputStream(fileStream);
					for (DataField df : Main.FIELDS) os.writeObject(df);
					os.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				System.exit(0);
			}
			
			//...user selected no; exit without saving
			else if (choice == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			
			//...user selected cancel
			else if (choice == JOptionPane.CANCEL_OPTION) {
				//do nothing
			}
		}
		
		else { //mainstream branch
			if (Main.HAS_UNSAVED_CHANGES || 
					(Main.FIELDS.size() > 1) || 
					(Main.FIELDS.size() == 1 && !Main.FIELDS.get(0).isEmpty())) {
				//confirm user wants to exit and delete data
				Object[] options = {"Yes", "No", "Save"};
				int choice = JOptionPane.showOptionDialog(this, 
						"Close the program and clear data?", "Warning", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
						null, options, options[0]);
				
				//...user said yes; close and destroy data
				if (choice == 0) {
					Main.FIELDS.clear();
					try { //write to file
						FileOutputStream fileStream = new FileOutputStream("Data.ser");
						ObjectOutputStream os = new ObjectOutputStream(fileStream);
						for (DataField df : Main.FIELDS) os.writeObject(df);
						os.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					System.exit(0);
				}
				
				//...user said no; don't close program
				else if (choice == 1) {
					
				}
				
				//...user said save
				else if (choice == 2) {
					//warn user about saving customer data
					int c = JOptionPane.showConfirmDialog(this, 
							"Storing customer data requires approval.\n"
							+ "Are you sure you want to perform a one time save?");
					
					//...user said yes; save and exit
					if (c == JOptionPane.YES_OPTION) {
						Main.SAVE_CHANGEABLE_FIELDS();
						try { //write to file
							FileOutputStream fileStream = new FileOutputStream("Data.ser");
							ObjectOutputStream os = new ObjectOutputStream(fileStream);
							for (DataField df : Main.FIELDS) os.writeObject(df);
							os.close();
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
						System.exit(0);
					}
					
					//...user said no; don't close program
					else if (c == JOptionPane.NO_OPTION) {
						
					}
					
					//...user said cancel; don't close program
					else if (c == JOptionPane.CANCEL_OPTION) {
						
					}
				}
			} else { //nothing to save; exit without saving
				System.exit(0);
			}
		}
	} //endif window closing
	
	@Override public void windowDeactivated(WindowEvent e) {
		
	}
	
	@Override public void windowDeiconified(WindowEvent e) {
		
	}
	
	@Override public void windowIconified(WindowEvent e) {
		
	}
	
	@Override public void windowOpened(WindowEvent e) {
		
	}
	
	private void write() {
		try {
			XStream xstream = new XStream();
			xstream.alias("servicerequest", DataField.class);
			String xml = xstream.toXML(Main.FIELDS);			
			
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML Document", "xml");
			chooser.setFileFilter(filter);
			chooser.setSelectedFile(new File("save.xml"));
			int choice = chooser.showSaveDialog(this);
			if (choice == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
				FileWriter fw = new FileWriter(f);
				fw.write(xml);
				fw.close();
			} else {
				//do nothing
			}
		} catch (Exception e) {
			//do some stuff
		}
	}
		
	private void from_file() {
		try {
			XStream xstream = new XStream();
			xstream.alias("servicerequest", DataField.class);
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"XML Documents", "xml");
			chooser.setFileFilter(filter);
			int choice = chooser.showOpenDialog(this);
			if (choice == JFileChooser.OPEN_DIALOG) {
				Main.SELECTED_INDEX = 0;
				Main.FIELDS.clear();
				Main.LIST_MODEL.clear();
				
				Main.FIELDS = (ArrayList<DataField>) xstream.fromXML(chooser.getSelectedFile());
				
				//update list model
				Main.UPDATE_LIST_MODEL();
				
				Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
				Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
			}
		} catch (Exception e) {
			//reset all fields
			Main.FIELDS.clear();
			Main.LIST_MODEL.clear();			
			Main.SELECTED_INDEX = 0;
			Main.FIELDS.add(new DataField());
			Main.LIST_MODEL.add(Main.SELECTED_INDEX, "NEW");
			Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
			Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
			
			//output error message
			JOptionPane.showMessageDialog(this, "Error importing file!");
		}
	}
	
	private void from_csv_file() {
		
		//get path to .csv from user
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"CSV Files", "csv");
		chooser.setFileFilter(filter);
		int choice = chooser.showOpenDialog(this);
		if (choice != JFileChooser.OPEN_DIALOG) {
			return;
		}
		
		//extract data from .csv file
		char delimiter = '\t';
		try {
			CSVReader reader = new CSVReader(
					new FileReader(chooser.getSelectedFile()), delimiter);
			
			String[] nextLine = reader.readNext(); //first row of .csv w/ string values to check
			
			//check the first row, extract important indexes
			
			int OPENED_DATE_INDEX = 0;
			int SR_NUMBER_INDEX = 0;
			int SR_TITLE_INDEX = 0;
			int SERVICE_TAG_INDEX = 0;
			int LAST_NAME_INDEX = 0;
			int FIRST_NAME_INDEX = 0;
			int PRIMARY_PHONE_NUMBER_INDEX = 0;
			int E_MAIL_INDEX = 0;
			int COMMITTED_DATE_INDEX = 0;
						
			for (int i = 0; i < nextLine.length; i++) {
				
				//re-build title, e.g. "SR #", "SR Title", etc.. from .csv first row items
				// Note: fixes unicode spacing issue, e.g. changes "S R  T i t l e" back to "SR Title"
				StringBuilder temp = new StringBuilder();
				if (nextLine.length > 1) {
					
					//find start char
					int startChar = 0;
					for (int j = 1; j < nextLine[i].length(); j++) {
						if (nextLine[i].charAt(j) != ' ' &&
								nextLine[i].charAt(j) != 'þ') {
							startChar = j;
							break;
						}
					}
					
					for (int j = startChar; j <= nextLine[i].length() - 2; j+= 2) {
						temp.append(nextLine[i].charAt(j));
					}
					
					System.out.println(temp.toString()); //TODO remove this line; for troubleshooting
				}
				
				//assign index values based on column location in first row of .csv
				if (temp.toString().equals("Opened"))
					OPENED_DATE_INDEX = i;
				
				else if (temp.toString().equals("SR #"))
					SR_NUMBER_INDEX = i;
				
				else if (temp.toString().equals("SR Title"))
					SR_TITLE_INDEX = i;
				
				else if (temp.toString().equals("Service Tag"))
					SERVICE_TAG_INDEX = i;
				
				else if (temp.toString().equals("Last Name"))
					LAST_NAME_INDEX = i;
				
				else if (temp.toString().equals("First Name"))
					FIRST_NAME_INDEX = i;
				
				else if (temp.toString().equals("Primary Phone #"))
					PRIMARY_PHONE_NUMBER_INDEX = i;
				
				else if (temp.toString().equals("Email"))
					E_MAIL_INDEX = i;
				
				else if (temp.toString().equals("Committed"))
					COMMITTED_DATE_INDEX = i;
				
				else { } 
					//nothing else yet... lots of potential to extract other data from .csv file
			}
			
			//read the rest of the rows in the .csv
			
			while ((nextLine = reader.readNext()) != null) {
				
				//opened date
				StringBuilder openedDateString = new StringBuilder();
				if (nextLine.length > OPENED_DATE_INDEX) {
					for (int i = 3; i < nextLine[OPENED_DATE_INDEX].length() - 2; i += 2) {
						openedDateString.append(nextLine[OPENED_DATE_INDEX].charAt(i));
					}
				}
				String[] strDate = openedDateString.toString().split(" ")[0].split("/");
				boolean hasOpenedDate = false;
				LocalDate openedDate = new LocalDate();
				if (strDate.length >= 3) {
					hasOpenedDate = true;
					openedDate = new LocalDate(Integer.parseInt(strDate[2]),
										Integer.parseInt(strDate[0]),
										Integer.parseInt(strDate[1]));
				}
				
				//service request number
				StringBuilder serviceRequest = new StringBuilder();
				if (nextLine.length > SR_NUMBER_INDEX) {
					for (int i = 3; i < nextLine[SR_NUMBER_INDEX].length() - 2; i += 2) {
						serviceRequest.append(nextLine[SR_NUMBER_INDEX].charAt(i));
					}
				}
				
				//description (a.k.a service request title)
				StringBuilder description = new StringBuilder();
				if (nextLine.length > SR_TITLE_INDEX) {
					for (int i = 3; i < nextLine[SR_TITLE_INDEX].length() - 2; i += 2) {
						description.append(nextLine[SR_TITLE_INDEX].charAt(i));
					}
				}
				
				//service tag
				StringBuilder serviceTag = new StringBuilder();
				if (nextLine.length > SERVICE_TAG_INDEX) {
					for (int i = 3; i < nextLine[SERVICE_TAG_INDEX].length() - 2; i += 2) {
						serviceTag.append(nextLine[SERVICE_TAG_INDEX].charAt(i));
					}
				}
				
				//last name
				StringBuilder lastName = new StringBuilder();
				if (nextLine.length > LAST_NAME_INDEX) {
					for (int i = 3; i < nextLine[LAST_NAME_INDEX].length() - 2; i += 2) {
						lastName.append(nextLine[LAST_NAME_INDEX].charAt(i));
					}
				}
				
				//first name
				StringBuilder firstName = new StringBuilder();
				if (nextLine.length > FIRST_NAME_INDEX) {
					for (int i = 3; i < nextLine[FIRST_NAME_INDEX].length() - 2; i += 2) {
						firstName.append(nextLine[FIRST_NAME_INDEX].charAt(i));
					}
				}
				
				//name
				String name = firstName.toString() + " " + lastName.toString();
				
				//phone number
				StringBuilder phone = new StringBuilder();
				if (nextLine.length > PRIMARY_PHONE_NUMBER_INDEX) {
					for (int i = 3; i < nextLine[PRIMARY_PHONE_NUMBER_INDEX].length() - 2; i += 2) {
						if (Character.isDigit(nextLine[PRIMARY_PHONE_NUMBER_INDEX].charAt(i))) {
							phone.append(nextLine[PRIMARY_PHONE_NUMBER_INDEX].charAt(i));
						}
					}
				}
				
				//email
				StringBuilder email = new StringBuilder();
				if (nextLine.length > E_MAIL_INDEX) {
					for (int i = 3; i < nextLine[E_MAIL_INDEX].length() - 2; i += 2) {
						email.append(nextLine[E_MAIL_INDEX].charAt(i));
					}
				}
				
				//committed date
				StringBuilder committedDateString = new StringBuilder();
				if (nextLine.length > COMMITTED_DATE_INDEX) {
					for (int i = 3; i < nextLine[COMMITTED_DATE_INDEX].length() - 2; i+= 2) {
						committedDateString.append(nextLine[COMMITTED_DATE_INDEX].charAt(i));
					}
				}
				String[] stringDate = committedDateString.toString()
					.split(" ")[0].split("/"); //{month, day, year}
				boolean hasCommittedDate = false;
				LocalDate committedDate = new LocalDate();
				if (stringDate.length >= 3) {
					hasCommittedDate = true;
					committedDate = new LocalDate(Integer.parseInt(stringDate[2]), 
										 Integer.parseInt(stringDate[0]), 
										 Integer.parseInt(stringDate[1]));
				}
				
				//create data field object and populate
				DataField df = new DataField();
				df.setServiceRequest(serviceRequest.toString());
				df.setDescription(description.toString());
				df.setServiceTag(serviceTag.toString());
				df.setPrimaryFirstName(firstName.toString());
				df.setPrimaryLastName(lastName.toString());
				if (phone.toString().length() >= 3) {
					df.setPrimaryAreaCode(phone.substring(0, 3));
				}
				if (phone.toString().length() > 3) {
					df.setPrimaryPhoneNumber(phone.substring(3));
				}
				df.setPrimaryEmail(email.toString());
				
				if (hasCommittedDate) {
					df.setCommittedDate(committedDate);
					LocalDate today = new LocalDate();
					if (df.getCommittedDate().equals(today)) {
						df.setStatus(Main.STATUS_IS_DUE);
					}
					
					else if (df.getCommittedDate().isBefore(today)) {
						df.setStatus(Main.STATUS_IS_OVERDUE);
					}
					
					else if (df.getCommittedDate().isAfter(today)) {
						df.setStatus(Main.STATUS_IS_TOUCHED);
					}
				}
				if (hasOpenedDate) {
					df.setOpenedDate(openedDate);
				}
				
				//check if SR already exists
				boolean hasSR = false;
				int indexOfSR = 0;
				for (int i = 0; i < Main.FIELDS.size(); i++) {
					if (df.getServiceRequest().equals(Main.FIELDS.get(i).getServiceRequest())) {
						hasSR = true;
						indexOfSR = i;
						break;
					}
				}
				
				//...if not, add it
				if (!hasSR && !df.getServiceRequest().isEmpty()) {
					//add to FIELDS
					Main.FIELDS.add(df);
					
					//add to LIST_MODEL
					StringBuilder sb = new StringBuilder();
					if (!df.getServiceRequest().isEmpty()) {
						sb.append(df.getServiceRequest());
					}
					
					if (!df.getServiceRequest().isEmpty() &&
						!df.getPrimaryFirstName().isEmpty()) {
						sb.append(" / ");
					}
					
					if (!df.getPrimaryFirstName().isEmpty()) {
						sb.append(df.getPrimaryFirstName()
								+ " "
								+ df.getPrimaryLastName());
					}
					
					if (!sb.toString().isEmpty()) {
						Main.LIST_MODEL.add(Main.FIELDS.size() - 1, sb.toString());
					}
					
					//flag unsaved changes
					Main.HAS_UNSAVED_CHANGES = true;
				}
				
				//...if yes, set committed date to committed date from .csv
				if (hasSR & hasCommittedDate) {
					Main.FIELDS.get(indexOfSR).setCommittedDate(df.getCommittedDate());
					LocalDate today = new LocalDate();
					if (Main.FIELDS.get(indexOfSR).getCommittedDate().isBefore(today)) {
						Main.FIELDS.get(indexOfSR).setStatus(Main.STATUS_IS_OVERDUE);
					}
					
					else if (Main.FIELDS.get(indexOfSR).getCommittedDate().isAfter(today)) {
						Main.FIELDS.get(indexOfSR).setStatus(Main.STATUS_IS_TOUCHED);
					}
					
					else if (Main.FIELDS.get(indexOfSR).getCommittedDate().equals(today)) {
						Main.FIELDS.get(indexOfSR).setStatus(Main.STATUS_IS_DUE);
					}
					Main.LIST.repaint();
					
					//flag unsaved changes
					Main.HAS_UNSAVED_CHANGES = true;
				}
				
				//...if yes, set opened date to opened date from .csv
				if (hasSR & hasOpenedDate) {
					Main.FIELDS.get(indexOfSR).setOpenedDate(df.getOpenedDate());
				}
			}
			reader.close();
		} catch (Exception ex) {
			
			//reset all fields
			Main.FIELDS.clear();
			Main.LIST_MODEL.clear();			
			Main.SELECTED_INDEX = 0;
			Main.FIELDS.add(new DataField());
			Main.LIST_MODEL.add(Main.SELECTED_INDEX, "NEW");
			Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
			Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
			
			//output error message
			JOptionPane.showMessageDialog(this, "Error importing file!");
		}
	}
	
	private void case_copy() {
		//update object
		Main.SAVE_CHANGEABLE_FIELDS();
		
		//update list model
		Main.UPDATE_LIST_MODEL(Main.SELECTED_INDEX);
				
		//add new item
		int copyIndex = Main.SELECTED_INDEX;
		Main.FIELDS.add(new DataField(Main.FIELDS.get(copyIndex)));
		Main.SELECTED_INDEX = Main.FIELDS.size() - 1;
		
		//set initial status to touched and committed date to next business day
		LocalDate today = new LocalDate();
		int days = 1;
		while (days < 4) {
			if (Main.FIELDS.get(Main.SELECTED_INDEX).workingDaysBetween(today, today.plusDays(days)) == 1)
				break;
			days++;
		}
		Main.FIELDS.get(Main.SELECTED_INDEX).setCommittedDate(today.plusDays(days));
		Main.FIELDS.get(Main.SELECTED_INDEX).setStatus(Main.STATUS_IS_TOUCHED);
		
		//add new item to case list
		StringBuilder sb = new StringBuilder();
		
		sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest());
		
		if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty() && 
				(!Main.FIELDS.get(Main.SELECTED_INDEX).getPrimaryFirstName().isEmpty() || 
					!Main.FIELDS.get(Main.SELECTED_INDEX).getPrimaryLastName().isEmpty())) {
			sb.append(" / ");
		}
		
		sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getPrimaryFirstName());
		
		if (!Main.FIELDS.get(Main.SELECTED_INDEX).getPrimaryFirstName().isEmpty() &&
			!Main.FIELDS.get(Main.SELECTED_INDEX).getPrimaryLastName().isEmpty()) {
			sb.append(" ");
		}
		
		sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getPrimaryLastName());
		
		if (!sb.toString().isEmpty()) {
			Main.LIST_MODEL.addElement(sb.toString());
		} else {
			Main.LIST_MODEL.addElement("NEW");
		}
		
		Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
		Main.LIST.ensureIndexIsVisible(Main.SELECTED_INDEX);
		Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
	}
	
	void update_list_items() {
		
	}
}
