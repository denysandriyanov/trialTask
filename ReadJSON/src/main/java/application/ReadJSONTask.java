package application;

/**
 * Finds and prints count of objects and values (optional) in JSON file
 * 
 */
public class ReadJSONTask 
{
    /**
     * @param args as pathTofile fieldToFind valueToFind(optional)
     */
    public static void main(String[] args) 
    {        
        CommandLineParser arguments = new CommandLineParser(args);

        if (arguments.validated())
        {
            ConditionMatcher.init(arguments.getField(), arguments.getValue());

            if (new FileReader(arguments.getFilePath()).analyze())
                printResults(arguments.getField(), arguments.getValue());
        }
    }


    private static void printResults(String fieldName, String valueName)
    {
        System.out.println("-found " + ConditionMatcher.getFoundFieldsCount() + " objects with field \"" + fieldName + "\"");

        if (valueName != null)
            System.out.println("-found " + ConditionMatcher.getFoundValuesCount() + " objects where field " + "\"" + fieldName + "\" equals " + "\"" + valueName + "\"");
    }
}
