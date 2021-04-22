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
    private boolean validated;

    /**
     * Constructs CommandLineParser 
     * 
     * @param args command line arguments
     */
    public CommandLineParser(String[] args)
    {
        if (this.validateArgs(args))
        {
            this.filePath = args[0];
            this.field = args[1];
            this.value = args.length == 3 ? args[2] : null;
            this.validated = true;
        }
    }


    private boolean validateArgs(String[] args)
    {
        if (args.length < 2 || args[0].isEmpty() || args[1].isEmpty())
        {
            System.err.print("ReadJSONTask: [pathToFie] [specificField] [specificValue] (optional)");
            return false;
        }
        return true;
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

    /**
     * @return true if arguments are valid
     */
    public boolean validated()
    {
        return this.validated;
    }


}
