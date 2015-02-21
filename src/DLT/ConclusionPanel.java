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
 * ConclusionPanel
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class ConclusionPanel extends JPanel implements DocumentListener {
	
	private static final long serialVersionUID = -2745439282533900475L;
	
	public ConclusionPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(0, 0, 5, 0));
		
		Main.JTA_CONCLUSION.setLineWrap(true);
		Main.JTA_CONCLUSION.setWrapStyleWord(true);
		Main.JTA_CONCLUSION.setEditable(true);
				
		JPanel jpScrollPaneContainer = new JPanel();
		jpScrollPaneContainer.setOpaque(true);
		jpScrollPaneContainer.setBackground(Color.WHITE);
		jpScrollPaneContainer.setLayout(new BorderLayout());
		jpScrollPaneContainer.setBorder(new EmptyBorder(0, 5, 0, 5));
		jpScrollPaneContainer.add(new BetterScrollPane(Main.JTA_CONCLUSION), 
									BorderLayout.CENTER);
		
		JLabel jlConclusion = new JLabel("Conclusion:");
		jlConclusion.setOpaque(true);
		jlConclusion.setBackground(new Color(90, 167, 226));
		jlConclusion.setForeground(Color.WHITE);
		jlConclusion.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		this.setPreferredSize(new Dimension(0, 100));
		this.setLayout(new BorderLayout(5, 5));
		
		this.add(jlConclusion, BorderLayout.NORTH);		
		this.add(jpScrollPaneContainer, BorderLayout.CENTER);
		
		Main.JTA_CONCLUSION.getDocument().addDocumentListener(this);

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
	
