package application;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Finds and prints count of objects and values (optional) in JSON file
 * 
 */
public class ReadJSONTask 
{
    /**
     * @param args
     * @throws IOException 
     * @throws JsonProcessingException 
     * @throws IllegalStateException 
     */
    public static void main(String[] args) throws IllegalStateException, IOException
    {        
        CommandLineParser arguments = new CommandLineParser(args);
        
        ConditionMatcher.init(arguments.getField(), arguments.getValue());

        new FileReader(arguments.getFilePath()).analyzeFile();
               
        printResults(arguments.getField(), arguments.getValue());
    }
  
    private static void printResults(String fieldName, Object valueName)
    {
        System.out.println("Found " + ConditionMatcher.getExpecedFieldsFound() + " objects with field " + fieldName);
        System.out.println("Found " + ConditionMatcher.getExpectedValuesFound() + " objects with field " + valueName.toString());
    }
}
