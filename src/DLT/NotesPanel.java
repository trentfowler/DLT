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
 * NotesPanel
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class NotesPanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = -234554954643544382L;

	public NotesPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		
		Main.JTA_NOTES.setLineWrap(true);
		Main.JTA_NOTES.setWrapStyleWord(true);
		Main.JTA_NOTES.setEditable(true);
		
		JPanel jpScrollPaneContainer = new JPanel();
		jpScrollPaneContainer.setOpaque(true);
		jpScrollPaneContainer.setBackground(Color.WHITE);
		jpScrollPaneContainer.setLayout(new BorderLayout());
		jpScrollPaneContainer.setBorder(new EmptyBorder(5, 5, 0, 5));
		jpScrollPaneContainer.add(new BetterScrollPane(Main.JTA_NOTES), 
									BorderLayout.CENTER);
		
		JLabel jlNotes = new JLabel("Notes:");
		jlNotes.setOpaque(true);
		jlNotes.setBackground(new Color(90, 167, 226));
		jlNotes.setForeground(Color.WHITE);
		jlNotes.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		this.setPreferredSize(new Dimension(0, 200));
		this.setLayout(new BorderLayout());
		
		this.add(jlNotes, BorderLayout.NORTH);		
		this.add(jpScrollPaneContainer, BorderLayout.CENTER);
		
		Main.JTA_NOTES.getDocument().addDocumentListener(this);
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
	
