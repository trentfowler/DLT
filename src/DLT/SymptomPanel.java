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
 * SymptomPanel class
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class SymptomPanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = 640391127491133496L;

	public SymptomPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(0, 0, 5, 0));
		
		Main.JTA_SYMPTOMS.setCaretPosition(Main.JTA_SYMPTOMS.getDocument().getLength());
		Main.JTA_SYMPTOMS.setLineWrap(true);
		Main.JTA_SYMPTOMS.setWrapStyleWord(true);
		Main.JTA_SYMPTOMS.setEditable(true);
		
		JPanel jpScrollPaneContainer = new JPanel();
		jpScrollPaneContainer.setLayout(new BorderLayout());
		jpScrollPaneContainer.setOpaque(true);
		jpScrollPaneContainer.setBackground(Color.WHITE);
		jpScrollPaneContainer.setBorder(new EmptyBorder(5, 5, 0, 5));
		jpScrollPaneContainer.add(new BetterScrollPane(Main.JTA_SYMPTOMS), 
									BorderLayout.CENTER);
		
		JLabel jlSymptoms = new JLabel("Symptoms:");
		jlSymptoms.setOpaque(true);
		jlSymptoms.setBackground(new Color(90, 167, 226));
		jlSymptoms.setForeground(Color.WHITE);
		jlSymptoms.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		this.setPreferredSize(new Dimension(0, 200));
		this.setLayout(new BorderLayout());
		
		this.add(jlSymptoms, BorderLayout.NORTH);
		this.add(jpScrollPaneContainer, BorderLayout.CENTER);
		
		Main.JTA_SYMPTOMS.getDocument().addDocumentListener(this);
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