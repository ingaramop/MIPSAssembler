/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;


import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.Utilities;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class CustomizedTextPane {	
	private LinePainter painter;
	private JTextPane logTextPane;
	private JScrollPane logScrollPane;
	private int pos = 0;
	private TextLineNumber tln;

	
	public CustomizedTextPane(File file) throws FileNotFoundException, IOException{
		
		logTextPane = new JTextPane();
        logTextPane.setEditorKit(new WrapEditorKit());
        logTextPane.read(new FileReader(file),null);
		painter = new LinePainter(logTextPane);
		logTextPane.setEditable(true);
		logScrollPane = new JScrollPane(logTextPane);
		tln = new TextLineNumber(logTextPane);
		tln.setMinimumDisplayDigits(6);
		logScrollPane.setRowHeaderView(tln);		
		
	}
	public CustomizedTextPane() throws FileNotFoundException, IOException{
		
		logTextPane = new JTextPane();
        logTextPane.setEditorKit(new WrapEditorKit());
		painter = new LinePainter(logTextPane);
		logTextPane.setEditable(true);
		logScrollPane = new JScrollPane(logTextPane);
		tln = new TextLineNumber(logTextPane);
		tln.setMinimumDisplayDigits(6);
		logScrollPane.setRowHeaderView(tln);		
		
	}
	
	 	
	public void centerLineInScrollPane(){
		Container container = SwingUtilities.getAncestorOfClass(JViewport.class, logTextPane);

		if (container == null) return;
		try
		{
			Rectangle r = logTextPane.modelToView(logTextPane.getCaretPosition());
			JViewport viewport = (JViewport)container;
			int extentHeight = viewport.getExtentSize().height;
			int viewHeight = viewport.getViewSize().height;

			int y = Math.max(0, r.y - (extentHeight / 2));
			y = Math.min(y, viewHeight - extentHeight);

			viewport.setViewPosition(new Point(0, y));
		}
		catch(BadLocationException ble) {}
	}

	public void searchString(String find){
		pos = logTextPane.getCaretPosition()+1;
		// Focus the text area, otherwise the highlighting won't show up
		logTextPane.requestFocusInWindow();
		// Make sure we have a valid search term
		if (find != null && find.length() > 0) {
			Document document = logTextPane.getDocument();
			int findLength = find.length();
			try {
				boolean found = false;
				// Reset the search position if we're at the end of the document
				if (pos + findLength > document.getLength()) {
					pos = 0;
				}
				// While we haven't reached the end...
				// "<=" Correction
				while (pos + findLength <= document.getLength()) {
					// Extract the text from the docuemnt
					String match = document.getText(pos, findLength).toLowerCase();
					// Check to see if it matches or request
					if (match.equals(find)) {
						found = true;
						break;
					}
					pos++;
				}

				// Did we find something...
				if (found) {
					// Get the rectangle of the where the text would be visible...
					Rectangle viewRect = logTextPane.modelToView(pos);
					// Scroll to make the rectangle visible
					logTextPane.scrollRectToVisible(viewRect);
					// Highlight the text
					logTextPane.setCaretPosition(pos + findLength);
					logTextPane.moveCaretPosition(pos);
					//Center text on screen
					centerLineInScrollPane();					
					// Move the search position beyond the current match			
					pos += findLength;					
				}

			} catch (Exception exp) {
				exp.printStackTrace();
			}

		}
	}
	
	
	public Component getScrollPane() {
		// TODO Auto-generated method stub
		return logScrollPane;
		
	}
	
	public void goToLine(int line){		
		
		//System.out.println("element: "+logTextPane.getDocument().getDefaultRootElement().getElement(line -1));	
		//System.out.println("element: "+logTextPane.getDocument().getDefaultRootElement().getElement(line -1).getStartOffset());

		logTextPane.setCaretPosition(logTextPane.getDocument().getDefaultRootElement().getElement(line -1).getStartOffset());	
	};
	
	
	
	/*
	 * 
	 * 
	 * THE CODE ABOVE FIXES A BUG REGARDING LINE WRAPPING NOT HAPPENNING ON CERTAIN
	 * JAVA 1.7 VERSIONS
	 * 
	 * 
	 * 
	 * 
	 */
	class WrapEditorKit extends StyledEditorKit {
        ViewFactory defaultFactory=new WrapColumnFactory();
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }

    }

    class WrapColumnFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WrapLabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }
    }

    class WrapLabelView extends LabelView {
        public WrapLabelView(Element elem) {
            super(elem);
        }

        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }

    }

	public String getCode() {
		// TODO Auto-generated method stub
		return logTextPane.getText();
	}
}
