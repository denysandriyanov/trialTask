package params;


public class ErrorParams extends Params
{
    private String expectedErrorMessage;
    private final static String EMPTYSTRING = "";

    /**
     * @param pathToFile the path to file parameter
     * @param fieldToSearch the field to search parameter
     * @param expectedErrorMessage the expected error message parameter
     */
    public ErrorParams (String pathToFile,String fieldToSearch, String expectedErrorMessage)
    {
        super(pathToFile, fieldToSearch);
        this.expectedErrorMessage = expectedErrorMessage;
    }

    /**
     * @param pathToFile the path to tested file parameter
     * @param expectedErrorMessage the expected error message parameter
     */
    public ErrorParams (String pathToFile, String expectedErrorMessage)
    {
        this(pathToFile, EMPTYSTRING, expectedErrorMessage);
    }    

    @Override
    public String getExpectedOutput()
    {
        return this.expectedErrorMessage;
    }

    @Override
    public Object getValueToSearch()
    {
        return EMPTYSTRING;
    }

    @Override
    public boolean getErrorMessageIsExpected()
    {
        return true;
    }
}
