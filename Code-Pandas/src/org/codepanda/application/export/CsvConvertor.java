package org.codepanda.application.export;

import java.io.FileWriter;
import java.io.IOException;

public class CsvConvertor {
	
	public void convert(String filename){
		try {
			FileWriter fw = new FileWriter(filename);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
