package DLT;
import java.awt.Color;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.joda.time.LocalDate;

/**
 * Backup Logging Tool. For offline logging and case management.
 * 
 * @version 2.3
 * @author Trent Fowler
 * @author Bryan W.W.
 */

/**
 * Main class
 *
 * Defines and initializes the global variables. The constructor for this 
 * class contains the control flow for the program. This class also 
 * contains the main method which initializes the program.
 * 
 * @author Trent
 * @author Bryan
 *
 */
public class Main {
	
	static MenuFrame f;
	
	static final int STATUS_IS_UNKNOWN = 0;
	static final int STATUS_IS_TOUCHED = 1;
	static final int STATUS_IS_CLOSED = 3;
	static final int STATUS_IS_DUE = 4;
	static final int STATUS_IS_OVERDUE = 5;
	
	//TODO implement cdo priorities
	
	//config.txt variables
	static int BRANCH = 1;
	static String OWNER = "";
	
	//frame dimensions
	static int F_MIN_WIDTH = 300;
	static int F_MIN_HEIGHT = 300;
	static int F_WIDTH = 1075;
	static int F_HEIGHT = 618;
	
	//...
	static boolean HAS_UNSAVED_CHANGES = false;
	static ArrayList<DataField> FIELDS = new ArrayList<DataField>();
	static int SELECTED_INDEX = 0;
	static DataField AUTO_SAVE_FIELD = new DataField(); //TODO legacy variable
	
	//changeable fields
	
	//TODO remove legacy code
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
	//TODO end legacy code
	
	static JCheckBox JCHK_VA = new JCheckBox("VA");
	static JCheckBox JCHK_TOADE = new JCheckBox("TOADE");
	static JCheckBox JCHK_VDI = new JCheckBox("VDI");
	static JCheckBox JCHK_EMAIL_CAP = new JCheckBox("EMAIL CAP");
	static JCheckBox JCHK_TARP = new JCheckBox("TARP");
	static JCheckBox JCHK_POS = new JCheckBox("POS");
	static JCheckBox JCHK_PAL = new JCheckBox("OST DPS");
	//TODO implement JCHK_DEPOT
	//TODO implement JCHK_TATT
	static JCheckBox JCHK_PLASTICS = new JCheckBox("CHK PLASTICS");
	static JCheckBox JCHK_CIDAR = new JCheckBox("CIDAR");
	static JCheckBox JCHK_NOAC = new JCheckBox("NOAC");
	static JTextField JTF_COMPANY = new JTextField();
	static JTextField PRIMARY_FIRST_NAME = new JTextField();
	static JTextField PRIMARY_LAST_NAME = new JTextField();
	static JTextField PRIMARY_AREA_CODE = new JTextField();
	static JTextField PRIMARY_PHONE_NUMBER = new JTextField();
	static JTextField PRIMARY_EXT = new JTextField();
	static JTextField PRIMARY_EMAIL = new JTextField();
	static JTextField PRIMARY_ALT_AREA_CODE = new JTextField();
	static JTextField PRIMARY_ALT_PHONE_NUMBER = new JTextField();
	static JTextField PRIMARY_ALT_EXT = new JTextField();
	static JTextField PRIMARY_ADDRESS = new JTextField();
	static JTextField PRIMARY_ADDRESS_L2 = new JTextField();
	static JTextField PRIMARY_CITY = new JTextField();
	static JTextField PRIMARY_ZIP = new JTextField();
	static JTextField ALT_FIRST_NAME = new JTextField();
	static JTextField ALT_LAST_NAME = new JTextField();
	static JTextField ALT_AREA_CODE = new JTextField();
	static JTextField ALT_PHONE_NUMBER = new JTextField();
	static JTextField ALT_EXT = new JTextField();
	static JTextField ALT_EMAIL = new JTextField();
	static JTextField ALT_ALT_AREA_CODE = new JTextField();
	static JTextField ALT_ALT_PHONE_NUMBER = new JTextField();
	static JTextField ALT_ALT_EXT = new JTextField();
	static JTextField ALT_ADDRESS = new JTextField();
	static JTextField ALT_ADDRESS_L2 = new JTextField();
	static JTextField ALT_CITY = new JTextField();
	static JTextField ALT_ZIP = new JTextField();
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
	static JTextField JTF_SEARCH_FIELD = new JTextField();
	static JLabel JL_PRIMARY_NAME = new JLabel("Name");
	static JLabel JL_PRIMARY_PHONE = new JLabel("Phone");
	static JLabel JL_PRIMARY_EMAIL = new JLabel("E-mail");
	static JLabel JL_PRIMARY_ALT_PHONE = new JLabel("Alt. Phone");
	static JLabel JL_ALT_NAME = new JLabel("Name");
	static JLabel JL_ALT_PHONE = new JLabel("Phone");
	static JLabel JL_ALT_EMAIL = new JLabel("E-mail");
	static JLabel JL_ALT_ALT_PHONE = new JLabel("Alt. Phone");
	static JLabel JL_ADDRESS = new JLabel("Address");
	static JLabel JL_ADDRESS_L2 = new JLabel("Apt/Ste/Flr");
	static JLabel JL_CITY = new JLabel("City");
	static JLabel JL_ZIP = new JLabel("Postal Code");
	static JButton JB_SERVICE_TAG = new JButton("Service Tag");
	static JButton JB_SERVICE_REQUEST = new JButton("Service Request");
	
	static void UPDATE_LIST_MODEL() {
		for (int i = 0; i < Main.FIELDS.size(); i++) {
			StringBuilder sb = new StringBuilder();
			
			sb.append(Main.FIELDS.get(i).getServiceRequest());
			
			if (!Main.FIELDS.get(i).getServiceRequest().isEmpty() && 
					(!Main.FIELDS.get(i).getPrimaryFirstName().isEmpty() || 
						!Main.FIELDS.get(i).getPrimaryLastName().isEmpty())) {
				sb.append(" / ");
			}
							
			sb.append(Main.FIELDS.get(i).getPrimaryFirstName());
			
			if (!Main.FIELDS.get(i).getPrimaryFirstName().isEmpty() &&
				!Main.FIELDS.get(i).getPrimaryLastName().isEmpty()) {
				sb.append(" ");
			}
			
			sb.append(Main.FIELDS.get(i).getPrimaryLastName());
			
			if (!sb.toString().isEmpty()) {
				Main.LIST_MODEL.set(i, sb.toString());
			} else Main.LIST_MODEL.set(i, "NEW");
		}
	}
	
	static void UPDATE_LIST_MODEL(int index) {
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
			Main.LIST_MODEL.set(index, sb.toString());
		} else Main.LIST_MODEL.set(index, "NEW");
	}
	
	//saves content of the view to the FIELDS object
	static void SAVE_CHANGEABLE_FIELDS() {
		Main.FIELDS.get(Main.SELECTED_INDEX).setVAIsChecked(Main.JCHK_VA.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setTOADEIsChecked(Main.JCHK_TOADE.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setVDIIsChecked(Main.JCHK_VDI.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setEmailCapIsChecked(Main.JCHK_EMAIL_CAP.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setTARPIsChecked(Main.JCHK_TARP.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPOSIsChecked(Main.JCHK_POS.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPALIsChecked(Main.JCHK_PAL.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPlasticsIsChecked(Main.JCHK_PLASTICS.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setCIDARIsChecked(Main.JCHK_CIDAR.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setNOACIsChecked(Main.JCHK_NOAC.isSelected());
		Main.FIELDS.get(Main.SELECTED_INDEX).setCompany(Main.JTF_COMPANY.getText());
		
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryFirstName(Main.PRIMARY_FIRST_NAME.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryLastName(Main.PRIMARY_LAST_NAME.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryAreaCode(Main.PRIMARY_AREA_CODE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryPhoneNumber(Main.PRIMARY_PHONE_NUMBER.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryExt(Main.PRIMARY_EXT.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryEmail(Main.PRIMARY_EMAIL.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryAltAreaCode(Main.PRIMARY_ALT_AREA_CODE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryAltPhoneNumber(Main.PRIMARY_ALT_PHONE_NUMBER.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryAltExt(Main.PRIMARY_ALT_EXT.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryAddress(Main.PRIMARY_ADDRESS.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryAddressL2(Main.PRIMARY_ADDRESS_L2.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryCity(Main.PRIMARY_CITY.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setPrimaryZip(Main.PRIMARY_ZIP.getText());
		
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltFirstName(Main.ALT_FIRST_NAME.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltLastName(Main.ALT_LAST_NAME.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltAreaCode(Main.ALT_AREA_CODE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltPhoneNumber(Main.ALT_PHONE_NUMBER.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltExt(Main.ALT_EXT.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltEmail(Main.ALT_EMAIL.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltAltAreaCode(Main.ALT_ALT_AREA_CODE.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltAltPhoneNumber(Main.ALT_ALT_PHONE_NUMBER.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltAltExt(Main.ALT_ALT_EXT.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltAddress(Main.ALT_ADDRESS.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltAddressL2(Main.ALT_ADDRESS_L2.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltCity(Main.ALT_CITY.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setAltZip(Main.ALT_ZIP.getText());
		
		Main.FIELDS.get(Main.SELECTED_INDEX).setServiceTag(Main.JTF_SERVICE_TAG.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setServiceRequest(Main.JTF_SERVICE_REQUEST.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setSymptoms(Main.JTA_SYMPTOMS.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setTroubleshooting(Main.JTA_TROUBLESHOOTING.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setConclusion(Main.JTA_CONCLUSION.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setNotes(Main.JTA_NOTES.getText());
		Main.FIELDS.get(Main.SELECTED_INDEX).setDescription(Main.JTF_DESCRIPTION.getText());
	}
	
	//sets the view with the data from the DataField element for the selected case
	static void SET_CHANGEABLE_FIELDS(int index) {
		Main.JCHK_VA.setSelected(Main.FIELDS.get(index).getVAIsChecked());
		Main.JCHK_TOADE.setSelected(Main.FIELDS.get(index).getTOADEIsChecked());
		Main.JCHK_VDI.setSelected(Main.FIELDS.get(index).getVDIIsChecked());
		Main.JCHK_EMAIL_CAP.setSelected(Main.FIELDS.get(index).getEmailCapIsChecked());
		Main.JCHK_TARP.setSelected(Main.FIELDS.get(index).getTARPIsChecked());
		Main.JCHK_POS.setSelected(Main.FIELDS.get(index).getPOSIsChecked());
		Main.JCHK_PAL.setSelected(Main.FIELDS.get(index).getPALIsChecked());
		Main.JCHK_PLASTICS.setSelected(Main.FIELDS.get(index).getPlasticsIsChecked());
		Main.JCHK_CIDAR.setSelected(Main.FIELDS.get(index).getCIDARIsChecked());
		Main.JCHK_NOAC.setSelected(Main.FIELDS.get(index).getNOACIsChecked());
		Main.JTF_COMPANY.setText(Main.FIELDS.get(index).getCompany());
		
		Main.PRIMARY_FIRST_NAME.setText(Main.FIELDS.get(index).getPrimaryFirstName());
		Main.PRIMARY_LAST_NAME.setText(Main.FIELDS.get(index).getPrimaryLastName());
		Main.PRIMARY_AREA_CODE.setText(Main.FIELDS.get(index).getPrimaryAreaCode());
		Main.PRIMARY_PHONE_NUMBER.setText(Main.FIELDS.get(index).getPrimaryPhoneNumber());
		Main.PRIMARY_EXT.setText(Main.FIELDS.get(index).getPrimaryExt());
		Main.PRIMARY_EMAIL.setText(Main.FIELDS.get(index).getPrimaryEmail());
		Main.PRIMARY_ALT_AREA_CODE.setText(Main.FIELDS.get(index).getPrimaryAltAreaCode());
		Main.PRIMARY_ALT_PHONE_NUMBER.setText(Main.FIELDS.get(index).getPrimaryAltPhoneNumber());
		Main.PRIMARY_ALT_EXT.setText(Main.FIELDS.get(index).getPrimaryAltExt());
		Main.PRIMARY_ADDRESS.setText(Main.FIELDS.get(index).getPrimaryAddress());
		Main.PRIMARY_ADDRESS_L2.setText(Main.FIELDS.get(index).getPrimaryAddressL2());
		Main.PRIMARY_CITY.setText(Main.FIELDS.get(index).getPrimaryCity());
		Main.PRIMARY_ZIP.setText(Main.FIELDS.get(index).getPrimaryZip());
		
		Main.ALT_FIRST_NAME.setText(Main.FIELDS.get(index).getAltFirstName());
		Main.ALT_LAST_NAME.setText(Main.FIELDS.get(index).getAltLastName());
		Main.ALT_AREA_CODE.setText(Main.FIELDS.get(index).getAltAreaCode());
		Main.ALT_PHONE_NUMBER.setText(Main.FIELDS.get(index).getAltPhoneNumber());
		Main.ALT_EXT.setText(Main.FIELDS.get(index).getAltExt());
		Main.ALT_EMAIL.setText(Main.FIELDS.get(index).getAltEmail());
		Main.ALT_ALT_AREA_CODE.setText(Main.FIELDS.get(index).getAltAltAreaCode());
		Main.ALT_ALT_PHONE_NUMBER.setText(Main.FIELDS.get(index).getAltAltPhoneNumber());
		Main.ALT_ALT_EXT.setText(Main.FIELDS.get(index).getAltAltExt());
		Main.ALT_ADDRESS.setText(Main.FIELDS.get(index).getAltAddress());
		Main.ALT_ADDRESS_L2.setText(Main.FIELDS.get(index).getAltAddressL2());
		Main.ALT_CITY.setText(Main.FIELDS.get(index).getAltCity());
		Main.ALT_ZIP.setText(Main.FIELDS.get(index).getAltZip());
		
		Main.JTF_SERVICE_TAG.setText(Main.FIELDS.get(index).getServiceTag());		
		Main.JTF_SERVICE_REQUEST.setText(Main.FIELDS.get(index).getServiceRequest());
		Main.JTA_SYMPTOMS.setText(Main.FIELDS.get(index).getSymptoms());
		Main.JTA_TROUBLESHOOTING.setText(Main.FIELDS.get(index).getTroubleshooting());
		Main.JTA_CONCLUSION.setText(Main.FIELDS.get(index).getConclusion());
		Main.JTA_NOTES.setText(Main.FIELDS.get(index).getNotes());
		Main.JTF_DESCRIPTION.setText(Main.FIELDS.get(index).getDescription());
		
		//check boxes enabled
		if (Main.FIELDS.get(index).getPOSIsChecked()) {
			Main.JCHK_PAL.setEnabled(false);
		} else {
			Main.JCHK_PAL.setEnabled(true);
		}
		
		if (Main.FIELDS.get(index).getPALIsChecked()) {
			Main.JCHK_POS.setEnabled(false);
			Main.JCHK_NOAC.setEnabled(true);
		} else {
			Main.JCHK_POS.setEnabled(true);
			Main.JCHK_NOAC.setEnabled(false);
		}
				
		//set address fields enabled/disabled
		if (!Main.JCHK_POS.isSelected() &&
			!Main.JCHK_PAL.isSelected()) {
			Main.PRIMARY_ADDRESS.setEnabled(false);
			Main.PRIMARY_ADDRESS_L2.setEnabled(false);
			Main.PRIMARY_CITY.setEnabled(false);
			Main.PRIMARY_ZIP.setEnabled(false);
			Main.JL_ADDRESS.setEnabled(false);
			Main.JL_ADDRESS_L2.setEnabled(false);
			Main.JL_CITY.setEnabled(false);
			Main.JL_ZIP.setEnabled(false);
		} else {
			Main.PRIMARY_ADDRESS.setEnabled(true);
			Main.PRIMARY_ADDRESS_L2.setEnabled(true);
			Main.PRIMARY_CITY.setEnabled(true);
			Main.PRIMARY_ZIP.setEnabled(true);
			Main.JL_ADDRESS.setEnabled(true);
			Main.JL_ADDRESS_L2.setEnabled(true);
			Main.JL_CITY.setEnabled(true);
			Main.JL_ZIP.setEnabled(true);
		}
		
		//alt contact
		if (!Main.JCHK_PAL.isSelected()) {
			Main.ALT_FIRST_NAME.setEnabled(false);
			Main.ALT_LAST_NAME.setEnabled(false);
			Main.ALT_AREA_CODE.setEnabled(false);
			Main.ALT_PHONE_NUMBER.setEnabled(false);
			Main.ALT_EXT.setEnabled(false);
			Main.ALT_EMAIL.setEnabled(false);
			Main.ALT_ALT_AREA_CODE.setEnabled(false);
			Main.ALT_ALT_PHONE_NUMBER.setEnabled(false);
			Main.ALT_ALT_EXT.setEnabled(false);
			Main.JL_ALT_NAME.setEnabled(false);
			Main.JL_ALT_PHONE.setEnabled(false);
			Main.JL_ALT_EMAIL.setEnabled(false);
			Main.JL_ALT_ALT_PHONE.setEnabled(false);
			
		} else {
			Main.ALT_FIRST_NAME.setEnabled(true);
			Main.ALT_LAST_NAME.setEnabled(true);
			Main.ALT_AREA_CODE.setEnabled(true);
			Main.ALT_PHONE_NUMBER.setEnabled(true);
			Main.ALT_EXT.setEnabled(true);
			Main.ALT_EMAIL.setEnabled(true);
			Main.ALT_ALT_AREA_CODE.setEnabled(true);
			Main.ALT_ALT_PHONE_NUMBER.setEnabled(true);
			Main.ALT_ALT_EXT.setEnabled(true);
			Main.JL_ALT_NAME.setEnabled(true);
			Main.JL_ALT_PHONE.setEnabled(true);
			Main.JL_ALT_EMAIL.setEnabled(true);
			Main.JL_ALT_ALT_PHONE.setEnabled(true);
		}
		
		if (Main.JCHK_NOAC.isSelected()) {
			Main.ALT_FIRST_NAME.setEnabled(false);
			Main.ALT_LAST_NAME.setEnabled(false);
			Main.ALT_AREA_CODE.setEnabled(false);
			Main.ALT_PHONE_NUMBER.setEnabled(false);
			Main.ALT_EXT.setEnabled(false);
			Main.ALT_EMAIL.setEnabled(false);
			Main.ALT_ALT_AREA_CODE.setEnabled(false);
			Main.ALT_ALT_PHONE_NUMBER.setEnabled(false);
			Main.ALT_ALT_EXT.setEnabled(false);
			Main.JL_ALT_NAME.setEnabled(false);
			Main.JL_ALT_PHONE.setEnabled(false);
			Main.JL_ALT_EMAIL.setEnabled(false);
			Main.JL_ALT_ALT_PHONE.setEnabled(false);
		}
		
		//button enabled/disabled state
		if (Main.JTF_SERVICE_TAG.getText().length() == 0) {
			Main.JB_SERVICE_TAG.setEnabled(false);
		} else {
			Main.JB_SERVICE_TAG.setEnabled(true);
		}
		
		if (Main.JTF_SERVICE_REQUEST.getText().length() == 0) {
			Main.JB_SERVICE_REQUEST.setEnabled(false);
		} else {
			Main.JB_SERVICE_REQUEST.setEnabled(true);
		}
		
		//set colors
		
		// company, not currently implemented in gui
		if (Main.JTF_COMPANY.getText().length() < 3) {
			Main.JTF_COMPANY.setBackground(new Color(255, 181, 181)); //red
		} 
		else Main.JTF_COMPANY.setBackground(new Color(161, 255, 161)); //green
		
		// name
		if (Main.PRIMARY_FIRST_NAME.getText().length() < 3) {
			Main.PRIMARY_FIRST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else Main.PRIMARY_FIRST_NAME.setBackground(new Color(161, 255, 161)); //green
		
		if (Main.PRIMARY_LAST_NAME.getText().length() < 3) {
			Main.PRIMARY_LAST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else Main.PRIMARY_LAST_NAME.setBackground(new Color(161, 255, 161)); //green
		
		// e-mail
		if (Main.PRIMARY_EMAIL.getText().length() < 6) {
			Main.PRIMARY_EMAIL.setBackground(new Color(255, 181, 181)); //red
		}
		else Main.PRIMARY_EMAIL.setBackground(new Color(161, 255, 161)); //green
		
		// phone -- area code
		if (Main.PRIMARY_AREA_CODE.getText().length() != 3) {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(161, 255, 161)); //green
		}
		
		// phone -- phone number
		int digits = 0;
		for (int i = 0; i < Main.PRIMARY_PHONE_NUMBER.getText().length(); i++) {
			if (Character.isDigit(Main.PRIMARY_PHONE_NUMBER.getText().charAt(i))) {
				digits++;
			}
		}
		if (digits == 7) {
			Main.PRIMARY_PHONE_NUMBER.setBackground(new Color(161, 255, 161)); //green
		} 
		else {
			Main.PRIMARY_PHONE_NUMBER.setBackground(new Color(255, 181, 181)); //red
		}
		
		// service tag
		if (Main.JTF_SERVICE_TAG.getText().length() < 7) {
			Main.JTF_SERVICE_TAG.setBackground(new Color(255, 181, 181)); //red
		} else {
			int count = 0;
			for (int i = 0; i < Main.JTF_SERVICE_TAG.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_SERVICE_TAG.getText().charAt(i)) ||
					Character.isLetter(Main.JTF_SERVICE_TAG.getText().charAt(i))) {
					count++;
				}
			}
			if (count % 7 == 0) {
				Main.JTF_SERVICE_TAG.setBackground(new Color(161, 255, 161)); //green
			} else Main.JTF_SERVICE_TAG.setBackground(new Color(255, 181, 181)); //red
		}
		
	}
	
	/**
	 * TODO ... work in progress, for disabling all clickable items so that 
	 * 			it's possible to have no items in the case list on the left
	 */
	static void DISABLE_ALL() {
		Main.JCHK_VA.setEnabled(false);
		Main.JCHK_TOADE.setEnabled(false);
		Main.JCHK_EMAIL_CAP.setEnabled(false);
		Main.JCHK_VDI.setEnabled(false);
		Main.JCHK_TARP.setEnabled(false);
		Main.JCHK_POS.setEnabled(false);
		Main.JCHK_PAL.setEnabled(false);
		Main.JCHK_PLASTICS.setEnabled(false);
		Main.JCHK_CIDAR.setEnabled(false);
		Main.JCHK_NOAC.setEnabled(false);
		Main.JL_PRIMARY_NAME.setEnabled(false);
		Main.JL_PRIMARY_PHONE.setEnabled(false);
		Main.JL_PRIMARY_EMAIL.setEnabled(false);
		Main.JL_PRIMARY_ALT_PHONE.setEnabled(false);
		Main.JL_ADDRESS.setEnabled(false);
		Main.JL_ADDRESS_L2.setEnabled(false);
		Main.JL_ALT_NAME.setEnabled(false);
		Main.JL_ALT_PHONE.setEnabled(false);
		Main.JL_ALT_EMAIL.setEnabled(false);
		Main.JL_ALT_ALT_PHONE.setEnabled(false);
		Main.JL_CITY.setEnabled(false);
		Main.JL_ZIP.setEnabled(false);
		Main.PRIMARY_FIRST_NAME.setEnabled(false);
		Main.PRIMARY_LAST_NAME.setEnabled(false);
		Main.PRIMARY_AREA_CODE.setEnabled(false);
		Main.PRIMARY_PHONE_NUMBER.setEnabled(false);
		Main.PRIMARY_EXT.setEnabled(false);
		Main.PRIMARY_EMAIL.setEnabled(false);
		Main.PRIMARY_ALT_AREA_CODE.setEnabled(false);
		Main.PRIMARY_ALT_PHONE_NUMBER.setEnabled(false);
		Main.PRIMARY_ALT_EXT.setEnabled(false);
		Main.PRIMARY_ADDRESS.setEnabled(false);
		Main.PRIMARY_ADDRESS_L2.setEnabled(false);
		Main.ALT_FIRST_NAME.setEnabled(false);
		Main.ALT_LAST_NAME.setEnabled(false);
		Main.ALT_AREA_CODE.setEnabled(false);
		Main.ALT_PHONE_NUMBER.setEnabled(false);
		Main.ALT_EXT.setEnabled(false);
		Main.ALT_EMAIL.setEnabled(false);
		Main.ALT_ALT_AREA_CODE.setEnabled(false);
		Main.ALT_ALT_PHONE_NUMBER.setEnabled(false);
		Main.ALT_ALT_EXT.setEnabled(false);
		Main.PRIMARY_CITY.setEnabled(false);
		Main.PRIMARY_ZIP.setEnabled(false);
		Main.JB_SERVICE_TAG.setEnabled(false);
		Main.JB_SERVICE_REQUEST.setEnabled(false);
		Main.JTF_DESCRIPTION.setEnabled(false);
		Main.JTF_SERVICE_REQUEST.setEnabled(false);
		Main.JTF_SERVICE_TAG.setEnabled(false);
		Main.JTA_NOTES.setEnabled(false);
		Main.JTA_TROUBLESHOOTING.setEnabled(false);
		Main.JTA_CONCLUSION.setEnabled(false);
	}
	
	/**
	 * TODO ... work in progress
	 */
	static void SET_TO_INITIAL_ENABLED_STATE() {
		Main.JCHK_VA.setEnabled(false);
		Main.JCHK_TOADE.setEnabled(false);
		Main.JCHK_EMAIL_CAP.setEnabled(false);
		Main.JCHK_VDI.setEnabled(false);
		Main.JCHK_TARP.setEnabled(false);
		Main.JCHK_POS.setEnabled(false);
		Main.JCHK_PAL.setEnabled(false);
		Main.JCHK_PLASTICS.setEnabled(false);
		Main.JCHK_CIDAR.setEnabled(false);
		Main.JCHK_NOAC.setEnabled(false);
		Main.JL_PRIMARY_NAME.setEnabled(true);
		Main.JL_PRIMARY_PHONE.setEnabled(true);
		Main.JL_PRIMARY_EMAIL.setEnabled(true);
		Main.JL_PRIMARY_ALT_PHONE.setEnabled(true);
		Main.JL_ADDRESS.setEnabled(false);
		Main.JL_ADDRESS_L2.setEnabled(false);
		Main.JL_ALT_NAME.setEnabled(false);
		Main.JL_ALT_PHONE.setEnabled(false);
		Main.JL_ALT_EMAIL.setEnabled(false);
		Main.JL_ALT_ALT_PHONE.setEnabled(false);
		Main.JL_CITY.setEnabled(false);
		Main.JL_ZIP.setEnabled(false);
		Main.PRIMARY_FIRST_NAME.setEnabled(true);
		Main.PRIMARY_LAST_NAME.setEnabled(true);
		Main.PRIMARY_AREA_CODE.setEnabled(true);
		Main.PRIMARY_PHONE_NUMBER.setEnabled(true);
		Main.PRIMARY_EXT.setEnabled(true);
		Main.PRIMARY_EMAIL.setEnabled(true);
		Main.PRIMARY_ALT_AREA_CODE.setEnabled(true);
		Main.PRIMARY_ALT_PHONE_NUMBER.setEnabled(true);
		Main.PRIMARY_ALT_EXT.setEnabled(true);
		Main.PRIMARY_ADDRESS.setEnabled(false);
		Main.PRIMARY_ADDRESS_L2.setEnabled(false);
		Main.ALT_FIRST_NAME.setEnabled(false);
		Main.ALT_LAST_NAME.setEnabled(false);
		Main.ALT_AREA_CODE.setEnabled(false);
		Main.ALT_PHONE_NUMBER.setEnabled(false);
		Main.ALT_EXT.setEnabled(false);
		Main.ALT_EMAIL.setEnabled(false);
		Main.ALT_ALT_AREA_CODE.setEnabled(false);
		Main.ALT_ALT_PHONE_NUMBER.setEnabled(false);
		Main.ALT_ALT_EXT.setEnabled(false);
		Main.PRIMARY_CITY.setEnabled(false);
		Main.PRIMARY_ZIP.setEnabled(false);
		Main.JB_SERVICE_TAG.setEnabled(false);
		Main.JB_SERVICE_REQUEST.setEnabled(false);
		Main.JTF_DESCRIPTION.setEnabled(true);
		Main.JTF_SERVICE_REQUEST.setEnabled(true);
		Main.JTF_SERVICE_TAG.setEnabled(true);
		Main.JTA_NOTES.setEnabled(true);
		Main.JTA_TROUBLESHOOTING.setEnabled(true);
		Main.JTA_CONCLUSION.setEnabled(true);
	}
	
	/**
	 * Contains the control flow for the program. Reads the data in from 'Data.ser' 
	 * and initializes the frame. To be called by the main method.
	 */
	public Main() {
		
		//set margins for text fields/areas
		Insets i = new Insets(1, 1, 1, 1);
		Main.JTF_COMPANY.setMargin(i);
		Main.PRIMARY_FIRST_NAME.setMargin(i);
		Main.PRIMARY_LAST_NAME.setMargin(i);
		Main.PRIMARY_AREA_CODE.setMargin(i);
		Main.PRIMARY_PHONE_NUMBER.setMargin(i);
		Main.PRIMARY_EXT.setMargin(i);
		Main.PRIMARY_EMAIL.setMargin(i);
		Main.PRIMARY_ALT_AREA_CODE.setMargin(i);
		Main.PRIMARY_ALT_PHONE_NUMBER.setMargin(i);
		Main.PRIMARY_ALT_EXT.setMargin(i);
		Main.PRIMARY_ADDRESS.setMargin(i);
		Main.PRIMARY_ADDRESS_L2.setMargin(i);
		Main.PRIMARY_CITY.setMargin(i);
		Main.PRIMARY_ZIP.setMargin(i);
		Main.ALT_FIRST_NAME.setMargin(i);
		Main.ALT_LAST_NAME.setMargin(i);
		Main.ALT_AREA_CODE.setMargin(i);
		Main.ALT_PHONE_NUMBER.setMargin(i);
		Main.ALT_EXT.setMargin(i);
		Main.ALT_EMAIL.setMargin(i);
		Main.ALT_ALT_AREA_CODE.setMargin(i);
		Main.ALT_ALT_PHONE_NUMBER.setMargin(i);
		Main.ALT_ALT_EXT.setMargin(i);
		Main.ALT_ADDRESS.setMargin(i);
		Main.ALT_ADDRESS_L2.setMargin(i);
		Main.ALT_CITY.setMargin(i);
		Main.ALT_ZIP.setMargin(i);
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
		Main.JTF_SEARCH_FIELD.setMargin(i);
		
		//read config file
		try {
			File f = new File("config.txt");
			Scanner s = new Scanner(f);
			while (s.hasNextLine()) {
				String LINE = s.nextLine();
				System.out.println(LINE);
				String[] LINE_DATA = LINE.split("="); //break line at delimeter =
				if (LINE_DATA.length == 2) {
					if (LINE_DATA[0].toLowerCase().contains("branch") && 
							LINE_DATA[1].contains("-1")) {
						Main.BRANCH = -1;						
					}
										
					else if (LINE_DATA[0].toLowerCase().contains("owner")) {
						Main.OWNER = LINE_DATA[1];
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//add objects from "Data.ser" and populate JList with those objects
		try {
			FileInputStream fileStream = new FileInputStream("Data.ser");
			ObjectInputStream os = new ObjectInputStream(fileStream);
			
			while (true) {
				Main.FIELDS.add((DataField) os.readObject());
				Main.FIELDS.get(Main.SELECTED_INDEX).initializeStatus();
				
				//add FIELDS item to LIST_MODEL
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
					Main.LIST_MODEL.add(Main.SELECTED_INDEX, sb.toString());
				}
				else Main.LIST_MODEL.add(Main.SELECTED_INDEX, "NEW");
				
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
			LocalDate today = new LocalDate();
			int days = 1;
			while (days < 4) {
				if (Main.FIELDS.get(Main.SELECTED_INDEX).workingDaysBetween(today, today.plusDays(days)) == 1)
					break;
				days++;
			}
			Main.FIELDS.get(Main.SELECTED_INDEX).setCommittedDate(today.plusDays(days));
			Main.FIELDS.get(Main.SELECTED_INDEX).setStatus(Main.STATUS_IS_TOUCHED);
			//add troubleshooting template
			String LINE = "";
			try {
				File f = new File("TS_Template.txt");
				Scanner s = new Scanner(f);
				while (s.hasNextLine()) {
					LINE += s.nextLine() + "\n";
					System.out.println(LINE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			Main.FIELDS.get(Main.SELECTED_INDEX).setTroubleshooting(LINE);
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
		f = new MenuFrame();
	}
	
	/**
	 * The main method. Initializes the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}
}
