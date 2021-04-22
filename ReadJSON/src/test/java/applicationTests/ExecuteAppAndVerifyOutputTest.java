package applicationTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import application.ReadJSONTask;
import params.CommonParams;
import params.ErrorParams;
import params.Params;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class ExecuteAppAndVerifyOutputTest extends CommonTest
{
    private Params params;
    private final static String EMPTYSTRING = "";

    /**
     * Constructor
     * 
     * @param params the Params object to be used for the test
     */
    public ExecuteAppAndVerifyOutputTest(Params params)
    {
        this.params = params;
    }
    
    /**
     * This test executes application passing various input parameters and verifies the output result
     * 
     */
    @Test
    public void executeAppAndVerifyOutput()
    {
        ReadJSONTask.main(new String[] {this.params.getPathToFile(), this.params.getFieldToSearch(), String.valueOf(this.params.getValueToSearch())});;

        if (this.params.getErrorMessageIsExpected())
           this.checkOutputError();
        else
            this.checkSearchResult();
    }
    
    /**
     * Asserts that output matches expected error message and there is no more information displayed afterwards
     * 
     */
    public void checkOutputError()
    {      
        assertEquals(this.params.getExpectedOutput(), this.errContent.toString());
        assertTrue("There should be no results displayed after error", this.outContent.toString().isEmpty());
    }
    
    /**
     * Asserts that search results matches expected and there are no error messages displayed
     * 
     */
    public void checkSearchResult()
    {      
        assertEquals(this.params.getExpectedOutput(), this.outContent.toString()); 
        assertTrue("There should be no error messages", this.errContent.toString().isEmpty()); 
    }
    
    /**
     * Creates list of param objects for the test
     * 
     * @return list of param objects
     */
    @Parameterized.Parameters
    public static List<Params> testDataProvider()
    {
        List<Params> params = new ArrayList<>();
        
        params.add(new ErrorParams(EMPTYSTRING,"ReadJSONTask: [pathToFie] [specificField] [specificValue] (optional)"));
        params.add(new ErrorParams("src/test/resources/blabla.json", "anything","File does not exist"));
        params.add(new ErrorParams("src/test/resources/wrongFormat.json", "anything", "Failed to parse JSON file"));
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
 }
