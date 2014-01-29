package changwon.study.project.euler;


public class PathCase {


	private long[][] pathCase;
	private int cellSize;

	public PathCase(int cellSize) {
		this.cellSize = cellSize+1;
		this.pathCase = new long[cellSize+1][cellSize+1];
	}

	public long getPathCase() {
		initArray();
		display();
		return pathCase[cellSize-1][cellSize-1];
	}

	public void initArray() {
		for (int i = 0; i < cellSize; i++) {
			pathCase[i][0] = 1;
			pathCase[0][i] = 1;
		}
		
		for (int i = 1; i < cellSize; i++) {
			for (int j = 1; j < cellSize; j++) {
				pathCase[i][j] = pathCase[i][j-1] + pathCase[i-1][j];
			}
			
		}
	}

	private void display() {
		for (int i = 0; i < cellSize; i++) {
			for (int j = 0; j < cellSize; j++) {
				System.out.format(" %10d ", pathCase[i][j]);
			}
			System.out.println("");
		}
		
		
		
	}

}
