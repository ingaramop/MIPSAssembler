/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ConfigParsePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ConfigParsePanel() {
		
		JRadioButton rdbtnGetExceptions = new JRadioButton("Parse for exceptions");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnGetExceptions)
					.addContainerGap(319, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnGetExceptions)
					.addContainerGap(270, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
