package DLT;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * AssetPanel class
 * 
 * ...
 * 
 * @author Trent
 *
 */
public class AssetPanel extends JPanel {//implements DocumentListener {
	
	private static final long serialVersionUID = 7253634567240634528L;
	
	public AssetPanel() {
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.setBorder(new EmptyBorder(0, 0, 5, 0));
		this.setLayout(new BorderLayout());
		
		JPanel jpModel = new JPanel();
		jpModel.setOpaque(true);
		jpModel.setBackground(Color.WHITE);
		jpModel.setBorder(new EmptyBorder(0, 5, 0, 5));
		jpModel.setLayout(new BorderLayout());
		jpModel.add(new JLabel("Model "), BorderLayout.WEST);
		jpModel.add(Main.JTF_MODEL, BorderLayout.CENTER);
		
		JPanel jpOS = new JPanel();
		jpOS.setOpaque(true);
		jpOS.setBackground(Color.WHITE);
		jpOS.setBorder(new EmptyBorder(0, 0, 0, 5));
		jpOS.setLayout(new BorderLayout());
		jpOS.add(new JLabel("Operating System "), BorderLayout.WEST);
		jpOS.add(Main.JTF_OS, BorderLayout.CENTER);
		
		JLabel jlAsset = new JLabel("Asset:");
		jlAsset.setOpaque(true);
		jlAsset.setBackground(new Color(90, 167, 226));
		jlAsset.setForeground(Color.WHITE);
		jlAsset.setBorder(new EmptyBorder(3, 5, 3, 5));
		
		JPanel jpContainer = new JPanel();
		jpContainer.setOpaque(true);
		jpContainer.setBackground(Color.WHITE);
		jpContainer.setBorder(new EmptyBorder(5, 0, 0, 0));
		jpContainer.setLayout(new GridLayout(1, 2));
		jpContainer.add(jpModel);
		jpContainer.add(jpOS);
		
		this.add(jlAsset, BorderLayout.NORTH);
		this.add(jpContainer, BorderLayout.CENTER);
		
		//Main.JTF_SERVICE_TAG.getDocument().addDocumentListener(this);
	}
	/*
	@Override public void changedUpdate(DocumentEvent e) {
		//set state
		Main.HAS_UNSAVED_CHANGES = true;
		
		//service tag color
		if (Main.JTF_SERVICE_TAG.getText().length() < 7) {
			Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		} else {
			int count = 0;
			for (int i = 0; i < Main.JTF_SERVICE_TAG.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_SERVICE_TAG.getText().charAt(i)) ||
					Character.isLetter(Main.JTF_SERVICE_TAG.getText().charAt(i))) {
					count++;
				}
			}
			if (count % 7 == 0) {
				Main.JTF_SERVICE_TAG.setBackground(new Color(106, 255, 84)); //green
			} else Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		}
	}

	@Override public void insertUpdate(DocumentEvent e) {
		//set state
		Main.HAS_UNSAVED_CHANGES = true;
		
		//service tag color
		if (Main.JTF_SERVICE_TAG.getText().length() < 7) {
			Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		} else {
			int count = 0;
			for (int i = 0; i < Main.JTF_SERVICE_TAG.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_SERVICE_TAG.getText().charAt(i)) ||
					Character.isLetter(Main.JTF_SERVICE_TAG.getText().charAt(i))) {
					count++;
				}
			}
			if (count % 7 == 0) {
				Main.JTF_SERVICE_TAG.setBackground(new Color(106, 255, 84)); //green
			} else Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		}
	}

	@Override public void removeUpdate(DocumentEvent e) {
		//set state
		Main.HAS_UNSAVED_CHANGES = true;
		
		//service tag color
		if (Main.JTF_SERVICE_TAG.getText().length() < 7) {
			Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		} else {
			int count = 0;
			for (int i = 0; i < Main.JTF_SERVICE_TAG.getText().length(); i++) {
				if (Character.isDigit(Main.JTF_SERVICE_TAG.getText().charAt(i)) ||
					Character.isLetter(Main.JTF_SERVICE_TAG.getText().charAt(i))) {
					count++;
				}
			}
			if (count % 7 == 0) {
				Main.JTF_SERVICE_TAG.setBackground(new Color(106, 255, 84)); //green
			} else Main.JTF_SERVICE_TAG.setBackground(new Color(255, 81, 81)); //red
		}
	}
	*/
}
