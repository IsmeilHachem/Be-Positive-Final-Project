package co.grandcircus.bepositive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

public class WordFilter {

	public static boolean badwordfinder(String words) {
		boolean flag = false;

		File csvFile = null;
		try {
			csvFile = new ClassPathResource("badWords.csv").getFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (csvFile.isFile()) {
			// create BufferedReader and read data from csv
			BufferedReader csvReader = null;
			try {
				csvReader = new BufferedReader(new FileReader(csvFile));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String row;

			try {
				while ((row = csvReader.readLine()) != null) {
					String[] data = row.split(",");

					for (String s : data) {
						if (words.contains(s)) {
							flag = true;
						}
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return flag;
	}

}
