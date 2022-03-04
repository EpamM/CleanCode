package com.epam.engx.cleancode.naming.task3;

public class HarshadNumber {

	// print some Harshad numbers
	public String main() {
		StringBuilder result = new StringBuilder();
		long limit = 200; // limit the seq of Harshad numbers
		for (int i = 1; i <= limit; i++) {
			if (i % findSumOfDigits(i) == 0) {
				result.append(i).append("\n");
			}
		}
		return result.toString();
	}

	/**
	 * find sum of digits of a number
	 *
	 * @param number : number to find the sum of its digit
	 * @return : sum of all digits
	 */
	private int findSumOfDigits(int number) {
		int sum = 0;
		while (number != 0) {
			sum += number % 10;
			number = number / 10;
        }
		return sum;
	}

}
