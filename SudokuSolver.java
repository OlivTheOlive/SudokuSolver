
public class SudokuSolver {

	private static final int GRID_SIZE = 9;

	public static void main(String[] args) {
		int[][] board = { 
				{ 0, 3, 6, 5, 8, 0, 9, 0, 0 }, 
				{ 0, 9, 0, 3, 0, 0, 0, 7, 0 }, 
				{ 0, 5, 0, 2, 7, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 4 }, 
				{ 0, 0, 0, 0, 0, 3, 0, 0, 0 }, 
				{ 0, 0, 5, 9, 6, 0, 7, 0, 0 },
				{ 0, 0, 3, 0, 0, 7, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0, 2, 8, 0, 0 }, 
				{ 5, 0, 0, 8, 3, 0, 0, 1, 0 }, 
				};

		printboard(board);

		if (solveBoard(board)) {
			System.out.println("Solved!!");
		} else {
			System.out.println("Could not solve");
		}

		printboard(board);

	}

	private static boolean isNumberInRow(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInColumn(int[][] board, int number, int column) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInBox(int[][] board, int number, int column, int row) {
		int localBoxRow = row - row % 3;
		int localBoxColumn = column - column % 3;

		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean isValidPlacement(int[][] board, int number, int column, int row) {
		return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column)
				&& !isNumberInBox(board, number, column, row);

	}

	private static boolean solveBoard(int[][] board) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) {
					for (int numToTry = 1; numToTry <= GRID_SIZE; numToTry++) {
						if (isValidPlacement(board, numToTry, column, row)) { // test until valid number does not mean
																				// it is the right one
							board[row][column] = numToTry;

							if (solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0; 
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static void printboard(int[][] board) {
		for (int row = 0; row < GRID_SIZE; row++) {
			if (row % 3 == 0 && row != 0) {
				System.out.println("-------------------");
			}
			for (int column = 0; column < GRID_SIZE; column++) {
				if (column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				System.out.print(board[row][column]+" ");

			}
			System.out.println();
		}

	}

}
