package cse360assign3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void test() 
	{
		//Instantiate Adding Machine object
		Calculator test = new Calculator();
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
		
		//10. Long complicated case for add and subtract
		test.subtract(1);
		test.subtract(2);
		test.subtract(3);
		test.subtract(4);
		test.subtract(5);
		test.add(6);
		test.add(7);
		test.add(8);
		test.add(9);
		test.add(10);
		outputS = test.toString();
		assertEquals("0 - 1 - 2 - 3 - 4 - 5 + 6 + 7 + 8 + 9 + 10", outputS);
		output = test.getTotal();
		assertEquals(25, output);
		test.clear();
		
		//11. Test for basic multiplication
		test.add(1);
		test.multiply(4);
		output = test.getTotal();
		assertEquals(4, output);
		test.clear();
		
		//12. Test for basic division
		test.add(1);
		test.multiply(4);
		test.divide(2);
		output = test.getTotal();
		assertEquals(2, output);
		test.clear();
		
		//13. Test for multiplication string
		test.add(1);
		test.multiply(4);
		outputS = test.toString();
		assertEquals("0 + 1 * 4", outputS);
		test.clear();
		
		//14. Test for division string
		test.add(1);
		test.multiply(4);
		test.divide(2);
		outputS = test.toString();
		assertEquals("0 + 1 * 4 / 2", outputS);
		test.clear();
		
		//15. Test for basic exponents
		test.add(4);
		test.power(2);
		output = test.getTotal();
		assertEquals(16, output);
		test.clear();
		
		//16. Test for exponent string
		test.add(4);
		test.power(3);
		outputS = test.toString();
		assertEquals("0 + 4 ^ 3", outputS);
		test.clear();
		
		//17. Test for division by 0
		test.add(4);
		test.divide(0);
		output = test.getTotal();
		assertEquals(0, output);
		test.clear();
		
		//18. Test for exponent of 0
		test.add(4);
		test.power(0);
		output = test.getTotal();
		assertEquals(1, output);
		test.clear();
		
		//19. Test for negative exponent
		test.add(4);
		test.power(-1);
		output = test.getTotal();
		assertEquals(0, output);
		test.clear();
	}

}
