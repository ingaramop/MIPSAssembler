/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package fileHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderCountable extends BufferedReader {
    
    private int currentLineNum;
    private int markNum;
    private String currentLineText;
    private String markText;
    
    public BufferedReaderCountable(FileReader fr, int sz){
        super(fr, sz);
        currentLineNum=0; 
        markNum=0;
        currentLineText = null;
        markText=null;
        
    }
    public BufferedReaderCountable(FileReader fr){
        super(fr);
        currentLineNum=0; 
        markNum=0;
        currentLineText = null;
        markText=null;
    }
    
    public void mark(int maxSz){
        try {
            super.mark(maxSz);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        markNum = currentLineNum;
        markText = currentLineText;
    }
    
    public void reset (){
        try {
            super.reset();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        currentLineNum= markNum;
        currentLineText = markText;
    }
    
    public String readLine() throws IOException {
        currentLineText = super.readLine();
        currentLineNum ++;
        return currentLineText;
    }
    
    public int getCurrentLineNum() {
        return currentLineNum;
    }

    public String getCurrentLineText() {
        return currentLineText;
    }
    
}
