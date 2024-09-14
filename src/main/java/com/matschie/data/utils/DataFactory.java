package com.matschie.data.utils;

public class DataFactory {

	public static DataHandler engine(DataEngine dataEngine) {
		if (DataEngine.EXCEL == dataEngine) {
			return new ExcelData();
		} else {
			return new CsvData();
		}
	}

}