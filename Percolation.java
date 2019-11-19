package Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private WeightedQuickUnionUF union;
	private boolean[][] grid;
	private int n;
	private int numberOfOpenSites;

	/**
	 * create N≠by≠N grid, with all sites blocked
	 * 
	 * @param N
	 */
	public Percolation(int n) {
		if (n <= 0) throw new IllegalArgumentException();
		this.n = n;
		grid = new boolean[n][n];
		union = new WeightedQuickUnionUF(n*n+2); // (n*n) connects to squares on top row.
	}											// (n*n+1) connects to squares on bottom row										
	

	/**
	 * open site (row i, column j) if it is not open already
	 * 
	 * @param i
	 * @param j
	 */
	public void open(int i, int j) {
		if (!isOpen(i, j)) { // if coordinate is closed
			grid[i][j] = true; // coordinate is opened
			if (i > 0)              // if coordinate isn't on top row 
				if (grid[i - 1][j]) // then check if square above is open									
					union.union(convertToOneD(i, j), // if so, connect the two squares														 
							convertToOneD(i - 1, j));
			if (i < n - 1)          // if coordinate isn't on bottom row then
				if (grid[i + 1][j]) // check if square below is open									
					union.union(convertToOneD(i, j), // if so, connect the two squares														
							convertToOneD(i + 1, j));
			if (j > 0)               // if coordinate isn't on left-most column
				if (grid[i][j - 1])  // then check if square to left is open									
					union.union(convertToOneD(i, j), // if so, connect the two squares														
							convertToOneD(i, j - 1));
			if (j < n - 1)          // if coordinate isn't on right-most column
				if (grid[i][j + 1]) // then check if square to right is open									
					union.union(convertToOneD(i, j), // if so, connect the two squares														
							convertToOneD(i, j + 1));
			if (i == 0) // if coordinate is on top row
				union.union(convertToOneD(i, j), n * n); // connect the square to the top fake square							
			if (i == n - 1) { // if coordinate is on bottom row
				if (!(union.connected(n * n, n * n + 1))) // and if top fake square and bottom fake square aren't connected																				
					union.union(convertToOneD(i, j), n * n + 1); // connect square to bottom fake square																
			}
		}
	}

	/**
	 * is site (row i, column j) open?
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		return grid[i][j];
	}
	/**
	 * is site (row i, column j) full?
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) {
		return union.connected(convertToOneD(i, j), n * n);
	}
	/**
	 * does the system percolate?
	 * 
	 * @return
	 */
	public boolean percolates() {
		return union.connected(n * n, n * n + 1);
	}

	private int convertToOneD(int i, int j) {
		return (i * n) + j;
	}


	public int numberOfOpenSites() {
	
		return numberOfOpenSites;
	}
}