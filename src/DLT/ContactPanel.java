package DLT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * ContactPanel class
 * 
 * ..
 * 
 * @author Trent Fowler
 * @author Bryan W.W.
 */
public class ContactPanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = 4220570468139986554L;

	public ContactPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		//CONTACT
		JPanel contactContainer = new JPanel();
		contactContainer.setOpaque(true);
		contactContainer.setBackground(Color.WHITE);
		contactContainer.setBorder(new EmptyBorder(0, 0, 0, 5));
		GridBagLayout gbl_C = new GridBagLayout();
		gbl_C.columnWidths = new int[]{100, 0};
		gbl_C.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_C.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_C.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contactContainer.setLayout(gbl_C);
		
		//name
		JLabel jlName = new JLabel("     Name");
		
		GridBagConstraints gbc_Name_Label = new GridBagConstraints();
		gbc_Name_Label.anchor = GridBagConstraints.WEST;
		gbc_Name_Label.gridx = 0;
		gbc_Name_Label.gridy = 0;
		
		contactContainer.add(jlName, gbc_Name_Label);
		
		GridBagConstraints gbc_Name_Field = new GridBagConstraints();
		gbc_Name_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Name_Field.gridx = 1;
		gbc_Name_Field.gridy = 0;
		
		contactContainer.add(Main.JTF_NAME, gbc_Name_Field);
		
		//email
		JLabel jlEmail = new JLabel("     E-mail");
		
		GridBagConstraints gbc_Email_Label = new GridBagConstraints();
		gbc_Email_Label.anchor = GridBagConstraints.WEST;
		gbc_Email_Label.gridx = 0;
		gbc_Email_Label.gridy = 2;
		
		contactContainer.add(jlEmail, gbc_Email_Label);
		
		GridBagConstraints gbc_Email_Field = new GridBagConstraints();
		gbc_Email_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Email_Field.gridx = 1;
		gbc_Email_Field.gridy = 2;
		
		contactContainer.add(Main.JTF_EMAIL, gbc_Email_Field);
		
		//phone
		JLabel jlPhone = new JLabel("     Phone");
		
		GridBagConstraints gbc_Phone_Label = new GridBagConstraints();
		gbc_Phone_Label.anchor = GridBagConstraints.WEST;
		gbc_Phone_Label.gridx = 0;
		gbc_Phone_Label.gridy = 1;
		
		contactContainer.add(jlPhone, gbc_Phone_Label);
		
		GridBagConstraints gbc_Phone_Field = new GridBagConstraints();
		gbc_Phone_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Phone_Field.gridx = 1;
		gbc_Phone_Field.gridy = 1;
		
		contactContainer.add(Main.JTF_PHONE, gbc_Phone_Field);
		
		//alt phone
		JLabel jlAltPhone = new JLabel("     Alt. Phone");
		
		GridBagConstraints gbc_Alt_Phone_Label = new GridBagConstraints();
		gbc_Alt_Phone_Label.anchor = GridBagConstraints.WEST;
		gbc_Alt_Phone_Label.gridx = 0;
		gbc_Alt_Phone_Label.gridy = 3;
		
		contactContainer.add(jlAltPhone, gbc_Alt_Phone_Label);
		
		GridBagConstraints gbc_Alt_Phone_Field = new GridBagConstraints();
		gbc_Alt_Phone_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Alt_Phone_Field.gridx = 1;
		gbc_Alt_Phone_Field.gridy = 3;
		
		contactContainer.add(Main.JTF_ALT_PHONE, gbc_Alt_Phone_Field);
		
		//address
		JLabel jlAddress = new JLabel("  Address Line#1");
		
		GridBagConstraints gbc_Address_Label = new GridBagConstraints();
		gbc_Address_Label.anchor = GridBagConstraints.WEST;
		gbc_Address_Label.gridx = 0;
		gbc_Address_Label.gridy = 4;
		
		contactContainer.add(jlAddress, gbc_Address_Label);
		
		GridBagConstraints gbc_Address_Field = new GridBagConstraints();
		gbc_Address_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Address_Field.gridx = 1;
		gbc_Address_Field.gridy = 4;
		
		contactContainer.add(Main.JTF_ADDRESS, gbc_Address_Field);
		
		//made second line as address line#2:: used for APT,FLR,Suite,etc.
		GridBagConstraints gbc_CSZ_Label = new GridBagConstraints();
		gbc_CSZ_Label.anchor = GridBagConstraints.WEST;
		gbc_CSZ_Label.gridx = 0;
		gbc_CSZ_Label.gridy = 5;
		
		contactContainer.add(new JLabel("  Address Line#2"), gbc_CSZ_Label);
		
		GridBagConstraints gbc_CSZ_Field = new GridBagConstraints();
		gbc_CSZ_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_CSZ_Field.gridx = 1;
		gbc_CSZ_Field.gridy = 5;
		
		contactContainer.add(Main.JTF_CITY_STATE_ZIP, gbc_CSZ_Field);
		
		//SECONDARY CONTACT
		JPanel altContactContainer = new JPanel();
		altContactContainer.setOpaque(true);
		altContactContainer.setBackground(Color.WHITE);
		GridBagLayout gbl_AC = new GridBagLayout();
		gbl_AC.columnWidths = new int[]{100, 0};
		gbl_AC.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_AC.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_AC.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		altContactContainer.setLayout(gbl_AC);
		
		//alt name
		JLabel jlAltName = new JLabel("     Name");
		
		GridBagConstraints gbc_Alt_Name_Label = new GridBagConstraints();
		gbc_Alt_Name_Label.anchor = GridBagConstraints.WEST;
		gbc_Alt_Name_Label.gridx = 0;
		gbc_Alt_Name_Label.gridy = 0;
		
		GridBagConstraints gbc_Alt_Name_Field = new GridBagConstraints();
		gbc_Alt_Name_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Alt_Name_Field.gridx = 1;
		gbc_Alt_Name_Field.gridy = 0;
		
		altContactContainer.add(jlAltName, gbc_Alt_Name_Label);
		altContactContainer.add(Main.JTF_ALT_NAME, gbc_Alt_Name_Field);
		
		//alt email: arranged after alt primary phone
		JLabel jlAltEmail = new JLabel("     E-mail");
		
		GridBagConstraints gbc_Alt_Email_Label = new GridBagConstraints();
		gbc_Alt_Email_Label.anchor = GridBagConstraints.WEST;
		gbc_Alt_Email_Label.gridx = 0;
		gbc_Alt_Email_Label.gridy = 2;
				
		GridBagConstraints gbc_Alt_Email_Field = new GridBagConstraints();
		gbc_Alt_Email_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Alt_Email_Field.gridx = 1;
		gbc_Alt_Email_Field.gridy = 2;
		
		altContactContainer.add(jlAltEmail, gbc_Alt_Email_Label);
		altContactContainer.add(Main.JTF_ALT_EMAIL, gbc_Alt_Email_Field);
		
		//alt primary phone: arranged before alt email
		JLabel jlAltPrimaryPhone = new JLabel("     Phone");
		
		GridBagConstraints gbc_Alt_PP_Label = new GridBagConstraints();
		gbc_Alt_PP_Label.anchor = GridBagConstraints.WEST;
		gbc_Alt_PP_Label.gridx = 0;
		gbc_Alt_PP_Label.gridy = 1;
				
		GridBagConstraints gbc_Alt_PP_Field = new GridBagConstraints();
		gbc_Alt_PP_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Alt_PP_Field.gridx = 1;
		gbc_Alt_PP_Field.gridy = 1;
		
		altContactContainer.add(jlAltPrimaryPhone, gbc_Alt_PP_Label);
		altContactContainer.add(Main.JTF_ALT_PRIMARY_PHONE, gbc_Alt_PP_Field);
		
		//alt secondary phone
		JLabel jlAltSecondaryPhone = new JLabel("     Alt. Phone");
		
		GridBagConstraints gbc_Alt_SP_Label = new GridBagConstraints();
		gbc_Alt_SP_Label.anchor = GridBagConstraints.WEST;
		gbc_Alt_SP_Label.gridx = 0;
		gbc_Alt_SP_Label.gridy = 3;
		
		GridBagConstraints gbc_Alt_SP_Field = new GridBagConstraints();
		gbc_Alt_SP_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Alt_SP_Field.gridx = 1;
		gbc_Alt_SP_Field.gridy = 3;
		
		altContactContainer.add(jlAltSecondaryPhone, gbc_Alt_SP_Label);
		altContactContainer.add(Main.JTF_ALT_SECONDARY_PHONE, gbc_Alt_SP_Field);
		
		//alt address
		//used for city/state
		JLabel jlAltAddress = new JLabel("     City");
		
		GridBagConstraints gbc_Alt_Address_Label = new GridBagConstraints();
		gbc_Alt_Address_Label.anchor = GridBagConstraints.WEST;
		gbc_Alt_Address_Label.gridx = 0;
		gbc_Alt_Address_Label.gridy = 4;
		
		GridBagConstraints gbc_Alt_Address_Field = new GridBagConstraints();
		gbc_Alt_Address_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Alt_Address_Field.gridx = 1;
		gbc_Alt_Address_Field.gridy = 4;
		
		altContactContainer.add(jlAltAddress, gbc_Alt_Address_Label);
		altContactContainer.add(Main.JTF_ALT_ADDRESS, gbc_Alt_Address_Field);
		
		//alt city, state, zip
		//used for postal code
		GridBagConstraints gbc_Alt_CSZ_Label = new GridBagConstraints();
		gbc_Alt_CSZ_Label.anchor = GridBagConstraints.WEST;
		gbc_Alt_CSZ_Label.gridx = 0;
		gbc_Alt_CSZ_Label.gridy = 5;
		
		altContactContainer.add(new JLabel("  Postal Code"), gbc_Alt_CSZ_Label);
		
		GridBagConstraints gbc_Alt_CSZ_Field = new GridBagConstraints();
		gbc_Alt_CSZ_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Alt_CSZ_Field.gridx = 1;
		gbc_Alt_CSZ_Field.gridy = 5;
		
		altContactContainer.add(Main.JTF_ALT_CITY_STATE_ZIP, gbc_Alt_CSZ_Field);
		
		//...
		JPanel jpContactsContainer = new JPanel();
		jpContactsContainer.setOpaque(true);
		jpContactsContainer.setBackground(Color.WHITE);
		jpContactsContainer.setBorder(new EmptyBorder(0, 0, 5, 0));
		jpContactsContainer.setLayout(new GridLayout(1, 2));
		jpContactsContainer.add(contactContainer);
		jpContactsContainer.add(altContactContainer);
				
		//company
		JPanel companyContainer = new JPanel();
		companyContainer.setOpaque(true);
		companyContainer.setBackground(Color.WHITE);
		GridBagLayout gbl_Co = new GridBagLayout();
		gbl_Co.columnWidths = new int[]{100, 0};
		gbl_Co.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_Co.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_Co.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		companyContainer.setLayout(gbl_Co);
				
		GridBagConstraints gbc_Company_Label = new GridBagConstraints();
		gbc_Company_Label.anchor = GridBagConstraints.WEST;
		gbc_Company_Label.gridx = 0;
		gbc_Company_Label.gridy = 0;
		
		GridBagConstraints gbc_Company_Field = new GridBagConstraints();
		gbc_Company_Field.fill = GridBagConstraints.HORIZONTAL;
		gbc_Company_Field.gridx = 1;
		gbc_Company_Field.gridy = 0;
		
		companyContainer.add(new JLabel("     Company"), gbc_Company_Label);
		companyContainer.add(Main.JTF_COMPANY, gbc_Company_Field);
		
		//...
		JPanel container = new JPanel();
		container.setOpaque(true);
		container.setBackground(Color.WHITE);
		container.setBorder(new EmptyBorder(5, 5, 0, 5));
		container.setLayout(new BorderLayout());
		//container.add(companyContainer, BorderLayout.NORTH);
		container.add(jpContactsContainer, BorderLayout.CENTER);
		
		//...
		JLabel jlTitle = new JLabel("Contact Info:");
		jlTitle.setOpaque(true);
		jlTitle.setBackground(new Color(90, 167, 226));
		jlTitle.setForeground(Color.WHITE);
		jlTitle.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		this.add(jlTitle, BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		
		Main.JTF_COMPANY.getDocument().addDocumentListener(this);
		Main.JTF_NAME.getDocument().addDocumentListener(this);
		Main.JTF_EMAIL.getDocument().addDocumentListener(this);
		Main.JTF_PHONE.getDocument().addDocumentListener(this);		
		Main.JTF_ALT_PHONE.getDocument().addDocumentListener(this);
		Main.JTF_ADDRESS.getDocument().addDocumentListener(this);
		Main.JTF_CITY_STATE_ZIP.getDocument().addDocumentListener(this);
		Main.JTF_ALT_NAME.getDocument().addDocumentListener(this);
		Main.JTF_ALT_EMAIL.getDocument().addDocumentListener(this);
		Main.JTF_ALT_PRIMARY_PHONE.getDocument().addDocumentListener(this);
		Main.JTF_ALT_SECONDARY_PHONE.getDocument().addDocumentListener(this);
		Main.JTF_ALT_ADDRESS.getDocument().addDocumentListener(this);
		Main.JTF_ALT_CITY_STATE_ZIP.getDocument().addDocumentListener(this);
	}

	@Override public void changedUpdate(DocumentEvent e) {
		Main.HAS_UNSAVED_CHANGES = true;
		
		//name
		if (Main.JTF_NAME.getText().length() < 3) Main.JTF_NAME.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_NAME.setBackground(new Color(161, 255, 161)); //green
		
		//e-mail
		if (Main.JTF_EMAIL.getText().length() < 6) Main.JTF_EMAIL.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_EMAIL.setBackground(new Color(161, 255, 161)); //green
		
		//phone
		if (Main.JTF_PHONE.getText().length() < 10) {
			Main.JTF_PHONE.setBackground(new Color(255, 221, 221)); //red
		} 
		else {
			int count = 0;
			for (int i = 0; i < Main.JTF_PHONE.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_PHONE.getText().charAt(i))) {
					count++;
				}
			}
			if (count >= 10) {
				Main.JTF_PHONE.setBackground(new Color(161, 255, 161)); //green	
			}
		}
		
		//company
		if (Main.JTF_COMPANY.getText().length() < 3) Main.JTF_COMPANY.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_COMPANY.setBackground(new Color(161, 255, 161)); //green
	}

	@Override public void insertUpdate(DocumentEvent e) {
		Main.HAS_UNSAVED_CHANGES = true;
		
		//name
		if (Main.JTF_NAME.getText().length() < 3) Main.JTF_NAME.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_NAME.setBackground(new Color(161, 255, 161)); //green
		
		//e-mail
		if (Main.JTF_EMAIL.getText().length() < 6) Main.JTF_EMAIL.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_EMAIL.setBackground(new Color(161, 255, 161)); //green
		
		//phone
		if (Main.JTF_PHONE.getText().length() < 10) {
			Main.JTF_PHONE.setBackground(new Color(255, 221, 221)); //red
		} 
		else {
			int count = 0;
			for (int i = 0; i < Main.JTF_PHONE.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_PHONE.getText().charAt(i))) {
					count++;
				}
			}
			if (count >= 10) {
				Main.JTF_PHONE.setBackground(new Color(161, 255, 161)); //green	
			}
		}
		
		//company
		if (Main.JTF_COMPANY.getText().length() < 3) Main.JTF_COMPANY.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_COMPANY.setBackground(new Color(161, 255, 161)); //green
	}

	@Override public void removeUpdate(DocumentEvent e) {
		Main.HAS_UNSAVED_CHANGES = true;
		
		//name
		if (Main.JTF_NAME.getText().length() < 3) Main.JTF_NAME.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_NAME.setBackground(new Color(161, 255, 161)); //green
		
		//e-mail
		if (Main.JTF_EMAIL.getText().length() < 6) Main.JTF_EMAIL.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_EMAIL.setBackground(new Color(161, 255, 161)); //green
		
		//phone
		if (Main.JTF_PHONE.getText().length() < 10) {
			Main.JTF_PHONE.setBackground(new Color(255, 221, 221)); //red
		} 
		else {
			int count = 0;
			for (int i = 0; i < Main.JTF_PHONE.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_PHONE.getText().charAt(i))) {
					count++;
				}
			}
			if (count >= 10) {
				Main.JTF_PHONE.setBackground(new Color(161, 255, 161)); //green	
			}
		}
		
		//company
		if (Main.JTF_COMPANY.getText().length() < 3) Main.JTF_COMPANY.setBackground(new Color(255, 221, 221)); //red
		else Main.JTF_COMPANY.setBackground(new Color(161, 255, 161)); //green
	}	
}
