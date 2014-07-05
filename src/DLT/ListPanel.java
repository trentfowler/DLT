package DLT;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.joda.time.LocalDate;



/**
 * ListPanel class
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class ListPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = -5490951413959124676L;
	
	private JButton jbAdd = new JButton("Add");
	
	public ListPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbAdd);
		buttonPanel.add(Main.JB_REMOVE);
		
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(0, 3, 0, 3));
		JScrollPane jspList = new JScrollPane(Main.LIST);
		container.add(jspList, BorderLayout.CENTER);
		
		this.add(container, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		Main.LIST.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//right-click
		Main.LIST.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					RightClickList click = new RightClickList();
					click.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		
		//user clicked a LIST item
		Main.LIST.addListSelectionListener(new ListSelectionListener(){
			@Override public void valueChanged(ListSelectionEvent e) {
				//avoid list selection listener triggering 2 events events for mousedown and mouseup
				if (e.getValueIsAdjusting()) {
					boolean hasUnsavedChanges = Main.HAS_UNSAVED_CHANGES;
					
					//update object
					Main.SAVE_CHANGEABLE_FIELDS();
					
					//update list model
					StringBuilder sb = new StringBuilder();
					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty()) {
						sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest());
					}
					
					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty() &&
						!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
						sb.append(" / ");
					}

					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
						sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getName());
					}
					
					if (!sb.toString().isEmpty()) {
						Main.LIST_MODEL.set(Main.SELECTED_INDEX, sb.toString());
					}
					
					//update view
					if (Main.LIST.getSelectedIndex() != -1) Main.SELECTED_INDEX = Main.LIST.getSelectedIndex();
					Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
					
					Main.HAS_UNSAVED_CHANGES = hasUnsavedChanges;
				}
			}
		});
		
		//user selects a new LIST item with the arrow keys
		Main.LIST.addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(KeyEvent e) {
				boolean hasUnsavedChanges = Main.HAS_UNSAVED_CHANGES;
				
				//update selected index in LIST
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					if (Main.LIST.getSelectedIndex() < Main.LIST_MODEL.size() - 1) {
						e.consume();
						Main.LIST.setSelectedIndex(Main.LIST.getSelectedIndex() + 1);
						Main.LIST.ensureIndexIsVisible(Main.LIST.getSelectedIndex());
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					if (Main.LIST.getSelectedIndex() > 0) {
						e.consume();
						Main.LIST.setSelectedIndex(Main.LIST.getSelectedIndex() - 1);
						Main.LIST.ensureIndexIsVisible(Main.LIST.getSelectedIndex());
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
					//update object
					Main.SAVE_CHANGEABLE_FIELDS();
					
					//update list model
					StringBuilder sb = new StringBuilder();
					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty()) {
						sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest());
					}
					
					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty() &&
						!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
						sb.append(" / ");
					}

					if (!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
						sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getName());
					}
					
					if (!sb.toString().isEmpty()) {
						Main.LIST_MODEL.set(Main.SELECTED_INDEX, sb.toString());
					}
					
					//update view
					if (Main.LIST.getSelectedIndex() != -1) 
						Main.SELECTED_INDEX = Main.LIST.getSelectedIndex();
					Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
					
					Main.HAS_UNSAVED_CHANGES = hasUnsavedChanges;
				}
			}
		});
		
		jbAdd.addActionListener(this);
		Main.JB_REMOVE.addActionListener(this);
	}
	
	@Override public void actionPerformed(ActionEvent e) {
		//user clicked add
		if (e.getSource() == jbAdd) {
			//update object
			Main.SAVE_CHANGEABLE_FIELDS();
			
			//update list model
			StringBuilder sb = new StringBuilder();
			if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty()) {
				sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest());
			}
			
			if (!Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest().isEmpty() &&
				!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
				sb.append(" / ");
			}

			if (!Main.FIELDS.get(Main.SELECTED_INDEX).getName().isEmpty()) {
				sb.append(Main.FIELDS.get(Main.SELECTED_INDEX).getName());
			}
			
			if (!sb.toString().isEmpty()) {
				Main.LIST_MODEL.set(Main.SELECTED_INDEX, sb.toString());
			}
			
			//add item
			Main.FIELDS.add(new DataField());
			Main.SELECTED_INDEX = Main.FIELDS.size() - 1;
			
			//set initial status to touched and committed date to next business day
			LocalDate today = new LocalDate();
			int days = 1;
			while (days < 4) {
				if (Main.FIELDS.get(Main.SELECTED_INDEX).workingDaysBetween(today, today.plusDays(days)) == 1)
					break;
				else days++;
			}
			Main.FIELDS.get(Main.SELECTED_INDEX).setCommittedDate(today.plusDays(days));
			Main.FIELDS.get(Main.SELECTED_INDEX).setStatus(Main.STATUS_IS_TOUCHED);
			
			//add new item to case list
			Main.LIST_MODEL.addElement("NEW");
			Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
			Main.LIST.ensureIndexIsVisible(Main.SELECTED_INDEX);
			
			Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
		}
		
		//user clicked remove
		else if (e.getSource() == Main.JB_REMOVE) {
			if (Main.FIELDS.size() == 1) {
				//reset all
				Main.FIELDS.clear();
				Main.LIST_MODEL.clear();
				Main.SELECTED_INDEX = 0;
				
				//add new element
				Main.LIST_MODEL.add(Main.SELECTED_INDEX, "NEW");
				Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
				Main.FIELDS.add(new DataField());
				Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
			}
			
			if (Main.FIELDS.size() > 1) {
				//remove element
				Main.FIELDS.remove(Main.SELECTED_INDEX);
				Main.LIST_MODEL.remove(Main.SELECTED_INDEX);
				if (Main.SELECTED_INDEX == Main.FIELDS.size())
					Main.SELECTED_INDEX -= 1;
				
				//update view
				Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
				Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
			}
		}
	}
}