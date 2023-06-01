package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CsvReader {

    public static Object[][] load(String path, int colSize) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader(ResourceReader.getInstance().asFile(path)));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][colSize];
            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        } catch (URISyntaxException | IOException | CsvException ex) {
            return null;
        }
    }
}