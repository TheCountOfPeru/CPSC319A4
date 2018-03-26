/**
 * This class creates a 2D array of Integer
 * @author jonathan.yee
 *
 */
public class AdjMatrix {
	private int[][] matrix;
	
	public AdjMatrix(int size) {
		 matrix = new int[size][size];
	}
	public AdjMatrix() {
		
	}
	public int[][] getMatrix() {
		return matrix;
	}
	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	public void addToMatrix(int row, int col, int el) {
		matrix[row][col] = el;
	}
	public void PrintMatrix() {
		for(int i=0;i<matrix[0].length;i++) {
			for(int j=0;j<matrix[0].length;j++) {
				System.out.print(matrix[i][j]+"	");
			}
			System.out.println();
		}
	}
}
