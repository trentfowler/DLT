package DLT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

/**
 * ContactPanel class
 * 
 * This class defines the JPanel which allows you to view and modify 
 * the contact info like name, phone number, e-mail, address, etc...
 * 
 * @author Trent
 * @author Bryan
 */
public class ContactPanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = 4220570468139986554L;

	public ContactPanel() {
		
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());		
		
		JPanel primary = new JPanel();
		primary.setOpaque(true);
		primary.setBackground(Color.WHITE);
		primary.setLayout(new MigLayout("fill",
										"10[min!][][][][][][]",
										""));
		primary.add(Main.JL_PRIMARY_NAME,           "grow, cell 0 0 1 1");
		primary.add(Main.PRIMARY_FIRST_NAME,		"grow, cell 1 0 3 1"); // cell column row width height
		primary.add(Main.PRIMARY_LAST_NAME,			"grow, cell 4 0 3 1, wrap");
		primary.add(Main.JL_PRIMARY_PHONE,				  "cell 0 1 1 1");
		primary.add(Main.PRIMARY_AREA_CODE,			"grow, cell 1 1 1 1");
		primary.add(Main.PRIMARY_PHONE_NUMBER,		"grow, cell 2 1 3 1");
		primary.add(Main.PRIMARY_EXT,				"grow, cell 5 1 2 1, wrap");
		primary.add(Main.JL_PRIMARY_EMAIL,				  "cell 0 2 1 1");
		primary.add(Main.PRIMARY_EMAIL,				"grow, cell 1 2 6 1, wrap");
		primary.add(Main.JL_PRIMARY_ALT_PHONE,			  "cell 0 3 1 1");
		primary.add(Main.PRIMARY_ALT_AREA_CODE,		"grow, cell 1 3 1 1");
		primary.add(Main.PRIMARY_ALT_PHONE_NUMBER,	"grow, cell 2 3 3 1");
		primary.add(Main.PRIMARY_ALT_EXT,			"grow, cell 5 3 2 1, wrap");
		primary.add(Main.JL_ADDRESS,					  "cell 0 4 1 1");
		primary.add(Main.PRIMARY_ADDRESS,			"grow, cell 1 4 6 1, wrap");
		primary.add(Main.JL_ADDRESS_L2,				"grow, cell 0 5 1 1");
		primary.add(Main.PRIMARY_ADDRESS_L2,		"grow, cell 1 5 6 1, wrap");
		
		JPanel alt = new JPanel();
		alt.setOpaque(true);
		alt.setBackground(Color.WHITE);
		alt.setLayout(new MigLayout("fill",
									"[min!][][][][][][]",
									""));
		alt.add(Main.JL_ALT_NAME,					"grow, cell 0 0 1 1"); //"cell column row width height"
		alt.add(Main.ALT_FIRST_NAME,				"grow, cell 1 0 3 1");
		alt.add(Main.ALT_LAST_NAME,					"grow, cell 4 0 3 1, wrap");
		alt.add(Main.JL_ALT_PHONE,					"grow, cell 0 1 1 1");
		alt.add(Main.ALT_AREA_CODE,					"grow, cell 1 1 1 1");
		alt.add(Main.ALT_PHONE_NUMBER,				"grow, cell 2 1 3 1");
		alt.add(Main.ALT_EXT,						"grow, cell 5 1 2 1, wrap");
		alt.add(Main.JL_ALT_EMAIL,					"grow, cell 0 2 1 1");
		alt.add(Main.ALT_EMAIL,						"grow, cell 1 2 6 1, wrap");
		alt.add(Main.JL_ALT_ALT_PHONE,				"grow, cell 0 3 1 1");
		alt.add(Main.ALT_ALT_AREA_CODE,				"grow, cell 1 3 1 1");
		alt.add(Main.ALT_ALT_PHONE_NUMBER,			"grow, cell 2 3 4 1");
		alt.add(Main.ALT_ALT_EXT,					"grow, cell 6 3 1 1, wrap");
		alt.add(Main.JL_CITY,						"grow, cell 0 4 1 1");
		alt.add(Main.PRIMARY_CITY,					"grow, cell 1 4 6 1, wrap");
		alt.add(Main.JL_ZIP,						"grow, cell 0 5 1 1");
		alt.add(Main.PRIMARY_ZIP,					"grow, cell 1 5 6 1, wrap");
				
		JPanel p = new JPanel();
		p.setOpaque(true);
		p.setLayout(new GridLayout(0, 2));
		p.setBackground(Color.WHITE);
		p.add(primary);
		p.add(alt);
		
		JLabel blue = new JLabel(" ");
		blue.setOpaque(true);
		blue.setBackground(new Color(90, 167, 226));
		blue.setForeground(Color.WHITE);
		blue.setBorder(new EmptyBorder(3, 5, 3, 5));

		this.add(blue, BorderLayout.NORTH);
		this.add(p, BorderLayout.CENTER);
		
		Main.PRIMARY_FIRST_NAME.getDocument().addDocumentListener(this);
		Main.PRIMARY_LAST_NAME.getDocument().addDocumentListener(this);
		Main.PRIMARY_AREA_CODE.getDocument().addDocumentListener(this);
		Main.PRIMARY_PHONE_NUMBER.getDocument().addDocumentListener(this);
		Main.PRIMARY_EXT.getDocument().addDocumentListener(this);
		Main.PRIMARY_EMAIL.getDocument().addDocumentListener(this);
		Main.PRIMARY_ALT_AREA_CODE.getDocument().addDocumentListener(this);
		Main.PRIMARY_ALT_PHONE_NUMBER.getDocument().addDocumentListener(this);
		Main.PRIMARY_ALT_EXT.getDocument().addDocumentListener(this);
		Main.ALT_AREA_CODE.getDocument().addDocumentListener(this);
		Main.ALT_PHONE_NUMBER.getDocument().addDocumentListener(this);
		Main.ALT_EXT.getDocument().addDocumentListener(this);
		Main.ALT_ALT_AREA_CODE.getDocument().addDocumentListener(this);
		Main.ALT_ALT_PHONE_NUMBER.getDocument().addDocumentListener(this);
		Main.ALT_ALT_EXT.getDocument().addDocumentListener(this);
	}

	@Override public void changedUpdate(DocumentEvent arg0) {
		Main.HAS_UNSAVED_CHANGES = true;
		
		//name
		if (Main.PRIMARY_FIRST_NAME.getText().length() < 3) {
			Main.PRIMARY_FIRST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_FIRST_NAME.setBackground(new Color(161, 255, 161)); //green
		}
		
		if (Main.PRIMARY_LAST_NAME.getText().length() < 3) {
			Main.PRIMARY_LAST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_LAST_NAME.setBackground(new Color(161, 255, 161)); //green
		}
		
		//e-mail
		if (Main.PRIMARY_EMAIL.getText().length() < 6) {
			Main.PRIMARY_EMAIL.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_EMAIL.setBackground(new Color(161, 255, 161)); //green
		}
		
		//phone -- area code
		if (Main.PRIMARY_AREA_CODE.getText().length() != 3) {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(161, 255, 161)); //green
		}
		
		//phone -- phone number
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
	}

	@Override public void insertUpdate(DocumentEvent arg0) {
		Main.HAS_UNSAVED_CHANGES = true;
		
		//name
		if (Main.PRIMARY_FIRST_NAME.getText().length() < 3) {
			Main.PRIMARY_FIRST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_FIRST_NAME.setBackground(new Color(161, 255, 161)); //green
		}
		
		if (Main.PRIMARY_LAST_NAME.getText().length() < 3) {
			Main.PRIMARY_LAST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_LAST_NAME.setBackground(new Color(161, 255, 161)); //green
		}
		
		//e-mail
		if (Main.PRIMARY_EMAIL.getText().length() < 6) {
			Main.PRIMARY_EMAIL.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_EMAIL.setBackground(new Color(161, 255, 161)); //green
		}
		
		//phone -- area code
		if (Main.PRIMARY_AREA_CODE.getText().length() != 3) {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(161, 255, 161)); //green
		}
		
		//phone -- phone number
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
		
		//move focus to phone number automatically after area code entry
		if (Main.PRIMARY_AREA_CODE.isFocusOwner() && Main.PRIMARY_AREA_CODE.getText().length() == 3) {
			Main.PRIMARY_PHONE_NUMBER.grabFocus();
		}
		
		if (Main.PRIMARY_ALT_AREA_CODE.isFocusOwner() && Main.PRIMARY_ALT_AREA_CODE.getText().length() == 3) {
			Main.PRIMARY_ALT_PHONE_NUMBER.grabFocus();
		}
		
		if (Main.ALT_AREA_CODE.isFocusOwner() && Main.ALT_AREA_CODE.getText().length() == 3) {
			Main.ALT_PHONE_NUMBER.grabFocus();
		}
		
		if (Main.ALT_ALT_AREA_CODE.isFocusOwner() && Main.ALT_ALT_AREA_CODE.getText().length() == 3) {
			Main.ALT_ALT_PHONE_NUMBER.grabFocus();
		}
	}
	
	@Override public void removeUpdate(DocumentEvent arg0) {
		Main.HAS_UNSAVED_CHANGES = true;
		
		//name
		if (Main.PRIMARY_FIRST_NAME.getText().length() < 3) {
			Main.PRIMARY_FIRST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_FIRST_NAME.setBackground(new Color(161, 255, 161)); //green
		}
		
		if (Main.PRIMARY_LAST_NAME.getText().length() < 3) {
			Main.PRIMARY_LAST_NAME.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_LAST_NAME.setBackground(new Color(161, 255, 161)); //green
		}
		
		//e-mail
		if (Main.PRIMARY_EMAIL.getText().length() < 6) {
			Main.PRIMARY_EMAIL.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_EMAIL.setBackground(new Color(161, 255, 161)); //green
		}
		
		//phone -- area code
		if (Main.PRIMARY_AREA_CODE.getText().length() != 3) {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(255, 181, 181)); //red
		}
		else {
			Main.PRIMARY_AREA_CODE.setBackground(new Color(161, 255, 161)); //green
		}
		
		//phone -- phone number
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
	}	
}
