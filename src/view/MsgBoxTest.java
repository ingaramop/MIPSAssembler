/*
 * 2014 AA/HP
 * Pablo Ingaramo
 */
package view;

import static org.junit.Assert.*;

import org.junit.Test;

public class MsgBoxTest {
	private MsgBox msjBox;
	@Test
	
	public void test() {
		msjBox.error("error message", "caller method");
		
	}

}
