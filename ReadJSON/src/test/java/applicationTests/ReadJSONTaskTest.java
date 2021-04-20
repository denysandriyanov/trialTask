package applicationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import application.ReadJSONTask;
import params.CommonParams;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.runners.Parameterized;

/**
 * 
 */
@RunWith(Parameterized.class)
public class ReadJSONTaskTest
{
    private String pathToFile;
    private String expectedOutput;
    private String fieldToSearch;
    private Object valueToSearch;
    private boolean errorMessageExpected;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    /**
     * Constructor
     * 
     * @param params for the test
     */
    public ReadJSONTaskTest (CommonParams params)
    {
        this.pathToFile = params.getPathToFile();
        this.fieldToSearch = params.getFieldToSearch();
        this.valueToSearch = params.getValueToSearch();
        this.expectedOutput = params.getExpectedOutput();
        this.errorMessageExpected = params.getIsErrorMessage();
    }

    /**
     * Creates list of param objects for the test
     * 
     * @return list of param objects
     */
    @Parameterized.Parameters
    public static List<CommonParams> testParameters()
    {
        final String EMPTYSTRING = "";
        List<CommonParams> params = new ArrayList<>();

        params.add(new CommonParams("src/test/resources/blabla.json","File does not exist"));
        params.add(new CommonParams("src/test/resources/wrongFormat.json", "Failed to parse JSON file"));
        params.add(new CommonParams("src/test/resources/simple.json", "title", "\"Thinking in Java\"", 1, 1));
        params.add(new CommonParams("src/test/resources/simple.json", "title", "\"Hello Wordl\"", 1, 0));
        params.add(new CommonParams("src/test/resources/simple.json", "abrakadabra", "\"Hello Wordl\"", 0, 0));
        params.add(new CommonParams("src/test/resources/advanced.json", "isbn", EMPTYSTRING, 2, 0));
        params.add(new CommonParams("src/test/resources/advanced.json", "isbn", "\"978-0131872486\"", 2, 1));
        params.add(new CommonParams("src/test/resources/advanced.json", "authors", "\"Denys\"", 2, 0));
        params.add(new CommonParams("src/test/resources/advanced.json", "authors", "[\"Bruce Eckel\",\"Victor Pavlik\"]", 2, 1));
        params.add(new CommonParams("src/test/resources/advanced.json", "name", "\"Victor\"", 3, 1));
        params.add(new CommonParams("src/test/resources/advanced.json", "age", "29", 3, 1));
        params.add(new CommonParams("src/test/resources/advanced.json", "age", "\"29\"", 3, 2));
        params.add(new CommonParams("src/test/resources/advanced.json", "name", "[\"Denys\",{\"firstName\":\"Arkasha\",\"secondName\":\"Pasha\"}]", 3, 1));

        return params;
    }

    /**
     * Setting up output streams
     */
    @Before
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Restoring output streams to initial values
     */
    @After
    public void restoreStreams()
    {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    /**
     * This test executes application passing various input parameters and verifies the output result
     * 
     */
    @Test
    public void executeAppAndVerifyOutput()
    {
        String[] args = new String[] {this.pathToFile, this.fieldToSearch, String.valueOf(this.valueToSearch)};

        ReadJSONTask.main(args);

        if (this.errorMessageExpected)
        {
            assertEquals(this.expectedOutput, this.errContent.toString());
            assertTrue("There should be no results displayed after error", this.outContent.toString().isEmpty());
        }
        else
        {
            assertEquals(this.expectedOutput, this.outContent.toString()); 
            assertTrue("There should be no error messages", this.errContent.toString().isEmpty()); 
        }
    }
}
