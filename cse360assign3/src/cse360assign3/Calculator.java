//Brandon Barrett
//CSE360 - Wednesday 9:30am
//Assignment 3

/* Calculates via division, multiplication and exponents different inputs. 
   Extends the AddingMachine class with further functionality */

package cse360assign3;

public class Calculator extends AddingMachine
{
	/*Calculator
	  Constructor for Calculator*/
	public Calculator()
	{
		total = 0;  //Not needed - included for clarity
		stringTotal = "0"; //Base instantiation
	}
	
	/*SetStringTotalMultiply
	  Concatenates stringtotal when multiply method called*/
	public void setStringTotalMultiply(int value)
	{
		stringTotal = stringTotal + " * " + value;
	}
	
	/*SetStringTotalDivide
	  Concatenates stringtotal when divide method called*/
	public void setStringTotalDivide(int value)
	{
		stringTotal = stringTotal + " / " + value;
	}
	
	/*SetStringTotalDivide
	  Concatenates stringtotal when power method called*/
	public void setStringTotalPower(int value)
	{
		stringTotal = stringTotal + " ^ " + value;
	}
	
	/*Multiply
	  Multiplies the parameter value with the total variable*/
	public void multiply(int value)
	{
		total = total * value;
		setStringTotalMultiply(value);
	}
	
	/*Divide
	  Divides the total by the parameter
	  If value is 0 set total to 0*/
	public void divide(int value)
	{
		if(value == 0)
			total = 0;
		else
			total = total / value;
		
		setStringTotalDivide(value);
	}
	
	/*Power
	  Raises the total by the inputted power amount.
	  If power value is negative total is set to 0*/
	public void power(int value)
	{
		if(value < 0)
			total = 0;
		else
		{
			double result = Math.pow(total, value);
			total = (int) result;
		}
		
		setStringTotalPower(value);
	}
}
