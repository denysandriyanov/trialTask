package ReadJSON;

/**
 * Parses command line arguments to separate string fields
 * 
 */
public class CommandLineParser
{
    private static CommandLineParser parserInstance; 
    private String filePath;
    private String field;
    private String value;  
    
    private CommandLineParser(String[] arguments)
    {
        this.filePath = arguments[0];
        this.field = arguments[1];
        this.value = arguments.length == 3 ? arguments[2] : null ;   
    }
    
    /**
     * Constructs CommandLineParser and returns it's instance
     * 
     * @param arguments command line arguments
     * @return CommandLineParser instance
     */
    public static CommandLineParser parse(String[] arguments) 
    {
        if (parserInstance == null) 
            parserInstance = new CommandLineParser(arguments);
        
        return parserInstance;
    }
    
    /**
     * @return the filePath
     */
    public String getFilePath()
    {
        return this.filePath;
    }

    /**
     * @return the field
     */
    public String getField()
    {
        return this.field;
    }
    
    /**
     * @return the fieldValue
     */
    public String getValue()
    {
        return this.value;
    }
}
