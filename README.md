# CSE360

## CSE360Assignment1

Create a class Palindrome that determines if a string is a Palindrome. A palindrome, for this assignment,
is defined as a word or phrase consisting of 0 or more alphanumeric characters that reads the same forwards
and backwards while ignoring cases, punctuation and white space. If there are no alphanumeric characters, it is considered a palindrome.
#### Restrictions
1. You may NOT return from inside of a loop
2. You may NOT break from inside of a loop
3. You may use ONLY ONE loop
4. You may NOT copy the String to another String
5. You may NOT process the String more than one time
6. You must STOP processing as early as possible

## CSE360Assignment2

Use the provided class template AddingMachine.java and make the following changes to the class

1. The add method should add the parameter to the total variable
2. The subtract method should subtract the parameter from the total variable
3. The getTotal method should return the current total
4. A history of transactions must be kept to be returned by the toString method. The history should start from initial 0.
Example: 
  test.add(4); test.subtract(2); test.add(5) should return "0 + 4 - 2 + 5" in the string and a value of 7 in the total.
5. The clear method clears the string and the current total.

## CSE360Assignment3

Change the AddingMachine.java class in the previous assignment to:
1. Instance variables have protected visibility
2. Correct the style and any errors in the class from the previous assignment.

Create a calculator.java class which:
1. Inherits from AddingMachine.java
2. Add three aditional methods which handle multiplication, division and exponents
3. If dvision by 0 is detected, set the total to 0. Do not clear the string.
4. If raising a number by a negative power, set the total to 0. Do not clear the string.
