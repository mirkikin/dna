package com.marco.test;

public class Stats {
	private long countMutations;
	private long countNoMutations;
	private double ratio;

	public long getCountMutations() {
		return countMutations;
	}

	public void setCountMutations(long countMutations) {
		this.countMutations = countMutations;
	}

	public long getCountNoMutations() {
		return countNoMutations;
	}

	public void setCountNoMutations(long countNoMutations) {
		this.countNoMutations = countNoMutations;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
}
