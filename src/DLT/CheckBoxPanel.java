package DLT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * CheckBoxPanel class
 * 
 * ...
 * 
 * @author Trent
 * @author Bryan
 *
 */
public class CheckBoxPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -6399129758447314560L;
	
	public CheckBoxPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		
		this.setLayout(new BorderLayout());
		
		Main.JCHK_VA.setBackground(Color.WHITE);
		Main.JCHK_TOADE.setBackground(Color.WHITE);
		Main.JCHK_VDI.setBackground(Color.WHITE);
		Main.JCHK_EMAIL_CAP.setBackground(Color.WHITE);
		Main.JCHK_TARP.setBackground(Color.WHITE);
		Main.JCHK_POS.setBackground(Color.WHITE);
		Main.JCHK_PAL.setBackground(Color.WHITE);
		Main.JCHK_PLASTICS.setBackground(Color.WHITE);
		Main.JCHK_CIDAR.setBackground(Color.WHITE);
		Main.JCHK_NOAC.setBackground(Color.WHITE);
		
		Main.JCHK_VA.setToolTipText("Verified Authorization");
		Main.JCHK_TOADE.setToolTipText("Told Owner About Date of Expiration");
		Main.JCHK_VDI.setToolTipText("Verified Dispatch Information");
		Main.JCHK_EMAIL_CAP.setToolTipText("E-mail Captured");
		Main.JCHK_TARP.setToolTipText("Told About Return Policy");
		Main.JCHK_POS.setToolTipText("Parts Only Service");
		Main.JCHK_PAL.setToolTipText("Parts and Labor Dispatch");
		Main.JCHK_PLASTICS.setToolTipText("Checked Plastics");
		Main.JCHK_CIDAR.setToolTipText("Customer Induced Damage Policy Explained");
		Main.JCHK_NOAC.setToolTipText("No Alt. Contact");
		
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setOpaque(true);
		checkBoxPanel.setBackground(Color.WHITE);
		checkBoxPanel.add(Main.JCHK_VA);
		checkBoxPanel.add(Main.JCHK_TOADE);
		checkBoxPanel.add(Main.JCHK_EMAIL_CAP);
		checkBoxPanel.add(Main.JCHK_VDI);
		checkBoxPanel.add(Main.JCHK_TARP);
		checkBoxPanel.add(Main.JCHK_POS);
		checkBoxPanel.add(Main.JCHK_PAL);
		checkBoxPanel.add(Main.JCHK_PLASTICS);
		checkBoxPanel.add(Main.JCHK_CIDAR);
		checkBoxPanel.add(Main.JCHK_NOAC);
		
		JLabel jl = new JLabel(" ");
		jl.setOpaque(true);
		jl.setBackground(new Color(90, 167, 226));
		jl.setForeground(Color.WHITE);
		jl.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		this.add(jl, BorderLayout.NORTH);
		this.add(checkBoxPanel, BorderLayout.CENTER);
		
		Main.JCHK_VA.addActionListener(this);
		Main.JCHK_TOADE.addActionListener(this);
		Main.JCHK_VDI.addActionListener(this);
		Main.JCHK_EMAIL_CAP.addActionListener(this);
		Main.JCHK_TARP.addActionListener(this);
		Main.JCHK_POS.addActionListener(this);
		Main.JCHK_PAL.addActionListener(this);
		Main.JCHK_PLASTICS.addActionListener(this);
		Main.JCHK_CIDAR.addActionListener(this);
		Main.JCHK_NOAC.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Main.JCHK_VA) {
			Main.HAS_UNSAVED_CHANGES = true;
		}
		
		else if (e.getSource() == Main.JCHK_TOADE) {
			Main.HAS_UNSAVED_CHANGES = true;
		}
		
		else if (e.getSource() == Main.JCHK_VDI) {
			Main.HAS_UNSAVED_CHANGES = true;
		}
		
		else if (e.getSource() == Main.JCHK_EMAIL_CAP) {
			Main.HAS_UNSAVED_CHANGES = true;
		}
		
		else if (e.getSource() == Main.JCHK_TARP) {
			Main.HAS_UNSAVED_CHANGES = true;
		}
		
		else if (e.getSource() == Main.JCHK_POS) {
			Main.HAS_UNSAVED_CHANGES = true;
			
			//pos and pal
			if (Main.JCHK_POS.isSelected()) {
				Main.JCHK_PAL.setEnabled(false);
			} else {
				Main.JCHK_PAL.setEnabled(true);
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
			} 
			else {
				Main.PRIMARY_ADDRESS.setEnabled(true);
				Main.PRIMARY_ADDRESS_L2.setEnabled(true);
				Main.PRIMARY_CITY.setEnabled(true);
				Main.PRIMARY_ZIP.setEnabled(true);
				Main.JL_ADDRESS.setEnabled(true);
				Main.JL_ADDRESS_L2.setEnabled(true);
				Main.JL_CITY.setEnabled(true);
				Main.JL_ZIP.setEnabled(true);
			}
		}
		
		else if (e.getSource() == Main.JCHK_PAL) {
			Main.HAS_UNSAVED_CHANGES = true;
			
			//pos and pal
			if (Main.JCHK_PAL.isSelected()) {
				Main.JCHK_POS.setEnabled(false);
				Main.JCHK_NOAC.setEnabled(true);
			} else {
				Main.JCHK_POS.setEnabled(true);
				Main.JCHK_NOAC.setSelected(false);
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
		}
		
		else if (e.getSource() == Main.JCHK_PLASTICS) {
			Main.HAS_UNSAVED_CHANGES = true;
		}
		
		else if (e.getSource() == Main.JCHK_CIDAR) {
			Main.HAS_UNSAVED_CHANGES = true;
		}
		
		else if (e.getSource() == Main.JCHK_NOAC) {
			Main.HAS_UNSAVED_CHANGES = true;
			
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
			} else {
				if (Main.JCHK_PAL.isSelected()) {
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
			}
		}
	}
}
