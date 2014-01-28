package changwon.study.project.euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Product {

	private String inputFileName;
	private int[][] digitList;

	public Product(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public long getMaximumProduct() {
		init();
		long maximumProduct = 0;
		long product = 0;
		for (int i = 0; i < digitList.length; i++) {
			for (int j = 0; j < digitList[i].length - 3; j++) {
				product = digitList[i][j] * digitList[i][j+1] * 
						digitList[i][j+2] * digitList[i][j+3];
				if ( product > maximumProduct)	maximumProduct = product;
				
				
				product = digitList[j][i] * digitList[j+1][i] * 
						digitList[j+2][i] * digitList[j+3][i];
				
				if ( product > maximumProduct)	maximumProduct = product;
			}
		}
		for (int i = 0; i < digitList.length - 3; i++) {
			for (int j = 0; j < digitList[i].length - 3; j++) {
				product = digitList[i][j] * digitList[i+1][j+1] * 
						digitList[i+2][j+2] * digitList[i+3][j+3];
				if ( product > maximumProduct)	maximumProduct = product;
				
				product = digitList[i][j+3] * digitList[i+1][j+3-1] * 
						digitList[i+2][j+3-2] * digitList[i+3][j+3-3];
				
				if ( product > maximumProduct)	maximumProduct = product;
			}
		}
		
		
		return maximumProduct;
	}

	private void init() {
		digitList = new int[20][20];
		File file = new File(inputFileName);
		
		try {
			Scanner sc = new Scanner(file);
			
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					digitList[i][j] = sc.nextInt();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void display(){
		for (int i = 0; i < digitList.length; i++) {
			for (int j = 0; j < digitList[i].length; j++) {
				System.out.format("%d ", digitList[i][j]);
			}
			System.out.println();
		}
	}

}
