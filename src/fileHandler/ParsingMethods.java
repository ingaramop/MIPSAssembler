/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package fileHandler;
//aaa

import java.util.Arrays;


public class ParsingMethods { 
    


	public static String prepareParseInstruction(String currentLineText) {//prepara la secuencia de caracteres para el parseo
		currentLineText = currentLineText.split(";")[0];// elimino todo lo que se encuentre después de ;
		currentLineText = currentLineText.replace('$', ' ');//reemplazo todos los caracteres por un espacio
		currentLineText = currentLineText.replace(',', ' ');
		currentLineText = currentLineText.replace('(', ' ');
		currentLineText = currentLineText.replace(')', ' ');
		currentLineText = currentLineText.replace('\t', ' ');
		currentLineText = currentLineText.replaceAll("[ ]+", " ");//reemplazo múltiples espacios en blanco por uno solo
		currentLineText = currentLineText.toUpperCase();//paso a mayúscula
		currentLineText = currentLineText.trim();// Elimino espacios blancos al comienzo y al final
		return currentLineText;
	}
	
	public static String parseInstruction(String line, int lineNumber) throws Exception{
		String output;
		line = prepareParseInstruction(line);	
		System.out.println(line);
		String params [] = line.split(" ");	
		if(params.length<1) return "";// si es una linea vacía, no hago nada
		for (int i =0; i<params.length; i++){
			System.out.print("'"+params[i]+"'");
		}
		System.out.println();
		if (Arrays.asList(ParsingConstants.RT_TYPE).contains(params[0])){
			//Ejemplo: (SLL rd, rt, const) 
			//rd <- rt << sa
			//00000000000rtrdsa000000 (donde 000000 es el opcode de SLL)
			output = "00000000000"+ regDecode(params[2])+ regDecode(params[1])+decodeBits(params[3],5)+ opDecode(params[0]);
			
		}
		else if (Arrays.asList(ParsingConstants.RS1_TYPE).contains(params[0])){
			//Ejemplo: (SRAV rd,rt,rs) 
			//rd <- rt << rs
			//000000rsrtrd00000000111 (donde 000111 es el opcode de SRAV)
			output = "000000"+ regDecode(params[3])+ regDecode(params[2])+regDecode(params[1])+"00000" + opDecode(params[0]);			
		}
		else if (Arrays.asList(ParsingConstants.RS2_TYPE).contains(params[0])){
			//Ejemplo: (SUB rd,rs,rt) 
			//rd <- rs - rt
			//000000rsrtrd00000100001 (donde 100001 es el opcode de SUB)
			output = "000000"+ regDecode(params[2])+ regDecode(params[3])+regDecode(params[1])+"00000" + opDecode(params[0]);	
		}
		else if (Arrays.asList(ParsingConstants.I1_TYPE).contains(params[0])){
			//Ejemplo: (LB rt,offset(base)) 
			//100000basertoffset (donde 100000 es el opcode de LB)
			output = opDecode(params[0])+ regDecode(params[3])+regDecode(params[1])+decodeBits(params[2],16);
		}
		else if (Arrays.asList(ParsingConstants.I2_TYPE).contains(params[0])){
			//Ejemplo: (ADDI rt,rs,inmediate) 
			//001000rsrtinmediate (donde 001000 es el opcode de ADDI)
			output = opDecode(params[0])+ regDecode(params[2])+regDecode(params[1])+decodeBits(params[3],16);		
		}
		else if (Arrays.asList(ParsingConstants.I3_TYPE).contains(params[0])){
			//Ejemplo: (LUI rt,inmediate) 
			//00111100000rtinmediate (donde 001111 es el opcode de LUI)
			output = opDecode(params[0])+ "00000" + regDecode(params[1])+ decodeBits(params[2],16);		
		}
		else if (Arrays.asList(ParsingConstants.I4_TYPE).contains(params[0])){
			//Ejemplo: (BEQ rs,rt,offset) 
			//000100rsrtoffset (donde 000100 es el opcode de BEQ)
			output = opDecode(params[0])+ regDecode(params[1])+regDecode(params[2])+ decodeBits(params[3],16);			
		}
		else if (Arrays.asList(ParsingConstants.I5_TYPE).contains(params[0])){
			//Ejemplo: (J target) 
			//000010target (donde 000010 es el opcode de J)
			output = opDecode(params[0])+  decodeBits(params[1],26);
		}
		else if (Arrays.asList(ParsingConstants.J1_TYPE).contains(params[0])){
			//Ejemplo: (JR rs) 
			//000000rs000000000000000001000 (donde 001000 es el opcode de JR)
			output = "000000"+ regDecode(params[1])+ "000000000000000"+ opDecode(params[0]);
		}
		else if (Arrays.asList(ParsingConstants.J2_TYPE).contains(params[0])){
			//Ejemplo: (JALR rd,rs) 
			//000000rs00000rd0000000100 (donde 000100 es el opcode de JALR)
			output = "000000"+ regDecode(params[2])+ "00000"+ regDecode(params[1])+ "0000"+ opDecode(params[0]);
		}
		else if (Arrays.asList(ParsingConstants.NOP_TYPE).contains(params[0])){
			output = "11111100000000000000000000000000";
		}
		else{ 
			throw new Exception("Linea: "+ lineNumber + ". Instrucción no reconocida.");		
		}
	
	if(output.length()!=32) throw new Exception("Linea: "+ lineNumber + ". Error en el parseo. Linea con distinto tamaño que 32 bits.");
	
	return output + "\n";
		
	}

	public static String decodeBits(String string, int bits) {		
		String out = String.format("%"+String.valueOf(bits)+"s", Integer.toBinaryString(Integer.valueOf(string))).replace(' ', '0');// convierto a binario y relleno con ceros
		out = out.substring(out.length() - bits);//recorto los 16 menos significativos		
		return out;
	}

	private static String regDecode(String string) {
		int position = Arrays.asList(ParsingConstants.REG_CODE_MAP).indexOf(string);
		return ParsingConstants.REG_CODE_MAP[position+1];
	}

	public static String opDecode(String string) {
		int position = Arrays.asList(ParsingConstants.OPERATION_CODE_MAP).indexOf(string);
		return ParsingConstants.OPERATION_CODE_MAP[position+1];
	}
}
