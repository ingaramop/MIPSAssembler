/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import controller.ConfigProperties;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class ConfigDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private ConfigProperties confProp;
	private ConfigDirPanel configDirPanel;


	/**
	 * Create the dialog.
	 */
	public ConfigDialog(ConfigProperties confProp, MainWindow win) {
		super(win);
		setAlwaysOnTop(true);
		
		this.confProp=confProp;
		
		configDirPanel= new ConfigDirPanel(confProp);
		
		
		setBounds(100, 100, 535, 458);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane);
			tabbedPane.addTab("Directories", null, configDirPanel,"Does nothing");
			//tabbedPane.addTab("Program Config", null, config,"Does nothing");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							saveProperties();
							dispose();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}


				});
				okButton.setActionCommand("Apply");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void saveProperties() throws IOException {
		this.configDirPanel.updateProperties();
		
		confProp.savePropertiesToDisk();
		
	}
}
