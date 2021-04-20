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

        ConditionMatcher.init(arguments.getField(), arguments.getValue());

        if (analyzeFile(arguments))
            printResults(arguments.getField(), arguments.getValue());
    }


    private static boolean analyzeFile(CommandLineParser arguments)
    {
        try
        {
            new FileReader(arguments.getFilePath()).analyze();
        }
        catch (Exception x)
        {
            System.err.print(x.getMessage());
            return false;
        }
        return true;
    }

    private static void printResults(String fieldName, String valueName)
    {
        System.out.println("-found " + ConditionMatcher.getExpecedFieldsFound() + " objects with field \"" + fieldName + "\"");

        if (valueName != null)
            System.out.println("-found " + ConditionMatcher.getExpectedValuesFound() + " objects where field " + "\"" + fieldName + "\" equals " + "\"" + valueName + "\"");
    }
}
