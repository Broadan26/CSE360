//Brandon Barrett
//CSE360 - Wednesday 9:30am
//Assignment 1

/* Determines if a given string is a palindrome.
   Iterates through only a single string using left and right pointers. 
   The only acceptable palindrome characters are alphanumeric, 
   everything else is ignored*/

package assign1;

public class Palindrome 
{
	private static String testString; //Global variable holding the string to be tested
	
	/*Palindrome
	  Constructor method which checks if the string is a palindrome*/
	public Palindrome(String word) 
	{
		testString = word;
	}
	
	/*isPalindrome
	Checks to see if the string that is input is a Palindrome
	Returns true if it is a palindrome, returns false if it is not*/
	public boolean isPalindrome()
	{
		int right = testString.length() - 1; //Find the length of the word
		int left = 0;
		boolean exit = true; //Exit control for the loop & return variable
		
		/*Iterate through the word checking left against right*/
		while(exit == true && left <= right)
		{
			//Pull chars from string and convert them to lowercase
			char leftC = testString.charAt(left);
			leftC = Character.toLowerCase(leftC);
			char rightC = testString.charAt(right);
			rightC = Character.toLowerCase(rightC);
			
			//If there is a different symbol to the left
			if(!(leftC >= 'a' && leftC <= 'z') && !(leftC >= '1' && leftC <= '9'))
				left++;
			//If there is a different symbol to the right
			else if(!(rightC >= 'a' && rightC <= 'z') && !(rightC >= '1' && rightC <= '9'))
				right--;
			//If chars are equivalent
			else if(rightC == leftC)
			{
				left++;
				right--;
			}
			//If chars are not equal break the loop
			else
				exit = false;
		}
		
		return exit;
	}
	
	/*getTestString
	Allows checking what the current string is*/
	public String getTestString()
	{
		return testString;
	}
	
	/*setTestString
	Allows changing what the current string is*/
	public void setTestString(String word) 
	{
		testString = word;
	}
	
}