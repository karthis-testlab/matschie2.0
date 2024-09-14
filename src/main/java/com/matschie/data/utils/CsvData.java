package com.matschie.data.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class CsvData implements DataHandler {

	public String[][] getData(String fileName) {
		String[][] data = null;
		try {
			CSVReader csvReader = new CSVReaderBuilder(new FileReader("src/main/resources/data/" + fileName + ".csv"))
					                  .withSkipLines(1).build();
			List<String[]> records = csvReader.readAll();
			data = new String[records.size()][records.get(0).length];
			for (int i = 0; i < records.size(); i++) {
				for (int j = 0; j < records.get(i).length; j++) {
					data[i][j] = records.get(i)[j];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		return data;
	}

}