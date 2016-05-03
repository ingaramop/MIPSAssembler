/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package fileHandler;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import fileHandler.ParsingMethods;

public class ParsingMethodsTest {

    @Test
    public void testPrepareParse() throws IOException {
        String instructions []={
        		"LW 1, 0(20) ; Comentario",
        		"XOR 2, 2, 2",
        		"ADD  3 1   2",
        		"ADD $5, $10, $3"
        };
        
        for (int i=0; i<instructions.length; i++){
        	System.out.println(instructions[i] + " ->"+ ParsingMethods.prepareParseInstruction(instructions[i]));     	
        }        
    }
    @Test
    public void testToBinaryString(){
    	System.out.println("prueba de binarios");
    	String numeros[]={
    			"10000",
    			"2",
    			"1231231"};
        for (int i=0; i<numeros.length; i++){
        	System.out.println(numeros[i] + " ->"+ ParsingMethods.decodeBits(numeros[i], 26));     	
        } 
        for (int i=0; i<numeros.length; i++){
        	System.out.println(numeros[i] + " ->"+ ParsingMethods.decodeBits(numeros[i], 16));     	
        } 
        for (int i=0; i<numeros.length; i++){
        	System.out.println(numeros[i] + " ->"+ ParsingMethods.decodeBits(numeros[i], 5));     	
        } 
    }      
        @Test
        public void testOpDecode(){
        	System.out.println("prueba de operation decode");
        	String operation[]={
        			"ADD",
        			"SLTU",
        			"ORI"};
            for (int i=0; i<operation.length; i++){
            	System.out.println(operation[i] + " ->"+ ParsingMethods.opDecode(operation[i]));     	
            } 
        }
        
        @Test
        public void testParseInstruction() throws Exception{
        	System.out.println("prueba de parse instruction");
        	String instruction[]={
						        "ADD $s1,$s2,$s3 ;",
						        "SUB $s1,$s2,$s3 ;",
						        "ADDI $s1,$s2,4 ;",
						        "ORI $s1,$s2,4;",
						        "LW $s1,100($s2) ;",
						        "SW $s1,100($s2);"};
        	
            for (int i=0; i<instruction.length; i++){
            	System.out.println(instruction[i] + " ->"+ ParsingMethods.parseInstruction(instruction[i], 1));     	
            }
        }

}
