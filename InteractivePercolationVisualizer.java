package Percolation;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class InteractivePercolationVisualizer {
	private static final int DELAY = 20;

	public static void main(String[] args) {
		// n-by-n percolation system (read from command-line, default = 10)
		int n = 10;
		if (args.length == 1)
			n = Integer.parseInt(args[0]);

		// turn on animation mode
		StdDraw.enableDoubleBuffering();

		// repeatedly open site specified my mouse click and draw resulting system
		StdOut.println(n);

		Percolation percolation = new Percolation(n);
		PercolationVisualizer.draw(percolation, n);
		StdDraw.show();

		while (true) {

			// detected mouse click
			if (StdDraw.mousePressed()) {

				// screen coordinates
				double x = StdDraw.mouseX();
				double y = StdDraw.mouseY();

				// convert to (row, col)
				int row = (int) (n - Math.floor(y) - 1);
				int col = (int) (Math.floor(x));

				// open site (i, col) provided it's in bounds
				if (row >= 0 && row < n && col >= 0 && col < n) {
					if (!percolation.isOpen(row, col)) {
						StdOut.println(row + " " + col);
					}
					percolation.open(row, col);
				}

				// draw n-by-n percolation system
				PercolationVisualizer.draw(percolation, n);
			}
			StdDraw.show();
			StdDraw.pause(DELAY);
		}
	}
}