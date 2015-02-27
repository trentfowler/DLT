package DLT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * TroubleshootingPanel
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class TroubleshootingPanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = -6403382972795542666L;

	public TroubleshootingPanel() {
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		this.setBorder(new EmptyBorder(0, 0, 5, 0));
		
		Main.JTA_TROUBLESHOOTING.setLineWrap(true);
		Main.JTA_TROUBLESHOOTING.setWrapStyleWord(true);
		Main.JTA_TROUBLESHOOTING.setEditable(true);
		Main.JTA_TROUBLESHOOTING.setBackground(Color.BLACK);
		Main.JTA_TROUBLESHOOTING.setForeground(Color.GREEN);
		Main.JTA_TROUBLESHOOTING.setCaretColor(Color.GREEN);
				
		JPanel jpScrollPaneContainer = new JPanel();
		jpScrollPaneContainer.setOpaque(true);
		jpScrollPaneContainer.setBackground(Color.black);
		
		
		jpScrollPaneContainer.setLayout(new BorderLayout());
		jpScrollPaneContainer.setBorder(new EmptyBorder(5, 5, 0, 5));
		jpScrollPaneContainer.add(new BetterScrollPane(Main.JTA_TROUBLESHOOTING), 
									BorderLayout.CENTER);
		
		JLabel jlTroubleshooting = new JLabel("Troubleshooting:");
		jlTroubleshooting.setOpaque(true);
		jlTroubleshooting.setBackground(new Color(80,80,80));
		jlTroubleshooting.setForeground(Color.CYAN);
		jlTroubleshooting.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		this.setPreferredSize(new Dimension(0, 200));
		this.setLayout(new BorderLayout());
		
		this.add(jlTroubleshooting, BorderLayout.NORTH);		
		this.add(jpScrollPaneContainer, BorderLayout.CENTER);
		
		Main.JTA_TROUBLESHOOTING.getDocument().addDocumentListener(this);

	}

	@Override public void changedUpdate(DocumentEvent e) {
		Main.HAS_UNSAVED_CHANGES = true;
	}

	@Override public void insertUpdate(DocumentEvent e) {
		Main.HAS_UNSAVED_CHANGES = true;
	}

	@Override public void removeUpdate(DocumentEvent e) {
		Main.HAS_UNSAVED_CHANGES = true;
	}
}
