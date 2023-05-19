package org.readCSVInput;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //This will be used for JSON parsing
    public static Matcher regexMatcher(String regex, String line) {
        line = "{ \r\n" + " \"employee\": { \r\n" + " \"name\": \"sonoo\", \r\n" + " \"salary\": 56000, \r\n" + " \"married\": true \r\n" + " } \r\n" + "} ";
        regex = "name\":\\s+\"(.*?)\".*?:\\s+(\\d+).*?:\\s+(\\w+)";
        // Create a Pattern object
        Pattern r = Pattern.compile(regex, Pattern.DOTALL);
        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Name : " + m.group(1));
            System.out.println("Salary: " + m.group(2));
            System.out.println("Is Married ? : " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
        return m;
    }
}
