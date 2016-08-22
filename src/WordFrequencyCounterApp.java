import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by saurabh on 21/8/16.
 */
public class WordFrequencyCounterApp {

    private final static Scanner console = new Scanner(System.in);
    private static final int MIN_THRESHOLD = FrequencyCounter.MIN_THRESHOLD;
    public static void main(String[] args) {

        File file = getInputFile();
        int threshold = getThreshold();

        FrequencyCounter frequencyCounter = null;
        try {
            frequencyCounter = new FrequencyCounter(file, threshold);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            frequencyCounter.displayResult();
        } catch (IOException e) {
            System.err.println("IOException occured");
        }
    }

    private static File getInputFile() {
        File file = null;

        while (true) {
            try {
                System.out.println("Enter input file name : ");
                String inputFilePath = console.nextLine();
                file = new File(inputFilePath);

                if (!file.exists())
                    throw new FileNotFoundException();

                if(file.isDirectory())
                    throw new SecurityException();

                if (file.length() ==0)
                    throw new Exception();

                break;

            } catch (FileNotFoundException e ) {
                System.err.println("File doesn't exist or Incorrect path");
            } catch (SecurityException e) {
                System.err.println("This is a directory, cannot proceed.");
            } catch (Exception e ) {
                System.err.println("File cannot be empty");
            }
        }

        return file;
    }

    private static int getThreshold() {
        int threshold;

        while (true) {
            try {
                System.out.println("Enter the word frequency threshold:- ");
                threshold = console.nextInt();
                if (threshold > MIN_THRESHOLD)
                    break;
                else
                    throw new IndexOutOfBoundsException();

            } catch (InputMismatchException e) {
                System.err.println("Threshold should be an Integer");
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Threshold cannot be negative");
            }
        }
        return threshold;
    }
}
