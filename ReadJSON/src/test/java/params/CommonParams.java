package params;

public class CommonParams extends Params
{
    private int expectedFieldsCount;
    private int expectedValuesCount;
    private String valueToSearch;

    /**
     * @param pathToFile the path to tested file parameter
     * @param fieldToSearch the field to search parameter
     * @param valueToSearch the value to search parameter
     * @param expectedFieldsCount the expected count of fields found parameter
     * @param expectedValuesCount the expected found of values found parameter
     */
    public CommonParams (String pathToFile, String fieldToSearch, String valueToSearch, int expectedFieldsCount, int expectedValuesCount)
    {
        super(pathToFile, fieldToSearch);
        this.valueToSearch = valueToSearch;
        this.expectedFieldsCount = expectedFieldsCount;
        this.expectedValuesCount = expectedValuesCount;
    }
    
    /**
     * @return the expectedFieldsCount
     */
    public int getExpectedFieldsCount()
    {
        return this.expectedFieldsCount;
    }

    /**
     * @return the expectedValuesCount
     */
    public int getExpectedValuesCount()
    {     
        return this.expectedValuesCount;
    }

    /**
     * @return the pathToFile
     */
    @Override
    public String getExpectedOutput()
    {
            return("-found " + this.expectedFieldsCount +" objects with field \"" + this.fieldToSearch + "\"\r\n"
                            + "-found " + this.expectedValuesCount +" objects where field \"" 
                            + this.fieldToSearch + "\" equals \"" + this.valueToSearch + "\"\r\n");
    }
 
    /**
     * @return the valueToSearch
     */
    @Override
    public Object getValueToSearch()
    {
        return this.valueToSearch;
    }

    @Override
    public boolean getErrorMessageIsExpected()
    {
        return false;
    }
}
