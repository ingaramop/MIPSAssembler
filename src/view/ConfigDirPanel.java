/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.TextField;

import javax.swing.JTextField;

import controller.ConfigProperties;

public class ConfigDirPanel extends JPanel {
	private JRadioButton rdbtnDefaultDir;
	private JRadioButton rdbtnLatestDir;
	private JTextField textFieldDefaultDir;
	private JTextField textFieldLatestDir;
	private ConfigProperties confProp;

	/**
	 * Create the panel.
	 * @param confProp 
	 */
	public ConfigDirPanel(ConfigProperties confProp) {
		this.confProp = confProp;
		
		ButtonGroup bg = new ButtonGroup();
		
		//radio button default dir
		rdbtnDefaultDir = new JRadioButton("Use default directory");
		rdbtnDefaultDir.setSelected(!confProp.getUseLatestDir());
		bg.add(rdbtnDefaultDir);
		//jtext field default dir
		textFieldDefaultDir = new JTextField();
		textFieldDefaultDir.setColumns(10);
		textFieldDefaultDir.setText(confProp.getDefaultDir());
		//radio button latest dir
		rdbtnLatestDir = new JRadioButton("Use latest directory");
		rdbtnLatestDir.setSelected(confProp.getUseLatestDir());
		bg.add(rdbtnLatestDir);
		//jtext field latest dir
		textFieldLatestDir = new JTextField();
		textFieldLatestDir.setColumns(10);
		textFieldLatestDir.setText(confProp.getLatestDir());
		textFieldLatestDir.setEditable(false);
		

		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(rdbtnLatestDir, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnDefaultDir, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldDefaultDir, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
						.addComponent(textFieldLatestDir, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnDefaultDir)
						.addComponent(textFieldDefaultDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnLatestDir)
						.addComponent(textFieldLatestDir, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(180, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	

	public void updateProperties() {
		confProp.setDefaultDir(textFieldDefaultDir.getText());
		confProp.setLatestDir(textFieldLatestDir.getText());
		confProp.setUseLatestDir(String.valueOf(rdbtnLatestDir.isSelected()));
		
	}
}
