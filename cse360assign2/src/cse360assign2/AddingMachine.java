//Brandon Barrett
//CSE360 - Wednesday 9:30am
//Assignment 2

/* Calculates via addition or subtraction different inputs. 
   The form of inputs can be returned either as an integer sum value.
   Or it can be returned as a sequence of inputs in string format */

package cse360assign2;

public class AddingMachine 
{
	private int total;
	private String stringTotal;
	
	/*Adding Machine
	  Constructor for AddingMachine */
	public AddingMachine () 
	{
		total = 0;  //Not needed - included for clarity
		stringTotal = "0"; //Base instantiation
	}
	
	/*GetTotal
	  Returns the total*/
	public int getTotal () 
	{
		return total;
	}
	
	/*SetStringTotalAdd
	  Concatenates stringtotal when add method called*/
	public void setStringTotalAdd(int value)
	{
		stringTotal = stringTotal + " + " + value;
	}
	
	/*SetStringTotalSubtract
	  Concatenates stringtotal when subtract method called*/
	public void setStringTotalSubtract(int value)
	{
		stringTotal = stringTotal + " - " + value;
	}
	
	/*Add
	  Adds the parameter value to the total variable*/
	public void add (int value) 
	{
		total = total + value;
		setStringTotalAdd(value);
	}
	
	/*Subtract
	  Subtracts the parameter value to the total variable*/
	public void subtract (int value) 
	{
		total = total - value;
		setStringTotalSubtract(value);
	}
	
	/*toString
	  Returns the object contents as a string*/	
	public String toString () 
	{
		return stringTotal;
	}
	
	/*Clear
	  Clears the total and stringTotal*/
	public void clear() 
	{
		total = 0;
		stringTotal = "0";
	}
}
