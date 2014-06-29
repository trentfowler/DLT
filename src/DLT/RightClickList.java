package DLT;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import org.joda.time.LocalDate;


public class RightClickList extends JPopupMenu implements ActionListener {

	private static final long serialVersionUID = -1474472275653429868L;
	
	private JMenuItem touch = new JMenuItem("Touch");
	private JMenuItem close = new JMenuItem("Close case");
	private JMenuItem open = new JMenuItem("Open case");
	private JMenuItem follow = new JMenuItem("Follow up in X days");
	private JMenuItem follow2 = new JMenuItem("Follow up in 2 days");
	private JMenuItem follow3 = new JMenuItem("Follow up in 3 days");
	private JMenuItem followOnDate = new JMenuItem("Follow up on...");
	
	public RightClickList() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		close.setOpaque(true);
		close.setBackground(Color.WHITE);
		
		open.setOpaque(true);
		open.setBackground(Color.WHITE);
		
		if (Main.FIELDS.get(Main.SELECTED_INDEX).getFollowUpStatus() == Main.STATUS_IS_CLOSED ||
			Main.FIELDS.get(Main.SELECTED_INDEX).getFollowUpStatus() == Main.STATUS_IS_UNKNOWN) {
			this.add(open);
			touch.setEnabled(false);
			follow.setEnabled(false);
			follow2.setEnabled(false);
			follow3.setEnabled(false);
			followOnDate.setEnabled(false);
		} else {
			this.add(close);
			touch.setEnabled(true);
			follow.setEnabled(true);
			follow2.setEnabled(true);
			follow3.setEnabled(true);
			followOnDate.setEnabled(true);
		}
		
		this.addSeparator();
		
		touch.setOpaque(true);
		touch.setBackground(Color.WHITE);
		this.add(touch);
		
		follow2.setOpaque(true);
		follow2.setBackground(Color.WHITE);
		this.add(follow2);
		
		follow3.setOpaque(true);
		follow3.setBackground(Color.WHITE);
		this.add(follow3);
		
		follow.setOpaque(true);
		follow.setBackground(Color.WHITE);
		this.add(follow);
		
		followOnDate.setOpaque(true);
		followOnDate.setBackground(Color.WHITE);
		//this.add(followOnDate);
		
		touch.addActionListener(this);
		open.addActionListener(this);
		close.addActionListener(this);
		follow2.addActionListener(this);
		follow3.addActionListener(this);
		follow.addActionListener(this);
		followOnDate.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LocalDate today = new LocalDate();
		if (e.getSource() == touch) {
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
					Main.STATUS_IS_TOUCHED);
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(1);
			Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today);
		}
		
		else if (e.getSource() == open) {
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
					Main.STATUS_IS_DUE);
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(0);
			Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today);
		}
		
		else if (e.getSource() == close) {
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
					Main.STATUS_IS_CLOSED);
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(0);
			Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today); //TODO
		}
				
		else if (e.getSource() == follow2) {
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
					Main.STATUS_IS_TOUCHED);
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(2);
			Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today);
		}
		
		else if (e.getSource() == follow3) {
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
					Main.STATUS_IS_TOUCHED);
			Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(3);
			Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today);
		}
		
		//...
		else if (e.getSource() == follow) {
			String sDays = JOptionPane.showInputDialog(Main.f, "How many days?");
			
			int days = -400;
			try {
				days = Integer.parseInt(sDays);
			} catch (NumberFormatException nfe) {
				days = -400;
				//TODO show dialog for invalid input
				JOptionPane.showMessageDialog(Main.f, "Invalid input!");
			}
			
			if (days == -400) {
				//do nothing
			}
			else if (days < 0) {
				Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
						Main.STATUS_IS_OVERDUE);
				Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(days);
				Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today);
			}
			
			else if (days > 0) {
				Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
						Main.STATUS_IS_TOUCHED);
				Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(days);
				Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today);
			}
			
			else if (days == 0) {
				Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpStatus(
						Main.STATUS_IS_DUE);
				Main.FIELDS.get(Main.SELECTED_INDEX).setFollowUpIn(days);
				Main.FIELDS.get(Main.SELECTED_INDEX).setDateAssignedFollowUp(today);
			}
		}
		
		else if (e.getSource() == followOnDate) {
			//TODO
		}
		
		Main.HAS_UNSAVED_CHANGES = true;
	}
}
