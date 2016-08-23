import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by saurabh on 23/8/16.
 */
public class FrequencyCounterTest {

    private FrequencyCounter frequencyCounter;

    @Before
    public void setUp() throws Exception {
        frequencyCounter = new FrequencyCounter(new File("input.txt"), 2);
    }

    @Test
    public void testReadFile() throws Exception {
        assertNotEquals(null, frequencyCounter.populateMap());
    }

    @Test(expected = NullPointerException.class)
    public void testSetInputFileIfNull() throws IOException {
        frequencyCounter.setInputFile(null);
    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void testSetInputFileIfExist() throws Exception {
        frequencyCounter.setInputFile(new File("hsdkjfh"));
    }

    @Test(expected = java.io.IOException.class)
    public void testSetInputFileIfEmpty() throws Exception {
        frequencyCounter.setInputFile(new File("text.txt"));
    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void testSetInputFileIfDirectory() throws IOException {
        frequencyCounter.setInputFile(new File("out"));
    }

    @Test
    public void testSetValidThreshold() throws Exception {
        frequencyCounter.setThreshold(2);
    }

    @Test (expected = java.lang.IllegalArgumentException.class)
    public void testSetInvalidThreshold() throws Exception {
        frequencyCounter.setThreshold(-4);

    }
}