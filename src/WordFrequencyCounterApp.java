import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by saurabh on 21/8/16.
 */
public class WordFrequencyCounterApp {

    private final static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        String filename;
        File file;
        int threshold;
        FrequencyCounter frequencyCounter;

        while (true) {
            try {
                filename = getFilename();
                file = getInputFile(filename);
                threshold = getThreshold();

                frequencyCounter = new FrequencyCounter(file, threshold);
                break;

                } catch (FileNotFoundException e) {
                    System.err.println(e.getMessage());
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Threshold shoud be an Integer");
                }
            console.nextLine();
            }

        try {
            frequencyCounter.displayResult();
        } catch (IOException e) {
            System.err.println("IOException occured");
        }
    }

    private static String getFilename()
    {
        System.out.println("Enter input file name : ");
        String filename = console.nextLine();
        return filename;
    }

    private static File getInputFile(String filename) {
        File file = null;

        file = new File(filename);

        return file;
    }


    public static int getThreshold() {
        int threshold;

        System.out.println("Enter the word frequency threshold:- ");
        threshold = console.nextInt();

        return threshold;
    }
}
