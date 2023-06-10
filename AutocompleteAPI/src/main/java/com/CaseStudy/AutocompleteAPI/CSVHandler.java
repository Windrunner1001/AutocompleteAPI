package com.CaseStudy.AutocompleteAPI;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;


import java.io.*;
import java.util.List;


/**
 * Class CSVHandler deals with all actions related to the CSV-File. It therefore imports the opencsv-library.
 **/
public class CSVHandler {


    private final static String index = "NAME";
    private final static String path = "D_Bahnhof_2016_01_alle.csv";
    private static int indexColumn;

    public static String getPath() {
        return path;
    }

    public static String getIndex() {
        return index;
    }

    public static int getIndexColumn() {
        return indexColumn;
    }

    public static void setIndexColumn(String[] header) {
        indexColumn = findColumnIndex(header);
    }


    /**
     * Method readCsvFile reads a csv file in the location path, iterates all rows and stores them in a ArrayList as Strings.
     *
     * @return - ArrayList with all csv-rows
     **/
    public static List<String[]> readCsvFile() throws IOException {


        List<String[]> csvData;
        String[] header;

        try {
            //get path and fill inputStream
            ClassPathResource resource = new ClassPathResource(getPath());
            InputStream inputStream = resource.getInputStream();

            //the csv contains semicolons instead of comma, change separator within the builder
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream))
                    .withCSVParser(parser)
                    .build();

            //get all rows, separate head row in index 0, delete header from content data
            csvData = reader.readAll();

            //if there are no values, throw error
            if (csvData.isEmpty()) {
                throw new CSVFileEmpty();
            }

            header = csvData.get(0);
            csvData.remove(0);

            //find the column index, of the compare-column
            setIndexColumn(header);

            return csvData;

        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Method findColumnIndex gets the index of the compare-column e.g "NAME", in case of changes in the column order.
     *
     * @param header holds a string array with all column headings of the csv
     * @return i number of column index
     **/
    private static int findColumnIndex(String[] header) {

        //iterate header-row, to get the column number which the user input should be compared to
        for (int i = 0; i < header.length; i++) {
            if (getIndex().equals(header[i])) {
                return i; // Found the column index
            }
        }
        throw new HeaderNotFound();
    }
}
