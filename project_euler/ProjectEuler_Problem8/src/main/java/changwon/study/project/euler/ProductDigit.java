package changwon.study.project.euler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductDigit {

	private String fileName;
	private List<Integer> digitList;

	public ProductDigit(String fileName) {
		this.fileName = fileName;
		
	}

	public int getMaximumProduct() {
		initDigitList();
		
		int maximumProduct = 0;
		int product;
		int prevDigit = 1;
		int nextDigit;
		
		for (int i = 0; i < digitList.size() -4; i++) {
			
			product = digitList.get(i) * digitList.get(i+1) * digitList.get(i+2) * 
					digitList.get(i+3) * digitList.get(i+4);
			
			if ( product > maximumProduct) maximumProduct = product;
			
			
		}
		
		return maximumProduct;
	}

	public void initDigitList() {
		File file = new File(fileName);
		FileInputStream fis;
		int ch;
		
		digitList = new ArrayList<Integer>();
		
		try {
			fis = new FileInputStream(file);
			
			while ((ch = fis.read()) != -1){
				if (ch == '\n') continue;
				Character c = new Character((char) ch);
				digitList.add(new Integer(c.toString()));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
