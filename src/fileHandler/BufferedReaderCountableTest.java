/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package fileHandler;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class BufferedReaderCountableTest {
    private BufferedReaderCountable buff;
    private String line;
    private int a;
    private File file;
    
    
    @Before
    public void initialize(){
        a =0; 
        String line;
        file = new File("LogSamples/SSMApplet.log");

    }
    

    @Test
    public void testMarkAndResetEndOfLine() throws IOException {
        System.out.println("testMarkAndReset end of line");
         
        buff = new BufferedReaderCountable(new FileReader(file), 1024*1024);
        long start = System.currentTimeMillis();
        
        while ((buff.readLine()) != null){
        	//System.out.println(buff.getCurrentLineText());
            if(buff.getCurrentLineNum()==45637){
                buff.mark(1024*1024);
                System.out.println("marked: line= "+buff.getCurrentLineNum() + "text= "+ buff.getCurrentLineText());
            }
            
        }
        System.out.println("Reached end of file= "+buff.getCurrentLineNum() + "text= "+ buff.getCurrentLineText());
        buff.reset();
        a= buff.getCurrentLineNum();
        line = buff.getCurrentLineText();
        System.out.println("AFTER RESET AND READ... marked: current line= "+ a + "text currentLine= "+ line);
        
        assertEquals("line compare", 45637, a);
        assertEquals("text compare", "            starting state: start", line);
        
    }
    
    @Test
    public void testMarkAndReset300Lines() throws IOException {
        System.out.println("testMarkAndReset 300 lines");
         
        buff = new BufferedReaderCountable(new FileReader(file), 1024*1024);
        long start = System.currentTimeMillis();
        
        while ((buff.getCurrentLineNum() != 10272)){
        	buff.readLine();
        	//System.out.println(buff.getCurrentLineText());
            if(buff.getCurrentLineNum()==9972){
                buff.mark(1024*1024);
                System.out.println("marked: line= "+buff.getCurrentLineNum() + "text= "+ buff.getCurrentLineText());
            }
            
        }
        System.out.println("Reached line: 10272; text= "+ buff.getCurrentLineText());
        buff.reset();
        a= buff.getCurrentLineNum();
        line = buff.getCurrentLineText();
        System.out.println("AFTER RESET AND READ... marked: current line= "+ a + "text currentLine= "+ line);
        
        assertEquals("line compare", 9972, a);
        assertEquals("text compare", "            starting state: GroupOneInfoWait", line);
        
    }

}
