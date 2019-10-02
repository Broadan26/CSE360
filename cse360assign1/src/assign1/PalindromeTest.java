//Brandon Barrett
//CSE360 - Wednesday 9:30am
//Assignment 1

/* Contains the tests to be run for the Palindrome class.
   Palindrome must pass all tests to be successful */

package assign1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeTest 
{
	@Test
	void testPalindrome() 
	{
		//Instantiate Palindrome object
		Palindrome test = new Palindrome("HaAh");
		
		//1. Condition for palindrome with odd letters and cases
		boolean output = test.isPalindrome(); 
		assertEquals(true, output);
		
		//2. Condition for palindrome with even letters & cases
		test.setTestString("NeverOddOrEven");
		output = test.isPalindrome(); 
		assertEquals(true, output); 
		
		//3. Condition for not palindrome
		test.setTestString("NotPalindrome");
		output = test.isPalindrome();
		assertEquals(false, output);
		
		//4. Condition for a single character
		test.setTestString("a");
		output = test.isPalindrome(); 
		assertEquals(true, output);
		
		//5. Condition for empty line
		test.setTestString("\n");
		output = test.isPalindrome(); 
		assertEquals(true, output);
		
		//6. Condition apostrophes
		test.setTestString("cu'u'c");
		output = test.isPalindrome(); 
		assertEquals(true, output);
		
		//7. Condition for spaces
		test.setTestString("cu u c");
		output = test.isPalindrome(); 
		assertEquals(true, output);
		
		//8. Condition for commas
		test.setTestString("cu,u,c");
		output = test.isPalindrome(); 
		assertEquals(true, output);
		
		//9. Condition for a partial sentence
		test.setTestString("Don't nod.");
		output = test.isPalindrome(); 
		assertEquals(true, output);
		
		//10. Condition for a full sentence
		test.setTestString("Was it a cat I saw?");
		output = test.isPalindrome(); 
		assertEquals(true, output); 
		
		//11. Numeric Characters
		test.setTestString("131");
		output = test.isPalindrome(); 
		assertEquals(true, output); 
		
		//12. Numeric Characters, letters and punctuation
		test.setTestString(" 1a3a1!");
		output = test.isPalindrome(); 
		assertEquals(true, output); 
		
		//13. Long Palindrome
		test.setTestString("Are we not pure? “No, sir!” Panama’s moody Noriega brags. “It is garbage!” Irony dooms a man—a prisoner up to new era.");
		output = test.isPalindrome(); 
		assertEquals(true, output); 
	}
}