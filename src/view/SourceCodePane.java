/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.Document;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;

public class SourceCodePane extends JPanel {
	private JTextField findField;
	private JButton searchButton;
	private CustomizedTextPane customizedFilePane;
	private JSplitPane splitPane;
	

	/**
	 * Create the panel.
	 * @param logFile 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	

	public SourceCodePane(File file) throws FileNotFoundException, IOException {
		customizedFilePane = new CustomizedTextPane(file);
		splitPane = new JSplitPane();
		splitPane.setRightComponent(customizedFilePane.getScrollPane());
		initialize();
	}
	public SourceCodePane() throws FileNotFoundException, IOException {
		customizedFilePane = new CustomizedTextPane();
		splitPane = new JSplitPane();
		splitPane.setRightComponent(customizedFilePane.getScrollPane());	
		initialize();
	}
		 
	
		
	public void initialize(){	
		setMinimumSize(new Dimension(500, 500));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		splitPane.setMinimumSize(new Dimension(500, 500));
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane);
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(32767, 20));
		panel.setMinimumSize(new Dimension(10, 20));
		panel.setPreferredSize(new Dimension(10, 20));
		splitPane.setLeftComponent(panel);
		
		findField = new JTextField();
		findField.setColumns(10);
		findField.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
            	String find = findField.getText().toLowerCase();
                customizedFilePane.searchString(find);
                findField.requestFocusInWindow();
            }});
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the text to find...convert it to lower case for easier comparison
                String find = findField.getText().toLowerCase();
                customizedFilePane.searchString(find);
            }
        });

    
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(255, Short.MAX_VALUE)
					.addComponent(findField, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addComponent(findField, GroupLayout.PREFERRED_SIZE, 19, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
	
	
	public void centerLineInScrollPane(){
		customizedFilePane.centerLineInScrollPane();
	}
	public void goToLine(int line){		
		customizedFilePane.goToLine(line);	
	}
	public String getCode() {
		// TODO Auto-generated method stub
		return customizedFilePane.getCode();
	};
}
