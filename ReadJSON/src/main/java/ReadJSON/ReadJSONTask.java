package ReadJSON;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * Finds and prints count of objects and values (optional) in JSON file
 * 
 */
public class ReadJSONTask 
{
    static int fieldsFoundCount;
    static int valuesFoundCount;
    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {        
        CommandLineParser arguments = CommandLineParser.parse(args);
        List<Map<String, Object>> objects =  new JSONReader(arguments.getFilePath()).getListOfObjects(); 

        findFieldsAndValuesCount(arguments.getField(), arguments.getValue(), objects);
        printResults(arguments.getField(), arguments.getValue());
    }


    private static void findFieldsAndValuesCount(String fieldToFind, Object valueToFind, List<Map<String, Object>> objects)
    {
        for (Map<String, Object> currentObject : objects)
        {
            for (Map.Entry<String,Object> entry : currentObject.entrySet()) 
            {
                if (entry.getKey().equals(fieldToFind))
                {                
                    fieldsFoundCount++;
                    
                    if (String.valueOf(entry.getValue()).equals(valueToFind))
                        valuesFoundCount++;
                }
            }
        }
    }
       
    private static void printResults(String fieldName, String valueName)
    {
        System.out.println("Found " + fieldsFoundCount + " objects with field " + fieldName);
        System.out.println("Found " + valuesFoundCount + " objects with field " + valueName);
    }
}
