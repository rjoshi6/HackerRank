package armedia;

import java.util.Scanner;
/* 
Program for evaluation of given given string in infix using Shunting Yard Algorithm by Dijkstra
Pre-validation is performed on the given input string to sanitize input.
Test Cases:
  "5 + 2 * 3"   ---> 22
  "7.2s + 3"    ---> Invalid entry
  The program takes only integers, arithmetic operators all separated by a single space
  Only( & ) allowed single spaced.    
*/
import java.util.Stack;
public class PostFix
{
/* Function will take input of valid string and evaluate. This is done with the help of two stacks 
 * namely operands and operators respectively
 * Given String is broken down into array of tokens by toCharArray() function
 * Algorithm works as follows
 * Looks for next
 * if a number then, it is pushed onto the operand stack
 * if left parenthesis, push it onto operator stack
 * if right parenthesis, Here is where we calculate and return result to stack
 * Until we encounter left parenthesis as operator stack top
 * 	Pop current stack top from operator stack 
 * 	Pop two stack tops from operand stack
 * 	Perform operation on those two values
 * 	Return value onto operand stack
 * Pop left parenthesis and discard
 * if you encounter an operator, check if stack isn't empty
 * and operator on operator stack has greater or equal precedence than the encountered operator
 * 	Pop operator from operator stack
 * 	Pop twice from operand stack 
 * 	Apply the operator
 * 	return result onto the stack
 * Push the encountered operator onto the operator stack
 * Until operator stack is not empty
 *  Pop current stack top from operator stack 
 * 	Pop two stack tops from operand stack
 * 	Perform operation on those two values
 * 	Return value onto operand stack
 * Check if operator stacj is empty and then return the
 * result of the operand(value) stack as the asnwer.
 * * 
 *  	*/
public static double evalExpression(String expression)
 {	
	//convert the string into char array, easy to manipulate
	 char[] tokens = expression.toCharArray();
	 
     Stack<Integer> operands = new Stack<Integer>();			//Integer stack to store operands
 
     Stack<Character> operators = new Stack<Character>();			// Character Stack for operators

     for (int i = 0; i < tokens.length; i++)
     {
    	
         if (tokens[i] == ' ')								// Skip white spaces
             continue;

         if (tokens[i] >= '0' && tokens[i] <= '9')			// Current token is a number, push it to stack for numbers
         {
             StringBuffer sbuf = new StringBuffer();		// TO build new string for pushing onto stack
             
          // For multiple digits, check every token if it has a digit next to it and append it
             while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') 	
                 sbuf.append(tokens[i++]);
             operands.push(Integer.parseInt(sbuf.toString()));
         }
         
         else if (tokens[i] == '(')							// If current token is left parenthesis
             operators.push(tokens[i]);

         else if (tokens[i] == ')')							// If current token is right parenthesis
         {
             while (operators.peek() != '(')
               operands.push(performOp(operators.pop(), operands.pop(), operands.pop()));
             operators.pop();
         }

         
         else if (tokens[i] == '+' || tokens[i] == '-' ||	// If token is an operator.
                  tokens[i] == '*' || tokens[i] == '/')
         {
        /*
         * Check precedence, select operator and perform appropriate operation on two popped operands, return result back to operand stack
         */       	 
             while (!operators.empty() && checkPrecedence(tokens[i], operators.peek()))
               operands.push(performOp(operators.pop(), operands.pop(), operands.pop()));

             
             operators.push(tokens[i]);			// Push current token to operator stack.
         }
     }
     // Loop until operator stack is not empty and apply operators on remaining operands and return results to operand stacks				
     while (!operators.empty())
         operands.push(performOp(operators.pop(), operands.pop(), operands.pop()));

     return operands.pop();				// Top of operand stack contains result, pop and return it
 }

 // Returns true if 'operator2' has higher or same precedence as 'operator1',
 // otherwise returns false.
 public static boolean checkPrecedence(char operator1, char operator2)
 {
     if (operator2 == '(' || operator2 == ')')
         return false;
     if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
         return false;
     else
         return true;
 }

 /*A method to perform required operation according to operator and return the result.*/
 public static int performOp(char op, int b, int a)
 {
     switch (op)
     {
     case '+':
         return a + b;
     case '-':
         return a - b;
     case '*':
         return a * b;
     case '/':
         if (b == 0)
             throw new
             UnsupportedOperationException("Divide by zeor exception");
         return a / b;
     }
     return 0;
 }
 	
 public static void main(String[] args)
 {		
	 	System.out.println("Enter a valid expression :");
	 	
	 	Scanner kbd = new Scanner(System.in);	// Declare scanner for parsing string input
	 
		String str = kbd.nextLine();			// store input in variable
		kbd.close();
 /*
 * This part of the code validates the given string to match given input and performs appropriate actions, viz
 *  Passing input to evaluate() function if input is sanitized
 *  Rejecting input and providing reason for incorrectness
 * 
 * 	Initially input is checked if any characters have even been entered, or only white spaces have been entered
 * 	if string is empty, appropriate message is provided to users on format of input
 * 	Else string isn't empty, then perform following validations depending on their ASCII values
 * 		The input string is ran passed through a special regex which accepts only integers, spaces and arithmetic operators
 * 			Valid strings are then sent of to the evaluate() function
 * 			Invalid input is then iterated through to find and display appropriate messages as following
 * 			if string contains decimal point,
 * 			if string contains alphabets, or any characters less than or equal to ASCII value 39 and Greater than equal to ASCII value 58
 * 			are displayed with appropriate messages. 
 */		
		// Check for null,empty and only white spaces
		
		if(((str.trim()).length() == 0)){
			System.out.println("Empty Expression, Enter expression with valid single spaced operators and Integers ");
		}
		else{
			
			if( (str.matches("^(?=.*[0-9])[- * / +()0-9]+$")))	{
				 System.out.println(evalExpression(str));
			}
			else{

				System.out.println(str);	// Input displayed for providing reason for rejection
			 
				for(int i=0;i<str.length();i++){
					
					if( ((int)str.charAt(i)) == 32 )											       // Space is valid
						continue;
					if( ((int)str.charAt(i)) == 46 ){											       // Cast and compare ASCII values 	
						System.out.println(str.charAt(i)+" Decimals are not valid integers");
					}								
					if( ((int)str.charAt(i)) <=39 ||  ((int)str.charAt(i)) >= 58 ){
						System.out.println(str.charAt(i)+" Is not a valid integer nor an operator");   // Cast and compare ASCII values 
					}
				}// end for
			}// end else
		}// end outer else

 	}//end main
}//end class