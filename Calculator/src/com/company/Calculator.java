/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author ParkerC
 * 
 * @author vedaa
 * 
 */
//i added ceiling,floor,e to the power of input operators in TwoPartExpressions method
//i added max,min operators in ThreePartExpressions method
public class Calculator {

	private static final String QUIT = "QUIT";

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws FileNotFoundException {

		//welcome message here
		System.out.println("Welcome to my Calculator!!!");
        
		//call startcalc method
		startCalc();
		
		//call testcalc method
		testCalc();

		//goodbye message here
		System.out.println("Hope you enjoyed the calculations! Have a good day!!!");

	}

	 public static void startCalc() {
	
		//Prompt the user to enter the expression to be evaluated in a loop

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter an expression to calculate: ");
		String input = "";
		String result = null;
		Pattern quitPattern = Pattern.compile(QUIT, Pattern.CASE_INSENSITIVE);

		while (!scanner.hasNext(quitPattern)) {
			input = scanner.nextLine();
			result = calculate(input);
			System.out.println(result);
			System.out.println("Enter next expression to calculate: ");
			// your code here to get user input, and calculate/print results. You'll call
			// the calculate(String s) as part of your code here, which returns a String
			// with the result to print.
		}

		scanner.close();
	}

	/*
	 * Evaluates the expression provided as a string input/parameter and returns the
	 * result as a string.
	 */
	public static String calculate(String input) {
		// you add code here to take a String as a parameter, and return a String with
		// the result

		Scanner inputScanner = new Scanner(input);
		String result = "";

		Stream<String> tokens = inputScanner.tokens();
		long count = tokens.count();

		if (input.equalsIgnoreCase("quit")) {
			result = "quit";
		} 
		else if (count == 2) {
			result = evaluateTwoPartExpression(input);
		}
		else if (count == 3) {
			result = evaluateThreePartExpression(input);
		}
		else {
			System.out.println(" Enter valid expression " + input);
			result = "ERROR";
		}
		
		inputScanner.close();
		// you'll probably call other methods (that you write) here to do work,
		// like deciding if it's a 2-part or 3-part expression, or calculating the
		// result of a
		// 2-part expression or a 3-part expression
		return result;
	}

	/*
	 * 
	 */
	public static String evaluateTwoPartExpression(String s) {

		Scanner inputScanner = new Scanner(s);
		double result = 0;
		String returnString = null;

		String operator = inputScanner.next();
		double num1 = 0;

		if (inputScanner.hasNextDouble()) {
			num1 = inputScanner.nextDouble();
		} else {
			//System.out.println("Enter valid number. Unexpected value: " + inputScanner.next());
			inputScanner.close();
			return "ERROR";
		}

		switch (operator) {
		case "|": {

			result = Math.abs(num1);
			break;
		}
		case "v": {

			result = Math.sqrt(num1);
			break;
		}
		case "t": {

			result = Math.tan(num1);
			break;
		}
		case "s": {

			result = Math.sin(num1);
			break;
		}
		case "c": {

			result = Math.cos(num1);
			break;
		}
		case "~": {

			result = Math.round(num1);
			break;
		}
		//to give the higher integer value of a float value
		case "ceiling": {
		     result = Math.ceil(num1);
		     break;
		}
		//to give the lower integer value of a float value
		case "floor": {
		     result = Math.floor(num1);
		     break;
		} 
		//to give the value of e to the power num1
		case "exp": {

			result = Math.exp(num1);
			break;
		}
		default:
			System.out.println("Unexpected operator for two part expression: " + operator);
			inputScanner.close();
			return "ERROR";
		}

		inputScanner.close();
		returnString = String.valueOf(result);
		return returnString;
	}
	
	
	/*
	 * 
	 */
	public static String evaluateThreePartExpression(String s) {

		Scanner inputScanner = new Scanner(s);
		double result = 0;
		String returnString = null;

		String operator = "" ;
		double num1 = 0;
		double num2 = 0;

		if (inputScanner.hasNextDouble()) {
			num1 = inputScanner.nextDouble();
			operator = inputScanner.next();
			if (inputScanner.hasNextDouble())
			{
			  num2 = inputScanner.nextDouble();
			}
			else {
				System.out.println("Enter valid number. Unexpected value: " + inputScanner.next());
				inputScanner.close();
				return "ERROR";
			}
		} 
		else {
			System.out.println("Enter valid number. Unexpected value: " + inputScanner.next());
			inputScanner.close();
			return "ERROR";
		}

		switch (operator) {
		case "+": {

			result = num1 + num2;
			break;
		}
		case "-": {

			result = num1 - num2;
			break;
		}
		case "/": {

			result = num1 / num2;
			break;
		}
		case "*": {

			result = num1 * num2;
			break;
		}
		case "^": {

			result = Math.pow(num1, num2);
			break;
		}
		case "%": {

			result = num1 % num2;
			break;
		}
		//to give the maximum of 2 numbers
		case "max": {

			result = Math.max(num1, num2);
			break;
		}
		//to give the minimum of 2 numbers
		case "min": {

			result = Math.min(num1, num2);
			break;
		}
	
		default:
			System.out.println("Unexpected operator for double operand expression: " + operator);
			inputScanner.close();
			return "ERROR";
		}

		inputScanner.close();
		returnString = String.valueOf(result);
		return returnString;
	}

	
	//
	// DO NOT MODIFY THE METHOD BELOW!!!
	// YOU WILL CALL IT FROM MAIN TO TEST OUT YOUR OTHER CODE/METHODS
	//
	public static void testCalc() throws FileNotFoundException {
		ArrayList<String> problems = new ArrayList<>();
		ArrayList<String> results = new ArrayList<>();
		// load problems from a file
		File fProblems = new File("problems.txt");
		Scanner sc = new Scanner(fProblems);
		int count = 0;
		String line = "";
		int problemCount = 0;
		int resultCount = 0;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			if (!line.startsWith("//") && !line.trim().equals("")) { // ignore comments at the beginning
				problems.add(line.substring(3).trim());
				problemCount++;
				if (sc.hasNextLine()) {
					line = sc.nextLine();
					if (!line.startsWith("//") && !line.trim().equals("")) {
						results.add(line.substring(3).trim());
						resultCount++;
					}
				}
				count++;
			}
		}
		if (problemCount == resultCount) {
			// now run the tests
			for (int i = 0; i < problemCount; i++) {
				String prob = problems.get(i);
				String result = calculate(prob);
				if (result == null) {
					System.out.println("FAILED test " + i);
					System.out.println("Expression: " + problems.get(i));
					System.out.println("Expected result: " + results.get(i));
					System.out.println("Actual: null String returned from calculate()");
				} else {
					if (result.equals(results.get(i))) {
						System.out.println("PASSED test " + i);
					} else {
						System.out.println("FAILED test " + i);
						System.out.println("Expression: " + problems.get(i));
						System.out.println("Expected result: " + results.get(i));
						System.out.println("Actual: " + result);
					}
				}

			}
		} else {
			System.out.println("problem file error");
		}

	}

}
