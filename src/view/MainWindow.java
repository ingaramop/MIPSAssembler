/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;
//

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import controller.Controller;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.BorderLayout;

public class MainWindow extends JFrame {
	//Harcoded image paths.
	public static final String LUKE_NORMAL_IMAGE_RELATIVE_PATH = "/resources/Luke Logwalker.png";
	
	private Controller controller;

	//top menu bar
	JMenuBar menuBar;
	JMenu mnNewMenu;
	JMenuItem mntmOptions;
	JMenuItem mntmOpenFile;
	JMenuItem mntmNewFile;
	JMenuItem mntmSaveFile;
	JMenuItem mntmEnsamblar;
	JMenuItem mntmAbout;
	private JSplitPane splitPane;
	SourceCodePane sourceCodePane;
	JTextPane outputPane = new JTextPane();


	public MainWindow(final Controller controller) throws FileNotFoundException, IOException {
		this.controller = controller;
		
		//window configuration
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource(LUKE_NORMAL_IMAGE_RELATIVE_PATH)));
		setTitle("Filewalker");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 533);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		
		//code for top menu bar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		//add new file on menu
		mntmNewFile = new JMenuItem("New File");
		mntmNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				controller.newFile();
			}
		});
		mnNewMenu.add(mntmNewFile);
				
		//add open file on menu
		mntmOpenFile = new JMenuItem("Open File");
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				controller.openFileChooser();
			}
		});
		mnNewMenu.add(mntmOpenFile);
		
		//add save file on menu
		mntmSaveFile = new JMenuItem("Save As");
		mntmSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(sourceCodePane == null ){
					String message = "Primero abra un archivo fuente.\n";				
					MsgBox.info(message, "Error");
				}
				else{
					controller.saveFileChooser();
				}
			}
		});
		mnNewMenu.add(mntmSaveFile);
		
		//add Ensamblar on menu
		mntmEnsamblar = new JMenuItem("Guardar y Ensamblar");
		mntmEnsamblar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(sourceCodePane == null ){
					String message = "Primero abra un archivo fuente.\n";				
					MsgBox.info(message, "Error");
					
				}
				else{
					controller.saveAndEnsamble();
				}		
			}
		});
		mnNewMenu.add(mntmEnsamblar);
		
		//add options on menu
		mntmOptions = new JMenuItem("Options");
		mntmOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.print("entered action");
				try {

					controller.openConfigDialog();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmOptions);
		
		
		mntmAbout = new JMenuItem("About...");
		mnNewMenu.add(mntmAbout);
		
		splitPane = new JSplitPane();
		splitPane.setLeftComponent(new JPanel());
		splitPane.setRightComponent(outputPane);
		outputPane.setEditable(false);
		splitPane.setResizeWeight(.5d);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = "Filewalker v1.1 BETA (Java 1.6)\n"+
						 "Author: Pablo Ingaramo\n"+
						 "Release date: 30/09/2015\n"+
						 "Herramienta de parseo y ensamblado de instrucciones MIPS IV \n"
						 + "Materia: Arquitectura de computadoras FCEFyN UNC 2015";
				
				MsgBox.info(message, "About");
			}
		});
		
		//initializing main panel				
	}
	
	public void displayFile(File file) throws FileNotFoundException, IOException{
		sourceCodePane = new SourceCodePane(file);
		splitPane.setLeftComponent(sourceCodePane);
	}

	public void displayEmptyFile() throws FileNotFoundException, IOException {
		sourceCodePane = new SourceCodePane();
		splitPane.setLeftComponent(sourceCodePane);
		
	}

	public String getCodeText() {
		// TODO Auto-generated method stub
		return sourceCodePane.getCode();
	}
	
	public void setOutputPaneText(String text){
		outputPane.setText(text);
	}
	
		
	
   
}
