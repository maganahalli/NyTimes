package com.mac.nytimes.pojo;

import java.util.BitSet;

/**
 * Created by u1d090 on 2/22/2017.
 */

public class AceStringManager {

	public boolean canPermutationPalindrome(String s) {
		BitSet bitSet = new BitSet();
		for (byte b : s.getBytes()) {
			bitSet.flip(b);
		}
		return bitSet.cardinality() < 2;
	}

	public boolean isPalinDrome(String str) {
		str = str.toLowerCase().replaceAll("[^a-z!1-9]/g", "");
		if (str.length() == 0) {
			return true;
		}
		return canPermutationPalindrome(str);
	}

	public boolean isPalinDromeInPlace(String str) {
		if (str.length() == 0) {
			return true;
		}

		int head = 0;
		int tail = str.length() - 1;

		char chHead, chTail;

		while (head <= tail) {
			chHead = str.charAt(head);
			chTail = str.charAt(tail);

			if (!Character.isLetterOrDigit(chHead)) {
				head++;
			} else if (!Character.isLetterOrDigit(chTail)) {
				tail--;
			} else if (chHead != chTail) {
				return false;
			} else {
				head++;
				tail--;
			}

		}
		return true;
	}

	public boolean isPalinDromeInPlaceNumber(int number) {
		if (number < 0) {
			return false;
		}

		int runningNumber = number;
		int reminder = 0;

		while (runningNumber >= 10) {
			reminder *= 10;
			reminder += runningNumber % 10;
			runningNumber /= 10;
		}
		return reminder == number / 10 && runningNumber == number % 10;
	}
}
