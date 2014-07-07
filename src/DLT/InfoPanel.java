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
		
		JPanel main = new JPanel();
		main.setOpaque(true);
		main.setBackground(Color.WHITE);
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.add(new CheckBoxPanel());
		main.add(new ServicePanel());
		main.add(new ContactPanel());
		main.add(new DescriptionPanel());
		main.add(new TroubleshootingPanel());
		main.add(new ConclusionPanel());
		
		JPanel flex = new JPanel();
		flex.setOpaque(true);
		flex.setBackground(Color.WHITE);
		flex.setLayout(new BorderLayout());
		flex.add(new NotesPanel(), BorderLayout.CENTER);
		flex.add(new ButtonPanel(), BorderLayout.SOUTH);
		
		this.add(main, BorderLayout.NORTH);
		this.add(flex, BorderLayout.CENTER);
	}
}