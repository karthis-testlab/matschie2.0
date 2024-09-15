package com.matschie.data.utils;

public class DataFactory {

	public static DataHandler engine(DataEngine dataEngine) {
		if (DataEngine.EXCEL == dataEngine) {
			return new ExcelData();
		} else if (DataEngine.CSV == dataEngine) {
			return new CsvData();
		}
		return null;
	}

}