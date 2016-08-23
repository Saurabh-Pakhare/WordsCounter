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

    FileReader fileReader;
    Scanner inputText;
    int threshold;
    Map<String, Integer> frquencyMap;

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public void setFileReader(FileReader fileReader)
    {
        this.fileReader = fileReader;
        frquencyMap = readFile();
    }

    public FrequencyCounter(File file, int threshold) throws FileNotFoundException {

        if(file == null)
            throw new NullPointerException("File cannot be null");

        if(threshold < MIN_THRESHOLD) {
            throw new InvalidParameterException("Value of threshold cannot be negative");
        }

        fileReader = new FileReader(file);
        inputText = new Scanner(fileReader);
        this.threshold = threshold;

        frquencyMap = readFile();

        //assert this.threshold >= MIN_THRESHOLD : "Threshold cannot be less than zero";

    }

    private Map<String,Integer> readFile()
    {
        inputText.useDelimiter("\\s+|\\.\\s|,\\s+|;|\\s+|\\\\s'+|\"|\\.|,|:|;+");

        Map<String, Integer> map = new HashMap<>();

        while (inputText.hasNext())
        {
            String str = inputText.next().toLowerCase();
            if (map.containsKey(str))
            {
                map.put(str,map.get(str) + 1);
            }
            else
                map.put(str,1);
        }
        return map;
    }


    public void displayResult() throws IOException {

        for (Map.Entry<String, Integer> entry: frquencyMap.entrySet())
        {
            if (entry.getValue() >=threshold)
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
        }

        fileReader.close();
    }
}
