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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import au.com.bytecode.opencsv.CSVReader;

import com.thoughtworks.xstream.XStream;

/**
 * MenuFrame class
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class MenuFrame extends JFrame implements WindowListener {
	
	private static final long serialVersionUID = -8248541759702200986L;
	
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem export = new JMenuItem("Export");
	private JMenu imprt = new JMenu("Import");
	private JMenuItem recover = new JMenuItem("Recover");
	
	private JMenuItem importCSV = new JMenuItem("CSV");
	private JMenuItem importXML = new JMenuItem("XML");
	
	public MenuFrame() {
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		JMenu fileMenu = new JMenu("File");
		menu.add(fileMenu);
		
		fileMenu.add(save);
		KeyStroke ctrl_s = KeyStroke.getKeyStroke(
				KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
		save.setAccelerator(ctrl_s);
		
		fileMenu.add(export);
		KeyStroke ctrl_e = KeyStroke.getKeyStroke(
				KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
		export.setAccelerator(ctrl_e);
		
		fileMenu.add(imprt);
		
		imprt.add(importCSV);
		imprt.add(importXML);
		
		//user clicked save
		save.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if (e.getSource() == save) {
					
					//update object
					Main.SAVE_CHANGEABLE_FIELDS();
					
					//update list model
					StringBuilder sb = new StringBuilder();
					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty()) {
						sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest());
					}
					
					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty() &&
						!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
						sb.append(" / ");
					}

					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
						sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getName());
					}
					
					if (!sb.toString().isEmpty()) {
						Main.LIST_MODEL.set(Main.SELECTED_INDEX, sb.toString());
					}
					
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
		
		//user clicked recover
		recover.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//...
		JPanel mainPanel = new JPanel();
		mainPanel.setOpaque(true);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BorderLayout());
		JScrollPane jspInfo = new JScrollPane(new InfoPanel());
		jspInfo.getVerticalScrollBar().setUnitIncrement(16);
		mainPanel.add(new ListPanel(), BorderLayout.WEST);
		mainPanel.add(jspInfo, BorderLayout.CENTER);
		
		this.setSize(Main.F_WIDTH, Main.F_HEIGHT);
		this.setMinimumSize(new Dimension(Main.F_MIN_WIDTH, Main.F_MIN_HEIGHT));
		this.setTitle("BLT Tool"); //Backup Logging Tool... Tool!
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.setResizable(true);
		this.addWindowListener(this);
		this.setVisible(true);
	}

	@Override public void windowActivated(WindowEvent e) {
		
	}

	@Override public void windowClosed(WindowEvent e) {
		
	}

	@Override public void windowClosing(WindowEvent e) {
		//handle unsaved changes
		if (Main.HAS_UNSAVED_CHANGES) {
			
			int choice = JOptionPane.showConfirmDialog(this, 
					"Would you like to save your changes?", "Save", 
					JOptionPane.YES_NO_CANCEL_OPTION);
			
			//save changes and exit
			if (choice == JOptionPane.YES_OPTION) {
				//update FIELDS
				Main.SAVE_CHANGEABLE_FIELDS();
				
				//write to file
				try {
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
			
			//discard changes and exit
			else if (choice == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			
			//cancel exit
			else if (choice == JOptionPane.CANCEL_OPTION) {
				//do nothing
			}
		}
		
		//no unsaved changes --> exit
		else {
			System.exit(0);
		}
	}
	
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
				for (int i = 0; i < Main.FIELDS.size(); i++) {
					StringBuilder sb = new StringBuilder();
					sb.append(Main.FIELDS.get(i).getServiceRequest());
					if (!Main.FIELDS.get(i).getServiceRequest().isEmpty() &&
						!Main.FIELDS.get(i).getName().isEmpty()) {
						sb.append(" / ");
					}
					sb.append(Main.FIELDS.get(i).getName());
					if (!sb.toString().isEmpty()) {
						Main.LIST_MODEL.add(i, sb.toString());
					}
				}
				
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
			String[] nextLine = reader.readNext(); //skip first line
			while ((nextLine = reader.readNext()) != null) {
				
				//service request
				StringBuilder serviceRequest = new StringBuilder();
				if (nextLine.length > 3) {
					for (int i = 3; i < nextLine[3].length() - 2; i += 2) {
						serviceRequest.append(nextLine[3].charAt(i));
					}
				}
				
				//description
				StringBuilder description = new StringBuilder();
				if (nextLine.length > 4) {
					for (int i = 3; i < nextLine[4].length() - 2; i += 2) {
						description.append(nextLine[4].charAt(i));
					}
				}
				
				//service tag
				StringBuilder serviceTag = new StringBuilder();
				if (nextLine.length > 5) {
					for (int i = 3; i < nextLine[5].length() - 2; i += 2) {
						serviceTag.append(nextLine[5].charAt(i));
					}
				}
				
				//last name
				StringBuilder lastName = new StringBuilder();
				if (nextLine.length > 8) {
					for (int i = 3; i < nextLine[8].length() - 2; i += 2) {
						lastName.append(nextLine[8].charAt(i));
					}
				}
				
				//last name
				StringBuilder firstName = new StringBuilder();
				if (nextLine.length > 9) {
					for (int i = 3; i < nextLine[9].length() - 2; i += 2) {
						firstName.append(nextLine[9].charAt(i));
					}
				}
				
				//name
				String name = firstName.toString() + " " + lastName.toString();
				
				//phone number
				StringBuilder phone = new StringBuilder();
				if (nextLine.length > 10) {
					for (int i = 3; i < nextLine[10].length() - 2; i += 2) {
						phone.append(nextLine[10].charAt(i));
					}
				}
				
				//email
				StringBuilder email = new StringBuilder();
				if (nextLine.length > 11) {
					for (int i = 3; i < nextLine[11].length() - 2; i += 2) {
						email.append(nextLine[11].charAt(i));
					}
				}
				
				//committed date
				StringBuilder committedDateString = new StringBuilder();
				if (nextLine.length > 42) {
					for (int i = 3; i < nextLine[42].length() - 2; i+= 2) {
						committedDateString.append(nextLine[42].charAt(i));
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
				df.setName(name);
				df.setPhone(phone.toString());
				df.setEmail(email.toString());
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
						!df.getName().isEmpty()) {
						sb.append(" / ");
					}
					
					if (!df.getName().isEmpty()) {
						sb.append(df.getName());
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
	
}