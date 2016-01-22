package com.member.model;

import java.util.Random;

public class RandomPassWord {
	
	public static String getRandomPassWord(int length) {
		Random r = new Random();
		StringBuilder password = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			if (RandomPassWord.letterOrNum(r)) {
				password.append(r.nextInt(9));
			} else {
				int letterCase = r.nextInt(52);
				if (letterCase < 26) {
					password.append((char)(letterCase+65));
				} else {
					password.append((char)(letterCase-26+97));
				}
			}
		}
		return password.toString();
	}
	
	public static boolean letterOrNum(Random random) {
		if (random.nextInt(100)>60) {       
			return true;
		} else {
			return false;
		}
	}
	public static void main (String[] args){
		String r = getRandomPassWord(6);
		System.out.println(r);
		
	}
}
