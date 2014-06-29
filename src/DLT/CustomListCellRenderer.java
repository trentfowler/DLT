package DLT;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.joda.time.LocalDate;

/**
 * Determines the color of each cell in the case list according to its status.
 * 
 * @author Trent
 *
 */
public class CustomListCellRenderer extends JLabel implements ListCellRenderer {
	
	private static final long serialVersionUID = 2690029116548791087L;
	
	private JList defaultColorList = new JList();
	private Color defaultListColor = defaultColorList.getBackground();
	private Color defaultSelectorColor = defaultColorList.getSelectionBackground();
	
	public CustomListCellRenderer() {
		setOpaque(true);
	}
	
	@Override public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		setText(value.toString());
		
		if (Main.FIELDS.size() > 0) {
			int status = Main.FIELDS.get(index).getFollowUpStatus();
			if (status == Main.STATUS_IS_UNKNOWN || status == Main.STATUS_IS_CLOSED) {
				setBackground(Color.WHITE);
			}
			
			else if (status == Main.STATUS_IS_OVERDUE) {
				setBackground(new Color(255, 81, 81)); //red
			}
			
			else if (status == Main.STATUS_IS_TOUCHED) {
				setBackground(new Color(106, 255, 84)); //green
			}
			
			else if (status == Main.STATUS_IS_DUE) {
				setBackground(new Color(255, 255, 178)); //yellow
			}
					
			if (isSelected) {
				setBackground(defaultSelectorColor);
			}			
		}
		
		return this;
	}
	
}
