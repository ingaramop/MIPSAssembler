/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package controller;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import fileHandler.BufferedReaderCountable;
import fileHandler.ParsingMethods;
import view.ConfigDialog;
import view.MainWindow;


public class Controller {
	
	private MainWindow mainWindow;
	private ConfigProperties confProp;
	


	public MainWindow getMainWindow() {
		return mainWindow;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Controller controller = new Controller();
		controller.startProgram();
	}

    public Controller(){

    }

    public void startProgram() throws FileNotFoundException, IOException{
   	
    	mainWindow = new MainWindow(this);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					mainWindow.setVisible(true);
			}
		});   
    	confProp = new ConfigProperties();
    }
    
    public void openConfigDialog() throws FileNotFoundException, IOException{
    	ConfigDialog dialog;
    	dialog = new ConfigDialog(confProp, mainWindow);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

    }
    
    public void openFileChooser(){
				
		JFileChooser fC = new JFileChooser(confProp.getInUseDir());
		fC.setPreferredSize(new Dimension(800,500));
		fC.setDialogTitle("Abrir");
		
		int returnVal = fC.showOpenDialog(mainWindow);	
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fC.getSelectedFile();		
			loadfile(file);					
		}
		if (returnVal == JFileChooser.CANCEL_OPTION) {
			//do nothing					
		}
    }
    
    public void saveFileChooser(){
    	JFileChooser fC = new JFileChooser(confProp.getInUseDir());
    	fC.setPreferredSize(new Dimension(800,500));
    	fC.setDialogTitle("Guardar");
    	if (fC.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
    		String content = mainWindow.getCodeText();
    		File file = fC.getSelectedFile();
    	    // save to file
	    	try{
	    		FileWriter fW = new FileWriter(file.getPath());
	    		fW.write(content);
	    		fW.flush();
	    		fW.close();
	    	}
	    	catch(Exception E2){
	    		JOptionPane.showMessageDialog(null, E2.getMessage());
	    	}
    	}
    }
    
    public void saveAndEnsamble(){
    	JFileChooser fC = new JFileChooser(confProp.getInUseDir());
    	fC.setPreferredSize(new Dimension(800,500));
    	fC.setDialogTitle("Guardar");
    	if (fC.showSaveDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
    		String content = mainWindow.getCodeText();
    		File file = fC.getSelectedFile();
    	    // save to file
	    	try{
	    		FileWriter fW = new FileWriter(file.getPath());
	    		fW.write(content);
	    		fW.flush();
	    		fW.close();
	    	}
	    	catch(Exception E2){
	    		JOptionPane.showMessageDialog(null, "Error al guardar código"+E2.getMessage());
	    		return;
	    	}
	    	//Crear y guardar el archivo ensamblado
	    	try {
				content = ensamblar(file);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
				return;
			}
	    	try{
	    		FileWriter fW = new FileWriter(file.getPath()+".COE");
	    		fW.write(content);
	    		fW.flush();
	    		fW.close();
	    		mainWindow.setOutputPaneText(content);
	    	}
	    	catch(Exception E2){
	    		JOptionPane.showMessageDialog(null, "Error al guardar .coe"+E2.getMessage());
	    		return;
	    	}
	    	
    	}
    }
    private String ensamblar(File file) throws Exception {
    	String binario = "";
    	BufferedReaderCountable buff = new BufferedReaderCountable(new FileReader(file), 1024*1024);
        while ((buff.readLine()) != null){
        	try {
				binario = binario + ParsingMethods.parseInstruction(buff.getCurrentLineText(), buff.getCurrentLineNum());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception("Linea: "+ buff.getCurrentLineNum());
			}
            }
            
    	
    	return binario;
    }

    public void loadfile(File file) {
    	try {
			//sourceFile = new LogFile(file, this);	
						   
			mainWindow.displayFile(file);  
					
			//mainWindow.setLogFileComponent((Component)sourceFile.getLogFilePane());

			
			// Once the parse is successful, we change the latestDir variable in the configuration file
			
			confProp.setLatestDir(file.getParent());
			confProp.savePropertiesToDisk();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    public void newFile() {
    	try {
						   
			mainWindow.displayEmptyFile();  
											
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

}
