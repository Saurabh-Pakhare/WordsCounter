import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by saurabh on 22/8/16.
 */
public class FrequencyCounter {

    public static final int MIN_THRESHOLD = 1;

    int threshold;
    Map<String, Integer> frquencyMap;
    File inputFile;

    public FrequencyCounter(File file, int threshold) throws IOException {

        setInputFile(file);
        setThreshold(threshold);
        frquencyMap = populateMap();

    }

    public int getThreshold() {
        return threshold;
    }

    public File getInputFile() {
        return inputFile;
    }

    public Map<String, Integer> getFrquencyMap() {
        return frquencyMap;
    }

    public void setInputFile(File inputFile) throws IOException {

        if(inputFile == null) {
            throw new NullPointerException("File cannot be null");
        }

        if(!isFileExisting(inputFile)) {
            throw new FileNotFoundException("File does not exist or Incorrect path");
        }

        if(isFileDirectory(inputFile)) {
            throw new FileNotFoundException("File is a directory, cannot proceed");
        }

        if(isFileEmpty(inputFile)) {
            throw new IOException("File is empty");
        }

        this.inputFile = inputFile;
        frquencyMap = populateMap();
    }

    public void setThreshold(int threshold) {

        if(threshold < MIN_THRESHOLD) {
            throw new IllegalArgumentException("Threshold cannot be negative");
        }
        this.threshold = threshold;
    }


    private boolean isFileExisting (File file) throws FileNotFoundException{
        if(!file.exists()) {
            return false;
        }

        return true;
    }

    private boolean isFileDirectory(File file) throws FileNotFoundException {
        if(file.isDirectory())
            return true;

        return false;
    }

    private boolean isFileEmpty(File file) throws IOException {
        if(file.length() == 0)
           return true;

        return false;
    }


    public Map<String,Integer> populateMap() throws IOException {
        FileReader fileReader = new FileReader(inputFile);
        Scanner inputText = new Scanner(fileReader);

        inputText.useDelimiter("\\s+|\\.\\s|,\\s+|;|\\s+|\\\\s'+|\"|\\.|,|:|;+");

        Map<String, Integer> map = new HashMap<>();

        while (inputText.hasNext())
        {
            String str = inputText.next().toLowerCase();
            if (map.containsKey(str))
            {
                map.put(str,map.get(str) + 1);
            }
            else {
                map.put(str, 1);
            }

        }

        inputText.close();
        fileReader.close();

        return map;
    }


    public void displayResult() throws IOException {

        for (Map.Entry<String, Integer> entry: frquencyMap.entrySet())
        {
            if (entry.getValue() >= threshold)
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
        }
    }
}
