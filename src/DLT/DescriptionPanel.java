package DLT;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * DescriptionPanel
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class DescriptionPanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = 3763013862202090810L;

	public DescriptionPanel() {
		this.setOpaque(true);
		this.setBackground(Color.black);
		this.setBorder(new EmptyBorder(0, 0, 5, 0));
		
		this.setLayout(new BorderLayout());
		
		JLabel jlDescription = new JLabel("Description:");
		jlDescription.setOpaque(true);
		jlDescription.setBackground(new Color(80,80,80));
		jlDescription.setForeground(Color.CYAN);
		jlDescription.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		JPanel jpDescriptionContainer = new JPanel();
		jpDescriptionContainer.setOpaque(true);
		jpDescriptionContainer.setBackground(Color.black);
		jpDescriptionContainer.setLayout(new BorderLayout());
		jpDescriptionContainer.setBorder(new EmptyBorder(5, 5, 0, 5));
		jpDescriptionContainer.add(Main.JTF_DESCRIPTION, BorderLayout.CENTER);
		
		this.add(jlDescription, BorderLayout.NORTH);
		this.add(jpDescriptionContainer, BorderLayout.CENTER);
		
		Main.JTF_DESCRIPTION.getDocument().addDocumentListener(this);
		Main.JTF_DESCRIPTION.setBackground(Color.BLACK);
		Main.JTF_DESCRIPTION.setForeground(Color.GREEN);
		Main.JTF_DESCRIPTION.setCaretColor(Color.GREEN);
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