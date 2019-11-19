package Percolation;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	double[] numbers;
	
	/**
	 * perform T independent experiments on an N≠by≠N grid
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T){
		Percolation p = new Percolation(N);
		numbers = new double[T];
		
		for(int i = 0; i < T-1; i++){
			int counter = 0;
			
			while(!p.percolates()){
				p.open(StdRandom.uniform(N-1), StdRandom.uniform(N-1));
				counter++;
			}
			numbers[i] = counter;
		}
	}
	
	/**
	 * sample mean of percolation threshold
	 * @return
	 */
	public double mean(){
		return StdStats.mean(numbers);
	}
	
	/**
	 * sample standard deviation of percolation threshold
	 * @return
	 */
	public double stddev() {
		return StdStats.stddev(numbers);
	}
	
	/**
	 * low endpoint of 95% confidence interval
	 * @return
	 */
	public double confidenceLow() {
		return mean() - StdStats.stddev(numbers);
	}
	
	/**
	 * high endpoint of 95% confidence interval
	 * @return
	 */
	public double confidenceHigh() {
		return mean() + StdStats.stddev(numbers);
	}
	
	/**
	 * Main method to check for percolation stats.
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println("Here we go!");
		PercolationStats p = new PercolationStats(10, 100);
		
		System.out.println("Mean: " + p.mean());
		System.out.println("Standard Deviation: " + p.stddev());
		
	}
}
