package DLT;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
	private JButton jbSort = new JButton("Sort");
	
	public ListPanel() {
		this.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(jbAdd);
		buttonPanel.add(Main.JB_REMOVE);
		buttonPanel.add(jbSort);
		
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(new EmptyBorder(0, 3, 0, 3));
		JScrollPane jspList = new JScrollPane(Main.LIST);
		Dimension d = Main.LIST.getPreferredSize();
		d.width = 225;
		jspList.setPreferredSize(d);
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
		jbSort.addActionListener(this);
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
		
		//user clicked sort
		else if (e.getSource() == jbSort) {
			
			String selectedIndexSR = Main.FIELDS.get(Main.SELECTED_INDEX).getServiceRequest();
			
			//sort by committed date
			int noSwapCounter = 0;
			while (noSwapCounter < 2) {
				boolean didSwap = false;
				for (int i = 0; i < Main.FIELDS.size() - 1; i++) {
					if (Main.FIELDS.get(i).getCommittedDate().isAfter(
							Main.FIELDS.get(i + 1).getCommittedDate())) {
						DataField temp = new DataField(Main.FIELDS.get(i));
						Main.FIELDS.set(i, new DataField(Main.FIELDS.get(i + 1)));
						Main.FIELDS.set(i + 1, temp);
						didSwap = true;
						break;
					}
				}
				if (didSwap == false) {
					noSwapCounter++;
				}
			}
			
			//bubble down closed cases
			noSwapCounter = 0;
			while (noSwapCounter < 2) {
				boolean didSwap = false;
				for (int i = 0; i < Main.FIELDS.size() - 1; i++) {
					if ((Main.FIELDS.get(i).getStatus() == Main.STATUS_IS_CLOSED) && 
						(Main.FIELDS.get(i + 1).getStatus() != Main.STATUS_IS_CLOSED)) {
						DataField temp = new DataField(Main.FIELDS.get(i));
						Main.FIELDS.set(i, new DataField(Main.FIELDS.get(i + 1)));
						Main.FIELDS.set(i + 1, temp);
						didSwap = true;
					}
				}
				if (didSwap == false) {
					noSwapCounter++;
				}
			}
			
			//then sort by case age
			noSwapCounter = 0;
			while (noSwapCounter < 2) {
				boolean didSwap = false;
				for (int i = 0; i < Main.FIELDS.size() - 1; i++) {
					if (Main.FIELDS.get(i).getCommittedDate().equals(
							Main.FIELDS.get(i + 1).getCommittedDate())) {
						if (Main.FIELDS.get(i).getOpenedDate().isAfter(
								Main.FIELDS.get(i + 1).getOpenedDate())) {
							DataField temp = new DataField(Main.FIELDS.get(i));
							Main.FIELDS.set(i, new DataField(Main.FIELDS.get(i + 1)));
							Main.FIELDS.set(i + 1, temp);
							didSwap = true;
							break;
						}
					}
				}
				if (didSwap == false) {
					noSwapCounter++;
				}
			}
			
			//update list model
			for (int i = 0; i < Main.FIELDS.size(); i++) {
				StringBuilder sb = new StringBuilder();
				if (!Main.FIELDS.get(i).getServiceRequest().isEmpty()) {
					sb.append(Main.FIELDS.get(i).getServiceRequest());
				}
				
				if (!Main.FIELDS.get(i).getServiceRequest().isEmpty() &&
						!Main.FIELDS.get(i).getName().isEmpty()) {
					 sb.append(" / ");
				}
				
				if (!Main.FIELDS.get(i).getName().isEmpty()) {
					sb.append(Main.FIELDS.get(i).getName());
				}
				
				if (!sb.toString().isEmpty()) {
					Main.LIST_MODEL.set(i, sb.toString());
				} 
				else Main.LIST_MODEL.set(i, "NEW");
			}
			
			//set selected index
			if (!selectedIndexSR.isEmpty()) {
				for (int i = 0; i < Main.FIELDS.size(); i++) {
					if (Main.FIELDS.get(i).getServiceRequest().equals(selectedIndexSR)) {
						Main.SELECTED_INDEX = i;
						break;
					}
				}
			}
			Main.LIST.setSelectedIndex(Main.SELECTED_INDEX);
			Main.SET_CHANGEABLE_FIELDS(Main.SELECTED_INDEX);
			
			Main.HAS_UNSAVED_CHANGES = true;
		}
	}
}