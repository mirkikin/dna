package com.marco.test;

import java.util.List;

public class DnaRequest {
	private List<String> dna;
	private boolean stats;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

	public boolean isStats() {
		return stats;
	}

	public void setStats(boolean stats) {
		this.stats = stats;
	}

}
