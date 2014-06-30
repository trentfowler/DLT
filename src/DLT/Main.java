package DLT;
import java.awt.Color;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Delta Offline Logging Tool
 * 
 * @author Trent Fowler
 */

/**
 * Main class
 *
 * ...
 * 
 * @author Trent
 *
 */
public class Main {
	
	static MenuFrame f;
	
	static final int STATUS_IS_TOUCHED = 1;
	static final int STATUS_IS_CLOSED = 3;
	static final int STATUS_IS_UNKNOWN = 0;
	static final int STATUS_IS_DUE = 4;
	static final int STATUS_IS_OVERDUE = 5;
	static final int STATUS_IS_DEPOT = 6;
	
	//frame dimensions
	static int F_MIN_WIDTH = 300;
	static int F_MIN_HEIGHT = 300;
	
	static int F_WIDTH = 945;
	//static int F_HEIGHT = 597;
	static int F_HEIGHT = 618;
	
	static boolean HAS_UNSAVED_CHANGES = false;
	
	static ArrayList<DataField> FIELDS = new ArrayList<DataField>();
	static int SELECTED_INDEX = 0;
	
	static DataField AUTO_SAVE_FIELD = new DataField();
	
	//changeable fields
	static JComboBox<String> JCB_MONTH = new JComboBox<String>(new String[]{"January", 
			"February", "March", "April", "May", "June", "July", "August", 
			"September", "October", "November", "December"});
	
	static JComboBox<Integer> JCB_DAY = new JComboBox<Integer>(new Integer[]{1, 2, 3, 4, 5, 6,
			7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
			24, 25, 26, 27, 28, 29, 30, 31});
	static int START_YEAR = 2012;
	
	static JComboBox<Integer> JCB_YEAR = new JComboBox<Integer>(new Integer[]{2012, 2013, 2014, 
			2015, 2016, 2017, 2018, 2019, 2020});
	
	static JComboBox<String> JCB_EXPIRATION_MONTH = new JComboBox<String>(new String[]{"January", 
			"February", "March", "April", "May", "June", "July", "August", 
			"September", "October", "November", "December"});
	
	static JComboBox<Integer> JCB_EXPIRATION_DAY = new JComboBox<Integer>(new Integer[]{1, 2, 3, 4, 5, 6,
			7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
			24, 25, 26, 27, 28, 29, 30, 31});
	
	static JComboBox<Integer> JCB_EXPIRATION_YEAR = new JComboBox<Integer>(new Integer[]{2012, 2013, 2014, 
			2015, 2016, 2017, 2018, 2019, 2020});
	
	static JCheckBox JCHK_VA = new JCheckBox("VA");
	static JCheckBox JCHK_TOADE = new JCheckBox("TOADE");
	static JCheckBox JCHK_VDI = new JCheckBox("VDI");
	static JCheckBox JCHK_EMAIL_CAP = new JCheckBox("EMAIL CAP");
	static JCheckBox JCHK_TARP = new JCheckBox("TARP");
	static JCheckBox JCHK_POS = new JCheckBox("POS");
	static JCheckBox JCHK_PAL = new JCheckBox("PAL");
	static JCheckBox JCHK_PLASTICS = new JCheckBox("CHK PLASTICS");
	static JCheckBox JCHK_CIDAR = new JCheckBox("CIDAR");
	static JTextField JTF_COMPANY = new JTextField();
	static JTextField JTF_NAME = new JTextField();
	static JTextField JTF_PHONE = new JTextField();
	static JTextField JTF_ALT_PHONE = new JTextField();
	static JTextField JTF_ADDRESS = new JTextField();
	static JTextField JTF_CITY_STATE_ZIP = new JTextField();
	static JTextField JTF_EMAIL = new JTextField();
	static JTextField JTF_ALT_NAME = new JTextField();
	static JTextField JTF_ALT_PRIMARY_PHONE = new JTextField();
	static JTextField JTF_ALT_SECONDARY_PHONE = new JTextField();
	static JTextField JTF_ALT_ADDRESS = new JTextField();
	static JTextField JTF_ALT_CITY_STATE_ZIP = new JTextField();
	static JTextField JTF_ALT_EMAIL = new JTextField();
	static JTextField JTF_SERVICE_TAG = new JTextField();
	static JTextField JTF_SERVICE_REQUEST = new JTextField();
	static JTextField JTF_ORDER_NUMBER = new JTextField();
	static JTextField JTF_WARRANTY_TYPE = new JTextField();
	static JTextField JTF_MODEL = new JTextField();
	static JTextField JTF_FORM_FACTOR = new JTextField();
	static JTextField JTF_OS = new JTextField();
	static JTextArea JTA_SYMPTOMS = new JTextArea();
	static JTextArea JTA_TROUBLESHOOTING = new JTextArea();
	static JTextArea JTA_CONCLUSION = new JTextArea();
	static JTextArea JTA_NOTES = new JTextArea();
	static JTextField JTF_DESCRIPTION = new JTextField();
	
	static JButton JB_REMOVE = new JButton("Remove");
	
	static DefaultListModel LIST_MODEL = new DefaultListModel();
	static JList LIST = new JList(Main.LIST_MODEL);
	
	//saves content of the view to the FIELDS object
	static void SAVE_CHANGEABLE_FIELDS() {
		Main.FIELDS.get(Main.SELECTED_INDEX).setMonth(Main.JCB_MONTH.getSelectedIndex());
		Main.FIELDS.get(Main.SELECTED_INDEX).setDay(Main.JCB_DAY.getSelectedIndex());
		Main.FIELDS.get(Main.SELECTED_INDEX).setYear(Main.JCB_YEAR.getSelectedIndex());
		Main.FIELDS.get(Main.SELECTED_INDEX).setExpirationMonth(Main.JCB_EXPIRATION_MONTH.getSelectedIndex());
		Main.FIELDS.get(Main.SELECTED_INDEX).setExpirationDay(Main.JCB_EXPIRATION_DAY.getSelectedIndex());
		Main.FIELDS.get(Main.SELECTED_INDEX).setExpirationYear(Main.JCB_EXPIRATION_YEAR.getSelectedIndex());
		Main.FIELDS.get(Main.SELECTED_INDEX).setVAIsChecked(Main.JCHK_VA.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setTOADEIsChecked(Main.JCHK_TOADE.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setVDIIsChecked(Main.JCHK_VDI.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setEmailCapIsChecked(Main.JCHK_EMAIL_CAP.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setTARPIsChecked(Main.JCHK_TARP.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPOSIsChecked(Main.JCHK_POS.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPALIsChecked(Main.JCHK_PAL.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPlasticsIsChecked(Main.JCHK_PLASTICS.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setCIDARIsChecked(Main.JCHK_CIDAR.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setCompany(Main.JTF_COMPANY.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setName(Main.JTF_NAME.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setEmail(Main.JTF_EMAIL.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPhone(Main.JTF_PHONE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltPhone(Main.JTF_ALT_PHONE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAddress(Main.JTF_ADDRESS.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setCityStateZip(Main.JTF_CITY_STATE_ZIP.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltName(Main.JTF_ALT_NAME.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltEmail(Main.JTF_ALT_EMAIL.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltPrimaryPhone(Main.JTF_ALT_PRIMARY_PHONE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltSecondaryPhone(Main.JTF_ALT_SECONDARY_PHONE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltAddress(Main.JTF_ALT_ADDRESS.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltCityStateZip(Main.JTF_ALT_CITY_STATE_ZIP.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setServiceTag(Main.JTF_SERVICE_TAG.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setServiceRequest(Main.JTF_SERVICE_REQUEST.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setOrderNumber(Main.JTF_ORDER_NUMBER.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setWarrantyType(Main.JTF_WARRANTY_TYPE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setModel(Main.JTF_MODEL.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setFormFactor(Main.JTF_FORM_FACTOR.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setOS(Main.JTF_OS.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setSymptoms(Main.JTA_SYMPTOMS.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setTroubleshooting(Main.JTA_TROUBLESHOOTING.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setConclusion(Main.JTA_CONCLUSION.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setNotes(Main.JTA_NOTES.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setDescription(Main.JTF_DESCRIPTION.getText());
	}
	
	//sets the view with the data from the DataField element at a specified index in FIELDS
	static void SET_CHANGEABLE_FIELDS(int index) {
		Main.JCB_MONTH.setSelectedIndex(Main.FIELDS.get(index).getMonth());
		Main.JCB_DAY.setSelectedIndex(Main.FIELDS.get(index).getDay());
		Main.JCB_YEAR.setSelectedIndex(Main.FIELDS.get(index).getYear());
		Main.JCB_EXPIRATION_MONTH.setSelectedIndex(Main.FIELDS.get(index).getExpirationMonth());
		Main.JCB_EXPIRATION_DAY.setSelectedIndex(Main.FIELDS.get(index).getExpirationDay());
		Main.JCB_EXPIRATION_YEAR.setSelectedIndex(Main.FIELDS.get(index).getExpirationYear());
		Main.JCHK_VA.setSelected(Main.FIELDS.get(index).getVAIsChecked());
		Main.JCHK_TOADE.setSelected(Main.FIELDS.get(index).getTOADEIsChecked());
		Main.JCHK_VDI.setSelected(Main.FIELDS.get(index).getVDIIsChecked());
		Main.JCHK_EMAIL_CAP.setSelected(Main.FIELDS.get(index).getEmailCapIsChecked());
		Main.JCHK_TARP.setSelected(Main.FIELDS.get(index).getTARPIsChecked());
		Main.JCHK_POS.setSelected(Main.FIELDS.get(index).getPOSIsChecked());
		Main.JCHK_PAL.setSelected(Main.FIELDS.get(index).getPALIsChecked());
		Main.JCHK_PLASTICS.setSelected(Main.FIELDS.get(index).getPlasticsIsChecked());
		Main.JCHK_CIDAR.setSelected(Main.FIELDS.get(index).getCIDARIsChecked());
		Main.JTF_COMPANY.setText(Main.FIELDS.get(index).getCompany());
		Main.JTF_NAME.setText(Main.FIELDS.get(index).getName());
		Main.JTF_EMAIL.setText(Main.FIELDS.get(index).getEmail());
		Main.JTF_PHONE.setText(Main.FIELDS.get(index).getPhone());
		Main.JTF_ALT_PHONE.setText(Main.FIELDS.get(index).getAltPhone());
		Main.JTF_ADDRESS.setText(Main.FIELDS.get(index).getAddress());
		Main.JTF_CITY_STATE_ZIP.setText(Main.FIELDS.get(index).getCityStateZip());
		Main.JTF_ALT_NAME.setText(Main.FIELDS.get(index).getAltName());
		Main.JTF_ALT_EMAIL.setText(Main.FIELDS.get(index).getAltEmail());
		Main.JTF_ALT_PRIMARY_PHONE.setText(Main.FIELDS.get(index).getAltPrimaryPhone());
		Main.JTF_ALT_SECONDARY_PHONE.setText(Main.FIELDS.get(index).getAltSecondaryPhone());
		Main.JTF_ALT_ADDRESS.setText(Main.FIELDS.get(index).getAltAddress());
		Main.JTF_ALT_CITY_STATE_ZIP.setText(Main.FIELDS.get(index).getAltCityStateZip());
		Main.JTF_SERVICE_TAG.setText(Main.FIELDS.get(index).getServiceTag());		
		Main.JTF_SERVICE_REQUEST.setText(Main.FIELDS.get(index).getServiceRequest());
		Main.JTF_ORDER_NUMBER.setText(Main.FIELDS.get(index).getOrderNumber());
		Main.JTF_WARRANTY_TYPE.setText(Main.FIELDS.get(index).getWarrantyType());
		Main.JTF_MODEL.setText(Main.FIELDS.get(index).getModel());
		Main.JTF_FORM_FACTOR.setText(Main.FIELDS.get(index).getFormFactor());
		Main.JTF_OS.setText(Main.FIELDS.get(index).getOS());		
		Main.JTA_SYMPTOMS.setText(Main.FIELDS.get(index).getSymptoms());
		Main.JTA_TROUBLESHOOTING.setText(Main.FIELDS.get(index).getTroubleshooting());
		Main.JTA_CONCLUSION.setText(Main.FIELDS.get(index).getConclusion());
		Main.JTA_NOTES.setText(Main.FIELDS.get(index).getNotes());
		
		Main.JTF_DESCRIPTION.setText(Main.FIELDS.get(index).getDescription());
		
		//remove button
		/*
		if (Main.FIELDS.size() <= 1) {
			Main.JB_REMOVE.setEnabled(false);
		} else Main.JB_REMOVE.setEnabled(true);
		*/
		
		//check boxes enabled
		if (Main.FIELDS.get(index).getPOSIsChecked()) {
			Main.JCHK_PAL.setEnabled(false);
		} else Main.JCHK_PAL.setEnabled(true);
		
		if (Main.FIELDS.get(index).getPALIsChecked()) {
			Main.JCHK_POS.setEnabled(false);
		} else Main.JCHK_POS.setEnabled(true);
		
		//set colors
		if (Main.JTF_COMPANY.getText().length() < 3) Main.JTF_COMPANY.setBackground(new Color(255, 81, 81));
		else Main.JTF_COMPANY.setBackground(new Color(106, 255, 84));
		
		if (Main.JTF_NAME.getText().length() < 3) Main.JTF_NAME.setBackground(new Color(255, 81, 81));
		else Main.JTF_NAME.setBackground(new Color(106, 255, 84));
		
		if (Main.JTF_EMAIL.getText().length() < 6) Main.JTF_EMAIL.setBackground(new Color(255, 81, 81));
		else Main.JTF_EMAIL.setBackground(new Color(106, 255, 84));
		
		//phone
		if (Main.JTF_PHONE.getText().length() < 10) {
			Main.JTF_PHONE.setBackground(new Color(255, 81, 81));
		} 
		else {
			int count = 0;
			for (int i = 0; i < Main.JTF_PHONE.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_PHONE.getText().charAt(i))) {
					count++;
				}
			}
			if (count >= 10) {
				Main.JTF_PHONE.setBackground(new Color(106, 255, 84));	
			}
		}
		
		//service tag
		if (Main.JTF_SERVICE_TAG.getText().length() < 7) {
			Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		} else {
			int count = 0;
			for (int i = 0; i < Main.JTF_SERVICE_TAG.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_SERVICE_TAG.getText().charAt(i)) ||
					Character.isLetter(Main.JTF_SERVICE_TAG.getText().charAt(i))) {
					count++;
				}
			}
			if (count % 7 == 0) {
				Main.JTF_SERVICE_TAG.setBackground(new Color(106, 255, 84)); //green
			} else Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		}
		
	}
	
	//contains the main method
	public Main() {
				
		//set margins for text fields/areas
		Insets i = new Insets(1, 1, 1, 1);
		Main.JTF_NAME.setMargin(i);
		Main.JTF_COMPANY.setMargin(i);
		Main.JTF_PHONE.setMargin(i);
		Main.JTF_ALT_PHONE.setMargin(i);
		Main.JTF_ADDRESS.setMargin(i);
		Main.JTF_CITY_STATE_ZIP.setMargin(i);
		Main.JTF_EMAIL.setMargin(i);
		Main.JTF_ALT_NAME.setMargin(i);
		Main.JTF_ALT_PRIMARY_PHONE.setMargin(i);
		Main.JTF_ALT_SECONDARY_PHONE.setMargin(i);
		Main.JTF_ALT_ADDRESS.setMargin(i);
		Main.JTF_ALT_CITY_STATE_ZIP.setMargin(i);
		Main.JTF_ALT_EMAIL.setMargin(i);
		Main.JTF_SERVICE_TAG.setMargin(i);
		Main.JTF_SERVICE_REQUEST.setMargin(i);
		Main.JTF_ORDER_NUMBER.setMargin(i);
		Main.JTF_WARRANTY_TYPE.setMargin(i);
		Main.JTF_MODEL.setMargin(i);
		Main.JTF_FORM_FACTOR.setMargin(i);
		Main.JTF_OS.setMargin(i);
		Main.JTA_SYMPTOMS.setMargin(i);
		Main.JTA_TROUBLESHOOTING.setMargin(i);
		Main.JTA_CONCLUSION.setMargin(i);
		Main.JTA_NOTES.setMargin(i);
		Main.JTF_DESCRIPTION.setMargin(i);		
		
		//add objects from "Data.ser" and populate JList with those objects
		try {
			FileInputStream fileStream = new FileInputStream("Data.ser");
			ObjectInputStream os = new ObjectInputStream(fileStream);
			while (true) {
				Main.FIELDS.add((DataField) os.readObject());
				
				Main.FIELDS.get(Main.SELECTED_INDEX).initializeStatus();
				
				//add FIELDS item to LIST_MODEL
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
				
				if (sb.toString().isEmpty())
					Main.LIST_MODEL.add(Main.SELECTED_INDEX, "NEW");
				else 
					Main.LIST_MODEL.add(Main.SELECTED_INDEX, sb.toString());
				
				Main.SELECTED_INDEX += 1;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) { //everything else
			e.printStackTrace();
		}
				
		//if no objects exist already, create one
		if (Main.FIELDS.size() == 0) {
			Main.FIELDS.add(new DataField());
			Main.LIST_MODEL.addElement("NEW");
			Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
			Main.SET_CHANGEABLE_FIELDS(0);
		}
		
		//if at least one object exists, set the initial selection to the first item
		else {
			Main.SELECTED_INDEX = 0;
			Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
			Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
		}
		Main.LIST.setCellRenderer(new CustomListCellRenderer());

		//initialize the frame
		//MenuFrame f = new MenuFrame();
		f = new MenuFrame();
	}
	
	//main method
	public static void main(String[] args) {
		new Main();
	}
}