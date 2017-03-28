package com.ensf409.calculator;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Calculator {
	private LinkedList<Double> numbers;//holds all numbers in order for current calculation
	private LinkedList<Character> operations;//holds all operations in order for current calculation
	private Double currentNumber;//current number being built
	private double lastSum;//holds value of the lastSum calculated
	private int afterDec; //counts how many elements past decimal, 0 if no decimal entered
	private String toDisplay;//string to be returned on ButtonPressed call
	private char lastReceived;//keeps track of last received char on a ButtonPressed call
	private boolean divZeroErr;//becomes true if a calculation attempts to divide by zero
	private boolean formatErr;
	private int beforeDec;

	//initializes calculator
	public Calculator(){
		clear();
		lastSum = 0;
		lastReceived = 'C';
		toDisplay = "";
		divZeroErr = false;
		formatErr = false;
	}
	//clears Lists of numbers & operations, sets afterDec and currentNumber to 0
	//gets called by when 'C' or '=' is pressed and when initializing calculator
	private void clear(){
		numbers = new LinkedList<Double>();
		operations = new LinkedList<Character>();
		currentNumber = 0.0;
		afterDec = 0;
		beforeDec = 0;
	}
	//called from '=', calculates sum of stored numbers with respective operations and stores value in lastSum
	//if a divide by zero is attempted, sets the divZeroErr to true 
	private void calculate(){
		Double sum = numbers.pop();//set sum to the first number added, removed it from list
		while(!numbers.isEmpty()){//while there are still numbers to pop, keep poppin'
			char op = operations.pop();
			Double num = numbers.pop();
			if (op == '+')
				sum += num;
			else if (op == '-')
				sum -= num;
			else if (op == '*')
				sum *= num;
			else if (op == '/'){
				//if a divide by zero is attempted, make note of it
				if(num == 0) {
					divZeroErr = true;
					toDisplay = "Divide by Zero Error";
				}
				sum /= num;
			}
		}
		lastSum = sum;
	}
	//first version of overloaded ButtonPressed, accepts integer to build up currentNumber
	public String ButtonPressed(int num){
		//if divZeroErr or formatErr has occurred, "lock out" method until 'C' has been pressed
		if(divZeroErr || formatErr)
			return toDisplay;
		double asDouble = (double)num;
		if (beforeDec >= 9)
		{
			return toDisplay;
		}
		//if decimal has been used on current number
		else if(afterDec!= 0){
			//compute arithmetic for currentNumber
			currentNumber += asDouble*Math.pow(10, (afterDec));
			//this code is to produce a proper string toDisplay just in case 0's have been pressed after decimal place
			int places = -1;
			places *= (int)afterDec;
			DecimalFormat df = new DecimalFormat("#.##");
			df.setMaximumFractionDigits(places);
			df.setMinimumFractionDigits(places);
			toDisplay = df.format(currentNumber);
			//decrement afterDec for proper arithmetic on next call of ButtonPressed
			afterDec--;
		}
		//if decimal has not been used on currentNumber
		else{
			//compute arithmetic for currentNumber
			currentNumber = (currentNumber*10) + asDouble;
			toDisplay = Integer.toString((int)currentNumber.doubleValue());

		}
		//set lastReceived to 'N' notifying a number was entered last
		beforeDec++;
		lastReceived = 'N';
		return toDisplay;
	}
	//first version of overloaded ButtonPressed, accepts a char, 4 possible char types 'O'(operation), 'C'(cancel), '=', or '.'
	public String ButtonPressed(char ch) {
		//if '=' pressed
		switch (ch) {
			case '=':
				//if divZeroErr or formatErr has occurred, "lock out" method until 'C' has been pressed
				if (divZeroErr || formatErr)
					break;
					//if a operation was just pressed, display a format error to screen
				else if (lastReceived == 'O') {
					formatErr = true;
					toDisplay = "FORMAT ERROR";
					break;
				}
				//if '=' was just pressed, display lastSum again, if 'C' was just pressed, display blank again
				else if (lastReceived == '=' || lastReceived == 'C') {
					break;
				}
				//if '.' or 'N' was pressed last
				else {
					//add currentNumber to the list and calculate the sum
					numbers.add(currentNumber);
					calculate();
					//if a divide by zero occured during calculate, display error, do not pass Go, do not collect $200
					//if divZeroErr or formatErr has occurred, "lock out" method until 'C' has been pressed
					if (divZeroErr || formatErr)
						break;
					//display the sum that was calculated, clear, and set lastReceived to '='
					toDisplay = Double.toString(lastSum);
					clear();
					lastReceived = ch;
					break;
				}
				//if 'C' is pressed, reset everything as if the calculator was just initialized
			case 'C':
				clear();
				lastSum = 0;
				toDisplay = "";
				divZeroErr = false;
				formatErr = false;
				break;

			case '.':
				//if divZeroErr or formatErr has occurred, "lock out" method until 'C' has been pressed
				if (divZeroErr || formatErr)
					break;
				//if decimal has not been pressed for currentNumber
				if (afterDec == 0) {
					afterDec--;
					//if a number was received last, display the number with a decimal after it
					if (lastReceived == 'N') {
						String temp = toDisplay + ".";
						toDisplay = temp;
					}
					//if a number was not received last, display only the decimal
					else
						toDisplay = ".";
					lastReceived = ch;
				}
				break;

			default:
				//if divZeroErr or formatErr has occurred, "lock out" method until 'C' has been pressed
				if (divZeroErr || formatErr)
					break;
				//if an operation was entered last, replace it with new operation entereed
				if (lastReceived == 'O') {
					operations.removeLast();
				}
				//if a sum was just calculated, add it as first number
				else if (lastReceived == '=')
					numbers.add(lastSum);
					//if 'C' was just entered, do nothing and show a blank screen still
				else if (lastReceived == 'C')
					break;
					//in the case where none of the above occur ('N' or '.'), add currentNumber
					//to numbers and reset currentNumber & afterDec to be ready for new number input
				else {
					numbers.add(currentNumber);
					currentNumber = 0.0;
					afterDec = 0;
					beforeDec = 0;
				}
				//add operation to the operation list, display operation to screen and set lastReceived to 'O' for operation
				operations.add(ch);
				toDisplay = Character.toString(ch);
				lastReceived = 'O';
				break;

		}
		return toDisplay;
	}
}

