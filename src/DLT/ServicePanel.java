package DLT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class ServicePanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = -8451889141356208101L;

	public ServicePanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
				
		JPanel jpServiceTag = new JPanel();
		jpServiceTag.setOpaque(true);
		jpServiceTag.setBackground(Color.WHITE);
		jpServiceTag.setBorder(new EmptyBorder(0, 0, 0, 5));
		jpServiceTag.setLayout(new BorderLayout(5, 5));
		JButton jbServiceTag = new JButton("Service Tag");
		jpServiceTag.add(jbServiceTag, BorderLayout.WEST);
		jpServiceTag.add(Main.JTF_SERVICE_TAG, BorderLayout.CENTER);
		
		JPanel jpServiceRequest = new JPanel();
		jpServiceRequest.setOpaque(true);
		jpServiceRequest.setBackground(Color.WHITE);
		jpServiceRequest.setLayout(new BorderLayout(5, 5));
		JButton jbServiceRequest = new JButton("Service Request");
		jpServiceRequest.add(jbServiceRequest, BorderLayout.WEST);
		jpServiceRequest.add(Main.JTF_SERVICE_REQUEST, BorderLayout.CENTER);

		JPanel jpInsertButtons = new JPanel();
		jpInsertButtons.setBorder(new EmptyBorder(5, 0, 0, 5));
		jpInsertButtons.setLayout(new BorderLayout(5,5));
		jpInsertButtons.add(Main.JB_CDO, BorderLayout.WEST);
		jpInsertButtons.add(Main.JB_DCSID, BorderLayout.CENTER);
		jpInsertButtons.add(Main.JB_PPID, BorderLayout.EAST);
		
		JLabel jl = new JLabel(" ");
		jl.setOpaque(true);
		jl.setBackground(new Color(90, 167, 226));
		jl.setForeground(Color.WHITE);
		jl.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		JPanel jpContainer = new JPanel();
		jpContainer.setOpaque(true);
		jpContainer.setBackground(Color.WHITE);
		jpContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		jpContainer.setLayout(new GridLayout(0, 2));
		
		jpContainer.add(jpServiceTag);
		jpContainer.add(jpServiceRequest);
		jpContainer.add(jpInsertButtons);
		
		this.add(jl, BorderLayout.NORTH);
		this.add(jpContainer, BorderLayout.CENTER);
		
		//user pressed copy service tag
		jbServiceTag.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(Main.JTF_SERVICE_TAG.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
				
			}
		});
		
		//user press insert dell connect
		Main.JB_DCSID.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Main.JTA_TROUBLESHOOTING.append("\nDellConnectTSessionID#\n");
			}
		});
		
		Main.JB_PPID.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Main.JTA_TROUBLESHOOTING.append("\nPPID#\n");
			}
		});
				
		//user pressed copy service request
		jbServiceRequest.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(Main.JTF_SERVICE_REQUEST.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
		});
		
		Main.JTF_SERVICE_TAG.getDocument().addDocumentListener(this);
		Main.JTF_SERVICE_REQUEST.getDocument().addDocumentListener(this);
	}

	@Override public void changedUpdate(DocumentEvent e) {
		//flag unsaved changes
		Main.HAS_UNSAVED_CHANGES = true;
		
		//service tag color
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
	
	@Override public void insertUpdate(DocumentEvent e) {
		//flag unsaved changes
		Main.HAS_UNSAVED_CHANGES = true;
		
		//service tag color
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
	
	@Override public void removeUpdate(DocumentEvent e) {
		//flag unsaved changes
		Main.HAS_UNSAVED_CHANGES = true;
		
		//service tag color
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
}
