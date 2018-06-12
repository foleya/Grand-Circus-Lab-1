import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DigitSumChecker {

	public static void main(String[] args) {
		DigitSumChecker dsc = new DigitSumChecker();
		dsc.checkDigitSums();
	}

	/**
	 * Main Program: Prompts user to input two positive integers,
	 * validates that the inputs are integers composed of Arabic
	 * numerals, containing equal numbers of digits. Calls a method
	 * to check if the sum of each digit in each place of the two
	 * integers is equal, printing each calculation, then ultimately
	 * printing out true or false depending on the result.
	 */
	private void checkDigitSums() {
		/* Declare variables for storing and validating user input */
		Scanner scnr = new Scanner(System.in);
		String inputOne;
		String inputTwo;
		boolean inputOneIsValid = false;
		boolean inputTwoIsValid = false;
		
		/*
		 * Prompt user to enter a positive integer, then validate that
		 * the user's input is composed of only Arabic numerals (0-9).
		 */
		do {
			System.out.print("Enter a positive integer: ");
			inputOne = scnr.nextLine();
			try {
				validateNumeric(inputOne);
				inputOneIsValid = true;
			} catch (IllegalArgumentException iae) {
				System.out.printf("%s Please try again. %n%n", 
						 	  	  iae.getMessage());
			}
		} while (!inputOneIsValid);

		/*
		 * Next, prompt user to enter a second positive integer,
		 * validating that the user's second input is composed of only
		 * Arabic numerals (0-9) AND has the same number of digits as
		 * the user's first input.
		 */
		do {
			System.out.printf("Enter a second %d digit positive integer: ",
							  inputOne.length());
			inputTwo = scnr.nextLine();
			try {
				validateNumeric(inputTwo);
				validateNumDigits(inputOne, inputTwo);
				inputTwoIsValid = true;
			} catch (IllegalArgumentException iae) {
				System.out.printf("%s Please try again. %n%n", 
								  iae.getMessage());
			}
		} while (!inputTwoIsValid);
		
		/* 
		 * With two valid inputs, run the method to check for digit 
		 * sum equivalences, printing out the boolean result of that
		 * method.
		 */
		boolean hasFeature = checkSumEquivalence(inputOne, inputTwo);
		System.out.println(hasFeature);
	}

	/**
	 * This helper method is for validating that user input 
	 * contains only Arabic numerals (0-9). It throws an
	 * IllegalArgumentException if not.
	 * 
	 * @param string
	 */
	private static void validateNumeric(String input) {
		if (!input.matches("^[\\d]+$")) {
			throw new IllegalArgumentException(
					"-- You must enter an integer (Arabic numerals only) --"
			);
		}
	}

	/**
	 * This helper method is for validating that the first and second 
	 * user inputs contain the same number of digits (i.e. are the same
	 * length). It throws an IllegalArgumentException if not.
	 * 
	 * @param two strings
	 */
	private static void validateNumDigits(String inputOne, String inputTwo) {
		if (inputOne.length() != inputTwo.length()) {
			throw new IllegalArgumentException(
					"-- Both integers must have the same number of digits --"
			);
		}
	}

	/**
	 * This method is for checking whether each sum of each place 
	 * (e.g. ones, tens hundreds, etc.) in two integers are equal. 
	 * 
	 * For example: 
	 * If Number1 = 153 && Number2 = 345
	 * => 1+3 ≠ 5+4 ≠ 3+5 
	 * Returns false. 
	 * 
	 * But if Number1 = 543 && Number2 = 456
	 * => 5+4 = 4+5 = 3+6 
	 * Returns true.
	 * 
	 * @param two equal length strings of Arabic numerals (0-9)
	 * @return boolean
	 */
	private boolean checkSumEquivalence(String inputOne, String inputTwo) {
		/* Create two arrays of strings from user input (two Strings). */
		String[] arrayOne = inputOne.split("");
		String[] arrayTwo = inputTwo.split("");

		/* Create an empty set to check for sum equivalences later */
		Set<Integer> set = new HashSet<Integer>();

		/*
		 * Sum correspondingly indexed digits in each Array and add each 
		 * result to the set (to see if there are two or more unique values).
		 * Print each calculation for the user.
		 */
		for (int i = 0; i < arrayOne.length; i++) {
			int placeSum = (Integer.parseInt(arrayOne[i])
							+ Integer.parseInt(arrayTwo[i]));
			System.out.printf("%s + %s = %s%n", arrayOne[i],
							  arrayTwo[i], placeSum);
			set.add(placeSum);
		}
		
		/* 
		 * Check the size of the set of sums, report results, and return
		 * true or false. If set size was 1, then all sums were the same,
		 * otherwise at least two of the sums were different.
		 */
		if (set.size() == 1) {
			System.out.println("All of the sums were equal!");
			return true;
		} else {
			System.out.println("Not all of the sums were equal!");
			return false;
		}
	}
}