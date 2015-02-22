package DLT;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * InfoPanel class
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class InfoPanel extends JPanel {
	
	private static final long serialVersionUID = 8395942318689747627L;

	public InfoPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setOpaque(true);
		top.setBackground(Color.WHITE);
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		top.add(new CheckBoxPanel());
		top.add(new ServicePanel());
		top.add(new ContactPanel());
		top.add(new DescriptionPanel());
				
		JPanel bottom = new JPanel();
		bottom.setOpaque(true);
		bottom.setBackground(Color.WHITE);
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		bottom.add(new ConclusionPanel());
		bottom.add(new NotesPanel());
		bottom.add(new ButtonPanel());
		
		JPanel flex = new JPanel();
		flex.setOpaque(true);
		flex.setBackground(Color.WHITE);
		flex.setLayout(new BorderLayout());
		flex.add(new TroubleshootingPanel(), BorderLayout.CENTER);
		flex.add(bottom, BorderLayout.SOUTH);
		
		this.add(top, BorderLayout.NORTH);
		this.add(flex, BorderLayout.CENTER);
	}
}	
