package application;

/**
 * Parses command line arguments to separate string fields
 * 
 */
public class CommandLineParser
{
    private String filePath;
    private String field;
    private String value;  

    /**
     * Constructs CommandLineParser 
     * 
     * @param arguments command line arguments
     */
    public CommandLineParser(String[] arguments)
    {
        this.filePath = arguments[0];
        this.field = arguments[1];
        this.value = arguments.length == 3 ? arguments[2] : null ;   
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
