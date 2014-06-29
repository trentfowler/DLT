package DLT;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

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
		setOpaque(true);
		setBackground(Color.WHITE);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(new CheckBoxPanel());
		add(new ServicePanel());
		add(new ContactPanel());
		//add(new AssetPanel());
		add(new DescriptionPanel());
		
		/* for split between troubleshooting and symptoms
		JPanel jpContainer = new JPanel();
		jpContainer.setLayout(new GridLayout(1, 2));
		jpContainer.add(new SymptomPanel());
		jpContainer.add(new TroubleshootingPanel());
		add(jpContainer);
		*/
		
		add(new TroubleshootingPanel());
		add(new ConclusionPanel());
		add(new NotesPanel());
		add(new ButtonPanel());
	}
	
	@Override public void paintComponent(Graphics g) {
		
	}
}