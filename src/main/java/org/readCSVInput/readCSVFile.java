package org.readCSVInput;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.HashMap;

public class readCSVFile {
    public static void main(String[] arg) {
        //Method to load CSV file and read data
        HashMap<String, String> detailsMap = new HashMap<String, String>();
        //Store the details of Cosmos Database
        detailsMap = csvReader("C:\\Users\\MahipalSingh\\Documents\\ConfigCSV.csv");
        System.out.println("Map Value :: "+detailsMap);
    }

    public static HashMap csvReader(String path) {
        CSVReader reader = null;
        HashMap<String, String> map = new HashMap<String, String>();
        String[] columnName;
        String[] userData;
        try { //parsing a CSV file into CSVReader
            reader = new CSVReader(new FileReader(path));
            if ((columnName = reader.readNext()) != null) {
                if ((userData = reader.readNext()) != null) {
                    for (int i = 1; i <= columnName.length; i++) {
                        map.put(Array.get(columnName, i).toString(), (Array.get(userData, i)).toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
