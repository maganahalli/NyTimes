package com.mac.nytimes.pojo;

import java.util.BitSet;

/**
 * Created by u1d090 on 2/22/2017.
 */

public  class AceStringManager {

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
}
