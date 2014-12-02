package DLT;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ButtonPanel class
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class ButtonPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -2498243794148353449L;
	
	private JButton jbGenerateDescription = new JButton ("Copy Description");	
	private JButton jbGenerateTroubleshooting = new JButton("Copy Troubleshooting");	
	private JButton jbGenerateConclusion = new JButton("Copy Conclusion");
	private JButton jbGenerateSummary = new JButton("Generate Summary");
	
	public ButtonPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		
		this.add(jbGenerateDescription);
		this.add(jbGenerateTroubleshooting);
		this.add(jbGenerateConclusion);
		//this.add(jbGenerateSummary);
		
		jbGenerateTroubleshooting.addActionListener(this);
		jbGenerateConclusion.addActionListener(this);
		jbGenerateDescription.addActionListener(this);
		jbGenerateSummary.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//if generate troubleshooting button is pressed --> format output and copy to clipboard
		if (e.getSource() == jbGenerateTroubleshooting) {
			
			//build troubleshooting string based on field data
			StringBuilder sb = new StringBuilder("***\n");
			if (Main.JCHK_VA.isSelected()) sb.append(" VA \n");
			if (Main.JCHK_TOADE.isSelected()) sb.append(" TOADE \n");
			if (Main.JCHK_EMAIL_CAP.isSelected()) sb.append(" EMAILCAP \n");
			if (Main.JCHK_VDI.isSelected()) sb.append(" VDI \n");
			if (Main.JCHK_TARP.isSelected()) sb.append(" TARP \n");
			if (Main.JCHK_POS.isSelected()) sb.append(" POS \n");
			if (Main.JCHK_PAL.isSelected()) sb.append(" OST DPS \n");
			if (Main.JCHK_PLASTICS.isSelected()) sb.append(" CHECK PLASTICS \n");
			if (Main.JCHK_CIDAR.isSelected()) sb.append(" CIDAR EXPLAINED \n");
			sb.append("***\n");
			if (!Main.JTF_SERVICE_TAG.getText().isEmpty()) {
				sb.append("ST:" + Main.JTF_SERVICE_TAG.getText() + " ");
			}
			if (!Main.JTF_SERVICE_TAG.getText().isEmpty() &&
				!Main.JTF_SERVICE_REQUEST.getText().isEmpty()) {
				sb.append(" // ");
			}
			if (!Main.JTF_SERVICE_REQUEST.getText().isEmpty()) {
				sb.append(" SR#" + Main.JTF_SERVICE_REQUEST.getText() + " ");
			}
			if (!Main.JTA_TROUBLESHOOTING.getText().isEmpty()) {
				sb.append("\n\n" + Main.JTA_TROUBLESHOOTING.getText() + "\n\n***NO OTHER ISSUES RAISED***");
			}
			if (Main.JCHK_NOC.isSelected()) sb.append("\n***NO ALT CONTACT*** ");
			
			Object priority = Main.JB_CDO.getSelectedItem();
			sb.append("\n***"+ priority +"***\n");
						
			//clean up -- no consecutive space characters
			for (int i = 0; i < sb.length() - 1; i++) {
				if (sb.charAt(i) == ' ' && sb.charAt(i + 1) == ' ') sb.deleteCharAt(i + 1);
			}
						
			//copy to clipboard
			StringSelection stringSelection = new StringSelection(sb.toString());
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		} 
		
		//if generate conclusion button is pressed --> copy to clipboard
		else if (e.getSource() == jbGenerateConclusion) {
			
			StringSelection stringSelection = 
					new StringSelection(Main.JTA_CONCLUSION.getText());
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}
		
		//if generate description button is pressed --> copy to clipboard
		else if (e.getSource() == jbGenerateDescription) {
			
			//build description string based on field data
			StringBuilder sb = new StringBuilder();

			/*
			if (!Main.JTF_SERVICE_TAG.getText().isEmpty()) {
				sb.append("ST:" + Main.JTF_SERVICE_TAG.getText() + " // ");
			}
			if (!Main.JTF_SERVICE_REQUEST.getText().isEmpty()) {
				sb.append("SR#" + Main.JTF_SERVICE_REQUEST.getText() + " // ");
			}
			*/

			

			if (!Main.JTF_DESCRIPTION.getText().isEmpty()) {
				sb.append(Main.JTF_DESCRIPTION.getText());
			}
			
			//copy to clipboard
			StringSelection stringSelection = new StringSelection(sb.toString());
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
			
		}
		
		else if (e.getSource() == jbGenerateSummary) {
			
			//get input from file
			StringBuilder sb = new StringBuilder();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						"./emails/summary.txt"));
				String line = null;
				while((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				//TODO
			}
			
			//substitute %
			
			String s = sb.toString();
			if (!Main.JTF_SERVICE_TAG.getText().isEmpty()) {
				s = s.replace("%st%", Main.JTF_SERVICE_TAG.getText());
			}
			if (!Main.JTF_SERVICE_REQUEST.getText().isEmpty()) {
				s = s.replace("%sr%", Main.JTF_SERVICE_REQUEST.getText());
			}
			if (!Main.JTF_NAME.getText().isEmpty()) {
				String[] names = Main.JTF_NAME.getText().split("\\s+"); //split at white space
				s = s.replace("%fn%", names[0]);
			}
			if (!Main.JTF_DESCRIPTION.getText().isEmpty()) {
				s = s.replace("%id%", Main.JTF_DESCRIPTION.getText());
			}
			
			//copy to clipboard
			StringSelection stringSelection = new StringSelection(s);
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
		}
	}
}
