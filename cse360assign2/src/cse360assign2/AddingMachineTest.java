package cse360assign2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddingMachineTest {

	@Test
	void testAddingMachine() 
	{
		//Instantiate Palindrome object
		AddingMachine test = new AddingMachine();
		int output;
		String outputS;
		
		//1. Test for basic addition
		test.add(4);
		output = test.getTotal();
		assertEquals(4, output);
		test.clear();
		
		//2. Test for basic subtraction
		test.subtract(4);
		output = test.getTotal();
		assertEquals(-4, output);
		test.clear();
		
		//3. Test for addition then subtraction
		test.add(7);
		test.subtract(4);
		output = test.getTotal();
		assertEquals(3, output);
		test.clear();
		
		//4. Test for subtraction then addition
		test.subtract(7);
		test.add(4);
		output = test.getTotal();
		assertEquals(-3, output);
		test.clear();
		
		//5. Test toString basic addition
		test.add(4);
		outputS = test.toString();
		assertEquals("0 + 4", outputS);
		test.clear();
		
		//6. Test toString basic subtraction
		test.subtract(4);
		outputS = test.toString();
		assertEquals("0 - 4", outputS);
		test.clear();
		
		//7. Test toString for addition then subtraction
		test.add(4);
		test.subtract(8);
		outputS = test.toString();
		assertEquals("0 + 4 - 8", outputS);
		test.clear();
		
		//8. Test toString for subtraction then addition
		test.subtract(4);
		test.add(8);
		outputS = test.toString();
		assertEquals("0 - 4 + 8", outputS);
		test.clear();
		
		//9. Long complicated case for add and subtract
		test.add(1);
		test.subtract(2);
		test.add(3);
		test.subtract(4);
		test.add(5);
		test.subtract(6);
		test.add(7);
		test.subtract(8);
		test.add(9);
		test.subtract(10);
		outputS = test.toString();
		assertEquals("0 + 1 - 2 + 3 - 4 + 5 - 6 + 7 - 8 + 9 - 10", outputS);
		output = test.getTotal();
		assertEquals(-5, output);
		test.clear();
	}
}
