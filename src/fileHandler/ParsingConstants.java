/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package fileHandler;

public final class ParsingConstants {
		
	//***** TIPO R ********
	public static final String RT_TYPE[] = {"SLL", "SRL", "SRA"};
	public static final String RS1_TYPE[] = {"SRLV", "SRAV"};
	public static final String RS2_TYPE[] = {"ADD", "SLLV", "ADDU", "SUB", "SUBU","AND", "OR", "XOR", "NOR", "SLT", "SLTU"};
	//***** TIPO I ********
	public static final String I1_TYPE[] = {"LB", "LH", "LW", "LWU", "LBU", "LHU", "SB", "SH", "SW"};
	public static final String I2_TYPE[] = {"ADDI", "ADDIU", "ANDI", "ORI", "XORI", "SLTIU", "SLTI"};
	public static final String I3_TYPE[] = {"LUI"};
	public static final String I4_TYPE[] = {"BEQ", "BNE"};
	public static final String I5_TYPE[] = {"J", "JAL"};
	//***** TIPO J ********
	public static final String J1_TYPE[] = {"JR"};
	public static final String J2_TYPE[] = {"JALR"};
	//***** NOP ********
	public static final String NOP_TYPE[] = {"NOP"};
	
	public static final String OPERATION_CODE_MAP [] = {	"SLL","000000",
															"SRL","000010", 
															"SRA","000011",          
															"SRLV","000110",
															"SRAV","000111",
															"ADD","100000",
															"SLLV","000100", 
															"ADDU","100001",
															"SUB","100010",
															"SUBU","100011",
															"AND","100100",
															"OR","100101",
															"XOR","100110",
															"NOR","100111",
															"SLT","101010",
															"SLTU","101011",
															"LB","100000",
															"LH","100001",
															"LW","100011",
															"LWU","100111", 
															"LBU","100100",
															"LHU","100101",
															"SB","101000",
															"SH","101001",
															"SW","101011",
															"ADDI","001000",
															"ADDIU","001001",
															"ANDI","001100",
															"ORI","001101",
															"XORI","001110",
															"SLTIU","001011",
															"SLTI","001010",
															"LUI","001111",
															"BEQ","000100",
															"BNE","000101",
															"J","000010",
															"JAL","000011",
															"JR","001000",
															"JALR","001001",
															"NOP","111111"};
	
	
	public static final String REG_CODE_MAP [] = {	"T0","01000",
													"T1","01001", 
													"T2","01010",          
													"T3","01011",
													"T4","01100",
													"T5","01101",
													"T6","01110", 
													"T7","01111",
													"T8","11000",
													"T9","11001",
													"S0","10000",
													"S1","10001", 
													"S2","10010",          
													"S3","10011",
													"S4","10100",
													"S5","10101",
													"S6","10110", 
													"S7","10111"};
	}
		
