import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by saurabh on 21/8/16.
 */
public class WordsCounter {

    public static void main(String[] args) {

        Scanner CONSOLE = new Scanner(System.in);
        String input = "I am the. In in i the EE!";

        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\\s+|\\.|\\,|\\;|\\:|\\'|\\?|\\!");

        System.out.println("Enter the word frequency threshold:- ");
        int threshold = CONSOLE.nextInt();

        Map<String, Integer> map = new HashMap<>();

        while (scanner.hasNext())
        {
            String str = scanner.next().toLowerCase();
            if (map.containsKey(str))
            {
                map.put(str,map.get(str) + 1);
            }
            else
                map.put(str,1);
        }

        for (Map.Entry<String, Integer> entry: map.entrySet())
        {
            if (entry.getValue() >=threshold)
                System.out.println(entry.getKey() + " - " + entry.getValue() + " times");
        }

    }
}
