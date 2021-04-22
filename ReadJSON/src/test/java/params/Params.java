package params;

/**
 * Param class stores values that are used for tests
 * 
 */
public abstract class Params //implements ProvidingOutputInfo
{
    private String pathToFile;
    protected String fieldToSearch;
    
    /**
     * @param pathToFile the path to file parameter
     * @param fieldToSearch the field to search parameter
     */
    public Params (String pathToFile, String fieldToSearch)
    {
        this.pathToFile = pathToFile;
        this.fieldToSearch = fieldToSearch;
    }
    
    /**
     * @return the pathToFile
     */
    public String getPathToFile()
    {
        return this.pathToFile;
    }
    
    /**
     * @return the fieldToSearch
     */
    public String getFieldToSearch()
    {
        return this.fieldToSearch;
    }
    
    /**
     * @return the expected output message
     */
    public abstract String getExpectedOutput();
    
     /**
     * @return the valueToSearch
     */
    public abstract Object getValueToSearch();
    
    /**
     * @return true if expected output is error message
     */
    public abstract boolean getErrorMessageIsExpected();
    
}
