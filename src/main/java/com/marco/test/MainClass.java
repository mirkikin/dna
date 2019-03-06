package com.marco.test;

import java.util.Arrays;
import java.util.List;

public class MainClass {
	public static void main(String[] args) {
		List<String> tests = Arrays.asList(new String[] { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" });
		IDnaService service = new DnaService();
		boolean hasMutation = service
				.hasMutation(new String[] { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" });
		service.getStats();
		System.out.println(hasMutation);
	}
}
